package com.kwad.sdk.core.b.kwai;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dt implements com.kwad.sdk.core.d<com.kwad.sdk.collector.kwai.f> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.collector.kwai.f fVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        fVar.a = jSONObject.optInt("phoneCount");
        fVar.b = jSONObject.optInt("activePhoneCount");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.collector.kwai.f fVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "phoneCount", fVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "activePhoneCount", fVar.b);
        return jSONObject;
    }
}
