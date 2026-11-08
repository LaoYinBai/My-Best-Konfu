package com.tachikoma.core.component.anim;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.text.TextUtils;
import com.tachikoma.core.component.TKBase;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CustomViewPropertyAnimator {
    private final TKBase<?> mBase;
    private final long mDuration;
    private final float mEnd;
    private final String mProperty;
    private final float mStart;

    public CustomViewPropertyAnimator(TKBase<?> tKBase, String str, long j, float f, float f2) {
        this.mBase = tKBase;
        this.mProperty = str;
        this.mDuration = j;
        this.mStart = f;
        this.mEnd = f2;
    }

    public void start() {
        if (TextUtils.isEmpty(this.mProperty) || this.mBase == null || this.mBase.getDomNode() == null) {
            return;
        }
        if (!AnimationProperty.HEIGHT.equals(this.mProperty) && !AnimationProperty.WIDTH.equals(this.mProperty)) {
            ObjectAnimator.ofFloat(this.mBase.getView(), this.mProperty, this.mStart, this.mEnd).setDuration(this.mDuration).start();
            return;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(this.mStart, this.mEnd);
        valueAnimatorOfFloat.setDuration(this.mDuration);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.tachikoma.core.component.anim.CustomViewPropertyAnimator.1
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                if (CustomViewPropertyAnimator.this.mBase == null || CustomViewPropertyAnimator.this.mBase.getDomNode() == null || CustomViewPropertyAnimator.this.mBase.getDomNode().getYogaNode() == null) {
                    return;
                }
                if (AnimationProperty.HEIGHT.equals(CustomViewPropertyAnimator.this.mProperty)) {
                    CustomViewPropertyAnimator.this.mBase.getDomNode().getYogaNode().setHeight(((Number) valueAnimator.getAnimatedValue()).intValue());
                } else {
                    CustomViewPropertyAnimator.this.mBase.getDomNode().getYogaNode().setWidth(((Number) valueAnimator.getAnimatedValue()).intValue());
                }
                if (CustomViewPropertyAnimator.this.mBase.getView().getParent() != null) {
                    CustomViewPropertyAnimator.this.mBase.getView().getParent().requestLayout();
                }
            }
        });
        valueAnimatorOfFloat.start();
    }
}
