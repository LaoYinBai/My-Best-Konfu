package com.baidu.mobads.sdk.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface InterstitialAdListener {
    void onAdClick(InterstitialAd interstitialAd);

    void onAdDismissed();

    void onAdFailed(String str);

    void onAdPresent();

    void onAdReady();
}
