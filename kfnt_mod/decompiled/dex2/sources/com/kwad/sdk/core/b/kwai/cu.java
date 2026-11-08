package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.p;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cu implements com.kwad.sdk.core.d<p.a> {
    @Override // com.kwad.sdk.core.d
    public void a(p.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt("status");
        aVar.b = jSONObject.optString("errorMsg");
        if (jSONObject.opt("errorMsg") == JSONObject.NULL) {
            aVar.b = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(p.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "status", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "errorMsg", aVar.b);
        return jSONObject;
    }
}
