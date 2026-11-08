package com.kwad.sdk.reward.presenter;

import com.kwad.sdk.R;
import com.kwad.sdk.core.page.AdWebViewLandPageActivityProxy;
import com.kwad.sdk.core.page.widget.webview.KsAdWebView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g extends com.kwad.sdk.reward.g {
    private com.kwad.sdk.widget.e b;
    private com.kwad.sdk.reward.a.f c = new com.kwad.sdk.reward.a.f() { // from class: com.kwad.sdk.reward.presenter.g.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (g.this.b != null && g.this.b.e()) {
                g.this.b.c();
            } else if (g.this.b != null) {
                g.this.b.d();
                g.this.h();
            }
        }
    };
    private final com.kwad.sdk.reward.a.g d = new com.kwad.sdk.reward.a.g() { // from class: com.kwad.sdk.reward.presenter.g.2
        @Override // com.kwad.sdk.reward.a.g
        public void a() {
            if (g.this.b != null && g.this.b.e()) {
                g.this.b.c();
                if (g.this.a != null) {
                    g.this.a.b();
                    g.this.a.u = true;
                    g.this.a.k.a(true);
                    return;
                }
                return;
            }
            if (g.this.b != null) {
                g.this.b.d();
                if (com.kwad.sdk.core.response.a.a.B(com.kwad.sdk.core.response.a.d.j(g.this.a.g))) {
                    AdWebViewLandPageActivityProxy.launch(g.this.o(), g.this.a.g, true);
                }
            }
        }
    };

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.a.a(this.c);
        this.b = this.a.m;
        this.b.a(this.a.g, this.a.j, this.a.l);
        this.b.b();
        com.kwad.sdk.reward.c.a().a(this.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.b.a();
        this.b.d();
        this.a.b(this.c);
        com.kwad.sdk.reward.c.a().b(this.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        ((KsAdWebView) a(R.id.ksad_playable_webview)).setVisibility(4);
    }
}
