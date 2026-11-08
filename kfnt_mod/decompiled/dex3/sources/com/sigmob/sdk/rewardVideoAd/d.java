package com.sigmob.sdk.rewardVideoAd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface d {
    void onVideoAdClicked(String str);

    void onVideoAdClosed(b bVar, String str);

    void onVideoAdLoadError(int i, String str, String str2);

    void onVideoAdLoadSuccess(String str);

    void onVideoAdPlayComplete(String str);

    void onVideoAdPlayEnd(String str);

    void onVideoAdPlayError(int i, String str, String str2);

    void onVideoAdPlayStart(String str);

    void onVideoAdPreLoadFail(int i, String str, String str2);

    void onVideoAdPreLoadSuccess(String str);
}
