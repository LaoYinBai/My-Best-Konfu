package com.kwad.sdk.f.a;

import android.view.View;
import com.kwad.sdk.R;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends com.kwad.sdk.f.kwai.a {
    private View b;
    private com.kwad.sdk.contentalliance.detail.video.d c = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.f.a.d.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            super.c();
            d.this.e();
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void d() {
            super.d();
            d.this.e();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        if (this.b.getVisibility() != 0) {
            return;
        }
        this.b.setVisibility(8);
    }

    @Override // com.kwad.sdk.f.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        if (com.ksad.download.c.b.a(this.b.getContext())) {
            this.b.setVisibility(8);
        } else {
            this.b.setVisibility(0);
            this.a.f.a(this.c);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.f.b(this.c);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = a(R.id.ksad_video_network_unavailable);
    }
}
