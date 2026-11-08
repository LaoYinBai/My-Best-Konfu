package com.tachikoma.core.component.network;

import com.sigmob.sdk.common.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum NetworkType {
    API("api"),
    LOG("log"),
    UPLOAD("upload"),
    PAY("pay"),
    PAYGATEWAY("payGateway"),
    HTTPS(Constants.HTTPS),
    LIVE("live"),
    IM("IM"),
    PUSH("push"),
    AD("ad"),
    MERCHANT("merchant"),
    GZONE("gzone"),
    ZT("zt");

    private final String text;

    NetworkType(String str) {
        this.text = str;
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.text;
    }
}
