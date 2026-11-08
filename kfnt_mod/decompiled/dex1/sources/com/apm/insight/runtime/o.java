package com.apm.insight.runtime;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.apm.insight.CrashType;
import com.apm.insight.ICrashCallback;
import com.apm.insight.IOOMCallback;
import com.apm.insight.nativecrash.NativeImpl;
import java.io.File;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class o {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f3051a = false;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f3052b = false;

    /* renamed from: c, reason: collision with root package name */
    private static boolean f3053c = false;
    private static boolean d = false;
    private static boolean e = false;
    private static c f = new c();
    private static volatile boolean g = false;
    private static boolean h = false;

    public static c a() {
        return f;
    }

    public static void a(long j) {
        NativeImpl.a(j);
    }

    public static synchronized void a(@NonNull Application application, @NonNull Context context, boolean z, boolean z2, boolean z3, boolean z4, long j) {
        long jUptimeMillis = SystemClock.uptimeMillis();
        if (f3051a) {
            return;
        }
        f3051a = true;
        if (context == null || application == null) {
            throw new IllegalArgumentException("context or Application must be not null.");
        }
        com.apm.insight.h.a(application, context);
        if (z || z2) {
            com.apm.insight.g.a aVarA = com.apm.insight.g.a.a();
            if (z2) {
                aVarA.a(new com.apm.insight.i.b(context));
            }
            if (z) {
                aVarA.b(new com.apm.insight.g.d(context));
            }
            f3052b = true;
        }
        NativeImpl.a();
        if (z3) {
            d = NativeImpl.a(context);
            if (!d) {
                e = true;
            }
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            g = true;
            NativeImpl.h();
        }
        c(z4);
        com.apm.insight.l.q.a((Object) ("Npth.init takes " + (SystemClock.uptimeMillis() - jUptimeMillis) + " ms."));
    }

    public static synchronized void a(@NonNull Context context, boolean z, boolean z2, boolean z3, boolean z4, long j) {
        Application applicationH;
        if (com.apm.insight.h.h() != null) {
            applicationH = com.apm.insight.h.h();
        } else if (context instanceof Application) {
            applicationH = (Application) context;
            if (applicationH.getBaseContext() == null) {
                throw new IllegalArgumentException("初始化时传入的Application还未attach, 请在init时传入attachBaseContext的参数, 并在init之前手动调用Npth.setApplication(Application).");
            }
        } else {
            applicationH = (Application) context.getApplicationContext();
            if (applicationH == null) {
                throw new IllegalArgumentException("初始化时传入了baseContext, 导致无法获取Application实例, 请在init之前手动调用Npth.setApplication(Application).");
            }
            if (applicationH.getBaseContext() != null) {
                context = applicationH.getBaseContext();
            }
        }
        a(applicationH, context, z, z2, z3, z4, j);
    }

    public static void a(ICrashCallback iCrashCallback, CrashType crashType) {
        a().a(iCrashCallback, crashType);
    }

    public static void a(IOOMCallback iOOMCallback) {
        a().a(iOOMCallback);
    }

    public static void a(IOOMCallback iOOMCallback, CrashType crashType) {
        a().b(iOOMCallback);
    }

    public static void a(@NonNull com.apm.insight.d dVar) {
        com.apm.insight.h.i().setEncryptImpl(dVar);
    }

    public static void a(com.apm.insight.k.h hVar) {
        com.apm.insight.k.e.a(hVar);
    }

    public static void a(j jVar) {
        k.a(jVar);
    }

    public static void a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.apm.insight.d.a.a(str);
    }

    public static void a(String str, com.apm.insight.a.b bVar, com.apm.insight.a.c cVar) {
        if (TextUtils.isEmpty(str) || !new File(str).exists()) {
            return;
        }
        com.apm.insight.a.a.a().a(str, bVar, cVar);
    }

    public static void a(final String str, final com.apm.insight.e eVar) {
        p.b().a(new Runnable() { // from class: com.apm.insight.runtime.o.1
            @Override // java.lang.Runnable
            public void run() {
                if (com.apm.insight.l.a.b(com.apm.insight.h.g())) {
                    com.apm.insight.b.d.a(str, eVar);
                }
            }
        });
    }

    public static void a(String str, @Nullable Map<? extends String, ? extends String> map, @Nullable Map<String, String> map2, @Nullable com.apm.insight.f fVar) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.apm.insight.d.a.a(str, map, map2, fVar);
    }

    public static void a(String str, @Nullable Map<? extends String, ? extends String> map, @Nullable Map<String, String> map2, @Nullable Map<String, String> map3, @Nullable com.apm.insight.f fVar) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        com.apm.insight.d.a.a(str, map, map2, map3, fVar);
    }

    @Deprecated
    public static void a(@NonNull Throwable th) {
        if (com.apm.insight.h.i().isReportErrorEnable()) {
            com.apm.insight.g.a.a(th);
        }
    }

    public static void b(long j) {
        NativeImpl.b(j);
    }

    public static void b(ICrashCallback iCrashCallback, CrashType crashType) {
        a().b(iCrashCallback, crashType);
    }

    @Deprecated
    public static void b(String str) {
        if (com.apm.insight.h.i().isReportErrorEnable()) {
            com.apm.insight.g.a.c(str);
        }
    }

    public static boolean b() {
        return f3052b;
    }

    public static void c(long j) {
        NativeImpl.c(j);
    }

    public static void c(String str) {
        NativeImpl.b(str);
    }

    private static void c(final boolean z) {
        p.b().a(new Runnable() { // from class: com.apm.insight.runtime.o.2
            @Override // java.lang.Runnable
            public void run() {
                if (!o.g) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() { // from class: com.apm.insight.runtime.o.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            boolean unused = o.g = true;
                            NativeImpl.h();
                        }
                    });
                }
                o.d(z);
            }
        }, 0L);
    }

    public static boolean c() {
        return f3053c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(12:0|2|(1:4)(9:(1:7)|8|(1:10)|11|21|12|(1:17)|18|19)|5|8|(0)|11|21|12|(2:15|17)|18|19) */
    /* JADX WARN: Removed duplicated region for block: B:10:0x004f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void d(boolean r3) {
        /*
            android.content.Context r0 = com.apm.insight.h.g()
            com.apm.insight.runtime.a.f.a()
            com.apm.insight.runtime.m.a()
            java.lang.String r1 = "Npth.initAsync-createCallbackThread"
            com.apm.insight.j.a(r1)
            int r1 = com.apm.insight.nativecrash.NativeImpl.b()
            com.apm.insight.j.a()
            com.apm.insight.nativecrash.NativeImpl.c()
            boolean r2 = com.apm.insight.runtime.o.e
            if (r2 == 0) goto L27
            com.apm.insight.c r1 = com.apm.insight.b.a()
            java.lang.String r2 = "NativeLibraryLoad faild"
        L23:
            r1.a(r2)
            goto L30
        L27:
            if (r1 >= 0) goto L30
            com.apm.insight.c r1 = com.apm.insight.b.a()
            java.lang.String r2 = "createCallbackThread faild"
            goto L23
        L30:
            java.lang.String r1 = "Npth.initAsync-NpthDataManager"
            com.apm.insight.j.a(r1)
            com.apm.insight.e.a r1 = com.apm.insight.e.a.a()
            r1.a(r0)
            com.apm.insight.j.a()
            com.apm.insight.b.a()
            java.lang.String r1 = "Npth.initAsync-LaunchScanner"
            com.apm.insight.j.a(r1)
            com.apm.insight.k.i.a(r0)
            com.apm.insight.j.a()
            if (r3 == 0) goto L60
            java.lang.String r1 = "Npth.initAsync-CrashANRHandler"
            com.apm.insight.j.a(r1)
            com.apm.insight.b.g r1 = com.apm.insight.b.g.a(r0)
            r1.c()
            com.apm.insight.j.a()
            com.apm.insight.runtime.o.f3053c = r3
        L60:
            java.lang.String r3 = "Npth.initAsync-EventUploadQueue"
            com.apm.insight.j.a(r3)
            com.apm.insight.k.g r3 = com.apm.insight.k.g.a()
            r3.b()
            com.apm.insight.j.a()
            java.lang.String r3 = "Npth.initAsync-BlockMonitor"
            com.apm.insight.j.a(r3)
            com.apm.insight.j.a()
            java.lang.String r3 = "Npth.initAsync-OriginExceptionMonitor"
            com.apm.insight.j.a(r3)
            com.apm.insight.j.a()
            com.apm.insight.nativecrash.NativeImpl.f()
            com.apm.insight.i.a()
            java.lang.String r3 = "fastbot"
            java.io.File r3 = r0.getExternalFilesDir(r3)     // Catch: java.lang.Throwable -> La9
            android.content.Context r0 = com.apm.insight.h.g()     // Catch: java.lang.Throwable -> La9
            boolean r0 = com.apm.insight.l.a.b(r0)     // Catch: java.lang.Throwable -> La9
            if (r0 == 0) goto La9
            if (r3 == 0) goto La9
            boolean r0 = r3.exists()     // Catch: java.lang.Throwable -> La9
            if (r0 == 0) goto La9
            java.lang.String r3 = r3.getAbsolutePath()     // Catch: java.lang.Throwable -> La9
            com.apm.insight.runtime.o$3 r0 = new com.apm.insight.runtime.o$3     // Catch: java.lang.Throwable -> La9
            r0.<init>()     // Catch: java.lang.Throwable -> La9
            com.apm.insight.b.d.a(r3, r0)     // Catch: java.lang.Throwable -> La9
        La9:
            com.apm.insight.k.k.d()
            com.apm.insight.nativecrash.NativeImpl.j()
            java.lang.String r3 = "afterNpthInitAsync"
            java.lang.String r0 = "noValue"
            com.apm.insight.runtime.r.a(r3, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.runtime.o.d(boolean):void");
    }

    public static boolean d() {
        return d;
    }

    public static boolean e() {
        return f3051a;
    }

    public static void f() {
        if (!f3051a || f3052b) {
            return;
        }
        Context contextG = com.apm.insight.h.g();
        com.apm.insight.g.a aVarA = com.apm.insight.g.a.a();
        aVarA.a(new com.apm.insight.i.b(contextG));
        aVarA.b(new com.apm.insight.g.d(contextG));
    }

    public static void g() {
        if (f3051a) {
            com.apm.insight.b.g.a(com.apm.insight.h.g()).c();
            f3053c = true;
        }
    }

    public static boolean h() {
        if (f3051a && !d) {
            d = NativeImpl.a(com.apm.insight.h.g());
            if (!d) {
                e = true;
            }
        }
        return d;
    }

    public static boolean i() {
        return com.apm.insight.b.c.c();
    }

    public static void j() {
        if (f3051a) {
            com.apm.insight.b.g.a(com.apm.insight.h.g()).d();
            f3053c = false;
        }
    }

    public static boolean k() {
        return com.apm.insight.g.a.b() || NativeImpl.d();
    }

    public static boolean l() {
        return com.apm.insight.g.a.c() || NativeImpl.d();
    }

    public static boolean m() {
        return com.apm.insight.g.a.b();
    }

    public static boolean n() {
        return h;
    }

    public static void o() {
        h = true;
    }
}
