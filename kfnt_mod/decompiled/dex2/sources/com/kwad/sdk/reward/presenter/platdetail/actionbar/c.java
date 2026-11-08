package com.kwad.sdk.reward.presenter.platdetail.actionbar;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.core.response.model.AdTemplate;
import com.kwad.sdk.reward.d.e;
import com.kwad.sdk.reward.d.f;
import com.kwad.sdk.reward.g;
import com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl;
import com.kwad.sdk.utils.af;
import com.kwad.sdk.utils.w;
import com.kwad.sdk.widget.KsAuthorIconView;
import com.kwad.sdk.widget.KsLogoView;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends g implements View.OnClickListener, RewardActionBarControl.a {
    private KsAuthorIconView b;
    private KsLogoView c;
    private ViewGroup d;
    private com.kwad.sdk.reward.d.d e;
    private int f;
    private FrameLayout.LayoutParams g;

    public c(ViewGroup viewGroup) {
        this.d = viewGroup;
    }

    public static FrameLayout.LayoutParams a(Context context, AdInfo adInfo, KsLogoView ksLogoView, int i) {
        FrameLayout.LayoutParams layoutParamsA = null;
        if (ksLogoView != null && context != null) {
            ViewGroup.LayoutParams layoutParams = ksLogoView.getLayoutParams();
            if (!(layoutParams instanceof FrameLayout.LayoutParams)) {
                return null;
            }
            FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) layoutParams;
            layoutParamsA = a(layoutParams2);
            ksLogoView.setVisibility(0);
            boolean z = (!af.e(context) && (com.kwad.sdk.core.response.a.a.aH(adInfo) || com.kwad.sdk.core.response.a.a.aI(adInfo))) || (af.e(context) && com.kwad.sdk.core.response.a.a.aH(adInfo) && com.kwad.sdk.core.response.a.a.aA(adInfo));
            layoutParams2.gravity = 85;
            if (z) {
                i = R.dimen.ksad_reward_follow_card_margin;
            }
            layoutParams2.bottomMargin = context.getResources().getDimensionPixelOffset(i);
            layoutParams2.rightMargin = context.getResources().getDimensionPixelOffset(R.dimen.ksad_reward_follow_card_margin);
            ksLogoView.setLayoutParams(layoutParams2);
        }
        return layoutParamsA;
    }

    public static FrameLayout.LayoutParams a(FrameLayout.LayoutParams layoutParams) {
        FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams((ViewGroup.MarginLayoutParams) layoutParams);
        layoutParams2.gravity = layoutParams.gravity;
        return layoutParams2;
    }

    private void a(AdTemplate adTemplate, com.kwad.sdk.widget.c cVar) {
        this.b.setVisibility(0);
        this.b.a(adTemplate);
        this.b.a(com.kwad.sdk.core.config.b.aK(), cVar);
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        AdInfo adInfoJ = com.kwad.sdk.core.response.a.d.j(this.a.g);
        com.kwad.sdk.widget.c cVar = new com.kwad.sdk.widget.c(com.kwad.sdk.core.config.b.aJ());
        boolean z = com.kwad.sdk.core.config.b.aH() == 2;
        this.f = this.c.getVisibility();
        this.g = a(q(), adInfoJ, this.c, R.dimen.ksad_reward_follow_logo_margin_bottom);
        if (z) {
            a(this.a.g, cVar);
        }
        this.a.n.a(this);
        boolean zAA = com.kwad.sdk.core.response.a.a.aA(adInfoJ);
        com.kwad.sdk.reward.d.a aVar = new com.kwad.sdk.reward.d.a(q(), this.a);
        this.e = zAA ? new f(this.d, aVar) : new e(this.d, z, aVar);
        w.a(cVar, this.e.a());
        this.e.a(this.a.g);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c() {
        super.c();
        this.a.n.a((RewardActionBarControl.a) null);
        this.c.setVisibility(this.f);
        if (this.g != null) {
            this.c.setLayoutParams(this.g);
        }
        this.b.setVisibility(8);
    }

    @Override // com.kwad.sdk.mvp.Presenter
    protected void c_() {
        super.c_();
        this.c = (KsLogoView) a(R.id.ksad_ad_label_play_bar);
        this.b = (KsAuthorIconView) a(R.id.ksad_reward_author_view);
        this.b.setOnClickListener(this);
    }

    @Override // com.kwad.sdk.reward.presenter.platdetail.actionbar.RewardActionBarControl.a
    public void d() {
        this.d.setVisibility(0);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        if (view.equals(this.b)) {
            this.a.a(q(), 1, 1);
        }
    }
}
