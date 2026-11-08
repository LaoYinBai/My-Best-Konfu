package com.huawei.a.a.e;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a extends com.huawei.a.a.b.c.b.a {
    private String g;
    private String c = "";
    private String d = "";
    private String e = "";
    private String f = "";
    protected String b = "";

    public void b(String str) {
        this.c = str;
    }

    @Override // com.huawei.a.a.b.c.b.f
    public JSONObject c() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("androidid", this.a);
        jSONObject.put("oaid", this.g);
        jSONObject.put("uuid", this.b);
        jSONObject.put("upid", this.f);
        jSONObject.put("imei", this.c);
        jSONObject.put("sn", this.d);
        jSONObject.put("udid", this.e);
        return jSONObject;
    }

    public void c(String str) {
        this.d = str;
    }

    public void d(String str) {
        this.f = str;
    }

    public void e(String str) {
        this.e = str;
    }

    public void f(String str) {
        this.b = str;
    }

    public void g(String str) {
        this.g = str;
    }
}
