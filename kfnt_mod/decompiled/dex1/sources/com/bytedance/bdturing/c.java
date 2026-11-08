package com.bytedance.bdturing;

import android.content.Context;
import android.os.Build;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3765a = "BdTuringConfig";

    /* renamed from: b, reason: collision with root package name */
    private static final int f3766b = 0;

    /* renamed from: c, reason: collision with root package name */
    private static final int f3767c = 2;
    private boolean A;
    private b d;
    private String e;
    private String f;
    private String g;
    private String h;
    private String i;
    private String j;
    private String k;
    private String l;
    private String m;
    private String n;
    private String o;
    private String p;
    private String q;
    private String r;
    private String s;
    private Context t;
    private boolean u;
    private String v;
    private String w;
    private String x;
    private int y;
    private String z;

    public int a() {
        return 0;
    }

    public enum b {
        REGION_CHINA("cn"),
        REGION_SINGAPOER("sg"),
        REGION_USA_EAST("va");

        private String d;

        b(String str) {
            this.d = str;
        }

        public String a() {
            return this.d;
        }
    }

    private c(a aVar) {
        String[] strArrSplit;
        this.i = d.f;
        this.m = "Android";
        this.n = "" + Build.VERSION.SDK_INT;
        this.p = Build.BRAND;
        this.q = Build.MODEL;
        this.d = aVar.f3768a;
        this.e = aVar.f3769b;
        this.f = aVar.f3770c;
        this.g = aVar.d;
        this.h = aVar.e;
        this.j = aVar.f;
        this.l = aVar.g;
        this.x = Locale.getDefault().toString();
        if (this.x != null && (strArrSplit = this.x.split("_")) != null && strArrSplit.length > 2) {
            this.x = strArrSplit[0] + "_" + strArrSplit[1];
        }
        try {
            this.p = URLEncoder.encode(Build.BRAND, "utf-8");
            this.q = URLEncoder.encode(Build.MODEL, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        this.z = aVar.o;
        this.k = aVar.k;
        this.o = aVar.l;
        this.r = aVar.m;
        this.s = aVar.n;
        this.t = aVar.h;
        this.u = aVar.i;
        this.v = aVar.j;
        this.A = aVar.p;
    }

    public b b() {
        return this.d;
    }

    public String c() {
        return this.e;
    }

    public String d() {
        return this.g;
    }

    public String e() {
        return this.l;
    }

    public String f() {
        return this.f;
    }

    public String g() {
        return this.i;
    }

    public String h() {
        return this.j;
    }

    public String i() {
        return this.h;
    }

    public String j() {
        return this.p;
    }

    public String k() {
        return this.q;
    }

    public String l() {
        return this.n;
    }

    public String m() {
        return this.m;
    }

    public Context n() {
        return this.t;
    }

    public boolean o() {
        return this.u;
    }

    public String p() {
        return this.v;
    }

    public String q() {
        return this.w;
    }

    public String r() {
        return this.k;
    }

    public String s() {
        return this.o;
    }

    public String t() {
        return this.r;
    }

    public String u() {
        return this.s;
    }

    public String v() {
        return this.x;
    }

    public int w() {
        return this.y;
    }

    public String x() {
        return this.z;
    }

    public boolean y() {
        return this.A;
    }

    public c a(String str) {
        this.k = str;
        return this;
    }

    public c b(String str) {
        this.v = str;
        return this;
    }

    public c c(String str) {
        this.o = str;
        return this;
    }

    public c d(String str) {
        this.r = str;
        return this;
    }

    public c e(String str) {
        this.s = str;
        return this;
    }

    public c a(int i) {
        this.y = i;
        return this;
    }

    public c f(String str) {
        this.z = str;
        return this;
    }

    public c a(boolean z) {
        this.A = z;
        return this;
    }

    public c b(boolean z) {
        this.u = z;
        return this;
    }

    public c g(String str) {
        this.w = str;
        return this;
    }

    public static class a {

        /* renamed from: b, reason: collision with root package name */
        private String f3769b;

        /* renamed from: c, reason: collision with root package name */
        private String f3770c;
        private String d;
        private String e;
        private String g;
        private Context h;
        private String j;
        private String k;
        private String l;
        private String m;
        private String n;
        private String o;

        /* renamed from: a, reason: collision with root package name */
        private b f3768a = b.REGION_CHINA;
        private String f = "";
        private boolean i = false;
        private boolean p = true;

        public c a(Context context) {
            this.h = context;
            return new c(this);
        }

        public a a(String str) {
            this.f3769b = str;
            return this;
        }

        public a b(String str) {
            this.d = str;
            return this;
        }

        public a c(String str) {
            this.g = str;
            return this;
        }

        public a d(String str) {
            this.f3770c = str;
            return this;
        }

        public a e(String str) {
            this.e = str;
            return this;
        }

        public a a(boolean z) {
            this.i = z;
            return this;
        }

        public a f(String str) {
            this.j = str;
            return this;
        }

        public a a(b bVar) {
            this.f3768a = bVar;
            return this;
        }

        public a g(String str) {
            this.f = str;
            return this;
        }

        public a h(String str) {
            this.k = str;
            return this;
        }

        public a i(String str) {
            this.l = str;
            return this;
        }

        public a j(String str) {
            this.m = str;
            return this;
        }

        public a k(String str) {
            this.n = str;
            return this;
        }

        public a l(String str) {
            this.o = str;
            return this;
        }

        public a b(boolean z) {
            this.p = z;
            return this;
        }
    }
}
