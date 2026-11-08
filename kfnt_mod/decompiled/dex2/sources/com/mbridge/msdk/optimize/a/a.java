package com.mbridge.msdk.optimize.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import com.mbridge.msdk.optimize.a.c;

/* compiled from: OaidAidlUtil.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    private Context a;
    private ServiceConnection b;
    private c c;
    private b d;

    public a(Context context) {
        this.a = context;
    }

    public final void a(b bVar) {
        if (bVar == null) {
            return;
        }
        try {
            this.d = bVar;
            if (this.a == null) {
                return;
            }
            this.b = new ServiceConnectionC0126a();
            Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
            intent.setPackage("com.huawei.hwid");
            this.a.bindService(intent, this.b, 1);
        } catch (Throwable unused) {
        }
    }

    /* compiled from: OaidAidlUtil.java */
    /* renamed from: com.mbridge.msdk.optimize.a.a$a, reason: collision with other inner class name */
    private final class ServiceConnectionC0126a implements ServiceConnection {
        private ServiceConnectionC0126a() {
        }

        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            a.this.c = c.a.a(iBinder);
            try {
                if (a.this.c != null) {
                    try {
                        if (a.this.d != null) {
                            a.this.d.a(a.this.c.a(), a.this.c.b());
                        }
                    } catch (RemoteException e) {
                        if (a.this.d != null) {
                            a.this.d.a(e.getMessage());
                        }
                    } catch (Exception e2) {
                        if (a.this.d != null) {
                            a.this.d.a(e2.getMessage());
                        }
                    }
                }
            } finally {
                a.c(a.this);
            }
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            a.this.c = null;
        }
    }

    static /* synthetic */ void c(a aVar) {
        if (aVar.a == null || aVar.b == null) {
            return;
        }
        aVar.a.unbindService(aVar.b);
        aVar.c = null;
        aVar.a = null;
        aVar.d = null;
    }
}
