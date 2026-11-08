package com.mbridge.msdk.foundation.tools;

import android.content.Context;
import com.kwad.v8.Platform;
import com.mbridge.msdk.MBridgeConstans;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: DomainDeviceInfo.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class c {
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
    public String q;
    public String r;
    public String c = Platform.ANDROID;
    public String a = o.u();
    public String b = o.y();
    public String f = o.A();

    public c(Context context) {
        this.d = o.b(context);
        this.e = o.g(context);
        int iC = o.C(context);
        this.g = String.valueOf(iC);
        this.h = o.a(context, iC);
        this.i = o.B(context);
        this.j = com.mbridge.msdk.foundation.controller.a.f().l();
        this.k = com.mbridge.msdk.foundation.controller.a.f().k();
        this.l = String.valueOf(w.h(context));
        this.m = String.valueOf(w.g(context));
        this.q = String.valueOf(w.d(context));
        if (context.getResources().getConfiguration().orientation == 2) {
            this.n = "landscape";
        } else {
            this.n = "portrait";
        }
        this.r = o.a(context);
        this.o = com.mbridge.msdk.foundation.same.a.s;
        this.p = com.mbridge.msdk.foundation.same.a.t;
    }

    public final JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            if (com.mbridge.msdk.foundation.controller.authoritycontroller.a.a().a(MBridgeConstans.AUTHORITY_GENERAL_DATA)) {
                jSONObject.put("device", this.a);
                jSONObject.put("system_version", this.b);
                jSONObject.put("network_type", this.g);
                jSONObject.put("network_type_str", this.h);
                jSONObject.put("device_ua", this.i);
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
                jSONObject.put("oaid", this.r);
            }
            jSONObject.put("appkey", this.j);
            jSONObject.put("appId", this.k);
            jSONObject.put("screen_width", this.l);
            jSONObject.put("screen_height", this.m);
            jSONObject.put("orientation", this.n);
            jSONObject.put("scale", this.q);
            jSONObject.put(com.huawei.updatesdk.service.d.a.b.a, this.o);
            jSONObject.put("c", this.p);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
