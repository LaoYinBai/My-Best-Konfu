package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdStyleInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bu implements com.kwad.sdk.core.d<AdStyleInfo.PlayEndInfo.HorizontalPatchAdInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdStyleInfo.PlayEndInfo.HorizontalPatchAdInfo horizontalPatchAdInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        horizontalPatchAdInfo.patchCardUrl = jSONObject.optString("patchCardUrl");
        if (jSONObject.opt("patchCardUrl") == JSONObject.NULL) {
            horizontalPatchAdInfo.patchCardUrl = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdStyleInfo.PlayEndInfo.HorizontalPatchAdInfo horizontalPatchAdInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "patchCardUrl", horizontalPatchAdInfo.patchCardUrl);
        return jSONObject;
    }
}
