package com.xiaomi.push.service;

import com.xiaomi.push.service.bm;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class bn implements Runnable {
    final /* synthetic */ bm a;

    bn(bm bmVar) {
        this.a = bmVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            Iterator it = this.a.f935a.values().iterator();
            while (it.hasNext()) {
                ((bm.a) it.next()).run();
            }
        } catch (Exception e) {
            com.xiaomi.channel.commonutils.logger.b.m22a("Sync job exception :" + e.getMessage());
        }
        this.a.f936a = false;
    }
}
