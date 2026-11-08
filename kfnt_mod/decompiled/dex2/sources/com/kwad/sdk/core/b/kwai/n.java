package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n implements com.kwad.sdk.core.d<AdInfo.AdMaterialInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdInfo.AdMaterialInfo adMaterialInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        adMaterialInfo.materialType = jSONObject.optInt("materialType", new Integer("2").intValue());
        adMaterialInfo.videoVoice = jSONObject.optBoolean("videoVoice", new Boolean("false").booleanValue());
        adMaterialInfo.materialFeatureList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("materialFeature");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                AdInfo.AdMaterialInfo.MaterialFeature materialFeature = new AdInfo.AdMaterialInfo.MaterialFeature();
                materialFeature.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                adMaterialInfo.materialFeatureList.add(materialFeature);
            }
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdInfo.AdMaterialInfo adMaterialInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "materialType", adMaterialInfo.materialType);
        com.kwad.sdk.utils.t.a(jSONObject, "videoVoice", adMaterialInfo.videoVoice);
        com.kwad.sdk.utils.t.a(jSONObject, "materialFeature", adMaterialInfo.materialFeatureList);
        return jSONObject;
    }
}
