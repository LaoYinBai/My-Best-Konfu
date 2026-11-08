package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.CommandMessage;
import com.huawei.hms.push.constant.RemoteMessageConst;
import com.kwad.sdk.core.report.a;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bi implements com.kwad.sdk.core.d<a.C0045a> {
    @Override // com.kwad.sdk.core.d
    public void a(a.C0045a c0045a, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        c0045a.a = jSONObject.optInt(CommandMessage.CODE);
        c0045a.b = jSONObject.optString(RemoteMessageConst.MessageBody.MSG);
        if (jSONObject.opt(RemoteMessageConst.MessageBody.MSG) == JSONObject.NULL) {
            c0045a.b = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(a.C0045a c0045a, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, CommandMessage.CODE, c0045a.a);
        com.kwad.sdk.utils.t.a(jSONObject, RemoteMessageConst.MessageBody.MSG, c0045a.b);
        return jSONObject;
    }
}
