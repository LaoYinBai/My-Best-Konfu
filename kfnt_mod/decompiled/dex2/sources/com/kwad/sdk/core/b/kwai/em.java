package com.kwad.sdk.core.b.kwai;

import com.heytap.mcssdk.mode.Message;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.sdk.core.webview.jshandler.h;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class em implements com.kwad.sdk.core.d<h.a> {
    @Override // com.kwad.sdk.core.d
    public void a(h.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optInt(Message.TYPE);
        aVar.b = jSONObject.optString("appName");
        if (jSONObject.opt("appName") == JSONObject.NULL) {
            aVar.b = "";
        }
        aVar.c = jSONObject.optString("pkgName");
        if (jSONObject.opt("pkgName") == JSONObject.NULL) {
            aVar.c = "";
        }
        aVar.d = jSONObject.optString(HiAnalyticsConstant.HaKey.BI_KEY_VERSION);
        if (jSONObject.opt(HiAnalyticsConstant.HaKey.BI_KEY_VERSION) == JSONObject.NULL) {
            aVar.d = "";
        }
        aVar.e = jSONObject.optInt("versionCode");
        aVar.f = jSONObject.optInt("appSize");
        aVar.g = jSONObject.optString("md5");
        if (jSONObject.opt("md5") == JSONObject.NULL) {
            aVar.g = "";
        }
        aVar.h = jSONObject.optString("url");
        if (jSONObject.opt("url") == JSONObject.NULL) {
            aVar.h = "";
        }
        aVar.i = jSONObject.optString("appLink");
        if (jSONObject.opt("appLink") == JSONObject.NULL) {
            aVar.i = "";
        }
        aVar.j = jSONObject.optString("icon");
        if (jSONObject.opt("icon") == JSONObject.NULL) {
            aVar.j = "";
        }
        aVar.k = jSONObject.optString(CampaignEx.JSON_KEY_DESC);
        if (jSONObject.opt(CampaignEx.JSON_KEY_DESC) == JSONObject.NULL) {
            aVar.k = "";
        }
        aVar.l = jSONObject.optString("appId");
        if (jSONObject.opt("appId") == JSONObject.NULL) {
            aVar.l = "";
        }
        aVar.m = jSONObject.optString("marketUri");
        if (jSONObject.opt("marketUri") == JSONObject.NULL) {
            aVar.m = "";
        }
        aVar.n = jSONObject.optBoolean("disableLandingPageDeepLink");
        aVar.o = jSONObject.optBoolean("isLandscapeSupported");
        aVar.p = jSONObject.optBoolean("isFromLive");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(h.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, Message.TYPE, aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "appName", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "pkgName", aVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_VERSION, aVar.d);
        com.kwad.sdk.utils.t.a(jSONObject, "versionCode", aVar.e);
        com.kwad.sdk.utils.t.a(jSONObject, "appSize", aVar.f);
        com.kwad.sdk.utils.t.a(jSONObject, "md5", aVar.g);
        com.kwad.sdk.utils.t.a(jSONObject, "url", aVar.h);
        com.kwad.sdk.utils.t.a(jSONObject, "appLink", aVar.i);
        com.kwad.sdk.utils.t.a(jSONObject, "icon", aVar.j);
        com.kwad.sdk.utils.t.a(jSONObject, CampaignEx.JSON_KEY_DESC, aVar.k);
        com.kwad.sdk.utils.t.a(jSONObject, "appId", aVar.l);
        com.kwad.sdk.utils.t.a(jSONObject, "marketUri", aVar.m);
        com.kwad.sdk.utils.t.a(jSONObject, "disableLandingPageDeepLink", aVar.n);
        com.kwad.sdk.utils.t.a(jSONObject, "isLandscapeSupported", aVar.o);
        com.kwad.sdk.utils.t.a(jSONObject, "isFromLive", aVar.p);
        return jSONObject;
    }
}
