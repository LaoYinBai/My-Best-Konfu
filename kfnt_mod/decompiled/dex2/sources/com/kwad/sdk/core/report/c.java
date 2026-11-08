package com.kwad.sdk.core.report;

import androidx.annotation.Nullable;
import com.kwad.sdk.utils.ax;
import com.kwad.sdk.utils.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class c extends com.kwad.sdk.core.response.kwai.a {
    public String a;
    private JSONObject b;

    public JSONObject a() throws JSONException {
        JSONObject json = toJson();
        if (this.b != null) {
            json.remove("mMergeJsonData");
            ax.a(json, this.b);
        }
        return json;
    }

    @Override // com.kwad.sdk.core.response.kwai.a
    public void afterParseJson(@Nullable JSONObject jSONObject) {
        super.afterParseJson(jSONObject);
        if (jSONObject != null) {
            this.b = jSONObject.optJSONObject("mMergeJsonData");
        }
    }

    @Override // com.kwad.sdk.core.response.kwai.a
    public void afterToJson(JSONObject jSONObject) throws JSONException {
        super.afterToJson(jSONObject);
        if (this.b != null) {
            t.a(jSONObject, "mMergeJsonData", this.b);
        }
    }
}
