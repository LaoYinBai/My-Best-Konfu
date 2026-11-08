package com.umeng.message.proguard;

import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.umeng.commonsdk.proguard.az;
import java.math.BigInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class d extends e {
    private static final int m = 6;
    private static final int n = 3;
    private static final int o = 4;
    private static final int s = 63;
    private final byte[] t;
    private final byte[] u;
    private final byte[] v;
    private final int w;
    private final int x;
    private int y;
    static final byte[] a = {az.k, 10};
    private static final byte[] p = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 43, 47};
    private static final byte[] q = {65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 45, 95};
    private static final byte[] r = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, 62, -1, 63, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, az.k, az.l, az.m, az.n, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, 63, -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51};

    public d() {
        this(0);
    }

    public d(boolean z) {
        this(76, a, z);
    }

    public d(int i) {
        this(i, a);
    }

    public d(int i, byte[] bArr) {
        this(i, bArr, false);
    }

    public d(int i, byte[] bArr, boolean z) {
        super(3, 4, i, bArr == null ? 0 : bArr.length);
        this.u = r;
        if (bArr != null) {
            if (n(bArr)) {
                throw new IllegalArgumentException("lineSeparator must not contain base64 characters: [" + b.f(bArr) + "]");
            }
            if (i > 0) {
                this.x = bArr.length + 4;
                this.v = new byte[bArr.length];
                System.arraycopy(bArr, 0, this.v, 0, bArr.length);
            } else {
                this.x = 4;
                this.v = null;
            }
        } else {
            this.x = 4;
            this.v = null;
        }
        this.w = this.x - 1;
        this.t = z ? q : p;
    }

    public boolean a() {
        return this.t == q;
    }

    @Override // com.umeng.message.proguard.e
    void a(byte[] bArr, int i, int i2) {
        if (this.j) {
            return;
        }
        if (i2 >= 0) {
            int i3 = i;
            int i4 = 0;
            while (i4 < i2) {
                a(this.x);
                this.l = (this.l + 1) % 3;
                int i5 = i3 + 1;
                int i6 = bArr[i3];
                if (i6 < 0) {
                    i6 += ApkDownloadComplianceInterface.INSTALL_BITS;
                }
                this.y = (this.y << 8) + i6;
                if (this.l == 0) {
                    byte[] bArr2 = this.h;
                    int i7 = this.i;
                    this.i = i7 + 1;
                    bArr2[i7] = this.t[(this.y >> 18) & 63];
                    byte[] bArr3 = this.h;
                    int i8 = this.i;
                    this.i = i8 + 1;
                    bArr3[i8] = this.t[(this.y >> 12) & 63];
                    byte[] bArr4 = this.h;
                    int i9 = this.i;
                    this.i = i9 + 1;
                    bArr4[i9] = this.t[(this.y >> 6) & 63];
                    byte[] bArr5 = this.h;
                    int i10 = this.i;
                    this.i = i10 + 1;
                    bArr5[i10] = this.t[this.y & 63];
                    this.k += 4;
                    if (this.g > 0 && this.g <= this.k) {
                        System.arraycopy(this.v, 0, this.h, this.i, this.v.length);
                        this.i += this.v.length;
                        this.k = 0;
                    }
                }
                i4++;
                i3 = i5;
            }
            return;
        }
        this.j = true;
        if (this.l == 0 && this.g == 0) {
            return;
        }
        a(this.x);
        int i11 = this.i;
        switch (this.l) {
            case 1:
                byte[] bArr6 = this.h;
                int i12 = this.i;
                this.i = i12 + 1;
                bArr6[i12] = this.t[(this.y >> 2) & 63];
                byte[] bArr7 = this.h;
                int i13 = this.i;
                this.i = i13 + 1;
                bArr7[i13] = this.t[(this.y << 4) & 63];
                if (this.t == p) {
                    byte[] bArr8 = this.h;
                    int i14 = this.i;
                    this.i = i14 + 1;
                    bArr8[i14] = 61;
                    byte[] bArr9 = this.h;
                    int i15 = this.i;
                    this.i = i15 + 1;
                    bArr9[i15] = 61;
                    break;
                }
                break;
            case 2:
                byte[] bArr10 = this.h;
                int i16 = this.i;
                this.i = i16 + 1;
                bArr10[i16] = this.t[(this.y >> 10) & 63];
                byte[] bArr11 = this.h;
                int i17 = this.i;
                this.i = i17 + 1;
                bArr11[i17] = this.t[(this.y >> 4) & 63];
                byte[] bArr12 = this.h;
                int i18 = this.i;
                this.i = i18 + 1;
                bArr12[i18] = this.t[(this.y << 2) & 63];
                if (this.t == p) {
                    byte[] bArr13 = this.h;
                    int i19 = this.i;
                    this.i = i19 + 1;
                    bArr13[i19] = 61;
                    break;
                }
                break;
        }
        this.k += this.i - i11;
        if (this.g <= 0 || this.k <= 0) {
            return;
        }
        System.arraycopy(this.v, 0, this.h, this.i, this.v.length);
        this.i += this.v.length;
    }

    @Override // com.umeng.message.proguard.e
    void b(byte[] bArr, int i, int i2) {
        byte b;
        if (this.j) {
        }
        if (i2 < 0) {
            this.j = true;
        }
        int i3 = 0;
        while (true) {
            if (i3 >= i2) {
                break;
            }
            a(this.w);
            int i4 = i + 1;
            byte b2 = bArr[i];
            if (b2 == 61) {
                this.j = true;
                break;
            }
            if (b2 >= 0 && b2 < r.length && (b = r[b2]) >= 0) {
                this.l = (this.l + 1) % 4;
                this.y = (this.y << 6) + b;
                if (this.l == 0) {
                    byte[] bArr2 = this.h;
                    int i5 = this.i;
                    this.i = i5 + 1;
                    bArr2[i5] = (byte) ((this.y >> 16) & 255);
                    byte[] bArr3 = this.h;
                    int i6 = this.i;
                    this.i = i6 + 1;
                    bArr3[i6] = (byte) ((this.y >> 8) & 255);
                    byte[] bArr4 = this.h;
                    int i7 = this.i;
                    this.i = i7 + 1;
                    bArr4[i7] = (byte) (this.y & 255);
                }
            }
            i3++;
            i = i4;
        }
        if (!this.j || this.l == 0) {
            return;
        }
        a(this.w);
        switch (this.l) {
            case 2:
                this.y >>= 4;
                byte[] bArr5 = this.h;
                int i8 = this.i;
                this.i = i8 + 1;
                bArr5[i8] = (byte) (this.y & 255);
                break;
            case 3:
                this.y >>= 2;
                byte[] bArr6 = this.h;
                int i9 = this.i;
                this.i = i9 + 1;
                bArr6[i9] = (byte) ((this.y >> 8) & 255);
                byte[] bArr7 = this.h;
                int i10 = this.i;
                this.i = i10 + 1;
                bArr7[i10] = (byte) (this.y & 255);
                break;
        }
    }

    public static boolean a(byte b) {
        return b == 61 || (b >= 0 && b < r.length && r[b] != -1);
    }

    public static boolean a(String str) {
        return b(b.f(str));
    }

    public static boolean a(byte[] bArr) {
        return b(bArr);
    }

    public static boolean b(byte[] bArr) {
        for (int i = 0; i < bArr.length; i++) {
            if (!a(bArr[i]) && !c(bArr[i])) {
                return false;
            }
        }
        return true;
    }

    public static byte[] c(byte[] bArr) {
        return a(bArr, false);
    }

    public static String d(byte[] bArr) {
        return b.f(a(bArr, false));
    }

    public static byte[] e(byte[] bArr) {
        return a(bArr, false, true);
    }

    public static String f(byte[] bArr) {
        return b.f(a(bArr, false, true));
    }

    public static byte[] g(byte[] bArr) {
        return a(bArr, true);
    }

    public static byte[] a(byte[] bArr, boolean z) {
        return a(bArr, z, false);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2) {
        return a(bArr, z, z2, Integer.MAX_VALUE);
    }

    public static byte[] a(byte[] bArr, boolean z, boolean z2, int i) {
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        d dVar = z ? new d(z2) : new d(0, a, z2);
        long jO = dVar.o(bArr);
        if (jO > i) {
            throw new IllegalArgumentException("Input array too big, the output array would be bigger (" + jO + ") than the specified maximum size of " + i);
        }
        return dVar.l(bArr);
    }

    public static byte[] b(String str) {
        return new d().c(str);
    }

    public static byte[] h(byte[] bArr) {
        return new d().k(bArr);
    }

    public static BigInteger i(byte[] bArr) {
        return new BigInteger(1, h(bArr));
    }

    public static byte[] a(BigInteger bigInteger) {
        if (bigInteger == null) {
            throw new NullPointerException("encodeInteger called with null parameter");
        }
        return a(b(bigInteger), false);
    }

    static byte[] b(BigInteger bigInteger) {
        int iBitLength = ((bigInteger.bitLength() + 7) >> 3) << 3;
        byte[] byteArray = bigInteger.toByteArray();
        if (bigInteger.bitLength() % 8 != 0 && (bigInteger.bitLength() / 8) + 1 == iBitLength / 8) {
            return byteArray;
        }
        int i = 0;
        int length = byteArray.length;
        if (bigInteger.bitLength() % 8 == 0) {
            length--;
            i = 1;
        }
        int i2 = iBitLength / 8;
        int i3 = i2 - length;
        byte[] bArr = new byte[i2];
        System.arraycopy(byteArray, i, bArr, i3, length);
        return bArr;
    }

    @Override // com.umeng.message.proguard.e
    protected boolean b(byte b) {
        return b >= 0 && b < this.u.length && this.u[b] != -1;
    }
}
