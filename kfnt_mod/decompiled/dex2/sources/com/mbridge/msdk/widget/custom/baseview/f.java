package com.mbridge.msdk.widget.custom.baseview;

import android.graphics.drawable.GradientDrawable;

/* compiled from: StrokeGradientDrawable.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class f {
    private int a;
    private int b;
    private GradientDrawable c;

    public f(GradientDrawable gradientDrawable) {
        this.c = gradientDrawable;
    }

    public final void a(int i) {
        this.a = i;
        this.c.setStroke(i, this.b);
    }

    public final void b(int i) {
        this.b = i;
        this.c.setStroke(this.a, i);
    }

    public final GradientDrawable a() {
        return this.c;
    }
}
