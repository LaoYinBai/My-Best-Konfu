package com.sigmob.sdk.base.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.views.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f extends a {
    private final Paint a = new Paint();
    private final Paint b;
    private int c;
    private int d;
    private int e;
    private int f;
    private float g;
    private final int h;

    public f(Context context) {
        this.a.setColor(-1);
        this.a.setAlpha(128);
        this.a.setStyle(c.a.a);
        this.a.setAntiAlias(true);
        this.b = new Paint();
        this.b.setColor(c.a.b);
        this.b.setAlpha(255);
        this.b.setStyle(c.a.c);
        this.b.setAntiAlias(true);
        this.h = com.sigmob.sdk.common.utils.c.b(4.0f, context);
    }

    private void a() {
        this.e = this.c;
    }

    @SuppressLint({"DefaultLocale"})
    public void a(int i) {
        if (i >= this.f) {
            this.e = i;
            this.f = i;
        } else if (i != 0) {
            SigmobLog.d(String.format("Progress not monotonically increasing: last = %d, current = %d", Integer.valueOf(this.f), Integer.valueOf(i)));
            a();
        }
        invalidateSelf();
    }

    public void a(int i, int i2) {
        this.c = i;
        this.d = i2;
        this.g = this.d / this.c;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        canvas.drawRect(getBounds(), this.a);
        canvas.drawRect(getBounds().left, getBounds().top, getBounds().right * (this.e / this.c), getBounds().bottom, this.b);
        if (this.d <= 0 || this.d >= this.c) {
            return;
        }
        float f = getBounds().right * this.g;
        canvas.drawRect(f, getBounds().top, f + this.h, getBounds().bottom, this.b);
    }

    @Override // com.sigmob.sdk.base.views.a, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getOpacity() {
        return super.getOpacity();
    }

    @Override // com.sigmob.sdk.base.views.a, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setAlpha(int i) {
        super.setAlpha(i);
    }

    @Override // com.sigmob.sdk.base.views.a, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(ColorFilter colorFilter) {
        super.setColorFilter(colorFilter);
    }
}
