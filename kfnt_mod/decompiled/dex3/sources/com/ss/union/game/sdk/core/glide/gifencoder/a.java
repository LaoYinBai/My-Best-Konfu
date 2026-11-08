package com.ss.union.game.sdk.core.glide.gifencoder;

import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import java.io.IOException;
import java.io.OutputStream;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a {
    static final int a = 12;
    static final int b = 5003;
    private static final int t = -1;
    int c;
    int e;
    int l;
    int m;
    int n;
    int r;
    private int u;
    private int v;
    private byte[] w;
    private int x;
    private int y;
    private int z;
    int d = 12;
    int f = 4096;
    int[] g = new int[5003];
    int[] h = new int[5003];
    int i = 5003;
    int j = 0;
    boolean k = false;
    int o = 0;
    int p = 0;
    int[] q = {0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535};
    byte[] s = new byte[ApkDownloadComplianceInterface.INSTALL_BITS];

    final int b(int i) {
        return (1 << i) - 1;
    }

    a(int i, int i2, byte[] bArr, int i3) {
        this.u = i;
        this.v = i2;
        this.w = bArr;
        this.x = Math.max(2, i3);
    }

    void a(byte b2, OutputStream outputStream) throws IOException {
        byte[] bArr = this.s;
        int i = this.r;
        this.r = i + 1;
        bArr[i] = b2;
        if (this.r >= 254) {
            c(outputStream);
        }
    }

    void a(OutputStream outputStream) throws IOException {
        a(this.i);
        this.j = this.m + 2;
        this.k = true;
        b(this.m, outputStream);
    }

    void a(int i) {
        for (int i2 = 0; i2 < i; i2++) {
            this.g[i2] = -1;
        }
    }

    void a(int i, OutputStream outputStream) throws IOException {
        this.l = i;
        int i2 = 0;
        this.k = false;
        this.c = this.l;
        this.e = b(this.c);
        this.m = 1 << (i - 1);
        this.n = this.m + 1;
        this.j = this.m + 2;
        this.r = 0;
        int iA = a();
        for (int i3 = this.i; i3 < 65536; i3 *= 2) {
            i2++;
        }
        int i4 = 8 - i2;
        int i5 = this.i;
        a(i5);
        b(this.m, outputStream);
        while (true) {
            int iA2 = a();
            if (iA2 != -1) {
                int i6 = (iA2 << this.d) + iA;
                int i7 = (iA2 << i4) ^ iA;
                if (this.g[i7] == i6) {
                    iA = this.h[i7];
                } else {
                    if (this.g[i7] >= 0) {
                        int i8 = i5 - i7;
                        if (i7 == 0) {
                            i8 = 1;
                        }
                        do {
                            i7 -= i8;
                            if (i7 < 0) {
                                i7 += i5;
                            }
                            if (this.g[i7] == i6) {
                                iA = this.h[i7];
                                break;
                            }
                        } while (this.g[i7] >= 0);
                    }
                    b(iA, outputStream);
                    if (this.j < this.f) {
                        int[] iArr = this.h;
                        int i9 = this.j;
                        this.j = i9 + 1;
                        iArr[i7] = i9;
                        this.g[i7] = i6;
                    } else {
                        a(outputStream);
                    }
                    iA = iA2;
                }
            } else {
                b(iA, outputStream);
                b(this.n, outputStream);
                return;
            }
        }
    }

    void b(OutputStream outputStream) throws IOException {
        outputStream.write(this.x);
        this.y = this.u * this.v;
        this.z = 0;
        a(this.x + 1, outputStream);
        outputStream.write(0);
    }

    void c(OutputStream outputStream) throws IOException {
        if (this.r > 0) {
            outputStream.write(this.r);
            outputStream.write(this.s, 0, this.r);
            this.r = 0;
        }
    }

    private int a() {
        if (this.y == 0) {
            return -1;
        }
        this.y--;
        byte[] bArr = this.w;
        int i = this.z;
        this.z = i + 1;
        return bArr[i] & UByte.MAX_VALUE;
    }

    void b(int i, OutputStream outputStream) throws IOException {
        this.o &= this.q[this.p];
        if (this.p > 0) {
            this.o |= i << this.p;
        } else {
            this.o = i;
        }
        this.p += this.c;
        while (this.p >= 8) {
            a((byte) (this.o & 255), outputStream);
            this.o >>= 8;
            this.p -= 8;
        }
        if (this.j > this.e || this.k) {
            if (this.k) {
                int i2 = this.l;
                this.c = i2;
                this.e = b(i2);
                this.k = false;
            } else {
                this.c++;
                if (this.c == this.d) {
                    this.e = this.f;
                } else {
                    this.e = b(this.c);
                }
            }
        }
        if (i == this.n) {
            while (this.p > 0) {
                a((byte) (this.o & 255), outputStream);
                this.o >>= 8;
                this.p -= 8;
            }
            c(outputStream);
        }
    }
}
