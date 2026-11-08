package com.alibaba.sdk.android.utils;

import com.bytedance.common.utility.StringEncryptUtils;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static final char[] f2426a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /* renamed from: a, reason: collision with other method in class */
    public static boolean m36a(String str) {
        return str == null || str.length() == 0;
    }

    public static void a(double d) throws InterruptedException {
        try {
            Thread.sleep((long) (d * 1000.0d));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static String a(String str) throws NoSuchAlgorithmException {
        return a(MessageDigest.getInstance("MD5").digest(str.getBytes()));
    }

    public static String b(String str) throws NoSuchAlgorithmException {
        return a(MessageDigest.getInstance(StringEncryptUtils.SHA_1).digest(str.getBytes()));
    }

    public static String a(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (int i = 0; i < bArr.length; i++) {
            sb.append(f2426a[(bArr[i] & 240) >>> 4]);
            sb.append(f2426a[bArr[i] & 15]);
        }
        return sb.toString();
    }
}
