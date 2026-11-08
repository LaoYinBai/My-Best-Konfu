package com.kwad.sdk.f.a;

import com.kwad.sdk.api.KsNativeAd;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import java.util.List;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.kwad.sdk.f.kwai.a {
    private AdTemplate b;
    private AdInfo c;
    private List<Integer> d;
    private volatile boolean e = false;
    private com.kwad.sdk.contentalliance.detail.video.d f = new com.kwad.sdk.contentalliance.detail.video.e() { // from class: com.kwad.sdk.f.a.c.1
        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void a(long j, long j2) {
            c.this.a(j2);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void c() {
            c.this.e = false;
            if (!c.this.b.mPvReported) {
                c.this.a.a.a((KsNativeAd) null);
            }
            com.kwad.sdk.core.report.a.a(c.this.b, (JSONObject) null);
            com.kwad.sdk.core.report.a.i(c.this.b);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void d() {
            if (c.this.e) {
                return;
            }
            c.this.e = true;
            com.kwad.sdk.core.report.d.a(c.this.b, System.currentTimeMillis(), 1);
        }

        @Override // com.kwad.sdk.contentalliance.detail.video.e, com.kwad.sdk.contentalliance.detail.video.d
        public void f() {
            com.kwad.sdk.core.report.a.j(c.this.b);
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void a(long j) {
        int iCeil = (int) Math.ceil(j / 1000.0f);
        if (this.d == null || this.d.isEmpty()) {
            return;
        }
        for (Integer num : this.d) {
            if (iCeil >= num.intValue()) {
                com.kwad.sdk.core.report.a.b(this.b, iCeil, null);
                this.d.remove(num);
                return;
            }
        }
    }

    @Override // com.kwad.sdk.f.kwai.a, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b = this.a.d;
        this.c = com.kwad.sdk.core.response.a.d.j(this.b);
        this.d = com.kwad.sdk.core.response.a.a.S(this.c);
        this.a.f.a(this.f);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.f.b(this.f);
    }
}
