package com.xiaomi.push.service;

import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bu extends XMPushService.i {
    final /* synthetic */ XMPushService a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f941a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ byte[] f942a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    bu(XMPushService xMPushService, int i, String str, byte[] bArr) {
        super(i);
        this.a = xMPushService;
        this.f941a = str;
        this.f942a = bArr;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "send mi push message";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            y.a(this.a, this.f941a, this.f942a);
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            this.a.a(10, e);
        }
    }
}
