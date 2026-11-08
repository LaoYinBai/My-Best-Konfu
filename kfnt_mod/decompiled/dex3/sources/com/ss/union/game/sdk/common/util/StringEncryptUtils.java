package com.ss.union.game.sdk.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class StringEncryptUtils {
    public static final String MD5 = "MD5";
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_256 = "SHA-256";

    public static String encryptByMD5(String str) {
        return a(str, "MD5");
    }

    public static String encryptBySHA256(String str) {
        return a(str, SHA_256);
    }

    private static String a(String str, String str2) throws NoSuchAlgorithmException {
        byte[] bytes = str.getBytes();
        try {
            if (StringUtils.isEmpty(str2)) {
                str2 = "MD5";
            }
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(bytes);
            return a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    private static String a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.append(String.format("%02x", Byte.valueOf(b)));
        }
        return sb.toString();
    }
}
