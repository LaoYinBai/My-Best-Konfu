package com.bytedance.services.apm.api;

import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private String f5318a;

    /* renamed from: b, reason: collision with root package name */
    private int f5319b;

    /* renamed from: c, reason: collision with root package name */
    private JSONObject f5320c;
    private JSONObject d;
    private JSONObject e;
    private boolean f;

    public String a() {
        return this.f5318a;
    }

    public JSONObject b() {
        return this.f5320c;
    }

    public JSONObject c() {
        return this.d;
    }

    public int d() {
        return this.f5319b;
    }

    public JSONObject e() {
        return this.e;
    }

    public boolean f() {
        return this.f;
    }

    public static a g() {
        return new a();
    }

    public c(a aVar) {
        this.f5318a = aVar.f5321a;
        this.f5319b = aVar.f5322b;
        this.f5320c = aVar.f5323c;
        this.d = aVar.d;
        this.e = aVar.e;
        this.f = aVar.f;
    }

    public static final class a {

        /* renamed from: a, reason: collision with root package name */
        private String f5321a;

        /* renamed from: b, reason: collision with root package name */
        private int f5322b;

        /* renamed from: c, reason: collision with root package name */
        private JSONObject f5323c;
        private JSONObject d;
        private JSONObject e;
        private boolean f;

        private a() {
        }

        public a a(String str) {
            this.f5321a = str;
            return this;
        }

        public a a(int i) {
            this.f5322b = i;
            return this;
        }

        public a a(JSONObject jSONObject) {
            this.f5323c = jSONObject;
            return this;
        }

        public a b(JSONObject jSONObject) {
            this.d = jSONObject;
            return this;
        }

        public a c(JSONObject jSONObject) {
            this.e = jSONObject;
            return this;
        }

        public a a(boolean z) {
            this.f = z;
            return this;
        }

        public c a() {
            return new c(this);
        }
    }
}
