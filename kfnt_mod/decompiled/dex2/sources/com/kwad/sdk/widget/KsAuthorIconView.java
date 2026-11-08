package com.kwad.sdk.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.w;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class KsAuthorIconView extends FrameLayout {
    private ViewGroup a;
    private KSCornerImageView b;
    private KSCornerImageView c;
    private KSCornerImageView d;
    private KSCornerImageView e;
    private TextView f;
    private View g;
    private ImageView h;

    public KsAuthorIconView(@NonNull Context context) {
        super(context);
        a(context, (AttributeSet) null, 0);
    }

    public KsAuthorIconView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context, attributeSet, 0);
    }

    public KsAuthorIconView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context, attributeSet, i);
    }

    @RequiresApi(api = 21)
    public KsAuthorIconView(@NonNull Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        a(context, attributeSet, i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator a(View view) {
        float width = view.getWidth();
        if (width <= 0.0f) {
            return null;
        }
        float f = 1.2f * width;
        com.kwad.sdk.core.d.a.a("KsAuthorIconView", "getIconScaleAnimator size: " + width + ", endSize: " + f);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(width, f);
        valueAnimatorOfFloat.addUpdateListener(b(view));
        Interpolator interpolatorCreate = PathInterpolatorCompat.create(0.42f, 0.0f, 1.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(500L);
        valueAnimatorOfFloat.setInterpolator(interpolatorCreate);
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(f, width);
        valueAnimatorOfFloat2.addUpdateListener(b(view));
        Interpolator interpolatorCreate2 = PathInterpolatorCompat.create(0.0f, 0.0f, 0.58f, 1.0f);
        valueAnimatorOfFloat2.setDuration(500L);
        valueAnimatorOfFloat2.setInterpolator(interpolatorCreate2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(valueAnimatorOfFloat, valueAnimatorOfFloat2);
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator a(View view, long j) {
        if (view == null || view.getWidth() == 0 || view.getHeight() == 0) {
            return null;
        }
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(view.getWidth(), getResources().getDimension(R.dimen.ksad_reward_author_width));
        valueAnimatorOfFloat.addUpdateListener(b(view));
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        Interpolator interpolatorCreate = PathInterpolatorCompat.create(0.42f, 0.0f, 1.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(j);
        animatorSet.setInterpolator(interpolatorCreate);
        animatorSet.playTogether(valueAnimatorOfFloat, objectAnimatorOfFloat);
        return animatorSet;
    }

    private void a(@NonNull Context context, @Nullable AttributeSet attributeSet, int i) {
        inflate(context, R.layout.ksad_author_icon, this);
        this.a = (ViewGroup) findViewById(R.id.ksad_author_icon_frame);
        this.c = (KSCornerImageView) findViewById(R.id.ksad_author_icon);
        this.b = (KSCornerImageView) findViewById(R.id.ksad_author_icon_outer);
        this.d = (KSCornerImageView) findViewById(R.id.ksad_author_animator);
        this.e = (KSCornerImageView) findViewById(R.id.ksad_author_animator2);
        this.f = (TextView) findViewById(R.id.ksad_fans_count);
        this.g = findViewById(R.id.ksad_author_btn_follow);
        this.h = (ImageView) findViewById(R.id.ksad_author_arrow_down);
    }

    private void a(c cVar, Drawable drawable) {
        if (drawable instanceof GradientDrawable) {
            ((GradientDrawable) drawable).setStroke(getResources().getDimensionPixelSize(R.dimen.ksad_reward_author_icon_stroke_width), cVar.a());
        }
    }

    private static ValueAnimator.AnimatorUpdateListener b(final View view) {
        return new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.widget.KsAuthorIconView.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams != null) {
                    int i = (int) fFloatValue;
                    layoutParams.width = i;
                    layoutParams.height = i;
                    view.setLayoutParams(layoutParams);
                }
            }
        };
    }

    public void a(AdTemplate adTemplate) {
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
        String strAE = com.kwad.sdk.core.response.a.a.aE(adInfoJ);
        if (!TextUtils.isEmpty(strAE)) {
            KSImageLoader.loadImage(this.c, strAE, adTemplate);
        }
        this.f.setText(com.kwad.sdk.core.response.a.a.aB(adInfoJ));
        this.d.post(new Runnable() { // from class: com.kwad.sdk.widget.KsAuthorIconView.1
            @Override // java.lang.Runnable
            public void run() {
                final Animator animatorA = KsAuthorIconView.this.a(KsAuthorIconView.this.d, 900L);
                final Animator animatorA2 = KsAuthorIconView.this.a(KsAuthorIconView.this.e, 1000L);
                final Animator animatorA3 = KsAuthorIconView.this.a(KsAuthorIconView.this.c);
                if (animatorA == null || animatorA2 == null || animatorA3 == null) {
                    return;
                }
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animatorA3, animatorA, animatorA2);
                animatorSet.start();
                animatorA3.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.widget.KsAuthorIconView.1.1
                    @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                    public void onAnimationEnd(Animator animator) {
                        super.onAnimationEnd(animator);
                        animatorA.start();
                        animatorA2.start();
                        animatorA3.start();
                    }
                });
            }
        });
    }

    public void a(boolean z, c cVar) {
        ImageView imageView;
        int i;
        w.a(cVar, this);
        if (z) {
            imageView = this.h;
            i = R.drawable.ksad_reward_follow_arrow_down;
        } else {
            imageView = this.h;
            i = R.drawable.ksad_reward_follow_arrow_down_orange;
        }
        imageView.setImageResource(i);
        a(cVar, this.d.getBackground());
        a(cVar, this.b.getBackground());
        a(cVar, this.e.getBackground());
        a(cVar, this.a.getBackground());
    }
}
