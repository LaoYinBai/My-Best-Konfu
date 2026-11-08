package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdMatrixInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dz implements com.kwad.sdk.core.d<AdMatrixInfo.Styles> {
    @Override // com.kwad.sdk.core.d
    public void a(AdMatrixInfo.Styles styles, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        styles.templateList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("templates");
        if (jSONArrayOptJSONArray != null) {
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                AdMatrixInfo.MatrixTemplate matrixTemplate = new AdMatrixInfo.MatrixTemplate();
                matrixTemplate.parseJson(jSONArrayOptJSONArray.optJSONObject(i));
                styles.templateList.add(matrixTemplate);
            }
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdMatrixInfo.Styles styles, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "templates", styles.templateList);
        return jSONObject;
    }
}
