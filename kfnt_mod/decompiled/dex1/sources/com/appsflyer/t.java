package com.appsflyer;

import com.bytedance.common.utility.StringEncryptUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class t {
    t() {
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    public static String m242(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(StringEncryptUtils.SHA_1);
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            return m239(messageDigest.digest());
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to SHA1");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    public static String m241(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
            return m239(messageDigest.digest());
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to MD5");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    /* renamed from: ˋ, reason: contains not printable characters */
    public static String m240(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(StringEncryptUtils.SHA_256);
            messageDigest.update(str.getBytes());
            byte[] bArrDigest = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : bArrDigest) {
                stringBuffer.append(Integer.toString((b2 & 255) + 256, 16).substring(1));
            }
            return stringBuffer.toString();
        } catch (Exception e) {
            StringBuilder sb = new StringBuilder("Error turning ");
            sb.append(str.substring(0, 6));
            sb.append(".. to SHA-256");
            AFLogger.afErrorLog(sb.toString(), e);
            return null;
        }
    }

    /* renamed from: ˊ, reason: contains not printable characters */
    private static String m239(byte[] bArr) {
        Formatter formatter = new Formatter();
        for (byte b2 : bArr) {
            formatter.format("%02x", Byte.valueOf(b2));
        }
        String string = formatter.toString();
        formatter.close();
        return string;
    }
}
