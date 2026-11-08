package com.bytedance.pangle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c {

    /* renamed from: a, reason: collision with root package name */
    static final char[] f4708a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String a(byte[] bArr, int i) {
        if (bArr == null) {
            throw new NullPointerException("bytes is null");
        }
        if (i + 0 > bArr.length) {
            throw new IndexOutOfBoundsException();
        }
        int i2 = i * 2;
        char[] cArr = new char[i2];
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            int i5 = bArr[i4 + 0] & 255;
            int i6 = i3 + 1;
            cArr[i3] = f4708a[i5 >> 4];
            i3 = i6 + 1;
            cArr[i6] = f4708a[i5 & 15];
        }
        return new String(cArr, 0, i2);
    }

    public static String a(File file) throws NoSuchAlgorithmException, IOException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (messageDigest == null) {
                return null;
            }
            FileInputStream fileInputStream = new FileInputStream(file);
            byte[] bArr = new byte[8192];
            while (true) {
                int i = fileInputStream.read(bArr, 0, 8192);
                if (i <= 0) {
                    break;
                }
                messageDigest.update(bArr, 0, i);
            }
            fileInputStream.close();
            byte[] bArrDigest = messageDigest.digest();
            if (bArrDigest == null) {
                throw new NullPointerException("bytes is null");
            }
            return a(bArrDigest, bArrDigest.length);
        } catch (Exception unused) {
            return null;
        }
    }
}
