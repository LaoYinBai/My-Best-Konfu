package org.android.agoo.control;

import android.os.RemoteException;
import com.taobao.accs.utl.ALog;
import org.android.agoo.control.AgooFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes4.dex */
class f implements Runnable {
    final /* synthetic */ AgooFactory.a a;

    f(AgooFactory.a aVar) {
        this.a = aVar;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v0 */
    /* JADX WARN: Type inference failed for: r0v1, types: [org.android.agoo.service.SendMessage] */
    /* JADX WARN: Type inference failed for: r0v3, types: [android.content.Context] */
    /* JADX WARN: Type inference failed for: r0v4 */
    /* JADX WARN: Type inference failed for: r1v0 */
    /* JADX WARN: Type inference failed for: r1v1, types: [int] */
    /* JADX WARN: Type inference failed for: r1v10 */
    /* JADX WARN: Type inference failed for: r1v9, types: [android.content.ServiceConnection] */
    @Override // java.lang.Runnable
    public void run() {
        ?? r0 = 0;
        r0 = 0;
        int iC = 0;
        iC = 0;
        try {
            try {
                ALog.d("AgooFactory", "onConnected running tid:" + Thread.currentThread().getId(), new Object[0]);
                AgooFactory.a.b(this.a).doSend(AgooFactory.a.a(this.a));
            } catch (RemoteException e) {
                ALog.e("AgooFactory", "send error", e, new Object[0]);
            }
            ALog.d("AgooFactory", "send finish. close this connection", new Object[0]);
            AgooFactory.a.a(this.a, null);
            r0 = AgooFactory.mContext;
            iC = AgooFactory.a.c(this.a);
            r0.unbindService(iC);
        } catch (Throwable th) {
            ALog.d("AgooFactory", "send finish. close this connection", new Object[iC]);
            AgooFactory.a.a(this.a, r0);
            AgooFactory.mContext.unbindService(AgooFactory.a.c(this.a));
            throw th;
        }
    }
}
