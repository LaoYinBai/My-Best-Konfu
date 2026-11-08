package com.apm.insight.b;

import android.content.Context;
import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static volatile g f2782a;

    /* renamed from: c, reason: collision with root package name */
    private static h f2783c;

    /* renamed from: b, reason: collision with root package name */
    private final b f2784b;

    private g(@NonNull Context context) {
        this.f2784b = new b(context);
        f2783c = new h(0);
        f2783c.b();
    }

    public static g a(Context context) {
        if (f2782a == null) {
            synchronized (g.class) {
                if (f2782a == null) {
                    f2782a = new g(context);
                }
            }
        }
        return f2782a;
    }

    public static h b() {
        return f2783c;
    }

    public b a() {
        return this.f2784b;
    }

    public void c() {
        this.f2784b.a();
    }

    public void d() {
        this.f2784b.b();
    }
}
