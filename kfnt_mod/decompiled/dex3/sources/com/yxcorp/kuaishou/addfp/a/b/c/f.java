package com.yxcorp.kuaishou.addfp.a.b.c;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class f implements ServiceConnection {
    private /* synthetic */ e a;

    f(e eVar) {
        this.a = eVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            a aVarA = b.a(iBinder);
            if (aVarA != null) {
                this.a.b = aVarA;
            }
            if (this.a.d == null) {
                return;
            }
        } catch (Throwable th) {
            if (this.a.d != null) {
                this.a.d.countDown();
            }
            throw th;
        }
        this.a.d.countDown();
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.b = null;
    }
}
