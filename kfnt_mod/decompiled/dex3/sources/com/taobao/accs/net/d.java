package com.taobao.accs.net;

import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class d implements Runnable {
    final /* synthetic */ b a;

    d(b bVar) {
        this.a = bVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.a.e.c()) {
            ALog.e(this.a.d(), "receive ping time out! ", new Object[0]);
            f.a(this.a.d).c();
            this.a.a("", false, "receive ping timeout");
            this.a.e.a(-12);
        }
    }
}
