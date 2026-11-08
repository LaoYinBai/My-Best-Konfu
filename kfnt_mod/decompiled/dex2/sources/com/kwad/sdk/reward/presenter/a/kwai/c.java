package com.kwad.sdk.reward.presenter.a.kwai;

import androidx.annotation.Nullable;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.l;
import com.kwad.sdk.reward.widget.tailframe.TailFrameLandscapeHorizontal;
import com.kwad.sdk.reward.widget.tailframe.TailFrameLandscapeVertical;
import com.kwad.sdk.reward.widget.tailframe.TailFramePortraitHorizontal;
import com.kwad.sdk.reward.widget.tailframe.TailFramePortraitVertical;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends g {
    private TailFramePortraitVertical b;
    private TailFramePortraitHorizontal c;
    private TailFrameLandscapeVertical d;
    private TailFrameLandscapeHorizontal e;
    private AdTemplate f;
    private AdInfo g;

    @Nullable
    private com.kwad.sdk.i.b h;
    private boolean i;
    private l.b j;
    private f k = new f() { // from class: com.kwad.sdk.reward.presenter.a.kwai.c.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            if (com.kwad.sdk.core.response.a.a.aG(c.this.g)) {
                l.a(c.this.o(), c.this.f, c.this.j);
                return;
            }
            if (c.this.a.x) {
                return;
            }
            if (c.this.h != null && c.this.h.d()) {
                c.this.i = false;
            } else {
                c.this.i = true;
                c.this.i();
            }
        }
    };

    /* JADX INFO: Access modifiers changed from: private */
    public void b(boolean z) {
        com.kwad.sdk.core.report.a.a(this.f, z ? 2 : 153, this.a.j.getTouchCoords(), this.a.e);
        this.a.b.a();
    }

    private void e() {
        if (this.i) {
            this.b.a();
            this.b.setVisibility(8);
            this.c.a();
            this.c.setVisibility(8);
            this.d.a();
            this.d.setVisibility(8);
            this.e.a();
            this.e.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void i() {
        if (this.a.f == 0) {
            if (r()) {
                s();
                return;
            } else {
                t();
                return;
            }
        }
        if (r()) {
            u();
        } else {
            v();
        }
    }

    private boolean r() {
        AdInfo.AdMaterialInfo.MaterialFeature materialFeatureJ = com.kwad.sdk.core.response.a.a.J(this.g);
        return materialFeatureJ.height > materialFeatureJ.width;
    }

    private void s() {
        this.b.a(this.f, this.a.e, new com.kwad.sdk.reward.widget.tailframe.a() { // from class: com.kwad.sdk.reward.presenter.a.kwai.c.2
            @Override // com.kwad.sdk.reward.widget.tailframe.a
            public void a(boolean z) {
                c.this.b(z);
            }
        });
        this.b.setVisibility(0);
    }

    private void t() {
        this.c.a(this.f, this.a.e, new com.kwad.sdk.reward.widget.tailframe.a() { // from class: com.kwad.sdk.reward.presenter.a.kwai.c.3
            @Override // com.kwad.sdk.reward.widget.tailframe.a
            public void a(boolean z) {
                c.this.b(z);
            }
        });
        this.c.setVisibility(0);
    }

    private void u() {
        this.d.a(this.f, this.a.e, new com.kwad.sdk.reward.widget.tailframe.a() { // from class: com.kwad.sdk.reward.presenter.a.kwai.c.4
            @Override // com.kwad.sdk.reward.widget.tailframe.a
            public void a(boolean z) {
                c.this.b(z);
            }
        });
        this.d.setVisibility(0);
    }

    private void v() {
        this.e.a(this.f, this.a.e, new com.kwad.sdk.reward.widget.tailframe.a() { // from class: com.kwad.sdk.reward.presenter.a.kwai.c.5
            @Override // com.kwad.sdk.reward.widget.tailframe.a
            public void a(boolean z) {
                c.this.b(z);
            }
        });
        this.e.setVisibility(0);
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.b.setCallerContext(this.a);
        this.c.setCallerContext(this.a);
        this.d.setCallerContext(this.a);
        this.e.setCallerContext(this.a);
        this.f = this.a.g;
        this.g = com.kwad.sdk.core.response.a.d.j(this.f);
        this.h = this.a.o;
        this.a.a(this.k);
    }

    public void a(l.b bVar) {
        this.j = bVar;
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        e();
        this.a.b(this.k);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.b = (TailFramePortraitVertical) a(R.id.ksad_video_portrait_vertical);
        this.c = (TailFramePortraitHorizontal) a(R.id.ksad_video_portrait_horizontal);
        this.d = (TailFrameLandscapeVertical) a(R.id.ksad_video_landscape_vertical);
        this.e = (TailFrameLandscapeHorizontal) a(R.id.ksad_video_landscape_horizontal);
    }
}
