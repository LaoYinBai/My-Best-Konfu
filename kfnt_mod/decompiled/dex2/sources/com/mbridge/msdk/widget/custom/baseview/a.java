package com.mbridge.msdk.widget.custom.baseview;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.Property;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/* compiled from: CircularAnimatedDrawable.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class a extends Drawable implements Animatable {
    private static final Interpolator a = new LinearInterpolator();
    private static final Interpolator b = new DecelerateInterpolator();
    private ObjectAnimator d;
    private ObjectAnimator e;
    private boolean f;
    private float h;
    private float i;
    private float j;
    private float k;
    private boolean l;
    private final RectF c = new RectF();
    private Property<a, Float> m = new Property<a, Float>(Float.class, "angle") { // from class: com.mbridge.msdk.widget.custom.baseview.a.1
        @Override // android.util.Property
        public final /* synthetic */ Float get(a aVar) {
            return Float.valueOf(aVar.a());
        }

        @Override // android.util.Property
        public final /* synthetic */ void set(a aVar, Float f) {
            aVar.a(f.floatValue());
        }
    };
    private Property<a, Float> n = new Property<a, Float>(Float.class, "arc") { // from class: com.mbridge.msdk.widget.custom.baseview.a.2
        @Override // android.util.Property
        public final /* synthetic */ Float get(a aVar) {
            return Float.valueOf(aVar.b());
        }

        @Override // android.util.Property
        public final /* synthetic */ void set(a aVar, Float f) {
            aVar.b(f.floatValue());
        }
    };
    private Paint g = new Paint();

    @Override // android.graphics.drawable.Drawable
    public final int getOpacity() {
        return -2;
    }

    public a(int i, float f) {
        this.k = f;
        this.g.setAntiAlias(true);
        this.g.setStyle(Paint.Style.STROKE);
        this.g.setStrokeWidth(f);
        this.g.setColor(i);
        this.e = ObjectAnimator.ofFloat(this, this.m, 360.0f);
        this.e.setInterpolator(a);
        this.e.setDuration(2000L);
        this.e.setRepeatMode(1);
        this.e.setRepeatCount(-1);
        this.d = ObjectAnimator.ofFloat(this, this.n, 300.0f);
        this.d.setInterpolator(b);
        this.d.setDuration(600L);
        this.d.setRepeatMode(1);
        this.d.setRepeatCount(-1);
        this.d.addListener(new Animator.AnimatorListener() { // from class: com.mbridge.msdk.widget.custom.baseview.a.3
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
                a.a(a.this);
            }
        });
    }

    @Override // android.graphics.drawable.Drawable
    public final void draw(Canvas canvas) {
        float f;
        float f2 = this.i - this.h;
        float f3 = this.j;
        if (this.f) {
            f = f3 + 30.0f;
        } else {
            f2 += f3;
            f = (360.0f - f3) - 30.0f;
        }
        canvas.drawArc(this.c, f2, f, false, this.g);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setAlpha(int i) {
        this.g.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public final void setColorFilter(ColorFilter colorFilter) {
        this.g.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    protected final void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.c.left = rect.left + (this.k / 2.0f) + 0.5f;
        this.c.right = (rect.right - (this.k / 2.0f)) - 0.5f;
        this.c.top = rect.top + (this.k / 2.0f) + 0.5f;
        this.c.bottom = (rect.bottom - (this.k / 2.0f)) - 0.5f;
    }

    @Override // android.graphics.drawable.Animatable
    public final void start() {
        if (isRunning()) {
            return;
        }
        this.l = true;
        this.e.start();
        this.d.start();
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public final void stop() {
        if (isRunning()) {
            this.l = false;
            this.e.cancel();
            this.d.cancel();
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public final boolean isRunning() {
        return this.l;
    }

    public final void a(float f) {
        this.i = f;
        invalidateSelf();
    }

    public final float a() {
        return this.i;
    }

    public final void b(float f) {
        this.j = f;
        invalidateSelf();
    }

    public final float b() {
        return this.j;
    }

    static /* synthetic */ void a(a aVar) {
        aVar.f = !aVar.f;
        if (aVar.f) {
            aVar.h = (aVar.h + 60.0f) % 360.0f;
        }
    }
}
