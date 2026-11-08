package com.kwad.sdk.reward.presenter.a;

import com.kwad.sdk.R;
import com.kwad.sdk.core.response.a.d;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.l;
import com.kwad.sdk.reward.presenter.a.kwai.c;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends g {
    private AdInfo b;
    private AdTemplate c;
    private com.kwad.sdk.core.e.a d;
    private l.b e = new l.b(null, 0 == true ? 1 : 0) { // from class: com.kwad.sdk.reward.presenter.a.a.1
        @Override // com.kwad.sdk.reward.l.b, com.kwad.sdk.reward.l.a
        public void b() {
            super.b();
            a.this.h();
        }

        @Override // com.kwad.sdk.reward.l.b, com.kwad.sdk.reward.l.a
        public void c() {
            super.c();
            a.this.a.a(a.this.q(), 15, 1);
        }

        @Override // com.kwad.sdk.reward.l.b, com.kwad.sdk.reward.l.a
        public void d() {
            super.d();
            a.this.a.a(a.this.q(), 17, 2);
        }
    };

    /* renamed from: com.kwad.sdk.reward.presenter.a.a$a, reason: collision with other inner class name */
    private static class C0075a extends com.kwad.sdk.reward.presenter.a {
        private C0075a() {
        }

        @Override // com.kwad.sdk.reward.presenter.a
        protected int e() {
            return R.id.ksad_blur_end_cover;
        }

        @Override // com.kwad.sdk.reward.presenter.a
        protected int i() {
            return 8;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public a(com.kwad.sdk.core.e.a<?> aVar, AdTemplate adTemplate, boolean z) {
        this.c = adTemplate;
        this.d = aVar;
        this.b = d.j(this.c);
        if (z) {
            e();
        } else {
            i();
        }
    }

    private void e() {
        if (com.kwad.sdk.core.response.a.a.an(this.b)) {
            return;
        }
        if (com.kwad.sdk.core.response.a.b.j(this.c)) {
            a(new com.kwad.sdk.reward.presenter.a.kwai.a());
        }
        c cVar = new c();
        cVar.a(this.e);
        a((Presenter) cVar);
        if (com.kwad.sdk.core.response.a.b.h(this.c)) {
            a(new com.kwad.sdk.reward.presenter.a.kwai.d());
        }
        a(new C0075a());
        if (com.kwad.sdk.core.response.a.a.aG(this.b) || !com.kwad.sdk.core.response.a.a.q(this.b)) {
            return;
        }
        a(new com.kwad.sdk.reward.presenter.a.kwai.b());
    }

    private void i() {
        c cVar = new c();
        cVar.a(this.e);
        a((Presenter) cVar);
        if (com.kwad.sdk.core.response.a.b.h(this.c)) {
            a(new com.kwad.sdk.reward.presenter.a.kwai.d());
        }
        if (d.s(this.c)) {
            a(new com.kwad.sdk.reward.presenter.a.kwai.b());
        }
        if (com.kwad.sdk.core.response.a.b.j(this.c)) {
            a(new com.kwad.sdk.reward.presenter.a.kwai.a());
        }
    }
}
