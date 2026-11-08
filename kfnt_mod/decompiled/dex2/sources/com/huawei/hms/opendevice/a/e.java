package com.huawei.hms.opendevice.a;

import android.os.Build;
import java.io.UnsupportedEncodingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    private byte[] a = null;

    private int a(int i, int i2, int i3) {
        if (i2 < i) {
            i = i2;
        }
        return i3 < i ? i3 : i;
    }

    private static boolean a(int i) {
        return i >= 16;
    }

    private e() {
    }

    public static e a(String str, String str2, String str3, String str4) throws UnsupportedEncodingException {
        e eVar = new e();
        eVar.b(str, str2, str3, str4);
        return eVar;
    }

    public static e a(String str, String str2, String str3, byte[] bArr) throws UnsupportedEncodingException {
        e eVar = new e();
        eVar.b(str, str2, str3, bArr);
        return eVar;
    }

    private void b(String str, String str2, String str3, String str4) throws UnsupportedEncodingException {
        b(str, str2, str3, c.a(str4));
    }

    public byte[] a() {
        return (byte[]) this.a.clone();
    }

    private static boolean a(byte[] bArr) {
        return bArr.length >= 16;
    }

    private void b(String str, String str2, String str3, byte[] bArr) throws UnsupportedEncodingException {
        byte[] bArrA = c.a(str);
        byte[] bArrA2 = c.a(str2);
        byte[] bArrA3 = c.a(str3);
        int iA = a(bArrA.length, bArrA2.length, bArrA3.length);
        if (!a(iA, bArr)) {
            throw new IllegalArgumentException("key length must be more than 128bit.");
        }
        char[] cArr = new char[iA];
        for (int i = 0; i < iA; i++) {
            cArr[i] = (char) ((bArrA[i] ^ bArrA2[i]) ^ bArrA3[i]);
        }
        if (Build.VERSION.SDK_INT < 26) {
            this.a = d.a(cArr, bArr, 10000, 128);
        } else {
            this.a = d.b(cArr, bArr, 10000, 128);
        }
    }

    private boolean a(int i, byte[] bArr) {
        return a(i) & a(bArr);
    }
}
