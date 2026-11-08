package com.mbridge.msdk.widget.custom.baseview;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/* compiled from: CircularProgressDrawable.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class b extends Drawable {
    private int c;
    private int d;
    private int e;
    private RectF f;
    private Paint g;
    private Path h;
    private float b = -90.0f;
    private float a = 0.0f;

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return 1;
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
    }

    public b(int i, int i2, int i3) {
        this.c = i;
        this.d = i2;
        this.e = i3;
    }

    public final void a(float f) {
        this.a = f;
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        Rect bounds = getBounds();
        if (this.h == null) {
            this.h = new Path();
        }
        this.h.reset();
        Path path = this.h;
        if (this.f == null) {
            float f = this.d / 2;
            this.f = new RectF(f, f, this.c - r2, this.c - r2);
        }
        path.addArc(this.f, this.b, this.a);
        this.h.offset(bounds.left, bounds.top);
        Path path2 = this.h;
        if (this.g == null) {
            this.g = new Paint();
            this.g.setAntiAlias(true);
            this.g.setStyle(Paint.Style.STROKE);
            this.g.setStrokeWidth(this.d);
            this.g.setColor(this.e);
        }
        canvas.drawPath(path2, this.g);
    }
}
