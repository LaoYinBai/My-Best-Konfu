package com.taobao.accs.data;

import android.content.Intent;
import com.sigmob.sdk.common.mta.PointCategory;
import com.taobao.accs.common.Constants;
import com.taobao.accs.utl.ALog;
import com.taobao.accs.utl.BaseMonitor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class i implements Runnable {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ Intent c;
    final /* synthetic */ g d;

    i(g gVar, String str, String str2, Intent intent) {
        this.d = gVar;
        this.a = str;
        this.b = str2;
        this.c = intent;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (g.a == null || !g.a.contains(this.a)) {
            return;
        }
        ALog.e("MsgDistribute", "routing msg time out, try election", Constants.KEY_DATA_ID, this.a, Constants.KEY_SERVICE_ID, this.b);
        g.a.remove(this.a);
        com.taobao.accs.utl.k.a("accs", BaseMonitor.ALARM_MSG_ROUTING_RATE, "", PointCategory.TIMEOUT, "pkg:" + this.c.getPackage());
    }
}
