package com.kwad.sdk.splashscreen.a;

import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.mvp.Presenter;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends Presenter {
    private com.kwad.sdk.splashscreen.g a;
    private AdTemplate b;
    private List<Integer> c;
    private final com.kwad.sdk.contentalliance.detail.video.d d = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.splashscreen.a.d.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            d.this.a(j2);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            d.this.e();
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            d.this.f();
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        int iCeil = (int) Math.ceil(j / 1000.0f);
        if (this.c == null || this.c.isEmpty()) {
            return;
        }
        Iterator<Integer> it = this.c.iterator();
        while (it.hasNext()) {
            if (iCeil >= it.next().intValue()) {
                com.kwad.sdk.core.report.a.b(this.b, iCeil, null);
                it.remove();
                return;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() {
        com.kwad.sdk.core.report.a.i(this.b);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f() {
        com.kwad.sdk.core.report.a.j(this.b);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.a = (com.kwad.sdk.splashscreen.g) p();
        this.b = this.a.d;
        this.c = com.kwad.sdk.core.response.a.a.S(com.kwad.sdk.core.response.a.d.j(this.b));
        if (this.a.f != null) {
            this.a.f.a(this.d);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        if (this.a.f != null) {
            this.a.f.b(this.d);
        }
    }
}
