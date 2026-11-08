package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.Message;
import com.kwad.sdk.core.webview.jshandler.i;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bs implements com.kwad.sdk.core.d<i.a> {
    @Override // com.kwad.sdk.core.d
    public void a(i.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt(Message.TYPE);
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(i.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, Message.TYPE, aVar.a);
        return jSONObject;
    }
}
