package com.ss.union.game.sdk.vcenter.b;

import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "is_visitor";
    private static final String b = "galaxy_open_id";
    private static final String c = "is_login";
    private static final String d = "show_back_btn";
    private static final String e = "show_close_btn";
    private static final String f = "from_real_name_select";
    private static final String g = "pn";
    private static final String h = "did";
    private static final String i = "ext_json";
    private static final String j = "auto_login_fail";
    private JSONObject k = new JSONObject();

    public a(C0106a c0106a) throws JSONException {
        try {
            this.k.put("is_login", c0106a.a);
            this.k.put(a, c0106a.b);
            this.k.put("galaxy_open_id", c0106a.c);
            this.k.put(d, c0106a.f);
            this.k.put(e, c0106a.g);
            this.k.put(f, c0106a.d);
            this.k.put("ext_json", c0106a.j);
            this.k.put(g, c0106a.h);
            this.k.put(h, c0106a.i);
            this.k.put(j, c0106a.e);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    private a() {
    }

    public static C0106a a() {
        return new C0106a();
    }

    public static C0106a a(a aVar) {
        return new C0106a(aVar);
    }

    public static a a(String str) {
        a aVar = new a();
        try {
            aVar.k = new JSONObject(str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return aVar;
    }

    public boolean b() {
        return this.k.optBoolean(a, false);
    }

    public String c() {
        return this.k.optString("galaxy_open_id", "");
    }

    public boolean d() {
        return this.k.optBoolean("is_login", false);
    }

    public boolean e() {
        return this.k.optBoolean(d, false);
    }

    public boolean f() {
        return this.k.optBoolean(e, true);
    }

    public boolean g() {
        return this.k.optBoolean(f, false);
    }

    public boolean h() {
        return this.k.optBoolean(j, false);
    }

    public void i() throws JSONException {
        try {
            this.k.put(d, false);
            this.k.put(e, false);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public boolean j() {
        return e() || f();
    }

    public String k() {
        return this.k.optString("ext_json", "");
    }

    public String l() {
        return this.k.toString();
    }

    public String m() {
        return this.k.optString(g, "");
    }

    public String n() {
        return this.k.optString(h, "");
    }

    public String toString() {
        return this.k.toString();
    }

    /* renamed from: com.ss.union.game.sdk.vcenter.b.a$a, reason: collision with other inner class name */
    public static class C0106a {
        private boolean a;
        private boolean b;
        private String c;
        private boolean d;
        private boolean e;
        private boolean f;
        private boolean g;
        private String h;
        private String i;
        private String j;

        public C0106a() {
            this.a = false;
            this.b = false;
            this.c = "";
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = true;
            this.h = "";
            this.i = "";
            this.j = "";
        }

        public C0106a(a aVar) {
            this.a = false;
            this.b = false;
            this.c = "";
            this.d = false;
            this.e = false;
            this.f = false;
            this.g = true;
            this.h = "";
            this.i = "";
            this.j = "";
            this.c = aVar.c();
            this.h = aVar.m();
            this.i = aVar.n();
            this.a = aVar.d();
            this.b = aVar.b();
            this.d = aVar.g();
            this.f = aVar.e();
            this.g = aVar.f();
            this.j = aVar.k();
        }

        public C0106a a(boolean z) {
            this.a = z;
            return this;
        }

        public C0106a b(boolean z) {
            this.b = z;
            return this;
        }

        public C0106a a(String str) {
            this.c = str;
            return this;
        }

        public C0106a c(boolean z) {
            this.f = z;
            return this;
        }

        public C0106a d(boolean z) {
            this.g = z;
            return this;
        }

        public C0106a e(boolean z) {
            this.d = z;
            return this;
        }

        public C0106a b(String str) {
            this.j = str;
            return this;
        }

        public C0106a c(String str) {
            this.i = str;
            return this;
        }

        public C0106a d(String str) {
            this.h = str;
            return this;
        }

        public C0106a a() {
            this.e = true;
            return this;
        }

        public a b() {
            return new a(this);
        }
    }
}
