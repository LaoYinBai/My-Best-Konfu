package com.xiaomi.push.service;

import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.av;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ay extends XMPushService.i {
    final /* synthetic */ av.b.c a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ay(av.b.c cVar, int i) {
        super(i);
        this.a = cVar;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "check peer job";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        if (av.a().a(this.a.f910a.g, this.a.f910a.f906b).f897a == null) {
            av.b.this.f899a.a(this.a.f910a.g, this.a.f910a.f906b, 2, null, null);
        }
    }
}
