package com.umeng.commonsdk.proguard;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class bf extends bg {
    private byte[] a;
    private int b;
    private int c;

    @Override // com.umeng.commonsdk.proguard.bg
    public boolean a() {
        return true;
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public void b() throws bh {
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public void c() {
    }

    public bf() {
    }

    public bf(byte[] bArr) {
        a(bArr);
    }

    public bf(byte[] bArr, int i, int i2) {
        c(bArr, i, i2);
    }

    public void a(byte[] bArr) {
        c(bArr, 0, bArr.length);
    }

    public void c(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i + i2;
    }

    public void e() {
        this.a = null;
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public int a(byte[] bArr, int i, int i2) throws bh {
        int iH = h();
        if (i2 > iH) {
            i2 = iH;
        }
        if (i2 > 0) {
            System.arraycopy(this.a, this.b, bArr, i, i2);
            a(i2);
        }
        return i2;
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public void b(byte[] bArr, int i, int i2) throws bh {
        throw new UnsupportedOperationException("No writing allowed!");
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public byte[] f() {
        return this.a;
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public int g() {
        return this.b;
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public int h() {
        return this.c - this.b;
    }

    @Override // com.umeng.commonsdk.proguard.bg
    public void a(int i) {
        this.b += i;
    }
}
