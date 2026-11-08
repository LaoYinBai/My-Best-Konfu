package anet.channel;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.LruCache;
import anet.channel.Config;
import anet.channel.entity.ConnType;
import anet.channel.entity.ENV;
import anet.channel.status.NetworkStatusHelper;
import anet.channel.strategy.ConnProtocol;
import anet.channel.strategy.IStrategyListener;
import anet.channel.strategy.StrategyCenter;
import anet.channel.strategy.l;
import anet.channel.util.ALog;
import anet.channel.util.AppLifecycle;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpUrl;
import anet.channel.util.StringUtils;
import anet.channel.util.Utils;
import com.bytedance.common.utility.DeviceUtils;
import java.net.ConnectException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeoutException;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SessionCenter {
    public static final String TAG = "awcn.SessionCenter";

    /* renamed from: a, reason: collision with root package name */
    static Map<Config, SessionCenter> f376a = new HashMap();
    private static boolean j = false;

    /* renamed from: c, reason: collision with root package name */
    String f378c;
    Config d;
    final AccsSessionManager h;
    final d e = new d();
    final LruCache<String, SessionRequest> f = new LruCache<>(32);
    final b g = new b();
    final a i = new a(this, null);

    /* renamed from: b, reason: collision with root package name */
    Context f377b = GlobalAppRuntimeInfo.getContext();

    public static synchronized void init(Context context) {
        if (context == null) {
            ALog.e(TAG, "context is null!", null, new Object[0]);
            throw new NullPointerException("init failed. context is null");
        }
        GlobalAppRuntimeInfo.setContext(context.getApplicationContext());
        if (!j) {
            f376a.put(Config.DEFAULT_CONFIG, new SessionCenter(Config.DEFAULT_CONFIG));
            AppLifecycle.initialize();
            NetworkStatusHelper.startListener(context);
            StrategyCenter.getInstance().initialize(GlobalAppRuntimeInfo.getContext());
            if (GlobalAppRuntimeInfo.isTargetProcess()) {
                anet.channel.d.a.a();
                anet.channel.f.a.a();
            }
            j = true;
        }
    }

    @Deprecated
    public static synchronized void init(Context context, String str) {
        init(context, str, GlobalAppRuntimeInfo.getEnv());
    }

    public static synchronized void init(Context context, String str, ENV env) {
        if (context == null) {
            ALog.e(TAG, "context is null!", null, new Object[0]);
            throw new NullPointerException("init failed. context is null");
        }
        Config config = Config.getConfig(str, env);
        if (config == null) {
            config = new Config.Builder().setAppkey(str).setEnv(env).build();
        }
        init(context, config);
    }

    public static synchronized void init(Context context, Config config) {
        if (context == null) {
            ALog.e(TAG, "context is null!", null, new Object[0]);
            throw new NullPointerException("init failed. context is null");
        }
        if (config == null) {
            ALog.e(TAG, "paramter config is null!", null, new Object[0]);
            throw new NullPointerException("init failed. config is null");
        }
        init(context);
        if (!f376a.containsKey(config)) {
            f376a.put(config, new SessionCenter(config));
        }
    }

    private SessionCenter(Config config) {
        this.d = config;
        this.f378c = config.getAppkey();
        this.i.a();
        this.h = new AccsSessionManager(this);
        if (config.getAppkey().equals("[default]")) {
            return;
        }
        anet.channel.strategy.dispatch.a.a(new c(this, config.getAppkey(), config.getSecurity()));
    }

    @Deprecated
    public synchronized void switchEnv(ENV env) {
        switchEnvironment(env);
    }

    public static synchronized void switchEnvironment(ENV env) {
        try {
            if (GlobalAppRuntimeInfo.getEnv() != env) {
                ALog.i(TAG, "switch env", null, "old", GlobalAppRuntimeInfo.getEnv(), "new", env);
                GlobalAppRuntimeInfo.setEnv(env);
                StrategyCenter.getInstance().switchEnv();
                SpdyAgent.getInstance(GlobalAppRuntimeInfo.getContext(), SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION).switchAccsServer(env == ENV.TEST ? 0 : 1);
            }
            Iterator<Map.Entry<Config, SessionCenter>> it = f376a.entrySet().iterator();
            while (it.hasNext()) {
                SessionCenter value = it.next().getValue();
                if (value.d.getEnv() != env) {
                    ALog.i(TAG, "remove instance", value.f378c, "ENVIRONMENT", value.d.getEnv());
                    value.h.forceCloseSession(false);
                    value.i.b();
                    it.remove();
                }
            }
        } catch (Throwable th) {
            ALog.e(TAG, "switch env error.", null, th, new Object[0]);
        }
    }

    public static synchronized SessionCenter getInstance(String str) {
        Config configByTag;
        configByTag = Config.getConfigByTag(str);
        if (configByTag == null) {
            throw new RuntimeException("tag not exist!");
        }
        return getInstance(configByTag);
    }

    public static synchronized SessionCenter getInstance(Config config) {
        SessionCenter sessionCenter;
        Context appContext;
        if (config == null) {
            throw new NullPointerException("config is null!");
        }
        if (!j && (appContext = Utils.getAppContext()) != null) {
            init(appContext);
        }
        sessionCenter = f376a.get(config);
        if (sessionCenter == null) {
            sessionCenter = new SessionCenter(config);
            f376a.put(config, sessionCenter);
        }
        return sessionCenter;
    }

    @Deprecated
    public static synchronized SessionCenter getInstance() {
        Context appContext;
        if (!j && (appContext = Utils.getAppContext()) != null) {
            init(appContext);
        }
        SessionCenter sessionCenter = null;
        for (Map.Entry<Config, SessionCenter> entry : f376a.entrySet()) {
            SessionCenter value = entry.getValue();
            if (entry.getKey() != Config.DEFAULT_CONFIG) {
                return value;
            }
            sessionCenter = value;
        }
        return sessionCenter;
    }

    public Session getThrowsException(String str, long j2) throws Exception {
        return a(HttpUrl.parse(str), anet.channel.entity.c.f437c, j2, null);
    }

    @Deprecated
    public Session getThrowsException(String str, ConnType.TypeLevel typeLevel, long j2) throws Exception {
        return a(HttpUrl.parse(str), typeLevel == ConnType.TypeLevel.SPDY ? anet.channel.entity.c.f435a : anet.channel.entity.c.f436b, j2, null);
    }

    public Session getThrowsException(HttpUrl httpUrl, int i, long j2) throws Exception {
        return a(httpUrl, i, j2, null);
    }

    @Deprecated
    public Session getThrowsException(HttpUrl httpUrl, ConnType.TypeLevel typeLevel, long j2) throws Exception {
        return a(httpUrl, typeLevel == ConnType.TypeLevel.SPDY ? anet.channel.entity.c.f435a : anet.channel.entity.c.f436b, j2, null);
    }

    public Session get(String str, long j2) {
        return get(HttpUrl.parse(str), anet.channel.entity.c.f437c, j2);
    }

    @Deprecated
    public Session get(String str, ConnType.TypeLevel typeLevel, long j2) {
        return get(HttpUrl.parse(str), typeLevel == ConnType.TypeLevel.SPDY ? anet.channel.entity.c.f435a : anet.channel.entity.c.f436b, j2);
    }

    @Deprecated
    public Session get(HttpUrl httpUrl, ConnType.TypeLevel typeLevel, long j2) {
        return get(httpUrl, typeLevel == ConnType.TypeLevel.SPDY ? anet.channel.entity.c.f435a : anet.channel.entity.c.f436b, j2);
    }

    public Session get(HttpUrl httpUrl, int i, long j2) {
        try {
            return a(httpUrl, i, j2, null);
        } catch (NoAvailStrategyException e) {
            ALog.i(TAG, "[Get]" + e.getMessage(), this.f378c, null, "url", httpUrl.urlString());
            return null;
        } catch (ConnectException e2) {
            ALog.e(TAG, "[Get]connect exception", this.f378c, "errMsg", e2.getMessage(), "url", httpUrl.urlString());
            return null;
        } catch (InvalidParameterException e3) {
            ALog.e(TAG, "[Get]param url is invalid", this.f378c, e3, "url", httpUrl);
            return null;
        } catch (TimeoutException e4) {
            ALog.e(TAG, "[Get]timeout exception", this.f378c, e4, "url", httpUrl.urlString());
            return null;
        } catch (Exception e5) {
            ALog.e(TAG, "[Get]" + e5.getMessage(), this.f378c, null, "url", httpUrl.urlString());
            return null;
        }
    }

    public void asyncGet(HttpUrl httpUrl, int i, long j2, SessionGetCallback sessionGetCallback) {
        if (sessionGetCallback == null) {
            throw new NullPointerException("cb is null");
        }
        if (j2 <= 0) {
            throw new InvalidParameterException("timeout must > 0");
        }
        try {
            Session sessionA = a(httpUrl, i, j2, sessionGetCallback);
            if (sessionA != null) {
                sessionGetCallback.onSessionGetSuccess(sessionA);
            }
        } catch (Exception unused) {
            sessionGetCallback.onSessionGetFail();
        }
    }

    public void registerSessionInfo(SessionInfo sessionInfo) {
        this.g.a(sessionInfo);
        if (sessionInfo.isKeepAlive) {
            this.h.checkAndStartSession();
        }
    }

    public void unregisterSessionInfo(String str) {
        SessionInfo sessionInfoA = this.g.a(str);
        if (sessionInfoA == null || !sessionInfoA.isKeepAlive) {
            return;
        }
        this.h.checkAndStartSession();
    }

    public void registerPublicKey(String str, int i) {
        this.g.a(str, i);
    }

    public static void checkAndStartAccsSession() {
        Iterator<SessionCenter> it = f376a.values().iterator();
        while (it.hasNext()) {
            it.next().h.checkAndStartSession();
        }
    }

    public void forceRecreateAccsSession() {
        this.h.forceCloseSession(true);
    }

    private SessionRequest a(HttpUrl httpUrl) {
        String cNameByHost = StrategyCenter.getInstance().getCNameByHost(httpUrl.host());
        if (cNameByHost == null) {
            cNameByHost = httpUrl.host();
        }
        String strScheme = httpUrl.scheme();
        if (!httpUrl.isSchemeLocked()) {
            strScheme = StrategyCenter.getInstance().getSchemeByHost(cNameByHost, strScheme);
        }
        return a(StringUtils.concatString(strScheme, HttpConstant.SCHEME_SPLIT, cNameByHost));
    }

    protected Session a(HttpUrl httpUrl, int i, long j2, SessionGetCallback sessionGetCallback) throws Exception {
        SessionInfo sessionInfoB;
        if (!j) {
            ALog.e(TAG, "getInternal not inited!", this.f378c, new Object[0]);
            throw new IllegalStateException("getInternal not inited");
        }
        if (httpUrl == null) {
            throw new InvalidParameterException("httpUrl is null");
        }
        String str = this.f378c;
        Object[] objArr = new Object[6];
        objArr[0] = "u";
        objArr[1] = httpUrl.urlString();
        objArr[2] = "sessionType";
        objArr[3] = i == anet.channel.entity.c.f435a ? "LongLink" : "ShortLink";
        objArr[4] = "timeout";
        objArr[5] = Long.valueOf(j2);
        ALog.d(TAG, "getInternal", str, objArr);
        SessionRequest sessionRequestA = a(httpUrl);
        Session sessionA = this.e.a(sessionRequestA, i);
        if (sessionA != null) {
            ALog.d(TAG, "get internal hit cache session", this.f378c, "session", sessionA);
        } else {
            if (this.d == Config.DEFAULT_CONFIG && i != anet.channel.entity.c.f436b) {
                if (sessionGetCallback == null) {
                    return null;
                }
                sessionGetCallback.onSessionGetFail();
                return null;
            }
            if (GlobalAppRuntimeInfo.isAppBackground() && i == anet.channel.entity.c.f435a && AwcnConfig.isAccsSessionCreateForbiddenInBg() && (sessionInfoB = this.g.b(httpUrl.host())) != null && sessionInfoB.isAccs) {
                ALog.w(TAG, "app background, forbid to create accs session", this.f378c, new Object[0]);
                throw new ConnectException("accs session connecting forbidden in background");
            }
            sessionRequestA.a(this.f377b, i, anet.channel.util.i.a(this.f378c), sessionGetCallback, j2);
            if (sessionGetCallback == null && j2 > 0 && (i == anet.channel.entity.c.f437c || sessionRequestA.b() == i)) {
                sessionRequestA.a(j2);
                sessionA = this.e.a(sessionRequestA, i);
                if (sessionA == null) {
                    throw new ConnectException("session connecting failed or timeout");
                }
            }
        }
        return sessionA;
    }

    @Deprecated
    public void enterBackground() {
        AppLifecycle.onBackground();
    }

    @Deprecated
    public void enterForeground() {
        AppLifecycle.onForeground();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(l.d dVar) {
        try {
            for (l.b bVar : dVar.f580b) {
                if (bVar.k) {
                    b(bVar);
                }
                if (bVar.e != null) {
                    a(bVar);
                }
            }
        } catch (Exception e) {
            ALog.e(TAG, "checkStrategy failed", this.f378c, e, new Object[0]);
        }
    }

    private void a(l.b bVar) {
        for (Session session : this.e.a(a(StringUtils.buildKey(bVar.f576c, bVar.f574a)))) {
            if (!StringUtils.isStringEqual(session.l, bVar.e)) {
                ALog.i(TAG, "unit change", session.p, "session unit", session.l, "unit", bVar.e);
                session.close(true);
            }
        }
    }

    private void b(l.b bVar) {
        boolean z;
        boolean z2;
        ALog.i(TAG, "find effectNow", this.f378c, com.alipay.sdk.cons.c.f, bVar.f574a);
        l.a[] aVarArr = bVar.h;
        String[] strArr = bVar.f;
        for (Session session : this.e.a(a(StringUtils.buildKey(bVar.f576c, bVar.f574a)))) {
            if (!session.getConnType().isHttpType()) {
                int i = 0;
                while (true) {
                    if (i >= strArr.length) {
                        z = false;
                        break;
                    } else {
                        if (session.getIp().equals(strArr[i])) {
                            z = true;
                            break;
                        }
                        i++;
                    }
                }
                if (z) {
                    int i2 = 0;
                    while (true) {
                        if (i2 >= aVarArr.length) {
                            z2 = false;
                            break;
                        } else {
                            if (session.getPort() == aVarArr[i2].f571a && session.getConnType().equals(ConnType.valueOf(ConnProtocol.valueOf(aVarArr[i2])))) {
                                z2 = true;
                                break;
                            }
                            i2++;
                        }
                    }
                    if (!z2) {
                        if (ALog.isPrintLog(2)) {
                            ALog.i(TAG, "aisle not match", session.p, "port", Integer.valueOf(session.getPort()), "connType", session.getConnType(), "aisle", Arrays.toString(aVarArr));
                        }
                        session.close(true);
                    }
                } else {
                    if (ALog.isPrintLog(2)) {
                        ALog.i(TAG, "ip not match", session.p, "session ip", session.getIp(), "ips", Arrays.toString(strArr));
                    }
                    session.close(true);
                }
            }
        }
    }

    protected SessionRequest a(String str) {
        SessionRequest sessionRequest;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        synchronized (this.f) {
            sessionRequest = this.f.get(str);
            if (sessionRequest == null) {
                sessionRequest = new SessionRequest(str, this);
                this.f.put(str, sessionRequest);
            }
        }
        return sessionRequest;
    }

    private class a implements NetworkStatusHelper.INetworkStatusChangeListener, IStrategyListener, AppLifecycle.AppLifecycleListener {

        /* renamed from: a, reason: collision with root package name */
        boolean f379a;

        private a() {
            this.f379a = false;
        }

        /* synthetic */ a(SessionCenter sessionCenter, c cVar) {
            this();
        }

        void a() {
            AppLifecycle.registerLifecycleListener(this);
            NetworkStatusHelper.addStatusChangeListener(this);
            StrategyCenter.getInstance().registerListener(this);
        }

        void b() {
            StrategyCenter.getInstance().unregisterListener(this);
            AppLifecycle.unregisterLifecycleListener(this);
            NetworkStatusHelper.removeStatusChangeListener(this);
        }

        @Override // anet.channel.status.NetworkStatusHelper.INetworkStatusChangeListener
        public void onNetworkStatusChanged(NetworkStatusHelper.NetworkStatus networkStatus) {
            ALog.e(SessionCenter.TAG, "onNetworkStatusChanged.", SessionCenter.this.f378c, "networkStatus", networkStatus);
            List<SessionRequest> listA = SessionCenter.this.e.a();
            if (!listA.isEmpty()) {
                for (SessionRequest sessionRequest : listA) {
                    ALog.d(SessionCenter.TAG, "network change, try recreate session", SessionCenter.this.f378c, new Object[0]);
                    sessionRequest.a((String) null);
                }
            }
            SessionCenter.this.h.checkAndStartSession();
        }

        @Override // anet.channel.strategy.IStrategyListener
        public void onStrategyUpdated(l.d dVar) {
            SessionCenter.this.a(dVar);
            SessionCenter.this.h.checkAndStartSession();
        }

        @Override // anet.channel.util.AppLifecycle.AppLifecycleListener
        public void forground() {
            ALog.i(SessionCenter.TAG, "[forground]", SessionCenter.this.f378c, new Object[0]);
            if (SessionCenter.this.f377b == null || this.f379a) {
                return;
            }
            this.f379a = true;
            try {
                if (!SessionCenter.j) {
                    ALog.e(SessionCenter.TAG, "forground not inited!", SessionCenter.this.f378c, new Object[0]);
                    return;
                }
                try {
                    if (AppLifecycle.lastEnterBackgroundTime != 0 && System.currentTimeMillis() - AppLifecycle.lastEnterBackgroundTime > 60000) {
                        SessionCenter.this.h.forceCloseSession(true);
                    } else {
                        SessionCenter.this.h.checkAndStartSession();
                    }
                } catch (Exception unused) {
                } catch (Throwable th) {
                    this.f379a = false;
                    throw th;
                }
                this.f379a = false;
            } catch (Exception unused2) {
            }
        }

        @Override // anet.channel.util.AppLifecycle.AppLifecycleListener
        public void background() {
            ALog.i(SessionCenter.TAG, "[background]", SessionCenter.this.f378c, new Object[0]);
            if (!SessionCenter.j) {
                ALog.e(SessionCenter.TAG, "background not inited!", SessionCenter.this.f378c, new Object[0]);
                return;
            }
            try {
                StrategyCenter.getInstance().saveData();
                if (DeviceUtils.ROM_OPPO.equalsIgnoreCase(Build.BRAND)) {
                    ALog.i(SessionCenter.TAG, "close session for OPPO", SessionCenter.this.f378c, new Object[0]);
                    SessionCenter.this.h.forceCloseSession(false);
                }
            } catch (Exception unused) {
            }
        }
    }
}
