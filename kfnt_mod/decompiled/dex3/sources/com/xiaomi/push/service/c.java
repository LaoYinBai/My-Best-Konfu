package com.xiaomi.push.service;

import com.xiaomi.push.fg;
import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c extends XMPushService.i {
    private XMPushService a;

    /* renamed from: a, reason: collision with other field name */
    private fg[] f943a;

    public c(XMPushService xMPushService, fg[] fgVarArr) {
        super(4);
        this.a = null;
        this.a = xMPushService;
        this.f943a = fgVarArr;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "batch send message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            if (this.f943a != null) {
                this.a.a(this.f943a);
            }
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            this.a.a(10, e);
        }
    }
}
