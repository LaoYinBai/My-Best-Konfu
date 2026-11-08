package com.huawei.hms.opendevice.a;

import android.os.Build;
import com.huawei.hms.support.log.HMSLog;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class d {
    public static byte[] a(char[] cArr, byte[] bArr, int i, int i2) {
        return a(cArr, bArr, i, i2, false);
    }

    public static byte[] b(char[] cArr, byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[0];
        if (Build.VERSION.SDK_INT < 26) {
            HMSLog.e("PBKDF2", "system version not high than 26");
            return bArr2;
        }
        return a(cArr, bArr, i, i2, true);
    }

    private static byte[] a(char[] cArr, byte[] bArr, int i, int i2, boolean z) throws NoSuchAlgorithmException {
        SecretKeyFactory secretKeyFactory;
        try {
            PBEKeySpec pBEKeySpec = new PBEKeySpec(cArr, bArr, i, i2);
            if (z) {
                secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            } else {
                secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            }
            return secretKeyFactory.generateSecret(pBEKeySpec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            HMSLog.e("PBKDF2", "pbkdf exception : " + e.getMessage());
            return new byte[0];
        }
    }
}
