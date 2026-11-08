package com.huawei.a.a.b.a;

import com.huawei.hms.aaid.constant.AaidIdConstant;
import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c {
    static String a(String str) {
        String str2;
        String str3;
        try {
            return d.a(MessageDigest.getInstance(AaidIdConstant.SIGNATURE_SHA256).digest(str.getBytes(C.UTF8_NAME)));
        } catch (UnsupportedEncodingException unused) {
            str2 = "EncryptUtil";
            str3 = "sha256Digest(): UnsupportedEncodingException: Exception when writing the log file.";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return "";
        } catch (NoSuchAlgorithmException unused2) {
            str2 = "EncryptUtil";
            str3 = "sha256Digest(): NoSuch Algorithm Exception";
            com.huawei.a.a.b.e.a.c(str2, str3);
            return "";
        }
    }

    static String a(char[] cArr, byte[] bArr) {
        String str;
        String str2;
        try {
            return d.a(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1").generateSecret(new PBEKeySpec(cArr, bArr, 10000, 128)).getEncoded());
        } catch (NoSuchAlgorithmException unused) {
            str = "EncryptUtil";
            str2 = "getAuthToken() encryptPBKDF2 No such algorithm!";
            com.huawei.a.a.b.e.a.d(str, str2);
            return null;
        } catch (InvalidKeySpecException unused2) {
            str = "EncryptUtil";
            str2 = "getAuthToken() encryptPBKDF2 Invalid key specification !";
            com.huawei.a.a.b.e.a.d(str, str2);
            return null;
        }
    }
}
