package com.sigmob.sdk.mraid;

import android.content.Context;
import android.graphics.Rect;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h {
    private final Context a;
    private final Rect b = new Rect();
    private final Rect c = new Rect();
    private final Rect d = new Rect();
    private final Rect e = new Rect();
    private final Rect f = new Rect();
    private final Rect g = new Rect();
    private final Rect h = new Rect();
    private final Rect i = new Rect();
    private final float j;

    h(Context context, float f) {
        this.a = context.getApplicationContext();
        this.j = f;
    }

    private void a(Rect rect, Rect rect2) {
        rect2.set(com.sigmob.sdk.common.utils.c.a(rect.left, this.a), com.sigmob.sdk.common.utils.c.a(rect.top, this.a), com.sigmob.sdk.common.utils.c.a(rect.right, this.a), com.sigmob.sdk.common.utils.c.a(rect.bottom, this.a));
    }

    Rect a() {
        return this.c;
    }

    void a(int i, int i2) {
        this.b.set(0, 0, i, i2);
        a(this.b, this.c);
    }

    void a(int i, int i2, int i3, int i4) {
        this.d.set(i, i2, i3 + i, i4 + i2);
        a(this.d, this.e);
    }

    Rect b() {
        return this.e;
    }

    void b(int i, int i2, int i3, int i4) {
        this.f.set(i, i2, i3 + i, i4 + i2);
        a(this.f, this.g);
    }

    Rect c() {
        return this.g;
    }

    void c(int i, int i2, int i3, int i4) {
        this.h.set(i, i2, i3 + i, i4 + i2);
        a(this.h, this.i);
    }

    Rect d() {
        return this.i;
    }
}
