package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiTinyDataClient;
import com.xiaomi.push.hn;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class z implements Runnable {
    final /* synthetic */ MiTinyDataClient.a.C0164a a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ hn f100a;

    z(MiTinyDataClient.a.C0164a c0164a, hn hnVar) {
        this.a = c0164a;
        this.f100a = hnVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.f54a.add(this.f100a);
        this.a.a();
    }
}
