package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.request.model.g;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ab implements com.kwad.sdk.core.d<g.a> {
    @Override // com.kwad.sdk.core.d
    public void a(g.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt("streamType");
        aVar.b = jSONObject.optInt("maxVolume");
        aVar.c = jSONObject.optInt("minVolume");
        aVar.d = jSONObject.optInt("currentVolume");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(g.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "streamType", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "maxVolume", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "minVolume", aVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "currentVolume", aVar.d);
        return jSONObject;
    }
}
