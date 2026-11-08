package com.xiaomi.push.service;

import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class be {
    private static int a = 8;
    private int d = -666;

    /* renamed from: a, reason: collision with other field name */
    private byte[] f917a = new byte[ApkDownloadComplianceInterface.INSTALL_BITS];
    private int c = 0;
    private int b = 0;

    public static int a(byte b) {
        return b >= 0 ? b : b + 256;
    }

    private void a() {
        this.c = 0;
        this.b = 0;
    }

    private void a(int i, byte[] bArr, boolean z) {
        int length = bArr.length;
        for (int i2 = 0; i2 < 256; i2++) {
            this.f917a[i2] = (byte) i2;
        }
        this.c = 0;
        this.b = 0;
        while (this.b < i) {
            this.c = ((this.c + a(this.f917a[this.b])) + a(bArr[this.b % length])) % ApkDownloadComplianceInterface.INSTALL_BITS;
            a(this.f917a, this.b, this.c);
            this.b++;
        }
        if (i != 256) {
            this.d = ((this.c + a(this.f917a[i])) + a(bArr[i % length])) % ApkDownloadComplianceInterface.INSTALL_BITS;
        }
        if (z) {
            StringBuilder sb = new StringBuilder();
            sb.append("S_");
            int i3 = i - 1;
            sb.append(i3);
            sb.append(Constants.COLON_SEPARATOR);
            for (int i4 = 0; i4 <= i; i4++) {
                sb.append(" ");
                sb.append(a(this.f917a[i4]));
            }
            sb.append("   j_");
            sb.append(i3);
            sb.append("=");
            sb.append(this.c);
            sb.append("   j_");
            sb.append(i);
            sb.append("=");
            sb.append(this.d);
            sb.append("   S_");
            sb.append(i3);
            sb.append("[j_");
            sb.append(i3);
            sb.append("]=");
            sb.append(a(this.f917a[this.c]));
            sb.append("   S_");
            sb.append(i3);
            sb.append("[j_");
            sb.append(i);
            sb.append("]=");
            sb.append(a(this.f917a[this.d]));
            if (this.f917a[1] != 0) {
                sb.append("   S[1]!=0");
            }
            com.xiaomi.channel.commonutils.logger.b.m22a(sb.toString());
        }
    }

    private void a(byte[] bArr) {
        a(ApkDownloadComplianceInterface.INSTALL_BITS, bArr, false);
    }

    private static void a(byte[] bArr, int i, int i2) {
        byte b = bArr[i];
        bArr[i] = bArr[i2];
        bArr[i2] = b;
    }

    public static byte[] a(String str, String str2) {
        byte[] bArrM132a = com.xiaomi.push.bj.m132a(str);
        byte[] bytes = str2.getBytes();
        byte[] bArr = new byte[bArrM132a.length + 1 + bytes.length];
        for (int i = 0; i < bArrM132a.length; i++) {
            bArr[i] = bArrM132a[i];
        }
        bArr[bArrM132a.length] = 95;
        for (int i2 = 0; i2 < bytes.length; i2++) {
            bArr[bArrM132a.length + 1 + i2] = bytes[i2];
        }
        return bArr;
    }

    public static byte[] a(byte[] bArr, String str) {
        return a(bArr, com.xiaomi.push.bj.m132a(str));
    }

    public static byte[] a(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = new byte[bArr2.length];
        be beVar = new be();
        beVar.a(bArr);
        beVar.a();
        for (int i = 0; i < bArr2.length; i++) {
            bArr3[i] = (byte) (bArr2[i] ^ beVar.m614a());
        }
        return bArr3;
    }

    public static byte[] a(byte[] bArr, byte[] bArr2, boolean z, int i, int i2) {
        byte[] bArr3;
        int i3;
        if (i < 0 || i > bArr2.length || i + i2 > bArr2.length) {
            throw new IllegalArgumentException("start = " + i + " len = " + i2);
        }
        if (z) {
            bArr3 = bArr2;
            i3 = i;
        } else {
            bArr3 = new byte[i2];
            i3 = 0;
        }
        be beVar = new be();
        beVar.a(bArr);
        beVar.a();
        for (int i4 = 0; i4 < i2; i4++) {
            bArr3[i3 + i4] = (byte) (bArr2[i + i4] ^ beVar.m614a());
        }
        return bArr3;
    }

    /* renamed from: a, reason: collision with other method in class */
    byte m614a() {
        this.b = (this.b + 1) % ApkDownloadComplianceInterface.INSTALL_BITS;
        this.c = (this.c + a(this.f917a[this.b])) % ApkDownloadComplianceInterface.INSTALL_BITS;
        a(this.f917a, this.b, this.c);
        return this.f917a[(a(this.f917a[this.b]) + a(this.f917a[this.c])) % ApkDownloadComplianceInterface.INSTALL_BITS];
    }
}
