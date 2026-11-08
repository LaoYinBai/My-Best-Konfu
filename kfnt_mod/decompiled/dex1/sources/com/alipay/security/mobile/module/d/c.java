package com.alipay.security.mobile.module.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class c implements Runnable {

    /* renamed from: a, reason: collision with root package name */
    final /* synthetic */ b f2719a;

    c(b bVar) {
        this.f2719a = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        try {
            this.f2719a.b();
        } catch (Exception e) {
            d.a(e);
        }
    }
}
