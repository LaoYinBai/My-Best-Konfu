package com.taobao.accs.net;

import com.taobao.accs.data.Message;
import com.taobao.accs.utl.BaseMonitor;
import java.util.Iterator;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class o implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ boolean b;
    final /* synthetic */ int c;
    final /* synthetic */ j d;

    o(j jVar, int i, boolean z, int i2) {
        this.d = jVar;
        this.a = i;
        this.b = z;
        this.c = i2;
    }

    @Override // java.lang.Runnable
    public void run() {
        Message messageB;
        if (this.a > 0) {
            Message.Id id = new Message.Id(this.a, "");
            Message.Id id2 = null;
            Iterator<Message.Id> it = this.d.e.f().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Message.Id next = it.next();
                if (next.equals(id)) {
                    id2 = next;
                    break;
                }
            }
            if (id2 != null && (messageB = this.d.e.b(id2.getDataId())) != null) {
                if (this.b) {
                    if (!this.d.a(messageB, 2000)) {
                        this.d.e.a(messageB, this.c);
                    }
                    if (messageB.getNetPermanceMonitor() != null) {
                        com.taobao.accs.utl.k.a("accs", BaseMonitor.COUNT_POINT_RESEND, "total_tnet", 0.0d);
                    }
                } else {
                    this.d.e.a(messageB, this.c);
                }
            }
        }
        if (this.a >= 0 || !this.b) {
            return;
        }
        this.d.b(this.a);
    }
}
