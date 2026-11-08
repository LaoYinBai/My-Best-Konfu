package com.huawei.a.a.b.e;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private static b a = new b();

    public static void a(String str, String str2) {
        if (!a() || str == null || str2 == null) {
            return;
        }
        a.a(3, str, str2);
    }

    public static void a(String str, String str2, Object... objArr) {
        if (!b() || str == null || str2 == null) {
            return;
        }
        a.a(4, str, String.format(str2, objArr));
    }

    private static boolean a() {
        return a.a(3);
    }

    public static void b(String str, String str2) {
        if (!b() || str == null || str2 == null) {
            return;
        }
        a.a(4, str, str2);
    }

    private static boolean b() {
        return a.a(4);
    }

    public static void c(String str, String str2) {
        if (!c() || str == null || str2 == null) {
            return;
        }
        a.a(5, str, str2);
    }

    private static boolean c() {
        return a.a(5);
    }

    public static void d(String str, String str2) {
        if (!d() || str == null || str2 == null) {
            return;
        }
        a.a(6, str, str2);
    }

    private static boolean d() {
        return a.a(6);
    }
}
