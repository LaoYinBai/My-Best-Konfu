package com.taobao.accs.net;

import anet.channel.session.TnetSpdySession;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class n implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ byte[] b;
    final /* synthetic */ TnetSpdySession c;
    final /* synthetic */ j d;

    n(j jVar, int i, byte[] bArr, TnetSpdySession tnetSpdySession) {
        this.d = jVar;
        this.a = i;
        this.b = bArr;
        this.c = tnetSpdySession;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (ALog.isPrintLog(ALog.Level.I)) {
            ALog.i(this.d.d(), "onDataReceive", "type", Integer.valueOf(this.a));
        }
        if (this.a == 200) {
            try {
                long jCurrentTimeMillis = System.currentTimeMillis();
                this.d.e.a(this.b, this.c.getHost());
                com.taobao.accs.ut.a.d dVarG = this.d.e.g();
                if (dVarG != null) {
                    dVarG.c = String.valueOf(jCurrentTimeMillis);
                    dVarG.g = this.d.c == 0 ? "service" : "inapp";
                    dVarG.a();
                    return;
                }
                return;
            } catch (Throwable th) {
                ALog.e(this.d.d(), "onDataReceive ", th, new Object[0]);
                UTMini.getInstance().commitEvent(66001, "DATA_RECEIVE", UtilityImpl.a(th));
                return;
            }
        }
        ALog.e(this.d.d(), "drop frame len:" + this.b.length + " frameType" + this.a, new Object[0]);
    }
}
