package com.cmic.sso.sdk.b.b;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e extends g {

    /* renamed from: a, reason: collision with root package name */
    private a f5412a;

    /* renamed from: b, reason: collision with root package name */
    private byte[] f5413b;

    /* renamed from: c, reason: collision with root package name */
    private String f5414c;
    private String d;
    private boolean e = false;

    public void a(boolean z) {
        this.e = z;
    }

    public void a(String str) {
        this.d = str;
    }

    public void a(byte[] bArr) {
        this.f5413b = bArr;
    }

    public void b(String str) {
        this.f5414c = str;
    }

    public void a(a aVar) {
        this.f5412a = aVar;
    }

    public a c() {
        return this.f5412a;
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public String a() {
        return this.f5412a.a();
    }

    @Override // com.cmic.sso.sdk.b.b.g
    public JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (this.e) {
            try {
                jSONObject.put("encrypted", this.f5414c);
                jSONObject.put("reqdata", com.cmic.sso.sdk.d.a.a(this.f5413b, this.f5412a.toString()));
                jSONObject.put("securityreinforce", this.d);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject;
    }

    public static class a {
        private String E;
        private long q;

        /* renamed from: a, reason: collision with root package name */
        private String f5415a = "";

        /* renamed from: b, reason: collision with root package name */
        private String f5416b = "";

        /* renamed from: c, reason: collision with root package name */
        private String f5417c = "";
        private String d = "";
        private String e = "";
        private String f = "";
        private String g = "";
        private String h = "";
        private String i = "";
        private String j = "";
        private String k = "";
        private String l = "";
        private String m = "";
        private String n = "";
        private String o = "";
        private String p = "";
        private String r = "";
        private String s = "";
        private String t = "";
        private String u = "";
        private String v = "";
        private String w = "";
        private String x = "";
        private String y = "";
        private String z = "";
        private String A = "";
        private String B = "";
        private String C = "";
        private String D = "";

        private String x(String str) {
            return str == null ? "" : str;
        }

        public void a(String str) {
            this.C = str;
        }

        public void b(String str) {
            this.D = str;
        }

        public void c(String str) {
            this.y = str;
        }

        public void d(String str) {
            this.z = str;
        }

        public void a(long j) {
            this.q = j;
        }

        public void e(String str) {
            this.f5415a = x(str);
        }

        public void f(String str) {
            this.f5416b = x(str);
        }

        public String a() {
            return this.f5417c;
        }

        public void g(String str) {
            this.f5417c = x(str);
        }

        public void h(String str) {
            this.d = x(str);
        }

        public void i(String str) {
            this.e = x(str);
        }

        public void j(String str) {
            this.f = x(str);
        }

        public void k(String str) {
            this.g = x(str);
        }

        public void l(String str) {
            this.h = x(str);
        }

        public void m(String str) {
            this.i = x(str);
        }

        public void n(String str) {
            String strX = x(str);
            try {
                this.j = URLEncoder.encode(strX, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                this.j = strX;
            }
        }

        public void o(String str) {
            String strX = x(str);
            try {
                this.k = URLEncoder.encode(strX, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                this.k = strX;
            }
        }

        public void p(String str) {
            this.l = x(str);
        }

        public void q(String str) {
            this.m = x(str);
        }

        public void r(String str) {
            this.o = x(str);
        }

        public void s(String str) {
            this.p = x(str);
        }

        public void t(String str) {
            this.A = x(str);
        }

        public void u(String str) {
            this.B = x(str);
        }

        public void v(String str) {
            this.E = x(str);
        }

        public String w(String str) {
            return com.cmic.sso.sdk.d.d.a(this.f5416b + this.f5417c + this.d + this.e + this.f + this.g + this.h + this.i + this.j + this.k + this.l + this.m + this.o + this.p + str + this.r + this.s + this.t + this.u + this.v + this.w + this.x + this.y + this.z + this.A + this.B + this.C + this.D);
        }

        public String toString() {
            return this.f5415a + "&" + this.f5416b + "&" + this.f5417c + "&" + this.d + "&" + this.e + "&" + this.f + "&" + this.g + "&" + this.h + "&" + this.i + "&" + this.j + "&" + this.k + "&" + this.l + "&" + this.m + "&6.0&" + this.n + "&" + this.o + "&" + this.p + "&" + this.r + "&" + this.s + "&" + this.t + "&" + this.u + "&" + this.v + "&" + this.w + "&" + this.x + "&" + this.y + "&" + this.z + "&" + this.A + "&" + this.B + "&" + this.E + "&&" + this.C + "&" + this.D;
        }
    }
}
