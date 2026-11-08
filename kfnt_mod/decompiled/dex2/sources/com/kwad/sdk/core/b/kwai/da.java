package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.Message;
import com.kwad.sdk.core.response.model.AdStyleInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class da implements com.kwad.sdk.core.d<AdStyleInfo.PlayDetailInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdStyleInfo.PlayDetailInfo playDetailInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        playDetailInfo.type = jSONObject.optInt(Message.TYPE);
        playDetailInfo.detailWebCardInfo = new AdStyleInfo.PlayDetailInfo.DetailWebCardInfo();
        playDetailInfo.detailWebCardInfo.parseJson(jSONObject.optJSONObject("detailWebCardInfo"));
        playDetailInfo.detailTopToolBarInfo = new AdStyleInfo.PlayDetailInfo.DetailTopToolBarInfo();
        playDetailInfo.detailTopToolBarInfo.parseJson(jSONObject.optJSONObject("detailTopToolBarInfo"));
        playDetailInfo.actionBarInfo = new AdStyleInfo.PlayDetailInfo.ActionBarInfo();
        playDetailInfo.actionBarInfo.parseJson(jSONObject.optJSONObject("actionBarInfo"));
        playDetailInfo.patchAdInfo = new AdStyleInfo.PlayDetailInfo.PatchAdInfo();
        playDetailInfo.patchAdInfo.parseJson(jSONObject.optJSONObject("patchAdInfo"));
        playDetailInfo.widgetAdInfo = new AdStyleInfo.PlayDetailInfo.WidgetAdInfo();
        playDetailInfo.widgetAdInfo.parseJson(jSONObject.optJSONObject("widgetAdInfo"));
        playDetailInfo.detailCommonInfo = new AdStyleInfo.PlayDetailInfo.DetailCommonInfo();
        playDetailInfo.detailCommonInfo.parseJson(jSONObject.optJSONObject("detailCommonInfo"));
        playDetailInfo.drawAdInfo = new AdStyleInfo.PlayDetailInfo.DrawAdInfo();
        playDetailInfo.drawAdInfo.parseJson(jSONObject.optJSONObject("drawAdInfo"));
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdStyleInfo.PlayDetailInfo playDetailInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, Message.TYPE, playDetailInfo.type);
        com.kwad.sdk.utils.t.a(jSONObject, "detailWebCardInfo", playDetailInfo.detailWebCardInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "detailTopToolBarInfo", playDetailInfo.detailTopToolBarInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "actionBarInfo", playDetailInfo.actionBarInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "patchAdInfo", playDetailInfo.patchAdInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "widgetAdInfo", playDetailInfo.widgetAdInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "detailCommonInfo", playDetailInfo.detailCommonInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "drawAdInfo", playDetailInfo.drawAdInfo);
        return jSONObject;
    }
}
