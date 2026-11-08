package com.sigmob.sdk.base.views;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sigmob.logger.SigmobLog;
import com.sigmob.sdk.base.models.BaseAdUnit;
import com.sigmob.volley.s;
import com.sigmob.volley.toolbox.k;
import com.ss.union.game.sdk.common.ui.roundedimageview.RoundedDrawable;
import com.tachikoma.core.component.anim.AnimationProperty;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CompanionAdsWidget extends RelativeLayout {
    int a;
    private final ImageView b;
    private final TextView c;
    private final TextView d;
    private final int e;
    private TextView f;
    private TextView g;
    private FiveStarView h;
    private k.c i;
    private CompanionAdsWidget j;
    private int k;
    private boolean l;
    private int m;
    private int n;
    private boolean o;
    private boolean p;
    private boolean q;
    private LinearLayout r;
    private BaseAdUnit s;

    public CompanionAdsWidget(Context context, BaseAdUnit baseAdUnit, String str, String str2, float f, String str3, int i, int i2, String str4, int i3, int i4, int i5, int i6, int i7, float f2) {
        super(context);
        this.j = null;
        this.k = 0;
        this.a = com.sigmob.sdk.common.utils.c.c(f2, context);
        this.n = com.sigmob.sdk.common.utils.c.c(10.0f, context);
        this.s = baseAdUnit;
        this.k = i;
        this.e = i2;
        this.d = new TextView(context);
        this.d.setText(str);
        this.d.setTextSize(1, 16.0f);
        this.d.setTextColor(RoundedDrawable.DEFAULT_BORDER_COLOR);
        this.d.setSingleLine();
        this.d.setTypeface(Typeface.defaultFromStyle(0));
        this.d.setEllipsize(TextUtils.TruncateAt.END);
        this.d.setGravity(16);
        this.d.setId(com.sigmob.sdk.common.a.r());
        this.d.setPadding(this.n / 6, 0, 0, 0);
        this.m = i5;
        this.c = new TextView(context);
        this.c.setTextSize(1, 14.0f);
        this.c.setTextColor(i4);
        this.c.setGravity(17);
        this.d.setSingleLine();
        this.c.setEllipsize(TextUtils.TruncateAt.END);
        this.c.setText(str2);
        int iC = com.sigmob.sdk.common.utils.c.c(5.0f, context);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(i3);
        gradientDrawable.setCornerRadius(iC * 6);
        this.b = new CircleImageView(context);
        this.b.setScaleType(ImageView.ScaleType.FIT_START);
        this.b.setId(com.sigmob.sdk.common.a.r());
        ((CircleImageView) this.b).b = false;
        float f3 = iC * 2;
        ((CircleImageView) this.b).a = f3;
        if (Build.VERSION.SDK_INT >= 16) {
            this.c.setBackground(gradientDrawable);
        } else {
            this.c.setBackgroundDrawable(gradientDrawable);
        }
        this.c.setId(com.sigmob.sdk.common.a.r());
        GradientDrawable gradientDrawable2 = new GradientDrawable();
        gradientDrawable2.setCornerRadius(f3);
        gradientDrawable2.setColor(i6);
        gradientDrawable2.setAlpha(i7);
        if (Build.VERSION.SDK_INT >= 16) {
            setBackground(gradientDrawable2);
        } else {
            setBackgroundDrawable(gradientDrawable2);
        }
        this.j = this;
        if (this.e == 1) {
            a(context, f);
        } else {
            a(context, str3);
        }
        if (baseAdUnit.getMaterial() != null && baseAdUnit.getMaterial().ad_privacy != null) {
            a(context);
        }
        setupUILayout(context);
        this.b.setVisibility(4);
        a(str4);
        f();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c() {
        switch (this.k) {
            case 1:
                d();
                break;
            case 2:
            default:
                g();
                break;
            case 3:
                e();
                break;
        }
    }

    @SuppressLint({"ObjectAnimatorBinding"})
    private void d() {
        Keyframe keyframeOfFloat = Keyframe.ofFloat(0.0f, 0.0f);
        Keyframe keyframeOfFloat2 = Keyframe.ofFloat(0.5f, 0.95f);
        Keyframe keyframeOfFloat3 = Keyframe.ofFloat(0.6f, 0.9f);
        Keyframe keyframeOfFloat4 = Keyframe.ofFloat(0.7f, 1.0f);
        Keyframe keyframeOfFloat5 = Keyframe.ofFloat(0.8f, 0.95f);
        Keyframe keyframeOfFloat6 = Keyframe.ofFloat(1.0f, 1.0f);
        ObjectAnimator objectAnimatorOfPropertyValuesHolder = ObjectAnimator.ofPropertyValuesHolder(this, PropertyValuesHolder.ofKeyframe(AnimationProperty.SCALE_X, keyframeOfFloat, keyframeOfFloat2, keyframeOfFloat3, keyframeOfFloat4, keyframeOfFloat5, keyframeOfFloat6), PropertyValuesHolder.ofKeyframe(AnimationProperty.SCALE_Y, keyframeOfFloat, keyframeOfFloat2, keyframeOfFloat3, keyframeOfFloat4, keyframeOfFloat5, keyframeOfFloat6));
        objectAnimatorOfPropertyValuesHolder.setDuration(1000L);
        e();
        this.q = true;
        objectAnimatorOfPropertyValuesHolder.addListener(new AnimatorListenerAdapter() { // from class: com.sigmob.sdk.base.views.CompanionAdsWidget.1
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator animator) {
                super.onAnimationCancel(animator);
                CompanionAdsWidget.this.p = false;
                CompanionAdsWidget.this.q = false;
                CompanionAdsWidget.this.j.clearAnimation();
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                CompanionAdsWidget.this.p = false;
                CompanionAdsWidget.this.q = false;
                CompanionAdsWidget.this.j.clearAnimation();
            }
        });
        objectAnimatorOfPropertyValuesHolder.start();
    }

    private void e() {
        this.o = true;
        super.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        super.setVisibility(4);
    }

    private void g() {
        SigmobLog.d("startDownToUpAnimator");
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 1.0f, 2, 0.0f);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setDuration(500L);
        e();
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sigmob.sdk.base.views.CompanionAdsWidget.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CompanionAdsWidget.this.p = false;
                CompanionAdsWidget.this.j.clearAnimation();
                SigmobLog.d("startDownToUpAnimator end");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.j.startAnimation(translateAnimation);
    }

    private void h() {
        SigmobLog.d("startUpToDownAnimator");
        TranslateAnimation translateAnimation = new TranslateAnimation(2, 0.0f, 2, 0.0f, 2, 0.0f, 2, 1.0f);
        translateAnimation.setInterpolator(new LinearInterpolator());
        translateAnimation.setDuration(500L);
        translateAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sigmob.sdk.base.views.CompanionAdsWidget.3
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                CompanionAdsWidget.this.p = true;
                CompanionAdsWidget.this.j.clearAnimation();
                CompanionAdsWidget.this.f();
                SigmobLog.d("startUpToDownAnimator end");
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.j.startAnimation(translateAnimation);
    }

    private void setupUILayout(Context context) {
        int iC = com.sigmob.sdk.common.utils.c.c(60.0f, context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(iC, iC);
        layoutParams.addRule(15);
        layoutParams.setMargins(this.n, this.n / 4, 0, 0);
        addView(this.b, layoutParams);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, (int) (iC / 1.8f));
        layoutParams2.setMargins(0, 0, this.n, 0);
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        this.c.setPadding(this.n, 0, this.n, 0);
        addView(this.c, layoutParams2);
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams3.setMargins(this.n / 2, 0, 0, 0);
        layoutParams3.addRule(15);
        layoutParams3.addRule(1, this.b.getId());
        layoutParams3.addRule(0, this.c.getId());
        addView(linearLayout, layoutParams3);
        linearLayout.addView(this.d, new RelativeLayout.LayoutParams(-2, -2));
        if (this.f != null) {
            RelativeLayout relativeLayout = new RelativeLayout(context);
            relativeLayout.addView(this.f, new RelativeLayout.LayoutParams(-2, -2));
            RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams4.addRule(1, this.f.getId());
            layoutParams4.addRule(6, this.f.getId());
            layoutParams4.addRule(8, this.f.getId());
            relativeLayout.addView(this.h, layoutParams4);
            linearLayout.addView(relativeLayout, new RelativeLayout.LayoutParams(-2, -2));
        } else if (this.g != null) {
            linearLayout.addView(this.g, new RelativeLayout.LayoutParams(-2, -2));
        }
        if (this.r != null) {
            linearLayout.addView(this.r, new RelativeLayout.LayoutParams(-2, -2));
        }
    }

    public void a(Context context) {
        this.r = new LinearLayout(context);
        this.r.setOrientation(0);
        this.r.setId(com.sigmob.sdk.common.a.r());
        int iC = com.sigmob.sdk.common.utils.c.c(5.0f, context);
        for (String str : new String[]{"权限", "开发者", "版本", "隐私协议"}) {
            TextView textView = new TextView(context);
            textView.setTextSize(1, 10.0f);
            textView.setText(str);
            textView.setTextColor(-7829368);
            textView.setAlpha(0.7f);
            int i = iC / 2;
            textView.setPadding(i, 0, i, 0);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
            layoutParams.setMargins(0, 0, iC, 0);
            this.r.addView(textView, layoutParams);
        }
    }

    public void a(Context context, float f) {
        this.f = new TextView(context);
        this.f.setTextSize(1, 14.0f);
        this.f.setId(com.sigmob.sdk.common.a.r());
        this.f.setGravity(17);
        this.f.setTextColor(-7829368);
        if (f > 0.0f) {
            this.f.setText(String.format("%.1f", Float.valueOf(f)));
        }
        this.h = new FiveStarView(context, f);
    }

    public void a(Context context, String str) {
        this.g = new TextView(context);
        this.g.setId(com.sigmob.sdk.common.a.r());
        this.g.setTextColor(-7829368);
        this.g.setSingleLine();
        this.g.setGravity(17);
        this.g.setTextSize(1, 14.0f);
        this.g.setEllipsize(TextUtils.TruncateAt.END);
        this.g.setText(str);
    }

    public void a(String str) {
        k kVarB;
        if (TextUtils.isEmpty(str) || (kVarB = com.sigmob.sdk.common.e.f.b()) == null) {
            return;
        }
        this.i = kVarB.a(str, new k.d() { // from class: com.sigmob.sdk.base.views.CompanionAdsWidget.4
            @Override // com.sigmob.volley.toolbox.k.d
            public void a(k.c cVar, boolean z) {
                if (cVar.b() != null) {
                    CompanionAdsWidget.this.b.setImageBitmap(cVar.b());
                    CompanionAdsWidget.this.b.setVisibility(0);
                    if (CompanionAdsWidget.this.l) {
                        CompanionAdsWidget.this.c();
                    }
                }
            }

            @Override // com.sigmob.volley.n.a
            public void onErrorResponse(s sVar) {
            }
        });
    }

    public boolean a() {
        return this.o;
    }

    public boolean b() {
        return this.p;
    }

    public LinearLayout getFourElementsLayout() {
        return this.r;
    }

    @Override // android.view.View
    public int getVisibility() {
        return super.getVisibility();
    }

    @Override // android.view.View
    public void setOnTouchListener(View.OnTouchListener onTouchListener) {
        if (this.m == 1) {
            this.c.setOnTouchListener(onTouchListener);
        } else {
            super.setOnTouchListener(onTouchListener);
        }
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i == 8) {
            super.setVisibility(i);
            this.i.a();
            this.l = false;
        } else {
            if (i == 0) {
                this.l = true;
                if (this.j.getAnimation() == null && !this.q && this.b.getVisibility() == 0) {
                    c();
                    return;
                }
                return;
            }
            if (i != 4) {
                super.setVisibility(i);
            } else {
                if (this.j.getAnimation() != null) {
                    return;
                }
                h();
            }
        }
    }
}
