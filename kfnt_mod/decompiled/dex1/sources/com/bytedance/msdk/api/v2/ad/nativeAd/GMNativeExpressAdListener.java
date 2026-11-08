package com.bytedance.msdk.api.v2.ad.nativeAd;

import android.view.View;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface GMNativeExpressAdListener extends GMNativeAdListener {
    void onRenderFail(View view, String str, int i);

    void onRenderSuccess(float f, float f2);
}
