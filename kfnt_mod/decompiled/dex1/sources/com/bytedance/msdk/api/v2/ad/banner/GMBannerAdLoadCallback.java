package com.bytedance.msdk.api.v2.ad.banner;

import androidx.annotation.NonNull;
import com.bytedance.msdk.api.AdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface GMBannerAdLoadCallback {
    void onAdFailedToLoad(@NonNull AdError adError);

    void onAdLoaded();
}
