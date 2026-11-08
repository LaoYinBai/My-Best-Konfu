package com.bytedance.msdk.core.corelogic;

import com.bytedance.msdk.api.AdError;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends AdError {
    public b(AdError adError) {
        super(adError.code, adError.message, adError.thirdSdkErrorCode, adError.thirdSdkErrorMessage);
    }

    public void a(String str) {
    }
}
