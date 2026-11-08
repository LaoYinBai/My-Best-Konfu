package com.ss.union.game.sdk.ad.ad_mediation;

import com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdSplashAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdSplashAd;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class g implements LGMediationAdService.MediationSplashAdListener {
    private LGMediationAdSplashAdDTO a;
    private LGMediationAdService.MediationSplashAdListener b;
    private l c;

    public g(LGMediationAdSplashAdDTO lGMediationAdSplashAdDTO, LGMediationAdService.MediationSplashAdListener mediationSplashAdListener) {
        this.a = lGMediationAdSplashAdDTO;
        this.b = mediationSplashAdListener;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationSplashAdListener
    public void onError(int i, String str) {
        if (this.b == null) {
            return;
        }
        this.b.onError(i, str);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_SPLASH_LOAD_SUCCESS, LGDetectionConstant.DetectionState.FAIL);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationSplashAdListener
    public void onTimeout() {
        if (this.b == null) {
            return;
        }
        this.b.onTimeout();
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_SPLASH_LOAD_SUCCESS, LGDetectionConstant.DetectionState.FAIL);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationSplashAdListener
    public void onSplashAdLoad(LGMediationAdSplashAd lGMediationAdSplashAd) {
        if (this.b == null) {
            return;
        }
        if (this.c == null) {
            this.c = new l(this.a.codeID, lGMediationAdSplashAd);
        }
        this.b.onSplashAdLoad(this.c);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_SPLASH_LOAD_SUCCESS, LGDetectionConstant.DetectionState.PASS);
    }
}
