package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1;

import android.text.TextUtils;
import com.bytedance.msdk.adapter.util.Logger;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f {

    /* renamed from: c, reason: collision with root package name */
    private static volatile f f2005c;

    /* renamed from: a, reason: collision with root package name */
    private String f2006a = "";

    /* renamed from: b, reason: collision with root package name */
    private final ExecutorService f2007b = Executors.newSingleThreadExecutor();

    private class b implements Callable<String> {
        private b() {
        }

        @Override // java.util.concurrent.Callable
        public String call() throws Exception {
            long jCurrentTimeMillis = System.currentTimeMillis();
            try {
                AdvertisingIdClient.Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
                if (advertisingIdInfo != null) {
                    String id = advertisingIdInfo.getId();
                    Logger.d("gaid-", "getAdvertisingId: " + id);
                    f.b(id);
                }
            } catch (Throwable unused) {
            }
            try {
                AdvertisingIdClient.Info advertisingIdInfo2 = AdvertisingIdClient.getAdvertisingIdInfo(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d());
                if (advertisingIdInfo2 != null) {
                    f.this.f2006a = advertisingIdInfo2.getId();
                    advertisingIdInfo2.isLimitAdTrackingEnabled();
                }
            } catch (Throwable unused2) {
            }
            Logger.d("AdvertisingIdHelper", "mGAId:" + f.this.f2006a + " , get gaid consume time :" + (System.currentTimeMillis() - jCurrentTimeMillis));
            return f.this.f2006a;
        }
    }

    private f() {
    }

    public static f c() {
        if (f2005c == null) {
            synchronized (f.class) {
                if (f2005c == null) {
                    f2005c = new f();
                }
            }
        }
        return f2005c;
    }

    public String b() {
        try {
            this.f2006a = u.a("tt_device_info", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).a("gaid", "");
            Logger.i("gaid-", "getGAIdTimeOut-mGAId=" + this.f2006a);
            if (TextUtils.isEmpty(this.f2006a)) {
                this.f2007b.execute(new FutureTask(new b()));
            }
        } catch (Throwable unused) {
        }
        return this.f2006a;
    }

    public String a() {
        try {
            this.f2006a = u.a("tt_device_info", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).a("gaid", "");
            Logger.i("gaid-", "getGAIdTimeOut-mGAId=" + this.f2006a);
            if (TextUtils.isEmpty(this.f2006a)) {
                FutureTask futureTask = new FutureTask(new b());
                this.f2007b.execute(futureTask);
                this.f2006a = (String) futureTask.get(500000L, TimeUnit.MICROSECONDS);
            }
        } catch (Throwable unused) {
        }
        return this.f2006a;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        u.a("tt_device_info", bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()).b("gaid", str);
    }
}
