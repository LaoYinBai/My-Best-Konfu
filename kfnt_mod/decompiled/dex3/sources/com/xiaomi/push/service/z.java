package com.xiaomi.push.service;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import com.xiaomi.push.service.bm;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class z extends bm.a {
    final /* synthetic */ XMPushService a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ l f982a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    z(String str, long j, XMPushService xMPushService, l lVar) {
        super(str, j);
        this.a = xMPushService;
        this.f982a = lVar;
    }

    @Override // com.xiaomi.push.service.bm.a
    void a(bm bmVar) {
        String strA = bmVar.a("GAID", "gaid");
        String strA2 = com.xiaomi.push.i.a((Context) this.a);
        com.xiaomi.channel.commonutils.logger.b.c("gaid :" + strA2);
        if (TextUtils.isEmpty(strA2) || TextUtils.equals(strA, strA2)) {
            return;
        }
        bmVar.a("GAID", "gaid", strA2);
        ii iiVar = new ii();
        iiVar.b(this.f982a.d);
        iiVar.c(ht.ClientInfoUpdate.f509a);
        iiVar.a(at.a());
        iiVar.a(new HashMap());
        iiVar.m477a().put("gaid", strA2);
        this.a.a(this.a.getPackageName(), it.a(y.a(this.a.getPackageName(), this.f982a.d, iiVar, hj.Notification)), true);
    }
}
