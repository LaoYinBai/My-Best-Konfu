package com.kwad.sdk.core.b.kwai;

import com.mbridge.msdk.foundation.entity.CampaignEx;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dp implements com.kwad.sdk.core.d<com.kwad.sdk.collector.kwai.e> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.collector.kwai.e eVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        eVar.a = jSONObject.optInt("sensorType");
        eVar.c = jSONObject.optLong(CampaignEx.JSON_KEY_TIMESTAMP);
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.collector.kwai.e eVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "sensorType", eVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, CampaignEx.JSON_KEY_TIMESTAMP, eVar.c);
        return jSONObject;
    }
}
