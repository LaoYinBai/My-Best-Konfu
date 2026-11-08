package com.kwad.sdk.core.report.kwai;

import com.kwad.sdk.utils.t;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a implements b {
    @Override // com.kwad.sdk.core.report.kwai.b
    public void a(JSONObject jSONObject, Map<String, Object> map) {
        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("ecExt");
        if (jSONObjectOptJSONObject != null) {
            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
            while (itKeys.hasNext()) {
                String next = itKeys.next();
                map.put(next, jSONObjectOptJSONObject.opt(next));
            }
        }
    }

    @Override // com.kwad.sdk.core.report.kwai.b
    public void b(JSONObject jSONObject, Map<String, Object> map) throws JSONException {
        JSONObject jSONObject2 = new JSONObject();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            try {
                jSONObject.put(entry.getKey(), entry.getValue());
                jSONObject2.put(entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }
        if (jSONObject2.length() > 0) {
            t.a(jSONObject, "ecExt", jSONObject2);
        }
    }
}
