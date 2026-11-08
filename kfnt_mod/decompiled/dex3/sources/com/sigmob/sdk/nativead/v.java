package com.sigmob.sdk.nativead;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class v {
    private ViewGroup a;
    private ViewAttr b;
    private ViewAttr c;
    private long d;

    public v(ViewGroup viewGroup, ViewAttr viewAttr, ViewAttr viewAttr2, long j) {
        this.a = viewGroup;
        this.b = viewAttr;
        this.c = viewAttr2;
        this.d = j;
    }

    public void a() {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(this.a, "x", this.b.a(), this.c.a());
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(this.a, "y", this.b.b(), this.c.b());
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(this.b.c(), this.c.c());
        ValueAnimator valueAnimatorOfInt2 = ValueAnimator.ofInt(this.b.d(), this.c.d());
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sigmob.sdk.nativead.v.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams layoutParams = v.this.a.getLayoutParams();
                layoutParams.width = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                v.this.a.setLayoutParams(layoutParams);
            }
        });
        valueAnimatorOfInt2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sigmob.sdk.nativead.v.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams layoutParams = v.this.a.getLayoutParams();
                layoutParams.height = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                v.this.a.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2, valueAnimatorOfInt, valueAnimatorOfInt2);
        animatorSet.setDuration(this.d);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }
}
