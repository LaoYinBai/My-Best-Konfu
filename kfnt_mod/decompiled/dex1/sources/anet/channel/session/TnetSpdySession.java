package anet.channel.session;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.AwcnConfig;
import anet.channel.Config;
import anet.channel.DataFrameCb;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.IAuth;
import anet.channel.RequestCb;
import anet.channel.Session;
import anet.channel.SessionInfo;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.bytes.ByteArray;
import anet.channel.bytes.a;
import anet.channel.heartbeat.HeartbeatManager;
import anet.channel.heartbeat.IHeartbeat;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.security.ISecurity;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.ConnEvent;
import anet.channel.strategy.StrategyCenter;
import anet.channel.util.ALog;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpHelper;
import anet.channel.util.Utils;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.android.spdy.RequestPriority;
import org.android.spdy.SessionCb;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdyByteArray;
import org.android.spdy.SpdyDataProvider;
import org.android.spdy.SpdyErrorException;
import org.android.spdy.SpdyRequest;
import org.android.spdy.SpdySession;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;
import org.android.spdy.SuperviseConnectInfo;
import org.android.spdy.SuperviseData;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TnetSpdySession extends Session implements SessionCb {
    protected long A;
    protected int B;
    protected DataFrameCb C;
    protected IHeartbeat D;
    protected IAuth E;
    protected String F;
    protected ISecurity G;
    private int H;
    protected SpdyAgent w;
    protected SpdySession x;
    protected volatile boolean y;
    protected long z;

    public void bioPingRecvCallback(SpdySession spdySession, int i) {
    }

    static /* synthetic */ int d(TnetSpdySession tnetSpdySession) {
        int i = tnetSpdySession.H + 1;
        tnetSpdySession.H = i;
        return i;
    }

    public TnetSpdySession(Context context, anet.channel.entity.a aVar) {
        super(context, aVar);
        this.y = false;
        this.A = 0L;
        this.H = 0;
        this.B = -1;
        this.C = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
    }

    public void initConfig(Config config) {
        if (config != null) {
            this.F = config.getAppkey();
            this.G = config.getSecurity();
        }
    }

    public void initSessionInfo(SessionInfo sessionInfo) {
        if (sessionInfo != null) {
            this.C = sessionInfo.dataFrameCb;
            this.E = sessionInfo.auth;
            if (sessionInfo.isKeepAlive) {
                this.q.isKL = 1L;
                this.t = true;
                this.D = sessionInfo.heartbeat;
                if (this.D == null) {
                    this.D = HeartbeatManager.getDefaultHeartbeat();
                }
            }
        }
        if (AwcnConfig.isIdleSessionCloseEnable() && this.D == null) {
            this.D = new anet.channel.heartbeat.b();
        }
    }

    public void setTnetPublicKey(int i) {
        this.B = i;
    }

    @Override // anet.channel.Session
    public Cancelable request(Request request, RequestCb requestCb) {
        SpdyRequest spdyRequest;
        anet.channel.request.c cVar = anet.channel.request.c.NULL;
        RequestStatistic requestStatistic = request != null ? request.f475a : new RequestStatistic(this.d, null);
        requestStatistic.setConnType(this.j);
        if (requestStatistic.start == 0) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            requestStatistic.reqStart = jCurrentTimeMillis;
            requestStatistic.start = jCurrentTimeMillis;
        }
        requestStatistic.setIPAndPort(this.f, this.g);
        requestStatistic.ipRefer = this.k.getIpSource();
        requestStatistic.ipType = this.k.getIpType();
        requestStatistic.unit = this.l;
        if (request == null || requestCb == null) {
            if (requestCb != null) {
                requestCb.onFinish(ErrorConstant.ERROR_PARAM_ILLEGAL, ErrorConstant.getErrMsg(ErrorConstant.ERROR_PARAM_ILLEGAL), requestStatistic);
            }
            return cVar;
        }
        try {
            if (this.x != null && (this.n == 0 || this.n == 4)) {
                if (this.m) {
                    request.setDnsOptimize(this.e, this.g);
                }
                request.setUrlScheme(this.j.isSSL());
                URL url = request.getUrl();
                if (ALog.isPrintLog(2)) {
                    ALog.i("awcn.TnetSpdySession", "", request.getSeq(), "request URL", url.toString());
                    ALog.i("awcn.TnetSpdySession", "", request.getSeq(), "request Method", request.getMethod());
                    ALog.i("awcn.TnetSpdySession", "", request.getSeq(), "request headers", request.getHeaders());
                }
                if (TextUtils.isEmpty(this.h) || this.i <= 0) {
                    spdyRequest = new SpdyRequest(url, request.getMethod(), RequestPriority.DEFAULT_PRIORITY, -1, request.getConnectTimeout());
                } else {
                    spdyRequest = new SpdyRequest(url, url.getHost(), url.getPort(), this.h, this.i, request.getMethod(), RequestPriority.DEFAULT_PRIORITY, -1, request.getConnectTimeout(), 0);
                }
                spdyRequest.setRequestRdTimeoutMs(request.getReadTimeout());
                Map<String, String> headers = request.getHeaders();
                if (!headers.containsKey(HttpConstant.HOST)) {
                    spdyRequest.addHeaders(headers);
                    spdyRequest.addHeader(":host", this.m ? this.e : request.getHost());
                } else {
                    HashMap map = new HashMap(request.getHeaders());
                    String str = (String) map.remove(HttpConstant.HOST);
                    if (this.m) {
                        str = this.e;
                    }
                    map.put(":host", str);
                    spdyRequest.addHeaders(map);
                }
                SpdyDataProvider spdyDataProvider = new SpdyDataProvider(request.getBodyBytes());
                request.f475a.sendStart = System.currentTimeMillis();
                request.f475a.processTime = request.f475a.sendStart - request.f475a.start;
                int iSubmitRequest = this.x.submitRequest(spdyRequest, spdyDataProvider, this, new a(request, requestCb));
                if (ALog.isPrintLog(1)) {
                    ALog.d("awcn.TnetSpdySession", "", request.getSeq(), "streamId", Integer.valueOf(iSubmitRequest));
                }
                anet.channel.request.c cVar2 = new anet.channel.request.c(this.x, iSubmitRequest, request.getSeq());
                try {
                    this.q.requestCount++;
                    this.q.stdRCount++;
                    this.z = System.currentTimeMillis();
                    if (this.D != null) {
                        this.D.reSchedule();
                    }
                    if (this.j.isQuic()) {
                        request.f475a.putExtra("QuicConnectionID", this.x.getQuicConnectionID());
                    }
                    return cVar2;
                } catch (Exception unused) {
                    cVar = cVar2;
                    requestCb.onFinish(ErrorConstant.ERROR_EXCEPTION, ErrorConstant.getErrMsg(ErrorConstant.ERROR_EXCEPTION), requestStatistic);
                    return cVar;
                } catch (SpdyErrorException e) {
                    e = e;
                    cVar = cVar2;
                    if (e.SpdyErrorGetCode() == -1104 || e.SpdyErrorGetCode() == -1103) {
                        ALog.e("awcn.TnetSpdySession", "Send request on closed session!!!", this.p, new Object[0]);
                        notifyStatus(6, new anet.channel.entity.b(2));
                    }
                    requestCb.onFinish(ErrorConstant.ERROR_TNET_EXCEPTION, ErrorConstant.formatMsg(ErrorConstant.ERROR_TNET_EXCEPTION, String.valueOf(e.SpdyErrorGetCode())), requestStatistic);
                    return cVar;
                }
            }
            requestCb.onFinish(ErrorConstant.ERROR_SESSION_INVALID, ErrorConstant.getErrMsg(ErrorConstant.ERROR_SESSION_INVALID), request.f475a);
            return cVar;
        } catch (SpdyErrorException e2) {
            e = e2;
        } catch (Exception unused2) {
        }
    }

    @Override // anet.channel.Session
    public void sendCustomFrame(int i, byte[] bArr, int i2) {
        try {
            if (this.C == null) {
                return;
            }
            ALog.e("awcn.TnetSpdySession", "sendCustomFrame", this.p, "dataId", Integer.valueOf(i), "type", Integer.valueOf(i2));
            if (this.n == 4 && this.x != null) {
                if (bArr != null && bArr.length > 16384) {
                    a(i, ErrorConstant.ERROR_DATA_TOO_LARGE, false, null);
                    return;
                }
                this.x.sendCustomControlFrame(i, i2, 0, bArr == null ? 0 : bArr.length, bArr);
                this.q.requestCount++;
                this.q.cfRCount++;
                this.z = System.currentTimeMillis();
                if (this.D != null) {
                    this.D.reSchedule();
                    return;
                }
                return;
            }
            ALog.e("awcn.TnetSpdySession", "sendCustomFrame", this.p, "sendCustomFrame con invalid mStatus:" + this.n);
            a(i, ErrorConstant.ERROR_SESSION_INVALID, true, "session invalid");
        } catch (Exception e) {
            ALog.e("awcn.TnetSpdySession", "sendCustomFrame error", this.p, e, new Object[0]);
            a(i, ErrorConstant.ERROR_EXCEPTION, true, e.toString());
        } catch (SpdyErrorException e2) {
            ALog.e("awcn.TnetSpdySession", "sendCustomFrame error", this.p, e2, new Object[0]);
            a(i, ErrorConstant.ERROR_TNET_EXCEPTION, true, "SpdyErrorException: " + e2.toString());
        }
    }

    private void a(int i, int i2, boolean z, String str) {
        if (this.C != null) {
            this.C.onException(i, i2, z, str);
        }
    }

    @Override // anet.channel.Session
    public void connect() {
        if (this.n == 1 || this.n == 0 || this.n == 4) {
            return;
        }
        try {
            if (this.w == null) {
                c();
            }
            if (anet.channel.util.c.a() && anet.channel.strategy.utils.c.a(this.e)) {
                try {
                    this.f = anet.channel.util.c.a(this.e);
                } catch (Exception unused) {
                }
            }
            String strValueOf = String.valueOf(System.currentTimeMillis());
            ALog.e("awcn.TnetSpdySession", "connect", this.p, com.alipay.sdk.cons.c.f, this.f374c, "ip", this.f, "port", Integer.valueOf(this.g), "sessionId", strValueOf, "SpdyProtocol,", this.j, "proxyIp,", this.h, "proxyPort,", Integer.valueOf(this.i));
            org.android.spdy.SessionInfo sessionInfo = new org.android.spdy.SessionInfo(this.f, this.g, this.f374c + "_" + this.F, this.h, this.i, strValueOf, this, this.j.getTnetConType());
            sessionInfo.setConnectionTimeoutMs((int) (((float) this.r) * Utils.getNetworkTimeFactor()));
            if (this.j.isPublicKeyAuto() || this.j.isQuic() || this.j.isH2S()) {
                sessionInfo.setCertHost(this.m ? this.e : this.d);
            } else if (this.B >= 0) {
                sessionInfo.setPubKeySeqNum(this.B);
            } else {
                this.B = this.j.getTnetPublicKey(this.G != null ? this.G.isSecOff() : true);
                sessionInfo.setPubKeySeqNum(this.B);
            }
            this.x = this.w.createSession(sessionInfo);
            if (this.x.getRefCount() > 1) {
                ALog.e("awcn.TnetSpdySession", "get session ref count > 1!!!", this.p, new Object[0]);
                notifyStatus(0, new anet.channel.entity.b(1));
                b();
            } else {
                notifyStatus(1, null);
                this.z = System.currentTimeMillis();
                this.q.isProxy = !TextUtils.isEmpty(this.h) ? 1 : 0;
                this.q.isTunnel = "false";
                this.q.isBackground = GlobalAppRuntimeInfo.isAppBackground();
                this.A = 0L;
            }
        } catch (Throwable th) {
            notifyStatus(2, null);
            ALog.e("awcn.TnetSpdySession", "connect exception ", this.p, th, new Object[0]);
        }
    }

    @Override // anet.channel.Session
    public void close() {
        ALog.e("awcn.TnetSpdySession", "force close!", this.p, "session", this);
        notifyStatus(7, null);
        try {
            if (this.D != null) {
                this.D.stop();
                this.D = null;
            }
            if (this.x != null) {
                this.x.closeSession();
            }
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // anet.channel.Session
    public void onDisconnect() {
        this.y = false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // anet.channel.Session
    public Runnable getRecvTimeOutRunnable() {
        return new h(this);
    }

    @Override // anet.channel.Session
    public void ping(boolean z) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.TnetSpdySession", "ping", this.p, com.alipay.sdk.cons.c.f, this.f374c, "thread", Thread.currentThread().getName());
        }
        if (z) {
            try {
                if (this.x != null) {
                    if (this.n == 0 || this.n == 4) {
                        handleCallbacks(64, null);
                        this.y = true;
                        this.q.ppkgCount++;
                        this.x.submitPing();
                        if (ALog.isPrintLog(1)) {
                            ALog.d("awcn.TnetSpdySession", this.f374c + " submit ping ms:" + (System.currentTimeMillis() - this.z) + " force:" + z, this.p, new Object[0]);
                        }
                        setPingTimeout();
                        this.z = System.currentTimeMillis();
                        if (this.D != null) {
                            this.D.reSchedule();
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (this.q != null) {
                    this.q.closeReason = "session null";
                }
                ALog.e("awcn.TnetSpdySession", this.f374c + " session null", this.p, new Object[0]);
                close();
            } catch (SpdyErrorException e) {
                if (e.SpdyErrorGetCode() == -1104 || e.SpdyErrorGetCode() == -1103) {
                    ALog.e("awcn.TnetSpdySession", "Send request on closed session!!!", this.p, new Object[0]);
                    notifyStatus(6, new anet.channel.entity.b(2));
                }
                ALog.e("awcn.TnetSpdySession", "ping", this.p, e, new Object[0]);
            } catch (Exception e2) {
                ALog.e("awcn.TnetSpdySession", "ping", this.p, e2, new Object[0]);
            }
        }
    }

    protected void b() {
        if (this.E != null) {
            this.E.auth(this, new i(this));
            return;
        }
        notifyStatus(4, null);
        this.q.ret = 1;
        if (this.D != null) {
            this.D.start(this);
        }
    }

    @Override // anet.channel.Session
    public boolean isAvailable() {
        return this.n == 4;
    }

    private void c() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        SpdyAgent.enableDebug = false;
        this.w = SpdyAgent.getInstance(this.f372a, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
        if (this.G != null && !this.G.isSecOff()) {
            this.w.setAccsSslCallback(new j(this));
        }
        if (AwcnConfig.isTnetHeaderCacheEnable()) {
            return;
        }
        try {
            this.w.getClass().getDeclaredMethod("disableHeaderCache", new Class[0]).invoke(this.w, new Object[0]);
            ALog.i("awcn.TnetSpdySession", "tnet disableHeaderCache", null, new Object[0]);
        } catch (Exception e) {
            ALog.e("awcn.TnetSpdySession", "tnet disableHeaderCache", null, e, new Object[0]);
        }
    }

    public void spdySessionConnectCB(SpdySession spdySession, SuperviseConnectInfo superviseConnectInfo) {
        this.q.connectionTime = superviseConnectInfo.connectTime;
        this.q.sslTime = superviseConnectInfo.handshakeTime;
        this.q.sslCalTime = superviseConnectInfo.doHandshakeTime;
        this.q.netType = NetworkStatusHelper.getNetworkSubType();
        this.A = System.currentTimeMillis();
        notifyStatus(0, new anet.channel.entity.b(1));
        b();
        ALog.e("awcn.TnetSpdySession", "spdySessionConnectCB connect", this.p, "connectTime", Integer.valueOf(superviseConnectInfo.connectTime), "sslTime:", Integer.valueOf(superviseConnectInfo.handshakeTime));
    }

    public void spdyPingRecvCallback(SpdySession spdySession, long j, Object obj) {
        if (ALog.isPrintLog(2)) {
            ALog.i("awcn.TnetSpdySession", "ping receive", this.p, HttpConstant.HOST, this.f374c, "id", Long.valueOf(j));
        }
        if (j < 0) {
            return;
        }
        this.y = false;
        this.H = 0;
        if (this.D != null) {
            this.D.reSchedule();
        }
        handleCallbacks(128, null);
    }

    public void spdyCustomControlFrameRecvCallback(SpdySession spdySession, Object obj, int i, int i2, int i3, int i4, byte[] bArr) {
        ALog.e("awcn.TnetSpdySession", "[spdyCustomControlFrameRecvCallback]", this.p, "len", Integer.valueOf(i4), "frameCb", this.C);
        if (ALog.isPrintLog(1) && i4 < 512) {
            String str = "";
            for (byte b2 : bArr) {
                str = str + Integer.toHexString(b2 & 255) + " ";
            }
            ALog.e("awcn.TnetSpdySession", null, this.p, "str", str);
        }
        if (this.C != null) {
            this.C.onDataReceive(this, bArr, i, i2);
        } else {
            ALog.e("awcn.TnetSpdySession", "AccsFrameCb is null", this.p, new Object[0]);
            AppMonitor.getInstance().commitStat(new ExceptionStatistic(ErrorConstant.ERROR_ACCS_CUSTOM_FRAME_CB_NULL, null, "rt"));
        }
        this.q.inceptCount++;
        if (this.D != null) {
            this.D.reSchedule();
        }
    }

    public void spdySessionFailedError(SpdySession spdySession, int i, Object obj) {
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e("awcn.TnetSpdySession", "[spdySessionFailedError]session clean up failed!", null, e, new Object[0]);
            }
        }
        notifyStatus(2, new anet.channel.entity.b(256, i, "tnet connect fail"));
        ALog.e("awcn.TnetSpdySession", null, this.p, " errorId:", Integer.valueOf(i));
        this.q.errorCode = i;
        this.q.ret = 0;
        this.q.netType = NetworkStatusHelper.getNetworkSubType();
        AppMonitor.getInstance().commitStat(this.q);
        AppMonitor.getInstance().commitAlarm(this.q.getAlarmObject());
    }

    public void spdySessionCloseCallback(SpdySession spdySession, Object obj, SuperviseConnectInfo superviseConnectInfo, int i) throws JSONException {
        ALog.e("awcn.TnetSpdySession", "spdySessionCloseCallback", this.p, " errorCode:", Integer.valueOf(i));
        if (this.D != null) {
            this.D.stop();
            this.D = null;
        }
        if (spdySession != null) {
            try {
                spdySession.cleanUp();
            } catch (Exception e) {
                ALog.e("awcn.TnetSpdySession", "session clean up failed!", null, e, new Object[0]);
            }
        }
        notifyStatus(6, new anet.channel.entity.b(2));
        if (superviseConnectInfo != null) {
            this.q.requestCount = superviseConnectInfo.reused_counter;
            this.q.liveTime = superviseConnectInfo.keepalive_period_second;
            try {
                if (this.j.isQuic()) {
                    this.q.extra = new JSONObject();
                    this.q.extra.put("QuicConnectionID", this.x.getQuicConnectionID());
                    this.q.extra.put("retransmissionRate", superviseConnectInfo.retransmissionRate);
                    this.q.extra.put("lossRate", superviseConnectInfo.lossRate);
                    this.q.extra.put("tlpCount", superviseConnectInfo.tlpCount);
                    this.q.extra.put("rtoCount", superviseConnectInfo.rtoCount);
                }
            } catch (JSONException unused) {
            }
        }
        if (this.q.errorCode == 0) {
            this.q.errorCode = i;
        }
        this.q.lastPingInterval = (int) (System.currentTimeMillis() - this.z);
        AppMonitor.getInstance().commitStat(this.q);
        AppMonitor.getInstance().commitAlarm(this.q.getAlarmObject());
    }

    public void spdyCustomControlFrameFailCallback(SpdySession spdySession, Object obj, int i, int i2) {
        ALog.e("awcn.TnetSpdySession", "spdyCustomControlFrameFailCallback", this.p, "dataId", Integer.valueOf(i));
        a(i, i2, true, "tnet error");
    }

    public byte[] getSSLMeta(SpdySession spdySession) {
        String domain = spdySession.getDomain();
        if (TextUtils.isEmpty(domain)) {
            ALog.i("awcn.TnetSpdySession", "get sslticket host is null", null, new Object[0]);
            return null;
        }
        try {
            if (this.G == null) {
                return null;
            }
            return this.G.getBytes(this.f372a, "accs_ssl_key2_" + domain);
        } catch (Throwable th) {
            ALog.e("awcn.TnetSpdySession", "getSSLMeta", null, th, new Object[0]);
            return null;
        }
    }

    public int putSSLMeta(SpdySession spdySession, byte[] bArr) {
        String domain = spdySession.getDomain();
        if (TextUtils.isEmpty(domain)) {
            return -1;
        }
        try {
            if (this.G == null) {
                return -1;
            }
            ISecurity iSecurity = this.G;
            Context context = this.f372a;
            StringBuilder sb = new StringBuilder();
            sb.append("accs_ssl_key2_");
            sb.append(domain);
            return iSecurity.saveBytes(context, sb.toString(), bArr) ? 0 : -1;
        } catch (Throwable th) {
            ALog.e("awcn.TnetSpdySession", "putSSLMeta", null, th, new Object[0]);
            return -1;
        }
    }

    private class a extends anet.channel.session.a {

        /* renamed from: b, reason: collision with root package name */
        private Request f492b;

        /* renamed from: c, reason: collision with root package name */
        private RequestCb f493c;
        private int d = 0;
        private long e = 0;

        public a(Request request, RequestCb requestCb) {
            this.f492b = request;
            this.f493c = requestCb;
        }

        @Override // anet.channel.session.a
        public void spdyDataChunkRecvCB(SpdySession spdySession, boolean z, long j, SpdyByteArray spdyByteArray, Object obj) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.TnetSpdySession", "spdyDataChunkRecvCB", this.f492b.getSeq(), "len", Integer.valueOf(spdyByteArray.getDataLength()), "fin", Boolean.valueOf(z));
            }
            this.e += spdyByteArray.getDataLength();
            this.f492b.f475a.recDataSize += spdyByteArray.getDataLength();
            if (TnetSpdySession.this.D != null) {
                TnetSpdySession.this.D.reSchedule();
            }
            if (this.f493c != null) {
                ByteArray byteArrayA = a.C0007a.f408a.a(spdyByteArray.getByteArray(), spdyByteArray.getDataLength());
                spdyByteArray.recycle();
                this.f493c.onDataReceive(byteArrayA, z);
            }
            TnetSpdySession.this.handleCallbacks(32, null);
        }

        @Override // anet.channel.session.a
        public void spdyStreamCloseCallback(SpdySession spdySession, long j, int i, Object obj, SuperviseData superviseData) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.TnetSpdySession", "spdyStreamCloseCallback", this.f492b.getSeq(), "streamId", Long.valueOf(j), AccountMonitorConstants.CommonParameter.ERROR, Integer.valueOf(i));
            }
            String msg = "SUCCESS";
            if (i != 0) {
                this.d = ErrorConstant.ERROR_TNET_REQUEST_FAIL;
                msg = ErrorConstant.formatMsg(ErrorConstant.ERROR_TNET_REQUEST_FAIL, String.valueOf(i));
                if (i != -2005) {
                    AppMonitor.getInstance().commitStat(new ExceptionStatistic(ErrorConstant.ERROR_TNET_EXCEPTION, msg, this.f492b.f475a, null));
                }
                ALog.e("awcn.TnetSpdySession", "spdyStreamCloseCallback error", this.f492b.getSeq(), "session", TnetSpdySession.this.p, "status code", Integer.valueOf(i), "URL", this.f492b.getHttpUrl().simpleUrlString());
            }
            this.f492b.f475a.tnetErrorCode = i;
            a(superviseData, this.d, msg);
            if (this.f493c != null) {
                this.f493c.onFinish(this.d, msg, this.f492b.f475a);
            }
            if (i == -2004) {
                if (!TnetSpdySession.this.y) {
                    TnetSpdySession.this.ping(true);
                }
                if (TnetSpdySession.d(TnetSpdySession.this) >= 2) {
                    ConnEvent connEvent = new ConnEvent();
                    connEvent.isSuccess = false;
                    StrategyCenter.getInstance().notifyConnEvent(TnetSpdySession.this.d, TnetSpdySession.this.k, connEvent);
                    TnetSpdySession.this.close(true);
                }
            }
        }

        private void a(SuperviseData superviseData, int i, String str) {
            try {
                this.f492b.f475a.rspEnd = System.currentTimeMillis();
                if (this.f492b.f475a.isDone.get()) {
                    return;
                }
                if (i > 0) {
                    this.f492b.f475a.ret = 1;
                }
                this.f492b.f475a.statusCode = i;
                this.f492b.f475a.msg = str;
                if (superviseData != null) {
                    this.f492b.f475a.rspEnd = superviseData.responseEnd;
                    this.f492b.f475a.sendBeforeTime = superviseData.sendStart - superviseData.requestStart;
                    this.f492b.f475a.sendDataTime = superviseData.sendEnd - this.f492b.f475a.sendStart;
                    this.f492b.f475a.firstDataTime = superviseData.responseStart - superviseData.sendEnd;
                    this.f492b.f475a.recDataTime = superviseData.responseEnd - superviseData.responseStart;
                    this.f492b.f475a.sendDataSize = superviseData.bodySize + superviseData.compressSize;
                    this.f492b.f475a.recDataSize = this.e + superviseData.recvUncompressSize;
                    this.f492b.f475a.reqHeadInflateSize = superviseData.uncompressSize;
                    this.f492b.f475a.reqHeadDeflateSize = superviseData.compressSize;
                    this.f492b.f475a.reqBodyInflateSize = superviseData.bodySize;
                    this.f492b.f475a.reqBodyDeflateSize = superviseData.bodySize;
                    this.f492b.f475a.rspHeadDeflateSize = superviseData.recvCompressSize;
                    this.f492b.f475a.rspHeadInflateSize = superviseData.recvUncompressSize;
                    this.f492b.f475a.rspBodyDeflateSize = superviseData.recvBodySize;
                    this.f492b.f475a.rspBodyInflateSize = this.e;
                    if (this.f492b.f475a.contentLength == 0) {
                        this.f492b.f475a.contentLength = superviseData.originContentLength;
                    }
                    TnetSpdySession.this.q.recvSizeCount += superviseData.recvBodySize + superviseData.recvCompressSize;
                    TnetSpdySession.this.q.sendSizeCount += superviseData.bodySize + superviseData.compressSize;
                }
            } catch (Exception unused) {
            }
        }

        @Override // anet.channel.session.a
        public void spdyOnStreamResponse(SpdySession spdySession, long j, Map<String, List<String>> map, Object obj) {
            this.f492b.f475a.firstDataTime = System.currentTimeMillis() - this.f492b.f475a.sendStart;
            this.d = HttpHelper.parseStatusCode(map);
            TnetSpdySession.this.H = 0;
            ALog.i("awcn.TnetSpdySession", "", this.f492b.getSeq(), "statusCode", Integer.valueOf(this.d));
            ALog.i("awcn.TnetSpdySession", "", this.f492b.getSeq(), "response headers", map);
            if (this.f493c != null) {
                this.f493c.onResponseCode(this.d, HttpHelper.cloneMap(map));
            }
            TnetSpdySession.this.handleCallbacks(16, null);
            this.f492b.f475a.contentEncoding = HttpHelper.getSingleHeaderFieldByKey(map, "Content-Encoding");
            this.f492b.f475a.contentType = HttpHelper.getSingleHeaderFieldByKey(map, "Content-Type");
            this.f492b.f475a.contentLength = HttpHelper.parseContentLength(map);
            this.f492b.f475a.serverRT = HttpHelper.parseServerRT(map);
            TnetSpdySession.this.handleResponseCode(this.f492b, this.d);
            TnetSpdySession.this.handleResponseHeaders(this.f492b, map);
            if (TnetSpdySession.this.D != null) {
                TnetSpdySession.this.D.reSchedule();
            }
        }
    }
}
