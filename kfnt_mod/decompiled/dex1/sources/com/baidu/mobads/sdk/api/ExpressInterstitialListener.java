package com.baidu.mobads.sdk.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ExpressInterstitialListener {
    void onADExposed();

    void onADExposureFailed();

    void onADLoaded();

    void onAdClick();

    void onAdClose();

    void onAdFailed(int i, String str);

    void onLpClosed();

    void onNoAd(int i, String str);

    void onVideoDownloadFailed();

    void onVideoDownloadSuccess();
}
