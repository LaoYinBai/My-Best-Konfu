package bykvm_19do.bykvm_19do.bykvm_19do;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.Signature;
import android.os.Build;
import android.os.IBinder;
import android.text.TextUtils;
import bykvm_19do.bykvm_19do.bykvm_19do.a1;
import bykvm_19do.bykvm_19do.bykvm_19do.i1;
import bykvm_19do.bykvm_19do.bykvm_19do.p1;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class f1 implements a1 {

    /* renamed from: a, reason: collision with root package name */
    private final a1 f1332a;

    /* renamed from: b, reason: collision with root package name */
    private o0<Boolean> f1333b;

    class a extends o0<Boolean> {
        a(f1 f1Var) {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // bykvm_19do.bykvm_19do.bykvm_19do.o0
        /* renamed from: c, reason: merged with bridge method [inline-methods] */
        public Boolean a(Object... objArr) {
            try {
                PackageInfo packageInfo = ((Context) objArr[0]).getPackageManager().getPackageInfo("com.heytap.openid", 0);
                if (packageInfo == null) {
                    return false;
                }
                return Boolean.valueOf((Build.VERSION.SDK_INT >= 28 ? packageInfo.getLongVersionCode() : (long) packageInfo.versionCode) >= 1);
            } catch (Throwable th) {
                th.printStackTrace();
                return false;
            }
        }
    }

    f1() {
        this(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String c(Context context) throws NoSuchAlgorithmException {
        PackageInfo packageInfo;
        try {
            packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
        } catch (Throwable th) {
            th.printStackTrace();
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public a1.a b(Context context) {
        if (this.f1332a != null && !this.f1333b.b(new Object[0]).booleanValue()) {
            return this.f1332a.b(context);
        }
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.heytap.openid", "com.heytap.openid.IdentifyService"));
        intent.setAction("action.com.heytap.openid.OPEN_ID_SERVICE");
        String str = (String) new i1(context, intent, new b(context)).a();
        a1.a aVar = new a1.a();
        aVar.f1312a = str;
        return aVar;
    }

    class b implements i1.b<p1, String> {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ Context f1334a;

        b(Context context) {
            this.f1334a = context;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        /* renamed from: b, reason: merged with bridge method [inline-methods] */
        public p1 a(IBinder iBinder) {
            return p1.a.a(iBinder);
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_19do.i1.b
        public String a(p1 p1Var) throws NoSuchAlgorithmException {
            if (p1Var == null) {
                return null;
            }
            String strC = f1.this.c(this.f1334a);
            if (TextUtils.isEmpty(strC)) {
                return null;
            }
            return p1Var.a(this.f1334a.getPackageName(), strC, "OUID");
        }
    }

    f1(a1 a1Var) {
        this.f1333b = new a(this);
        this.f1332a = a1Var;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_19do.a1
    public boolean a(Context context) {
        if (context == null) {
            return false;
        }
        Boolean boolB = this.f1333b.b(context);
        return (this.f1332a == null || boolB.booleanValue()) ? boolB.booleanValue() : this.f1332a.a(context);
    }
}
