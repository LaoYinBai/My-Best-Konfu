package com.xiaomi.push;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class dp implements Runnable {
    final /* synthetic */ Cdo a;

    dp(Cdo cdo) {
        this.a = cdo;
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.channel.commonutils.logger.b.b("cde handle intent in sjm");
        this.a.f246a.b(this.a.a, this.a.f245a);
    }
}
