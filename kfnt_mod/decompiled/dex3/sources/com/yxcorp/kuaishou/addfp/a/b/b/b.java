package com.yxcorp.kuaishou.addfp.a.b.b;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class b implements ServiceConnection {
    private /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    @Override // android.content.ServiceConnection
    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        try {
            try {
                this.a.c = e.a(iBinder);
                if (this.a.d == null) {
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                if (this.a.d == null) {
                    return;
                }
            }
            this.a.d.countDown();
        } catch (Throwable th2) {
            if (this.a.d != null) {
                this.a.d.countDown();
            }
            throw th2;
        }
    }

    @Override // android.content.ServiceConnection
    public final void onServiceDisconnected(ComponentName componentName) {
        this.a.c = null;
    }
}
