package com.kwad.sdk.core.b.kwai;

import com.kwad.tachikoma.config.TKConfigResultData;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class an implements com.kwad.sdk.core.d<TKConfigResultData.a> {
    @Override // com.kwad.sdk.core.d
    public void a(TKConfigResultData.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optLong("interval");
        aVar.b = jSONObject.optString("jsVersion");
        if (jSONObject.opt("jsVersion") == JSONObject.NULL) {
            aVar.b = "";
        }
        aVar.c = jSONObject.optString("dynamicUrl");
        if (jSONObject.opt("dynamicUrl") == JSONObject.NULL) {
            aVar.c = "";
        }
        aVar.d = jSONObject.optString("md5");
        if (jSONObject.opt("md5") == JSONObject.NULL) {
            aVar.d = "";
        }
        aVar.e = jSONObject.optInt("dynamicType");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(TKConfigResultData.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "interval", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "jsVersion", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "dynamicUrl", aVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "md5", aVar.d);
        com.kwad.sdk.utils.t.a(jSONObject, "dynamicType", aVar.e);
        return jSONObject;
    }
}
