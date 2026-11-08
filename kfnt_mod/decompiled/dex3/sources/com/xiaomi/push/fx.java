package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class fx extends XMPushService.i {
    final /* synthetic */ long a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ fw f413a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    fx(fw fwVar, int i, long j) {
        super(i);
        this.f413a = fwVar;
        this.a = j;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "check the ping-pong." + this.a;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a, reason: collision with other method in class */
    public void mo342a() {
        Thread.yield();
        if (!this.f413a.c() || this.f413a.a(this.a)) {
            return;
        }
        this.f413a.b.a(22, (Exception) null);
    }
}
