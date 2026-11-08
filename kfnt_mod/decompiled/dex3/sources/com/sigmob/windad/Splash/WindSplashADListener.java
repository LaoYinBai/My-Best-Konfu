package com.sigmob.windad.Splash;

import com.sigmob.windad.WindAdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindSplashADListener {
    void onSplashAdClicked();

    void onSplashAdFailToLoad(WindAdError windAdError, String str);

    void onSplashAdSuccessLoad();

    void onSplashAdSuccessPresent();

    void onSplashClosed();
}
