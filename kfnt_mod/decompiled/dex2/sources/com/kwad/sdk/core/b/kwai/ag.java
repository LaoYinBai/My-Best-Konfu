package com.kwad.sdk.core.b.kwai;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ag implements com.kwad.sdk.core.d<com.kwad.sdk.core.request.model.c> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.core.request.model.c cVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        cVar.a = jSONObject.optInt("cellId");
        cVar.b = jSONObject.optInt("lac");
        cVar.c = jSONObject.optInt("bsss");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.core.request.model.c cVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "cellId", cVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "lac", cVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "bsss", cVar.c);
        return jSONObject;
    }
}
