package com.cmic.sso.sdk.b.b;

import anet.channel.strategy.dispatch.DispatchConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class f extends g {

    /* renamed from: a, reason: collision with root package name */
    private b f5418a;

    /* renamed from: b, reason: collision with root package name */
    private a f5419b;

    public void a(b bVar) {
        this.f5418a = bVar;
    }

    public void a(a aVar) {
        this.f5419b = aVar;
    }

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f5421a;

        /* renamed from: b, reason: collision with root package name */
        private String f5422b;

        /* renamed from: c, reason: collision with root package name */
        private String f5423c;
        private String d;
        private String e;

        public String a() {
            return this.e;
        }

        public void a(String str) {
            this.e = str;
        }

        public String b() {
            return this.d;
        }

        public void b(String str) {
            this.d = str;
        }

        public String c() {
            return this.f5421a;
        }

        public void c(String str) {
            this.f5421a = str;
        }

        public String d() {
            return this.f5422b;
        }

        public void d(String str) {
            this.f5422b = str;
        }

        public String e() {
            return this.f5423c;
        }

        public void e(String str) {
            this.f5423c = str;
        }

        public String f() {
            return com.cmic.sso.sdk.d.d.a(this.e + this.d + this.f5423c + this.f5422b + "@Fdiwmxy7CBDDQNUI");
        }
    }

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private JSONObject f5420a;

        public JSONObject a() {
            return this.f5420a;
        }

        public void a(JSONObject jSONObject) {
            this.f5420a = jSONObject;
        }
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public String a() {
        return this.f5418a.d;
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject2.put(DispatchConstants.SIGN, this.f5418a.c());
            jSONObject2.put("msgid", this.f5418a.d());
            jSONObject2.put("systemtime", this.f5418a.e());
            jSONObject2.put("appid", this.f5418a.b());
            jSONObject2.put("version", this.f5418a.a());
            jSONObject.put("header", jSONObject2);
            jSONObject3.put("log", this.f5419b.a());
            jSONObject.put("body", jSONObject3);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }
}
