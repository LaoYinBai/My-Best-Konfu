package com.ss.union.game.sdk.ad.ad_mediation;

import com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdNativeBannerAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdBannerAd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c implements LGMediationAdService.MediationBannerAdListener {
    private LGMediationAdService.MediationBannerAdListener a;
    private LGMediationAdNativeBannerAdDTO b;
    private h c;

    public c(LGMediationAdNativeBannerAdDTO lGMediationAdNativeBannerAdDTO, LGMediationAdService.MediationBannerAdListener mediationBannerAdListener) {
        this.a = mediationBannerAdListener;
        this.b = lGMediationAdNativeBannerAdDTO;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationBannerAdListener
    public void onError(int i, String str) {
        if (this.a == null) {
            return;
        }
        this.a.onError(i, str);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationBannerAdListener
    public void onBannerAdLoad(LGMediationAdBannerAd lGMediationAdBannerAd) {
        if (this.a == null) {
            return;
        }
        if (this.c == null) {
            this.c = new h(lGMediationAdBannerAd);
        }
        this.a.onBannerAdLoad(this.c);
    }
}
