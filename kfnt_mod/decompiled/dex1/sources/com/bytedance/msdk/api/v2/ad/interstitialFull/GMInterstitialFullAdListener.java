package com.bytedance.msdk.api.v2.ad.interstitialFull;

import androidx.annotation.NonNull;
import com.bytedance.msdk.adapter.listener.ITTAdatperCallback;
import com.bytedance.msdk.api.AdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface GMInterstitialFullAdListener extends ITTAdatperCallback {
    void onAdLeftApplication();

    void onAdOpened();

    void onInterstitialFullClick();

    void onInterstitialFullClosed();

    void onInterstitialFullShow();

    void onInterstitialFullShowFail(@NonNull AdError adError);

    void onSkippedVideo();

    void onVideoComplete();

    void onVideoError();
}
