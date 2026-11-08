package com.kwad.sdk.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import com.kwad.sdk.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DividerView extends View {
    public static int a = 0;
    public static int b = 1;
    private Paint c;
    private int d;
    private int e;

    public DividerView(Context context) {
        this(context, null);
    }

    public DividerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArrayObtainStyledAttributes = context.getTheme().obtainStyledAttributes(attributeSet, R.styleable.ksad_DividerView, 0, 0);
        try {
            int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ksad_DividerView_ksad_dashGap, 5);
            int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ksad_DividerView_ksad_dashLength, 5);
            int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R.styleable.ksad_DividerView_ksad_dashThickness, 3);
            this.e = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_DividerView_ksad_color, -16777216);
            this.d = typedArrayObtainStyledAttributes.getInt(R.styleable.ksad_DividerView_ksad_orientation, a);
            typedArrayObtainStyledAttributes.recycle();
            this.c = new Paint();
            this.c.setAntiAlias(true);
            this.c.setColor(this.e);
            this.c.setStyle(Paint.Style.STROKE);
            this.c.setStrokeWidth(dimensionPixelSize3);
            this.c.setPathEffect(new DashPathEffect(new float[]{dimensionPixelSize2, dimensionPixelSize}, 0.0f));
        } catch (Throwable th) {
            typedArrayObtainStyledAttributes.recycle();
            throw th;
        }
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        float width;
        float f;
        float height;
        Paint paint;
        Canvas canvas2;
        float f2;
        if (this.d == a) {
            height = getHeight() * 0.5f;
            f2 = 0.0f;
            width = getWidth();
            paint = this.c;
            canvas2 = canvas;
            f = height;
        } else {
            width = getWidth() * 0.5f;
            f = 0.0f;
            height = getHeight();
            paint = this.c;
            canvas2 = canvas;
            f2 = width;
        }
        canvas2.drawLine(f2, f, width, height, paint);
    }

    public void setDividerColor(int i) {
        this.e = i;
        this.c.setColor(this.e);
        postInvalidate();
    }
}
