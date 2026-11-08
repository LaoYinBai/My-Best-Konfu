package anetwork.channel.j;

import android.text.TextUtils;
import anet.channel.Config;
import anet.channel.GlobalAppRuntimeInfo;
import anet.channel.NoAvailStrategyException;
import anet.channel.Session;
import anet.channel.SessionCenter;
import anet.channel.appmonitor.AppMonitor;
import anet.channel.bytes.ByteArray;
import anet.channel.entity.ENV;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.statist.ExceptionStatistic;
import anet.channel.statist.RequestStatistic;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.dispatch.DispatchConstants;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;
import anet.channel.util.ErrorConstant;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import anet.channel.util.StringUtils;
import anetwork.channel.a.a;
import anetwork.channel.aidl.DefaultFinishEvent;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class g implements anetwork.channel.j.a {

    /* renamed from: a, reason: collision with root package name */
    l f720a;

    /* renamed from: b, reason: collision with root package name */
    anetwork.channel.a.a f721b;

    /* renamed from: c, reason: collision with root package name */
    a.C0010a f722c;
    String e;
    volatile AtomicBoolean h;
    ByteArrayOutputStream d = null;
    volatile Cancelable f = null;
    volatile boolean g = false;
    int i = 0;
    int j = 0;
    boolean k = false;
    boolean l = false;
    a m = null;

    g(l lVar, anetwork.channel.a.a aVar, a.C0010a c0010a) {
        this.f721b = null;
        this.f722c = null;
        this.e = DispatchConstants.OTHER;
        this.h = null;
        this.f720a = lVar;
        this.h = lVar.d;
        this.f721b = aVar;
        this.f722c = c0010a;
        this.e = lVar.f736a.h().get(HttpConstant.F_REFER);
    }

    @Override // anet.channel.request.Cancelable
    public void cancel() {
        this.g = true;
        if (this.f != null) {
            this.f.cancel();
        }
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.g) {
            return;
        }
        RequestStatistic requestStatistic = this.f720a.f736a.f700b;
        requestStatistic.f_refer = this.e;
        if (!NetworkStatusHelper.isConnected()) {
            if (anetwork.channel.b.b.n() && requestStatistic.statusCode != -200) {
                requestStatistic.statusCode = ErrorConstant.ERROR_NO_NETWORK;
                ThreadPoolExecutorFactory.submitScheduledTask(new h(this), 1000L, TimeUnit.MILLISECONDS);
                return;
            }
            if (ALog.isPrintLog(2)) {
                ALog.i("anet.NetworkTask", "network unavailable", this.f720a.f738c, "NetworkStatus", NetworkStatusHelper.getStatus());
            }
            this.h.set(true);
            this.f720a.a();
            requestStatistic.isDone.set(true);
            requestStatistic.statusCode = ErrorConstant.ERROR_NO_NETWORK;
            requestStatistic.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_NO_NETWORK);
            requestStatistic.rspEnd = System.currentTimeMillis();
            this.f720a.f737b.a(new DefaultFinishEvent(ErrorConstant.ERROR_NO_NETWORK, null, requestStatistic));
            return;
        }
        if (anetwork.channel.b.b.j() && GlobalAppRuntimeInfo.isAppBackground() && AppLifecycle.lastEnterBackgroundTime > 0 && System.currentTimeMillis() - AppLifecycle.lastEnterBackgroundTime > anetwork.channel.b.b.m() && !anetwork.channel.b.b.a(this.f720a.f736a.f())) {
            this.h.set(true);
            this.f720a.a();
            if (ALog.isPrintLog(2)) {
                ALog.i("anet.NetworkTask", "request forbidden in background", this.f720a.f738c, "url", this.f720a.f736a.f());
            }
            requestStatistic.isDone.set(true);
            requestStatistic.statusCode = ErrorConstant.ERROR_REQUEST_FORBIDDEN_IN_BG;
            requestStatistic.msg = ErrorConstant.getErrMsg(ErrorConstant.ERROR_REQUEST_FORBIDDEN_IN_BG);
            requestStatistic.rspEnd = System.currentTimeMillis();
            this.f720a.f737b.a(new DefaultFinishEvent(ErrorConstant.ERROR_REQUEST_FORBIDDEN_IN_BG, null, requestStatistic));
            ExceptionStatistic exceptionStatistic = new ExceptionStatistic(ErrorConstant.ERROR_REQUEST_FORBIDDEN_IN_BG, null, "rt");
            exceptionStatistic.host = this.f720a.f736a.f().host();
            exceptionStatistic.url = this.f720a.f736a.g();
            AppMonitor.getInstance().commitStat(exceptionStatistic);
            return;
        }
        if (ALog.isPrintLog(2)) {
            ALog.i("anet.NetworkTask", "exec request", this.f720a.f738c, "retryTimes", Integer.valueOf(this.f720a.f736a.f699a));
        }
        if (anetwork.channel.b.b.l()) {
            c();
            return;
        }
        try {
            Session sessionB = b();
            if (sessionB == null) {
                return;
            }
            a(sessionB, this.f720a.f736a.a());
        } catch (Exception e) {
            ALog.e("anet.NetworkTask", "send request failed.", this.f720a.f738c, e, new Object[0]);
        }
    }

    private HttpUrl a(HttpUrl httpUrl) {
        HttpUrl httpUrl2;
        String str = this.f720a.f736a.h().get(HttpConstant.X_HOST_CNAME);
        return (TextUtils.isEmpty(str) || (httpUrl2 = HttpUrl.parse(httpUrl.urlString().replaceFirst(httpUrl.host(), str))) == null) ? httpUrl : httpUrl2;
    }

    private SessionCenter a() {
        String strA = this.f720a.f736a.a("APPKEY");
        if (TextUtils.isEmpty(strA)) {
            return SessionCenter.getInstance();
        }
        ENV env = ENV.ONLINE;
        String strA2 = this.f720a.f736a.a("ENVIRONMENT");
        if ("pre".equalsIgnoreCase(strA2)) {
            env = ENV.PREPARE;
        } else if ("test".equalsIgnoreCase(strA2)) {
            env = ENV.TEST;
        }
        if (env != anetwork.channel.e.b.f681a) {
            anetwork.channel.e.b.f681a = env;
            SessionCenter.switchEnvironment(env);
        }
        Config config = Config.getConfig(strA, env);
        if (config == null) {
            config = new Config.Builder().setAppkey(strA).setEnv(env).setAuthCode(this.f720a.f736a.a("AuthCode")).build();
        }
        return SessionCenter.getInstance(config);
    }

    private Session b() {
        Session throwsException;
        SessionCenter sessionCenterA = a();
        HttpUrl httpUrlF = this.f720a.f736a.f();
        boolean zContainsNonDefaultPort = httpUrlF.containsNonDefaultPort();
        RequestStatistic requestStatistic = this.f720a.f736a.f700b;
        if (this.f720a.f736a.f == 1 && anetwork.channel.b.b.c() && this.f720a.f736a.f699a == 0 && !zContainsNonDefaultPort) {
            HttpUrl httpUrlA = a(httpUrlF);
            try {
                throwsException = sessionCenterA.getThrowsException(httpUrlA, anet.channel.entity.c.f435a, 0L);
            } catch (NoAvailStrategyException unused) {
                return a(null, sessionCenterA, httpUrlF, zContainsNonDefaultPort);
            } catch (Exception unused2) {
                throwsException = null;
            }
            if (throwsException == null) {
                ThreadPoolExecutorFactory.submitPriorityTask(new i(this, sessionCenterA, httpUrlA, requestStatistic, httpUrlF, zContainsNonDefaultPort), ThreadPoolExecutorFactory.Priority.NORMAL);
                return null;
            }
            ALog.i("anet.NetworkTask", "tryGetSession", this.f720a.f738c, "Session", throwsException);
            requestStatistic.spdyRequestSend = true;
            return throwsException;
        }
        return a(null, sessionCenterA, httpUrlF, zContainsNonDefaultPort);
    }

    private void c() {
        SessionCenter sessionCenterA = a();
        HttpUrl httpUrlF = this.f720a.f736a.f();
        boolean zContainsNonDefaultPort = httpUrlF.containsNonDefaultPort();
        RequestStatistic requestStatistic = this.f720a.f736a.f700b;
        Request requestA = this.f720a.f736a.a();
        if (this.f720a.f736a.f == 1 && anetwork.channel.b.b.c() && this.f720a.f736a.f699a == 0 && !zContainsNonDefaultPort) {
            sessionCenterA.asyncGet(a(httpUrlF), anet.channel.entity.c.f435a, OnekeyLoginConfig.OVER_TIME, new j(this, requestStatistic, System.currentTimeMillis(), requestA, sessionCenterA, httpUrlF, zContainsNonDefaultPort));
            return;
        }
        a(a(null, sessionCenterA, httpUrlF, zContainsNonDefaultPort), requestA);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Session a(Session session, SessionCenter sessionCenter, HttpUrl httpUrl, boolean z) {
        RequestStatistic requestStatistic = this.f720a.f736a.f700b;
        if (session == null && this.f720a.f736a.e() && !z && !NetworkStatusHelper.isProxy()) {
            session = sessionCenter.get(httpUrl, anet.channel.entity.c.f436b, 0L);
        }
        if (session == null) {
            ALog.i("anet.NetworkTask", "create HttpSession with local DNS", this.f720a.f738c, new Object[0]);
            session = new anet.channel.session.d(GlobalAppRuntimeInfo.getContext(), new anet.channel.entity.a(StringUtils.concatString(httpUrl.scheme(), HttpConstant.SCHEME_SPLIT, httpUrl.host()), this.f720a.f738c, null));
        }
        if (requestStatistic.spdyRequestSend) {
            requestStatistic.degraded = 1;
        }
        ALog.i("anet.NetworkTask", "tryGetHttpSession", this.f720a.f738c, "Session", session);
        return session;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private anet.channel.request.Request a(anet.channel.request.Request r7) {
        /*
            r6 = this;
            anetwork.channel.j.l r0 = r6.f720a
            anetwork.channel.entity.h r0 = r0.f736a
            boolean r0 = r0.i()
            if (r0 == 0) goto L3e
            anetwork.channel.j.l r0 = r6.f720a
            anetwork.channel.entity.h r0 = r0.f736a
            java.lang.String r0 = r0.g()
            java.lang.String r0 = anetwork.channel.c.a.a(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L3e
            anet.channel.request.Request$Builder r1 = r7.newBuilder()
            java.util.Map r2 = r7.getHeaders()
            java.lang.String r3 = "Cookie"
            java.lang.Object r2 = r2.get(r3)
            java.lang.String r2 = (java.lang.String) r2
            boolean r3 = android.text.TextUtils.isEmpty(r2)
            if (r3 != 0) goto L38
            java.lang.String r3 = "; "
            java.lang.String r0 = anet.channel.util.StringUtils.concatString(r2, r3, r0)
        L38:
            java.lang.String r2 = "Cookie"
            r1.addHeader(r2, r0)
            goto L3f
        L3e:
            r1 = 0
        L3f:
            anetwork.channel.a.a$a r0 = r6.f722c
            if (r0 == 0) goto L6f
            if (r1 != 0) goto L49
            anet.channel.request.Request$Builder r1 = r7.newBuilder()
        L49:
            anetwork.channel.a.a$a r0 = r6.f722c
            java.lang.String r0 = r0.f622b
            if (r0 == 0) goto L58
            java.lang.String r0 = "If-None-Match"
            anetwork.channel.a.a$a r2 = r6.f722c
            java.lang.String r2 = r2.f622b
            r1.addHeader(r0, r2)
        L58:
            anetwork.channel.a.a$a r0 = r6.f722c
            long r2 = r0.d
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L6f
            java.lang.String r0 = "If-Modified-Since"
            anetwork.channel.a.a$a r2 = r6.f722c
            long r2 = r2.d
            java.lang.String r2 = anetwork.channel.a.d.a(r2)
            r1.addHeader(r0, r2)
        L6f:
            anetwork.channel.j.l r0 = r6.f720a
            anetwork.channel.entity.h r0 = r0.f736a
            int r0 = r0.f699a
            if (r0 != 0) goto L8c
            java.lang.String r0 = "weex"
            java.lang.String r2 = r6.e
            boolean r0 = r0.equalsIgnoreCase(r2)
            if (r0 == 0) goto L8c
            if (r1 != 0) goto L87
            anet.channel.request.Request$Builder r1 = r7.newBuilder()
        L87:
            r0 = 3000(0xbb8, float:4.204E-42)
            r1.setReadTimeout(r0)
        L8c:
            if (r1 != 0) goto L8f
            goto L93
        L8f:
            anet.channel.request.Request r7 = r1.build()
        L93:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: anetwork.channel.j.g.a(anet.channel.request.Request):anet.channel.request.Request");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(Session session, Request request) {
        if (session == null || this.g) {
            return;
        }
        Request requestA = a(request);
        RequestStatistic requestStatistic = this.f720a.f736a.f700b;
        requestStatistic.reqStart = System.currentTimeMillis();
        this.f = session.request(requestA, new k(this, requestA, requestStatistic));
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class a {

        /* renamed from: a, reason: collision with root package name */
        int f723a;

        /* renamed from: b, reason: collision with root package name */
        Map<String, List<String>> f724b;

        /* renamed from: c, reason: collision with root package name */
        List<ByteArray> f725c = new ArrayList();

        a(int i, Map<String, List<String>> map) {
            this.f723a = i;
            this.f724b = map;
        }

        void a() {
            Iterator<ByteArray> it = this.f725c.iterator();
            while (it.hasNext()) {
                it.next().recycle();
            }
        }

        int a(anetwork.channel.f.a aVar, int i) {
            aVar.a(this.f723a, this.f724b);
            Iterator<ByteArray> it = this.f725c.iterator();
            int i2 = 1;
            while (it.hasNext()) {
                aVar.a(i2, i, it.next());
                i2++;
            }
            return i2;
        }
    }
}
