package com.xiaomi.push.service;

import com.xiaomi.push.fg;
import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bg extends XMPushService.i {
    private fg a;

    /* renamed from: a, reason: collision with other field name */
    private XMPushService f920a;

    public bg(XMPushService xMPushService, fg fgVar) {
        super(4);
        this.f920a = null;
        this.f920a = xMPushService;
        this.a = fgVar;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "send a message.";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        try {
            if (this.a != null) {
                this.f920a.a(this.a);
            }
        } catch (ga e) {
            com.xiaomi.channel.commonutils.logger.b.a(e);
            this.f920a.a(10, e);
        }
    }
}
