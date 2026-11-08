package com.xiaomi.push;

import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class fy extends XMPushService.i {
    final /* synthetic */ fw a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ Exception f414a;
    final /* synthetic */ int b;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    fy(fw fwVar, int i, int i2, Exception exc) {
        super(i);
        this.a = fwVar;
        this.b = i2;
        this.f414a = exc;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "shutdown the connection. " + this.b + ", " + this.f414a;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        this.a.b.a(this.b, this.f414a);
    }
}
