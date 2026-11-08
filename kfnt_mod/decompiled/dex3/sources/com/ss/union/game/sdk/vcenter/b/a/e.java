package com.ss.union.game.sdk.vcenter.b.a;

import java.util.Objects;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class e {

    public static class f {
        public static final int a = 999;
        public static final int b = 1000;
        public static final int c = 1001;
        public static final int d = 1002;
        public static final int e = 1003;
    }

    /* renamed from: com.ss.union.game.sdk.vcenter.b.a.e$e, reason: collision with other inner class name */
    private static class C0108e {
        public static final int a = 0;
        public static final int b = 9999;
        public static final int c = 10000;
        public static final int d = 10001;
        public static final int e = 10002;
        public static final int f = 10003;

        private C0108e() {
        }
    }

    public static class c {
        public static c a = new c(0, "调用成功");
        public static c b = new c(9999, "调用的api不支持");
        public static c c = new c(10000, "解析请求失败,request == null");
        public static c d = new c(10001, "找不到对应的IPC Binder");
        public static c e = new c(10003, "IPC调用执行过程中发生了错误");
        public static c f = new c(10003, "IPC的结果是null");
        private int g;
        private String h;
        private String i = "";

        public c(int i, String str) {
            this.g = i;
            this.h = str;
        }

        public c a(String str) {
            this.h = str;
            return this;
        }

        public c b(String str) {
            this.h += str;
            return this;
        }

        public c c(String str) {
            this.i = str;
            return this;
        }

        public b a(a aVar) {
            return b.a(this, aVar);
        }
    }

    public static class a {
        public static final int a = -1;
        private static final String b = "req_code";
        private static final String c = "type";
        private static final String d = "params";
        private int e;
        private int f;
        private JSONObject g;

        private a(C0107a c0107a) {
            this.e = c0107a.a;
            this.g = c0107a.c;
            this.f = c0107a.b;
        }

        private a(JSONObject jSONObject) {
            this.e = jSONObject.optInt("req_code", -1);
            this.g = jSONObject.optJSONObject(d);
            this.f = jSONObject.optInt("type", -1);
        }

        private a() {
        }

        public static a a(String str) {
            try {
                return new a(new JSONObject(str));
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        public String toString() {
            return d();
        }

        public final int a() {
            return this.e;
        }

        public final JSONObject b() {
            return this.g;
        }

        public final int c() {
            return this.f;
        }

        public final String d() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("req_code", this.e);
                jSONObject.put(d, this.g);
                jSONObject.put("type", this.f);
            } catch (Exception unused) {
            }
            return jSONObject.toString();
        }

        /* renamed from: com.ss.union.game.sdk.vcenter.b.a.e$a$a, reason: collision with other inner class name */
        public static class C0107a {
            private int a;
            private int b;
            private JSONObject c;

            public C0107a a(int i) {
                this.a = i;
                return this;
            }

            public C0107a a(JSONObject jSONObject) {
                this.c = jSONObject;
                return this;
            }

            public C0107a b(int i) {
                this.b = i;
                return this;
            }

            public a a() {
                Objects.requireNonNull(this.c, "params 信息不能为null");
                return new a(this);
            }
        }
    }

    public static class b {
        private static final String a = "req_code";
        private static final String b = "status";
        private static final String c = "message";
        private static final String d = "ext";
        private static final String e = "data";
        private int f;
        private int g;
        private String h;
        private String i;
        private JSONObject j;

        private b(a aVar) {
            this.g = -1;
            this.f = aVar.a;
            this.g = aVar.b;
            this.h = aVar.c;
            this.i = aVar.d;
            this.j = aVar.e;
        }

        private b(JSONObject jSONObject) {
            this.g = -1;
            this.f = jSONObject.optInt("req_code", -1);
            this.g = jSONObject.optInt("status", -1);
            this.h = jSONObject.optString("message", "");
            this.i = jSONObject.optString("ext", "");
            this.j = d.b(jSONObject, "data");
        }

        private b() {
            this.g = -1;
        }

        public static b a(a aVar) {
            return a(0, "", "", new JSONObject(), aVar);
        }

        public static b a(String str, JSONObject jSONObject, a aVar) {
            return a(0, str, "", jSONObject, aVar);
        }

        public static b a(int i, String str, a aVar) {
            return a(i, str, "", new JSONObject(), aVar);
        }

        public static b a(c cVar, a aVar) {
            return a(cVar.g, cVar.h, cVar.i, new JSONObject(), aVar);
        }

        private static b a(int i, String str, String str2, JSONObject jSONObject, a aVar) {
            return new a().b(i).a(str).b(str2).a(jSONObject).a(aVar == null ? -1 : aVar.a()).a();
        }

        public static final b a(String str) {
            try {
                return new b(new JSONObject(str));
            } catch (Exception e2) {
                e2.printStackTrace();
                return null;
            }
        }

        public boolean a() {
            return this.g == 0;
        }

        public String toString() {
            return g();
        }

        public int b() {
            return this.f;
        }

        public int c() {
            return this.g;
        }

        public String d() {
            return this.h;
        }

        public String e() {
            return this.i;
        }

        public JSONObject f() {
            return this.j;
        }

        public final String g() throws JSONException {
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("req_code", this.f);
                jSONObject.put("status", this.g);
                jSONObject.put("message", this.h);
                jSONObject.put("ext", this.i);
                jSONObject.put("data", this.j);
            } catch (Exception unused) {
            }
            return jSONObject.toString();
        }

        public static class a {
            private int a;
            private int b;
            private String c;
            private String d;
            private JSONObject e;

            public a a(int i) {
                this.a = i;
                return this;
            }

            public a b(int i) {
                this.b = i;
                return this;
            }

            public a a(String str) {
                this.c = str;
                return this;
            }

            public a b(String str) {
                this.d = str;
                return this;
            }

            public a a(JSONObject jSONObject) {
                this.e = jSONObject;
                return this;
            }

            public b a() {
                Objects.requireNonNull(this.c, "message 信息不能为null");
                Objects.requireNonNull(this.d, "ext 信息不能为null");
                Objects.requireNonNull(this.e, "data 信息不能为null");
                return new b(this);
            }
        }
    }

    private static class d {
        private d() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static JSONObject b(JSONObject jSONObject, String str) {
            JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(str);
            return jSONObjectOptJSONObject == null ? new JSONObject() : jSONObjectOptJSONObject;
        }
    }
}
