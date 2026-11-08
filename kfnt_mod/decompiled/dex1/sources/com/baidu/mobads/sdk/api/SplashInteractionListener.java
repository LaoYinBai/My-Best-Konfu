package com.baidu.mobads.sdk.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface SplashInteractionListener extends SplashAdListener {
    void onAdCacheFailed();

    void onAdCacheSuccess();

    void onAdClick();

    void onAdDismissed();

    void onAdPresent();

    void onLpClosed();
}
