package com.ss.union.game.sdk.vcenter.b;

import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    private static final String a = "mmyUid";
    private static final String b = "authorizationCode";
    private static final String c = "ext_json";
    private String d;
    private String e;
    private String f;

    private b() {
    }

    public b(a aVar) {
        this.d = aVar.a;
        this.e = aVar.b;
        this.f = aVar.c;
    }

    public b(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            this.d = jSONObject.optString(a, "");
            this.e = jSONObject.optString(b, "");
            this.f = jSONObject.optString("ext_json", "");
        } catch (Exception unused) {
        }
    }

    public static b a(String str) {
        return new b(str);
    }

    public String a() {
        return this.d;
    }

    public String b() {
        return this.e;
    }

    public String c() {
        return this.f;
    }

    public String d() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(a, this.d);
            jSONObject.put(b, this.e);
            jSONObject.put("ext_json", this.f);
        } catch (Exception unused) {
        }
        return jSONObject.toString();
    }

    public String toString() {
        return d();
    }

    public static class a {
        private String b;
        private String a = "";
        private String c = "";

        public a a(String str) {
            this.a = str;
            return this;
        }

        public a b(String str) {
            this.b = str;
            return this;
        }

        public a c(String str) {
            this.c = str;
            return this;
        }

        public b a() {
            Objects.requireNonNull(this.a, "vUid 不能为null");
            Objects.requireNonNull(this.b, "authorizationCode 不能为null");
            Objects.requireNonNull(this.c, "extJson 不能为null");
            return new b(this);
        }
    }
}
