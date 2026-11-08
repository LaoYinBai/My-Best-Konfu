package com.kwad.sdk.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;
import androidx.annotation.ColorInt;
import androidx.annotation.RequiresApi;
import com.kwad.sdk.R;

@SuppressLint({"AppCompatCustomView"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KSCouponLabelTextView extends TextView implements b {
    private float a;
    private float b;

    @ColorInt
    private int c;
    private float d;
    private final Paint e;
    private final Rect f;
    private final RectF g;
    private final RectF h;
    private final RectF i;
    private final Path j;
    private Path k;
    private Path l;
    private final RectF m;
    private boolean n;

    public KSCouponLabelTextView(Context context) throws Resources.NotFoundException {
        super(context);
        this.e = new Paint();
        this.f = new Rect();
        this.g = new RectF();
        this.h = new RectF();
        this.i = new RectF();
        this.j = new Path();
        this.m = new RectF();
        this.n = true;
        a(context, null, 0);
    }

    public KSCouponLabelTextView(Context context, AttributeSet attributeSet) throws Resources.NotFoundException {
        super(context, attributeSet);
        this.e = new Paint();
        this.f = new Rect();
        this.g = new RectF();
        this.h = new RectF();
        this.i = new RectF();
        this.j = new Path();
        this.m = new RectF();
        this.n = true;
        a(context, attributeSet, 0);
    }

    public KSCouponLabelTextView(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        super(context, attributeSet, i);
        this.e = new Paint();
        this.f = new Rect();
        this.g = new RectF();
        this.h = new RectF();
        this.i = new RectF();
        this.j = new Path();
        this.m = new RectF();
        this.n = true;
        a(context, attributeSet, i);
    }

    private void a() {
        this.e.setColor(this.c);
        this.e.setStrokeWidth(this.b);
        this.e.setStyle(Paint.Style.STROKE);
        this.e.setAntiAlias(true);
    }

    @SuppressLint({"CustomViewStyleable"})
    private void a(Context context, AttributeSet attributeSet, int i) throws Resources.NotFoundException {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ksad_KSCouponLabelTextView, i, 0);
        int color = context.getResources().getColor(R.color.ksad_reward_main_color);
        this.a = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KSCouponLabelTextView_ksad_labelRadius, 8.0f);
        this.b = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KSCouponLabelTextView_ksad_strokeSize, 2.0f);
        this.c = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_KSCouponLabelTextView_ksad_strokeColor, color);
        this.d = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KSCouponLabelTextView_ksad_sideRadius, 16.0f);
        typedArrayObtainStyledAttributes.recycle();
        a();
    }

    @RequiresApi(api = 19)
    private void a(Path path, Path path2, Path path3, RectF rectF, RectF rectF2, RectF rectF3) {
        path.reset();
        path.addRoundRect(rectF, this.a, this.a, Path.Direction.CW);
        path2.addArc(rectF2, 90.0f, -180.0f);
        path3.addArc(rectF3, 90.0f, 180.0f);
        path.op(this.k, Path.Op.DIFFERENCE);
        path.op(this.l, Path.Op.DIFFERENCE);
    }

    private void a(Path path, RectF rectF, RectF rectF2, RectF rectF3) {
        path.reset();
        path.moveTo(rectF.left, rectF.top + this.a);
        this.m.set(rectF);
        this.m.bottom = this.m.top + (this.a * 2.0f);
        this.m.right = this.m.left + (this.a * 2.0f);
        path.arcTo(this.m, 180.0f, 90.0f);
        path.lineTo(rectF.width() - this.a, rectF.top);
        this.m.set(rectF);
        this.m.left = this.m.right - (this.a * 2.0f);
        this.m.bottom = this.m.top + (this.a * 2.0f);
        path.arcTo(this.m, 270.0f, 90.0f);
        path.lineTo(rectF.right, rectF3.top);
        path.arcTo(rectF3, 270.0f, -180.0f);
        path.lineTo(rectF.right, rectF.bottom - this.a);
        this.m.set(rectF);
        this.m.left = this.m.right - (this.a * 2.0f);
        this.m.top = this.m.bottom - (this.a * 2.0f);
        path.arcTo(this.m, 0.0f, 90.0f);
        path.lineTo(rectF.left + this.a, rectF.bottom);
        this.m.set(rectF);
        this.m.right = this.m.left + (this.a * 2.0f);
        this.m.top = this.m.bottom - (this.a * 2.0f);
        path.arcTo(this.m, 90.0f, 90.0f);
        path.lineTo(rectF.left, rectF2.bottom);
        path.arcTo(rectF2, 90.0f, -180.0f);
        path.close();
    }

    private void a(RectF rectF, RectF rectF2) {
        rectF2.set(rectF);
        rectF2.left -= this.d;
        rectF2.right = rectF2.left + (this.d * 2.0f);
        rectF2.top += (rectF.height() - (this.d * 2.0f)) / 2.0f;
        rectF2.bottom = rectF2.top + (this.d * 2.0f);
    }

    private void b(RectF rectF, RectF rectF2) {
        rectF2.set(rectF);
        rectF2.right += this.d;
        rectF2.left = rectF2.right - (this.d * 2.0f);
        rectF2.top += (rectF.height() - (this.d * 2.0f)) / 2.0f;
        rectF2.bottom = rectF2.top + (this.d * 2.0f);
    }

    @Override // com.kwad.sdk.widget.b
    public void a(c cVar) {
        this.c = getResources().getColor(com.kwad.sdk.core.config.b.aM() ? R.color.ksad_reward_main_color : R.color.ksad_reward_main_color_orange);
        setTextColor(this.c);
        a();
        invalidate();
    }

    @Override // android.widget.TextView, android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.f.setEmpty();
        getDrawingRect(this.f);
        float f = this.b / 2.0f;
        this.g.set(this.f);
        this.g.left += f;
        this.g.top += f;
        this.g.right -= f;
        this.g.bottom -= f;
        a(this.g, this.h);
        b(this.g, this.i);
        if (Build.VERSION.SDK_INT >= 19) {
            if (this.k == null) {
                this.k = new Path();
            } else {
                this.k.reset();
            }
            if (this.l == null) {
                this.l = new Path();
            } else {
                this.l.reset();
            }
            a(this.j, this.k, this.l, this.g, this.h, this.i);
        } else {
            a(this.j, this.g, this.h, this.i);
        }
        canvas.drawPath(this.j, this.e);
    }

    @Override // android.widget.TextView, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.n) {
            if (((float) (getPaddingLeft() + getPaddingRight())) + getPaint().measureText(getText().toString()) <= ((float) getMeasuredWidth())) {
                return;
            }
            setVisibility(8);
        }
    }
}
