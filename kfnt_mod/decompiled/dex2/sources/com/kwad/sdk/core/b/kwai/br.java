package com.kwad.sdk.core.b.kwai;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class br implements com.kwad.sdk.core.d<com.kwad.sdk.c.kwai.a> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.c.kwai.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optString("Access-Control-Allow-Origin");
        if (jSONObject.opt("Access-Control-Allow-Origin") == JSONObject.NULL) {
            aVar.a = "";
        }
        aVar.b = jSONObject.optString("Timing-Allow-Origin");
        if (jSONObject.opt("Timing-Allow-Origin") == JSONObject.NULL) {
            aVar.b = "";
        }
        aVar.c = jSONObject.optString("content-type");
        if (jSONObject.opt("content-type") == JSONObject.NULL) {
            aVar.c = "";
        }
        aVar.d = jSONObject.optString("Date");
        if (jSONObject.opt("Date") == JSONObject.NULL) {
            aVar.d = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.c.kwai.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "Access-Control-Allow-Origin", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "Timing-Allow-Origin", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "content-type", aVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "Date", aVar.d);
        return jSONObject;
    }
}
