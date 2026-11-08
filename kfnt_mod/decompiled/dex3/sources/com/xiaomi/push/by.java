package com.xiaomi.push;

import com.xiaomi.push.ai;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class by extends ai.a {
    final /* synthetic */ bw a;

    by(bw bwVar) {
        this.a = bwVar;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "10054";
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.channel.commonutils.logger.b.c("exec== DbSizeControlJob");
        ci.a(this.a.f166a).a(new cb(this.a.c(), new WeakReference(this.a.f166a)));
        this.a.b("check_time");
    }
}
