package com.kwad.sdk.core.b.kwai;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cr implements com.kwad.sdk.core.d<com.kwad.sdk.c.kwai.c> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.c.kwai.c cVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        cVar.a = jSONObject.optString("sceneId");
        if (jSONObject.opt("sceneId") == JSONObject.NULL) {
            cVar.a = "";
        }
        cVar.b = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("packages");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                com.kwad.sdk.c.kwai.d dVar = new com.kwad.sdk.c.kwai.d();
                dVar.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                cVar.b.add(dVar);
            }
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.c.kwai.c cVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "sceneId", cVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "packages", cVar.b);
        return jSONObject;
    }
}
