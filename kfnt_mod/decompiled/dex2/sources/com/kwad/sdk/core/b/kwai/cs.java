package com.kwad.sdk.core.b.kwai;

import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class cs implements com.kwad.sdk.core.d<com.kwad.sdk.c.kwai.d> {
    @Override // com.kwad.sdk.core.d
    public void a(com.kwad.sdk.c.kwai.d dVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        dVar.a = jSONObject.optString("packageId");
        if (jSONObject.opt("packageId") == JSONObject.NULL) {
            dVar.a = "";
        }
        dVar.b = jSONObject.optString("zipFileName");
        if (jSONObject.opt("zipFileName") == JSONObject.NULL) {
            dVar.b = "";
        }
        dVar.c = jSONObject.optString("zipPath");
        if (jSONObject.opt("zipPath") == JSONObject.NULL) {
            dVar.c = "";
        }
        dVar.d = jSONObject.optString("packageUrl");
        if (jSONObject.opt("packageUrl") == JSONObject.NULL) {
            dVar.d = "";
        }
        dVar.e = jSONObject.optString(HiAnalyticsConstant.HaKey.BI_KEY_VERSION);
        if (jSONObject.opt(HiAnalyticsConstant.HaKey.BI_KEY_VERSION) == JSONObject.NULL) {
            dVar.e = "";
        }
        dVar.f = jSONObject.optString("checksum");
        if (jSONObject.opt("checksum") == JSONObject.NULL) {
            dVar.f = "";
        }
        dVar.g = jSONObject.optInt("loadType");
        dVar.h = jSONObject.optInt("packageType");
        dVar.i = jSONObject.optBoolean("isPublic");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(com.kwad.sdk.c.kwai.d dVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "packageId", dVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "zipFileName", dVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "zipPath", dVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "packageUrl", dVar.d);
        com.kwad.sdk.utils.t.a(jSONObject, HiAnalyticsConstant.HaKey.BI_KEY_VERSION, dVar.e);
        com.kwad.sdk.utils.t.a(jSONObject, "checksum", dVar.f);
        com.kwad.sdk.utils.t.a(jSONObject, "loadType", dVar.g);
        com.kwad.sdk.utils.t.a(jSONObject, "packageType", dVar.h);
        com.kwad.sdk.utils.t.a(jSONObject, "isPublic", dVar.i);
        return jSONObject;
    }
}
