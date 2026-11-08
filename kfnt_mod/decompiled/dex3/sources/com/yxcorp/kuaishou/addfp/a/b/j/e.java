package com.yxcorp.kuaishou.addfp.a.b.j;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class e implements ServiceConnection {
    private /* synthetic */ d a;

    e(d dVar) {
        this.a = dVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            this.a.a = b.a(iBinder);
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
        this.a.a = null;
    }
}
