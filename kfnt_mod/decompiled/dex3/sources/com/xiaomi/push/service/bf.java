package com.xiaomi.push.service;

import com.sigmob.sdk.common.Constants;
import com.xiaomi.push.hb;
import com.xiaomi.push.service.XMPushService.d;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bf {
    private static int d = 300000;

    /* renamed from: a, reason: collision with other field name */
    private XMPushService f919a;
    private int b = 0;
    private int c = 0;
    private int a = 500;

    /* renamed from: a, reason: collision with other field name */
    private long f918a = 0;

    public bf(XMPushService xMPushService) {
        this.f919a = xMPushService;
    }

    private int a() {
        double d2;
        if (this.b > 8) {
            return Constants.PRECACHE_SIZE;
        }
        double dRandom = (Math.random() * 2.0d) + 1.0d;
        if (this.b > 4) {
            d2 = 60000.0d;
        } else {
            if (this.b <= 1) {
                if (this.f918a == 0) {
                    return 0;
                }
                if (System.currentTimeMillis() - this.f918a >= 310000) {
                    this.a = 1000;
                    this.c = 0;
                    return 0;
                }
                if (this.a >= d) {
                    return this.a;
                }
                int i = this.a;
                this.c++;
                if (this.c >= 4) {
                    return d;
                }
                this.a = (int) (this.a * 1.5d);
                return i;
            }
            d2 = 10000.0d;
        }
        return (int) (dRandom * d2);
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m615a() {
        this.f918a = System.currentTimeMillis();
        this.f919a.a(1);
        this.b = 0;
    }

    public void a(boolean z) {
        if (!this.f919a.m570a()) {
            com.xiaomi.channel.commonutils.logger.b.c("should not reconnect as no client or network.");
            return;
        }
        if (z) {
            if (!this.f919a.m571a(1)) {
                this.b++;
            }
            this.f919a.a(1);
            XMPushService xMPushService = this.f919a;
            XMPushService xMPushService2 = this.f919a;
            xMPushService2.getClass();
            xMPushService.a(xMPushService2.new d());
            return;
        }
        if (this.f919a.m571a(1)) {
            return;
        }
        int iA = a();
        this.b++;
        com.xiaomi.channel.commonutils.logger.b.m22a("schedule reconnect in " + iA + "ms");
        XMPushService xMPushService3 = this.f919a;
        XMPushService xMPushService4 = this.f919a;
        xMPushService4.getClass();
        xMPushService3.a(xMPushService4.new d(), (long) iA);
        if (this.b == 2 && hb.m374a().m379a()) {
            ag.b();
        }
        if (this.b == 3) {
            ag.a();
        }
    }
}
