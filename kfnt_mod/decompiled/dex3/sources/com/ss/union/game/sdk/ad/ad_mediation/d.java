package com.ss.union.game.sdk.ad.ad_mediation;

import com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdFullScreenVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class d implements LGMediationAdService.MediationFullScreenVideoAdListener {
    private LGMediationAdService.MediationFullScreenVideoAdListener a;
    private LGMediationAdFullScreenVideoAdDTO b;
    private i c;

    d(LGMediationAdFullScreenVideoAdDTO lGMediationAdFullScreenVideoAdDTO, LGMediationAdService.MediationFullScreenVideoAdListener mediationFullScreenVideoAdListener) {
        this.a = mediationFullScreenVideoAdListener;
        this.b = lGMediationAdFullScreenVideoAdDTO;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationFullScreenVideoAdListener
    public void onError(int i, String str) throws JSONException {
        if (this.a == null) {
            return;
        }
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_LOAD_SUCCESS, LGDetectionConstant.DetectionState.FAIL);
        e.a("ad_load_callback", this.b.codeID, "full", 0);
        this.a.onError(i, str);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationFullScreenVideoAdListener
    public void onFullVideoAdLoad(LGMediationAdFullScreenVideoAd lGMediationAdFullScreenVideoAd) throws JSONException {
        if (this.a == null) {
            return;
        }
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_LOAD_SUCCESS, LGDetectionConstant.DetectionState.PASS);
        e.a("ad_load_callback", this.b.codeID, "full", 1);
        if (this.c == null) {
            this.c = new i(this.b.codeID, lGMediationAdFullScreenVideoAd);
        }
        this.a.onFullVideoAdLoad(this.c);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationFullScreenVideoAdListener
    public void onFullVideoCached(LGMediationAdFullScreenVideoAd lGMediationAdFullScreenVideoAd) {
        if (this.a == null) {
            return;
        }
        if (this.c == null) {
            this.c = new i(this.b.codeID, lGMediationAdFullScreenVideoAd);
        }
        this.a.onFullVideoCached(this.c);
    }
}
