package com.kwad.sdk.core.f.kwai;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.Signature;
import android.os.IBinder;
import com.kwad.sdk.core.f.a.d;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import java.security.MessageDigest;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f {
    private Context a;
    private final LinkedBlockingQueue<IBinder> b = new LinkedBlockingQueue<>(1);
    private ServiceConnection c = new ServiceConnection() { // from class: com.kwad.sdk.core.f.kwai.f.1
        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) throws InterruptedException {
            com.kwad.sdk.core.d.a.c("OppoDeviceIDHelper", "onServiceConnected");
            try {
                f.this.b.put(iBinder);
            } catch (InterruptedException e) {
                com.kwad.sdk.core.d.a.a(e);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };

    public f(Context context) {
        this.a = context;
    }

    private String b() {
        try {
            Signature[] signatureArr = this.a.getPackageManager().getPackageInfo(this.a.getPackageName(), 64).signatures;
            if (signatureArr == null || signatureArr.length <= 0) {
                return null;
            }
            byte[] bArrDigest = MessageDigest.getInstance("SHA1").digest(signatureArr[0].toByteArray());
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toHexString((b & FileDownloadStatus.error) | 256).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception e) {
            com.kwad.sdk.core.d.a.a(e);
            return null;
        }
    }

    public String a() throws Throwable {
        String strA;
        String str = "";
        try {
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
            intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
            boolean zBindService = this.a.bindService(intent, this.c, 1);
            com.kwad.sdk.core.d.a.c("OppoDeviceIDHelper", "getOAID isBin=" + zBindService);
            try {
                if (zBindService) {
                    try {
                        strA = new d.a(this.b.take()).a(this.a.getPackageName(), b(), "OUID");
                    } catch (Exception e) {
                        e = e;
                    }
                    try {
                        com.kwad.sdk.core.d.a.c("OppoDeviceIDHelper", "getOAID oaid" + strA);
                        try {
                            this.a.unbindService(this.c);
                            str = strA;
                        } catch (Exception e2) {
                            e = e2;
                            str = strA;
                            com.kwad.sdk.core.d.a.c("OppoDeviceIDHelper", "getOAID service not found");
                            com.kwad.sdk.core.d.a.b(e);
                            return str;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        str = strA;
                        com.kwad.sdk.core.d.a.a(e);
                        this.a.unbindService(this.c);
                        return str;
                    } catch (Throwable th) {
                        th = th;
                        str = strA;
                        this.a.unbindService(this.c);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Exception e4) {
            e = e4;
        }
        return str;
    }
}
