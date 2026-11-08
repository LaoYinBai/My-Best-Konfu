package com.kwad.sdk.reward.presenter;

import androidx.annotation.NonNull;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e extends com.kwad.sdk.reward.g {

    @NonNull
    private com.kwad.sdk.reward.a.b b;
    private com.kwad.sdk.reward.c.a c;
    private long d;
    private final com.kwad.sdk.contentalliance.detail.video.d e = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.reward.presenter.e.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(int i, int i2) {
            e.this.b.a(i, i2);
            e.this.h();
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            if (e.this.a.u) {
                return;
            }
            e.this.d = j2;
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            e.this.b.c();
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            if (e.this.a.r && e.this.a.u) {
                e.this.b.a(e.this.d);
            } else {
                e.this.b.d();
            }
        }
    };

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = this.a.b;
        this.c = this.a.k;
        this.c.a(this.e);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.c.b(this.e);
    }
}
