package com.taobao.accs.net;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.Config;
import anet.channel.SessionCenter;
import anet.channel.entity.ENV;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.StrategyTemplate;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.ss.android.pushmanager.PushCommonConstants;
import com.tachikoma.core.utility.UriUtil;
import com.taobao.accs.ACCSManager;
import com.taobao.accs.AccsClientConfig;
import com.taobao.accs.AccsException;
import com.taobao.accs.ErrorCode;
import com.taobao.accs.common.Constants;
import com.taobao.accs.common.ThreadPoolExecutorFactory;
import com.taobao.accs.data.Message;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;
import com.taobao.accs.utl.UtilityImpl;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class b {
    public static final int ACCS_RECEIVE_TIMEOUT = 40000;
    public static final int INAPP = 1;
    public static final int SERVICE = 0;
    protected static int n = 1;
    public String a;
    public String b;
    protected int c;
    protected Context d;
    protected com.taobao.accs.data.d e;
    public com.taobao.accs.client.b h;
    public AccsClientConfig i;
    protected String j;
    public String m;
    private Runnable p;
    private ScheduledFuture<?> q;
    protected int f = 0;
    private long o = 0;
    protected volatile boolean g = false;
    protected String k = null;
    protected LinkedHashMap<Integer, Message> l = new LinkedHashMap<Integer, Message>() { // from class: com.taobao.accs.net.BaseConnection$1
        @Override // java.util.LinkedHashMap
        protected boolean removeEldestEntry(Map.Entry<Integer, Message> entry) {
            return size() > 10;
        }
    };

    protected String a(int i) {
        switch (i) {
            case 1:
                return "CONNECTED";
            case 2:
                return "CONNECTING";
            case 3:
                return "DISCONNECTED";
            case 4:
                return "DISCONNECTING";
            default:
                return "DISCONNECTED";
        }
    }

    public abstract void a();

    public abstract void a(Message message, boolean z);

    public abstract void a(String str, boolean z, String str2);

    public abstract void a(boolean z, boolean z2);

    public abstract boolean a(String str);

    public abstract void b();

    public abstract com.taobao.accs.ut.a.c c();

    public abstract String d();

    public void e() {
    }

    protected boolean h() {
        return true;
    }

    public boolean m() {
        return false;
    }

    protected b(Context context, int i, String str) throws AccsException {
        this.b = "";
        this.c = i;
        this.d = context.getApplicationContext();
        AccsClientConfig configByTag = AccsClientConfig.getConfigByTag(str);
        if (configByTag == null) {
            ALog.e(d(), "BaseConnection config null!!", new Object[0]);
            try {
                configByTag = new AccsClientConfig.Builder().setAppKey(ACCSManager.getDefaultAppkey(context)).setTag(str).build();
            } catch (AccsException e) {
                ALog.e(d(), "BaseConnection build config", e, new Object[0]);
            }
        }
        this.m = configByTag.getTag();
        this.b = configByTag.getAppKey();
        this.i = configByTag;
        this.e = new com.taobao.accs.data.d(context, this);
        this.e.b = this.c;
        ALog.d(d(), "new connection", new Object[0]);
    }

    public void b(Message message, boolean z) {
        if (!message.isAck && !UtilityImpl.k(this.d)) {
            ALog.e(d(), "sendMessage ready no network", Constants.KEY_DATA_ID, message.dataId);
            this.e.a(message, -13);
            return;
        }
        long jA = message.getType() != 2 ? this.e.d.a(message.serviceId, message.bizId) : 0L;
        if (jA == -1) {
            ALog.e(d(), "sendMessage ready server limit high", Constants.KEY_DATA_ID, message.dataId);
            this.e.a(message, ErrorCode.SERVIER_HIGH_LIMIT);
            return;
        }
        if (jA == -1000) {
            ALog.e(d(), "sendMessage ready server limit high for brush", Constants.KEY_DATA_ID, message.dataId);
            this.e.a(message, ErrorCode.SERVIER_HIGH_LIMIT_BRUSH);
            return;
        }
        if (jA > 0) {
            if (System.currentTimeMillis() > this.o) {
                message.delyTime = jA;
            } else {
                message.delyTime = (this.o + jA) - System.currentTimeMillis();
            }
            this.o = System.currentTimeMillis() + message.delyTime;
            ALog.e(d(), "sendMessage ready", Constants.KEY_DATA_ID, message.dataId, "type", Message.MsgType.name(message.getType()), "delay", Long.valueOf(message.delyTime));
        } else if ("accs".equals(message.serviceId)) {
            ALog.e(d(), "sendMessage ready", Constants.KEY_DATA_ID, message.dataId, "type", Message.MsgType.name(message.getType()), "delay", Long.valueOf(message.delyTime));
        } else if (ALog.isPrintLog(ALog.Level.D)) {
            ALog.d(d(), "sendMessage ready", Constants.KEY_DATA_ID, message.dataId, "type", Message.MsgType.name(message.getType()), "delay", Long.valueOf(message.delyTime));
        }
        try {
            if (TextUtils.isEmpty(this.j)) {
                this.j = UtilityImpl.l(this.d);
            }
            if (!message.isTimeOut()) {
                a(message, z);
            } else {
                this.e.a(message, -9);
            }
        } catch (RejectedExecutionException unused) {
            this.e.a(message, ErrorCode.MESSAGE_QUEUE_FULL);
            ALog.e(d(), "sendMessage ready queue full", "size", Integer.valueOf(ThreadPoolExecutorFactory.getSendScheduledExecutor().getQueue().size()));
        }
    }

    protected void a(String str, boolean z, long j) {
        ThreadPoolExecutorFactory.getScheduledExecutor().schedule(new c(this, str, z), j, TimeUnit.MILLISECONDS);
    }

    protected boolean a(Message message, int i) {
        boolean z = true;
        try {
        } catch (Throwable th) {
            th = th;
            z = false;
        }
        if (message.retryTimes > 3) {
            return false;
        }
        message.retryTimes++;
        message.delyTime = i;
        ALog.e(d(), "reSend dataid:" + message.dataId + " retryTimes:" + message.retryTimes, new Object[0]);
        b(message, true);
        try {
            if (message.getNetPermanceMonitor() != null) {
                message.getNetPermanceMonitor().take_date = 0L;
                message.getNetPermanceMonitor().to_tnet_date = 0L;
                message.getNetPermanceMonitor().retry_times = message.retryTimes;
                if (message.retryTimes == 1) {
                    com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_RESEND, "total", 0.0d);
                }
            }
        } catch (Throwable th2) {
            th = th2;
            this.e.a(message, -8);
            ALog.e(d(), "reSend error", th, new Object[0]);
            return z;
        }
        return z;
    }

    protected void b(int i) {
        if (i < 0) {
            ALog.e(d(), "reSendAck", Constants.KEY_DATA_ID, Integer.valueOf(i));
            Message message = this.l.get(Integer.valueOf(i));
            if (message != null) {
                a(message, 5000);
                com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_RESEND, BaseMonitor.COUNT_ACK, 0.0d);
            }
        }
    }

    protected void f() {
        if (this.p == null) {
            this.p = new d(this);
        }
        g();
        this.q = ThreadPoolExecutorFactory.getScheduledExecutor().schedule(this.p, 40000L, TimeUnit.MILLISECONDS);
    }

    protected void g() {
        if (this.q != null) {
            this.q.cancel(true);
        }
    }

    public String b(String str) {
        String inappHost = this.i.getInappHost();
        StringBuilder sb = new StringBuilder();
        sb.append(UriUtil.HTTPS_PREFIX);
        sb.append(TextUtils.isEmpty(str) ? "" : str);
        sb.append(inappHost);
        String string = sb.toString();
        try {
            StringBuilder sb2 = new StringBuilder();
            sb2.append(UriUtil.HTTPS_PREFIX);
            if (TextUtils.isEmpty(str)) {
                str = "";
            }
            sb2.append(str);
            sb2.append(inappHost);
            return sb2.toString();
        } catch (Throwable th) {
            ALog.e("InAppConnection", "getHost", th, new Object[0]);
            return string;
        }
    }

    protected void a(Context context) {
        try {
            ENV env = ENV.ONLINE;
            if (AccsClientConfig.mEnv == 2) {
                env = ENV.TEST;
                SessionCenter.switchEnvironment(env);
            } else if (AccsClientConfig.mEnv == 1) {
                env = ENV.PREPARE;
                SessionCenter.switchEnvironment(env);
            }
            SessionCenter.init(context, new Config.Builder().setAppkey(this.b).setAppSecret(this.i.getAppSecret()).setAuthCode(this.i.getAuthCode()).setEnv(env).setTag(this.i.getAppKey()).build());
            String str = (this.i.getInappPubKey() == 10 || this.i.getInappPubKey() == 11) ? PushCommonConstants.VALUE_OPEN : "acs";
            ALog.i(d(), "init awcn register new conn protocol host:", this.i.getInappHost());
            StrategyTemplate.getInstance().registerConnProtocol(this.i.getInappHost(), ConnProtocol.valueOf("http2", "0rtt", str, false));
        } catch (Throwable th) {
            ALog.e(d(), "initAwcn", th, new Object[0]);
        }
    }

    public void b(Message message, int i) {
        this.e.a(message, i);
    }

    public String i() {
        return this.b;
    }

    public com.taobao.accs.client.b j() {
        if (this.h == null) {
            ALog.d(d(), "new ClientManager", Constants.KEY_CONFIG_TAG, this.m);
            this.h = new com.taobao.accs.client.b(this.d, this.m);
        }
        return this.h;
    }

    public void k() {
        try {
            ThreadPoolExecutorFactory.schedule(new e(this), DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            ALog.w(d(), "startChannelService", th, new Object[0]);
        }
    }

    protected String c(String str) {
        String strEncode;
        String strL = UtilityImpl.l(this.d);
        try {
            strEncode = URLEncoder.encode(strL);
        } catch (Throwable th) {
            ALog.e(d(), "buildAuthUrl", th, new Object[0]);
            strEncode = strL;
        }
        String strA = UtilityImpl.a(this.d, i(), this.i.getAppSecret(), strL, this.m);
        StringBuilder sb = new StringBuilder(ApkDownloadComplianceInterface.INSTALL_BITS);
        sb.append(str);
        sb.append("auth?1=");
        sb.append(strEncode);
        sb.append("&2=");
        sb.append(strA);
        sb.append("&3=");
        sb.append(i());
        if (this.k != null) {
            sb.append("&4=");
            sb.append(this.k);
        }
        sb.append("&5=");
        sb.append(this.c);
        sb.append("&6=");
        sb.append(UtilityImpl.g(this.d));
        sb.append("&7=");
        sb.append(UtilityImpl.m(this.d));
        sb.append("&8=");
        sb.append(this.c == 1 ? "1.1.2" : Integer.valueOf(Constants.SDK_VERSION_CODE));
        sb.append("&9=");
        sb.append(System.currentTimeMillis());
        sb.append("&10=");
        sb.append(1);
        sb.append("&11=");
        sb.append(Build.VERSION.SDK_INT);
        sb.append("&12=");
        sb.append(this.d.getPackageName());
        sb.append("&13=");
        sb.append(UtilityImpl.o(this.d));
        sb.append("&14=");
        sb.append(this.a);
        sb.append("&15=");
        sb.append(UtilityImpl.c(Build.MODEL));
        sb.append("&16=");
        sb.append(UtilityImpl.c(Build.BRAND));
        sb.append("&17=");
        sb.append(Constants.SDK_VERSION_CODE);
        sb.append("&19=");
        sb.append(!l() ? 1 : 0);
        sb.append("&20=");
        sb.append(this.i.getStoreId());
        if (com.taobao.accs.utl.t.g()) {
            sb.append("&21=");
            sb.append(n);
        }
        return sb.toString();
    }

    public boolean l() {
        return 2 == this.i.getSecurity();
    }

    public void c(int i) {
        n = i != 1 ? 0 : 1;
    }
}
