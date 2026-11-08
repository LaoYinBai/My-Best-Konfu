package com.kwad.tachikoma.config;

import android.text.TextUtils;
import androidx.annotation.Nullable;
import com.kwad.sdk.core.a.d;
import com.kwad.sdk.core.network.BaseResultData;
import com.kwad.sdk.utils.t;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class TKConfigResultData extends BaseResultData {
    public a data = new a();

    public static class a extends com.kwad.sdk.core.response.kwai.a {
        public long a;
        public String b;
        public String c;
        public String d;
        public int e;
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws Throwable {
        super.parseJson(jSONObject);
        if (jSONObject == null) {
            return;
        }
        try {
            String strOptString = jSONObject.optString("data");
            String strB = d.b(strOptString);
            if (!TextUtils.isEmpty(strB)) {
                strOptString = strB;
            }
            this.data.parseJson(new JSONObject(strOptString));
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.network.BaseResultData, com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject json = super.toJson();
        t.a(json, "data", this.data);
        return json;
    }
}
