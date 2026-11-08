package com.kwad.sdk.reward.presenter.b;

import com.kwad.sdk.R;
import com.kwad.sdk.core.webview.jshandler.d;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.utils.bb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends a {
    private com.kwad.sdk.fullscreen.kwai.kwai.c b;
    private com.kwad.sdk.fullscreen.kwai.a.a c;
    private boolean d;
    private f e = new f() { // from class: com.kwad.sdk.reward.presenter.b.b.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (com.kwad.sdk.core.response.a.d.s(b.this.a.g)) {
                b.this.m().findViewById(b.this.i()).setVisibility(8);
            }
        }
    };

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        m().findViewById(R.id.ksad_play_detail_top_toolbar).setVisibility(8);
        m().findViewById(R.id.ksad_play_end_top_toolbar).setVisibility(8);
        super.a();
        this.a.a(this.e);
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected void a(d.a aVar) {
        aVar.a = (int) ((bb.b(q()) / bb.c(q())) + 0.5f);
        aVar.b = 44;
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        if (this.d) {
            this.c.j();
            this.b.j();
        }
        this.a.b(this.e);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = new com.kwad.sdk.fullscreen.kwai.kwai.c();
        this.c = new com.kwad.sdk.fullscreen.kwai.a.a();
        this.b.c(m());
        this.c.c(m());
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
        if (this.d) {
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
        this.d = true;
        m().findViewById(R.id.ksad_play_detail_top_toolbar).setVisibility(0);
        this.c.a(this.a);
        this.b.a(this.a);
    }
}
