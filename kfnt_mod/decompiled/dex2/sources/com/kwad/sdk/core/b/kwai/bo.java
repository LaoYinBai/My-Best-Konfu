package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.m;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bo implements com.kwad.sdk.core.d<m.b> {
    @Override // com.kwad.sdk.core.d
    public void a(m.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        bVar.a = jSONObject.optLong("creativeId", new Long("-1").longValue());
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(m.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "creativeId", bVar.a);
        return jSONObject;
    }
}
