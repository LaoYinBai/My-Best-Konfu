package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.n;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cd implements com.kwad.sdk.core.d<n.b> {
    @Override // com.kwad.sdk.core.d
    public void a(n.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        bVar.a = jSONObject.optDouble("progress");
        bVar.b = jSONObject.optInt("status");
        bVar.c = jSONObject.optLong("totalBytes");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(n.b bVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "progress", bVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "status", bVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "totalBytes", bVar.c);
        return jSONObject;
    }
}
