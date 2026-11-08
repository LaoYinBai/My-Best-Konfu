package com.ss.union.game.sdk.ad.ad_mediation.type;

import android.app.Activity;
import androidx.annotation.MainThread;
import com.bytedance.msdk.api.TTAdConstant;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGMediationAdFullScreenVideoAd extends LGMediationAdBaseAd {

    public interface InteractionCallback {
        void onFullVideoAdClick();

        void onFullVideoAdClosed();

        void onFullVideoAdShow();

        void onFullVideoAdShowFail(int i, String str);

        void onSkippedVideo();

        void onVideoComplete();

        void onVideoError();
    }

    void destroy();

    boolean isReady();

    void setInteractionCallback(InteractionCallback interactionCallback);

    @MainThread
    void showFullScreenVideoAd(Activity activity);

    @MainThread
    void showFullScreenVideoAd(Activity activity, TTAdConstant.GroMoreRitScenes groMoreRitScenes, String str);
}
