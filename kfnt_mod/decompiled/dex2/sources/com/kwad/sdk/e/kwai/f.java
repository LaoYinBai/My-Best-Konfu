package com.kwad.sdk.e.kwai;

import com.heytap.mcssdk.mode.CommandMessage;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.tachikoma.core.component.network.TKErrorInner;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f implements com.kwad.sdk.e.e<TKErrorInner> {
    @Override // com.kwad.sdk.e.e
    public void a(TKErrorInner tKErrorInner, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        tKErrorInner.name = jSONObject.optString("name");
        tKErrorInner.code = jSONObject.optInt(CommandMessage.CODE);
        tKErrorInner.msg = jSONObject.optString(RemoteMessageConst.MessageBody.MSG);
    }

    @Override // com.kwad.sdk.e.e
    public JSONObject b(TKErrorInner tKErrorInner, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.e.f.a(jSONObject, "name", tKErrorInner.name);
        com.kwad.sdk.e.f.a(jSONObject, CommandMessage.CODE, tKErrorInner.code);
        com.kwad.sdk.e.f.a(jSONObject, RemoteMessageConst.MessageBody.MSG, tKErrorInner.msg);
        return jSONObject;
    }
}
