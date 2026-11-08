package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.CommandMessage;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cq implements com.kwad.sdk.core.d<com.kwad.sdk.core.webview.kwai.i> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.core.webview.kwai.i iVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        iVar.a = jSONObject.optString("url");
        if (jSONObject.opt("url") == JSONObject.NULL) {
            iVar.a = "";
        }
        iVar.b = jSONObject.optString("title");
        if (jSONObject.opt("title") == JSONObject.NULL) {
            iVar.b = "";
        }
        iVar.c = jSONObject.optString(CommandMessage.PARAMS);
        if (jSONObject.opt(CommandMessage.PARAMS) == JSONObject.NULL) {
            iVar.c = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.core.webview.kwai.i iVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "url", iVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "title", iVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, CommandMessage.PARAMS, iVar.c);
        return jSONObject;
    }
}
