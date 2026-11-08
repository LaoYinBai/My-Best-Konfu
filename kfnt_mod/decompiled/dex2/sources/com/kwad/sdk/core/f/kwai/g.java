package com.kwad.sdk.core.f.kwai;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.kwad.sdk.core.f.a.e;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    private Context a;
    private final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    private ServiceConnection c = new ServiceConnection() { // from class: com.kwad.sdk.core.f.kwai.g.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                g.this.b.put(iBinder);
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public g(Context context) {
        this.a = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean] */
    public String a() throws Throwable {
        String strBindService;
        Exception e;
        Throwable th;
        String strA;
        Exception e2;
        Context context;
        ServiceConnection serviceConnection;
        try {
            Intent intent = new Intent();
            intent.setClassName("com.samsung.android.deviceidservice", "com.samsung.android.deviceidservice.DeviceIdService");
            strBindService = this.a.bindService(intent, this.c, 1);
        } catch (Exception e3) {
            strBindService = "";
            e = e3;
        }
        try {
            if (strBindService == 0) {
                return "";
            }
            try {
                strA = new e.a(this.b.take()).a();
                try {
                    try {
                        com.kwad.sdk.core.d.a.c("SamsungDeviceIDHelper", "getOAID oaid:" + strA);
                        context = this.a;
                        serviceConnection = this.c;
                    } catch (Exception e4) {
                        e2 = e4;
                        com.kwad.sdk.core.d.a.a(e2);
                        context = this.a;
                        serviceConnection = this.c;
                        context.unbindService(serviceConnection);
                        return strA;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    this.a.unbindService(this.c);
                    throw th;
                }
            } catch (Exception e5) {
                strA = "";
                e2 = e5;
            } catch (Throwable th3) {
                th = th3;
                this.a.unbindService(this.c);
                throw th;
            }
            context.unbindService(serviceConnection);
            return strA;
        } catch (Exception e6) {
            e = e6;
            com.kwad.sdk.core.d.a.c("SamsungDeviceIDHelper", "getOAID service not found");
            com.kwad.sdk.core.d.a.a(e);
            return strBindService;
        }
    }
}
