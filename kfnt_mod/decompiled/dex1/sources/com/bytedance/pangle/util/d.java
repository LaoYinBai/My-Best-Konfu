package com.bytedance.pangle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class d {

    /* renamed from: a, reason: collision with root package name */
    public static String f4709a = "DES/ECB/NoPadding";

    /* renamed from: b, reason: collision with root package name */
    public static String f4710b = "DESede/ECB/NoPadding";

    /* renamed from: c, reason: collision with root package name */
    public static String f4711c = "AES/ECB/NoPadding";
    private static final char[] d = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static String a(File file) {
        return a(b(file));
    }

    private static byte[] b(File file) throws Throwable {
        FileInputStream fileInputStream;
        if (file == null) {
            return null;
        }
        try {
            fileInputStream = new FileInputStream(file);
            try {
                try {
                    DigestInputStream digestInputStream = new DigestInputStream(fileInputStream, MessageDigest.getInstance("MD5"));
                    while (digestInputStream.read(new byte[262144]) > 0) {
                    }
                    byte[] bArrDigest = digestInputStream.getMessageDigest().digest();
                    f.a(fileInputStream);
                    return bArrDigest;
                } catch (IOException | NoSuchAlgorithmException e) {
                    e = e;
                    e.printStackTrace();
                    f.a(fileInputStream);
                    return null;
                }
            } catch (Throwable th) {
                th = th;
                f.a(fileInputStream);
                throw th;
            }
        } catch (IOException | NoSuchAlgorithmException e2) {
            e = e2;
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
            f.a(fileInputStream);
            throw th;
        }
    }

    private static String a(byte[] bArr) {
        int length;
        if (bArr == null || (length = bArr.length) <= 0) {
            return null;
        }
        char[] cArr = new char[length << 1];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = i + 1;
            cArr[i] = d[(bArr[i2] >>> 4) & 15];
            i = i3 + 1;
            cArr[i3] = d[bArr[i2] & 15];
        }
        return new String(cArr);
    }

    public static String a(String str) {
        boolean z = false;
        if (str != null) {
            int length = str.length();
            for (int i = 0; i < length; i++) {
                if (!Character.isWhitespace(str.charAt(i))) {
                    break;
                }
            }
            z = true;
        } else {
            z = true;
        }
        return a(z ? null : new File(str));
    }
}
