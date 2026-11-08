package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.b;
import com.kwad.sdk.core.webview.jshandler.l;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class as implements com.kwad.sdk.core.d<l.a> {
    @Override // com.kwad.sdk.core.d
    public void a(l.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optBoolean("clickActionButton");
        aVar.b = jSONObject.optString("adTemplate");
        if (jSONObject.opt("adTemplate") == JSONObject.NULL) {
            aVar.b = "";
        }
        aVar.c = jSONObject.optInt("area");
        aVar.d = new b.C0052b();
        aVar.d.parseJson(jSONObject.optJSONObject("logParam"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(l.a aVar, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "clickActionButton", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "adTemplate", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "area", aVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "logParam", aVar.d);
        return jSONObject;
    }
}
