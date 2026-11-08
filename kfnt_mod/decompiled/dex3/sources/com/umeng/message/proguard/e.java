package com.umeng.message.proguard;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class e {
    private static final int a = 2;
    public static final int b = 76;
    public static final int c = 64;
    protected static final int d = 255;
    protected static final byte e = 61;
    private static final int m = 8192;
    protected final byte f = e;
    protected final int g;
    protected byte[] h;
    protected int i;
    protected boolean j;
    protected int k;
    protected int l;
    private final int n;
    private final int o;
    private final int p;
    private int q;

    protected static boolean c(byte b2) {
        if (b2 == 13 || b2 == 32) {
            return true;
        }
        switch (b2) {
            case 9:
            case 10:
                return true;
            default:
                return false;
        }
    }

    abstract void a(byte[] bArr, int i, int i2);

    abstract void b(byte[] bArr, int i, int i2);

    protected abstract boolean b(byte b2);

    protected int d() {
        return 8192;
    }

    protected e(int i, int i2, int i3, int i4) {
        this.n = i;
        this.o = i2;
        this.g = (i3 <= 0 || i4 <= 0) ? 0 : (i3 / i2) * i2;
        this.p = i4;
    }

    boolean b() {
        return this.h != null;
    }

    int c() {
        if (this.h != null) {
            return this.i - this.q;
        }
        return 0;
    }

    private void a() {
        if (this.h == null) {
            this.h = new byte[d()];
            this.i = 0;
            this.q = 0;
        } else {
            byte[] bArr = new byte[this.h.length * 2];
            System.arraycopy(this.h, 0, bArr, 0, this.h.length);
            this.h = bArr;
        }
    }

    protected void a(int i) {
        if (this.h == null || this.h.length < this.i + i) {
            a();
        }
    }

    int c(byte[] bArr, int i, int i2) {
        if (this.h == null) {
            return this.j ? -1 : 0;
        }
        int iMin = Math.min(c(), i2);
        System.arraycopy(this.h, this.q, bArr, i, iMin);
        this.q += iMin;
        if (this.q >= this.i) {
            this.h = null;
        }
        return iMin;
    }

    private void e() {
        this.h = null;
        this.i = 0;
        this.q = 0;
        this.k = 0;
        this.l = 0;
        this.j = false;
    }

    public Object a(Object obj) throws Exception {
        if (!(obj instanceof byte[])) {
            throw new Exception("Parameter supplied to Base-N encode is not a byte[]");
        }
        return l((byte[]) obj);
    }

    public String j(byte[] bArr) {
        return b.f(l(bArr));
    }

    public Object b(Object obj) throws Exception {
        if (obj instanceof byte[]) {
            return k((byte[]) obj);
        }
        if (obj instanceof String) {
            return c((String) obj);
        }
        throw new Exception("Parameter supplied to Base-N decode is not a byte[] or a String");
    }

    public byte[] c(String str) {
        return k(b.f(str));
    }

    public byte[] k(byte[] bArr) {
        e();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        b(bArr, 0, bArr.length);
        b(bArr, 0, -1);
        byte[] bArr2 = new byte[this.i];
        c(bArr2, 0, bArr2.length);
        return bArr2;
    }

    public byte[] l(byte[] bArr) {
        e();
        if (bArr == null || bArr.length == 0) {
            return bArr;
        }
        a(bArr, 0, bArr.length);
        a(bArr, 0, -1);
        byte[] bArr2 = new byte[this.i - this.q];
        c(bArr2, 0, bArr2.length);
        return bArr2;
    }

    public String m(byte[] bArr) {
        return b.f(l(bArr));
    }

    public boolean b(byte[] bArr, boolean z) {
        for (int i = 0; i < bArr.length; i++) {
            if (!b(bArr[i]) && (!z || (bArr[i] != 61 && !c(bArr[i])))) {
                return false;
            }
        }
        return true;
    }

    public boolean d(String str) {
        return b(b.f(str), true);
    }

    protected boolean n(byte[] bArr) {
        if (bArr == null) {
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (61 == bArr[i] || b(bArr[i])) {
                return true;
            }
        }
        return false;
    }

    public long o(byte[] bArr) {
        long length = (((bArr.length + this.n) - 1) / this.n) * this.o;
        return this.g > 0 ? length + ((((this.g + length) - 1) / this.g) * this.p) : length;
    }
}
