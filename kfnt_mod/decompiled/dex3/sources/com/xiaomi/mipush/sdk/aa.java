package com.xiaomi.mipush.sdk;

import com.xiaomi.mipush.sdk.MiTinyDataClient;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class aa implements Runnable {
    final /* synthetic */ MiTinyDataClient.a.C0164a a;

    aa(MiTinyDataClient.a.C0164a c0164a) {
        this.a = c0164a;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.f54a.size() != 0) {
            this.a.b();
        } else if (this.a.f55a != null) {
            this.a.f55a.cancel(false);
            this.a.f55a = null;
        }
    }
}
