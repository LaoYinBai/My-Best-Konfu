package b.b.b;

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
import b.b.b.p;
import b.b.b.x;
import b.d.a.a;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class t implements p {

    /* renamed from: a, reason: collision with root package name */
    public final p f1185a;

    /* renamed from: b, reason: collision with root package name */
    public b.b.b.b<Boolean> f1186b = new a(this);

    public class a extends b.b.b.b<Boolean> {
        public a(t tVar) {
        }

        @Override // b.b.b.b
        public Boolean a(Object[] objArr) throws PackageManager.NameNotFoundException {
            try {
                PackageInfo packageInfo = ((Context) objArr[0]).getPackageManager().getPackageInfo("com.heytap.openid", 0);
                if (packageInfo == null) {
                    return false;
                }
                return Boolean.valueOf((Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : (long) packageInfo.versionCode) >= 1);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public t(p pVar) {
        this.f1185a = pVar;
    }

    public final String a(Context context) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        } catch (Exception e) {
            e.printStackTrace();
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
                e2.printStackTrace();
            }
        }
        return null;
    }

    @Override // b.b.b.p
    public boolean b(Context context) {
        if (context == null) {
            return false;
        }
        Boolean boolB = this.f1186b.b(context);
        return (this.f1185a == null || boolB.booleanValue()) ? boolB.booleanValue() : this.f1185a.b(context);
    }

    @Override // b.b.b.p
    public p.a c(Context context) {
        if (this.f1185a != null && !this.f1186b.b(new Object[0]).booleanValue()) {
            return this.f1185a.c(context);
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        String str = (String) new x(context, intent, new b(context)).a();
        p.a aVar = new p.a();
        aVar.f1176b = str;
        return aVar;
    }

    public class b implements x.b<b.d.a.a, String> {

        /* renamed from: a, reason: collision with root package name */
        public final /* synthetic */ Context f1187a;

        public b(Context context) {
            this.f1187a = context;
        }

        @Override // b.b.b.x.b
        public String a(b.d.a.a aVar) throws PackageManager.NameNotFoundException, NoSuchAlgorithmException {
            b.d.a.a aVar2 = aVar;
            if (aVar2 != null) {
                String strA = t.this.a(this.f1187a);
                if (!TextUtils.isEmpty(strA)) {
                    String packageName = this.f1187a.getPackageName();
                    a.AbstractBinderC0036a.C0037a c0037a = (a.AbstractBinderC0036a.C0037a) aVar2;
                    Parcel parcelObtain = Parcel.obtain();
                    Parcel parcelObtain2 = Parcel.obtain();
                    try {
                        parcelObtain.writeInterfaceToken("com.heytap.openid.IOpenID");
                        parcelObtain.writeString(packageName);
                        parcelObtain.writeString(strA);
                        parcelObtain.writeString("OUID");
                        c0037a.f1231a.transact(1, parcelObtain, parcelObtain2, 0);
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

        @Override // b.b.b.x.b
        public b.d.a.a a(IBinder iBinder) {
            return a.AbstractBinderC0036a.a(iBinder);
        }
    }
}
