package com.ss.union.game.sdk.ad.ad_mediation.type;

import android.app.Activity;
import androidx.annotation.MainThread;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGMediationAdSplashAd extends LGMediationAdBaseAd {

    public interface InteractionCallback {
        void onAdClicked();

        void onAdDismiss();

        void onAdShow();

        void onAdShowFail(int i, String str);

        void onAdSkip();
    }

    void destroy();

    void setInteractionCallback(InteractionCallback interactionCallback);

    @MainThread
    void showAd(Activity activity);
}
