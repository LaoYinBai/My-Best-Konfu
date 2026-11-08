package com.apm.insight.b;

import android.os.SystemClock;
import com.apm.insight.runtime.p;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: b, reason: collision with root package name */
    private static long f2767b;

    /* renamed from: a, reason: collision with root package name */
    private final b f2768a;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2769c = false;
    private final Runnable d = new Runnable() { // from class: com.apm.insight.b.c.1
        @Override // java.lang.Runnable
        public void run() throws Throwable {
            if (c.this.f2769c) {
                return;
            }
            c.this.f2768a.d();
            long unused = c.f2767b = SystemClock.uptimeMillis();
            f.a();
            p.b().a(c.this.d, 500L);
            com.apm.insight.runtime.b.a(c.f2767b);
        }
    };

    c(b bVar) {
        this.f2768a = bVar;
        p.b().a(this.d, 5000L);
    }

    public static boolean c() {
        return SystemClock.uptimeMillis() - f2767b <= 15000;
    }

    public void a() {
        if (this.f2769c) {
            return;
        }
        p.b().a(this.d, 5000L);
    }

    public void b() {
        this.f2769c = true;
    }
}
