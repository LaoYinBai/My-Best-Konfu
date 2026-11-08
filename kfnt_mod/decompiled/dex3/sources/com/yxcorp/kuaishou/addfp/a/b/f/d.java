package com.yxcorp.kuaishou.addfp.a.b.f;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import java.security.MessageDigest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class d {
    private com.yxcorp.kuaishou.addfp.a.b.b d;
    private Context e;
    private a a = null;
    private String b = null;
    private String c = null;
    private CountDownLatch f = new CountDownLatch(1);
    private ServiceConnection g = new e(this);
    private boolean h = false;

    private String a(String str) {
        try {
            if (!TextUtils.isEmpty(this.c) && !TextUtils.isEmpty(str) && !TextUtils.isEmpty(this.b)) {
                String strA = this.a.a(this.b, this.c, str);
                return TextUtils.isEmpty(strA) ? "" : strA;
            }
            return "";
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    private void a(boolean z) {
        if (z) {
            try {
                String strA = a("OUID");
                if (!TextUtils.isEmpty(strA)) {
                    this.d.a(strA);
                    return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        this.d.e();
    }

    private static boolean b(Context context) {
        try {
            return context.getPackageManager().getPackageInfo("com.heytap.openid", 0) != null;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    public final void a(Context context) {
        if (this.g == null || context == null) {
            return;
        }
        context.unbindService(this.g);
    }

    public final void a(Context context, com.yxcorp.kuaishou.addfp.a.b.b bVar) {
        Signature[] signatureArr;
        try {
            this.d = bVar;
            this.e = context;
            this.h = b(context);
            if (!this.h) {
                a(false);
                return;
            }
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
            intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
            if (!context.bindService(intent, this.g, 1)) {
                a(false);
                return;
            }
            if (TextUtils.isEmpty(this.b)) {
                this.b = context.getPackageName();
            }
            if (TextUtils.isEmpty(this.c)) {
                try {
                    signatureArr = context.getPackageManager().getPackageInfo(this.b, 64).signatures;
                } catch (Throwable th) {
                    th.printStackTrace();
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
                                sb.append(Integer.toHexString((b & UByte.MAX_VALUE) | ApkDownloadComplianceInterface.INSTALL_BITS).substring(1, 3));
                            }
                            this.c = sb.toString();
                        }
                    } catch (Throwable th2) {
                        th2.printStackTrace();
                    }
                }
            }
            this.f.await(10L, TimeUnit.SECONDS);
            if (this.a != null) {
                a(true);
            } else {
                a(false);
            }
        } catch (Throwable th3) {
            th3.printStackTrace();
            a(false);
        }
    }

    public final boolean a() {
        return this.h;
    }
}
