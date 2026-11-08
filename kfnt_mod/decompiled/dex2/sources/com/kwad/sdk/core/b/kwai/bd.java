package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.kwai.h;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bd implements com.kwad.sdk.core.d<h.b> {
    @Override // com.kwad.sdk.core.d
    public void a(h.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        bVar.a = jSONObject.optInt("status");
        bVar.b = jSONObject.optInt("progress");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(h.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "status", bVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "progress", bVar.b);
        return jSONObject;
    }
}
