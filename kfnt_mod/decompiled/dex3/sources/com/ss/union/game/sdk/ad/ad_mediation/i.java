package com.ss.union.game.sdk.ad.ad_mediation;

import android.app.Activity;
import com.bytedance.msdk.api.TTAdConstant;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class i implements LGMediationAdFullScreenVideoAd {
    private LGMediationAdFullScreenVideoAd a;
    private String b;

    i(String str, LGMediationAdFullScreenVideoAd lGMediationAdFullScreenVideoAd) {
        this.b = str;
        this.a = lGMediationAdFullScreenVideoAd;
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public void showFullScreenVideoAd(Activity activity) throws JSONException {
        if (this.a == null) {
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd ");
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_SHOW, LGDetectionConstant.DetectionState.PASS);
        this.a.showFullScreenVideoAd(activity);
        e.a(PointCategory.AD_SHOW, null, "full", -1);
        com.ss.union.game.sdk.ad.a.a.a(this.b);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public void showFullScreenVideoAd(Activity activity, TTAdConstant.GroMoreRitScenes groMoreRitScenes, String str) throws JSONException {
        if (this.a == null) {
            return;
        }
        com.ss.union.game.sdk.ad.ad_mediation.d.b.a("showFullScreenVideoAd with scenes " + groMoreRitScenes + " customRitScenes " + str);
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_SHOW, LGDetectionConstant.DetectionState.PASS);
        this.a.showFullScreenVideoAd(activity, groMoreRitScenes, str);
        e.a(PointCategory.AD_SHOW, null, "full", -1);
        com.ss.union.game.sdk.ad.a.a.a(this.b);
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public void setInteractionCallback(LGMediationAdFullScreenVideoAd.InteractionCallback interactionCallback) {
        if (this.a == null || interactionCallback == null) {
            return;
        }
        this.a.setInteractionCallback(new a(this.b, interactionCallback));
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public boolean isReady() {
        if (this.a == null) {
            return false;
        }
        return this.a.isReady();
    }

    @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd
    public void destroy() {
        if (this.a == null) {
            return;
        }
        this.a.destroy();
    }

    static class a implements LGMediationAdFullScreenVideoAd.InteractionCallback {
        private String a;
        private LGMediationAdFullScreenVideoAd.InteractionCallback b;

        a(String str, LGMediationAdFullScreenVideoAd.InteractionCallback interactionCallback) {
            this.a = str;
            this.b = interactionCallback;
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd.InteractionCallback
        public void onFullVideoAdShow() throws JSONException {
            this.b.onFullVideoAdShow();
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_SHOW_SUCCESS, LGDetectionConstant.DetectionState.PASS);
            e.a("ad_show_callback", null, "full", 1);
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd.InteractionCallback
        public void onFullVideoAdShowFail(int i, String str) {
            this.b.onFullVideoAdShowFail(i, str);
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd.InteractionCallback
        public void onFullVideoAdClick() {
            this.b.onFullVideoAdClick();
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd.InteractionCallback
        public void onFullVideoAdClosed() throws JSONException {
            this.b.onFullVideoAdClosed();
            LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.AD, LGDetectionConstant.AdItem.DETECTION_ID_FULL_SCREEN_CLOSE, LGDetectionConstant.DetectionState.PASS);
            e.a("ad_close", null, "full", -1);
            com.ss.union.game.sdk.ad.a.a.b(this.a);
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd.InteractionCallback
        public void onVideoComplete() {
            this.b.onVideoComplete();
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd.InteractionCallback
        public void onVideoError() {
            this.b.onVideoError();
        }

        @Override // com.ss.union.game.sdk.ad.ad_mediation.type.LGMediationAdFullScreenVideoAd.InteractionCallback
        public void onSkippedVideo() {
            this.b.onSkippedVideo();
        }
    }
}
