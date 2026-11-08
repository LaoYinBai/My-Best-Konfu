package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.config.item.i;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ca implements com.kwad.sdk.core.d<i.a> {
    @Override // com.kwad.sdk.core.d
    public void a(i.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt("horizontalShowDuration", new Integer("180000").intValue());
        aVar.b = jSONObject.optInt("verticalShowDuration", new Integer("90000").intValue());
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(i.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "horizontalShowDuration", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "verticalShowDuration", aVar.b);
        return jSONObject;
    }
}
