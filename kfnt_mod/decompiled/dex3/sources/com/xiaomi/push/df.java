package com.xiaomi.push;

import com.xiaomi.push.al;
import com.xiaomi.push.dd;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class df extends al.b {
    al.b a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ dd f236a;

    df(dd ddVar) {
        this.f236a = ddVar;
    }

    @Override // com.xiaomi.push.al.b
    public void b() {
        dd.b bVar = (dd.b) this.f236a.f224a.peek();
        if (bVar == null || !bVar.a()) {
            return;
        }
        if (this.f236a.f224a.remove(bVar)) {
            this.a = bVar;
        }
        if (this.a != null) {
            this.a.b();
        }
    }

    @Override // com.xiaomi.push.al.b
    /* renamed from: c */
    public void mo197c() {
        if (this.a != null) {
            this.a.mo197c();
        }
    }
}
