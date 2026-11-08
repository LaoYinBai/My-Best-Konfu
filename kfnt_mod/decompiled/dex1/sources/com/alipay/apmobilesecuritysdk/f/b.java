package com.alipay.apmobilesecuritysdk.f;

import java.util.LinkedList;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class b {

    /* renamed from: a, reason: collision with root package name */
    private static b f2506a = new b();

    /* renamed from: b, reason: collision with root package name */
    private Thread f2507b = null;

    /* renamed from: c, reason: collision with root package name */
    private LinkedList<Runnable> f2508c = new LinkedList<>();

    public static b a() {
        return f2506a;
    }

    static /* synthetic */ Thread b(b bVar) {
        bVar.f2507b = null;
        return null;
    }

    public final synchronized void a(Runnable runnable) {
        this.f2508c.add(runnable);
        if (this.f2507b == null) {
            this.f2507b = new Thread(new c(this));
            this.f2507b.start();
        }
    }
}
