package com.xiaomi.push;

import java.util.Date;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bv implements fs {
    final /* synthetic */ bu a;

    bv(bu buVar) {
        this.a = buVar;
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar) {
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.a.f164a.format(new Date()) + " Connection reconnected (" + this.a.f161a.hashCode() + com.umeng.message.proguard.l.t);
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar, int i, Exception exc) {
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.a.f164a.format(new Date()) + " Connection closed (" + this.a.f161a.hashCode() + com.umeng.message.proguard.l.t);
    }

    @Override // com.xiaomi.push.fs
    public void a(fp fpVar, Exception exc) {
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.a.f164a.format(new Date()) + " Reconnection failed due to an exception (" + this.a.f161a.hashCode() + com.umeng.message.proguard.l.t);
        exc.printStackTrace();
    }

    @Override // com.xiaomi.push.fs
    public void b(fp fpVar) {
        com.xiaomi.channel.commonutils.logger.b.c("[Slim] " + this.a.f164a.format(new Date()) + " Connection started (" + this.a.f161a.hashCode() + com.umeng.message.proguard.l.t);
    }
}
