package com.taobao.accs.net;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class v implements Runnable {
    final /* synthetic */ t a;

    v(t tVar) {
        this.a = tVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.a.n();
        if (this.a.I != null) {
            this.a.I.setCloseReason("shut down");
        }
        synchronized (this.a.u) {
            try {
                this.a.u.notifyAll();
            } catch (Exception unused) {
            }
        }
    }
}
