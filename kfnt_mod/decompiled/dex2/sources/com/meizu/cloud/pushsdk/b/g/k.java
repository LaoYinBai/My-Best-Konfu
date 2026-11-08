package com.meizu.cloud.pushsdk.b.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class k {
    private static j a;
    private static long b;

    private k() {
    }

    static j a() {
        synchronized (k.class) {
            if (a == null) {
                return new j();
            }
            j jVar = a;
            a = jVar.f;
            jVar.f = null;
            b -= 2048;
            return jVar;
        }
    }

    static void a(j jVar) {
        if (jVar.f != null || jVar.g != null) {
            throw new IllegalArgumentException();
        }
        if (jVar.d) {
            return;
        }
        synchronized (k.class) {
            if (b + 2048 > 65536) {
                return;
            }
            b += 2048;
            jVar.f = a;
            jVar.c = 0;
            jVar.b = 0;
            a = jVar;
        }
    }
}
