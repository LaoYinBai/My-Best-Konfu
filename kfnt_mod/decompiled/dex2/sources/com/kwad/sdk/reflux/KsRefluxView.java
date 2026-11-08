package com.kwad.sdk.reflux;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.kwad.sdk.R;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;
import com.kwad.sdk.reflux.KsRefluxContentView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsRefluxView extends FrameLayout implements View.OnClickListener, KsRefluxContentView.a {
    private KsAdWebView a;
    private KsRefluxNativeView b;
    private View c;
    private ImageView d;
    private View e;
    private a f;
    private volatile boolean g;

    public interface a {
        void d();

        void e();
    }

    public KsRefluxView(@NonNull Context context) {
        super(context);
        this.g = false;
        a(context, null, 0);
    }

    public KsRefluxView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.g = false;
        a(context, attributeSet, 0);
    }

    public KsRefluxView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.g = false;
        a(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public KsRefluxView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.g = false;
        a(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator a(final View view) {
        int measuredHeight = view.getMeasuredHeight();
        if (measuredHeight == 0) {
            return null;
        }
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(measuredHeight, getContext().getResources().getDimensionPixelOffset(R.dimen.ksad_reflux_back_height));
        valueAnimatorOfInt.setDuration(300L);
        valueAnimatorOfInt.setInterpolator(PathInterpolatorCompat.create(0.25f, 0.1f, 0.25f, 1.0f));
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reflux.KsRefluxView.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.height = iIntValue;
                    view.setLayoutParams(layoutParams);
                }
            }
        });
        return valueAnimatorOfInt;
    }

    @Nullable
    private Animator a(final View view, final View view2) {
        if (view == null || view2 == null || view.getMeasuredHeight() <= 0) {
            return null;
        }
        Drawable background = view2.getBackground();
        if (!(background instanceof GradientDrawable)) {
            return null;
        }
        final GradientDrawable gradientDrawable = (GradientDrawable) background;
        ValueAnimator valueAnimatorOfInt = ValueAnimator.ofInt(view.getMeasuredHeight(), 0);
        valueAnimatorOfInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reflux.KsRefluxView.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int iIntValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.height = iIntValue;
                    view.setLayoutParams(layoutParams);
                }
            }
        });
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(getResources().getDimension(R.dimen.ksad_reflux_title_bar_corner), 0.0f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reflux.KsRefluxView.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                com.kwad.sdk.core.d.a.a("KsRefluxView", "actionBar radii value: " + fFloatValue);
                gradientDrawable.setCornerRadii(new float[]{fFloatValue, fFloatValue, fFloatValue, fFloatValue, 0.0f, 0.0f, 0.0f, 0.0f});
                view2.setBackground(gradientDrawable);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300L);
        animatorSet.setInterpolator(PathInterpolatorCompat.create(0.25f, 0.1f, 0.25f, 1.0f));
        animatorSet.playTogether(valueAnimatorOfInt, valueAnimatorOfFloat);
        return animatorSet;
    }

    private void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        inflate(context, R.layout.ksad_reflux, this);
        this.c = findViewById(R.id.ksad_reflux_title_bar);
        this.d = (ImageView) findViewById(R.id.ksad_reflux_back);
        this.a = (KsAdWebView) findViewById(R.id.ksad_reflux_webview);
        this.b = (KsRefluxNativeView) findViewById(R.id.ksad_reflux_native);
        this.e = findViewById(R.id.ksad_reflux_close);
        this.e.setOnClickListener(this);
        this.d.setOnClickListener(this);
        ((KsRefluxContentView) findViewById(R.id.ksad_reflux_content)).setContentViewListener(this);
    }

    private boolean e() {
        return this.d != null && this.d.getMeasuredHeight() == 0;
    }

    public void a(boolean z) {
        this.a.setVisibility(z ? 0 : 4);
        this.b.setVisibility(z ? 8 : 0);
    }

    @Override // com.kwad.sdk.reflux.KsRefluxContentView.a
    public boolean a() {
        return e();
    }

    @Override // com.kwad.sdk.reflux.KsRefluxContentView.a
    public boolean b() {
        if (e()) {
            return false;
        }
        d();
        return true;
    }

    public void c() {
        setVisibility(0);
        this.d.post(new Runnable() { // from class: com.kwad.sdk.reflux.KsRefluxView.1
            @Override // java.lang.Runnable
            public void run() {
                Animator animatorA = KsRefluxView.this.a(KsRefluxView.this.d);
                if (animatorA != null) {
                    animatorA.start();
                }
                if (KsRefluxView.this.f != null) {
                    KsRefluxView.this.f.e();
                }
            }
        });
    }

    @MainThread
    public void d() {
        if (e() || this.g) {
            return;
        }
        Animator animatorA = a(this.d, this.c);
        animatorA.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reflux.KsRefluxView.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                KsRefluxView.this.g = false;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animator) {
                super.onAnimationStart(animator);
                KsRefluxView.this.g = true;
            }
        });
        animatorA.start();
    }

    public KsRefluxNativeView getNativeView() {
        return this.b;
    }

    public KsAdWebView getWebView() {
        return this.a;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (this.f == null) {
            return;
        }
        if (view.equals(this.e)) {
            this.f.d();
        } else if (view.equals(this.d)) {
            d();
        }
    }

    public void setViewListener(a aVar) {
        this.f = aVar;
    }
}
