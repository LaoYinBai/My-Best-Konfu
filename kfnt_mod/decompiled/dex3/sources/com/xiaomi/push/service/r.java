package com.xiaomi.push.service;

import com.xiaomi.push.Cif;
import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class r extends XMPushService.i {
    final /* synthetic */ Cif a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ XMPushService f967a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    r(int i, XMPushService xMPushService, Cif cif) {
        super(i);
        this.f967a = xMPushService;
        this.a = cif;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "send app absent message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            y.a(this.f967a, y.a(this.a.b(), this.a.m462a()));
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            this.f967a.a(10, e);
        }
    }
}
