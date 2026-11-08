package com.kwad.sdk.reward.presenter.b;

import android.widget.FrameLayout;
import com.kwad.sdk.R;
import com.kwad.sdk.core.webview.jshandler.d;
import com.kwad.sdk.core.webview.jshandler.j;
import com.kwad.sdk.reward.a.f;
import com.kwad.sdk.utils.av;
import com.kwad.sdk.utils.bb;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends a {
    private int b;
    private com.kwad.sdk.reward.presenter.platdetail.actionbar.a c;
    private com.kwad.sdk.reward.presenter.platdetail.actionbar.b d;
    private boolean e;
    private boolean f;
    private f g = new f() { // from class: com.kwad.sdk.reward.presenter.b.c.1
        @Override // com.kwad.sdk.reward.a.f
        public void a() {
            c.this.v();
        }
    };

    public c(boolean z) {
        this.f = z;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void v() {
        FrameLayout frameLayout = (FrameLayout) this.a.j.findViewById(i());
        if (frameLayout != null) {
            frameLayout.setVisibility(8);
        }
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        this.b = m().findViewById(R.id.ksad_video_play_bar_h5).getVisibility();
        m().findViewById(R.id.ksad_video_play_bar_h5).setVisibility(8);
        super.a();
        this.a.a(this.g);
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected void a(d.a aVar) {
        float fC = bb.c(q());
        aVar.a = (int) ((av.n(q()) / fC) + 0.5f);
        aVar.b = (int) ((av.o(q()) / fC) + 0.5f);
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected void a(j.a aVar) {
        FrameLayout frameLayout = (FrameLayout) this.a.j.findViewById(i());
        if (frameLayout != null) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) frameLayout.getLayoutParams();
            layoutParams.height = bb.a(q(), aVar.a);
            layoutParams.leftMargin = bb.a(q(), aVar.b);
            layoutParams.rightMargin = bb.a(q(), aVar.c);
            layoutParams.bottomMargin = bb.a(q(), aVar.d);
            layoutParams.width = -1;
            frameLayout.setLayoutParams(layoutParams);
        }
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.b(this.g);
        if (this.e) {
            this.c.j();
            if (this.d != null) {
                this.d.j();
            }
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.c = new com.kwad.sdk.reward.presenter.platdetail.actionbar.a();
        if (this.f) {
            this.d = new com.kwad.sdk.reward.presenter.platdetail.actionbar.b();
            this.d.c(m());
        }
        this.c.c(m());
    }

    @Override // com.kwad.sdk.reward.presenter.b.a, com.kwad.sdk.mvp.Presenter
    protected void d_() {
        super.d_();
        if (this.e) {
            if (this.d != null) {
                this.d.k();
            }
            this.c.k();
        }
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected String e() {
        return "ksad-video-bottom-card.js";
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected int i() {
        return R.id.ksad_js_bottom;
    }

    @Override // com.kwad.sdk.reward.presenter.b.a
    protected void r() {
        this.e = true;
        m().findViewById(R.id.ksad_video_play_bar_h5).setVisibility(this.b);
        this.c.a(this.a);
        if (this.d != null) {
            this.d.a(this.a);
        }
    }
}
