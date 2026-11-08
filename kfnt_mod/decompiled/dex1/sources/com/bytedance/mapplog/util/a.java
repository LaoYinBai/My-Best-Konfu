package com.bytedance.mapplog.util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private final String f3902a;

    /* renamed from: b, reason: collision with root package name */
    private final String f3903b;

    /* renamed from: c, reason: collision with root package name */
    private final String[] f3904c;
    private final String d;
    private final String e;

    public static class b {

        /* renamed from: a, reason: collision with root package name */
        private String f3905a;

        /* renamed from: b, reason: collision with root package name */
        private String f3906b;

        /* renamed from: c, reason: collision with root package name */
        private String[] f3907c;
        private String[] d;
        private String e;
        private String f;
        private String g;
        private String h;

        public b a(String str) {
            this.f3905a = str;
            return this;
        }

        public b b(String str) {
            this.f3906b = str;
            return this;
        }

        public b c(String str) {
            this.e = str;
            return this;
        }

        public b d(String str) {
            this.f = str;
            return this;
        }

        public b e(String str) {
            this.h = str;
            return this;
        }

        public b a(String[] strArr) {
            this.f3907c = strArr;
            return this;
        }

        public b b(String[] strArr) {
            this.d = strArr;
            return this;
        }

        public a a() {
            return new a(this);
        }
    }

    public String a() {
        return this.f3902a;
    }

    public String b() {
        return this.f3903b;
    }

    public String[] c() {
        return this.f3904c;
    }

    public String d() {
        return this.d;
    }

    public String e() {
        return this.e;
    }

    private a(b bVar) {
        this.f3902a = bVar.f3905a;
        this.f3903b = bVar.f3906b;
        this.f3904c = bVar.f3907c;
        String[] unused = bVar.d;
        this.d = bVar.e;
        this.e = bVar.f;
        String unused2 = bVar.g;
        String unused3 = bVar.h;
    }
}
