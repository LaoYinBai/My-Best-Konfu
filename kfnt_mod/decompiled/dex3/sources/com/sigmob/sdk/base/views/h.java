package com.sigmob.sdk.base.views;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h extends Drawable {
    private final Paint a;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private final int f;
    private final int[] g;
    private RectF h;

    public static class a {
        private int e;
        private int f;
        private int a = 1;
        private int b = 12;
        private int c = Color.parseColor("#4d000000");
        private int d = 18;
        private int[] g = new int[1];

        a() {
            this.e = 0;
            this.f = 0;
            this.e = 0;
            this.f = 0;
            this.g[0] = 0;
        }

        a a(int i) {
            this.b = i;
            return this;
        }

        h a() {
            return new h(this.a, this.g, this.b, this.c, this.d, this.e, this.f);
        }

        a b(int i) {
            this.c = i;
            return this;
        }

        a c(int i) {
            this.d = i;
            return this;
        }

        a d(int i) {
            this.e = i;
            return this;
        }

        a e(int i) {
            this.f = i;
            return this;
        }

        a f(int i) {
            this.g[0] = i;
            return this;
        }
    }

    private h(int i, int[] iArr, int i2, int i3, int i4, int i5, int i6) {
        this.c = i;
        this.g = iArr;
        this.d = i2;
        this.b = i4;
        this.e = i5;
        this.f = i6;
        this.a = new Paint();
        this.a.setColor(0);
        this.a.setAntiAlias(true);
        this.a.setShadowLayer(i4, i5, i6, i3);
        this.a.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_ATOP));
    }

    public static void a(View view, int i, int i2, int i3, int i4, int i5, int i6) {
        h hVarA = new a().f(i).a(i2).b(i3).c(i4).d(i5).e(i6).a();
        view.setLayerType(1, null);
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(hVarA);
        } else {
            view.setBackgroundDrawable(hVarA);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        if (this.g != null) {
            if (this.g.length == 1) {
                paint.setColor(this.g[0]);
            } else {
                paint.setShader(new LinearGradient(this.h.left, this.h.height() / 2.0f, this.h.right, this.h.height() / 2.0f, this.g, (float[]) null, Shader.TileMode.CLAMP));
            }
        }
        paint.setAntiAlias(true);
        if (this.c == 1) {
            canvas.drawRoundRect(this.h, this.d, this.d, this.a);
            canvas.drawRoundRect(this.h, this.d, this.d, paint);
        } else {
            canvas.drawCircle(this.h.centerX(), this.h.centerY(), Math.min(this.h.width(), this.h.height()) / 2.0f, this.a);
            canvas.drawCircle(this.h.centerX(), this.h.centerY(), Math.min(this.h.width(), this.h.height()) / 2.0f, paint);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.a.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setBounds(int i, int i2, int i3, int i4) {
        super.setBounds(i, i2, i3, i4);
        this.h = new RectF((i + this.b) - this.e, (i2 + this.b) - this.f, (i3 - this.b) - this.e, (i4 - this.b) - this.f);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.a.setColorFilter(colorFilter);
    }
}
