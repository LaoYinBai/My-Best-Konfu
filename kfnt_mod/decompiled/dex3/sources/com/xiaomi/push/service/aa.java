package com.xiaomi.push.service;

import android.text.TextUtils;
import com.xiaomi.push.hj;
import com.xiaomi.push.ht;
import com.xiaomi.push.ii;
import com.xiaomi.push.it;
import com.xiaomi.push.service.bm;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class aa extends bm.a {
    final /* synthetic */ XMPushService a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ l f863a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    aa(String str, long j, XMPushService xMPushService, l lVar) {
        super(str, j);
        this.a = xMPushService;
        this.f863a = lVar;
    }

    @Override // com.xiaomi.push.service.bm.a
    void a(bm bmVar) {
        com.xiaomi.push.ax axVarA = com.xiaomi.push.ax.a(this.a);
        String strA = bmVar.a("MSAID", "msaid");
        String str = axVarA.mo100a() + axVarA.mo102b() + axVarA.c() + axVarA.d();
        if (TextUtils.isEmpty(str) || TextUtils.equals(strA, str)) {
            return;
        }
        bmVar.a("MSAID", "msaid", str);
        ii iiVar = new ii();
        iiVar.b(this.f863a.d);
        iiVar.c(ht.ClientInfoUpdate.f509a);
        iiVar.a(at.a());
        iiVar.a(new HashMap());
        axVarA.a(iiVar.m477a());
        this.a.a(this.a.getPackageName(), it.a(y.a(this.a.getPackageName(), this.f863a.d, iiVar, hj.Notification)), true);
    }
}
