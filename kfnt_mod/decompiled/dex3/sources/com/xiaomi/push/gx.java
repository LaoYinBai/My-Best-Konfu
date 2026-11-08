package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class gx extends XMPushService.i {
    final /* synthetic */ gw a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    gx(gw gwVar, int i) {
        super(i);
        this.a = gwVar;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "Handling bind stats";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        this.a.c();
    }
}
