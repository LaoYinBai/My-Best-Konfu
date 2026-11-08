package com.kwad.sdk.fullscreen.kwai.kwai;

import android.animation.ValueAnimator;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.a.e;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends g implements View.OnClickListener {
    private ImageView b;
    private TextView c;
    private TextView e;
    private AdTemplate f;
    private View d = null;
    private e g = new e() { // from class: com.kwad.sdk.fullscreen.kwai.kwai.d.1
        @Override // com.kwad.sdk.reward.a.e
        public void a(long j, long j2, int i) {
            d.this.e();
        }
    };
    private com.kwad.sdk.contentalliance.detail.video.d h = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.fullscreen.kwai.kwai.d.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            if (j2 >= com.kwad.sdk.core.config.b.Q()) {
                d.this.e();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.d.getVisibility() == 0) {
            return;
        }
        this.d.setAlpha(0.0f);
        this.d.setVisibility(0);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.kwad.sdk.fullscreen.kwai.kwai.d.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                d.this.d.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
            }
        });
        valueAnimatorOfFloat.start();
        this.d.setOnClickListener(this);
    }

    private void i() {
        com.kwad.sdk.core.report.a.i(this.f, this.a.e);
        this.a.b.b();
        this.a.k.e();
        this.a.b();
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.f = this.a.g;
        this.a.k.a(this.h);
        this.a.q.add(this.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.k.b(this.h);
        this.a.q.remove(this.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        ImageView imageView;
        int i;
        View view;
        super.c_();
        this.e = (TextView) a(R.id.ksad_detail_call_btn);
        this.b = (ImageView) a(R.id.ksad_skip_icon);
        this.c = (TextView) a(R.id.ksad_top_toolbar_close_tip);
        if (TextUtils.isEmpty(com.kwad.sdk.core.config.b.T())) {
            if (com.kwad.sdk.core.config.b.P() == 0) {
                imageView = this.b;
                i = R.drawable.ksad_page_close;
            } else {
                imageView = this.b;
                i = R.drawable.ksad_video_skip_icon;
            }
            imageView.setImageResource(i);
            this.c.setVisibility(8);
            view = this.b;
        } else {
            this.c.setText(com.kwad.sdk.core.config.b.T());
            this.b.setVisibility(8);
            view = this.c;
        }
        this.d = view;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.d) {
            i();
        }
    }
}
