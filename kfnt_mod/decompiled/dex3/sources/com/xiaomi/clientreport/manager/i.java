package com.xiaomi.clientreport.manager;

import com.xiaomi.push.bo;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class i implements Runnable {
    final /* synthetic */ a a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ bo f41a;

    i(a aVar, bo boVar) {
        this.a = aVar;
        this.f41a = boVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.f41a.run();
    }
}
