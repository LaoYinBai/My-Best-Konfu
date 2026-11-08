package com.bytedance.gameprotect;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import androidx.annotation.RequiresApi;
import com.bytedance.common.utility.StringEncryptUtils;
import java.io.ByteArrayInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    private static Context f3863a;

    private static String a(byte[] bArr) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(StringEncryptUtils.SHA_1);
            messageDigest.update(bArr);
            return c.a.a.a.a.d.a(messageDigest.digest());
        } catch (Exception unused) {
            return "";
        }
    }

    public static HashMap<String, String> a(String str) {
        Signature[] signatureArrA;
        Context context = f3863a;
        return (context == null || (signatureArrA = a(context, str)) == null || signatureArrA.length <= 0) ? new HashMap<>() : b(signatureArrA[0].toByteArray());
    }

    public static void a(Context context) {
        f3863a = context.getApplicationContext();
    }

    private static Signature[] a(Context context, String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
        return Build.VERSION.SDK_INT >= 28 ? b(context, str) : c(context, str);
    }

    private static HashMap<String, String> b(byte[] bArr) {
        HashMap<String, String> map = new HashMap<>();
        try {
            X509Certificate x509Certificate = (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
            map.put("algorithm", x509Certificate.getSigAlgName());
            map.put("issuer", x509Certificate.getIssuerDN().toString());
            map.put("subject", x509Certificate.getSubjectDN().toString());
            map.put("not_before", String.valueOf(x509Certificate.getNotBefore().getTime()));
            map.put("not_after", String.valueOf(x509Certificate.getNotAfter().getTime()));
            map.put("version", String.valueOf(x509Certificate.getVersion()));
            map.put("serial", x509Certificate.getSerialNumber().toString(16));
            map.put("sha1_hash", a(bArr));
            map.put("xx_hash", p.a(bArr));
        } catch (CertificateException unused) {
        }
        return map;
    }

    @RequiresApi(api = 28)
    private static Signature[] b(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 134217728);
            if (packageInfo == null || packageInfo.signingInfo == null) {
                return null;
            }
            return packageInfo.signingInfo.getApkContentsSigners();
        } catch (Exception unused) {
            return null;
        }
    }

    private static Signature[] c(Context context, String str) throws PackageManager.NameNotFoundException {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(str, 64);
            if (packageInfo != null) {
                return packageInfo.signatures;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }
}
