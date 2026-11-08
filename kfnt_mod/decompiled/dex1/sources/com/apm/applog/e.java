package com.apm.applog;

import b.b.a.k.w;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    public static final String f2744a = "https://";

    /* renamed from: b, reason: collision with root package name */
    public static final String f2745b = "https://tbm.snssdk.com";

    /* renamed from: c, reason: collision with root package name */
    public static final String f2746c = "/apm/device_register";
    public static final String d = "/service/2/app_alert_check/";
    public static final String e = "/monitor/collect/c/session";
    public static final String f = "/service/2/log_settings/";
    public static final String g = "/service/2/abtest_config/";
    public static final String h = "/service/2/profile/";
    public static final String i = "/service/2/alink_data";
    public static final String j = "/service/2/attribution_data";
    public final String k;
    public final String l;
    public final String[] m;
    public final String[] n;
    public final String o;
    public final String p;
    public final String q;
    public final String r;
    public final String s;
    public final String t;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        public String f2747a;

        /* renamed from: b, reason: collision with root package name */
        public String f2748b;

        /* renamed from: c, reason: collision with root package name */
        public String[] f2749c;
        public String[] d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;

        public a a(String str) {
            this.f2747a = str;
            return this;
        }

        public a a(String[] strArr) {
            this.f2749c = strArr;
            return this;
        }

        public e a() {
            return new e(this, null);
        }

        public a b(String str) {
            this.f2748b = str;
            return this;
        }

        public a b(String[] strArr) {
            this.d = strArr;
            return this;
        }

        public a c(String str) {
            this.e = str;
            return this;
        }

        public a d(String str) {
            this.f = str;
            return this;
        }

        public a e(String str) {
            this.g = str;
            return this;
        }

        public a f(String str) {
            this.h = str;
            return this;
        }

        public a g(String str) {
            this.i = str;
            return this;
        }

        public a h(String str) {
            this.j = str;
            return this;
        }
    }

    public /* synthetic */ e(a aVar, b bVar) {
        this.k = aVar.f2747a;
        this.l = aVar.f2748b;
        this.m = aVar.f2749c;
        this.n = aVar.d;
        this.o = aVar.e;
        this.p = aVar.f;
        this.q = aVar.g;
        this.r = aVar.h;
        this.s = aVar.i;
        this.t = aVar.j;
    }

    public static e a(int i2) {
        return w.f1152a;
    }

    public static e a(String str, String[] strArr) {
        a aVar = new a();
        aVar.a(str + f2746c).b(str + "/service/2/app_alert_check/").h(str + "/service/2/attribution_data").g(str + "/service/2/alink_data");
        if (strArr == null || strArr.length == 0) {
            aVar.a(new String[]{str + e});
        } else {
            String[] strArr2 = new String[strArr.length + 1];
            strArr2[0] = str + e;
            for (int i2 = 1; i2 < strArr2.length; i2++) {
                strArr2[i2] = b.a.a.a.a.a(new StringBuilder(), strArr[i2 - 1], e);
            }
            aVar.a(strArr2);
        }
        aVar.c(str + "/service/2/log_settings/").d(str + "/service/2/abtest_config/").e(str + "/service/2/profile/");
        return aVar.a();
    }

    public String a() {
        return this.k;
    }

    public String b() {
        return this.l;
    }

    public String[] c() {
        return this.m;
    }

    public String[] d() {
        return this.n;
    }

    public String e() {
        return this.o;
    }

    public String f() {
        return this.p;
    }

    public String g() {
        return this.q;
    }

    public String h() {
        return this.r;
    }

    public String i() {
        return this.t;
    }

    public String j() {
        return this.s;
    }
}
