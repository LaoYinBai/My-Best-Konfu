package com.kwad.sdk.crash;

import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    public final com.kwad.sdk.crash.model.b a;
    public final com.kwad.sdk.crash.model.a b;
    public final f c;
    public final String[] d;
    public final String[] e;
    public final boolean f;
    public final Context g;
    public final e h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;

    public static class a {
        public int a;
        public String[] b;
        private boolean c = false;
        private Context d;
        private e e;
        private String f;
        private String g;
        private String h;
        private String i;
        private String j;
        private String k;
        private String l;
        private String m;
        private int n;
        private String o;
        private int p;
        private String q;
        private String r;
        private String s;
        private String t;
        private f u;
        private String[] v;

        public a a(int i) {
            this.n = i;
            return this;
        }

        public a a(Context context) {
            this.d = context;
            return this;
        }

        public a a(e eVar) {
            this.e = eVar;
            return this;
        }

        public a a(f fVar) {
            this.u = fVar;
            return this;
        }

        public a a(String str) {
            this.f = str;
            return this;
        }

        public a a(String[] strArr) {
            this.v = strArr;
            return this;
        }

        public b a() {
            return new b(this);
        }

        public a b(int i) {
            this.p = i;
            return this;
        }

        public a b(String str) {
            this.h = str;
            return this;
        }

        public a b(String[] strArr) {
            this.b = strArr;
            return this;
        }

        public a c(int i) {
            this.a = i;
            return this;
        }

        public a c(String str) {
            this.i = str;
            return this;
        }

        public a d(String str) {
            this.k = str;
            return this;
        }

        public a e(String str) {
            this.l = str;
            return this;
        }

        public a f(String str) {
            this.m = str;
            return this;
        }

        public a g(String str) {
            this.o = str;
            return this;
        }

        public a h(String str) {
            this.q = str;
            return this;
        }

        public a i(String str) {
            this.r = str;
            return this;
        }

        public a j(String str) {
            this.s = str;
            return this;
        }

        public a k(String str) {
            this.t = str;
            return this;
        }
    }

    private b(a aVar) {
        this.a = new com.kwad.sdk.crash.model.b();
        this.b = new com.kwad.sdk.crash.model.a();
        this.f = aVar.c;
        this.g = aVar.d;
        this.h = aVar.e;
        this.i = aVar.f;
        this.j = aVar.g;
        this.k = aVar.h;
        this.l = aVar.i;
        this.m = aVar.j;
        this.n = aVar.k;
        this.b.a = aVar.q;
        this.b.b = aVar.r;
        this.b.d = aVar.t;
        this.b.c = aVar.s;
        this.a.d = aVar.o;
        this.a.e = aVar.p;
        this.a.b = aVar.m;
        this.a.c = aVar.n;
        this.a.a = aVar.l;
        this.a.f = aVar.a;
        this.c = aVar.u;
        this.d = aVar.v;
        this.e = aVar.b;
    }

    public e a() {
        return this.h;
    }

    public boolean b() {
        return this.f;
    }
}
