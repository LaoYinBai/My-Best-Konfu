package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdMatrixInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dq implements com.kwad.sdk.core.d<AdMatrixInfo.ShakeInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdMatrixInfo.ShakeInfo shakeInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        shakeInfo.subtitle = jSONObject.optString("subtitle");
        if (jSONObject.opt("subtitle") == JSONObject.NULL) {
            shakeInfo.subtitle = "";
        }
        shakeInfo.acceleration = jSONObject.optInt("acceleration");
        shakeInfo.clickDisabled = jSONObject.optBoolean("clickDisabled");
        shakeInfo.componentIndex = jSONObject.optInt("componentIndex");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdMatrixInfo.ShakeInfo shakeInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "subtitle", shakeInfo.subtitle);
        com.kwad.sdk.utils.t.a(jSONObject, "acceleration", shakeInfo.acceleration);
        com.kwad.sdk.utils.t.a(jSONObject, "clickDisabled", shakeInfo.clickDisabled);
        com.kwad.sdk.utils.t.a(jSONObject, "componentIndex", shakeInfo.componentIndex);
        return jSONObject;
    }
}
