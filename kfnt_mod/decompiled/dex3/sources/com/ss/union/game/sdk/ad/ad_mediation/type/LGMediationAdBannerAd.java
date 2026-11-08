package com.ss.union.game.sdk.ad.ad_mediation.type;

import android.app.Activity;
import android.view.ViewGroup;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface LGMediationAdBannerAd extends LGMediationAdBaseAd {

    public interface InteractionCallback {
        void onAdClicked();

        void onAdClosed();

        void onAdLeftApplication();

        void onAdOpened();

        void onAdShow();

        void onAdShowFail(int i, String str);
    }

    void remove();

    void setInteractionCallback(InteractionCallback interactionCallback);

    void show(Activity activity, int i, int i2);

    void show(ViewGroup viewGroup);
}
