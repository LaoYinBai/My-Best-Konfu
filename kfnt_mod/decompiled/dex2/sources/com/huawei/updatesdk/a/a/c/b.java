package com.huawei.updatesdk.a.a.c;

import com.mbridge.msdk.playercommon.exoplayer2.C;
import java.io.UnsupportedEncodingException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private byte[] a;
    private int b = 1024;
    private int c = 0;

    public b() {
        this.a = null;
        this.a = new byte[this.b];
    }

    public static String a(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] cArr2 = new char[bArr.length * 2];
        int i = 0;
        for (byte b : bArr) {
            int i2 = i + 1;
            cArr2[i] = cArr[(b >>> 4) & 15];
            i = i2 + 1;
            cArr2[i2] = cArr[b & 15];
        }
        return String.valueOf(cArr2);
    }

    public String a() {
        if (this.c <= 0) {
            return null;
        }
        try {
            return new String(this.a, 0, this.c, C.UTF8_NAME);
        } catch (UnsupportedEncodingException unused) {
            return null;
        }
    }

    public void a(byte[] bArr, int i) {
        if (i <= 0) {
            return;
        }
        if (this.a.length - this.c >= i) {
            System.arraycopy(bArr, 0, this.a, this.c, i);
        } else {
            byte[] bArr2 = new byte[(this.a.length + i) << 1];
            System.arraycopy(this.a, 0, bArr2, 0, this.c);
            System.arraycopy(bArr, 0, bArr2, this.c, i);
            this.a = bArr2;
        }
        this.c += i;
    }
}
