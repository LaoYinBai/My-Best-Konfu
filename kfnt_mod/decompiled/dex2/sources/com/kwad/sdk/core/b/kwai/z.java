package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.q;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class z implements com.kwad.sdk.core.d<q.a> {
    @Override // com.kwad.sdk.core.d
    public void a(q.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optDouble("progress");
        aVar.b = jSONObject.optInt("status");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(q.a aVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "progress", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "status", aVar.b);
        return jSONObject;
    }
}
