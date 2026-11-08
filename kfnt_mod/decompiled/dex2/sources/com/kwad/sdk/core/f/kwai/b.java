package com.kwad.sdk.core.f.kwai;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.kwad.sdk.core.f.a.b;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private Context a;
    private final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    private ServiceConnection c = new ServiceConnection() { // from class: com.kwad.sdk.core.f.kwai.b.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                com.kwad.sdk.core.d.a.c("HWDeviceIDHelper", "onServiceConnected");
                b.this.b.put(iBinder);
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public b(Context context) {
        this.a = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1 */
    /* JADX WARN: Type inference failed for: r1v4, types: [boolean] */
    public String a() throws Throwable {
        String strBindService;
        Exception e;
        String strA;
        Exception e2;
        Context context;
        ServiceConnection serviceConnection;
        try {
            Intent intent = new Intent("com.uodis.opendevice.OPENIDS_SERVICE");
            intent.setPackage("com.huawei.hwid");
            strBindService = this.a.bindService(intent, this.c, 1);
            try {
                try {
                    if (strBindService == 0) {
                        return "";
                    }
                    try {
                        b.a aVar = new b.a(this.b.take());
                        strA = aVar.a();
                        try {
                            com.kwad.sdk.core.d.a.c("HWDeviceIDHelper", "getOAID oaid:" + strA + "--boos:" + aVar.b());
                            context = this.a;
                            serviceConnection = this.c;
                        } catch (Exception e3) {
                            e2 = e3;
                            com.kwad.sdk.core.d.a.a(e2);
                            context = this.a;
                            serviceConnection = this.c;
                            context.unbindService(serviceConnection);
                            return strA;
                        }
                    } catch (Exception e4) {
                        strA = "";
                        e2 = e4;
                    } catch (Throwable th) {
                        th = th;
                        this.a.unbindService(this.c);
                        throw th;
                    }
                    context.unbindService(serviceConnection);
                    return strA;
                } catch (Exception e5) {
                    e = e5;
                    com.kwad.sdk.core.d.a.c("HWDeviceIDHelper", "getOAID hw service not found");
                    com.kwad.sdk.core.d.a.b(e);
                    return strBindService;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e6) {
            strBindService = "";
            e = e6;
        }
    }
}
