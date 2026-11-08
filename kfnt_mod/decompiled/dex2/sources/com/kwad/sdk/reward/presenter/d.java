package com.kwad.sdk.reward.presenter;

import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.utils.e;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends com.kwad.sdk.reward.g implements e.a {
    private AdInfo b;
    private com.kwad.sdk.reward.c.a c;
    private com.kwad.sdk.utils.e d;

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = com.kwad.sdk.core.response.a.d.j(this.a.g);
        if (this.d == null) {
            this.d = new com.kwad.sdk.utils.e(com.kwad.sdk.core.response.a.a.v(this.b));
        } else {
            this.d.a(com.kwad.sdk.core.response.a.a.v(this.b));
        }
        this.c = this.a.k;
        this.d.a(this.a.i, this);
    }

    @Override // com.kwad.sdk.utils.e.a
    public void a(String str) {
        if (this.a.b != null) {
            this.a.b.e();
        }
        this.c.e();
        this.a.b();
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.d.a(this.a.i);
    }
}
