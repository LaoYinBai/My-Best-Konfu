package com.kwad.sdk.reward.presenter.b;

import com.kwad.sdk.R;
import com.kwad.sdk.core.webview.jshandler.d;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.utils.bb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends a {
    private com.kwad.sdk.reward.presenter.platdetail.c b;
    private com.kwad.sdk.reward.presenter.a.b c;
    private boolean d;
    private boolean e;
    private boolean f;
    private f g = new f() { // from class: com.kwad.sdk.reward.presenter.b.e.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (com.kwad.sdk.core.response.a.a.q(com.kwad.sdk.core.response.a.d.j(e.this.a.g))) {
                e.this.m().findViewById(e.this.i()).setVisibility(8);
            }
            e.this.s();
        }
    };

    public e(boolean z, boolean z2) {
        this.d = z;
        this.e = z2;
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        m().findViewById(R.id.ksad_play_detail_top_toolbar).setVisibility(8);
        m().findViewById(R.id.ksad_play_end_top_toolbar).setVisibility(8);
        super.a();
        this.a.a(this.g);
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected void a(d.a aVar) {
        aVar.a = (int) ((bb.b(q()) / bb.c(q())) + 0.5f);
        aVar.b = 44;
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        if (this.f) {
            this.c.j();
            this.b.j();
        }
        this.a.b(this.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = new com.kwad.sdk.reward.presenter.platdetail.c(this.e);
        this.c = new com.kwad.sdk.reward.presenter.a.b(this.d);
        this.b.c(m());
        this.c.c(m());
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
        if (this.f) {
            this.c.k();
            this.b.k();
        }
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected String e() {
        return "ksad-video-top-bar.js";
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected int i() {
        return R.id.ksad_js_top;
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected void r() {
        this.f = true;
        m().findViewById(R.id.ksad_play_detail_top_toolbar).setVisibility(0);
        this.a.t = false;
        this.c.a(this.a);
        this.b.a(this.a);
    }
}
