package com.kwad.sdk.e.kwai;

import com.heytap.mcssdk.mode.CommandMessage;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.kuaishou.tachikoma.api.app.TKError;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e implements com.kwad.sdk.e.e<TKError> {
    @Override // com.kwad.sdk.e.e
    public void a(TKError tKError, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        tKError.name = jSONObject.optString("name");
        tKError.code = jSONObject.optInt(CommandMessage.CODE);
        tKError.msg = jSONObject.optString(RemoteMessageConst.MessageBody.MSG);
    }

    @Override // com.kwad.sdk.e.e
    public JSONObject b(TKError tKError, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.e.f.a(jSONObject, "name", tKError.name);
        com.kwad.sdk.e.f.a(jSONObject, CommandMessage.CODE, tKError.code);
        com.kwad.sdk.e.f.a(jSONObject, RemoteMessageConst.MessageBody.MSG, tKError.msg);
        return jSONObject;
    }
}
