package com.huawei.hms.opendevice.a;

import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class a {
    public static String a(String str, String str2) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            HMSLog.e("AesCbc", "cbc encrypt param is not right");
            return "";
        }
        byte[] bArrA = c.a(str2);
        if (bArrA.length < 16) {
            HMSLog.e("AesCbc", "key length is not right");
            return "";
        }
        return a(str, bArrA);
    }

    public static String a(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length < 16) {
            HMSLog.e("AesCbc", "cbc encrypt param is not right");
            return "";
        }
        byte[] bArrA = b.a(16);
        byte[] bArrB = b(str, bArr, bArrA);
        return (bArrB == null || bArrB.length == 0) ? "" : c(c.a(bArrA), c.a(bArrB));
    }

    private static byte[] b(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str)) {
            HMSLog.e("AesCbc", "cbc encrypt param is not right");
            return new byte[0];
        }
        try {
            return a(str.getBytes(C.UTF8_NAME), bArr, bArr2);
        } catch (UnsupportedEncodingException e) {
            HMSLog.e("AesCbc", " cbc encrypt data error" + e.getMessage());
            return new byte[0];
        }
    }

    public static String b(String str, String str2) throws UnsupportedEncodingException {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            HMSLog.e("AesCbc", "content or key is null");
            return "";
        }
        byte[] bArrA = c.a(str2);
        if (bArrA.length < 16) {
            HMSLog.e("AesCbc", "key length is not right");
            return "";
        }
        return b(str, bArrA);
    }

    public static String b(String str, byte[] bArr) {
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length < 16) {
            return "";
        }
        String strA = a(str);
        String strB = b(str);
        if (TextUtils.isEmpty(strA) || TextUtils.isEmpty(strB)) {
            HMSLog.e("AesCbc", "ivParameter or encrypedWord is null");
            return "";
        }
        return a(strB, bArr, c.a(strA));
    }

    public static String a(String str, byte[] bArr, byte[] bArr2) {
        if (TextUtils.isEmpty(str) || bArr == null || bArr.length < 16 || bArr2 == null || bArr2.length < 16) {
            HMSLog.e("AesCbc", "cbc decrypt param is not right");
            return "";
        }
        try {
            return new String(b(c.a(str), bArr, bArr2), C.UTF8_NAME);
        } catch (UnsupportedEncodingException e) {
            HMSLog.e("AesCbc", " cbc decrypt data error" + e.getMessage());
            return "";
        }
    }

    private static String c(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return "";
        }
        try {
            return str2.substring(0, 6) + str.substring(0, 6) + str2.substring(6, 10) + str.substring(6, 16) + str2.substring(10, 16) + str.substring(16) + str2.substring(16);
        } catch (Exception e) {
            HMSLog.e("AesCbc", "mix exception: " + e.getMessage());
            return "";
        }
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length < 16 || bArr3 == null || bArr3.length < 16) {
            HMSLog.e("AesCbc", "cbc encrypt param is not right");
            return new byte[0];
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(1, secretKeySpec, new IvParameterSpec(bArr3));
            return cipher.doFinal(bArr);
        } catch (GeneralSecurityException e) {
            HMSLog.e("AesCbc", " cbc encrypt data error" + e.getMessage());
            return new byte[0];
        }
    }

    public static byte[] b(byte[] bArr, byte[] bArr2, byte[] bArr3) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length < 16 || bArr3 == null || bArr3.length < 16) {
            HMSLog.e("AesCbc", "cbc decrypt param is not right");
            return new byte[0];
        }
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(bArr2, "AES");
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(bArr3));
            return cipher.doFinal(bArr);
        } catch (GeneralSecurityException e) {
            HMSLog.e("AesCbc", " cbc decrypt data error" + e.getMessage());
            return new byte[0];
        }
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.substring(6, 12) + str.substring(16, 26) + str.substring(32, 48);
        } catch (Exception e) {
            HMSLog.e("AesCbc", "getIv exception : " + e.getMessage());
            return "";
        }
    }

    private static String b(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        try {
            return str.substring(0, 6) + str.substring(12, 16) + str.substring(26, 32) + str.substring(48);
        } catch (Exception e) {
            HMSLog.e("AesCbc", "get encryptword exception : " + e.getMessage());
            return "";
        }
    }
}
