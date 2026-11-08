package b.b.b;

import b.b.b.h;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g {

    /* renamed from: a, reason: collision with root package name */
    public static int f1164a = 2;

    /* renamed from: b, reason: collision with root package name */
    public static h f1165b = new h.a();

    public static void a(String str, String str2) {
        h hVar = f1165b;
        if (hVar == null || f1164a > 2) {
            return;
        }
        ((h.a) hVar).a(str, str2, null);
    }

    public static void b(String str, String str2) {
        a(str, str2, null);
    }

    public static void a(String str, String str2, Throwable th) {
        h hVar = f1165b;
        if (hVar == null || f1164a > 3) {
            return;
        }
        ((h.a) hVar).b(str, str2, th);
    }
}
