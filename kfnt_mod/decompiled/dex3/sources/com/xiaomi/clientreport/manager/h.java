package com.xiaomi.clientreport.manager;

import com.xiaomi.push.bn;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h implements Runnable {
    final /* synthetic */ a a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ bn f40a;

    h(a aVar, bn bnVar) {
        this.a = aVar;
        this.f40a = bnVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f40a.run();
    }
}
