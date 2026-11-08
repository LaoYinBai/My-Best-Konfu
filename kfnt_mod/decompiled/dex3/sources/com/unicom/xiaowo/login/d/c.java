package com.unicom.xiaowo.login.d;

import android.net.ConnectivityManager;
import android.net.Network;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class c extends ConnectivityManager.NetworkCallback {
    private /* synthetic */ e a;
    private /* synthetic */ a b;

    c(a aVar, e eVar) {
        this.b = aVar;
        this.a = eVar;
    }

    @Override // android.net.ConnectivityManager.NetworkCallback
    public final void onAvailable(Network network) {
        super.onAvailable(network);
        try {
            if (this.b.d != null) {
                this.b.d.cancel();
                a.a(this.b, (d) null);
                if (this.a != null) {
                    this.a.a(true, network);
                }
            }
        } catch (Exception unused) {
            if (this.a != null) {
                this.a.a(false, null);
            }
        }
    }
}
