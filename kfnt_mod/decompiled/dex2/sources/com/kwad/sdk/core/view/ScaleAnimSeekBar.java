package com.kwad.sdk.core.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import androidx.annotation.ColorInt;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ScaleAnimSeekBar extends View {
    private boolean A;
    private boolean B;
    private boolean C;
    private boolean D;
    private WeakReference<a> E;
    private boolean F;
    private boolean G;
    private ValueAnimator H;
    private ValueAnimator I;
    private ValueAnimator J;
    private float K;
    private float L;
    private float M;
    private float N;
    private int O;
    private boolean P;
    private Paint a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private int i;
    private boolean j;
    private int k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private GradientDrawable r;
    private GradientDrawable s;
    private GradientDrawable t;
    private Rect u;
    private Rect v;
    private Rect w;
    private Rect x;
    private Drawable y;
    private boolean z;

    public interface a {
        void a(ScaleAnimSeekBar scaleAnimSeekBar);

        void a(ScaleAnimSeekBar scaleAnimSeekBar, int i, boolean z);

        void b(ScaleAnimSeekBar scaleAnimSeekBar);
    }

    public ScaleAnimSeekBar(Context context) {
        this(context, null);
    }

    public ScaleAnimSeekBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public ScaleAnimSeekBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = 100;
        this.j = false;
        this.y = null;
        this.z = false;
        this.A = false;
        this.B = false;
        this.C = true;
        this.F = true;
        this.G = false;
        this.K = 1.0f;
        this.L = 1.34f;
        this.M = 1.0f;
        this.N = 2.0f;
        a(context, attributeSet);
    }

    private float a(int i) {
        return ((this.k * (i - this.f)) / (this.g - this.f)) - (this.k / 2.0f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void a(float f) {
        this.x.left = (int) (f - this.e);
        this.x.right = (int) (this.e + f);
        this.v.right = (int) f;
        invalidate();
    }

    private void a(int i, int i2) {
        if (i <= 0 || i2 <= 0) {
            return;
        }
        this.k = this.F ? (int) (i - ((this.O * 2) * (this.L - this.K))) : i - (this.O * 2);
        this.u.top = -this.i;
        this.u.bottom = -this.u.top;
        this.u.left = (this.j ? -i : -this.k) / 2;
        this.u.right = this.j ? i / 2 : this.k / 2;
        this.v.top = -this.i;
        this.v.bottom = -this.v.top;
        this.v.left = (this.j ? -i : -this.k) / 2;
        this.v.right = (-this.k) / 2;
        this.w.top = -this.i;
        this.w.bottom = -this.v.top;
        Rect rect = this.w;
        if (!this.j) {
            i = this.k;
        }
        rect.left = (-i) / 2;
        this.w.right = (-this.k) / 2;
        this.x.top = -this.e;
        this.x.bottom = this.e;
        this.x.left = ((-this.k) / 2) - this.e;
        this.x.right = ((-this.k) / 2) + this.e;
        setThumbDrawable(this.y);
        setProgress(this.m);
        setSecondaryProgress(this.o);
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            a(context);
        }
        this.a = new Paint();
        this.a.setStyle(Paint.Style.FILL);
        this.a.setAntiAlias(true);
        this.r = new GradientDrawable();
        this.r.setShape(0);
        this.r.setColor(this.b);
        this.s = new GradientDrawable();
        this.s.setShape(0);
        this.s.setColor(this.c);
        this.t = new GradientDrawable();
        this.t.setShape(0);
        this.t.setColor(this.d);
        this.u = new Rect();
        this.v = new Rect();
        this.x = new Rect();
        this.w = new Rect();
        this.m = this.f;
    }

    private void a(Canvas canvas) {
        canvas.save();
        if (this.y != null) {
            this.y.setBounds(this.x);
            this.y.draw(canvas);
        } else {
            this.a.setColor(this.c);
            canvas.drawCircle(this.x.centerX(), this.x.centerY(), (this.x.width() * this.K) / 2.0f, this.a);
        }
        canvas.restore();
    }

    private void a(Canvas canvas, Rect rect, GradientDrawable gradientDrawable) {
        canvas.save();
        Rect rect2 = new Rect();
        rect2.top = (int) (rect.top * this.M);
        rect2.bottom = (int) (rect.bottom * this.M);
        rect2.left = rect.left;
        rect2.right = rect.right;
        gradientDrawable.setBounds(rect2);
        gradientDrawable.setCornerRadius(this.h * this.M);
        gradientDrawable.draw(canvas);
        canvas.restore();
    }

    private void a(boolean z, int i) {
        if (!z) {
            this.m = i;
            a(b(a(i)));
            return;
        }
        float fB = b(a(this.m));
        float fB2 = b(a(i));
        if (this.J == null) {
            this.J = new ValueAnimator();
            this.J.setDuration(300L);
            this.J.setInterpolator(new Interpolator() { // from class: com.kwad.sdk.core.view.ScaleAnimSeekBar.1
                @Override // android.animation.TimeInterpolator
                public float getInterpolation(float f) {
                    float f2 = f - 1.0f;
                    return (f2 * f2 * f2) + 1.0f;
                }
            });
            this.J.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.core.view.ScaleAnimSeekBar.2
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    ScaleAnimSeekBar.this.m = ScaleAnimSeekBar.this.b((int) fFloatValue);
                    ScaleAnimSeekBar.this.a(fFloatValue);
                }
            });
        } else {
            this.J.cancel();
        }
        this.J.setFloatValues(fB, fB2);
        this.J.start();
    }

    private boolean a(float f, float f2) {
        return this.x.left < this.x.right && this.x.top < this.x.bottom && f >= (((float) this.x.left) * this.K) - ((float) this.l) && f <= (((float) this.x.right) * this.K) + ((float) this.l) && f2 >= (((float) this.x.top) * this.K) - ((float) this.l) && f2 <= (((float) this.x.bottom) * this.K) + ((float) this.l);
    }

    private float b(float f) {
        float f2 = this.k / 2;
        if (f > f2) {
            return f2;
        }
        float f3 = -f2;
        return f < f3 ? f3 : f;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int b(int i) {
        return i > this.k / 2 ? this.g : i < (-this.k) / 2 ? this.f : Math.round(((i + (this.k / 2.0f)) * (this.g - this.f)) / this.k) + this.f;
    }

    private void b(boolean z) {
        if (this.F) {
            boolean z2 = z;
            c(z2);
            d(z2);
        }
    }

    private boolean b(float f, float f2) {
        return this.u.left < this.u.right && this.u.top < this.u.bottom && f >= (((float) this.u.left) * this.M) - ((float) this.l) && f <= (((float) this.u.right) * this.M) + ((float) this.l) && f2 >= (((float) this.u.top) * this.M) - ((float) this.l) && f2 <= (((float) this.u.bottom) * this.M) + ((float) this.l);
    }

    private void c(boolean z) {
        float f = this.K;
        float f2 = z ? this.L : 1.0f;
        if (this.H == null) {
            this.H = new ValueAnimator();
            this.H.setDuration(250L);
            this.H.setInterpolator(new LinearInterpolator());
            this.H.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.core.view.ScaleAnimSeekBar.3
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ScaleAnimSeekBar.this.K = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    ScaleAnimSeekBar.this.requestLayout();
                }
            });
        } else {
            this.H.cancel();
        }
        this.H.setFloatValues(f, f2);
        this.H.start();
    }

    private void d(boolean z) {
        float f = this.M;
        float f2 = z ? this.N : 1.0f;
        if (this.I == null) {
            this.I = new ValueAnimator();
            this.I.setDuration(250L);
            this.I.setInterpolator(new LinearInterpolator());
            this.I.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.core.view.ScaleAnimSeekBar.4
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    ScaleAnimSeekBar.this.M = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    ScaleAnimSeekBar.this.requestLayout();
                }
            });
        } else {
            this.I.cancel();
        }
        this.I.setFloatValues(f, f2);
        this.I.start();
    }

    private a getOnSeekBarChangedListener() {
        if (this.E != null) {
            return this.E.get();
        }
        return null;
    }

    public void a(int i, boolean z, boolean z2) {
        if (i <= this.f) {
            i = this.f;
        } else if (i >= this.g) {
            i = this.g;
        }
        a(z, i);
        a onSeekBarChangedListener = getOnSeekBarChangedListener();
        if (onSeekBarChangedListener != null && this.n != this.m) {
            this.B = z2;
            onSeekBarChangedListener.a(this, this.m, this.B);
            this.B = false;
        }
        this.n = this.m;
    }

    public void a(Context context) {
        this.F = true;
        this.O = com.kwad.sdk.a.kwai.a.a(context, 10.0f);
        this.e = com.kwad.sdk.a.kwai.a.a(context, 3.0f);
        this.l = com.kwad.sdk.a.kwai.a.a(context, 20.0f);
        this.y = null;
        this.G = false;
        this.i = com.kwad.sdk.a.kwai.a.a(context, 0.3f);
        this.h = com.kwad.sdk.a.kwai.a.a(context, 1.0f);
        this.b = 654311423;
        this.c = -1;
        this.d = 1090519039;
        this.f = 0;
        this.g = 100;
        this.j = false;
    }

    public void a(boolean z) {
        this.P = z;
        b(z);
    }

    public int getMaxProgress() {
        return this.g;
    }

    public int getProgress() {
        return this.m;
    }

    public int getProgressLength() {
        return this.k;
    }

    public int getProgressX() {
        return (int) (getX() + (this.e * this.L));
    }

    public int getSecondaryProgress() {
        return this.o;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(this.q / 2, this.p / 2);
        a(canvas, this.u, this.r);
        a(canvas, this.w, this.t);
        a(canvas, this.v, this.s);
        if (this.P) {
            a(canvas);
        }
        canvas.restore();
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (mode != 1073741824) {
            size = getWidth();
        }
        this.q = size;
        if (mode2 == 1073741824) {
            this.p = size2;
        } else {
            this.p = getHeight();
        }
        a(this.q, this.p);
        setMeasuredDimension(this.q, this.p);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0078  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r8) {
        /*
            r7 = this;
            float r0 = r8.getX()
            int r1 = r7.q
            int r1 = r1 / 2
            float r1 = (float) r1
            float r0 = r0 - r1
            float r1 = r8.getY()
            int r2 = r7.p
            int r2 = r2 / 2
            float r2 = (float) r2
            float r1 = r1 - r2
            android.view.ViewParent r2 = r7.getParent()
            com.kwad.sdk.core.view.ScaleAnimSeekBar$a r3 = r7.getOnSeekBarChangedListener()
            int r4 = r8.getAction()
            r5 = 0
            r6 = 1
            switch(r4) {
                case 0: goto L5b;
                case 1: goto L38;
                case 2: goto L27;
                default: goto L25;
            }
        L25:
            goto L8f
        L27:
            boolean r8 = r7.z
            if (r8 != 0) goto L2f
            boolean r8 = r7.A
            if (r8 == 0) goto L8f
        L2f:
            int r8 = (int) r0
            int r8 = r7.b(r8)
            r7.a(r8, r5, r6)
            goto L8f
        L38:
            r7.D = r5
            boolean r8 = r7.A
            if (r8 != 0) goto L42
            boolean r8 = r7.z
            if (r8 == 0) goto L55
        L42:
            r7.A = r5
            r7.z = r5
            int r8 = (int) r0
            int r8 = r7.b(r8)
            boolean r0 = r7.G
            r7.a(r8, r0, r6)
            if (r3 == 0) goto L55
            r3.b(r7)
        L55:
            if (r2 == 0) goto L8f
            r2.requestDisallowInterceptTouchEvent(r5)
            goto L8f
        L5b:
            boolean r4 = r7.C
            if (r4 != 0) goto L64
            boolean r8 = super.onTouchEvent(r8)
            return r8
        L64:
            boolean r8 = r7.a(r0, r1)
            if (r8 == 0) goto L7c
            r7.b(r6)
            r7.z = r6
            r7.D = r6
            if (r3 == 0) goto L76
            r3.a(r7)
        L76:
            if (r2 == 0) goto L8f
        L78:
            r2.requestDisallowInterceptTouchEvent(r6)
            goto L8f
        L7c:
            boolean r8 = r7.b(r0, r1)
            if (r8 == 0) goto L8f
            r7.b(r6)
            r7.A = r6
            if (r3 == 0) goto L8c
            r3.a(r7)
        L8c:
            if (r2 == 0) goto L8f
            goto L78
        L8f:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.view.ScaleAnimSeekBar.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setMaxProgress(int i) {
        this.g = i;
    }

    public void setMinProgress(int i) {
        this.f = i;
        if (this.m < this.f) {
            this.m = this.f;
        }
    }

    public void setOnSeekBarChangeListener(a aVar) {
        this.E = new WeakReference<>(aVar);
    }

    public void setProgress(int i) {
        a(i, false, false);
    }

    public void setProgressBackgroundColor(@ColorInt int i) {
        this.b = i;
        this.r.setColor(this.b);
    }

    public void setProgressColor(@ColorInt int i) {
        this.c = i;
        this.s.setColor(this.c);
    }

    public void setSecondaryProgress(int i) {
        if (i <= this.f) {
            i = this.f;
        } else if (i >= this.g) {
            i = this.g;
        }
        this.o = i;
        this.w.right = (int) b(a(i));
        invalidate();
    }

    public void setSecondaryProgressColor(@ColorInt int i) {
        this.d = i;
        this.t.setColor(this.d);
    }

    public void setThumbDrawable(Drawable drawable) {
        if (drawable == null) {
            return;
        }
        this.y = drawable;
    }

    public void setThumbEnable(boolean z) {
        this.C = z;
    }

    public void setThumbScale(float f) {
        this.K = f;
    }

    public void setThumbTouchOffset(int i) {
        this.l = i;
        invalidate();
    }
}
