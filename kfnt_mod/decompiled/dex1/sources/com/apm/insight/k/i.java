package com.apm.insight.k;

import android.content.Context;
import com.apm.insight.l.q;
import com.apm.insight.nativecrash.NativeImpl;
import com.apm.insight.runtime.p;
import com.apm.insight.runtime.s;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class i implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    private Context f2933a;

    private i(Context context) {
        this.f2933a = context;
    }

    public static void a(Context context) {
        a(context, 0);
    }

    public static void a(Context context, int i) {
        p.b().a(new i(context), i);
    }

    public static boolean a() {
        return b.a().c() || !com.apm.insight.l.a.b(com.apm.insight.h.g());
    }

    @Override // java.lang.Runnable
    public void run() throws Throwable {
        try {
            com.apm.insight.b.g.a(this.f2933a).a().f();
        } catch (Throwable unused) {
        }
        try {
            try {
                if (com.apm.insight.l.a.b(this.f2933a)) {
                    b.a().a(com.apm.insight.l.p.b(this.f2933a));
                } else {
                    NativeImpl.i();
                }
                s.a().a(com.apm.insight.h.a().b(), com.apm.insight.entity.b.b());
                if (p.b().a() == null) {
                    return;
                }
            } catch (Throwable th) {
                q.b(th);
                s.a().a(com.apm.insight.h.a().b(), com.apm.insight.entity.b.b());
                if (p.b().a() == null) {
                    return;
                }
            }
            com.apm.insight.j.d.a(p.b().a(), this.f2933a).a();
        } catch (Throwable th2) {
            s.a().a(com.apm.insight.h.a().b(), com.apm.insight.entity.b.b());
            if (p.b().a() != null) {
                com.apm.insight.j.d.a(p.b().a(), this.f2933a).a();
            }
            throw th2;
        }
    }
}
