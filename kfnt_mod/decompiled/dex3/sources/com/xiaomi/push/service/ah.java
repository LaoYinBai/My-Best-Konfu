package com.xiaomi.push.service;

import com.xiaomi.push.hd;
import java.util.Iterator;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class ah implements Runnable {
    final /* synthetic */ List a;

    /* renamed from: a, reason: collision with other field name */
    final /* synthetic */ boolean f879a;

    ah(List list, boolean z) {
        this.a = list;
        this.f879a = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        boolean zB = ag.b("www.baidu.com:80");
        Iterator it = this.a.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            zB = zB || ag.b((String) it.next());
            if (zB && !this.f879a) {
                break;
            }
        }
        hd.a(zB ? 1 : 2);
    }
}
