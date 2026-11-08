package com.kwad.sdk.draw.a;

import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.api.KsAppDownloadListener;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.download.a.a;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.draw.view.DrawDownloadProgressBar;
import com.kwad.sdk.utils.az;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.kwad.sdk.draw.kwai.a implements View.OnClickListener {
    private ViewGroup b;
    private TextView c;
    private TextView d;
    private TextView e;
    private DrawDownloadProgressBar f;
    private AdTemplate g;
    private AdInfo h;

    @Nullable
    private com.kwad.sdk.core.download.a.b i;
    private ValueAnimator j;
    private int k;
    private int l;
    private int m;
    private KsLogoView n;
    private com.kwad.sdk.contentalliance.detail.video.d o = new e() { // from class: com.kwad.sdk.draw.a.c.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            if (j2 >= c.this.m * 1000) {
                c.this.a.f.a();
            } else if (j2 >= c.this.l * 1000) {
                c.this.h();
            } else if (j2 >= c.this.k * 1000) {
                c.this.f();
            }
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
        }
    };
    private KsAppDownloadListener p = new com.kwad.sdk.core.download.a.c() { // from class: com.kwad.sdk.draw.a.c.2
        @Override // com.kwad.sdk.core.download.a.c
        public void a(int i) {
            super.a(i);
            c.this.e.setText(com.kwad.sdk.core.response.a.a.a());
            c.this.f.a(com.kwad.sdk.core.response.a.a.a(), i);
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onDownloadFailed() {
            c.this.e.setText(com.kwad.sdk.core.response.a.a.A(c.this.h));
            c.this.f.a(com.kwad.sdk.core.response.a.a.A(c.this.h), c.this.f.getMax());
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onDownloadFinished() {
            c.this.e.setText(com.kwad.sdk.core.response.a.a.a(c.this.g));
            c.this.f.a(com.kwad.sdk.core.response.a.a.a(c.this.g), c.this.f.getMax());
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onIdle() {
            c.this.e.setText(com.kwad.sdk.core.response.a.a.A(c.this.h));
            c.this.f.a(com.kwad.sdk.core.response.a.a.A(c.this.h), c.this.f.getMax());
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onInstalled() {
            c.this.e.setText(com.kwad.sdk.core.response.a.a.m(c.this.h));
            c.this.f.a(com.kwad.sdk.core.response.a.a.m(c.this.h), c.this.f.getMax());
        }

        @Override // com.kwad.sdk.api.KsAppDownloadListener
        public void onProgressUpdate(int i) {
            c.this.e.setText(i + "%");
            c.this.f.a(i + "%", i);
        }
    };

    private void a(boolean z, int i) {
        com.kwad.sdk.core.download.a.a.a(new a.C0035a(this.b.getContext()).a(this.g).a(this.i).a(i).a(z).a(new a.b() { // from class: com.kwad.sdk.draw.a.c.3
            @Override // com.kwad.sdk.core.download.a.a.b
            public void a() {
                com.kwad.sdk.core.report.a.a(c.this.g, 1, c.this.a.b.getTouchCoords());
                if (c.this.a.a != null) {
                    c.this.a.a.onAdClicked();
                }
            }
        }));
    }

    private void e() {
        this.k = com.kwad.sdk.core.response.a.a.X(this.h);
        this.l = com.kwad.sdk.core.response.a.a.Y(this.h);
        this.m = com.kwad.sdk.core.response.a.a.Z(this.h);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        if (this.e.getVisibility() == 0 || this.f.getVisibility() == 0) {
            return;
        }
        this.e.setOnClickListener(this);
        this.e.setVisibility(0);
        this.j = az.a(this.e, 0, com.kwad.sdk.a.kwai.a.a(this.e.getContext(), 44.0f));
        this.j.setInterpolator(new DecelerateInterpolator(2.0f));
        this.j.setDuration(300L);
        this.j.start();
    }

    private void g() {
        if (this.j != null) {
            this.j.removeAllListeners();
            this.j.cancel();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void h() {
        if (this.f.getVisibility() == 0) {
            return;
        }
        this.f.setOnClickListener(this);
        this.f.setVisibility(0);
        this.e.setVisibility(8);
    }

    @Override // com.kwad.sdk.draw.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.g = this.a.c;
        this.h = com.kwad.sdk.core.response.a.d.j(this.g);
        this.i = this.a.d;
        e();
        this.n.a(this.g);
        this.e.setText(com.kwad.sdk.core.response.a.a.A(this.h));
        this.e.setVisibility(8);
        this.f.a(com.kwad.sdk.core.response.a.a.A(this.h), this.f.getMax());
        this.f.setVisibility(8);
        this.b.setVisibility(0);
        this.b.setOnClickListener(this);
        if (com.kwad.sdk.core.response.a.a.B(this.h)) {
            this.c.setText(com.kwad.sdk.core.response.a.a.t(this.h));
            this.c.setVisibility(0);
            if (this.i != null) {
                this.i.a(this.p);
            }
        } else {
            this.c.setVisibility(8);
        }
        this.d.setText(com.kwad.sdk.core.response.a.a.s(this.h));
        this.a.e.a(this.o);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        g();
        if (this.i != null && this.p != null) {
            this.i.b(this.p);
        }
        this.a.e.b(this.o);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_ad_normal_container);
        this.c = (TextView) a(R.id.ksad_ad_normal_title);
        this.d = (TextView) a(R.id.ksad_ad_normal_des);
        this.n = (KsLogoView) a(R.id.ksad_ad_normal_logo);
        this.e = (TextView) a(R.id.ksad_ad_normal_convert_btn);
        this.f = (DrawDownloadProgressBar) a(R.id.ksad_ad_light_convert_btn);
        this.f.setTextSize(16);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view == this.b) {
            h();
            a(false, 2);
            return;
        }
        if (view == this.e) {
            h();
        } else if (view != this.f) {
            return;
        }
        a(true, 1);
    }
}
