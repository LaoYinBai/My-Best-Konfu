package com.xiaomi.push.service;

import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.push.al;
import com.xiaomi.push.cy;
import com.xiaomi.push.ea;
import com.xiaomi.push.service.bk;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bl extends al.b {
    final /* synthetic */ bk a;

    /* renamed from: a, reason: collision with other field name */
    boolean f931a = false;

    bl(bk bkVar) {
        this.a = bkVar;
    }

    @Override // com.xiaomi.push.al.b
    public void b() {
        try {
            String strA = a.a(com.xiaomi.push.t.m643a()).a();
            ea.a aVarA = ea.a.a(Base64.decode(cy.a(com.xiaomi.push.t.m643a(), (TextUtils.isEmpty(strA) || com.xiaomi.push.o.China.name().equals(strA)) ? "https://resolver.msg.xiaomi.net/psc/?t=a" : "https://resolver.msg.global.xiaomi.net/psc/?t=a", (List<com.xiaomi.push.bf>) null), 10));
            if (aVarA != null) {
                this.a.f929a = aVarA;
                this.f931a = true;
                this.a.e();
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("fetch config failure: " + e.getMessage());
        }
    }

    @Override // com.xiaomi.push.al.b
    /* renamed from: c */
    public void mo197c() {
        bk.a[] aVarArr;
        this.a.f928a = null;
        if (this.f931a) {
            synchronized (this.a) {
                aVarArr = (bk.a[]) this.a.f930a.toArray(new bk.a[this.a.f930a.size()]);
            }
            for (bk.a aVar : aVarArr) {
                aVar.a(this.a.f929a);
            }
        }
    }
}
