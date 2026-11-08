package com.cmic.sso.sdk.b.b;

import anet.channel.strategy.dispatch.DispatchConstants;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends g {

    /* renamed from: a, reason: collision with root package name */
    private String f5399a;

    /* renamed from: b, reason: collision with root package name */
    private String f5400b;

    /* renamed from: c, reason: collision with root package name */
    private String f5401c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private long l;
    private String m;
    private JSONObject n;

    public void a(JSONObject jSONObject) {
        this.n = jSONObject;
    }

    public void a(long j) {
        this.l = j;
    }

    public void a(String str) {
        this.h = str;
    }

    public void b(String str) {
        this.i = str;
    }

    public void c(String str) {
        this.m = str;
    }

    public void d(String str) {
        this.f = str;
    }

    public void e(String str) {
        this.g = str;
    }

    public void f(String str) {
        this.f5399a = str;
    }

    public void g(String str) {
        this.f5400b = str;
    }

    public void h(String str) {
        this.f5401c = str;
    }

    public void i(String str) {
        this.d = str;
    }

    public void j(String str) {
        this.e = str;
    }

    public String toString() {
        return b().toString();
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public String a() {
        return this.f5401c;
    }

    public void k(String str) {
        this.j = str;
    }

    public void l(String str) {
        this.k = str;
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", this.f5399a);
            jSONObject.put("msgid", this.f5400b);
            jSONObject.put("appid", this.f5401c);
            jSONObject.put("scrip", this.d);
            jSONObject.put(DispatchConstants.SIGN, this.e);
            jSONObject.put("interfacever", this.f);
            jSONObject.put("userCapaid", this.g);
            jSONObject.put("clienttype", this.h);
            jSONObject.put("sourceid", this.i);
            jSONObject.put("authenticated_appid", this.j);
            jSONObject.put("genTokenByAppid", this.k);
            jSONObject.put("rcData", this.n);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONObject;
    }

    public String m(String str) {
        return n(this.f5399a + this.f5401c + str + this.d);
    }

    /* renamed from: com.cmic.sso.sdk.b.b.a$a, reason: collision with other inner class name */
    public static class C0163a {

        /* renamed from: a, reason: collision with root package name */
        private String f5402a;

        /* renamed from: b, reason: collision with root package name */
        private String f5403b;

        /* renamed from: c, reason: collision with root package name */
        private String f5404c;
        private String d;
        private String e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;

        public void a(String str) {
            this.f5402a = str;
        }

        public void b(String str) {
            this.f5403b = str;
        }

        public void c(String str) {
            this.f5404c = str;
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
            this.g = str;
        }

        public void h(String str) {
            this.h = str;
        }

        public void i(String str) {
            this.i = str;
        }

        public void j(String str) {
            this.j = str;
        }

        public void k(String str) {
            this.k = str;
        }

        public JSONObject a() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("phone_id", this.f5402a);
                jSONObject.put("os", this.f5403b);
                jSONObject.put("dev_model", this.f5404c);
                jSONObject.put("dev_brand", this.d);
                jSONObject.put(DispatchConstants.MNC, this.e);
                jSONObject.put("client_type", this.f);
                jSONObject.put(com.bytedance.b.a.a.d.s, this.g);
                jSONObject.put("ipv4_list", this.h);
                jSONObject.put("ipv6_list", this.i);
                jSONObject.put("is_cert", this.j);
                jSONObject.put("is_root", this.k);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return jSONObject;
        }
    }
}
