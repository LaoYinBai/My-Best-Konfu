package com.kwad.sdk.reward.presenter.platdetail.kwai;

import android.animation.ValueAnimator;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends g implements View.OnClickListener {
    private ViewGroup b;
    private ImageView c;
    private TextView d;
    private AdTemplate e;
    private AdInfo f;
    private long g;
    private boolean h;
    private boolean i = false;
    private View j = null;
    private long k = -1;
    private final f l = new f() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.c.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (c.this.h) {
                k.a(c.this.a);
            }
        }
    };
    private final com.kwad.sdk.contentalliance.detail.video.d m = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.c.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            c.this.g = j2;
            c.this.h = j - j2 < 800;
            if (c.this.k >= 0) {
                if (j2 > Math.min(Math.min(c.this.k, com.kwad.sdk.core.response.a.a.p(c.this.f)), j)) {
                    c.this.e();
                }
            }
        }
    };
    private final com.kwad.sdk.reward.a.e n = new com.kwad.sdk.reward.a.e() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.c.3
        @Override // com.kwad.sdk.reward.a.e
        public void a(long j, long j2, int i) {
            c.this.i = true;
            c.this.e();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.j.getVisibility() == 0) {
            return;
        }
        this.j.setAlpha(0.0f);
        this.j.setVisibility(0);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.reward.presenter.platdetail.kwai.c.4
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                c.this.j.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.start();
        this.j.setOnClickListener(this);
    }

    private void i() {
        com.kwad.sdk.core.report.a.a(this.e, 69, (int) (this.k / 1000), (int) (this.g / 1000));
        boolean z = !this.a.v && com.kwad.sdk.core.config.b.R();
        if (f() || g() || this.g < com.kwad.sdk.core.response.a.a.p(this.f)) {
            if (z) {
                com.kwad.sdk.reward.widget.a.a(this.a);
                return;
            }
            com.kwad.sdk.core.report.a.a(this.a.g, (int) (this.k / 1000), (int) (this.g / 1000));
        } else {
            if (!this.h && this.g < com.kwad.sdk.core.response.a.a.p(this.f)) {
                if (this.i) {
                    h();
                    return;
                }
                return;
            }
            com.kwad.sdk.core.report.a.a(this.a.g, (int) (this.k / 1000), (int) (this.g / 1000));
            s();
        }
        r();
        k.a(this.a);
    }

    private void r() {
        this.a.u = true;
        this.a.k.a(!com.kwad.sdk.core.response.a.a.aG(this.f));
    }

    private void s() {
        this.a.b.e();
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.e = this.a.g;
        this.f = com.kwad.sdk.core.response.a.d.j(this.e);
        this.a.k.a(this.m);
        this.a.q.add(this.n);
        this.a.a(this.l);
        this.k = com.kwad.sdk.core.response.a.a.o(this.f);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.k.b(this.m);
        this.a.q.remove(this.n);
        this.a.b(this.l);
        this.j.setVisibility(8);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        ImageView imageView;
        int i;
        View view;
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_reward_container_new);
        this.c = (ImageView) a(R.id.ksad_detail_close_btn);
        this.d = (TextView) a(R.id.ksad_top_toolbar_close_tip);
        if (TextUtils.isEmpty(com.kwad.sdk.core.config.b.V())) {
            if (com.kwad.sdk.core.config.b.S() == 0) {
                imageView = this.c;
                i = R.drawable.ksad_page_close;
            } else {
                imageView = this.c;
                i = R.drawable.ksad_video_skip_icon;
            }
            imageView.setImageResource(i);
            this.d.setVisibility(8);
            view = this.c;
        } else {
            this.d.setText(com.kwad.sdk.core.config.b.V());
            this.c.setVisibility(8);
            view = this.d;
        }
        this.j = view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.j) {
            i();
        }
    }
}
