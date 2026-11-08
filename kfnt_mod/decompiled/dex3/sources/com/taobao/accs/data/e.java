package com.taobao.accs.data;

import com.taobao.accs.ut.monitor.TrafficsMonitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class e implements Runnable {
    final /* synthetic */ TrafficsMonitor.a a;
    final /* synthetic */ d b;

    e(d dVar, TrafficsMonitor.a aVar) {
        this.b = dVar;
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.b.c != null) {
            this.b.c.a(this.a);
        }
    }
}
