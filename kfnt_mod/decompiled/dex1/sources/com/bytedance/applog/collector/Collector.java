package com.bytedance.applog.collector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.a.aa;
import com.bytedance.a.de;
import com.bytedance.a.dg;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Collector extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public void onReceive(Context context, Intent intent) {
        String[] stringArrayExtra = intent.getStringArrayExtra("K_DATA");
        if (stringArrayExtra == null || stringArrayExtra.length <= 0) {
            de.a("U SHALL NOT PASS!", null);
            return;
        }
        dg dgVar = dg.f3498a;
        if (dgVar == null) {
            aa.a(stringArrayExtra);
        } else {
            dgVar.p.removeMessages(4);
            dgVar.p.obtainMessage(4, stringArrayExtra).sendToTarget();
        }
    }
}
