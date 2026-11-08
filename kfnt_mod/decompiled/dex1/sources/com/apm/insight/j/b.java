package com.apm.insight.j;

import android.content.Context;
import android.os.Handler;
import com.apm.insight.h;
import com.apm.insight.runtime.p;
import com.bytedance.b.a.a.c.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends a {

    /* renamed from: b, reason: collision with root package name */
    private static Runnable f2888b = new Runnable() { // from class: com.apm.insight.j.b.1
        @Override // java.lang.Runnable
        public void run() {
            p.b().a().removeCallbacks(this);
            p.b().a(new b(p.b().a(), 0L, g.e, h.g()));
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private Context f2889a;

    public b(Handler handler, long j, long j2, Context context) {
        super(handler, j, j2);
        this.f2889a = context;
    }

    public static void d() {
        p.b().a(f2888b, 100L);
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x001e A[Catch: Throwable -> 0x0029, TRY_LEAVE, TryCatch #1 {Throwable -> 0x0029, blocks: (B:6:0x0010, B:8:0x0016, B:9:0x001e), top: B:15:0x0010 }] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void run() {
        /*
            r3 = this;
            com.apm.insight.runtime.d r0 = com.apm.insight.h.a()     // Catch: java.lang.Throwable -> Ld
            com.apm.insight.ICommonParams r0 = r0.c()     // Catch: java.lang.Throwable -> Ld
            java.util.Map r0 = r0.getCommonParams()     // Catch: java.lang.Throwable -> Ld
            goto Le
        Ld:
            r0 = 0
        Le:
            if (r0 == 0) goto L1e
            boolean r1 = com.apm.insight.runtime.d.a(r0)     // Catch: java.lang.Throwable -> L29
            if (r1 == 0) goto L1e
            long r0 = r3.c()     // Catch: java.lang.Throwable -> L29
            r3.a(r0)     // Catch: java.lang.Throwable -> L29
            goto L29
        L1e:
            com.apm.insight.runtime.s r1 = com.apm.insight.runtime.s.a()     // Catch: java.lang.Throwable -> L29
            org.json.JSONArray r2 = com.apm.insight.entity.b.b()     // Catch: java.lang.Throwable -> L29
            r1.a(r0, r2)     // Catch: java.lang.Throwable -> L29
        L29:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.apm.insight.j.b.run():void");
    }
}
