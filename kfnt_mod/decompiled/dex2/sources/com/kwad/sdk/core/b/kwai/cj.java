package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.b;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cj implements com.kwad.sdk.core.d<b.C0052b> {
    @Override // com.kwad.sdk.core.d
    public void a(b.C0052b c0052b, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        c0052b.a = jSONObject.optString("payload");
        if (jSONObject.opt("payload") == JSONObject.NULL) {
            c0052b.a = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(b.C0052b c0052b, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "payload", c0052b.a);
        return jSONObject;
    }
}
