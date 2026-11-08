package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.Cif;
import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class s extends XMPushService.i {
    final /* synthetic */ Cif a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ XMPushService f973a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    s(int i, XMPushService xMPushService, Cif cif) {
        super(i);
        this.f973a = xMPushService;
        this.a = cif;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "send ack message for message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            y.a(this.f973a, q.a((Context) this.f973a, this.a));
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            this.f973a.a(10, e);
        }
    }
}
