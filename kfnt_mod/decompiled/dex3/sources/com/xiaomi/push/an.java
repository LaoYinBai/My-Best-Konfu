package com.xiaomi.push;

import com.xiaomi.push.al;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class an implements Runnable {
    final /* synthetic */ al.b a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ al f117a;

    an(al alVar, al.b bVar) {
        this.f117a = alVar;
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f117a.a(this.a);
    }
}
