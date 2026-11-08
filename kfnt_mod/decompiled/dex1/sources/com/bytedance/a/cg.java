package com.bytedance.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcel;
import android.text.TextUtils;
import com.bytedance.a.bm;
import com.bytedance.a.da;
import com.bytedance.a.dz;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class cg implements bm {

    /* renamed from: a, reason: collision with root package name */
    public final bm f3464a;

    /* renamed from: b, reason: collision with root package name */
    public dt<Boolean> f3465b = new a(this);

    public class a extends dt<Boolean> {
        public a(cg cgVar) {
        }

        @Override // com.bytedance.a.dt
        public Boolean a(Object[] objArr) throws PackageManager.NameNotFoundException {
            try {
                PackageInfo packageInfo = ((Context) objArr[0]).getPackageManager().getPackageInfo("com.heytap.openid", 0);
                if (packageInfo == null) {
                    return false;
                }
                return Boolean.valueOf((Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : (long) packageInfo.versionCode) >= 1);
            } catch (Exception e) {
                de.a("", e);
                return false;
            }
        }
    }

    public class b implements da.b<dz, String> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f3466a;

        public b(Context context) {
            this.f3466a = context;
        }

        @Override // com.bytedance.a.da.b
        public dz a(IBinder iBinder) {
            return dz.a.a(iBinder);
        }

        @Override // com.bytedance.a.da.b
        public String a(dz dzVar) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
            dz dzVar2 = dzVar;
            if (dzVar2 != null) {
                String strB = cg.this.b(this.f3466a);
                if (!TextUtils.isEmpty(strB)) {
                    String packageName = this.f3466a.getPackageName();
                    dz.a.C0101a c0101a = (dz.a.C0101a) dzVar2;
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        parcelObtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                        parcelObtain.writeString(packageName);
                        parcelObtain.writeString(strB);
                        parcelObtain.writeString("OUID");
                        c0101a.f3521a.transact(1, parcelObtain, parcelObtain2, 0);
                        parcelObtain2.readException();
                        return parcelObtain2.readString();
                    } finally {
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                    }
                }
            }
            return null;
        }
    }

    public cg(bm bmVar) {
        this.f3464a = bmVar;
    }

    @Override // com.bytedance.a.bm
    public bm.a a(Context context) {
        if (this.f3464a != null && !this.f3465b.b(new Object[0]).booleanValue()) {
            return this.f3464a.a(context);
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        String str = (String) new da(context, intent, new b(context)).a();
        bm.a aVar = new bm.a();
        aVar.f3415b = str;
        return aVar;
    }

    public final String b(Context context) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        } catch (Exception e) {
            de.a("", e);
        }
        Signature[] signatureArr = packageInfo != null ? packageInfo.signatures : null;
        if (signatureArr != null && signatureArr.length > 0) {
            byte[] byteArray = signatureArr[0].toByteArray();
            try {
                MessageDigest messageDigest = MessageDigest.getInstance("SHA1");
                if (messageDigest != null) {
                    byte[] bArrDigest = messageDigest.digest(byteArray);
                    StringBuilder sb = new StringBuilder();
                    for (byte b2 : bArrDigest) {
                        sb.append(Integer.toHexString((b2 & 255) | 256).substring(1, 3));
                    }
                    return sb.toString();
                }
            } catch (Exception e2) {
                de.a("", e2);
            }
        }
        return null;
    }

    @Override // com.bytedance.a.bm
    public boolean c(Context context) {
        if (context == null) {
            return false;
        }
        Boolean boolB = this.f3465b.b(context);
        return (this.f3464a == null || boolB.booleanValue()) ? boolB.booleanValue() : this.f3464a.c(context);
    }
}
