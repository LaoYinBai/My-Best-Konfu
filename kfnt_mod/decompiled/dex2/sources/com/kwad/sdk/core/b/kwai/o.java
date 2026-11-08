package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdMatrixInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class o implements com.kwad.sdk.core.d<AdMatrixInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdMatrixInfo adMatrixInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        adMatrixInfo.styles = new AdMatrixInfo.Styles();
        adMatrixInfo.styles.parseJson(jSONObject.optJSONObject("styles"));
        adMatrixInfo.adDataV2 = new AdMatrixInfo.AdDataV2();
        adMatrixInfo.adDataV2.parseJson(jSONObject.optJSONObject("adDataV2"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdMatrixInfo adMatrixInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "styles", adMatrixInfo.styles);
        com.kwad.sdk.utils.t.a(jSONObject, "adDataV2", adMatrixInfo.adDataV2);
        return jSONObject;
    }
}
