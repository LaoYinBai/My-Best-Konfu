package anet.channel.monitor;

import anet.channel.status.NetworkStatusHelper;
import anet.channel.thread.ThreadPoolExecutorFactory;
import anet.channel.util.ALog;
import com.bytedance.sdk.account.platform.onekey.OnekeyLoginConfig;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    static int f461a = 0;

    /* renamed from: b, reason: collision with root package name */
    static long f462b = 0;

    /* renamed from: c, reason: collision with root package name */
    static long f463c = 0;
    static long d = 0;
    static long e = 0;
    static long f = 0;
    static double g = 0.0d;
    static double h = 0.0d;
    static double i = 0.0d;
    static double j = 40.0d;
    private static volatile boolean k = false;
    private int l;
    private int m;
    private e n;

    /* synthetic */ b(c cVar) {
        this();
    }

    static /* synthetic */ int b(b bVar) {
        int i2 = bVar.m;
        bVar.m = i2 + 1;
        return i2;
    }

    static class a {

        /* renamed from: a, reason: collision with root package name */
        static b f464a = new b(null);

        a() {
        }
    }

    public static b a() {
        return a.f464a;
    }

    private b() {
        this.l = 5;
        this.m = 0;
        this.n = new e();
        NetworkStatusHelper.addStatusChangeListener(new c(this));
    }

    public int b() {
        if (NetworkStatusHelper.getStatus() == NetworkStatusHelper.NetworkStatus.G2) {
            return 1;
        }
        return this.l;
    }

    public double c() {
        return i;
    }

    public synchronized void d() {
        try {
            ALog.i("awcn.BandWidthSampler", "[startNetworkMeter]", null, "NetworkStatus", NetworkStatusHelper.getStatus());
        } catch (Exception e2) {
            ALog.w("awcn.BandWidthSampler", "startNetworkMeter fail.", null, e2, new Object[0]);
        }
        if (NetworkStatusHelper.getStatus() == NetworkStatusHelper.NetworkStatus.G2) {
            k = false;
        } else {
            k = true;
        }
    }

    public void e() {
        k = false;
    }

    public void a(long j2, long j3, long j4) {
        if (k) {
            if (ALog.isPrintLog(1)) {
                ALog.d("awcn.BandWidthSampler", "onDataReceived", null, "mRequestStartTime", Long.valueOf(j2), "mRequestFinishedTime", Long.valueOf(j3), "mRequestDataSize", Long.valueOf(j4));
            }
            if (j4 <= OnekeyLoginConfig.OVER_TIME || j2 >= j3) {
                return;
            }
            ThreadPoolExecutorFactory.submitScheduledTask(new d(this, j4, j3, j2));
        }
    }
}
