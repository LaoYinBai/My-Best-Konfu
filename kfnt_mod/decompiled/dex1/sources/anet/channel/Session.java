package anet.channel;

import android.content.Context;
import android.text.TextUtils;
import anet.channel.entity.ConnType;
import anet.channel.entity.EventCb;
import anet.channel.request.Cancelable;
import anet.channel.request.Request;
import anet.channel.statist.SessionStatistic;
import anet.channel.strategy.IConnStrategy;
import anet.channel.strategy.StrategyCenter;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import anet.channel.util.HttpConstant;
import anet.channel.util.HttpHelper;
import anet.channel.util.StringUtils;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import org.android.spdy.SpdyAgent;
import org.android.spdy.SpdySessionKind;
import org.android.spdy.SpdyVersion;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class Session implements Comparable<Session> {
    static ExecutorService v = Executors.newSingleThreadExecutor();

    /* renamed from: a, reason: collision with root package name */
    public Context f372a;

    /* renamed from: c, reason: collision with root package name */
    public String f374c;
    public String d;
    public String e;
    public String f;
    public int g;
    public String h;
    public int i;
    public ConnType j;
    public IConnStrategy k;
    public boolean m;
    protected Runnable o;
    public final String p;
    public final SessionStatistic q;
    public int r;
    public int s;
    private Future<?> x;

    /* renamed from: b, reason: collision with root package name */
    Map<EventCb, Integer> f373b = new LinkedHashMap();
    private boolean w = false;
    public String l = null;
    public int n = 6;
    public boolean t = false;
    protected boolean u = true;
    private List<Long> y = null;
    private long z = 0;

    public abstract void close();

    public void connect() {
    }

    public abstract Runnable getRecvTimeOutRunnable();

    public abstract boolean isAvailable();

    public void onDisconnect() {
    }

    public void ping(boolean z) {
    }

    public abstract Cancelable request(Request request, RequestCb requestCb);

    public void sendCustomFrame(int i, byte[] bArr, int i2) {
    }

    public static class a {
        public static final int AUTHING = 3;
        public static final int AUTH_FAIL = 5;
        public static final int AUTH_SUCC = 4;
        public static final int CONNECTED = 0;
        public static final int CONNECTING = 1;
        public static final int CONNETFAIL = 2;
        public static final int DISCONNECTED = 6;
        public static final int DISCONNECTING = 7;

        /* renamed from: a, reason: collision with root package name */
        static final String[] f375a = {"CONNECTED", "CONNECTING", "CONNETFAIL", "AUTHING", "AUTH_SUCC", "AUTH_FAIL", "DISCONNECTED", "DISCONNECTING"};

        static String a(int i) {
            return f375a[i];
        }
    }

    @Override // java.lang.Comparable
    public int compareTo(Session session) {
        return ConnType.compare(this.j, session.j);
    }

    public Session(Context context, anet.channel.entity.a aVar) {
        boolean z = false;
        this.m = false;
        this.f372a = context;
        this.e = aVar.a();
        this.f = this.e;
        this.g = aVar.b();
        this.j = aVar.c();
        this.f374c = aVar.f();
        this.d = this.f374c.substring(this.f374c.indexOf(HttpConstant.SCHEME_SPLIT) + 3);
        this.s = aVar.e();
        this.r = aVar.d();
        this.k = aVar.f429a;
        if (this.k != null && this.k.getIpType() == -1) {
            z = true;
        }
        this.m = z;
        this.p = aVar.h();
        this.q = new SessionStatistic(aVar);
        this.q.host = this.d;
    }

    public void checkAvailable() {
        ping(true);
    }

    public static void configTnetALog(Context context, String str, int i, int i2) {
        SpdyAgent spdyAgent = SpdyAgent.getInstance(context, SpdyVersion.SPDY3, SpdySessionKind.NONE_SESSION);
        if (spdyAgent != null && SpdyAgent.checkLoadSucc()) {
            spdyAgent.configLogFile(str, i, i2);
        } else {
            ALog.e("agent null or configTnetALog load so fail!!!", null, "loadso", Boolean.valueOf(SpdyAgent.checkLoadSucc()));
        }
    }

    public void close(boolean z) {
        this.t = z;
        close();
    }

    public void registerEventcb(int i, EventCb eventCb) {
        if (this.f373b != null) {
            this.f373b.put(eventCb, Integer.valueOf(i));
        }
    }

    public String getIp() {
        return this.e;
    }

    public int getPort() {
        return this.g;
    }

    public ConnType getConnType() {
        return this.j;
    }

    public String getHost() {
        return this.f374c;
    }

    public String getRealHost() {
        return this.d;
    }

    public IConnStrategy getConnStrategy() {
        return this.k;
    }

    public String getUnit() {
        return this.l;
    }

    public void handleCallbacks(int i, anet.channel.entity.b bVar) {
        v.submit(new anet.channel.a(this, i, bVar));
    }

    public synchronized void notifyStatus(int i, anet.channel.entity.b bVar) {
        ALog.e("awcn.Session", "notifyStatus", this.p, "status", a.a(i));
        if (i == this.n) {
            ALog.i("awcn.Session", "ignore notifyStatus", this.p, new Object[0]);
            return;
        }
        this.n = i;
        switch (this.n) {
            case 0:
                handleCallbacks(1, bVar);
                break;
            case 2:
                handleCallbacks(256, bVar);
                break;
            case 4:
                this.l = StrategyCenter.getInstance().getUnitByHost(this.d);
                handleCallbacks(512, bVar);
                break;
            case 5:
                handleCallbacks(1024, bVar);
                break;
            case 6:
                onDisconnect();
                if (!this.w) {
                    handleCallbacks(2, bVar);
                    break;
                }
                break;
        }
    }

    public void setPingTimeout() {
        if (this.o == null) {
            this.o = getRecvTimeOutRunnable();
        }
        a();
        if (this.o != null) {
            this.x = ThreadPoolExecutorFactory.submitScheduledTask(this.o, this.s, TimeUnit.MILLISECONDS);
        }
    }

    protected void a() {
        if (this.o == null || this.x == null) {
            return;
        }
        this.x.cancel(true);
    }

    public String toString() {
        return "Session@[" + this.p + '|' + this.j + ']';
    }

    public void handleResponseCode(Request request, int i) {
        if (request.getHeaders().containsKey(HttpConstant.X_PV) && i >= 500 && i < 600) {
            synchronized (this) {
                if (this.y == null) {
                    this.y = new LinkedList();
                }
                if (this.y.size() < 5) {
                    this.y.add(Long.valueOf(System.currentTimeMillis()));
                } else {
                    long jLongValue = this.y.remove(0).longValue();
                    long jCurrentTimeMillis = System.currentTimeMillis();
                    if (jCurrentTimeMillis - jLongValue <= 60000) {
                        StrategyCenter.getInstance().forceRefreshStrategy(request.getHost());
                        this.y.clear();
                    } else {
                        this.y.add(Long.valueOf(jCurrentTimeMillis));
                    }
                }
            }
        }
    }

    public void handleResponseHeaders(Request request, Map<String, List<String>> map) {
        try {
            if (map.containsKey(HttpConstant.X_SWITCH_UNIT)) {
                String singleHeaderFieldByKey = HttpHelper.getSingleHeaderFieldByKey(map, HttpConstant.X_SWITCH_UNIT);
                if (TextUtils.isEmpty(singleHeaderFieldByKey)) {
                    singleHeaderFieldByKey = null;
                }
                if (StringUtils.isStringEqual(this.l, singleHeaderFieldByKey)) {
                    return;
                }
                long jCurrentTimeMillis = System.currentTimeMillis();
                if (jCurrentTimeMillis - this.z > 60000) {
                    StrategyCenter.getInstance().forceRefreshStrategy(request.getHost());
                    this.z = jCurrentTimeMillis;
                }
            }
        } catch (Exception unused) {
        }
    }
}
