package com.ss.union.game.sdk.core.glide.gifencoder;

import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.union.game.sdk.feedback.picture.select.d;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class b {
    protected static final int A = 18;
    protected static final int B = 262144;
    protected static final int a = 256;
    protected static final int b = 499;
    protected static final int c = 491;
    protected static final int d = 487;
    protected static final int e = 503;
    protected static final int f = 1509;
    protected static final int g = 255;
    protected static final int h = 4;
    protected static final int i = 100;
    protected static final int j = 16;
    protected static final int k = 65536;
    protected static final int l = 10;
    protected static final int m = 1024;
    protected static final int n = 10;
    protected static final int o = 64;
    protected static final int p = 65536;
    protected static final int q = 32;
    protected static final int r = 6;
    protected static final int s = 64;
    protected static final int t = 2048;
    protected static final int u = 30;
    protected static final int v = 10;
    protected static final int w = 1024;
    protected static final int y = 8;
    protected static final int z = 256;
    protected byte[] C;
    protected int D;
    protected int E;
    protected int x;
    protected int[] G = new int[ApkDownloadComplianceInterface.INSTALL_BITS];
    protected int[] H = new int[ApkDownloadComplianceInterface.INSTALL_BITS];
    protected int[] I = new int[ApkDownloadComplianceInterface.INSTALL_BITS];
    protected int[] J = new int[32];
    protected int[][] F = new int[ApkDownloadComplianceInterface.INSTALL_BITS][];

    public b(byte[] bArr, int i2, int i3) {
        this.C = bArr;
        this.D = i2;
        this.E = i3;
        for (int i4 = 0; i4 < 256; i4++) {
            this.F[i4] = new int[4];
            int[] iArr = this.F[i4];
            int i5 = (i4 << 12) / ApkDownloadComplianceInterface.INSTALL_BITS;
            iArr[2] = i5;
            iArr[1] = i5;
            iArr[0] = i5;
            this.I[i4] = 256;
            this.H[i4] = 0;
        }
    }

    public byte[] a() {
        byte[] bArr = new byte[768];
        int[] iArr = new int[ApkDownloadComplianceInterface.INSTALL_BITS];
        for (int i2 = 0; i2 < 256; i2++) {
            iArr[this.F[i2][3]] = i2;
        }
        int i3 = 0;
        int i4 = 0;
        while (i3 < 256) {
            int i5 = iArr[i3];
            int i6 = i4 + 1;
            bArr[i4] = (byte) this.F[i5][0];
            int i7 = i6 + 1;
            bArr[i6] = (byte) this.F[i5][1];
            bArr[i7] = (byte) this.F[i5][2];
            i3++;
            i4 = i7 + 1;
        }
        return bArr;
    }

    public void b() {
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < 256) {
            int[] iArr = this.F[i2];
            int i5 = i2 + 1;
            int i6 = i2;
            int i7 = iArr[1];
            for (int i8 = i5; i8 < 256; i8++) {
                int[] iArr2 = this.F[i8];
                if (iArr2[1] < i7) {
                    i7 = iArr2[1];
                    i6 = i8;
                }
            }
            int[] iArr3 = this.F[i6];
            if (i2 != i6) {
                int i9 = iArr3[0];
                iArr3[0] = iArr[0];
                iArr[0] = i9;
                int i10 = iArr3[1];
                iArr3[1] = iArr[1];
                iArr[1] = i10;
                int i11 = iArr3[2];
                iArr3[2] = iArr[2];
                iArr[2] = i11;
                int i12 = iArr3[3];
                iArr3[3] = iArr[3];
                iArr[3] = i12;
            }
            if (i7 != i3) {
                this.G[i3] = (i4 + i2) >> 1;
                while (true) {
                    i3++;
                    if (i3 >= i7) {
                        break;
                    } else {
                        this.G[i3] = i2;
                    }
                }
                i4 = i2;
                i3 = i7;
            }
            i2 = i5;
        }
        this.G[i3] = (i4 + 255) >> 1;
        for (int i13 = i3 + 1; i13 < 256; i13++) {
            this.G[i13] = 255;
        }
    }

    public void c() {
        int i2;
        if (this.D < f) {
            this.E = 1;
        }
        this.x = ((this.E - 1) / 3) + 30;
        byte[] bArr = this.C;
        int i3 = this.D;
        int i4 = this.D / (this.E * 3);
        int i5 = i4 / 100;
        for (int i6 = 0; i6 < 32; i6++) {
            this.J[i6] = d.d * (((1024 - (i6 * i6)) * ApkDownloadComplianceInterface.INSTALL_BITS) / d.d);
        }
        if (this.D < f) {
            i2 = 3;
        } else if (this.D % b != 0) {
            i2 = 1497;
        } else if (this.D % c != 0) {
            i2 = 1473;
        } else {
            i2 = this.D % d != 0 ? 1461 : f;
        }
        int i7 = i5;
        int i8 = 0;
        int i9 = 0;
        int i10 = d.d;
        int i11 = 32;
        int i12 = 2048;
        while (i8 < i4) {
            int i13 = (bArr[i9 + 0] & UByte.MAX_VALUE) << 4;
            int i14 = (bArr[i9 + 1] & UByte.MAX_VALUE) << 4;
            int i15 = (bArr[i9 + 2] & UByte.MAX_VALUE) << 4;
            int iB = b(i13, i14, i15);
            b(i10, iB, i13, i14, i15);
            if (i11 != 0) {
                a(i11, iB, i13, i14, i15);
            }
            i9 += i2;
            if (i9 >= i3) {
                i9 -= this.D;
            }
            i8++;
            if (i7 == 0) {
                i7 = 1;
            }
            if (i8 % i7 == 0) {
                i10 -= i10 / this.x;
                i12 -= i12 / 30;
                int i16 = i12 >> 6;
                if (i16 <= 1) {
                    i16 = 0;
                }
                for (int i17 = 0; i17 < i16; i17++) {
                    int i18 = i16 * i16;
                    this.J[i17] = (((i18 - (i17 * i17)) * ApkDownloadComplianceInterface.INSTALL_BITS) / i18) * i10;
                }
                i11 = i16;
            }
        }
    }

    public int a(int i2, int i3, int i4) {
        int i5 = this.G[i3];
        int i6 = i5 - 1;
        int i7 = -1;
        int i8 = 1000;
        while (true) {
            if (i5 >= 256 && i6 < 0) {
                return i7;
            }
            if (i5 < 256) {
                int[] iArr = this.F[i5];
                int i9 = iArr[1] - i3;
                if (i9 >= i8) {
                    i5 = ApkDownloadComplianceInterface.INSTALL_BITS;
                } else {
                    i5++;
                    if (i9 < 0) {
                        i9 = -i9;
                    }
                    int i10 = iArr[0] - i2;
                    if (i10 < 0) {
                        i10 = -i10;
                    }
                    int i11 = i9 + i10;
                    if (i11 < i8) {
                        int i12 = iArr[2] - i4;
                        if (i12 < 0) {
                            i12 = -i12;
                        }
                        int i13 = i11 + i12;
                        if (i13 < i8) {
                            i7 = iArr[3];
                            i8 = i13;
                        }
                    }
                }
            }
            if (i6 >= 0) {
                int[] iArr2 = this.F[i6];
                int i14 = i3 - iArr2[1];
                if (i14 >= i8) {
                    i6 = -1;
                } else {
                    i6--;
                    if (i14 < 0) {
                        i14 = -i14;
                    }
                    int i15 = iArr2[0] - i2;
                    if (i15 < 0) {
                        i15 = -i15;
                    }
                    int i16 = i14 + i15;
                    if (i16 < i8) {
                        int i17 = iArr2[2] - i4;
                        if (i17 < 0) {
                            i17 = -i17;
                        }
                        int i18 = i17 + i16;
                        if (i18 < i8) {
                            i7 = iArr2[3];
                            i8 = i18;
                        }
                    }
                }
            }
        }
    }

    public byte[] d() {
        c();
        e();
        b();
        return a();
    }

    public void e() {
        for (int i2 = 0; i2 < 256; i2++) {
            int[] iArr = this.F[i2];
            iArr[0] = iArr[0] >> 4;
            int[] iArr2 = this.F[i2];
            iArr2[1] = iArr2[1] >> 4;
            int[] iArr3 = this.F[i2];
            iArr3[2] = iArr3[2] >> 4;
            this.F[i2][3] = i2;
        }
    }

    protected void a(int i2, int i3, int i4, int i5, int i6) {
        int i7 = i3 - i2;
        if (i7 < -1) {
            i7 = -1;
        }
        int i8 = i3 + i2;
        if (i8 > 256) {
            i8 = ApkDownloadComplianceInterface.INSTALL_BITS;
        }
        int i9 = i3 + 1;
        int i10 = i3 - 1;
        int i11 = 1;
        while (true) {
            if (i9 >= i8 && i10 <= i7) {
                return;
            }
            int i12 = i11 + 1;
            int i13 = this.J[i11];
            if (i9 < i8) {
                int i14 = i9 + 1;
                int[] iArr = this.F[i9];
                try {
                    iArr[0] = iArr[0] - (((iArr[0] - i4) * i13) / 262144);
                    iArr[1] = iArr[1] - (((iArr[1] - i5) * i13) / 262144);
                    iArr[2] = iArr[2] - (((iArr[2] - i6) * i13) / 262144);
                } catch (Exception unused) {
                }
                i9 = i14;
            }
            if (i10 > i7) {
                int i15 = i10 - 1;
                int[] iArr2 = this.F[i10];
                try {
                    iArr2[0] = iArr2[0] - (((iArr2[0] - i4) * i13) / 262144);
                    iArr2[1] = iArr2[1] - (((iArr2[1] - i5) * i13) / 262144);
                    iArr2[2] = iArr2[2] - ((i13 * (iArr2[2] - i6)) / 262144);
                } catch (Exception unused2) {
                }
                i11 = i12;
                i10 = i15;
            } else {
                i11 = i12;
            }
        }
    }

    protected void b(int i2, int i3, int i4, int i5, int i6) {
        int[] iArr = this.F[i3];
        iArr[0] = iArr[0] - (((iArr[0] - i4) * i2) / d.d);
        iArr[1] = iArr[1] - (((iArr[1] - i5) * i2) / d.d);
        iArr[2] = iArr[2] - ((i2 * (iArr[2] - i6)) / d.d);
    }

    protected int b(int i2, int i3, int i4) {
        int i5 = -1;
        int i6 = -1;
        int i7 = Integer.MAX_VALUE;
        int i8 = Integer.MAX_VALUE;
        for (int i9 = 0; i9 < 256; i9++) {
            int[] iArr = this.F[i9];
            int i10 = iArr[0] - i2;
            if (i10 < 0) {
                i10 = -i10;
            }
            int i11 = iArr[1] - i3;
            if (i11 < 0) {
                i11 = -i11;
            }
            int i12 = i10 + i11;
            int i13 = iArr[2] - i4;
            if (i13 < 0) {
                i13 = -i13;
            }
            int i14 = i12 + i13;
            if (i14 < i7) {
                i5 = i9;
                i7 = i14;
            }
            int i15 = i14 - (this.H[i9] >> 12);
            if (i15 < i8) {
                i6 = i9;
                i8 = i15;
            }
            int i16 = this.I[i9] >> 10;
            int[] iArr2 = this.I;
            iArr2[i9] = iArr2[i9] - i16;
            int[] iArr3 = this.H;
            iArr3[i9] = iArr3[i9] + (i16 << 10);
        }
        int[] iArr4 = this.I;
        iArr4[i5] = iArr4[i5] + 64;
        int[] iArr5 = this.H;
        iArr5[i5] = iArr5[i5] - 65536;
        return i6;
    }
}
