package com.bytedance.msdk.api.v2.ad.custom.nativeAd;

import android.view.View;
import com.bytedance.msdk.api.v2.ad.custom.GMCustomAdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IGMCustomNativeEvent {
    void callNativeAdClick();

    void callNativeAdShow();

    void callNativeRenderFail(View view, String str, int i);

    void callNativeRenderSuccess(float f, float f2);

    void callNativeVideoCompleted();

    void callNativeVideoError(GMCustomAdError gMCustomAdError);

    void callNativeVideoPause();

    void callNativeVideoResume();

    void callNativeVideoStart();
}
