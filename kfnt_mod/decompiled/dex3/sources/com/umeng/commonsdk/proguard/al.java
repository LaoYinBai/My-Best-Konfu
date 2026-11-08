package com.umeng.commonsdk.proguard;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class al extends as {
    protected static final int a = -65536;
    protected static final int b = -2147418112;
    private static final ax h = new ax();
    protected boolean c;
    protected boolean d;
    protected int e;
    protected boolean f;
    private byte[] i;
    private byte[] j;
    private byte[] k;
    private byte[] l;
    private byte[] m;
    private byte[] n;
    private byte[] o;
    private byte[] p;

    @Override // com.umeng.commonsdk.proguard.as
    public void a() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(ax axVar) {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void b() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void c() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void e() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void f() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void g() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void i() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void k() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void m() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void o() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void q() {
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void s() {
    }

    public static class a implements au {
        protected boolean a;
        protected boolean b;
        protected int c;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.a = false;
            this.b = true;
            this.a = z;
            this.b = z2;
            this.c = i;
        }

        @Override // com.umeng.commonsdk.proguard.au
        public as a(bg bgVar) {
            al alVar = new al(bgVar, this.a, this.b);
            if (this.c != 0) {
                alVar.c(this.c);
            }
            return alVar;
        }
    }

    public al(bg bgVar) {
        this(bgVar, false, true);
    }

    public al(bg bgVar, boolean z, boolean z2) {
        super(bgVar);
        this.c = false;
        this.d = true;
        this.f = false;
        this.i = new byte[1];
        this.j = new byte[2];
        this.k = new byte[4];
        this.l = new byte[8];
        this.m = new byte[1];
        this.n = new byte[2];
        this.o = new byte[4];
        this.p = new byte[8];
        this.c = z;
        this.d = z2;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(aq aqVar) throws z, UnsupportedEncodingException {
        if (this.d) {
            a(b | aqVar.b);
            a(aqVar.a);
            a(aqVar.c);
        } else {
            a(aqVar.a);
            a(aqVar.b);
            a(aqVar.c);
        }
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(an anVar) throws z {
        a(anVar.b);
        a(anVar.c);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void d() throws z {
        a((byte) 0);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(ap apVar) throws z {
        a(apVar.a);
        a(apVar.b);
        a(apVar.c);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(ao aoVar) throws z {
        a(aoVar.a);
        a(aoVar.b);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(aw awVar) throws z {
        a(awVar.a);
        a(awVar.b);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(boolean z) throws z {
        a(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(byte b2) throws z {
        this.i[0] = b2;
        this.g.b(this.i, 0, 1);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(short s) throws z {
        this.j[0] = (byte) ((s >> 8) & 255);
        this.j[1] = (byte) (s & 255);
        this.g.b(this.j, 0, 2);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(int i) throws z {
        this.k[0] = (byte) ((i >> 24) & 255);
        this.k[1] = (byte) ((i >> 16) & 255);
        this.k[2] = (byte) ((i >> 8) & 255);
        this.k[3] = (byte) (i & 255);
        this.g.b(this.k, 0, 4);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(long j) throws z {
        this.l[0] = (byte) ((j >> 56) & 255);
        this.l[1] = (byte) ((j >> 48) & 255);
        this.l[2] = (byte) ((j >> 40) & 255);
        this.l[3] = (byte) ((j >> 32) & 255);
        this.l[4] = (byte) ((j >> 24) & 255);
        this.l[5] = (byte) ((j >> 16) & 255);
        this.l[6] = (byte) ((j >> 8) & 255);
        this.l[7] = (byte) (j & 255);
        this.g.b(this.l, 0, 8);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(double d) throws z {
        a(Double.doubleToLongBits(d));
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(String str) throws z, UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            a(bytes.length);
            this.g.b(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new z("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.commonsdk.proguard.as
    public void a(ByteBuffer byteBuffer) throws z {
        int iLimit = byteBuffer.limit() - byteBuffer.position();
        a(iLimit);
        this.g.b(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), iLimit);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public aq h() throws z {
        int iW = w();
        if (iW < 0) {
            if ((a & iW) != b) {
                throw new at(4, "Bad version in readMessageBegin");
            }
            return new aq(z(), (byte) (iW & 255), w());
        }
        if (this.c) {
            throw new at(4, "Missing version in readMessageBegin, old client?");
        }
        return new aq(b(iW), u(), w());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public ax j() {
        return h;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public an l() throws z {
        byte bU = u();
        return new an("", bU, bU == 0 ? (short) 0 : v());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public ap n() throws z {
        return new ap(u(), u(), w());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public ao p() throws z {
        return new ao(u(), w());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public aw r() throws z {
        return new aw(u(), w());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public boolean t() throws z {
        return u() == 1;
    }

    @Override // com.umeng.commonsdk.proguard.as
    public byte u() throws z {
        if (this.g.h() >= 1) {
            byte b2 = this.g.f()[this.g.g()];
            this.g.a(1);
            return b2;
        }
        a(this.m, 0, 1);
        return this.m[0];
    }

    @Override // com.umeng.commonsdk.proguard.as
    public short v() throws z {
        byte[] bArrF = this.n;
        int iG = 0;
        if (this.g.h() >= 2) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(2);
        } else {
            a(this.n, 0, 2);
        }
        return (short) ((bArrF[iG + 1] & UByte.MAX_VALUE) | ((bArrF[iG] & UByte.MAX_VALUE) << 8));
    }

    @Override // com.umeng.commonsdk.proguard.as
    public int w() throws z {
        byte[] bArrF = this.o;
        int iG = 0;
        if (this.g.h() >= 4) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(4);
        } else {
            a(this.o, 0, 4);
        }
        return (bArrF[iG + 3] & UByte.MAX_VALUE) | ((bArrF[iG] & UByte.MAX_VALUE) << 24) | ((bArrF[iG + 1] & UByte.MAX_VALUE) << 16) | ((bArrF[iG + 2] & UByte.MAX_VALUE) << 8);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public long x() throws z {
        byte[] bArrF = this.p;
        int iG = 0;
        if (this.g.h() >= 8) {
            bArrF = this.g.f();
            iG = this.g.g();
            this.g.a(8);
        } else {
            a(this.p, 0, 8);
        }
        return (bArrF[iG + 7] & UByte.MAX_VALUE) | ((bArrF[iG] & UByte.MAX_VALUE) << 56) | ((bArrF[iG + 1] & UByte.MAX_VALUE) << 48) | ((bArrF[iG + 2] & UByte.MAX_VALUE) << 40) | ((bArrF[iG + 3] & UByte.MAX_VALUE) << 32) | ((bArrF[iG + 4] & UByte.MAX_VALUE) << 24) | ((bArrF[iG + 5] & UByte.MAX_VALUE) << 16) | ((bArrF[iG + 6] & UByte.MAX_VALUE) << 8);
    }

    @Override // com.umeng.commonsdk.proguard.as
    public double y() throws z {
        return Double.longBitsToDouble(x());
    }

    @Override // com.umeng.commonsdk.proguard.as
    public String z() throws z {
        int iW = w();
        if (this.g.h() >= iW) {
            try {
                String str = new String(this.g.f(), this.g.g(), iW, "UTF-8");
                this.g.a(iW);
                return str;
            } catch (UnsupportedEncodingException unused) {
                throw new z("JVM DOES NOT SUPPORT UTF-8");
            }
        }
        return b(iW);
    }

    public String b(int i) throws z {
        try {
            d(i);
            byte[] bArr = new byte[i];
            this.g.d(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new z("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.umeng.commonsdk.proguard.as
    public ByteBuffer A() throws z {
        int iW = w();
        d(iW);
        if (this.g.h() >= iW) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.g.f(), this.g.g(), iW);
            this.g.a(iW);
            return byteBufferWrap;
        }
        byte[] bArr = new byte[iW];
        this.g.d(bArr, 0, iW);
        return ByteBuffer.wrap(bArr);
    }

    private int a(byte[] bArr, int i, int i2) throws z {
        d(i2);
        return this.g.d(bArr, i, i2);
    }

    public void c(int i) {
        this.e = i;
        this.f = true;
    }

    protected void d(int i) throws z {
        if (i < 0) {
            throw new at("Negative length: " + i);
        }
        if (this.f) {
            this.e -= i;
            if (this.e >= 0) {
                return;
            }
            throw new at("Message length exceeded: " + i);
        }
    }
}
