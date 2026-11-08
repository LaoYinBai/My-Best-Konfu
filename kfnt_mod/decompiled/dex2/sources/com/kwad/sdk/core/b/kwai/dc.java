package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.Message;
import com.kwad.sdk.core.response.model.AdStyleInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class dc implements com.kwad.sdk.core.d<AdStyleInfo.PlayEndInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdStyleInfo.PlayEndInfo playEndInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        playEndInfo.type = jSONObject.optInt(Message.TYPE);
        playEndInfo.showLandingPage3 = jSONObject.optInt("showLandingPage3");
        playEndInfo.adWebCardInfo = new AdStyleInfo.PlayEndInfo.AdWebCardInfo();
        playEndInfo.adWebCardInfo.parseJson(jSONObject.optJSONObject("adWebCardInfo"));
        playEndInfo.endTopToolBarInfo = new AdStyleInfo.PlayEndInfo.EndTopToolBarInfo();
        playEndInfo.endTopToolBarInfo.parseJson(jSONObject.optJSONObject("endTopToolBarInfo"));
        playEndInfo.horizontalPatchAdInfo = new AdStyleInfo.PlayEndInfo.HorizontalPatchAdInfo();
        playEndInfo.horizontalPatchAdInfo.parseJson(jSONObject.optJSONObject("horizontalPatchAdInfo"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdStyleInfo.PlayEndInfo playEndInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, Message.TYPE, playEndInfo.type);
        com.kwad.sdk.utils.t.a(jSONObject, "showLandingPage3", playEndInfo.showLandingPage3);
        com.kwad.sdk.utils.t.a(jSONObject, "adWebCardInfo", playEndInfo.adWebCardInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "endTopToolBarInfo", playEndInfo.endTopToolBarInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "horizontalPatchAdInfo", playEndInfo.horizontalPatchAdInfo);
        return jSONObject;
    }
}
