package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.push.fq;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ce extends XMPushService.i {
    final /* synthetic */ XMPushService a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ String f945a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ byte[] f946a;
    final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ce(XMPushService xMPushService, int i, int i2, byte[] bArr, String str) {
        super(i);
        this.a = xMPushService;
        this.b = i2;
        this.f946a = bArr;
        this.f945a = str;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "clear account cache.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        m.m638a((Context) this.a);
        av.a().m608a("5");
        com.xiaomi.push.ab.a(this.b);
        this.a.f836a.c(fq.a());
        this.a.a(this.f946a, this.f945a);
    }
}
