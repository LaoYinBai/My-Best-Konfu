package com.kwad.sdk.core.page.kwai;

import androidx.annotation.NonNull;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends g {

    @NonNull
    private com.kwad.sdk.reward.a.b b;
    private com.kwad.sdk.reward.c.a c;
    private com.kwad.sdk.contentalliance.detail.video.d d = new e() { // from class: com.kwad.sdk.core.page.kwai.c.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(int i, int i2) {
            c.this.h();
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
        }
    };

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = this.a.b;
        this.c = this.a.k;
        this.c.a(this.d);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.c.b(this.d);
    }
}
