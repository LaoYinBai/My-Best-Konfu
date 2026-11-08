package com.baidu.mobads.sdk.api;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ScreenVideoAdListener {
    void onAdClick();

    void onAdClose(float f);

    void onAdFailed(String str);

    void onAdLoaded();

    void onAdShow();

    void onAdSkip(float f);

    void onVideoDownloadFailed();

    void onVideoDownloadSuccess();

    void playCompletion();
}
