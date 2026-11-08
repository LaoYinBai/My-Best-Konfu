package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.n;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cc implements com.kwad.sdk.core.d<n.a> {
    @Override // com.kwad.sdk.core.d
    public void a(n.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optString("adTemplate");
        if (jSONObject.opt("adTemplate") == JSONObject.NULL) {
            aVar.a = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(n.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "adTemplate", aVar.a);
        return jSONObject;
    }
}
