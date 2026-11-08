package com.kwad.sdk.reward.d;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.kwad.sdk.R;
import com.kwad.sdk.core.imageloader.KSImageLoader;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.widget.KSRatingBar;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements com.kwad.sdk.widget.d {
    private static long l = 1600;
    private static long m = 1600;
    private static long n = 200;
    private View a;
    private View b;
    private Button c;
    private Button d;
    private TextView e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private KSRatingBar i;
    private LinearLayout j;
    private a k;

    @Nullable
    private Animator p;
    private AdTemplate r;
    private volatile boolean o = false;
    private boolean q = false;

    public interface a {
        void a(c cVar, View view, boolean z);
    }

    static class b {
        private String a;
        private String b;
        private float c;
        private List<String> d;
        private String e;
        private int f = 15;
        private String g;

        b() {
        }

        public static b a(AdTemplate adTemplate) {
            String str;
            if (adTemplate == null) {
                return null;
            }
            AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(adTemplate);
            b bVar = new b();
            bVar.b = com.kwad.sdk.core.response.a.d.v(adTemplate) ? com.kwad.sdk.core.response.a.a.u(adInfoJ) : com.kwad.sdk.core.response.a.a.t(adInfoJ);
            bVar.c = com.kwad.sdk.core.response.a.a.y(adInfoJ);
            bVar.e = com.kwad.sdk.core.response.a.a.s(adInfoJ);
            bVar.a = com.kwad.sdk.core.response.a.d.v(adTemplate) ? com.kwad.sdk.core.response.a.a.aE(adInfoJ) : com.kwad.sdk.core.response.a.a.au(adInfoJ);
            if (com.kwad.sdk.core.response.a.d.u(adTemplate)) {
                bVar.f = com.kwad.sdk.core.config.b.az();
                str = "安装并体验%s秒  可领取奖励";
            } else {
                bVar.f = com.kwad.sdk.core.config.b.aA();
                str = "浏览详情页%s秒，领取奖励";
            }
            bVar.g = str;
            bVar.d = com.kwad.sdk.core.response.a.c.i(adTemplate);
            return bVar;
        }

        public String a() {
            return String.format(this.g, Integer.valueOf(this.f));
        }
    }

    /* renamed from: com.kwad.sdk.reward.d.c$c, reason: collision with other inner class name */
    static class C0070c implements Animator.AnimatorListener {
        C0070c() {
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

    public c(View view) {
        this.a = view;
        c();
    }

    private static Animator a(final View view, float f, final float f2) throws Resources.NotFoundException {
        final float f3 = f / f2;
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, f2);
        valueAnimatorOfFloat.setDuration(n);
        valueAnimatorOfFloat.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.0f, 0.58f, 1.0f));
        final float dimension = view.getResources().getDimension(R.dimen.ksad_reward_apk_info_card_actionbar_text_size);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reward.d.c.4
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
                com.kwai.sodler.lib.a.b("ApkInfoCardViewHelper", "onAnimationUpdate: " + fFloatValue + ", currentWidth:" + f4 + ", ratio:" + f3);
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
        Animator animatorC = c(view);
        float f = i;
        float f2 = i2;
        Animator animatorA = a(view2, f, f2);
        animatorA.addListener(new C0070c() { // from class: com.kwad.sdk.reward.d.c.3
            @Override // com.kwad.sdk.reward.d.c.C0070c, android.animation.Animator.AnimatorListener
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
        objectAnimatorOfFloat.setDuration(m);
        Animator animatorC2 = c(view2);
        Animator animatorA2 = a(view, f, f2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(animatorC, animatorA, objectAnimatorOfFloat, animatorC2, animatorA2);
        return animatorSet;
    }

    private void a(LinearLayout linearLayout, String str) {
        TextView textView = (TextView) LayoutInflater.from(linearLayout.getContext()).inflate(R.layout.ksad_reward_apk_info_card_tag_item, (ViewGroup) linearLayout, false);
        textView.setText(str);
        this.j.addView(textView);
    }

    private static Animator c(View view) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        objectAnimatorOfFloat.setDuration(n);
        objectAnimatorOfFloat.setInterpolator(PathInterpolatorCompat.create(0.0f, 0.0f, 0.58f, 1.0f));
        return objectAnimatorOfFloat;
    }

    private void c() {
        this.c = (Button) this.a.findViewById(R.id.ksad_reward_apk_info_install_action);
        this.d = (Button) this.a.findViewById(R.id.ksad_reward_apk_info_install_start);
        this.b = this.a.findViewById(R.id.ksad_reward_apk_info_install_container);
        this.f = (ImageView) this.a.findViewById(R.id.ksad_reward_apk_info_icon);
        this.e = (TextView) this.a.findViewById(R.id.ksad_reward_apk_info_name);
        this.g = (TextView) this.a.findViewById(R.id.ksad_reward_apk_info_desc);
        this.i = (KSRatingBar) this.a.findViewById(R.id.ksad_reward_apk_info_score);
        this.j = (LinearLayout) this.a.findViewById(R.id.ksad_reward_apk_info_tags);
    }

    private void d() {
        if (this.p != null) {
            this.p.cancel();
            this.q = true;
        }
    }

    public void a() {
        com.kwai.sodler.lib.a.b("ApkInfoCardViewHelper", "startAnimation");
        int height = this.b.getHeight();
        int width = this.b.getWidth();
        if (height == 0 || width == 0) {
            return;
        }
        if (this.p == null) {
            this.p = a(this.d, this.c, width, height);
            this.p.addListener(new C0070c() { // from class: com.kwad.sdk.reward.d.c.2
                @Override // com.kwad.sdk.reward.d.c.C0070c, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(final Animator animator) {
                    if (c.this.q) {
                        return;
                    }
                    ax.a(new Runnable() { // from class: com.kwad.sdk.reward.d.c.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            animator.start();
                        }
                    }, null, c.m);
                }
            });
        }
        com.kwai.sodler.lib.a.b("ApkInfoCardViewHelper", "mAnimator isStarted: " + this.p.isStarted());
        if (!this.p.isStarted()) {
            com.kwai.sodler.lib.a.b("ApkInfoCardViewHelper", "mAnimator.start()");
            this.p.start();
        }
        this.q = false;
    }

    public void a(View view, boolean z) {
        int id = view.getId();
        if (id == R.id.ksad_reward_apk_info_install_container || id == R.id.ksad_reward_apk_info_install_action || id == R.id.ksad_reward_apk_info_install_start) {
            com.kwai.sodler.lib.a.b("ApkInfoCardViewHelper", "onClick install");
            this.o = true;
            if (this.k != null) {
                this.k.a(this, view, z);
            }
        }
    }

    public void a(AdTemplate adTemplate, boolean z) {
        this.r = adTemplate;
        b bVarA = b.a(adTemplate);
        if (bVarA == null) {
            return;
        }
        KSImageLoader.loadAppIcon(this.f, bVarA.a, adTemplate, 12);
        this.e.setText(bVarA.b);
        this.g.setText(bVarA.e);
        this.i.setStar(bVarA.c);
        int i = 0;
        if (com.kwad.sdk.core.response.a.d.u(adTemplate)) {
            this.d.setText("开始下载");
            this.i.setVisibility(0);
        } else {
            this.d.setText("查看详情");
            this.i.setVisibility(8);
        }
        this.c.setText(bVarA.a());
        this.c.setClickable(true);
        this.d.setClickable(true);
        this.b.setClickable(true);
        new com.kwad.sdk.widget.f(this.c, this);
        new com.kwad.sdk.widget.f(this.d, this);
        new com.kwad.sdk.widget.f(this.b, this);
        List<String> list = bVarA.d;
        if (z && list.size() == 0) {
            this.g.setVisibility(8);
            this.h = (TextView) this.a.findViewById(R.id.ksad_reward_apk_info_desc_2);
            this.h.setVisibility(0);
            this.h.setText(bVarA.e);
        }
        if (list.size() == 0) {
            this.j.setVisibility(8);
        }
        for (String str : list) {
            i++;
            if (i > 3) {
                break;
            } else {
                a(this.j, str);
            }
        }
        this.b.postDelayed(new Runnable() { // from class: com.kwad.sdk.reward.d.c.1
            @Override // java.lang.Runnable
            public void run() {
                com.kwai.sodler.lib.a.b("ApkInfoCardViewHelper", hashCode() + " parentHeight: " + c.this.b.getHeight());
                if (c.this.o) {
                    return;
                }
                c.this.a();
            }
        }, l);
    }

    public void a(a aVar) {
        this.k = aVar;
    }

    public void a(String str, int i, boolean z) {
        if (this.d != null && str != null) {
            this.d.setText(str);
        }
        if (z) {
            a();
        } else {
            d();
        }
    }

    @Override // com.kwad.sdk.widget.d
    public void a_(View view) {
        a(view, true);
    }

    @Override // com.kwad.sdk.widget.d
    public void b(View view) {
        if (com.kwad.sdk.core.response.a.c.g(this.r)) {
            a(view, false);
        }
    }
}
