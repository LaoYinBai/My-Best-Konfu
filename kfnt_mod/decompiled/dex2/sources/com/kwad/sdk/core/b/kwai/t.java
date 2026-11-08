package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdStyleInfo;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class t implements com.kwad.sdk.core.d<AdStyleInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdStyleInfo adStyleInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        adStyleInfo.playDetailInfo = new AdStyleInfo.PlayDetailInfo();
        adStyleInfo.playDetailInfo.parseJson(jSONObject.optJSONObject("playDetailInfo"));
        adStyleInfo.playEndInfo = new AdStyleInfo.PlayEndInfo();
        adStyleInfo.playEndInfo.parseJson(jSONObject.optJSONObject("playEndInfo"));
        adStyleInfo.feedAdInfo = new AdStyleInfo.FeedAdInfo();
        adStyleInfo.feedAdInfo.parseJson(jSONObject.optJSONObject("feedAdInfo"));
        adStyleInfo.adBrowseInfo = new AdStyleInfo.AdBrowseInfo();
        adStyleInfo.adBrowseInfo.parseJson(jSONObject.optJSONObject("adBrowseInfo"));
        adStyleInfo.extraDisplayInfo = new AdStyleInfo.ExtraDisplayInfo();
        adStyleInfo.extraDisplayInfo.parseJson(jSONObject.optJSONObject("extraDisplayInfo"));
        adStyleInfo.slideClick = jSONObject.optBoolean("slideClick");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdStyleInfo adStyleInfo, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "playDetailInfo", adStyleInfo.playDetailInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "playEndInfo", adStyleInfo.playEndInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "feedAdInfo", adStyleInfo.feedAdInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "adBrowseInfo", adStyleInfo.adBrowseInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "extraDisplayInfo", adStyleInfo.extraDisplayInfo);
        com.kwad.sdk.utils.t.a(jSONObject, "slideClick", adStyleInfo.slideClick);
        return jSONObject;
    }
}
