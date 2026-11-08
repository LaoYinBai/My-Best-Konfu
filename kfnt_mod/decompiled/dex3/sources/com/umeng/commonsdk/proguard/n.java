package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.content.pm.Signature;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class n {
    n() {
    }

    static String a(Context context, String str, String str2) {
        Signature[] signatureArrA = a(context, str);
        if (signatureArrA == null) {
            return null;
        }
        for (Signature signature : signatureArrA) {
            if ("SHA1".equals(str2)) {
                return a(signature, "SHA1");
            }
        }
        return null;
    }

    private static Signature[] a(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 64).signatures;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String a(Signature signature, String str) throws NoSuchAlgorithmException {
        byte[] byteArray = signature.toByteArray();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (messageDigest == null) {
                return null;
            }
            byte[] bArrDigest = messageDigest.digest(byteArray);
            StringBuilder sb = new StringBuilder();
            for (byte b : bArrDigest) {
                sb.append(Integer.toHexString((b & UByte.MAX_VALUE) | ApkDownloadComplianceInterface.INSTALL_BITS).substring(1, 3));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
