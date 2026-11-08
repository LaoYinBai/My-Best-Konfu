package com.kwad.sdk.core.b.kwai;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ck implements com.kwad.sdk.core.d<com.kwad.sdk.c.kwai.b> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.c.kwai.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        bVar.a = jSONObject.optInt("Status");
        bVar.b = jSONObject.optString("Content-Encoding");
        if (jSONObject.opt("Content-Encoding") == JSONObject.NULL) {
            bVar.b = "";
        }
        bVar.c = jSONObject.optString("Cache-Control");
        if (jSONObject.opt("Cache-Control") == JSONObject.NULL) {
            bVar.c = "";
        }
        bVar.d = jSONObject.optString("Content-Type");
        if (jSONObject.opt("Content-Type") == JSONObject.NULL) {
            bVar.d = "";
        }
        bVar.e = new com.kwad.sdk.c.kwai.a();
        bVar.e.parseJson(jSONObject.optJSONObject("headers"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.c.kwai.b bVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "Status", bVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "Content-Encoding", bVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "Cache-Control", bVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "Content-Type", bVar.d);
        com.kwad.sdk.utils.t.a(jSONObject, "headers", bVar.e);
        return jSONObject;
    }
}
