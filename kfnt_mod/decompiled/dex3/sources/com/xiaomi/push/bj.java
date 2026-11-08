package com.xiaomi.push;

import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class bj {

    /* renamed from: a, reason: collision with other field name */
    private static byte[] f156a;
    private static final String a = System.getProperty("line.separator");

    /* renamed from: a, reason: collision with other field name */
    private static char[] f157a = new char[64];

    static {
        char c = 'A';
        int i = 0;
        while (c <= 'Z') {
            f157a[i] = c;
            c = (char) (c + 1);
            i++;
        }
        char c2 = 'a';
        while (c2 <= 'z') {
            f157a[i] = c2;
            c2 = (char) (c2 + 1);
            i++;
        }
        char c3 = '0';
        while (c3 <= '9') {
            f157a[i] = c3;
            c3 = (char) (c3 + 1);
            i++;
        }
        f157a[i] = '+';
        f157a[i + 1] = '/';
        f156a = new byte[128];
        for (int i2 = 0; i2 < f156a.length; i2++) {
            f156a[i2] = -1;
        }
        for (int i3 = 0; i3 < 64; i3++) {
            f156a[f157a[i3]] = (byte) i3;
        }
    }

    public static String a(String str) {
        return new String(a(str.getBytes()));
    }

    /* renamed from: a, reason: collision with other method in class */
    public static byte[] m132a(String str) {
        return a(str.toCharArray());
    }

    public static byte[] a(char[] cArr) {
        return a(cArr, 0, cArr.length);
    }

    public static byte[] a(char[] cArr, int i, int i2) {
        int i3;
        char c;
        int i4;
        if (i2 % 4 != 0) {
            throw new IllegalArgumentException("Length of Base64 encoded input string is not a multiple of 4.");
        }
        while (i2 > 0 && cArr[(i + i2) - 1] == '=') {
            i2--;
        }
        int i5 = (i2 * 3) / 4;
        byte[] bArr = new byte[i5];
        int i6 = i2 + i;
        int i7 = 0;
        while (i < i6) {
            int i8 = i + 1;
            char c2 = cArr[i];
            int i9 = i8 + 1;
            char c3 = cArr[i8];
            char c4 = 'A';
            if (i9 < i6) {
                i3 = i9 + 1;
                c = cArr[i9];
            } else {
                i3 = i9;
                c = 'A';
            }
            if (i3 < i6) {
                int i10 = i3 + 1;
                char c5 = cArr[i3];
                i3 = i10;
                c4 = c5;
            }
            if (c2 > 127 || c3 > 127 || c > 127 || c4 > 127) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            byte b = f156a[c2];
            byte b2 = f156a[c3];
            byte b3 = f156a[c];
            byte b4 = f156a[c4];
            if (b < 0 || b2 < 0 || b3 < 0 || b4 < 0) {
                throw new IllegalArgumentException("Illegal character in Base64 encoded data.");
            }
            int i11 = (b << 2) | (b2 >>> 4);
            int i12 = ((b2 & com.umeng.commonsdk.proguard.az.m) << 4) | (b3 >>> 2);
            int i13 = ((b3 & 3) << 6) | b4;
            int i14 = i7 + 1;
            bArr[i7] = (byte) i11;
            if (i14 < i5) {
                i4 = i14 + 1;
                bArr[i14] = (byte) i12;
            } else {
                i4 = i14;
            }
            if (i4 < i5) {
                i7 = i4 + 1;
                bArr[i4] = (byte) i13;
            } else {
                i7 = i4;
            }
            i = i3;
        }
        return bArr;
    }

    public static char[] a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    public static char[] a(byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        int i6;
        int i7 = ((i2 * 4) + 2) / 3;
        char[] cArr = new char[((i2 + 2) / 3) * 4];
        int i8 = i2 + i;
        int i9 = 0;
        while (i < i8) {
            int i10 = i + 1;
            int i11 = bArr[i] & UByte.MAX_VALUE;
            if (i10 < i8) {
                i3 = i10 + 1;
                i4 = bArr[i10] & UByte.MAX_VALUE;
            } else {
                i3 = i10;
                i4 = 0;
            }
            if (i3 < i8) {
                i5 = i3 + 1;
                i6 = bArr[i3] & UByte.MAX_VALUE;
            } else {
                i5 = i3;
                i6 = 0;
            }
            int i12 = i11 >>> 2;
            int i13 = ((i11 & 3) << 4) | (i4 >>> 4);
            int i14 = ((i4 & 15) << 2) | (i6 >>> 6);
            int i15 = i6 & 63;
            int i16 = i9 + 1;
            cArr[i9] = f157a[i12];
            int i17 = i16 + 1;
            cArr[i16] = f157a[i13];
            char c = '=';
            cArr[i17] = i17 < i7 ? f157a[i14] : '=';
            int i18 = i17 + 1;
            if (i18 < i7) {
                c = f157a[i15];
            }
            cArr[i18] = c;
            i9 = i18 + 1;
            i = i5;
        }
        return cArr;
    }

    public static String b(String str) {
        return new String(m132a(str));
    }
}
