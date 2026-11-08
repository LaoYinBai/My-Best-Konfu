package com.bytedance.common.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class StringEncryptUtils {
    public static final String MD5 = "MD5";
    public static final String SHA_1 = "SHA-1";
    public static final String SHA_256 = "SHA-256";

    private StringEncryptUtils() {
    }

    public static String encryptBySHA256(String str) {
        return encrypt(str, SHA_256);
    }

    public static String encrypt(String str, String str2) throws NoSuchAlgorithmException {
        byte[] bytes = str.getBytes();
        try {
            if (StringUtils.isEmpty(str2)) {
                str2 = SHA_256;
            }
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            messageDigest.update(bytes);
            return bytes2Hex(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            return null;
        } catch (Exception unused2) {
            return null;
        }
    }

    public static String bytes2Hex(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            StringBuilder sb = new StringBuilder();
            for (byte b2 : bArr) {
                sb.append(String.format("%02x", Byte.valueOf(b2)));
            }
            return sb.toString();
        } catch (Throwable unused) {
            return null;
        }
    }
}
