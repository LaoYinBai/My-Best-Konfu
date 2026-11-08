package com.xiaomi.push.service;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.push.service.XMPushService.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ca extends ContentObserver {
    final /* synthetic */ XMPushService a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ca(XMPushService xMPushService, Handler handler) {
        super(handler);
        this.a = xMPushService;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        boolean zM563f = this.a.m563f();
        com.xiaomi.channel.commonutils.logger.b.m22a("ExtremePowerMode:" + zM563f);
        if (zM563f) {
            this.a.a(this.a.new f(23, null));
        } else {
            this.a.a(true);
        }
    }
}
