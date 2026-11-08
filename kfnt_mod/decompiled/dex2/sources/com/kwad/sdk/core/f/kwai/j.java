package com.kwad.sdk.core.f.kwai;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.kwad.sdk.core.f.a.f;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class j {
    public Context a;
    private final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    private ServiceConnection c = new ServiceConnection() { // from class: com.kwad.sdk.core.f.kwai.j.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            try {
                com.kwad.sdk.core.d.a.c("ZTEDeviceIDHelper", "onServiceConnected");
                j.this.b.put(iBinder);
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public j(Context context) {
        this.a = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v13 */
    /* JADX WARN: Type inference failed for: r1v21 */
    /* JADX WARN: Type inference failed for: r1v22 */
    /* JADX WARN: Type inference failed for: r1v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r1v4 */
    /* JADX WARN: Type inference failed for: r1v6 */
    /* JADX WARN: Type inference failed for: r1v7 */
    /* JADX WARN: Type inference failed for: r1v9 */
    /* JADX WARN: Type inference failed for: r3v5, types: [java.lang.StringBuilder] */
    public String a() throws Throwable {
        String strA;
        Exception e;
        Context context;
        ServiceConnection serviceConnection;
        String str;
        String str2 = "";
        try {
            Intent intent = new Intent();
            intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
            intent.setAction("com.bun.msa.action.bindto.service");
            intent.putExtra("com.bun.msa.param.pkgname", this.a.getPackageName());
            ?? BindService = this.a.bindService(intent, this.c, 1);
            com.kwad.sdk.core.d.a.c("ZTEDeviceIDHelper", "getOAID isBind=" + BindService);
            try {
                try {
                    if (BindService != 0) {
                        try {
                            strA = new f.a(this.b.take()).a();
                            try {
                                com.kwad.sdk.core.d.a.c("ZTEDeviceIDHelper", "getOAID oaid:" + strA);
                                context = this.a;
                                serviceConnection = this.c;
                                BindService = strA;
                            } catch (Exception e2) {
                                e = e2;
                                com.kwad.sdk.core.d.a.a(e);
                                context = this.a;
                                serviceConnection = this.c;
                                BindService = strA;
                                context.unbindService(serviceConnection);
                                str = BindService;
                                return str;
                            }
                        } catch (Exception e3) {
                            strA = "";
                            e = e3;
                        } catch (Throwable th) {
                            BindService = "";
                            th = th;
                            this.a.unbindService(this.c);
                            throw th;
                        }
                        context.unbindService(serviceConnection);
                        str = BindService;
                    } else {
                        str = "";
                    }
                    return str;
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Exception e4) {
                String str3 = BindService;
                e = e4;
                str2 = str3;
                com.kwad.sdk.core.d.a.c("ZTEDeviceIDHelper", "getOAID hw service not found");
                com.kwad.sdk.core.d.a.a(e);
                return str2;
            }
        } catch (Exception e5) {
            e = e5;
        }
    }
}
