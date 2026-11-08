package com.kwad.sdk.reward.presenter.a.kwai;

import android.widget.FrameLayout;
import com.kwad.sdk.R;
import com.kwad.sdk.contentalliance.detail.video.e;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.i.a;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b extends g {
    private FrameLayout b;
    private volatile long c = 0;
    private volatile boolean d = false;
    private com.kwad.sdk.contentalliance.detail.video.d e = new e() { // from class: com.kwad.sdk.reward.presenter.a.kwai.b.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            super.a(j, j2);
            b.this.c = j2;
            b.this.d = j - j2 < 800;
        }
    };
    private f f = new f() { // from class: com.kwad.sdk.reward.presenter.a.kwai.b.4
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (b.this.a.p != null) {
                b.this.a.p.c();
            }
        }
    };
    private b.c g = new b.c() { // from class: com.kwad.sdk.reward.presenter.a.kwai.b.5
        @Override // com.kwad.sdk.core.webview.jshandler.b.c
        public void a(b.a aVar) {
            b.this.a.b.a();
        }
    };

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.c = 0L;
        this.d = false;
        AdTemplate adTemplate = this.a.g;
        com.kwad.sdk.i.a aVar = this.a.p;
        if (aVar != null) {
            this.a.x = true;
            aVar.a(this.g);
            aVar.a(this.b, this.a.j, adTemplate);
            aVar.a(new a.b() { // from class: com.kwad.sdk.reward.presenter.a.kwai.b.2
                @Override // com.kwad.sdk.i.a.b
                public void a(boolean z) {
                    b.this.a.x = z;
                }
            });
            aVar.a(this.a.h);
            aVar.a();
            aVar.a(new a.InterfaceC0064a() { // from class: com.kwad.sdk.reward.presenter.a.kwai.b.3
                @Override // com.kwad.sdk.i.a.InterfaceC0064a
                public void a() {
                    if (b.this.a.b != null) {
                        long jP = com.kwad.sdk.core.response.a.a.p(com.kwad.sdk.core.response.a.d.j(b.this.a.g));
                        boolean z = true;
                        if (jP >= 0 && !b.this.d && b.this.c < jP) {
                            z = false;
                        }
                        if (z) {
                            b.this.a.b.e();
                        }
                    }
                }
            });
            this.a.a(this.f);
            this.a.k.a(this.e);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.b(this.f);
        this.a.k.b(this.e);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (FrameLayout) a(R.id.ksad_landing_page_container);
    }
}
