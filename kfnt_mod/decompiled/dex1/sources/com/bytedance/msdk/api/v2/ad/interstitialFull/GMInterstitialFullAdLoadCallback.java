package com.bytedance.msdk.api.v2.ad.interstitialFull;

import androidx.annotation.NonNull;
import com.bytedance.msdk.api.AdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface GMInterstitialFullAdLoadCallback {
    void onInterstitialFullAdLoad();

    void onInterstitialFullCached();

    void onInterstitialFullLoadFail(@NonNull AdError adError);
}
