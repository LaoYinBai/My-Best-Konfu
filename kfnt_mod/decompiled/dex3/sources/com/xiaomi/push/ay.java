package com.xiaomi.push;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Parcel;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class ay implements ar {
    private static boolean a;

    /* renamed from: a, reason: collision with other field name */
    private Context f140a;

    /* renamed from: a, reason: collision with other field name */
    private ServiceConnection f141a;

    /* renamed from: a, reason: collision with other field name */
    private volatile int f139a = 0;

    /* renamed from: a, reason: collision with other field name */
    private volatile a f142a = null;

    /* renamed from: a, reason: collision with other field name */
    private final Object f143a = new Object();

    /* JADX INFO: Access modifiers changed from: private */
    class a {

        /* renamed from: a, reason: collision with other field name */
        String f144a;
        String b;
        String c;
        String d;

        private a() {
            this.f144a = null;
            this.b = null;
            this.c = null;
            this.d = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    class b implements ServiceConnection {
        private b() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            if (ay.this.f142a != null) {
                return;
            }
            new Thread(new ba(this, iBinder)).start();
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    static class c {
        static String a(IBinder iBinder, String str, String str2, String str3) {
            Parcel parcelObtain = Parcel.obtain();
            Parcel parcelObtain2 = Parcel.obtain();
            try {
                parcelObtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                parcelObtain.writeString(str);
                parcelObtain.writeString(str2);
                parcelObtain.writeString(str3);
                iBinder.transact(1, parcelObtain, parcelObtain2, 0);
                parcelObtain2.readException();
                return parcelObtain2.readString();
            } finally {
                parcelObtain2.recycle();
                parcelObtain.recycle();
            }
        }
    }

    public ay(Context context) {
        this.f140a = context;
        a();
    }

    private void a() {
        boolean zBindService;
        this.f141a = new b();
        Intent intent = new Intent();
        intent.setClassName("com.heytap.openid", "com.heytap.openid.IdentifyService");
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        try {
            zBindService = this.f140a.bindService(intent, this.f141a, 1);
        } catch (Exception unused) {
            zBindService = false;
        }
        this.f139a = zBindService ? 1 : 2;
    }

    private void a(String str) {
        if (this.f139a != 1 || Looper.myLooper() == Looper.getMainLooper()) {
            return;
        }
        synchronized (this.f143a) {
            try {
                com.xiaomi.channel.commonutils.logger.b.m22a("oppo's " + str + " wait...");
                this.f143a.wait(3000L);
            } catch (Exception unused) {
            }
        }
    }

    public static boolean a(Context context) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo("com.heytap.openid", 128);
            if (packageInfo != null) {
                long longVersionCode = Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : packageInfo.versionCode;
                boolean z = (packageInfo.applicationInfo.flags & 1) != 0;
                a = longVersionCode >= 1;
                if (z) {
                    return true;
                }
            }
        } catch (Exception unused) {
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void b() {
        if (this.f141a != null) {
            try {
                this.f140a.unbindService(this.f141a);
            } catch (Exception unused) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String e() throws NoSuchAlgorithmException {
        try {
            Signature[] signatureArr = this.f140a.getPackageManager().getPackageInfo(this.f140a.getPackageName(), 64).signatures;
            MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
            StringBuilder sb = new StringBuilder();
            for (byte b2 : messageDigest.digest(signatureArr[0].toByteArray())) {
                sb.append(Integer.toHexString((b2 & UByte.MAX_VALUE) | ApkDownloadComplianceInterface.INSTALL_BITS).substring(1, 3));
            }
            return sb.toString();
        } catch (Exception unused) {
            return "";
        }
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public String mo100a() {
        a("getUDID");
        if (this.f142a == null) {
            return null;
        }
        return this.f142a.f144a;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: a */
    public boolean mo101a() {
        return a;
    }

    @Override // com.xiaomi.push.ar
    /* renamed from: b */
    public String mo102b() {
        a("getOAID");
        if (this.f142a == null) {
            return null;
        }
        return this.f142a.b;
    }

    @Override // com.xiaomi.push.ar
    public String c() {
        a("getVAID");
        if (this.f142a == null) {
            return null;
        }
        return this.f142a.c;
    }

    @Override // com.xiaomi.push.ar
    public String d() {
        a("getAAID");
        if (this.f142a == null) {
            return null;
        }
        return this.f142a.d;
    }
}
