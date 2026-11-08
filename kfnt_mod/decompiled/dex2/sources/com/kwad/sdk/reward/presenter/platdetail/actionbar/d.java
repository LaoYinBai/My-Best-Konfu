package com.kwad.sdk.reward.presenter.platdetail.actionbar;

import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.reward.d.g;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl;
import com.kwad.sdk.utils.w;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d extends g implements RewardActionBarControl.c {
    private ViewGroup b;
    private com.kwad.sdk.reward.d.g c;
    private KsLogoView d;
    private int e;
    private FrameLayout.LayoutParams f;
    private g.a g;

    public d(ViewGroup viewGroup, g.a aVar) {
        this.b = viewGroup;
        this.g = aVar;
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        this.a.n.a(this);
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.a.g);
        this.e = this.d.getVisibility();
        this.f = c.a(q(), adInfoJ, this.d, R.dimen.ksad_reward_order_logo_margin_bottom);
        this.c = new com.kwad.sdk.reward.d.g(this.b, new com.kwad.sdk.reward.d.a(q(), this.a) { // from class: com.kwad.sdk.reward.presenter.platdetail.actionbar.d.1
            @Override // com.kwad.sdk.reward.d.a, com.kwad.sdk.reward.d.b
            public void a() {
                d.this.a.a(d.this.q(), 10, 1);
            }

            @Override // com.kwad.sdk.reward.d.a, com.kwad.sdk.reward.d.b
            public void b() {
                d.this.a.a(d.this.q(), 10, 1);
            }
        });
        this.c.a(this.g);
        this.c.a(this.a.g);
        w.a(new com.kwad.sdk.widget.c(com.kwad.sdk.core.config.b.aL()), this.c.a());
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.d.setVisibility(this.e);
        if (this.f != null) {
            this.d.setLayoutParams(this.f);
        }
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.d = (KsLogoView) a(R.id.ksad_ad_label_play_bar);
    }

    @Override // com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl.c
    public void d() {
        this.b.setVisibility(0);
    }
}
