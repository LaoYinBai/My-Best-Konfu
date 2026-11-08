package com.ss.union.game.sdk.ad.ad_mediation;

import android.app.Activity;
import com.bytedance.msdk.api.TTAdConstant;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class j implements LGMediationAdRewardVideoAd {
    private LGMediationAdRewardVideoAd a;
    private String b;

    j(String str, LGMediationAdRewardVideoAd lGMediationAdRewardVideoAd) {
        this.b = str;
        this.a = lGMediationAdRewardVideoAd;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public void showRewardVideoAd(Activity activity) throws JSONException {
        if (this.a == null) {
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd ");
        this.a.showRewardVideoAd(activity);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_REWARD_SHOW, LGDetectionConstant.DetectionState.PASS);
        e.a(PointCategory.AD_SHOW, null, "reward", -1);
        com.ss.union.game.sdk.ad.a.a.a(this.b);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public void showRewardVideoAd(Activity activity, TTAdConstant.GroMoreRitScenes groMoreRitScenes, String str) throws JSONException {
        if (this.a == null) {
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showRewardVideoAd with scenes " + groMoreRitScenes + " customRitScenes " + str);
        this.a.showRewardVideoAd(activity, groMoreRitScenes, str);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_REWARD_SHOW, LGDetectionConstant.DetectionState.PASS);
        e.a(PointCategory.AD_SHOW, null, "reward", -1);
        com.ss.union.game.sdk.ad.a.a.a(this.b);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public void setInteractionCallback(LGMediationAdRewardVideoAd.InteractionCallback interactionCallback) {
        if (this.a == null || interactionCallback == null) {
            return;
        }
        this.a.setInteractionCallback(new a(this.b, interactionCallback));
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public boolean isReady() {
        if (this.a == null) {
            return false;
        }
        return this.a.isReady();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd
    public void destroy() {
        if (this.a == null) {
            return;
        }
        this.a.destroy();
    }

    static class a implements LGMediationAdRewardVideoAd.InteractionCallback {
        private String a;
        private LGMediationAdRewardVideoAd.InteractionCallback b;

        a(String str, LGMediationAdRewardVideoAd.InteractionCallback interactionCallback) {
            this.a = str;
            this.b = interactionCallback;
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd.InteractionCallback
        public void onRewardedAdShow() throws JSONException {
            this.b.onRewardedAdShow();
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_REWARD_SHOW_SUCCESS, LGDetectionConstant.DetectionState.PASS);
            e.a("ad_show_callback", null, "reward", 1);
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd.InteractionCallback
        public void onRewardedAdShowFail(int i, String str) {
            this.b.onRewardedAdShowFail(i, str);
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd.InteractionCallback
        public void onRewardClick() {
            this.b.onRewardClick();
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd.InteractionCallback
        public void onRewardedAdClosed() throws JSONException {
            this.b.onRewardedAdClosed();
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_REWARD_CLOSE, LGDetectionConstant.DetectionState.PASS);
            e.a("ad_close", null, "reward", -1);
            com.ss.union.game.sdk.ad.a.a.b(this.a);
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd.InteractionCallback
        public void onVideoComplete() {
            this.b.onVideoComplete();
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd.InteractionCallback
        public void onVideoError() throws JSONException {
            this.b.onVideoError();
            e.a("ad_show_callback", null, "reward", 0);
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd.InteractionCallback
        public void onRewardVerify(boolean z, float f, String str) {
            this.b.onRewardVerify(z, f, str);
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdRewardVideoAd.InteractionCallback
        public void onSkippedVideo() {
            this.b.onSkippedVideo();
        }
    }
}
