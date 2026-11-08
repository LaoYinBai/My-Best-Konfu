package com.unity3d.player;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class p {
    private static boolean a;
    private boolean b = false;
    private boolean c = false;
    private boolean d = true;
    private boolean e = false;

    p() {
    }

    static void a() {
        a = true;
    }

    static void b() {
        a = false;
    }

    static boolean c() {
        return a;
    }

    final void a(boolean z) {
        this.b = z;
    }

    final void b(boolean z) {
        this.d = z;
    }

    final void c(boolean z) {
        this.e = z;
    }

    final void d(boolean z) {
        this.c = z;
    }

    final boolean d() {
        return this.d;
    }

    final boolean e() {
        return this.e;
    }

    final boolean f() {
        return a && this.b && !this.d && !this.c;
    }

    final boolean g() {
        return this.c;
    }

    public final String toString() {
        return super.toString();
    }
}
