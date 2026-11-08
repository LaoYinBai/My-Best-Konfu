package com.kwad.sdk.core.b.kwai;

import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.kwad.sdk.core.webview.jshandler.q;
import com.mbridge.msdk.foundation.entity.CampaignEx;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class aa implements com.kwad.sdk.core.d<q.b> {
    @Override // com.kwad.sdk.core.d
    public void a(q.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        bVar.a = jSONObject.optString("appName");
        if (jSONObject.opt("appName") == JSONObject.NULL) {
            bVar.a = "";
        }
        bVar.b = jSONObject.optString("pkgName");
        if (jSONObject.opt("pkgName") == JSONObject.NULL) {
            bVar.b = "";
        }
        bVar.c = jSONObject.optString(HiAnalyticsConstant.HaKey.BI_KEY_VERSION);
        if (jSONObject.opt(HiAnalyticsConstant.HaKey.BI_KEY_VERSION) == JSONObject.NULL) {
            bVar.c = "";
        }
        bVar.d = jSONObject.optInt("versionCode");
        bVar.e = jSONObject.optLong("appSize");
        bVar.f = jSONObject.optString("md5");
        if (jSONObject.opt("md5") == JSONObject.NULL) {
            bVar.f = "";
        }
        bVar.g = jSONObject.optString("url");
        if (jSONObject.opt("url") == JSONObject.NULL) {
            bVar.g = "";
        }
        bVar.h = jSONObject.optString("icon");
        if (jSONObject.opt("icon") == JSONObject.NULL) {
            bVar.h = "";
        }
        bVar.i = jSONObject.optString(CampaignEx.JSON_KEY_DESC);
        if (jSONObject.opt(CampaignEx.JSON_KEY_DESC) == JSONObject.NULL) {
            bVar.i = "";
        }
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(q.b bVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "appName", bVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "pkgName", bVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_VERSION, bVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "versionCode", bVar.d);
        com.kwad.sdk.utils.t.a(jSONObject, "appSize", bVar.e);
        com.kwad.sdk.utils.t.a(jSONObject, "md5", bVar.f);
        com.kwad.sdk.utils.t.a(jSONObject, "url", bVar.g);
        com.kwad.sdk.utils.t.a(jSONObject, "icon", bVar.h);
        com.kwad.sdk.utils.t.a(jSONObject, CampaignEx.JSON_KEY_DESC, bVar.i);
        return jSONObject;
    }
}
