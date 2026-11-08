package com.xiaomi.push;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class fz implements Runnable {
    final /* synthetic */ fw a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f415a;

    fz(fw fwVar, String str) {
        this.a = fwVar;
        this.f415a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        cu.a().a(this.f415a, true);
    }
}
