package com.sigmob.sdk.base.services;

import android.content.Context;
import android.content.IntentFilter;
import com.sigmob.sdk.base.services.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d implements j.a {
    c a;
    k b = k.STOP;

    public d() {
        if (this.a == null) {
            this.a = new c();
        }
    }

    public void a(Context context) {
        context.registerReceiver(this.a, new IntentFilter("android.intent.action.DOWNLOAD_COMPLETE"));
    }

    public void b(Context context) {
        context.unregisterReceiver(this.a);
    }

    @Override // com.sigmob.sdk.base.services.j.a
    public boolean b() {
        a(com.sigmob.sdk.common.a.n().af());
        this.b = k.RUNNING;
        return false;
    }

    @Override // com.sigmob.sdk.base.services.j.a
    public void c() {
        b(com.sigmob.sdk.common.a.n().af());
        this.b = k.STOP;
    }
}
