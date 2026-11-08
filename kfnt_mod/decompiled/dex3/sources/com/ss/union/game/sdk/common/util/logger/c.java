package com.ss.union.game.sdk.common.util.logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class c {
    private static k a = new i();

    static {
        a((e) new a(true));
    }

    private c() {
    }

    public static void a(k kVar) {
        if (kVar != null) {
            a = kVar;
        }
    }

    public static void a(e eVar) {
        if (eVar != null) {
            a.a(eVar);
        }
    }

    public static void a() {
        a.a();
    }

    public static k a(String str) {
        return a.a(str);
    }

    public static void a(int i, String str, String str2, Throwable th) {
        a.a(i, str, str2, th);
    }

    public static void a(String str, Object... objArr) {
        a.a(str, objArr);
    }

    public static void a(Object obj) {
        a.a(obj);
    }

    public static void b(String str, Object... objArr) {
        a.a(null, str, objArr);
    }

    public static void a(Throwable th, String str, Object... objArr) {
        a.a(th, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        a.d(str, objArr);
    }

    public static void d(String str, Object... objArr) {
        a.e(str, objArr);
    }

    public static void e(String str, Object... objArr) {
        a.c(str, objArr);
    }

    public static void f(String str, Object... objArr) {
        a.f(str, objArr);
    }

    public static void b(String str) {
        a.b(str);
    }

    public static void c(String str) {
        a.c(str);
    }
}
