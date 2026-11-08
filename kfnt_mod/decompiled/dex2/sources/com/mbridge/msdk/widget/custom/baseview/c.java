package com.mbridge.msdk.widget.custom.baseview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.GradientDrawable;
import android.widget.TextView;

/* compiled from: MorphingAnimation.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class c {
    private d a;
    private int b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private float i;
    private float j;
    private float k;
    private TextView l;
    private f m;

    public c(TextView textView, f fVar) {
        this.l = textView;
        this.m = fVar;
    }

    public final void a(int i) {
        this.b = i;
    }

    public final void a(d dVar) {
        this.a = dVar;
    }

    public final void b(int i) {
        this.c = i;
    }

    public final void c(int i) {
        this.d = i;
    }

    public final void d(int i) {
        this.e = i;
    }

    public final void e(int i) {
        this.f = i;
    }

    public final void f(int i) {
        this.g = i;
    }

    public final void g(int i) {
        this.h = i;
    }

    public final void a(float f) {
        this.i = f;
    }

    public final void b(float f) {
        this.j = f;
    }

    public final void c(float f) {
        this.k = f;
    }

    public final void a() {
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.c, this.d);
        final GradientDrawable gradientDrawableA = this.m.a();
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.mbridge.msdk.widget.custom.baseview.c.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue;
                int i;
                int animatedFraction;
                Integer num = (Integer) valueAnimator.getAnimatedValue();
                if (c.this.c > c.this.d) {
                    iIntValue = (c.this.c - num.intValue()) / 2;
                    i = c.this.c - iIntValue;
                    animatedFraction = (int) (c.this.k * valueAnimator.getAnimatedFraction());
                } else {
                    iIntValue = (c.this.d - num.intValue()) / 2;
                    i = c.this.d - iIntValue;
                    animatedFraction = (int) (c.this.k - (c.this.k * valueAnimator.getAnimatedFraction()));
                }
                gradientDrawableA.setBounds(iIntValue + animatedFraction, animatedFraction, i - animatedFraction, c.this.l.getHeight() - animatedFraction);
            }
        });
        ObjectAnimator objectAnimatorOfInt = ObjectAnimator.ofInt(gradientDrawableA, "color", this.e, this.f);
        objectAnimatorOfInt.setEvaluator(new ArgbEvaluator());
        ObjectAnimator objectAnimatorOfInt2 = ObjectAnimator.ofInt(this.m, "strokeColor", this.g, this.h);
        objectAnimatorOfInt2.setEvaluator(new ArgbEvaluator());
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(gradientDrawableA, "cornerRadius", this.i, this.j);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(this.b);
        animatorSet.playTogether(valueAnimatorOfInt, objectAnimatorOfInt, objectAnimatorOfInt2, objectAnimatorOfFloat);
        animatorSet.addListener(new Animator.AnimatorListener() { // from class: com.mbridge.msdk.widget.custom.baseview.c.2
            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationCancel(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationRepeat(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationStart(Animator animator) {
            }

            @Override // android.animation.Animator.AnimatorListener
            public final void onAnimationEnd(Animator animator) {
                if (c.this.a != null) {
                    c.this.a.a();
                }
            }
        });
        animatorSet.start();
    }
}
