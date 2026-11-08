package com.sigmob.windad.interstitial;

import com.sigmob.windad.WindAdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public interface WindInterstitialAdListener {
    void onInterstitialAdClicked(String str);

    void onInterstitialAdClosed(String str);

    void onInterstitialAdLoadError(WindAdError windAdError, String str);

    void onInterstitialAdLoadSuccess(String str);

    void onInterstitialAdPlayEnd(String str);

    void onInterstitialAdPlayError(WindAdError windAdError, String str);

    void onInterstitialAdPlayStart(String str);

    void onInterstitialAdPreLoadFail(String str);

    void onInterstitialAdPreLoadSuccess(String str);
}
