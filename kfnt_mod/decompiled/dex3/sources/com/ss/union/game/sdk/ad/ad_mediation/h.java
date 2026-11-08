package com.ss.union.game.sdk.ad.ad_mediation;

import android.app.Activity;
import android.view.ViewGroup;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h implements LGMediationAdBannerAd {
    private LGMediationAdBannerAd a;

    h(LGMediationAdBannerAd lGMediationAdBannerAd) {
        this.a = lGMediationAdBannerAd;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd
    public void show(Activity activity, int i, int i2) {
        if (this.a == null) {
            return;
        }
        this.a.show(activity, i, i2);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd
    public void show(ViewGroup viewGroup) {
        if (this.a == null) {
            return;
        }
        this.a.show(viewGroup);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd
    public void remove() {
        if (this.a == null) {
            return;
        }
        this.a.remove();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd
    public void setInteractionCallback(LGMediationAdBannerAd.InteractionCallback interactionCallback) {
        if (this.a == null || interactionCallback == null) {
            return;
        }
        this.a.setInteractionCallback(interactionCallback);
    }
}
