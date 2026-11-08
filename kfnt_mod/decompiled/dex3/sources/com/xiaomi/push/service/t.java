package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.Cif;
import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class t extends XMPushService.i {
    final /* synthetic */ Cif a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ XMPushService f974a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    t(int i, XMPushService xMPushService, Cif cif) {
        super(i);
        this.f974a = xMPushService;
        this.a = cif;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "send ack message for obsleted message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            Cif cifA = q.a((Context) this.f974a, this.a);
            cifA.m461a().a("message_obsleted", "1");
            y.a(this.f974a, cifA);
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            this.f974a.a(10, e);
        }
    }
}
