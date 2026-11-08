package com.kwad.sdk.core.webview.kwai;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class i extends com.kwad.sdk.core.response.kwai.a implements com.kwad.sdk.core.b {
    public String a;
    public String b;
    public String c;

    public long a() {
        try {
            return new JSONObject(this.c).optLong("creativeId", -1L);
        } catch (Exception unused) {
            return -1L;
        }
    }
}
