package com.xiaomi.push.service;

import com.xiaomi.push.fg;
import com.xiaomi.push.fu;
import com.xiaomi.push.gg;
import com.xiaomi.push.service.XMPushService.c;
import com.xiaomi.push.service.XMPushService.k;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bt implements fu {
    final /* synthetic */ XMPushService a;

    bt(XMPushService xMPushService) {
        this.a = xMPushService;
    }

    @Override // com.xiaomi.push.fu
    public void a(fg fgVar) {
        this.a.a(this.a.new c(fgVar));
    }

    @Override // com.xiaomi.push.fu
    public void a(gg ggVar) {
        this.a.a(this.a.new k(ggVar));
    }
}
