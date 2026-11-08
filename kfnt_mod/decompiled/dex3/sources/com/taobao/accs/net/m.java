package com.taobao.accs.net;

import com.taobao.accs.data.Message;
import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class m implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ boolean b;
    final /* synthetic */ j c;

    m(j jVar, String str, boolean z) {
        this.c = jVar;
        this.a = str;
        this.b = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        Message messageA = this.c.e.a(this.a);
        if (messageA != null) {
            this.c.e.a(messageA, -9);
            this.c.a(this.a, this.b, "receive data time out");
            ALog.e(this.c.d(), this.a + "-> receive data time out!", new Object[0]);
        }
    }
}
