package com.mbridge.msdk.optimize.a.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.Signature;
import android.os.IBinder;
import android.os.Looper;
import android.os.SystemClock;
import com.liulishuo.filedownloader.model.FileDownloadStatus;
import com.mbridge.msdk.optimize.a.b.d;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: OppoDeviceHelper.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class f {
    com.mbridge.msdk.optimize.a.b.d b;
    private Context d;
    private String e;
    public String a = "OUID";
    ServiceConnection c = new ServiceConnection() { // from class: com.mbridge.msdk.optimize.a.a.f.1
        @Override // android.content.ServiceConnection
        public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            f.this.b = d.a.a(iBinder);
        }

        @Override // android.content.ServiceConnection
        public final void onServiceDisconnected(ComponentName componentName) {
            f.this.b = null;
        }
    };

    public f(Context context) {
        this.d = context;
    }

    public final String a(com.mbridge.msdk.optimize.a.b bVar) throws NoSuchAlgorithmException {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot run on MainThread");
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        if (this.d.bindService(intent, this.c, 1)) {
            try {
                SystemClock.sleep(3000L);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (this.b != null) {
                String strA = a("OUID");
                a("DUID");
                a("AUID");
                if (bVar == null) {
                    return strA;
                }
                bVar.a(strA, false);
                return strA;
            }
        }
        return null;
    }

    private String a(String str) throws NoSuchAlgorithmException {
        Signature[] signatureArr;
        String packageName = this.d.getPackageName();
        if (this.e == null) {
            String string = null;
            try {
                signatureArr = this.d.getPackageManager().getPackageInfo(packageName, 64).signatures;
            } catch (Exception e) {
                e.printStackTrace();
                signatureArr = null;
            }
            if (signatureArr != null && signatureArr.length > 0) {
                byte[] byteArray = signatureArr[0].toByteArray();
                try {
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                    if (messageDigest != null) {
                        byte[] bArrDigest = messageDigest.digest(byteArray);
                        StringBuilder sb = new StringBuilder();
                        for (byte b : bArrDigest) {
                            sb.append(Integer.toHexString((b & FileDownloadStatus.error) | 256).substring(1, 3));
                        }
                        string = sb.toString();
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            this.e = string;
        }
        return ((d.a.C0130a) this.b).a(packageName, this.e, str);
    }
}
