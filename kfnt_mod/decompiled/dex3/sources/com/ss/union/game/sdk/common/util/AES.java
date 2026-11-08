package com.ss.union.game.sdk.common.util;

import android.text.TextUtils;
import android.util.Base64;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AES {
    private static final String a = "AES/CBC/PKCS5Padding";
    private static final String b = "AES";
    private static final String c = "utf-8";
    private static final String d = "1234567890abcdef";

    public static String encrypt(String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), b);
            Cipher cipher = Cipher.getInstance(a);
            cipher.init(1, secretKeySpec, new IvParameterSpec(d.getBytes()));
            return Base64.encodeToString(cipher.doFinal(str.getBytes(c)), 0);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String str, String str2) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, InvalidAlgorithmParameterException {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            byte[] bArrDecode = Base64.decode(str, 0);
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes(), b);
            Cipher cipher = Cipher.getInstance(a);
            cipher.init(2, secretKeySpec, new IvParameterSpec(d.getBytes()));
            return new String(cipher.doFinal(bArrDecode));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
