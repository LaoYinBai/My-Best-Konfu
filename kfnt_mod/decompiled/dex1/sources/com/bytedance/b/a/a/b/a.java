package com.bytedance.b.a.a.b;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    public long f3590a;

    /* renamed from: b, reason: collision with root package name */
    public String f3591b;

    /* renamed from: c, reason: collision with root package name */
    public String f3592c;
    public String d;
    public String e;
    public long f;

    public a() {
    }

    public a(long j, String str) {
        this.f3590a = j;
        this.e = str;
    }

    public a(long j, String str, String str2) {
        this.f3590a = j;
        this.f3591b = str;
        this.e = str2;
    }

    public a(String str, String str2, String str3, String str4, long j) {
        this.f3591b = str;
        this.f3592c = str2;
        this.d = str3;
        this.e = str4;
        this.f = j;
    }

    public a a(String str) {
        this.f3591b = str;
        return this;
    }

    public a a(long j) {
        this.f3590a = j;
        return this;
    }

    public a b(String str) {
        this.f3592c = str;
        return this;
    }

    public a c(String str) {
        this.d = str;
        return this;
    }

    public a d(String str) {
        this.e = str;
        return this;
    }

    public a b(long j) {
        this.f = j;
        return this;
    }

    public static a e(String str) {
        return new a().b(str);
    }

    public String toString() {
        return "LocalLog{id=" + this.f3590a + ", aid=" + this.f3591b + ", type='" + this.f3592c + "', type2='" + this.d + "', data='" + this.e + "', createTime=" + this.f + '}';
    }
}
