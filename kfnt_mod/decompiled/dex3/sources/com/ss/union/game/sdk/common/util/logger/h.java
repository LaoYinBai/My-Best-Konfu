package com.ss.union.game.sdk.common.util.logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class h {
    public static final int a = 2;
    public static final int b = 3;
    public static final int c = 4;
    public static final int d = 5;
    public static final int e = 6;
    public static final int f = 7;
    private static k g = new i();

    static {
        a((e) new a(false));
    }

    private h() {
    }

    public static void a(k kVar) {
        if (kVar != null) {
            g = kVar;
        }
    }

    public static void a(e eVar) {
        if (eVar != null) {
            g.a(eVar);
        }
    }

    public static void a() {
        g.a();
    }

    public static k a(String str) {
        return g.a(str);
    }

    public static void a(int i, String str, String str2, Throwable th) {
        g.a(i, str, str2, th);
    }

    public static void a(String str, Object... objArr) {
        g.a(str, objArr);
    }

    public static void a(Object obj) {
        g.a(obj);
    }

    public static void b(String str, Object... objArr) {
        g.a(null, str, objArr);
    }

    public static void a(Throwable th, String str, Object... objArr) {
        g.a(th, str, objArr);
    }

    public static void c(String str, Object... objArr) {
        g.d(str, objArr);
    }

    public static void d(String str, Object... objArr) {
        g.e(str, objArr);
    }

    public static void e(String str, Object... objArr) {
        g.c(str, objArr);
    }

    public static void f(String str, Object... objArr) {
        g.f(str, objArr);
    }

    public static void b(String str) {
        g.b(str);
    }

    public static void c(String str) {
        g.c(str);
    }
}
