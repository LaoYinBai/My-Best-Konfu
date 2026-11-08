package com.kwad.sdk.reward.widget.tailframe.appbar;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.kwad.sdk.R;
import com.kwad.sdk.core.config.b;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.page.widget.TextProgressBar;
import com.kwad.sdk.core.response.a.c;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.widget.AppScoreView;
import com.kwad.sdk.utils.ax;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TailFrameBarAppPortraitVertical extends LinearLayout {
    private ImageView a;
    private TextView b;
    private AppScoreView c;
    private TextView d;
    private TextView e;
    private TextProgressBar f;
    private ValueAnimator g;
    private LinearLayout h;
    private View i;
    private Button j;
    private Button k;

    @Nullable
    private Animator l;
    private View m;

    static class a implements Animator.AnimatorListener {
        a() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationRepeat(Animator animator) {
        }

        @Override // android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator animator) {
        }
    }

    public TailFrameBarAppPortraitVertical(Context context) {
        this(context, null);
    }

    public TailFrameBarAppPortraitVertical(Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TailFrameBarAppPortraitVertical(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        c();
    }

    private static Animator a(View view) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        objectAnimatorOfFloat.setDuration(200L);
        objectAnimatorOfFloat.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.0f, 0.58f, 1.0f));
        return objectAnimatorOfFloat;
    }

    private static Animator a(final View view, float f, final float f2) throws Resources.NotFoundException {
        final float f3 = f / f2;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, f2);
        valueAnimatorOfFloat.setDuration(200L);
        valueAnimatorOfFloat.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.0f, 0.58f, 1.0f));
        final float dimension = view.getResources().getDimension(R.dimen.ksad_reward_apk_info_card_actionbar_text_size);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                float f4 = f3 * fFloatValue;
                if (f2 != 0.0f) {
                    float f5 = (fFloatValue / f2) * dimension;
                    if (view instanceof TextView) {
                        ((TextView) view).setTextSize(0, f5);
                    }
                }
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.height = (int) fFloatValue;
                    layoutParams.width = (int) f4;
                    view.setLayoutParams(layoutParams);
                }
            }
        });
        return valueAnimatorOfFloat;
    }

    private static Animator a(final View view, View view2, int i, int i2) throws Resources.NotFoundException {
        Animator animatorA = a(view);
        float f = i;
        float f2 = i2;
        Animator animatorA2 = a(view2, f, f2);
        animatorA2.addListener(new a() { // from class: com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical.3
            @Override // com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical.a, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                view.setAlpha(1.0f);
                ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                if (layoutParams != null) {
                    layoutParams.width = 0;
                    layoutParams.height = 0;
                    view.setLayoutParams(layoutParams);
                }
            }
        });
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view2, "scaleX", 1.0f, 1.0f);
        objectAnimatorOfFloat.setDuration(1600L);
        Animator animatorA3 = a(view2);
        Animator animatorA4 = a(view, f, f2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorA, animatorA2, objectAnimatorOfFloat, animatorA3, animatorA4);
        return animatorSet;
    }

    private void a(LinearLayout linearLayout, String str) {
        TextView textView = (TextView) LayoutInflater.from(linearLayout.getContext()).inflate(R.layout.ksad_reward_apk_info_card_tag_item, (ViewGroup) linearLayout, false);
        textView.setText(str);
        this.h.addView(textView);
    }

    private void c() {
        this.m = inflate(getContext(), R.layout.ksad_video_tf_bar_app_portrait_vertical, this);
        this.a = (ImageView) findViewById(R.id.ksad_app_icon);
        this.c = (AppScoreView) findViewById(R.id.ksad_app_score);
        this.d = (TextView) findViewById(R.id.ksad_app_download_count);
        this.b = (TextView) findViewById(R.id.ksad_app_name);
        this.e = (TextView) findViewById(R.id.ksad_app_introduce);
        this.f = (TextProgressBar) findViewById(R.id.ksad_download_bar);
        this.f.setTextDimen(com.kwad.sdk.a.kwai.a.a(getContext(), 16.0f));
        this.f.setTextColor(-1);
        this.h = (LinearLayout) findViewById(R.id.ksad_reward_apk_info_tags);
        this.j = (Button) findViewById(R.id.ksad_reward_apk_info_install_action);
        this.k = (Button) findViewById(R.id.ksad_reward_apk_info_install_start);
        this.i = findViewById(R.id.ksad_reward_apk_info_install_container);
    }

    private void d() {
        if (this.g == null || !this.g.isRunning()) {
            this.g = ValueAnimator.ofFloat(1.0f, 1.2f, 1.0f);
            this.g.setDuration(1200L);
            this.g.setRepeatCount(-1);
            this.g.setRepeatMode(1);
            this.g.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical.5
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                    TailFrameBarAppPortraitVertical.this.f.setScaleY(fFloatValue);
                    TailFrameBarAppPortraitVertical.this.f.setScaleX(fFloatValue);
                }
            });
            this.g.start();
        }
    }

    public void a() {
        int height = this.i.getHeight();
        int width = this.i.getWidth();
        if (height == 0 || width == 0) {
            return;
        }
        if (this.l == null) {
            this.l = a(this.k, this.j, width, height);
            this.l.addListener(new a() { // from class: com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical.2
                @Override // com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical.a, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(final Animator animator) {
                    ax.a(new Runnable() { // from class: com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            animator.start();
                        }
                    }, null, 1600L);
                }
            });
        }
        if (this.l.isStarted()) {
            return;
        }
        this.l.start();
    }

    public void a(@NonNull AdInfo adInfo) {
        switch (adInfo.status) {
            case 1:
            case 2:
            case 3:
                b();
                break;
            default:
                d();
                break;
        }
    }

    public void a(@NonNull AdTemplate adTemplate) {
        ImageView imageView;
        String strAu;
        TextView textView;
        String strAt;
        if (d.v(adTemplate)) {
            this.m.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.ksad_translucent));
            this.b.setTextColor(Color.parseColor("#FFFFFF"));
            this.e.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            this.m.setBackgroundColor(Color.parseColor("#E6FFFFFF"));
        }
        AdInfo adInfoJ = d.j(adTemplate);
        if (d.v(adTemplate)) {
            imageView = this.a;
            strAu = com.kwad.sdk.core.response.a.a.aE(adInfoJ);
        } else {
            imageView = this.a;
            strAu = com.kwad.sdk.core.response.a.a.au(adInfoJ);
        }
        KSImageLoader.loadAppIcon(imageView, strAu, adTemplate, 12);
        if (d.v(adTemplate)) {
            textView = this.b;
            strAt = com.kwad.sdk.core.response.a.a.u(adInfoJ);
        } else {
            textView = this.b;
            strAt = com.kwad.sdk.core.response.a.a.at(adInfoJ);
        }
        textView.setText(strAt);
        int i = 0;
        if (!d.v(adTemplate)) {
            float fX = com.kwad.sdk.core.response.a.a.x(adInfoJ);
            if (fX >= 3.0f) {
                this.c.setScore(fX);
                this.c.setVisibility(0);
            } else {
                this.c.setVisibility(8);
            }
            String strW = com.kwad.sdk.core.response.a.a.w(adInfoJ);
            if (TextUtils.isEmpty(strW)) {
                this.d.setVisibility(8);
            } else {
                this.d.setText(strW);
                this.d.setVisibility(0);
            }
        }
        this.e.setText(com.kwad.sdk.core.response.a.a.s(adInfoJ));
        if (d.v(adTemplate)) {
            this.f.setVisibility(8);
            this.i.setVisibility(0);
            this.k.setText("查看详情");
            this.j.setText(String.format("浏览详情页%s秒，领取奖励", b.aA() + ""));
            this.i.postDelayed(new Runnable() { // from class: com.kwad.sdk.reward.widget.tailframe.appbar.TailFrameBarAppPortraitVertical.1
                @Override // java.lang.Runnable
                public void run() {
                    TailFrameBarAppPortraitVertical.this.a();
                }
            }, 1600L);
        } else {
            this.f.setVisibility(0);
            this.i.setVisibility(8);
            a(d.j(adTemplate));
        }
        if (d.v(adTemplate)) {
            List<String> listI = c.i(adTemplate);
            if (listI == null || listI.size() <= 0) {
                this.h.setVisibility(8);
            } else {
                this.h.setVisibility(0);
            }
            for (String str : listI) {
                i++;
                if (i > 3) {
                    return;
                } else {
                    a(this.h, str);
                }
            }
        }
    }

    public void b() {
        if (this.g != null && this.g.isRunning()) {
            this.g.cancel();
            this.g.end();
        }
        if (this.l == null || !this.l.isRunning()) {
            return;
        }
        this.l.cancel();
        this.l.end();
    }

    public TextProgressBar getTextProgressBar() {
        return this.f;
    }
}
