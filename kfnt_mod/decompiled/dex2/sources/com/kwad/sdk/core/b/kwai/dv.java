package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdMatrixInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dv implements com.kwad.sdk.core.d<AdMatrixInfo.SplashInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdMatrixInfo.SplashInfo splashInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        splashInfo.interactionInfo = new AdMatrixInfo.SplashInteractionInfo();
        splashInfo.interactionInfo.parseJson(jSONObject.optJSONObject("interactionInfo"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdMatrixInfo.SplashInfo splashInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "interactionInfo", splashInfo.interactionInfo);
        return jSONObject;
    }
}
