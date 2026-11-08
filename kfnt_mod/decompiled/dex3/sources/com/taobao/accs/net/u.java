package com.taobao.accs.net;

import com.taobao.accs.data.Message;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class u implements Runnable {
    final /* synthetic */ Message a;
    final /* synthetic */ boolean b;
    final /* synthetic */ t c;

    u(t tVar, Message message, boolean z) {
        this.c = tVar;
        this.a = message;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.c.u) {
            this.c.a(this.a);
            if (this.c.u.size() == 0) {
                this.c.u.add(this.a);
            } else {
                Message message = (Message) this.c.u.getFirst();
                if (this.a.getType() == 1 || this.a.getType() == 0) {
                    this.c.u.addLast(this.a);
                    if (message.getType() == 2) {
                        this.c.u.removeFirst();
                    }
                } else if (this.a.getType() != 2 || message.getType() != 2) {
                    this.c.u.addLast(this.a);
                } else if (!message.force && this.a.force) {
                    this.c.u.removeFirst();
                    this.c.u.addFirst(this.a);
                }
            }
            if (this.b || this.c.t == 3) {
                try {
                    this.c.u.notifyAll();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
