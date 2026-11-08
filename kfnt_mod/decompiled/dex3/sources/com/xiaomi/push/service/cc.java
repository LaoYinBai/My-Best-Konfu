package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class cc extends XMPushService.i {
    final /* synthetic */ XMPushService a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    cc(XMPushService xMPushService, int i) {
        super(i);
        this.a = xMPushService;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "prepare the mi push account.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        y.a(this.a);
        if (com.xiaomi.push.bg.b(this.a)) {
            this.a.a(true);
        }
    }
}
