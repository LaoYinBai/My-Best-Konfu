package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdStyleInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bl implements com.kwad.sdk.core.d<AdStyleInfo.FeedAdInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdStyleInfo.FeedAdInfo feedAdInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        feedAdInfo.templateConfig = jSONObject.optString("templateConfig");
        if (jSONObject.opt("templateConfig") == JSONObject.NULL) {
            feedAdInfo.templateConfig = "";
        }
        feedAdInfo.heightRatio = jSONObject.optDouble("heightRatio");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdStyleInfo.FeedAdInfo feedAdInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "templateConfig", feedAdInfo.templateConfig);
        com.kwad.sdk.utils.t.a(jSONObject, "heightRatio", feedAdInfo.heightRatio);
        return jSONObject;
    }
}
