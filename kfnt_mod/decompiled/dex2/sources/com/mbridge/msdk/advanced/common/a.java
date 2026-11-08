package com.mbridge.msdk.advanced.common;

import android.content.Context;
import com.kwad.v8.Platform;
import com.mbridge.msdk.MBridgeConstans;
import com.mbridge.msdk.foundation.tools.o;
import com.mbridge.msdk.foundation.tools.w;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DeviceInfo.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public String d;
    public String e;
    public String g;
    public String h;
    public String i;
    public String j;
    public String k;
    public String l;
    public String m;
    public String n;
    public String o;
    public String p;
    public String c = Platform.ANDROID;
    public String a = o.u();
    public String b = o.y();
    public String f = o.A();

    public a(Context context) {
        this.d = o.b(context);
        this.e = o.g(context);
        int iC = o.C(context);
        this.h = String.valueOf(iC);
        this.i = o.a(context, iC);
        this.j = o.B(context);
        this.k = com.mbridge.msdk.foundation.controller.a.f().l();
        this.l = com.mbridge.msdk.foundation.controller.a.f().k();
        this.m = String.valueOf(w.h(context));
        this.n = String.valueOf(w.g(context));
        this.p = String.valueOf(w.d(context));
        if (context.getResources().getConfiguration().orientation == 2) {
            this.o = "landscape";
        } else {
            this.o = "portrait";
        }
        this.g = o.a(context);
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                jSONObject.put("device", this.a);
                jSONObject.put("system_version", this.b);
                jSONObject.put("network_type", this.h);
                jSONObject.put("network_type_str", this.i);
                jSONObject.put("device_ua", this.j);
            }
            jSONObject.put("plantform", this.c);
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_imei_mac")) {
                jSONObject.put("device_imei", this.d);
            }
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a("authority_android_id")) {
                jSONObject.put("android_id", this.e);
            }
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_DEVICE_ID)) {
                jSONObject.put("google_ad_id", this.f);
                jSONObject.put("oaid", this.g);
            }
            jSONObject.put("appkey", this.k);
            jSONObject.put("appId", this.l);
            jSONObject.put("screen_width", this.m);
            jSONObject.put("screen_height", this.n);
            jSONObject.put("orientation", this.o);
            jSONObject.put("scale", this.p);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
