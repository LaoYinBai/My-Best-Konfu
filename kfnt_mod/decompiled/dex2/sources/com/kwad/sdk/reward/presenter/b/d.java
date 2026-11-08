package com.kwad.sdk.reward.presenter.b;

import com.kwad.sdk.R;
import com.kwad.sdk.core.webview.jshandler.d;
import com.kwad.sdk.utils.bb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends a {
    private com.kwad.sdk.reward.presenter.platdetail.a b;
    private boolean c;

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        m().findViewById(R.id.ksad_video_play_bar_h5).setVisibility(8);
        super.a();
        if (this.c) {
            this.b.a(this.a);
        }
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected void a(d.a aVar) {
        float fC = bb.c(q());
        float fB = bb.b(q());
        float fA = bb.a(q());
        aVar.a = (int) ((fB / fC) + 0.5f);
        aVar.b = (int) ((fA / fC) + 0.5f);
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        if (this.c) {
            this.b.j();
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = new com.kwad.sdk.reward.presenter.platdetail.a();
        this.b.c(m());
        m().findViewById(i()).setVisibility(0);
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected String e() {
        return "ksad-video-bottom-card.js";
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected int i() {
        return R.id.ksad_js_slide_black;
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected void r() {
        this.b.a(this.a);
        this.c = true;
    }
}
