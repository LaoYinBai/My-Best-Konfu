package com.bytedance.msdk.api.v2.ad.custom.base;

import com.bytedance.msdk.api.v2.ad.custom.GMCustomAdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface IGMCustomLoadAdCall {
    void callLoadFail(GMCustomAdError gMCustomAdError);

    void callLoadSuccess();
}
