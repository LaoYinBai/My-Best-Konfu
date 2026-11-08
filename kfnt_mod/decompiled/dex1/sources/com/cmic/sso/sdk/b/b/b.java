package com.cmic.sso.sdk.b.b;

import anet.channel.strategy.dispatch.DispatchConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b extends g {

    /* renamed from: a, reason: collision with root package name */
    private String f5405a;

    /* renamed from: b, reason: collision with root package name */
    private String f5406b;

    /* renamed from: c, reason: collision with root package name */
    private String f5407c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;

    @Override // com.cmic.sso.sdk.b.b.g
    public String a() {
        return this.f;
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.f5405a);
            jSONObject.put("apptype", this.f5406b);
            jSONObject.put("phone_ID", this.f5407c);
            jSONObject.put("certflag", this.d);
            jSONObject.put("sdkversion", this.e);
            jSONObject.put("appid", this.f);
            jSONObject.put("expandparams", this.g);
            jSONObject.put(DispatchConstants.SIGN, this.h);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public void a(String str) {
        this.f5405a = str;
    }

    public void b(String str) {
        this.f5406b = str;
    }

    public void c(String str) {
        this.f5407c = str;
    }

    public void d(String str) {
        this.d = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void f(String str) {
        this.f = str;
    }

    public void g(String str) {
        this.h = str;
    }

    public String c() {
        return n(this.f5405a + this.e + this.f + "iYm0HAnkxQtpvN44").toLowerCase();
    }
}
