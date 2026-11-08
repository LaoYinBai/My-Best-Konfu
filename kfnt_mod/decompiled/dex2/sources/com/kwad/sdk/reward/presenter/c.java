package com.kwad.sdk.reward.presenter;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import com.kwad.sdk.R;
import com.kwad.sdk.core.response.model.AdInfo;
import com.kwad.sdk.mvp.Presenter;
import com.kwad.sdk.reward.d.g;
import com.kwad.sdk.utils.ag;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends com.kwad.sdk.reward.g {
    private AdInfo b;

    public c(AdInfo adInfo, ViewGroup viewGroup) {
        View viewInflate;
        this.b = adInfo;
        if (!com.kwad.sdk.core.response.a.a.aH(this.b)) {
            if (com.kwad.sdk.core.response.a.a.aI(this.b)) {
                final com.kwad.sdk.reward.kwai.c cVar = new com.kwad.sdk.reward.kwai.c();
                a((Presenter) cVar);
                a(new com.kwad.sdk.reward.presenter.platdetail.actionbar.d((ViewGroup) ((ViewStub) viewGroup.findViewById(R.id.ksad_reward_order_card)).inflate(), new g.a() { // from class: com.kwad.sdk.reward.presenter.c.1
                    @Override // com.kwad.sdk.reward.d.g.a
                    public void a() {
                        cVar.e();
                    }
                }));
                return;
            }
            return;
        }
        boolean zAA = com.kwad.sdk.core.response.a.a.aA(this.b);
        ViewStub viewStub = (ViewStub) viewGroup.findViewById(zAA ? R.id.ksad_reward_followed_card : R.id.ksad_reward_follow_card);
        if (viewStub == null) {
            viewInflate = viewGroup.findViewById(zAA ? R.id.ksad_reward_follow_root : R.id.ksad_reward_followed_root);
        } else {
            viewInflate = viewStub.inflate();
        }
        a(new com.kwad.sdk.reward.presenter.platdetail.actionbar.c((ViewGroup) viewInflate));
    }

    @Override // com.kwad.sdk.reward.g, com.kwad.sdk.mvp.Presenter
    protected void a() {
        super.a();
        int i = 0;
        if (!(com.kwad.sdk.core.response.a.a.az(this.b) || com.kwad.sdk.core.response.a.a.aF(this.b)) || this.a.g.mAdScene == null) {
            return;
        }
        if (!ag.a(q(), "com.smile.gifmaker") && ag.a(q(), "com.kuaishou.nebula")) {
            i = 3;
        }
        String backUrl = this.a.g.mAdScene.getBackUrl();
        String strAJ = com.kwad.sdk.core.response.a.a.aJ(this.b);
        if (TextUtils.isEmpty(backUrl)) {
            backUrl = "";
        }
        this.b.adConversionInfo.deeplinkUrl = com.kwad.sdk.ec.a.a.a(strAJ, i, backUrl);
    }
}
