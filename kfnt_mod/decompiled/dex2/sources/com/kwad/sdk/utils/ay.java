package com.kwad.sdk.utils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ay {
    private static volatile ay l;
    private volatile boolean a = false;
    private volatile boolean b = false;
    private volatile boolean c = false;
    private volatile boolean d = false;
    private volatile boolean e = false;
    private volatile boolean f = false;
    private volatile long g = 0;
    private volatile long h = 0;
    private volatile long i = 0;
    private volatile long j = -1;
    private volatile boolean k = false;

    private ay() {
    }

    public static ay a() {
        if (l == null) {
            synchronized (ay.class) {
                if (l == null) {
                    l = new ay();
                }
            }
        }
        return l;
    }

    public void a(long j) {
        this.j = j;
    }

    public boolean b() {
        return this.f;
    }
}
