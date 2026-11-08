package com.kwad.sdk.reward.kwai;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.animation.PathInterpolatorCompat;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdProductInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.response.model.CouponInfo;
import com.kwad.sdk.reward.d.d;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.utils.bb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends d implements View.OnClickListener {
    private final ViewGroup a;
    private int[] b;
    private View c;
    private ImageView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private Button h;

    @Nullable
    private b i;

    /* renamed from: com.kwad.sdk.reward.kwai.a$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ AdTemplate a;
        final /* synthetic */ long b;

        AnonymousClass1(AdTemplate adTemplate, long j) {
            this.a = adTemplate;
            this.b = j;
        }

        @Override // java.lang.Runnable
        public void run() {
            final Animator animatorA = a.this.a(a.this.c);
            animatorA.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reward.kwai.a.1.1
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    animatorA.removeListener(this);
                    com.kwad.sdk.core.report.a.c(AnonymousClass1.this.a, 169, null);
                    a.this.a(a.this.d, AnonymousClass1.this.b).start();
                    ax.a(new Runnable() { // from class: com.kwad.sdk.reward.kwai.a.1.1.1
                        @Override // java.lang.Runnable
                        public void run() {
                            a.this.b();
                        }
                    }, null, AnonymousClass1.this.b);
                }
            });
            animatorA.start();
        }
    }

    /* renamed from: com.kwad.sdk.reward.kwai.a$a, reason: collision with other inner class name */
    private static class C0074a {
        private CharSequence a;
        private CharSequence b;
        private CharSequence c;
        private CharSequence d;

        public C0074a(@NonNull CouponInfo couponInfo) {
            this.a = couponInfo.displayTitle;
            this.b = couponInfo.displayValue;
            this.c = !TextUtils.isEmpty(couponInfo.displayBase) ? String.format("满%s可用", couponInfo.displayBase) : "";
            this.d = couponInfo.displayActionWords;
        }

        @Nullable
        public static C0074a a(AdTemplate adTemplate) {
            AdProductInfo adProductInfoAK = com.kwad.sdk.core.response.a.a.aK(com.kwad.sdk.core.response.a.d.j(adTemplate));
            if (adProductInfoAK == null || adProductInfoAK.couponList == null || adProductInfoAK.couponList.size() <= 0) {
                return null;
            }
            return a(adProductInfoAK.couponList.get(0));
        }

        @Nullable
        public static C0074a a(CouponInfo couponInfo) {
            if (couponInfo == null) {
                return null;
            }
            return new C0074a(couponInfo);
        }

        public CharSequence a() {
            return this.a;
        }

        public CharSequence a(Context context) {
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(context.getResources().getDimensionPixelSize(R.dimen.ksad_coupon_dialog_value_prefix_text_size));
            SpannableString spannableString = new SpannableString("¥" + ((Object) this.b));
            spannableString.setSpan(absoluteSizeSpan, 0, "¥".length(), 17);
            return spannableString;
        }

        public CharSequence b() {
            return this.c;
        }

        public CharSequence c() {
            return this.d;
        }
    }

    public interface b {
        void a();

        void b();
    }

    public a(Context context, @Nullable ViewGroup viewGroup, @Nullable int[] iArr) {
        this.b = iArr;
        this.a = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.ksad_reward_coupon_dialog, viewGroup, false);
        a(context, this.a);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator a(View view) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "scaleX", 0.0f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "scaleY", 0.0f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300L);
        animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
        return animatorSet;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Animator a(ImageView imageView, long j) {
        ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(imageView, "alpha", imageView.getAlpha(), 0.0f);
        objectAnimatorOfFloat.setDuration(j);
        return objectAnimatorOfFloat;
    }

    private void a(Context context, ViewGroup viewGroup) {
        this.c = viewGroup.findViewById(R.id.ksad_coupon_dialog_card);
        this.d = (ImageView) viewGroup.findViewById(R.id.ksad_coupon_dialog_bg);
        this.e = (TextView) viewGroup.findViewById(R.id.ksad_coupon_dialog_title);
        this.f = (TextView) viewGroup.findViewById(R.id.ksad_coupon_dialog_content);
        this.g = (TextView) viewGroup.findViewById(R.id.ksad_coupon_dialog_desc);
        this.h = (Button) viewGroup.findViewById(R.id.ksad_coupon_dialog_btn_action);
        this.h.setOnClickListener(this);
    }

    private void a(C0074a c0074a) {
        if (c0074a == null) {
            return;
        }
        if (this.e != null) {
            this.e.setText(c0074a.a());
        }
        if (this.f != null) {
            this.f.setText(c0074a.a(this.a.getContext()));
        }
        if (this.g != null) {
            this.g.setText(c0074a.b());
        }
        if (this.h != null) {
            this.h.setText(c0074a.c());
        }
    }

    private Animator b(View view) {
        ObjectAnimator objectAnimatorOfFloat;
        int[] iArrC;
        Interpolator interpolatorCreate = PathInterpolatorCompat.create(0.89f, 0.02f, 0.72f, 1.0f);
        ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 0.0f);
        ObjectAnimator objectAnimatorOfFloat3 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 0.0f);
        objectAnimatorOfFloat2.setInterpolator(interpolatorCreate);
        objectAnimatorOfFloat3.setInterpolator(interpolatorCreate);
        ObjectAnimator objectAnimatorOfFloat4 = null;
        if (this.b == null || this.b.length < 2 || (iArrC = bb.c(view)) == null) {
            objectAnimatorOfFloat = null;
        } else {
            Interpolator interpolatorCreate2 = PathInterpolatorCompat.create(0.33f, 0.0f, 0.83f, 1.0f);
            objectAnimatorOfFloat4 = ObjectAnimator.ofFloat(view, "translationX", this.b[0] - iArrC[0]);
            objectAnimatorOfFloat = ObjectAnimator.ofFloat(view, "translationY", this.b[1] - iArrC[1]);
            objectAnimatorOfFloat4.setInterpolator(interpolatorCreate2);
            objectAnimatorOfFloat.setInterpolator(interpolatorCreate2);
        }
        ObjectAnimator objectAnimatorOfFloat5 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 1.0f);
        objectAnimatorOfFloat5.setDuration(200L);
        ObjectAnimator objectAnimatorOfFloat6 = ObjectAnimator.ofFloat(view, "alpha", 1.0f, 0.0f);
        objectAnimatorOfFloat6.setDuration(200L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(objectAnimatorOfFloat5, objectAnimatorOfFloat6);
        AnimatorSet animatorSet2 = new AnimatorSet();
        animatorSet2.setDuration(500L);
        if (objectAnimatorOfFloat4 != null) {
            animatorSet2.playTogether(objectAnimatorOfFloat2, objectAnimatorOfFloat3, animatorSet, objectAnimatorOfFloat4, objectAnimatorOfFloat);
        } else {
            animatorSet2.playTogether(objectAnimatorOfFloat2, objectAnimatorOfFloat3, animatorSet);
        }
        return animatorSet2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        final Animator animatorB = b(this.c);
        animatorB.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reward.kwai.a.2
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                animatorB.removeListener(this);
                if (a.this.i != null) {
                    a.this.i.b();
                }
            }
        });
        animatorB.start();
    }

    @Override // com.kwad.sdk.reward.d.d
    public ViewGroup a() {
        return this.a;
    }

    @Override // com.kwad.sdk.reward.d.d
    public void a(AdTemplate adTemplate) {
        super.a(adTemplate);
        a(C0074a.a(adTemplate));
        this.a.post(new AnonymousClass1(adTemplate, com.kwad.sdk.core.config.b.aN()));
    }

    public void a(@Nullable b bVar) {
        this.i = bVar;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (!view.equals(this.h) || this.i == null) {
            return;
        }
        this.i.a();
    }
}
