package com.xiaomi.push.service;

import android.database.ContentObserver;
import android.os.Handler;
import com.xiaomi.push.service.XMPushService.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class cb extends ContentObserver {
    final /* synthetic */ XMPushService a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    cb(XMPushService xMPushService, Handler handler) {
        super(handler);
        this.a = xMPushService;
    }

    @Override // android.database.ContentObserver
    public void onChange(boolean z) {
        super.onChange(z);
        boolean zM564g = this.a.m564g();
        com.xiaomi.channel.commonutils.logger.b.m22a("SuperPowerMode:" + zM564g);
        this.a.e();
        if (zM564g) {
            this.a.a(this.a.new f(24, null));
        } else {
            this.a.a(true);
        }
    }
}
