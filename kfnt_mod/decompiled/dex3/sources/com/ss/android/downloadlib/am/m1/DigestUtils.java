package com.ss.android.downloadlib.am.m1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DigestUtils {
    public static final String ALGORITHM_MD5 = "MD5";

    public static byte[] get(CharSequence charSequence, String str) throws Exception {
        return get(charSequence.toString().getBytes(), str);
    }

    public static byte[] get(byte[] bArr, String str) throws Exception {
        return get(new ByteArrayInputStream(bArr), str);
    }

    public static byte[] get(InputStream inputStream, String str) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        byte[] bArr = new byte[4096];
        while (true) {
            int i = inputStream.read(bArr);
            if (i <= 0) {
                return messageDigest.digest();
            }
            messageDigest.update(bArr, 0, i);
        }
    }
}
