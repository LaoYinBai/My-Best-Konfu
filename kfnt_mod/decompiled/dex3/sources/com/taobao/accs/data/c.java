package com.taobao.accs.data;

import anet.channel.appmonitor.AppMonitor;
import com.sigmob.sdk.common.mta.PointCategory;
import com.taobao.accs.common.Constants;
import com.taobao.accs.ut.monitor.AssembleMonitor;
import com.taobao.accs.utl.ALog;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class c implements Runnable {
    final /* synthetic */ a a;

    c(a aVar) {
        this.a = aVar;
    }

    @Override // java.lang.Runnable
    public void run() {
        synchronized (this.a) {
            if (this.a.f == 0) {
                ALog.e("AssembleMessage", PointCategory.TIMEOUT, Constants.KEY_DATA_ID, this.a.b);
                this.a.f = 1;
                this.a.h.clear();
                AppMonitor.getInstance().commitStat(new AssembleMonitor(this.a.b, String.valueOf(this.a.f)));
            }
        }
    }
}
