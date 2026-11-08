package com.kwad.sdk.f.a;

import com.kwad.sdk.api.KsNativeAd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends com.kwad.sdk.f.kwai.a {
    private KsNativeAd.VideoPlayListener b;
    private com.kwad.sdk.contentalliance.detail.video.d c = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.f.a.e.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(int i, int i2) {
            if (e.this.b != null) {
                e.this.b.onVideoPlayError(i, i2);
            }
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            if (e.this.b != null) {
                e.this.b.onVideoPlayStart();
            }
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            if (e.this.b != null) {
                e.this.b.onVideoPlayComplete();
            }
        }
    };

    @Override // com.kwad.sdk.f.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = this.a.b;
        this.a.f.a(this.c);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.f.b(this.c);
    }
}
