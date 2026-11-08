package com.kwad.sdk.core.page.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class RoundAngleImageView extends ImageView {
    private Path a;
    private float[] b;
    private RectF c;

    public RoundAngleImageView(Context context) {
        this(context, null);
    }

    public RoundAngleImageView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public RoundAngleImageView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new Path();
        this.c = new RectF();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.b == null || this.b.length != 8) {
            super.onDraw(canvas);
            return;
        }
        if (Build.VERSION.SDK_INT < 18) {
            setLayerType(1, null);
        }
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        float f = this.b[0] + this.b[3];
        float f2 = this.b[1] + this.b[7];
        this.a.rewind();
        float f3 = measuredWidth;
        if (f3 >= f) {
            float f4 = measuredHeight;
            if (f4 > f2) {
                this.c.set(0.0f, 0.0f, f3, f4);
                this.a.addRoundRect(this.c, this.b, Path.Direction.CW);
                canvas.clipPath(this.a);
            }
        }
        super.onDraw(canvas);
    }

    public void setRadius(float f) {
        this.b = new float[8];
        for (int i = 0; i < this.b.length; i++) {
            this.b[i] = f;
        }
    }

    public void setRadius(float[] fArr) {
        this.b = new float[8];
        for (int i = 0; i < this.b.length; i++) {
            this.b[i] = fArr[i];
        }
    }
}
