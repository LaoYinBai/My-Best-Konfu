package com.taobao.accs.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.text.TextUtils;
import anet.channel.AwcnConfig;
import anet.channel.DataFrameCb;
import anet.channel.IAuth;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.SessionInfo;
import anet.channel.request.Request;
import anet.channel.session.TnetSpdySession;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.StrategyTemplate;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.android.pushmanager.PushCommonConstants;
import com.tachikoma.core.utility.UriUtil;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.base.TaoBaseService;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.data.Message;
import com.taobao.accs.ut.monitor.NetPerformanceMonitor;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class j extends b implements DataFrameCb {
    private boolean o;
    private long p;
    private ScheduledFuture q;
    private s r;
    private AtomicBoolean s;
    private BroadcastReceiver t;
    private Runnable u;
    private Runnable v;
    private Set<String> w;

    @Override // com.taobao.accs.net.b
    public void a(boolean z, boolean z2) {
    }

    @Override // com.taobao.accs.net.b
    public com.taobao.accs.ut.a.c c() {
        return null;
    }

    public j(Context context, int i, String str) {
        super(context, i, str);
        this.o = true;
        this.p = 3600000L;
        this.s = new AtomicBoolean(false);
        this.t = new BroadcastReceiver() { // from class: com.taobao.accs.net.InAppConnection$1
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context2, Intent intent) {
                try {
                    TaoBaseService.ConnectInfo connectInfo = (TaoBaseService.ConnectInfo) intent.getExtras().get(Constants.KEY_CONNECT_INFO);
                    if (this.a.b((String) null).equals(connectInfo.host)) {
                        this.a.s.set(connectInfo.connected);
                    }
                } catch (Throwable th) {
                    ALog.e(this.a.d(), "receive connect info error", th, new Object[0]);
                }
            }
        };
        this.u = new k(this);
        this.v = new p(this);
        this.w = Collections.synchronizedSet(new HashSet());
        if (!com.taobao.accs.utl.t.a(true)) {
            String strC = UtilityImpl.c(this.d, "inapp");
            ALog.d(d(), "config tnet log path:" + strC, new Object[0]);
            if (!TextUtils.isEmpty(strC)) {
                Session.configTnetALog(context, strC, UtilityImpl.TNET_FILE_SIZE, 5);
            }
        }
        ThreadPoolExecutorFactory.getScheduledExecutor().schedule(this.v, 120000L, TimeUnit.MILLISECONDS);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Constants.ACTION_CONNECT_INFO);
        context.registerReceiver(this.t, intentFilter);
    }

    @Override // com.taobao.accs.net.b
    public synchronized void a() {
        ALog.d(d(), PointCategory.START, new Object[0]);
        this.o = true;
        a(this.d);
    }

    @Override // com.taobao.accs.net.b
    public void a(Message message, boolean z) {
        if (!this.o || message == null) {
            ALog.e(d(), "not running or msg null! " + this.o, new Object[0]);
            return;
        }
        try {
            if (ThreadPoolExecutorFactory.getSendScheduledExecutor().getQueue().size() > 1000) {
                throw new RejectedExecutionException("accs");
            }
            ScheduledFuture<?> scheduledFutureSchedule = ThreadPoolExecutorFactory.getSendScheduledExecutor().schedule(new l(this, message), message.delyTime, TimeUnit.MILLISECONDS);
            if (message.getType() == 1 && message.cunstomDataId != null) {
                if (message.isControlFrame() && a(message.cunstomDataId)) {
                    this.e.b(message);
                }
                this.e.a.put(message.cunstomDataId, scheduledFutureSchedule);
            }
            NetPerformanceMonitor netPermanceMonitor = message.getNetPermanceMonitor();
            if (netPermanceMonitor != null) {
                netPermanceMonitor.setDeviceId(UtilityImpl.l(this.d));
                netPermanceMonitor.setConnType(this.c);
                netPermanceMonitor.onEnterQueueData();
            }
        } catch (RejectedExecutionException unused) {
            this.e.a(message, ErrorCode.MESSAGE_QUEUE_FULL);
            ALog.e(d(), "send queue full count:" + ThreadPoolExecutorFactory.getSendScheduledExecutor().getQueue().size(), new Object[0]);
        } catch (Throwable th) {
            this.e.a(message, -8);
            ALog.e(d(), "send error", th, new Object[0]);
        }
    }

    @Override // com.taobao.accs.net.b
    protected void a(String str, boolean z, long j) {
        ThreadPoolExecutorFactory.getScheduledExecutor().schedule(new m(this, str, z), j, TimeUnit.MILLISECONDS);
    }

    @Override // com.taobao.accs.net.b
    public void e() {
        ALog.e(d(), "shut down", new Object[0]);
        this.o = false;
    }

    @Override // com.taobao.accs.net.b
    public void b() {
        this.f = 0;
    }

    @Override // com.taobao.accs.net.b
    public void a(String str, boolean z, String str2) {
        Session session;
        try {
            Message messageB = this.e.b(str);
            if (messageB != null && messageB.host != null && (session = SessionCenter.getInstance(this.i.getAppKey()).get(messageB.host.toString(), 0L)) != null) {
                if (z) {
                    session.close(true);
                } else {
                    session.ping(true);
                }
            }
        } catch (Exception e) {
            ALog.e(d(), "onTimeOut", e, new Object[0]);
        }
    }

    public void onDataReceive(TnetSpdySession tnetSpdySession, byte[] bArr, int i, int i2) {
        ThreadPoolExecutorFactory.getScheduledExecutor().execute(new n(this, i2, bArr, tnetSpdySession));
    }

    public void onException(int i, int i2, boolean z, String str) {
        ALog.e(d(), "errorId:" + i2 + "detail:" + str + " dataId:" + i + " needRetry:" + z, new Object[0]);
        ThreadPoolExecutorFactory.getScheduledExecutor().execute(new o(this, i, z, i2));
    }

    @Override // com.taobao.accs.net.b
    public boolean a(String str) {
        if (str == null) {
            return false;
        }
        ScheduledFuture<?> scheduledFuture = this.e.a.get(str);
        boolean zCancel = scheduledFuture != null ? scheduledFuture.cancel(false) : false;
        if (zCancel) {
            ALog.e(d(), "cancel", "customDataId", str);
        }
        return zCancel;
    }

    public void a(JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            ALog.e(d(), "onReceiveAccsHeartbeatResp response data is null", new Object[0]);
            return;
        }
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i(d(), "onReceiveAccsHeartbeatResp", "data", jSONObject);
        }
        try {
            int i = jSONObject.getInt("timeInterval");
            if (i == -1) {
                if (this.q != null) {
                    this.q.cancel(true);
                    return;
                }
                return;
            }
            long j = i * 1000;
            if (this.p != j) {
                if (i == 0) {
                    j = 3600000;
                }
                this.p = j;
                if (this.q != null) {
                    this.q.cancel(true);
                }
                this.q = ThreadPoolExecutorFactory.getScheduledExecutor().scheduleAtFixedRate(this.u, this.p, this.p, TimeUnit.MILLISECONDS);
            }
        } catch (JSONException e) {
            ALog.e(d(), "onReceiveAccsHeartbeatResp", "e", e.getMessage());
        }
    }

    @Override // com.taobao.accs.net.b
    public String d() {
        return "InAppConn_" + this.m;
    }

    @Override // com.taobao.accs.net.b
    protected void a(Context context) {
        boolean z;
        try {
            if (this.g) {
                return;
            }
            super.a(context);
            String inappHost = this.i.getInappHost();
            if (h() && this.i.isKeepalive()) {
                z = true;
            } else {
                ALog.d(d(), "initAwcn close keepalive", new Object[0]);
                z = false;
            }
            if (com.taobao.accs.utl.t.g()) {
                AwcnConfig.setAccsSessionCreateForbiddenInBg(false);
            }
            a(SessionCenter.getInstance(this.i.getAppKey()), inappHost, z);
            this.g = true;
            ALog.i(d(), "initAwcn success!", new Object[0]);
        } catch (Throwable th) {
            ALog.e(d(), "initAwcn", th, new Object[0]);
        }
    }

    public void a(SessionCenter sessionCenter, String str, boolean z) {
        if (this.w.contains(str)) {
            return;
        }
        if (com.taobao.accs.utl.t.g()) {
            if (this.r == null) {
                this.r = new s();
            }
        } else {
            this.r = null;
        }
        sessionCenter.registerSessionInfo(SessionInfo.create(str, z, true, new a(this, str), this.r, this));
        sessionCenter.registerPublicKey(str, this.i.getInappPubKey());
        this.w.add(str);
        ALog.i(d(), "registerSessionInfo", Constants.KEY_HOST, str);
    }

    public void a(AccsClientConfig accsClientConfig) {
        if (accsClientConfig == null) {
            ALog.i(d(), "updateConfig null", new Object[0]);
            return;
        }
        if (accsClientConfig.equals(this.i)) {
            ALog.w(d(), "updateConfig not any changed", new Object[0]);
            return;
        }
        if (!this.g) {
            if (com.taobao.accs.utl.t.g() || UtilityImpl.isMainProcess(this.d)) {
                this.i = accsClientConfig;
                a(this.d);
                return;
            }
            return;
        }
        try {
            boolean z = true;
            ALog.w(d(), "updateConfig", "old", this.i, "new", accsClientConfig);
            String inappHost = this.i.getInappHost();
            String inappHost2 = accsClientConfig.getInappHost();
            SessionCenter sessionCenter = SessionCenter.getInstance(this.i.getAppKey());
            if (sessionCenter == null) {
                ALog.w(d(), "updateConfig not need update", new Object[0]);
                return;
            }
            sessionCenter.unregisterSessionInfo(inappHost);
            ALog.w(d(), "updateConfig unregisterSessionInfo", Constants.KEY_HOST, inappHost);
            if (this.w.contains(inappHost)) {
                this.w.remove(inappHost);
                ALog.w(d(), "updateConfig removeSessionRegistered", "oldHost", inappHost);
            }
            this.i = accsClientConfig;
            this.b = this.i.getAppKey();
            this.m = this.i.getTag();
            String str = (this.i.getInappPubKey() == 10 || this.i.getInappPubKey() == 11) ? PushCommonConstants.VALUE_OPEN : "acs";
            ALog.i(d(), "update config register new conn protocol host:", this.i.getInappHost());
            StrategyTemplate.getInstance().registerConnProtocol(this.i.getInappHost(), ConnProtocol.valueOf("http2", "0rtt", str, false));
            if (!h() || !this.i.isKeepalive()) {
                ALog.i(d(), "updateConfig close keepalive", new Object[0]);
                z = false;
            }
            a(sessionCenter, inappHost2, z);
        } catch (Throwable th) {
            ALog.e(d(), "updateConfig", th, new Object[0]);
        }
    }

    public static class a implements IAuth {
        private String a;
        private int b;
        private String c;
        private b d;

        public a(b bVar, String str) {
            this.c = bVar.d();
            this.a = bVar.c(UriUtil.HTTPS_PREFIX + str + "/accs/");
            this.b = bVar.c;
            this.d = bVar;
        }

        public void auth(Session session, IAuth.AuthCallback authCallback) {
            if (com.taobao.accs.utl.t.g()) {
                this.a = this.a.substring(0, this.a.indexOf("&21=")) + "&21=" + b.n;
            }
            ALog.e(this.c, BaseMonitor.ALARM_POINT_AUTH, "URL", this.a);
            session.request(new Request.Builder().setUrl(this.a).build(), new q(this, authCallback));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void n() {
        if (this.i.isAccsHeartbeatEnable()) {
            ALog.e(d(), "startAccsHeartBeat", new Object[0]);
            if (this.q != null) {
                this.q.cancel(true);
            }
            this.q = ThreadPoolExecutorFactory.getScheduledExecutor().scheduleAtFixedRate(this.u, this.p, this.p, TimeUnit.MILLISECONDS);
        }
    }

    @Override // com.taobao.accs.net.b
    public void c(int i) {
        super.c(i);
        if (this.r != null) {
            this.r.a(i);
        }
    }

    @Override // com.taobao.accs.net.b
    public boolean m() {
        return this.s.get();
    }
}
