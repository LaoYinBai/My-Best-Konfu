package com.xiaomi.push.service;

import com.xiaomi.push.Cif;
import com.xiaomi.push.ga;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.ia;
import com.xiaomi.push.ii;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class x extends XMPushService.i {
    final /* synthetic */ Cif a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ ii f980a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ XMPushService f981a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    x(int i, ii iiVar, Cif cif, XMPushService xMPushService) {
        super(i);
        this.f980a = iiVar;
        this.a = cif;
        this.f981a = xMPushService;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "send ack message for clear push message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            ia iaVar = new ia();
            iaVar.c(ht.CancelPushMessageACK.f509a);
            iaVar.a(this.f980a.m476a());
            iaVar.a(this.f980a.a());
            iaVar.b(this.f980a.b());
            iaVar.e(this.f980a.c());
            iaVar.a(0L);
            iaVar.d("success clear push message.");
            y.a(this.f981a, y.b(this.a.b(), this.a.m462a(), iaVar, hj.Notification));
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.d("clear push message. " + e);
            this.f981a.a(10, e);
        }
    }
}
