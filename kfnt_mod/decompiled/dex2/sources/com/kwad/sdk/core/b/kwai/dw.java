package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdMatrixInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dw implements com.kwad.sdk.core.d<AdMatrixInfo.SplashInteractionInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdMatrixInfo.SplashInteractionInfo splashInteractionInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        splashInteractionInfo.interactiveStyle = jSONObject.optInt("interactiveStyle");
        splashInteractionInfo.shakeInfo = new AdMatrixInfo.ShakeInfo();
        splashInteractionInfo.shakeInfo.parseJson(jSONObject.optJSONObject("shakeInfo"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdMatrixInfo.SplashInteractionInfo splashInteractionInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "interactiveStyle", splashInteractionInfo.interactiveStyle);
        com.kwad.sdk.utils.t.a(jSONObject, "shakeInfo", splashInteractionInfo.shakeInfo);
        return jSONObject;
    }
}
