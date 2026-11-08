package com.kwad.sdk.core.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.kwad.sdk.R;
import com.kwad.sdk.a.kwai.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsShakeView extends FrameLayout {
    private ImageView a;
    private int b;
    private Paint c;
    private float d;

    @ColorInt
    private int e;

    @ColorInt
    private int f;

    @ColorInt
    private int g;
    private float h;
    private float i;

    @DrawableRes
    private int j;
    private Animator k;
    private boolean l;

    public KsShakeView(@NonNull Context context) {
        this(context, null, 0);
    }

    public KsShakeView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public KsShakeView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = new Paint();
        this.l = false;
        a(context, attributeSet, i);
    }

    private Animator a(View view, long j, float f) {
        Interpolator interpolatorCreate;
        float height;
        if (view == null) {
            return null;
        }
        AnimatorSet animatorSet = new AnimatorSet();
        if (this.b == 1) {
            interpolatorCreate = PathInterpolatorCompat.create(0.22f, 0.59f, 0.36f, 1.0f);
            view.setPivotX(view.getWidth());
            height = view.getHeight();
        } else {
            interpolatorCreate = PathInterpolatorCompat.create(0.33f, 0.0f, 0.36f, 1.0f);
            view.setPivotX(view.getWidth() / 2.0f);
            height = view.getHeight() / 2.0f;
        }
        view.setPivotY(height);
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(300L);
        ObjectAnimator duration = ObjectAnimator.ofFloat(view, "rotation", 0.0f, f).setDuration(j);
        float f2 = -f;
        long j2 = j * 2;
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(view, "rotation", f, f2).setDuration(j2);
        duration2.setInterpolator(interpolatorCreate);
        ObjectAnimator duration3 = ObjectAnimator.ofFloat(view, "rotation", f2, f).setDuration(j2);
        ObjectAnimator duration4 = ObjectAnimator.ofFloat(view, "rotation", f, f2).setDuration(j2);
        duration4.setInterpolator(interpolatorCreate);
        animatorSet.playSequentially(objectAnimatorOfFloat, duration, duration2, duration3, duration4, ObjectAnimator.ofFloat(view, "rotation", f2, 0.0f).setDuration(j));
        return animatorSet;
    }

    @SuppressLint({"CustomViewStyleable"})
    private void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ksad_KsShakeView, i, 0);
        this.d = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KsShakeView_ksad_outerStrokeWidth, 1.0f);
        this.e = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_KsShakeView_ksad_outerStrokeColor, Color.parseColor("#4DFFFFFF"));
        this.f = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_KsShakeView_ksad_solidColor, Color.parseColor("#66000000"));
        this.b = typedArrayObtainStyledAttributes.getInteger(R.styleable.ksad_KsShakeView_ksad_shakeViewStyle, 1);
        this.g = typedArrayObtainStyledAttributes.getColor(R.styleable.ksad_KsShakeView_ksad_innerCircleStrokeColor, Color.parseColor("#B3FFFFFF"));
        this.h = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KsShakeView_ksad_innerCircleStrokeWidth, 1.0f);
        this.j = typedArrayObtainStyledAttributes.getResourceId(R.styleable.ksad_KsShakeView_ksad_shakeIcon, R.drawable.ksad_ic_shake_hand);
        this.i = typedArrayObtainStyledAttributes.getDimension(R.styleable.ksad_KsShakeView_ksad_innerCirclePadding, a.a(context, 10.0f));
        typedArrayObtainStyledAttributes.recycle();
        this.a = new ImageView(getContext());
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 17;
        addView(this.a, layoutParams);
        b();
    }

    private Animator b(View view, long j, float f) {
        if (view == null) {
            return null;
        }
        Interpolator interpolatorCreate = PathInterpolatorCompat.create(0.33f, 0.0f, 0.36f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator duration = ObjectAnimator.ofFloat(view, "translationY", f).setDuration(j);
        duration.setInterpolator(interpolatorCreate);
        float f2 = -f;
        long j2 = 2 * j;
        ObjectAnimator duration2 = ObjectAnimator.ofFloat(view, "translationY", f2).setDuration(j2);
        duration2.setInterpolator(interpolatorCreate);
        ObjectAnimator duration3 = ObjectAnimator.ofFloat(view, "translationY", f).setDuration(j2);
        duration3.setInterpolator(interpolatorCreate);
        ObjectAnimator duration4 = ObjectAnimator.ofFloat(view, "translationY", f2).setDuration(j2);
        duration4.setInterpolator(interpolatorCreate);
        animatorSet.playSequentially(duration, duration2, duration3, duration4, ObjectAnimator.ofFloat(view, "translationY", f).setDuration(j), ObjectAnimator.ofFloat(view, "alpha", 1.0f, 1.0f).setDuration(j));
        return animatorSet;
    }

    private void b() {
        this.a.setImageResource(this.j);
    }

    private void setBgCirclePaint(Paint paint) {
        paint.reset();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(this.f);
        paint.setAntiAlias(true);
    }

    private void setInnerCirclePaint(Paint paint) {
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.h);
        paint.setColor(this.g);
        paint.setAntiAlias(true);
    }

    private void setOuterCirclePaint(Paint paint) {
        paint.reset();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(this.d);
        paint.setColor(this.e);
        paint.setAntiAlias(true);
    }

    @MainThread
    public void a() {
        if (this.k != null) {
            this.k.cancel();
            this.k = null;
        }
        this.k = a(this.a, 100L, 16.0f);
        if (this.k != null) {
            this.k.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.core.widget.KsShakeView.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationCancel(Animator animator) {
                    super.onAnimationCancel(animator);
                    KsShakeView.this.a.setRotation(0.0f);
                }

                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    if (KsShakeView.this.l) {
                        return;
                    }
                    KsShakeView.this.k.start();
                }
            });
            this.k.start();
        }
    }

    public void a(int i) {
        this.b = i;
        setIconDrawableRes(i != 2 ? R.drawable.ksad_ic_shake_hand : R.drawable.ksad_ic_shake_phone);
    }

    public void a(AnimatorListenerAdapter animatorListenerAdapter) {
        this.l = true;
        if (this.k != null) {
            this.k.cancel();
        }
        this.a.setRotation(0.0f);
        Animator animatorB = b(this, 100L, getResources().getDimensionPixelSize(R.dimen.ksad_splash_shake_animator_height));
        if (animatorListenerAdapter != null) {
            animatorB.addListener(animatorListenerAdapter);
        }
        animatorB.start();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void dispatchDraw(Canvas canvas) {
        float fMin = Math.min(getWidth(), getHeight()) / 2.0f;
        setBgCirclePaint(this.c);
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, fMin, this.c);
        setOuterCirclePaint(this.c);
        canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, fMin, this.c);
        if (this.b == 2) {
            setInnerCirclePaint(this.c);
            canvas.drawCircle(getWidth() / 2.0f, getHeight() / 2.0f, fMin - this.i, this.c);
        }
        super.dispatchDraw(canvas);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
    }

    @MainThread
    public void setIconDrawableRes(@DrawableRes int i) {
        if (this.a != null) {
            this.a.setImageResource(i);
        }
    }
}
