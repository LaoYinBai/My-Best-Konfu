package com.kwad.sdk.reward.presenter.a.kwai;

import android.widget.FrameLayout;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends g {
    private FrameLayout b;
    private com.kwad.sdk.i.b c;
    private b.c d = new b.c() { // from class: com.kwad.sdk.reward.presenter.a.kwai.d.1
        @Override // com.kwad.sdk.core.webview.jshandler.b.c
        public void a(b.a aVar) {
            d.this.a.b.a();
        }
    };

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        AdTemplate adTemplate = this.a.g;
        this.c = this.a.o;
        if (this.c == null) {
            return;
        }
        this.c.a(this.d);
        this.c.a(this.b, this.a.j, adTemplate, this.a.l, this.a.f);
        this.c.b();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        if (this.c != null) {
            this.c.g();
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (FrameLayout) a(R.id.ksad_web_card_container);
    }
}
