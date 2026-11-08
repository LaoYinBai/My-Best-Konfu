package com.tachikoma.core.component.network.delegate;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKBaseResponseInner extends BaseJsonCoreParse {
    public String allHeaderFields;
    public String body;
    public int statusCode;

    public String toJsonString() {
        return toJson().toString();
    }
}
