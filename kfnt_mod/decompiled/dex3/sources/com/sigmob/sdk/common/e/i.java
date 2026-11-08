package com.sigmob.sdk.common.e;

import com.umeng.message.util.HttpRequest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public enum i {
    LOCATION(HttpRequest.HEADER_LOCATION),
    USER_AGENT("User-Agent"),
    ACCEPT_LANGUAGE("Accept-Language");

    private final String d;

    i(String str) {
        this.d = str;
    }

    public String a() {
        return this.d;
    }
}
