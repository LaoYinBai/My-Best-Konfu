package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.Cif;
import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class w extends XMPushService.i {
    final /* synthetic */ Cif a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ XMPushService f978a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f979a;
    final /* synthetic */ String b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    w(int i, XMPushService xMPushService, Cif cif, String str, String str2) {
        super(i);
        this.f978a = xMPushService;
        this.a = cif;
        this.f979a = str;
        this.b = str2;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "send wrong message ack for message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            Cif cifA = q.a((Context) this.f978a, this.a);
            cifA.f632a.a("error", this.f979a);
            cifA.f632a.a("reason", this.b);
            y.a(this.f978a, cifA);
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            this.f978a.a(10, e);
        }
    }
}
