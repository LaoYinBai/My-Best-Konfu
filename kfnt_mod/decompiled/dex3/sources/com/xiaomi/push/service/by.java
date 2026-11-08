package com.xiaomi.push.service;

import com.xiaomi.push.eb;
import com.xiaomi.push.fq;
import com.xiaomi.push.ft;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class by extends fq {
    final /* synthetic */ XMPushService a;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    by(XMPushService xMPushService, Map map, int i, String str, ft ftVar) {
        super(map, i, str, ftVar);
        this.a = xMPushService;
    }

    @Override // com.xiaomi.push.fq
    /* renamed from: a */
    public byte[] mo338a() {
        try {
            eb.b bVar = new eb.b();
            bVar.a(bk.a().m622a());
            return bVar.a();
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("getOBBString err: " + e.toString());
            return null;
        }
    }
}
