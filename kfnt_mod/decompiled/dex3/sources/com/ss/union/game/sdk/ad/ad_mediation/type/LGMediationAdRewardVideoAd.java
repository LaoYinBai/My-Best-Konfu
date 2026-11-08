package com.ss.union.game.sdk.ad.ad_mediation.type;

import android.app.Activity;
import androidx.annotation.MainThread;
import com.bytedance.msdk.api.TTAdConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGMediationAdRewardVideoAd extends LGMediationAdBaseAd {

    public interface InteractionCallback {
        void onRewardClick();

        void onRewardVerify(boolean z, float f, String str);

        void onRewardedAdClosed();

        void onRewardedAdShow();

        void onRewardedAdShowFail(int i, String str);

        void onSkippedVideo();

        void onVideoComplete();

        void onVideoError();
    }

    void destroy();

    boolean isReady();

    void setInteractionCallback(InteractionCallback interactionCallback);

    @MainThread
    void showRewardVideoAd(Activity activity);

    @MainThread
    void showRewardVideoAd(Activity activity, TTAdConstant.GroMoreRitScenes groMoreRitScenes, String str);
}
