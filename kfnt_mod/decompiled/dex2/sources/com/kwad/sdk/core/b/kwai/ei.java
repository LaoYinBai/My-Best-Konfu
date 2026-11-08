package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.u;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ei implements com.kwad.sdk.core.d<u.a> {
    @Override // com.kwad.sdk.core.d
    public void a(u.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt("timerName");
        aVar.b = jSONObject.optInt("time");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(u.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "timerName", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "time", aVar.b);
        return jSONObject;
    }
}
