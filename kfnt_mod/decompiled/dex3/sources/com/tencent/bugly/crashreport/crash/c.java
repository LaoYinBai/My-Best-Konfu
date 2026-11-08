package com.tencent.bugly.crashreport.crash;

import android.content.Context;
import com.ss.android.downloadlib.applink.AppLinkMonitor;
import com.ss.android.downloadlib.constants.DownloadConstants;
import com.tencent.bugly.BuglyStrategy;
import com.tencent.bugly.crashreport.common.strategy.StrategyBean;
import com.tencent.bugly.crashreport.crash.jni.NativeCrashHandler;
import com.tencent.bugly.proguard.o;
import com.tencent.bugly.proguard.p;
import com.tencent.bugly.proguard.r;
import com.tencent.bugly.proguard.u;
import com.tencent.bugly.proguard.w;
import com.tencent.bugly.proguard.x;
import com.tencent.bugly.proguard.z;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* compiled from: BUGLY */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class c {
    public static int a = 0;
    public static boolean b = false;
    public static int c = 2;
    public static boolean d = true;
    public static int e = 20000;
    public static int f = 20000;
    public static long g = 604800000;
    public static String h = null;
    public static boolean i = false;
    public static String j = null;
    public static int k = 5000;
    public static boolean l = true;
    public static String m;
    public static String n;
    private static c q;
    public final b o;
    private final Context p;
    private final e r;
    private final NativeCrashHandler s;
    private com.tencent.bugly.crashreport.common.strategy.a t;
    private w u;
    private final com.tencent.bugly.crashreport.crash.anr.b v;
    private Boolean w;

    private c(int i2, Context context, w wVar, boolean z, BuglyStrategy.a aVar, o oVar, String str) {
        a = i2;
        Context contextA = z.a(context);
        this.p = contextA;
        this.t = com.tencent.bugly.crashreport.common.strategy.a.a();
        this.u = wVar;
        this.o = new b(i2, contextA, u.a(), p.a(), this.t, aVar, oVar);
        com.tencent.bugly.crashreport.common.info.a aVarA = com.tencent.bugly.crashreport.common.info.a.a(contextA);
        this.r = new e(contextA, this.o, this.t, aVarA);
        this.s = NativeCrashHandler.getInstance(contextA, aVarA, this.o, this.t, wVar, z, str);
        aVarA.D = this.s;
        this.v = new com.tencent.bugly.crashreport.crash.anr.b(contextA, this.t, aVarA, wVar, this.o);
    }

    public static synchronized void a(int i2, Context context, boolean z, BuglyStrategy.a aVar, o oVar, String str) {
        if (q == null) {
            q = new c(1004, context, w.a(), z, aVar, null, null);
        }
    }

    public static synchronized c a() {
        return q;
    }

    public final void a(StrategyBean strategyBean) {
        this.r.a(strategyBean);
        this.s.onStrategyChanged(strategyBean);
        this.v.a(strategyBean);
        w.a().a(new AnonymousClass2(), 0L);
    }

    public final boolean b() {
        Boolean bool = this.w;
        if (bool != null) {
            return bool.booleanValue();
        }
        String str = com.tencent.bugly.crashreport.common.info.a.b().d;
        List<r> listA = p.a().a(1);
        ArrayList arrayList = new ArrayList();
        if (listA != null && listA.size() > 0) {
            for (r rVar : listA) {
                if (str.equals(rVar.c)) {
                    this.w = true;
                    arrayList.add(rVar);
                }
            }
            if (arrayList.size() > 0) {
                p.a().a(arrayList);
            }
            return true;
        }
        this.w = false;
        return false;
    }

    public final synchronized void c() {
        this.r.a();
        this.s.setUserOpened(true);
        this.v.a(true);
    }

    public final synchronized void d() {
        this.r.b();
        this.s.setUserOpened(false);
        this.v.a(false);
    }

    public final void e() {
        this.r.a();
    }

    public final void f() {
        this.s.setUserOpened(false);
    }

    public final void g() {
        this.s.setUserOpened(true);
    }

    public final void h() {
        this.v.a(true);
    }

    public final void i() {
        this.v.a(false);
    }

    public final synchronized void j() {
        this.s.testNativeCrash();
    }

    public final synchronized void k() {
        com.tencent.bugly.crashreport.crash.anr.b bVar = this.v;
        int i2 = 0;
        while (true) {
            int i3 = i2 + 1;
            if (i2 < 30) {
                try {
                    x.a("try main sleep for make a test anr! try:%d/30 , kill it if you don't want to wait!", Integer.valueOf(i3));
                    z.b(AppLinkMonitor.DELAY_TIME);
                    i2 = i3;
                } catch (Throwable th) {
                    if (x.a(th)) {
                        return;
                    }
                    th.printStackTrace();
                    return;
                }
            }
        }
    }

    public final boolean l() {
        return this.v.a();
    }

    public final void a(final Thread thread, final Throwable th, boolean z, String str, byte[] bArr, final boolean z2) {
        final boolean z3 = false;
        final String str2 = null;
        final byte[] bArr2 = null;
        this.u.a(new Runnable() { // from class: com.tencent.bugly.crashreport.crash.c.1
            @Override // java.lang.Runnable
            public final void run() {
                try {
                    x.c("post a throwable %b", Boolean.valueOf(z3));
                    c.this.r.a(thread, th, false, str2, bArr2);
                    if (z2) {
                        x.a("clear user datas", new Object[0]);
                        com.tencent.bugly.crashreport.common.info.a.a(c.this.p).A();
                    }
                } catch (Throwable th2) {
                    if (!x.b(th2)) {
                        th2.printStackTrace();
                    }
                    x.e("java catch error: %s", th.toString());
                }
            }
        });
    }

    public final void a(CrashDetailBean crashDetailBean) {
        this.o.d(crashDetailBean);
    }

    /* compiled from: BUGLY */
    /* renamed from: com.tencent.bugly.crashreport.crash.c$2, reason: invalid class name */
    final class AnonymousClass2 extends Thread {
        AnonymousClass2() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public final void run() throws Throwable {
            List<CrashDetailBean> list;
            if (z.a(c.this.p, "local_crash_lock", DownloadConstants.DEFAULT_NEXT_INSTALL_MIN_INTERVAL)) {
                List<CrashDetailBean> listA = c.this.o.a();
                if (listA != null && listA.size() > 0) {
                    int size = listA.size();
                    if (size > 100) {
                        ArrayList arrayList = new ArrayList();
                        Collections.sort(listA);
                        for (int i = 0; i < 100; i++) {
                            arrayList.add(listA.get((size - 1) - i));
                        }
                        list = arrayList;
                    } else {
                        list = listA;
                    }
                    c.this.o.a(list, 0L, false, false, false);
                }
                z.b(c.this.p, "local_crash_lock");
            }
        }
    }

    public final void a(long j2) {
        w.a().a(new AnonymousClass2(), 0L);
    }
}
