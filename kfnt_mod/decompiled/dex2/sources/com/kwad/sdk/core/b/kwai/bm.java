package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdMatrixInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bm implements com.kwad.sdk.core.d<AdMatrixInfo.FullPageActionBarInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdMatrixInfo.FullPageActionBarInfo fullPageActionBarInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        fullPageActionBarInfo.maxTimeOut = jSONObject.optLong("maxTimeOut");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdMatrixInfo.FullPageActionBarInfo fullPageActionBarInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "maxTimeOut", fullPageActionBarInfo.maxTimeOut);
        return jSONObject;
    }
}
