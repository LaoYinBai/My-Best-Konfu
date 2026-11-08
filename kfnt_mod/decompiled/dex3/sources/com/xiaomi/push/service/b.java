package com.xiaomi.push.service;

import com.xiaomi.push.ai;
import com.xiaomi.push.hj;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import java.lang.ref.WeakReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b extends ai.a {
    private ii a;

    /* renamed from: a, reason: collision with other field name */
    private WeakReference<XMPushService> f912a;

    /* renamed from: a, reason: collision with other field name */
    private boolean f913a;

    public b(ii iiVar, WeakReference<XMPushService> weakReference, boolean z) {
        this.f913a = false;
        this.a = iiVar;
        this.f912a = weakReference;
        this.f913a = z;
    }

    @Override // com.xiaomi.push.ai.a
    /* renamed from: a */
    public String mo136a() {
        return "22";
    }

    @Override // java.lang.Runnable
    public void run() {
        XMPushService xMPushService;
        if (this.f912a == null || this.a == null || (xMPushService = this.f912a.get()) == null) {
            return;
        }
        this.a.a(at.a());
        this.a.a(false);
        com.xiaomi.channel.commonutils.logger.b.c("MoleInfo aw_ping : send aw_Ping msg " + this.a.m476a());
        try {
            String strC = this.a.c();
            xMPushService.a(strC, it.a(y.a(strC, this.a.b(), this.a, hj.Notification)), this.f913a);
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.d("MoleInfo aw_ping : send help app ping error" + e.toString());
        }
    }
}
