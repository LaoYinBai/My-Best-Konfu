package com.kwad.sdk.reward.presenter.platdetail.kwai;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.a.h;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.utils.bb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g implements View.OnClickListener {
    private TextView b;
    private ImageView c;
    private AdTemplate d;
    private AdInfo e;
    private com.kwad.sdk.core.download.a.b f;
    private ImageView g;
    private boolean h = false;
    private final com.kwad.sdk.contentalliance.detail.video.d i = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.a.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            if (j2 < a.this.a(j) - 800) {
                a.this.b((int) (((r3 - j2) / 1000.0f) + 0.5f));
            } else if (!a.this.f() && !a.this.g()) {
                a.this.r();
                a.this.i();
            } else {
                a.this.e();
                a.this.s();
                bb.a(a.this.g, 0, 0, 0, bb.a(a.this.q(), 1.0f));
            }
        }
    };
    private final h j = new h() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.a.2
        @Override // com.kwad.sdk.reward.a.h
        public void a() {
            a.this.i();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public long a(long j) {
        return Math.min(com.kwad.sdk.core.response.a.a.p(this.e), j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b(int i) {
        this.b.setText(String.valueOf(i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.a.3
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                a.this.b.setVisibility(8);
            }
        });
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.a.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                a.this.b.setAlpha(1.0f - ((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.h) {
            return;
        }
        this.h = true;
        if (f() || g()) {
            bb.a(this.g, bb.a(q(), 40.0f), 0, 0, bb.a(q(), 1.0f));
        }
        this.c.setAlpha(0.0f);
        this.c.setVisibility(0);
        this.c.setOnClickListener(this);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addListener(new AnimatorListenerAdapter() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.a.5
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                a.this.b.setVisibility(8);
            }
        });
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.a.6
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fFloatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                a.this.b.setAlpha(1.0f - fFloatValue);
                a.this.c.setAlpha(fFloatValue);
            }
        });
        valueAnimatorOfFloat.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r() {
        this.a.b.e();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void s() {
        if (this.a.c != null) {
            this.a.c.a();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void t() {
        com.kwad.sdk.core.report.a.a(this.d, 41, this.a.j.getTouchCoords(), this.a.e);
        this.a.b.a();
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.d = this.a.g;
        this.e = com.kwad.sdk.core.response.a.d.j(this.d);
        this.f = this.a.l;
        this.b.setText(String.valueOf(a(com.kwad.sdk.core.response.a.a.b(this.e))));
        this.b.setVisibility(0);
        this.b.setAlpha(1.0f);
        com.kwad.sdk.reward.d.a().a(this.j);
        this.a.k.a(this.i);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        com.kwad.sdk.reward.d.a().b(this.j);
        this.a.k.b(this.i);
        this.c.setVisibility(8);
        this.h = false;
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (TextView) a(R.id.ksad_video_count_down);
        this.c = (ImageView) a(R.id.ksad_detail_reward_icon);
        this.g = (ImageView) a(R.id.ksad_video_sound_switch);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.c) {
            com.kwad.sdk.core.download.a.a.a(new a.C0035a(view.getContext()).a(this.d).a(this.f).a(2).a(new a.b() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.a.7
                @Override // com.kwad.sdk.core.download.a.a.b
                public void a() {
                    a.this.t();
                }
            }));
        }
    }
}
