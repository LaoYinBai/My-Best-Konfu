package com.kwad.sdk.pngencrypt;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class p {
    public final k a;
    public final e b;
    public final boolean c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;
    int k;
    int l;
    int m;
    int n;
    byte[] o;
    int p;

    public p(k kVar, e eVar) {
        this.a = kVar;
        this.b = eVar;
        this.c = eVar != null;
    }

    void a(int i) {
        int i2;
        this.h = i;
        if (this.c) {
            this.n = this.b.d();
            this.e = this.b.c;
            this.d = this.b.b;
            this.g = this.b.e;
            this.f = this.b.d;
            this.i = this.b.c();
            this.j = this.b.b();
            this.k = this.b.e();
            this.l = this.b.f();
            i2 = ((this.a.i * this.l) + 7) / 8;
        } else {
            this.n = 1;
            this.d = 1;
            this.e = 1;
            this.f = 0;
            this.g = 0;
            this.j = i;
            this.i = i;
            this.k = this.a.b;
            this.l = this.a.a;
            i2 = this.a.k;
        }
        this.m = i2;
    }

    void a(byte[] bArr, int i) {
        this.o = bArr;
        this.p = i;
    }
}
