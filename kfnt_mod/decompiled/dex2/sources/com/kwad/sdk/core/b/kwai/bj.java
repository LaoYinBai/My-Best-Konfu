package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdStyleInfo;
import com.mbridge.msdk.playercommon.exoplayer2.util.MimeTypes;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bj implements com.kwad.sdk.core.d<AdStyleInfo.ExposeTagInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdStyleInfo.ExposeTagInfo exposeTagInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        exposeTagInfo.text = jSONObject.optString(MimeTypes.BASE_TYPE_TEXT);
        if (jSONObject.opt(MimeTypes.BASE_TYPE_TEXT) == JSONObject.NULL) {
            exposeTagInfo.text = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdStyleInfo.ExposeTagInfo exposeTagInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, MimeTypes.BASE_TYPE_TEXT, exposeTagInfo.text);
        return jSONObject;
    }
}
