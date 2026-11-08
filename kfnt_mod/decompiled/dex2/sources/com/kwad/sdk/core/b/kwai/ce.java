package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.z;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ce implements com.kwad.sdk.core.d<z.a> {
    @Override // com.kwad.sdk.core.d
    public void a(z.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt("itemClickType");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(z.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "itemClickType", aVar.a);
        return jSONObject;
    }
}
