package com.xiaomi.push;

import com.xiaomi.push.al;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class gt extends al.b {
    final /* synthetic */ Runnable a;

    gt(Runnable runnable) {
        this.a = runnable;
    }

    @Override // com.xiaomi.push.al.b
    public void b() {
        this.a.run();
    }
}
