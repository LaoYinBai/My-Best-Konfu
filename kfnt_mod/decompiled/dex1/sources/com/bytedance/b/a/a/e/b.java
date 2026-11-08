package com.bytedance.b.a.a.e;

import android.text.TextUtils;
import android.util.Base64;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    public static String a(byte[] bArr, String str) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            byte[] bArrDecode = Base64.decode(bArr, 0);
            if (TextUtils.isEmpty(str)) {
                return "";
            }
            byte[] bArr2 = new byte[0];
            try {
                SecretKeySpec secretKeySpec = new SecretKeySpec(str.getBytes(), "AES");
                Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
                cipher.init(2, secretKeySpec);
                String str2 = new String(cipher.doFinal(bArrDecode));
                try {
                    int iIndexOf = str2.indexOf("$");
                    if (iIndexOf != -1) {
                        return str2.substring(0, iIndexOf);
                    }
                } catch (Exception unused) {
                }
                return str2;
            } catch (Exception unused2) {
                return "";
            }
        } catch (Exception unused3) {
            return "";
        }
    }
}
