package com.taobao.accs.internal;

import android.content.Intent;
import android.os.Process;
import com.taobao.accs.common.Constants;
import com.taobao.accs.internal.ServiceImpl;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.UTMini;
import com.taobao.accs.utl.UtilityImpl;
import com.taobao.accs.utl.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c implements Runnable {
    final /* synthetic */ ServiceImpl.AnonymousClass1 a;

    c(ServiceImpl.AnonymousClass1 anonymousClass1) {
        this.a = anonymousClass1;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (ServiceImpl.this.c != null && UtilityImpl.e(ServiceImpl.this.c)) {
                Intent intent = new Intent();
                intent.setAction(Constants.ACTION_PING);
                intent.setClassName(ServiceImpl.this.c.getPackageName(), j.channelService);
                ServiceImpl.this.c.startService(intent);
                UTMini.getInstance().commitEvent(66001, "probeServiceEnabled", UtilityImpl.l(ServiceImpl.this.c));
                ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao........mContext.startService(intent) [probe][successfully]", new Object[0]);
            } else {
                Process.killProcess(Process.myPid());
            }
            ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao........messageServiceBinder [probe][end]", new Object[0]);
        } catch (Throwable th) {
            ALog.d("ServiceImpl", "ReceiverImpl probeTaoBao error........e=" + th, new Object[0]);
        }
    }
}
