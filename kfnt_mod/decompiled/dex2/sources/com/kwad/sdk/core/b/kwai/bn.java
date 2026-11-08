package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bn implements com.kwad.sdk.core.d<AdInfo.H5Config> {
    @Override // com.kwad.sdk.core.d
    public void a(AdInfo.H5Config h5Config, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        h5Config.apiMisTouch = jSONObject.optInt("apiMisTouch");
        h5Config.apiAdTag = jSONObject.optInt("apiAdTag");
        h5Config.apiBreathLamp = jSONObject.optInt("apiBreathLamp");
        h5Config.tagTip = jSONObject.optString("tagTip");
        if (jSONObject.opt("tagTip") == JSONObject.NULL) {
            h5Config.tagTip = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdInfo.H5Config h5Config, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "apiMisTouch", h5Config.apiMisTouch);
        com.kwad.sdk.utils.t.a(jSONObject, "apiAdTag", h5Config.apiAdTag);
        com.kwad.sdk.utils.t.a(jSONObject, "apiBreathLamp", h5Config.apiBreathLamp);
        com.kwad.sdk.utils.t.a(jSONObject, "tagTip", h5Config.tagTip);
        return jSONObject;
    }
}
