package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class am extends as {
    private static final ax d = new ax("");
    private static final an e = new an("", (byte) 0, 0);
    private static final byte[] f = new byte[16];
    private static final byte h = -126;
    private static final byte i = 1;
    private static final byte j = 31;
    private static final byte k = -32;
    private static final int l = 5;
    byte[] a;
    byte[] b;
    byte[] c;
    private r m;
    private short n;
    private an o;
    private Boolean p;
    private final long q;
    private byte[] r;

    private int c(int i2) {
        return (i2 >> 31) ^ (i2 << 1);
    }

    private long c(long j2) {
        return (j2 >> 63) ^ (j2 << 1);
    }

    private boolean c(byte b2) {
        int i2 = b2 & az.m;
        return i2 == 1 || i2 == 2;
    }

    private long d(long j2) {
        return (-(j2 & 1)) ^ (j2 >>> 1);
    }

    private int g(int i2) {
        return (-(i2 & 1)) ^ (i2 >>> 1);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void c() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void e() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void f() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void g() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void i() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void m() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void o() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void q() throws z {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void s() throws z {
    }

    static {
        f[0] = 0;
        f[2] = 1;
        f[3] = 3;
        f[6] = 4;
        f[8] = 5;
        f[10] = 6;
        f[4] = 7;
        f[11] = 8;
        f[15] = 9;
        f[14] = 10;
        f[13] = 11;
        f[12] = 12;
    }

    public static class a implements au {
        private final long a;

        public a() {
            this.a = -1L;
        }

        public a(int i) {
            this.a = i;
        }

        @Override // com.umeng.commonsdk.proguard.au
        public as a(bg bgVar) {
            return new am(bgVar, this.a);
        }
    }

    private static class b {
        public static final byte a = 1;
        public static final byte b = 2;
        public static final byte c = 3;
        public static final byte d = 4;
        public static final byte e = 5;
        public static final byte f = 6;
        public static final byte g = 7;
        public static final byte h = 8;
        public static final byte i = 9;
        public static final byte j = 10;
        public static final byte k = 11;
        public static final byte l = 12;

        private b() {
        }
    }

    public am(bg bgVar, long j2) {
        super(bgVar);
        this.m = new r(15);
        this.n = (short) 0;
        this.o = null;
        this.p = null;
        this.a = new byte[5];
        this.b = new byte[10];
        this.r = new byte[1];
        this.c = new byte[1];
        this.q = j2;
    }

    public am(bg bgVar) {
        this(bgVar, -1L);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void B() {
        this.m.c();
        this.n = (short) 0;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(aq aqVar) throws z, UnsupportedEncodingException {
        b(h);
        d(((aqVar.b << 5) & (-32)) | 1);
        b(aqVar.c);
        a(aqVar.a);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(ax axVar) throws z {
        this.m.a(this.n);
        this.n = (short) 0;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void b() throws z {
        this.n = this.m.a();
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(an anVar) throws z {
        if (anVar.b == 2) {
            this.o = anVar;
        } else {
            a(anVar, (byte) -1);
        }
    }

    private void a(an anVar, byte b2) throws z {
        if (b2 == -1) {
            b2 = e(anVar.b);
        }
        if (anVar.c > this.n && anVar.c - this.n <= 15) {
            d(b2 | ((anVar.c - this.n) << 4));
        } else {
            b(b2);
            a(anVar.c);
        }
        this.n = anVar.c;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void d() throws z {
        b((byte) 0);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(ap apVar) throws z {
        if (apVar.c == 0) {
            d(0);
            return;
        }
        b(apVar.c);
        d(e(apVar.b) | (e(apVar.a) << 4));
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(ao aoVar) throws z {
        a(aoVar.a, aoVar.b);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(aw awVar) throws z {
        a(awVar.a, awVar.b);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(boolean z) throws z {
        if (this.o != null) {
            a(this.o, z ? (byte) 1 : (byte) 2);
            this.o = null;
        } else {
            b(z ? (byte) 1 : (byte) 2);
        }
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(byte b2) throws z {
        b(b2);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(short s) throws z {
        b(c((int) s));
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(int i2) throws z {
        b(c(i2));
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(long j2) throws z {
        b(c(j2));
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(double d2) throws z {
        byte[] bArr = {0, 0, 0, 0, 0, 0, 0, 0};
        a(Double.doubleToLongBits(d2), bArr, 0);
        this.g.b(bArr);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(String str) throws z, UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new z("UTF-8 not supported!");
        }
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(ByteBuffer byteBuffer) throws z {
        a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), byteBuffer.limit() - byteBuffer.position());
    }

    private void a(byte[] bArr, int i2, int i3) throws z {
        b(i3);
        this.g.b(bArr, i2, i3);
    }

    protected void a(byte b2, int i2) throws z {
        if (i2 <= 14) {
            d(e(b2) | (i2 << 4));
        } else {
            d(e(b2) | 240);
            b(i2);
        }
    }

    private void b(int i2) throws z {
        int i3 = 0;
        while ((i2 & (-128)) != 0) {
            this.a[i3] = (byte) ((i2 & 127) | 128);
            i2 >>>= 7;
            i3++;
        }
        this.a[i3] = (byte) i2;
        this.g.b(this.a, 0, i3 + 1);
    }

    private void b(long j2) throws z {
        int i2 = 0;
        while (((-128) & j2) != 0) {
            this.b[i2] = (byte) ((127 & j2) | 128);
            j2 >>>= 7;
            i2++;
        }
        this.b[i2] = (byte) j2;
        this.g.b(this.b, 0, i2 + 1);
    }

    private void a(long j2, byte[] bArr, int i2) {
        bArr[i2 + 0] = (byte) (j2 & 255);
        bArr[i2 + 1] = (byte) ((j2 >> 8) & 255);
        bArr[i2 + 2] = (byte) ((j2 >> 16) & 255);
        bArr[i2 + 3] = (byte) ((j2 >> 24) & 255);
        bArr[i2 + 4] = (byte) ((j2 >> 32) & 255);
        bArr[i2 + 5] = (byte) ((j2 >> 40) & 255);
        bArr[i2 + 6] = (byte) ((j2 >> 48) & 255);
        bArr[i2 + 7] = (byte) ((j2 >> 56) & 255);
    }

    private void b(byte b2) throws z {
        this.r[0] = b2;
        this.g.b(this.r);
    }

    private void d(int i2) throws z {
        b((byte) i2);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public aq h() throws z {
        byte bU = u();
        if (bU != -126) {
            throw new at("Expected protocol id " + Integer.toHexString(-126) + " but got " + Integer.toHexString(bU));
        }
        byte bU2 = u();
        byte b2 = (byte) (bU2 & j);
        if (b2 != 1) {
            throw new at("Expected version 1 but got " + ((int) b2));
        }
        return new aq(z(), (byte) ((bU2 >> 5) & 3), E());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public ax j() throws z {
        this.m.a(this.n);
        this.n = (short) 0;
        return d;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void k() throws z {
        this.n = this.m.a();
    }

    @Override // com.umeng.commonsdk.proguard.as
    public an l() throws z {
        short sV;
        byte bU = u();
        if (bU == 0) {
            return e;
        }
        short s = (short) ((bU & 240) >> 4);
        if (s == 0) {
            sV = v();
        } else {
            sV = (short) (this.n + s);
        }
        byte b2 = (byte) (bU & az.m);
        an anVar = new an("", d(b2), sV);
        if (c(bU)) {
            this.p = b2 == 1 ? Boolean.TRUE : Boolean.FALSE;
        }
        this.n = anVar.c;
        return anVar;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public ap n() throws z {
        int iE = E();
        byte bU = iE == 0 ? (byte) 0 : u();
        return new ap(d((byte) (bU >> 4)), d((byte) (bU & az.m)), iE);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public ao p() throws z {
        byte bU = u();
        int iE = (bU >> 4) & 15;
        if (iE == 15) {
            iE = E();
        }
        return new ao(d(bU), iE);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public aw r() throws z {
        return new aw(p());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public boolean t() throws z {
        if (this.p == null) {
            return u() == 1;
        }
        boolean zBooleanValue = this.p.booleanValue();
        this.p = null;
        return zBooleanValue;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public byte u() throws z {
        if (this.g.h() > 0) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        this.g.d(this.c, 0, 1);
        return this.c[0];
    }

    @Override // com.umeng.commonsdk.proguard.as
    public short v() throws z {
        return (short) g(E());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public int w() throws z {
        return g(E());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public long x() throws z {
        return d(F());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public double y() throws z {
        byte[] bArr = new byte[8];
        this.g.d(bArr, 0, 8);
        return Double.longBitsToDouble(a(bArr));
    }

    @Override // com.umeng.commonsdk.proguard.as
    public String z() throws z {
        int iE = E();
        f(iE);
        if (iE == 0) {
            return "";
        }
        try {
            if (this.g.h() >= iE) {
                String str = new String(this.g.f(), this.g.g(), iE, "UTF-8");
                this.g.a(iE);
                return str;
            }
            return new String(e(iE), "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new z("UTF-8 not supported!");
        }
    }

    @Override // com.umeng.commonsdk.proguard.as
    public ByteBuffer A() throws z {
        int iE = E();
        f(iE);
        if (iE == 0) {
            return ByteBuffer.wrap(new byte[0]);
        }
        byte[] bArr = new byte[iE];
        this.g.d(bArr, 0, iE);
        return ByteBuffer.wrap(bArr);
    }

    private byte[] e(int i2) throws z {
        if (i2 == 0) {
            return new byte[0];
        }
        byte[] bArr = new byte[i2];
        this.g.d(bArr, 0, i2);
        return bArr;
    }

    private void f(int i2) throws at {
        if (i2 < 0) {
            throw new at("Negative length: " + i2);
        }
        if (this.q == -1 || i2 <= this.q) {
            return;
        }
        throw new at("Length exceeded max allowed: " + i2);
    }

    private int E() throws z {
        int i2 = 0;
        if (this.g.h() >= 5) {
            byte[] bArrF = this.g.f();
            int iG = this.g.g();
            int i3 = 0;
            int i4 = 0;
            while (true) {
                byte b2 = bArrF[iG + i2];
                i3 |= (b2 & 127) << i4;
                if ((b2 & 128) != 128) {
                    this.g.a(i2 + 1);
                    return i3;
                }
                i4 += 7;
                i2++;
            }
        } else {
            int i5 = 0;
            while (true) {
                byte bU = u();
                i2 |= (bU & 127) << i5;
                if ((bU & 128) != 128) {
                    return i2;
                }
                i5 += 7;
            }
        }
    }

    private long F() throws z {
        int i2 = 0;
        long j2 = 0;
        if (this.g.h() >= 10) {
            byte[] bArrF = this.g.f();
            int iG = this.g.g();
            int i3 = 0;
            while (true) {
                j2 |= (r7 & 127) << i3;
                if ((bArrF[iG + i2] & 128) != 128) {
                    break;
                }
                i3 += 7;
                i2++;
            }
            this.g.a(i2 + 1);
        } else {
            while (true) {
                j2 |= (r0 & 127) << i2;
                if ((u() & 128) != 128) {
                    break;
                }
                i2 += 7;
            }
        }
        return j2;
    }

    private long a(byte[] bArr) {
        return ((bArr[7] & 255) << 56) | ((bArr[6] & 255) << 48) | ((bArr[5] & 255) << 40) | ((bArr[4] & 255) << 32) | ((bArr[3] & 255) << 24) | ((bArr[2] & 255) << 16) | ((bArr[1] & 255) << 8) | (255 & bArr[0]);
    }

    private byte d(byte b2) throws at {
        byte b3 = (byte) (b2 & az.m);
        switch (b3) {
            case 0:
                return (byte) 0;
            case 1:
            case 2:
                return (byte) 2;
            case 3:
                return (byte) 3;
            case 4:
                return (byte) 6;
            case 5:
                return (byte) 8;
            case 6:
                return (byte) 10;
            case 7:
                return (byte) 4;
            case 8:
                return (byte) 11;
            case 9:
                return az.m;
            case 10:
                return az.l;
            case 11:
                return az.k;
            case 12:
                return (byte) 12;
            default:
                throw new at("don't know what type: " + ((int) b3));
        }
    }

    private byte e(byte b2) {
        return f[b2];
    }
}
