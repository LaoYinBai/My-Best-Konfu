package com.kwad.sdk.reward.presenter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.kwad.sdk.R;
import com.kwad.sdk.utils.af;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends com.kwad.sdk.reward.g {
    private ViewGroup b;
    private ViewGroup c;
    private ViewGroup d;
    private ImageView e;
    private com.kwad.sdk.reward.a.f f = new com.kwad.sdk.reward.a.f() { // from class: com.kwad.sdk.reward.presenter.f.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            f.this.e();
        }
    };
    private com.kwad.sdk.contentalliance.detail.video.d g = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.reward.presenter.f.2
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            super.f();
            f.this.a.b();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (!this.a.t) {
            this.b.setVisibility(8);
            this.c.setVisibility(0);
        }
        Context contextQ = q();
        if (g() && af.e(contextQ)) {
            this.e.setVisibility(0);
        }
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        ViewGroup viewGroup;
        super.a();
        int i = 0;
        if (this.a.t) {
            this.d.setVisibility(0);
            viewGroup = this.b;
            i = 8;
        } else {
            viewGroup = this.b;
        }
        viewGroup.setVisibility(i);
        this.a.a(this.f);
        this.a.k.a(this.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.b(this.f);
        this.a.k.b(this.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (ViewGroup) a(R.id.ksad_play_detail_top_toolbar);
        this.c = (ViewGroup) a(R.id.ksad_play_end_top_toolbar);
        this.d = (ViewGroup) a(R.id.ksad_js_top);
        this.e = (ImageView) a(R.id.ksad_blur_end_cover);
    }
}
