package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.x;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ee implements com.kwad.sdk.core.d<x.a> {
    @Override // com.kwad.sdk.core.d
    public void a(x.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt("taskStatus");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(x.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "taskStatus", aVar.a);
        return jSONObject;
    }
}
