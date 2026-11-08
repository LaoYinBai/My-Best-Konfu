package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.b;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements com.kwad.sdk.core.d<b.a> {
    @Override // com.kwad.sdk.core.d
    public void a(b.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.b = jSONObject.optBoolean("clickActionButton");
        aVar.c = jSONObject.optInt("area");
        aVar.d = new b.C0052b();
        aVar.d.parseJson(jSONObject.optJSONObject("logParam"));
        aVar.e = jSONObject.optLong("creativeId", new Long("-1").longValue());
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(b.a aVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "clickActionButton", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "area", aVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "logParam", aVar.d);
        com.kwad.sdk.utils.t.a(jSONObject, "creativeId", aVar.e);
        return jSONObject;
    }
}
