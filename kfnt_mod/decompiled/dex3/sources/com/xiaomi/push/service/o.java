package com.xiaomi.push.service;

import android.content.Context;
import com.xiaomi.mipush.sdk.ErrorCode;
import com.xiaomi.push.ga;
import com.xiaomi.push.service.XMPushService;
import com.xiaomi.push.service.XMPushService.a;
import com.xiaomi.push.service.av;
import java.util.Collection;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class o extends XMPushService.i {
    private XMPushService a;

    /* renamed from: a, reason: collision with other field name */
    private String f964a;

    /* renamed from: a, reason: collision with other field name */
    private byte[] f965a;
    private String b;
    private String c;

    public o(XMPushService xMPushService, String str, String str2, String str3, byte[] bArr) {
        super(9);
        this.a = xMPushService;
        this.f964a = str;
        this.f965a = bArr;
        this.b = str2;
        this.c = str3;
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    public String a() {
        return "register app";
    }

    @Override // com.xiaomi.push.service.XMPushService.i
    /* renamed from: a */
    public void mo342a() {
        av.b next;
        l lVarA = m.a((Context) this.a);
        if (lVarA == null) {
            try {
                lVarA = m.a(this.a, this.f964a, this.b, this.c);
            } catch (Exception e) {
                com.xiaomi.channel.commonutils.logger.b.d("fail to register push account. " + e);
            }
        }
        if (lVarA == null) {
            com.xiaomi.channel.commonutils.logger.b.d("no account for mipush");
            p.a(this.a, ErrorCode.ERROR_AUTHERICATION_ERROR, "no account.");
            return;
        }
        Collection<av.b> collectionM605a = av.a().m605a("5");
        if (collectionM605a.isEmpty()) {
            next = lVarA.a(this.a);
            y.a(this.a, next);
            av.a().a(next);
        } else {
            next = collectionM605a.iterator().next();
        }
        if (!this.a.m574c()) {
            this.a.a(true);
            return;
        }
        try {
            if (next.f901a == av.c.binded) {
                y.a(this.a, this.f964a, this.f965a);
            } else if (next.f901a == av.c.unbind) {
                XMPushService xMPushService = this.a;
                XMPushService xMPushService2 = this.a;
                xMPushService2.getClass();
                xMPushService.a(xMPushService2.new a(next));
            }
        } catch (ga e2) {
            com.xiaomi.channel.commonutils.logger.b.d("meet error, disconnect connection. " + e2);
            this.a.a(10, e2);
        }
    }
}
