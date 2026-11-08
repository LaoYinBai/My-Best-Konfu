package com.kwad.sdk.core.f.kwai;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.kwad.sdk.core.f.a.a;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    private Context a;
    private final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    private ServiceConnection c = new ServiceConnection() { // from class: com.kwad.sdk.core.f.kwai.a.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                a.this.b.put(iBinder);
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public a(Context context) {
        this.a = context;
    }

    /* JADX WARN: Type inference failed for: r1v3, types: [boolean] */
    public String a() throws Throwable {
        String strA;
        Exception e;
        Context context;
        ServiceConnection serviceConnection;
        String str = "";
        try {
            Intent intent = new Intent();
            intent.setAction("com.asus.msa.action.ACCESS_DID");
            intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
            ?? BindService = this.a.bindService(intent, this.c, 1);
            try {
                try {
                    if (BindService != 0) {
                        try {
                            strA = new a.C0038a(this.b.take()).a();
                            try {
                                com.kwad.sdk.core.d.a.c("ASUSDeviceIDHelper", "getOAID oaid:" + strA);
                                context = this.a;
                                serviceConnection = this.c;
                            } catch (Exception e2) {
                                e = e2;
                                com.kwad.sdk.core.d.a.a(e);
                                context = this.a;
                                serviceConnection = this.c;
                                context.unbindService(serviceConnection);
                                return strA;
                            }
                        } catch (Exception e3) {
                            strA = "";
                            e = e3;
                        } catch (Throwable th) {
                            th = th;
                            this.a.unbindService(this.c);
                            throw th;
                        }
                        context.unbindService(serviceConnection);
                    } else {
                        strA = "";
                    }
                    return strA;
                } catch (Exception e4) {
                    e = e4;
                    str = BindService;
                    com.kwad.sdk.core.d.a.c("ASUSDeviceIDHelper", "getOAID asus service not found;");
                    com.kwad.sdk.core.d.a.a(e);
                    return str;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }
}
