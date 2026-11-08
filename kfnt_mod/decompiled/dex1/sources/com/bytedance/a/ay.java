package com.bytedance.a;

import kotlin.jvm.internal.Intrinsics;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class ay extends bd {

    /* renamed from: b, reason: collision with root package name */
    @org.b.a.e
    public String f3383b;

    /* renamed from: c, reason: collision with root package name */
    @org.b.a.e
    public String f3384c;

    @org.b.a.e
    public String d;

    @org.b.a.e
    public String f;
    public boolean g;
    public boolean h;

    @org.b.a.e
    public String k;

    @org.b.a.e
    public String l;

    @org.b.a.e
    public String m;

    @org.b.a.e
    public String n;

    @org.b.a.e
    public String o;

    @org.b.a.e
    public String p;

    @org.b.a.e
    public String q;

    @org.b.a.e
    public String r;

    @org.b.a.e
    public String s;

    /* renamed from: a, reason: collision with root package name */
    @org.b.a.d
    public String f3382a = "";

    @org.b.a.d
    public String e = "";

    @org.b.a.d
    public String i = "";

    @org.b.a.d
    public String j = "";

    @Override // com.bytedance.a.bd
    @org.b.a.d
    public JSONObject a() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("aid", this.f3382a);
        jSONObject.put(com.bytedance.b.a.c.h.x, this.f3383b);
        jSONObject.put("bd_did", this.f3384c);
        jSONObject.put("install_id", this.d);
        jSONObject.put("os", this.e);
        jSONObject.put("idfa", this.k);
        jSONObject.put("caid", this.f);
        jSONObject.put("androidid", this.l);
        jSONObject.put("imei", this.m);
        jSONObject.put("oaid", this.n);
        jSONObject.put("google_aid", this.o);
        jSONObject.put("ip", this.p);
        jSONObject.put("ua", this.q);
        jSONObject.put("device_model", this.r);
        jSONObject.put("os_version", this.s);
        jSONObject.put("is_new_user", this.g);
        jSONObject.put("exist_app_cache", this.h);
        jSONObject.put("app_version", this.i);
        jSONObject.put("channel", this.j);
        return jSONObject;
    }

    @Override // com.bytedance.a.bd
    public void a(@org.b.a.d JSONObject jSONObject) {
        Intrinsics.checkParameterIsNotNull(jSONObject, "json");
    }

    @org.b.a.d
    public String toString() {
        StringBuilder sbA = a.a("AttributionRequest(aid='");
        sbA.append(this.f3382a);
        sbA.append("', deviceID=");
        sbA.append(this.f3383b);
        sbA.append(", bdDid=");
        sbA.append(this.f3384c);
        sbA.append(", installId=");
        sbA.append(this.d);
        sbA.append(", os='");
        sbA.append(this.e);
        sbA.append("', caid=");
        sbA.append(this.f);
        sbA.append(", isNewUser=");
        sbA.append(this.g);
        sbA.append(", existAppCache=");
        sbA.append(this.h);
        sbA.append(", appVersion='");
        sbA.append(this.i);
        sbA.append("', channel='");
        sbA.append(this.j);
        sbA.append("', idfa=");
        sbA.append(this.k);
        sbA.append(", androidId=");
        sbA.append(this.l);
        sbA.append(", imei=");
        sbA.append(this.m);
        sbA.append(", oaid=");
        sbA.append(this.n);
        sbA.append(", googleAid=");
        sbA.append(this.o);
        sbA.append(", ip=");
        sbA.append(this.p);
        sbA.append(", ua=");
        sbA.append(this.q);
        sbA.append(", deviceModel=");
        sbA.append(this.r);
        sbA.append(", osVersion=");
        sbA.append(this.s);
        sbA.append(')');
        return sbA.toString();
    }
}
