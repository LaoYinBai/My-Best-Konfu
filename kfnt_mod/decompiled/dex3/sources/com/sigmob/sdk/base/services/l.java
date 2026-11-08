package com.sigmob.sdk.base.services;

import android.content.Context;
import android.content.IntentFilter;
import com.sigmob.sdk.base.services.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class l implements j.a {
    k b = k.STOP;
    WifiScanReceiver a = new WifiScanReceiver();

    public void a(Context context) {
        IntentFilter intentFilter = new IntentFilter("android.net.wifi.SCAN_RESULTS");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        context.registerReceiver(this.a, intentFilter);
    }

    public void b(Context context) {
        context.unregisterReceiver(this.a);
    }

    @Override // com.sigmob.sdk.base.services.j.a
    public boolean b() {
        if (this.b == k.RUNNING) {
            return false;
        }
        a(com.sigmob.sdk.common.a.n().af());
        this.b = k.RUNNING;
        return true;
    }

    @Override // com.sigmob.sdk.base.services.j.a
    public void c() {
        if (this.b == k.RUNNING) {
            b(com.sigmob.sdk.common.a.n().af());
            this.b = k.STOP;
        }
    }
}
