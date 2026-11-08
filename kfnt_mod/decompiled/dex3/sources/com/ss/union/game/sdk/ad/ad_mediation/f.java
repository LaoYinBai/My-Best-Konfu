package com.ss.union.game.sdk.ad.ad_mediation;

import com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService;
import com.ss.union.game.sdk.ad.ad_mediation.dto.LGMediationAdRewardVideoAdDTO;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class f implements LGMediationAdService.MediationRewardVideoAdListener {
    private LGMediationAdService.MediationRewardVideoAdListener a;
    private LGMediationAdRewardVideoAdDTO b;
    private j c;

    f(LGMediationAdRewardVideoAdDTO lGMediationAdRewardVideoAdDTO, LGMediationAdService.MediationRewardVideoAdListener mediationRewardVideoAdListener) {
        this.a = mediationRewardVideoAdListener;
        this.b = lGMediationAdRewardVideoAdDTO;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationRewardVideoAdListener
    public void onError(int i, String str) throws JSONException {
        if (this.a == null) {
            return;
        }
        this.a.onError(i, str);
        e.a("ad_load_callback", this.b.codeID, "reward", 0);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_REWARD_LOAD_SUCCESS, LGDetectionConstant.DetectionState.FAIL);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationRewardVideoAdListener
    public void onRewardVideoAdLoad(LGMediationAdRewardVideoAd lGMediationAdRewardVideoAd) throws JSONException {
        if (this.a == null) {
            return;
        }
        if (this.c == null) {
            this.c = new j(this.b.codeID, lGMediationAdRewardVideoAd);
        }
        this.a.onRewardVideoAdLoad(this.c);
        e.a("ad_load_callback", this.b.codeID, "reward", 1);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_REWARD_LOAD_SUCCESS, LGDetectionConstant.DetectionState.PASS);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.LGMediationAdService.MediationRewardVideoAdListener
    public void onRewardVideoCached(LGMediationAdRewardVideoAd lGMediationAdRewardVideoAd) {
        if (this.a == null) {
            return;
        }
        if (this.c == null) {
            this.c = new j(this.b.codeID, lGMediationAdRewardVideoAd);
        }
        this.a.onRewardVideoCached(this.c);
    }
}
