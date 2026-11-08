package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.Cif;
import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class v extends XMPushService.i {
    final /* synthetic */ Cif a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ XMPushService f976a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f977a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    v(int i, XMPushService xMPushService, Cif cif, String str) {
        super(i);
        this.f976a = xMPushService;
        this.a = cif;
        this.f977a = str;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "send app absent ack message for message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            Cif cifA = q.a((Context) this.f976a, this.a);
            cifA.m461a().a("absent_target_package", this.f977a);
            y.a(this.f976a, cifA);
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            this.f976a.a(10, e);
        }
    }
}
