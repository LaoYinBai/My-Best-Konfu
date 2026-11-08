package com.bytedance.pangle.f;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.text.TextUtils;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.log.ZeusLogger;
import java.lang.ref.WeakReference;
import java.security.cert.CertificateException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    private static WeakReference<Signature[]> f4609a;

    public static boolean a(String str) throws Throwable {
        o oVarA;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        try {
            if (com.bytedance.pangle.util.g.a()) {
                oVarA = d.a(str);
            } else {
                oVarA = a.a(str);
            }
            Signature[] signatureArr = oVarA != null ? oVarA.f4625b : null;
            Signature[] signatureArr2 = f4609a != null ? f4609a.get() : null;
            if (signatureArr2 == null || signatureArr2.length == 0) {
                try {
                    PackageInfo packageInfo = Zeus.getAppApplication().getPackageManager().getPackageInfo(Zeus.getAppApplication().getPackageName(), 64);
                    Signature[] signatureArr3 = packageInfo.signatures;
                    f4609a = new WeakReference<>(packageInfo.signatures);
                    signatureArr2 = signatureArr3;
                } catch (PackageManager.NameNotFoundException unused) {
                    ZeusLogger.w(ZeusLogger.TAG_INSTALL, "ApkSignatureVerify get host signature error : ".concat(String.valueOf(str)));
                    return false;
                }
            }
            try {
                return o.b(signatureArr2, signatureArr);
            } catch (CertificateException unused2) {
                ZeusLogger.w(ZeusLogger.TAG_INSTALL, "ApkSignatureVerify host-plugin signature are effective match : ".concat(String.valueOf(str)));
                return false;
            }
        } catch (q unused3) {
            ZeusLogger.w(ZeusLogger.TAG_INSTALL, "ApkSignatureVerify verify plugin signature error : ".concat(String.valueOf(str)));
            return false;
        }
    }
}
