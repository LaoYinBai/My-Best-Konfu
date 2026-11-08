package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.CommandMessage;
import com.kwad.sdk.core.webview.jshandler.w;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dn implements com.kwad.sdk.core.d<w.a> {
    @Override // com.kwad.sdk.core.d
    public void a(w.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optString("url");
        if (jSONObject.opt("url") == JSONObject.NULL) {
            aVar.a = "";
        }
        aVar.b = jSONObject.optString("method");
        if (jSONObject.opt("method") == JSONObject.NULL) {
            aVar.b = "";
        }
        aVar.c = jSONObject.optString(CommandMessage.PARAMS);
        if (jSONObject.opt(CommandMessage.PARAMS) == JSONObject.NULL) {
            aVar.c = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(w.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "url", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "method", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, CommandMessage.PARAMS, aVar.c);
        return jSONObject;
    }
}
