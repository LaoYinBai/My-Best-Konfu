package com.kwad.sdk.core.b.kwai;

import com.kwad.sdk.core.webview.jshandler.e;
import com.liulishuo.filedownloader.services.FileDownloadBroadcastHandler;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class bp implements com.kwad.sdk.core.d<e.a> {
    @Override // com.kwad.sdk.core.d
    public void a(e.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        aVar.a = jSONObject.optString("SDKVersion");
        if (jSONObject.opt("SDKVersion") == JSONObject.NULL) {
            aVar.a = "";
        }
        aVar.b = jSONObject.optInt("SDKVersionCode");
        aVar.c = jSONObject.optString("sdkApiVersion");
        if (jSONObject.opt("sdkApiVersion") == JSONObject.NULL) {
            aVar.c = "";
        }
        aVar.d = jSONObject.optInt("sdkApiVersionCode");
        aVar.e = jSONObject.optInt("sdkType");
        aVar.f = jSONObject.optString("appVersion");
        if (jSONObject.opt("appVersion") == JSONObject.NULL) {
            aVar.f = "";
        }
        aVar.g = jSONObject.optString("appName");
        if (jSONObject.opt("appName") == JSONObject.NULL) {
            aVar.g = "";
        }
        aVar.h = jSONObject.optString("appId");
        if (jSONObject.opt("appId") == JSONObject.NULL) {
            aVar.h = "";
        }
        aVar.i = jSONObject.optString("globalId");
        if (jSONObject.opt("globalId") == JSONObject.NULL) {
            aVar.i = "";
        }
        aVar.j = jSONObject.optString("eGid");
        if (jSONObject.opt("eGid") == JSONObject.NULL) {
            aVar.j = "";
        }
        aVar.k = jSONObject.optString("deviceSig");
        if (jSONObject.opt("deviceSig") == JSONObject.NULL) {
            aVar.k = "";
        }
        aVar.l = jSONObject.optString("networkType");
        if (jSONObject.opt("networkType") == JSONObject.NULL) {
            aVar.l = "";
        }
        aVar.m = jSONObject.optString("manufacturer");
        if (jSONObject.opt("manufacturer") == JSONObject.NULL) {
            aVar.m = "";
        }
        aVar.n = jSONObject.optString(FileDownloadBroadcastHandler.KEY_MODEL);
        if (jSONObject.opt(FileDownloadBroadcastHandler.KEY_MODEL) == JSONObject.NULL) {
            aVar.n = "";
        }
        aVar.o = jSONObject.optString("deviceBrand");
        if (jSONObject.opt("deviceBrand") == JSONObject.NULL) {
            aVar.o = "";
        }
        aVar.p = jSONObject.optInt("osType");
        aVar.q = jSONObject.optString("systemVersion");
        if (jSONObject.opt("systemVersion") == JSONObject.NULL) {
            aVar.q = "";
        }
        aVar.r = jSONObject.optInt("osApi");
        aVar.s = jSONObject.optString("language");
        if (jSONObject.opt("language") == JSONObject.NULL) {
            aVar.s = "";
        }
        aVar.t = jSONObject.optString("locale");
        if (jSONObject.opt("locale") == JSONObject.NULL) {
            aVar.t = "";
        }
        aVar.u = jSONObject.optString("uuid");
        if (jSONObject.opt("uuid") == JSONObject.NULL) {
            aVar.u = "";
        }
        aVar.v = jSONObject.optInt("screenWidth");
        aVar.w = jSONObject.optInt("screenHeight");
        aVar.x = jSONObject.optString("imei");
        if (jSONObject.opt("imei") == JSONObject.NULL) {
            aVar.x = "";
        }
        aVar.y = jSONObject.optString("oaid");
        if (jSONObject.opt("oaid") == JSONObject.NULL) {
            aVar.y = "";
        }
        aVar.z = jSONObject.optString("androidId");
        if (jSONObject.opt("androidId") == JSONObject.NULL) {
            aVar.z = "";
        }
        aVar.A = jSONObject.optString("mac");
        if (jSONObject.opt("mac") == JSONObject.NULL) {
            aVar.A = "";
        }
        aVar.B = jSONObject.optInt("statusBarHeight");
        aVar.C = jSONObject.optInt("titleBarHeight");
    }

    @Override // com.kwad.sdk.core.d
    public JSONObject b(e.a aVar, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        com.kwad.sdk.utils.t.a(jSONObject, "SDKVersion", aVar.a);
        com.kwad.sdk.utils.t.a(jSONObject, "SDKVersionCode", aVar.b);
        com.kwad.sdk.utils.t.a(jSONObject, "sdkApiVersion", aVar.c);
        com.kwad.sdk.utils.t.a(jSONObject, "sdkApiVersionCode", aVar.d);
        com.kwad.sdk.utils.t.a(jSONObject, "sdkType", aVar.e);
        com.kwad.sdk.utils.t.a(jSONObject, "appVersion", aVar.f);
        com.kwad.sdk.utils.t.a(jSONObject, "appName", aVar.g);
        com.kwad.sdk.utils.t.a(jSONObject, "appId", aVar.h);
        com.kwad.sdk.utils.t.a(jSONObject, "globalId", aVar.i);
        com.kwad.sdk.utils.t.a(jSONObject, "eGid", aVar.j);
        com.kwad.sdk.utils.t.a(jSONObject, "deviceSig", aVar.k);
        com.kwad.sdk.utils.t.a(jSONObject, "networkType", aVar.l);
        com.kwad.sdk.utils.t.a(jSONObject, "manufacturer", aVar.m);
        com.kwad.sdk.utils.t.a(jSONObject, FileDownloadBroadcastHandler.KEY_MODEL, aVar.n);
        com.kwad.sdk.utils.t.a(jSONObject, "deviceBrand", aVar.o);
        com.kwad.sdk.utils.t.a(jSONObject, "osType", aVar.p);
        com.kwad.sdk.utils.t.a(jSONObject, "systemVersion", aVar.q);
        com.kwad.sdk.utils.t.a(jSONObject, "osApi", aVar.r);
        com.kwad.sdk.utils.t.a(jSONObject, "language", aVar.s);
        com.kwad.sdk.utils.t.a(jSONObject, "locale", aVar.t);
        com.kwad.sdk.utils.t.a(jSONObject, "uuid", aVar.u);
        com.kwad.sdk.utils.t.a(jSONObject, "screenWidth", aVar.v);
        com.kwad.sdk.utils.t.a(jSONObject, "screenHeight", aVar.w);
        com.kwad.sdk.utils.t.a(jSONObject, "imei", aVar.x);
        com.kwad.sdk.utils.t.a(jSONObject, "oaid", aVar.y);
        com.kwad.sdk.utils.t.a(jSONObject, "androidId", aVar.z);
        com.kwad.sdk.utils.t.a(jSONObject, "mac", aVar.A);
        com.kwad.sdk.utils.t.a(jSONObject, "statusBarHeight", aVar.B);
        com.kwad.sdk.utils.t.a(jSONObject, "titleBarHeight", aVar.C);
        return jSONObject;
    }
}
