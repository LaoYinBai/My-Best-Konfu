package com.taobao.accs.net;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class w implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ t b;

    w(t tVar, String str) {
        this.b = tVar;
        this.a = str;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a != null && this.a.equals(this.b.O) && this.b.t == 2) {
            this.b.K = false;
            this.b.M = true;
            this.b.n();
            this.b.I.setCloseReason("conn timeout");
        }
    }
}
