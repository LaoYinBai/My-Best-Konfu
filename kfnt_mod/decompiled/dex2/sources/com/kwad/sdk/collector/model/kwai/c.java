package com.kwad.sdk.collector.model.kwai;

import androidx.annotation.Nullable;
import com.kwad.sdk.utils.t;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c implements com.kwad.sdk.collector.model.d {
    private String a;
    private List<String> b = new ArrayList();

    public String a() {
        return this.a;
    }

    public List<String> b() {
        return this.b;
    }

    @Override // com.kwad.sdk.core.b
    public void parseJson(@Nullable JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            return;
        }
        try {
            this.a = jSONObject.optString("packageName");
            JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("paths");
            if (jSONArrayOptJSONArray != null) {
                this.b.clear();
                int length = jSONArrayOptJSONArray.length();
                for (int i = 0; i < length; i++) {
                    this.b.add(jSONArrayOptJSONArray.getString(i));
                }
            }
        } catch (Exception e) {
            com.kwad.sdk.crash.a.a(e);
            com.kwad.sdk.core.d.a.a(e);
        }
    }

    @Override // com.kwad.sdk.core.b
    public JSONObject toJson() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        t.a(jSONObject, "packageName", this.a);
        t.a(jSONObject, "paths", this.b);
        return jSONObject;
    }
}
