package com.sigmob.windad;

import com.sigmob.sdk.common.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum WindConsentStatus {
    UNKNOWN(Constants.FAIL),
    ACCEPT("1"),
    DENIED("2");

    private String a;

    WindConsentStatus(String str) {
        this.a = str;
    }

    String a() {
        return this.a;
    }
}
