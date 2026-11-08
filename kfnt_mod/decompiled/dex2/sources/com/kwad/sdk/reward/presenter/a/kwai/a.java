package com.kwad.sdk.reward.presenter.a.kwai;

import android.view.View;
import android.widget.FrameLayout;
import com.kwad.sdk.R;
import com.kwad.sdk.compliance.widget.ComplianceTextView;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.page.c;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.view.AdBaseFrameLayout;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g implements c.a {
    private FrameLayout b;
    private com.kwad.sdk.core.page.c c;
    private AdTemplate d;
    private AdBaseFrameLayout e;
    private View f;
    private View g;
    private ComplianceTextView h;
    private volatile long i = 0;
    private volatile boolean j = false;
    private final com.kwad.sdk.contentalliance.detail.video.d k = new e() { // from class: com.kwad.sdk.reward.presenter.a.kwai.a.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            super.a(j, j2);
            a.this.i = j2;
            a.this.j = j - j2 < 800;
        }
    };
    private final f l = new f() { // from class: com.kwad.sdk.reward.presenter.a.kwai.a.2
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (a.this.b == null || a.this.c == null || !a.this.c.d()) {
                return;
            }
            a.this.h.setVisibility(8);
            a.this.b.setVisibility(0);
            a.this.c.e();
        }
    };

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.d = this.a.g;
        if (this.c == null) {
            this.c = new com.kwad.sdk.core.page.c(q(), this.d, 4, false);
            this.c.a(this);
            this.c.a(new c.C0043c().b(false).a(true).a("").b(com.kwad.sdk.core.response.a.b.i(this.d)).a());
            this.b.addView(this.c.a());
            this.f = this.c.b();
            this.g = this.c.c();
        }
        this.i = 0L;
        this.j = false;
        this.e = this.a.j;
        this.a.a(this.l);
        this.a.k.a(this.k);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.b(this.l);
        this.a.k.b(this.k);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (FrameLayout) a(R.id.ksad_interactive_landing_page_container);
        this.h = (ComplianceTextView) a(R.id.ksad_compliance_view);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
        if (this.c != null) {
            this.c.g();
        }
    }

    @Override // com.kwad.sdk.core.page.c.a
    public void onBackBtnClicked(View view) {
        if (this.a.b != null) {
            long jP = com.kwad.sdk.core.response.a.a.p(com.kwad.sdk.core.response.a.d.j(this.a.g));
            boolean z = true;
            if (jP >= 0 && !this.j && this.i < jP) {
                z = false;
            }
            if (z) {
                this.a.b.e();
            }
        }
        this.a.b.a(false);
        h();
    }

    @Override // com.kwad.sdk.core.page.c.a
    public void onCloseBtnClicked(View view) {
    }
}
