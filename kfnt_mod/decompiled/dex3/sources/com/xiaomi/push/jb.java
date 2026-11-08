package com.xiaomi.push;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import kotlin.UByte;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class jb extends jf {
    private static final jk a = new jk();

    /* renamed from: a, reason: collision with other field name */
    protected int f805a;

    /* renamed from: a, reason: collision with other field name */
    protected boolean f806a;

    /* renamed from: a, reason: collision with other field name */
    private byte[] f807a;
    protected boolean b;

    /* renamed from: b, reason: collision with other field name */
    private byte[] f808b;
    protected boolean c;

    /* renamed from: c, reason: collision with other field name */
    private byte[] f809c;
    private byte[] d;
    private byte[] e;
    private byte[] f;
    private byte[] g;
    private byte[] h;

    public static class a implements jh {
        protected int a;

        /* renamed from: a, reason: collision with other field name */
        protected boolean f810a;
        protected boolean b;

        public a() {
            this(false, true);
        }

        public a(boolean z, boolean z2) {
            this(z, z2, 0);
        }

        public a(boolean z, boolean z2, int i) {
            this.f810a = false;
            this.b = true;
            this.f810a = z;
            this.b = z2;
            this.a = i;
        }

        @Override // com.xiaomi.push.jh
        public jf a(jp jpVar) {
            jb jbVar = new jb(jpVar, this.f810a, this.b);
            if (this.a != 0) {
                jbVar.b(this.a);
            }
            return jbVar;
        }
    }

    public jb(jp jpVar, boolean z, boolean z2) {
        super(jpVar);
        this.f806a = false;
        this.b = true;
        this.c = false;
        this.f807a = new byte[1];
        this.f808b = new byte[2];
        this.f809c = new byte[4];
        this.d = new byte[8];
        this.e = new byte[1];
        this.f = new byte[2];
        this.g = new byte[4];
        this.h = new byte[8];
        this.f806a = z;
        this.b = z2;
    }

    private int a(byte[] bArr, int i, int i2) throws iz {
        c(i2);
        return this.a.b(bArr, i, i2);
    }

    @Override // com.xiaomi.push.jf
    public byte a() throws iz {
        if (this.a.b() < 1) {
            a(this.e, 0, 1);
            return this.e[0];
        }
        byte b = this.a.mo543a()[this.a.a()];
        this.a.a(1);
        return b;
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public double mo528a() {
        return Double.longBitsToDouble(mo530a());
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public int mo529a() throws iz {
        byte[] bArrMo543a = this.g;
        int iA = 0;
        if (this.a.b() >= 4) {
            bArrMo543a = this.a.mo543a();
            iA = this.a.a();
            this.a.a(4);
        } else {
            a(this.g, 0, 4);
        }
        return (bArrMo543a[iA + 3] & UByte.MAX_VALUE) | ((bArrMo543a[iA] & UByte.MAX_VALUE) << 24) | ((bArrMo543a[iA + 1] & UByte.MAX_VALUE) << 16) | ((bArrMo543a[iA + 2] & UByte.MAX_VALUE) << 8);
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public long mo530a() throws iz {
        byte[] bArrMo543a = this.h;
        int iA = 0;
        if (this.a.b() >= 8) {
            bArrMo543a = this.a.mo543a();
            iA = this.a.a();
            this.a.a(8);
        } else {
            a(this.h, 0, 8);
        }
        return (bArrMo543a[iA + 7] & UByte.MAX_VALUE) | ((bArrMo543a[iA] & UByte.MAX_VALUE) << 56) | ((bArrMo543a[iA + 1] & UByte.MAX_VALUE) << 48) | ((bArrMo543a[iA + 2] & UByte.MAX_VALUE) << 40) | ((bArrMo543a[iA + 3] & UByte.MAX_VALUE) << 32) | ((bArrMo543a[iA + 4] & UByte.MAX_VALUE) << 24) | ((bArrMo543a[iA + 5] & UByte.MAX_VALUE) << 16) | ((bArrMo543a[iA + 6] & UByte.MAX_VALUE) << 8);
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public jc mo531a() throws iz {
        byte bA = a();
        return new jc("", bA, bA == 0 ? (short) 0 : mo538a());
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public jd mo532a() {
        return new jd(a(), mo529a());
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public je mo533a() {
        return new je(a(), a(), mo529a());
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public jj mo534a() {
        return new jj(a(), mo529a());
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public jk mo535a() {
        return a;
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public String mo536a() throws iz {
        int iMo529a = mo529a();
        if (this.a.b() < iMo529a) {
            return a(iMo529a);
        }
        try {
            String str = new String(this.a.mo543a(), this.a.a(), iMo529a, "UTF-8");
            this.a.a(iMo529a);
            return str;
        } catch (UnsupportedEncodingException unused) {
            throw new iz("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    public String a(int i) throws iz {
        try {
            c(i);
            byte[] bArr = new byte[i];
            this.a.b(bArr, 0, i);
            return new String(bArr, "UTF-8");
        } catch (UnsupportedEncodingException unused) {
            throw new iz("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public ByteBuffer mo537a() throws iz {
        int iMo529a = mo529a();
        c(iMo529a);
        if (this.a.b() >= iMo529a) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(this.a.mo543a(), this.a.a(), iMo529a);
            this.a.a(iMo529a);
            return byteBufferWrap;
        }
        byte[] bArr = new byte[iMo529a];
        this.a.b(bArr, 0, iMo529a);
        return ByteBuffer.wrap(bArr);
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public short mo538a() throws iz {
        byte[] bArrMo543a = this.f;
        int iA = 0;
        if (this.a.b() >= 2) {
            bArrMo543a = this.a.mo543a();
            iA = this.a.a();
            this.a.a(2);
        } else {
            a(this.f, 0, 2);
        }
        return (short) ((bArrMo543a[iA + 1] & UByte.MAX_VALUE) | ((bArrMo543a[iA] & UByte.MAX_VALUE) << 8));
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public void mo539a() {
    }

    @Override // com.xiaomi.push.jf
    public void a(byte b) {
        this.f807a[0] = b;
        this.a.mo542a(this.f807a, 0, 1);
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public void mo540a(int i) {
        this.f809c[0] = (byte) ((i >> 24) & 255);
        this.f809c[1] = (byte) ((i >> 16) & 255);
        this.f809c[2] = (byte) ((i >> 8) & 255);
        this.f809c[3] = (byte) (i & 255);
        this.a.mo542a(this.f809c, 0, 4);
    }

    @Override // com.xiaomi.push.jf
    public void a(long j) {
        this.d[0] = (byte) ((j >> 56) & 255);
        this.d[1] = (byte) ((j >> 48) & 255);
        this.d[2] = (byte) ((j >> 40) & 255);
        this.d[3] = (byte) ((j >> 32) & 255);
        this.d[4] = (byte) ((j >> 24) & 255);
        this.d[5] = (byte) ((j >> 16) & 255);
        this.d[6] = (byte) ((j >> 8) & 255);
        this.d[7] = (byte) (j & 255);
        this.a.mo542a(this.d, 0, 8);
    }

    @Override // com.xiaomi.push.jf
    public void a(jc jcVar) {
        a(jcVar.a);
        a(jcVar.f812a);
    }

    @Override // com.xiaomi.push.jf
    public void a(jd jdVar) {
        a(jdVar.a);
        mo540a(jdVar.f813a);
    }

    @Override // com.xiaomi.push.jf
    public void a(je jeVar) {
        a(jeVar.a);
        a(jeVar.b);
        mo540a(jeVar.f814a);
    }

    @Override // com.xiaomi.push.jf
    public void a(jk jkVar) {
    }

    @Override // com.xiaomi.push.jf
    public void a(String str) throws iz, UnsupportedEncodingException {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            mo540a(bytes.length);
            this.a.mo542a(bytes, 0, bytes.length);
        } catch (UnsupportedEncodingException unused) {
            throw new iz("JVM DOES NOT SUPPORT UTF-8");
        }
    }

    @Override // com.xiaomi.push.jf
    public void a(ByteBuffer byteBuffer) {
        int iLimit = (byteBuffer.limit() - byteBuffer.position()) - byteBuffer.arrayOffset();
        mo540a(iLimit);
        this.a.mo542a(byteBuffer.array(), byteBuffer.position() + byteBuffer.arrayOffset(), iLimit);
    }

    @Override // com.xiaomi.push.jf
    public void a(short s) {
        this.f808b[0] = (byte) ((s >> 8) & 255);
        this.f808b[1] = (byte) (s & 255);
        this.a.mo542a(this.f808b, 0, 2);
    }

    @Override // com.xiaomi.push.jf
    public void a(boolean z) {
        a(z ? (byte) 1 : (byte) 0);
    }

    @Override // com.xiaomi.push.jf
    /* renamed from: a, reason: collision with other method in class */
    public boolean mo541a() {
        return a() == 1;
    }

    @Override // com.xiaomi.push.jf
    public void b() {
    }

    public void b(int i) {
        this.f805a = i;
        this.c = true;
    }

    @Override // com.xiaomi.push.jf
    public void c() {
        a((byte) 0);
    }

    protected void c(int i) throws iz {
        if (i < 0) {
            throw new iz("Negative length: " + i);
        }
        if (this.c) {
            this.f805a -= i;
            if (this.f805a >= 0) {
                return;
            }
            throw new iz("Message length exceeded: " + i);
        }
    }

    @Override // com.xiaomi.push.jf
    public void d() {
    }

    @Override // com.xiaomi.push.jf
    public void e() {
    }

    @Override // com.xiaomi.push.jf
    public void f() {
    }

    @Override // com.xiaomi.push.jf
    public void g() {
    }

    @Override // com.xiaomi.push.jf
    public void h() {
    }

    @Override // com.xiaomi.push.jf
    public void i() {
    }

    @Override // com.xiaomi.push.jf
    public void j() {
    }
}
