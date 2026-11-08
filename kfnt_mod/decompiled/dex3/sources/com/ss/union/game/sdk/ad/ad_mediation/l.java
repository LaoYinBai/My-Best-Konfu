package com.ss.union.game.sdk.ad.ad_mediation;

import android.app.Activity;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class l implements LGMediationAdSplashAd {
    LGMediationAdSplashAd a;
    private String b;

    public l(String str, LGMediationAdSplashAd lGMediationAdSplashAd) {
        this.b = str;
        this.a = lGMediationAdSplashAd;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd
    public void showAd(Activity activity) {
        if (this.a == null) {
            return;
        }
        this.a.showAd(activity);
        com.ss.union.game.sdk.ad.a.a.a(this.b);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd
    public void setInteractionCallback(LGMediationAdSplashAd.InteractionCallback interactionCallback) {
        if (this.a == null || interactionCallback == null) {
            return;
        }
        this.a.setInteractionCallback(interactionCallback);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd
    public void destroy() {
        if (this.a == null) {
            return;
        }
        this.a.destroy();
        com.ss.union.game.sdk.ad.a.a.b(this.b);
    }
}
