package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.response.model.AdInfo;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class x implements com.kwad.sdk.core.d<AdInfo.AdvertiserInfo> {
    @Override // com.kwad.sdk.core.d
    public void a(AdInfo.AdvertiserInfo advertiserInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        advertiserInfo.userId = jSONObject.optLong("userId");
        advertiserInfo.userName = jSONObject.optString("userName");
        if (jSONObject.opt("userName") == JSONObject.NULL) {
            advertiserInfo.userName = "";
        }
        advertiserInfo.rawUserName = jSONObject.optString("rawUserName");
        if (jSONObject.opt("rawUserName") == JSONObject.NULL) {
            advertiserInfo.rawUserName = "";
        }
        advertiserInfo.userGender = jSONObject.optString("userGender");
        if (jSONObject.opt("userGender") == JSONObject.NULL) {
            advertiserInfo.userGender = "";
        }
        advertiserInfo.portraitUrl = jSONObject.optString("portraitUrl");
        if (jSONObject.opt("portraitUrl") == JSONObject.NULL) {
            advertiserInfo.portraitUrl = "";
        }
        advertiserInfo.adAuthorText = jSONObject.optString("adAuthorText");
        if (jSONObject.opt("adAuthorText") == JSONObject.NULL) {
            advertiserInfo.adAuthorText = "";
        }
        advertiserInfo.authorIconGuide = jSONObject.optString("authorIconGuide");
        if (jSONObject.opt("authorIconGuide") == JSONObject.NULL) {
            advertiserInfo.authorIconGuide = "";
        }
        advertiserInfo.followed = jSONObject.optBoolean("followed");
        advertiserInfo.fansCount = jSONObject.optInt("fansCount");
        advertiserInfo.brief = jSONObject.optString("brief");
        if (jSONObject.opt("brief") == JSONObject.NULL) {
            advertiserInfo.brief = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(AdInfo.AdvertiserInfo advertiserInfo, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "userId", advertiserInfo.userId);
        com.kwad.sdk.utils.t.a(jSONObject, "userName", advertiserInfo.userName);
        com.kwad.sdk.utils.t.a(jSONObject, "rawUserName", advertiserInfo.rawUserName);
        com.kwad.sdk.utils.t.a(jSONObject, "userGender", advertiserInfo.userGender);
        com.kwad.sdk.utils.t.a(jSONObject, "portraitUrl", advertiserInfo.portraitUrl);
        com.kwad.sdk.utils.t.a(jSONObject, "adAuthorText", advertiserInfo.adAuthorText);
        com.kwad.sdk.utils.t.a(jSONObject, "authorIconGuide", advertiserInfo.authorIconGuide);
        com.kwad.sdk.utils.t.a(jSONObject, "followed", advertiserInfo.followed);
        com.kwad.sdk.utils.t.a(jSONObject, "fansCount", advertiserInfo.fansCount);
        com.kwad.sdk.utils.t.a(jSONObject, "brief", advertiserInfo.brief);
        return jSONObject;
    }
}
