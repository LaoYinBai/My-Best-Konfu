package bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.n;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;
import com.bytedance.msdk.adapter.util.Logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static Context f1981a;

    /* renamed from: b, reason: collision with root package name */
    private static a f1982b;

    /* renamed from: c, reason: collision with root package name */
    private static a f1983c;

    private static class a {

        /* renamed from: c, reason: collision with root package name */
        private final String f1986c;
        private final long d;
        private final int e;
        private final int f;

        /* renamed from: b, reason: collision with root package name */
        private volatile boolean f1985b = false;
        private int h = 0;
        private final long g = System.currentTimeMillis();

        /* renamed from: a, reason: collision with root package name */
        private final Handler f1984a = new Handler(Looper.getMainLooper());

        /* renamed from: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d$a$a, reason: collision with other inner class name */
        class RunnableC0069a implements Runnable {
            RunnableC0069a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                d.b(n.d(bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d()));
                a.d(a.this);
                if (a.this.h >= 5 || d.d()) {
                    a.this.a(1);
                    return;
                }
                Logger.d("TMe", "--==-- 重试一次 eventType:" + a.this.f1986c + ", 重试次数：" + a.this.h);
                a.this.a();
            }
        }

        public a(String str, long j, int i, int i2) {
            this.f1986c = str;
            this.d = j;
            this.e = i;
            this.f = i2;
        }

        static /* synthetic */ int d(a aVar) {
            int i = aVar.h;
            aVar.h = i + 1;
            return i;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a() {
            this.f1984a.postDelayed(new RunnableC0069a(), 500L);
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* JADX WARN: Removed duplicated region for block: B:25:0x008e  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public synchronized void a(int r8) {
            /*
                r7 = this;
                monitor-enter(r7)
                android.os.Handler r0 = r7.f1984a     // Catch: java.lang.Throwable -> La7
                r1 = 0
                r0.removeCallbacksAndMessages(r1)     // Catch: java.lang.Throwable -> La7
                boolean r0 = r7.f1985b     // Catch: java.lang.Throwable -> La7
                if (r0 == 0) goto Ld
                monitor-exit(r7)
                return
            Ld:
                r0 = 1
                r7.f1985b = r0     // Catch: java.lang.Throwable -> La7
                if (r8 != r0) goto L41
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La7
                r8.<init>()     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = "--==-- 最终上报：eventType:"
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = r7.f1986c     // Catch: java.lang.Throwable -> La7
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = ", 重试次数："
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                int r1 = r7.h     // Catch: java.lang.Throwable -> La7
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = ", did: "
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d.a()     // Catch: java.lang.Throwable -> La7
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = "TMe"
                com.bytedance.msdk.adapter.util.Logger.d(r1, r8)     // Catch: java.lang.Throwable -> La7
                goto L68
            L41:
                r1 = 2
                if (r8 != r1) goto L68
                java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> La7
                r8.<init>()     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = "--==-- 最终上报：eventType:"
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = r7.f1986c     // Catch: java.lang.Throwable -> La7
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = ", 从applog回调中上报, did: "
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d.a()     // Catch: java.lang.Throwable -> La7
                r8.append(r1)     // Catch: java.lang.Throwable -> La7
                java.lang.String r8 = r8.toString()     // Catch: java.lang.Throwable -> La7
                java.lang.String r1 = "TMe"
                com.bytedance.msdk.adapter.util.Logger.d(r1, r8)     // Catch: java.lang.Throwable -> La7
            L68:
                java.lang.String r8 = r7.f1986c     // Catch: java.lang.Throwable -> La7
                r1 = -1
                int r2 = r8.hashCode()     // Catch: java.lang.Throwable -> La7
                r3 = -834688111(0xffffffffce3fab91, float:-8.03923E8)
                if (r2 == r3) goto L84
                r3 = 270071285(0x1018f5f5, float:3.0166193E-29)
                if (r2 == r3) goto L7a
                goto L8e
            L7a:
                java.lang.String r2 = "sdk_init"
                boolean r8 = r8.equals(r2)     // Catch: java.lang.Throwable -> La7
                if (r8 == 0) goto L8e
                r8 = 0
                goto L8f
            L84:
                java.lang.String r2 = "sdk_init_end"
                boolean r8 = r8.equals(r2)     // Catch: java.lang.Throwable -> La7
                if (r8 == 0) goto L8e
                r8 = 1
                goto L8f
            L8e:
                r8 = -1
            L8f:
                if (r8 == 0) goto La0
                if (r8 == r0) goto L94
                goto La5
            L94:
                long r1 = r7.d     // Catch: java.lang.Throwable -> La7
                int r3 = r7.e     // Catch: java.lang.Throwable -> La7
                int r4 = r7.f     // Catch: java.lang.Throwable -> La7
                long r5 = r7.g     // Catch: java.lang.Throwable -> La7
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.a(r1, r3, r4, r5)     // Catch: java.lang.Throwable -> La7
                goto La5
            La0:
                long r0 = r7.g     // Catch: java.lang.Throwable -> La7
                bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g.b(r0)     // Catch: java.lang.Throwable -> La7
            La5:
                monitor-exit(r7)
                return
            La7:
                r8 = move-exception
                monitor-exit(r7)
                throw r8
            */
            throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d.a.a(int):void");
        }
    }

    private static Context b() {
        Context context = f1981a;
        return context != null ? context : bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.d();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String c() {
        return u.a("tt_device_info", b()).a("did", (String) null);
    }

    public static boolean d() {
        return !TextUtils.isEmpty(u.a("tt_device_info", b()).a("did", (String) null));
    }

    public static void e() {
        f1982b = new a("sdk_init", 0L, 0, 0);
        f1982b.a();
    }

    public static void f() {
        a aVar = f1982b;
        if (aVar == null) {
            Logger.d("TMe", "--==-- sdkInit埋点还未产生，applog回调已触发，直接return");
        } else if (aVar.f1985b) {
            Logger.d("TMe", "--==-- applog回调触发时，sdkInit埋点已经上报，直接return");
            f1982b = null;
        } else {
            f1982b.a(2);
            f1982b = null;
        }
    }

    public static void g() {
        a aVar = f1983c;
        if (aVar == null) {
            Logger.d("TMe", "--==-- sdkInitEnd埋点还未产生，applog回调已触发，直接return");
        } else if (aVar.f1985b) {
            Logger.d("TMe", "--==-- applog回调触发时，sdkInitEnd埋点已经上报，直接return");
            f1983c = null;
        } else {
            f1983c.a(2);
            f1983c = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void b(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        u.a("tt_device_info", b()).b("did", str);
    }

    public static void a(Context context) {
        f1981a = context;
    }

    public static void a(long j, int i, int i2) {
        f1983c = new a("sdk_init_end", j, i, i2);
        f1983c.a();
    }
}
