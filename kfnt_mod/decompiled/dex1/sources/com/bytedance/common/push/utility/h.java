package com.bytedance.common.push.utility;

import com.bytedance.common.utility.StringEncryptUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {
    public static String a(String str, String str2) throws NoSuchAlgorithmException {
        byte[] bytes = str.getBytes();
        try {
            if (i.a(str2)) {
                str2 = StringEncryptUtils.SHA_256;
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

    public static String a(byte[] bArr) {
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
