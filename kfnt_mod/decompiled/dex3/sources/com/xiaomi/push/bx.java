package com.xiaomi.push;

import com.xiaomi.push.ai;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bx extends ai.a {
    final /* synthetic */ bw a;

    bx(bw bwVar) {
        this.a = bwVar;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "10052";
    }

    @Override // java.lang.Runnable
    public void run() {
        com.xiaomi.channel.commonutils.logger.b.c("exec== mUploadJob");
        if (this.a.f169a != null) {
            this.a.f169a.a(this.a.f166a);
            this.a.b("upload_time");
        }
    }
}
