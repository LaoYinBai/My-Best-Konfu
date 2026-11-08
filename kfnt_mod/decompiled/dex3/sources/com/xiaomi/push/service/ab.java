package com.xiaomi.push.service;

import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.service.av;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class ab implements av.b.a {
    final /* synthetic */ XMPushService a;

    ab(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    @Override // com.xiaomi.push.service.av.b.a
    public void a(av.c cVar, av.c cVar2, int i) throws InterruptedException {
        if (cVar2 == av.c.binded) {
            p.a(this.a);
            p.b(this.a);
        } else if (cVar2 == av.c.unbind) {
            p.a(this.a, ErrorCode.ERROR_SERVICE_UNAVAILABLE, " the push is not connected.");
        }
    }
}
