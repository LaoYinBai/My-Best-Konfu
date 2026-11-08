package com.qq.e.ads.interstitial3;

import com.qq.e.comm.util.AdError;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public interface ExpressInterstitialAdListener {
    void onAdLoaded();

    void onClick();

    void onClose();

    void onError(AdError adError);

    void onExpose();

    void onRenderFail();

    void onRenderSuccess();

    void onShow();

    void onVideoCached();

    void onVideoComplete();
}
