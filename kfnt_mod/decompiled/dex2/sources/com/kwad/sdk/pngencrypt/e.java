package com.kwad.sdk.pngencrypt;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    final k a;
    int b;
    int c;
    int d;
    int e;
    int f;
    int g;
    private int j;
    private int k;
    private boolean o;
    private int i = 0;
    private int l = -1;
    private int m = -1;
    private int n = 0;
    int h = 0;

    public e(k kVar) {
        this.o = false;
        this.a = kVar;
        this.o = false;
        a(1);
        c(0);
    }

    static byte[] b(int i) {
        switch (i) {
            case 1:
                return new byte[]{8, 8, 0, 0};
            case 2:
                return new byte[]{8, 8, 4, 0};
            case 3:
                return new byte[]{4, 8, 0, 4};
            case 4:
                return new byte[]{4, 4, 2, 0};
            case 5:
                return new byte[]{2, 4, 0, 2};
            case 6:
                return new byte[]{2, 2, 1, 0};
            case 7:
                return new byte[]{1, 2, 0, 1};
            default:
                throw new PngjException("bad interlace pass" + i);
        }
    }

    private void c(int i) {
        this.l = i;
        this.m = (i * this.b) + this.d;
        if (this.m < 0 || this.m >= this.a.b) {
            throw new PngjException("bad row - this should not happen");
        }
    }

    void a(int i) {
        if (this.i == i) {
            return;
        }
        this.i = i;
        byte[] bArrB = b(i);
        this.c = bArrB[0];
        this.b = bArrB[1];
        this.e = bArrB[2];
        this.d = bArrB[3];
        this.j = this.a.b > this.d ? (((this.a.b + this.b) - 1) - this.d) / this.b : 0;
        this.k = this.a.a > this.e ? (((this.a.a + this.c) - 1) - this.e) / this.c : 0;
        if (this.k == 0) {
            this.j = 0;
        }
        this.g = this.c * this.a.d;
        this.f = this.e * this.a.d;
    }

    boolean a() {
        this.n++;
        if (this.j != 0 && this.l < this.j - 1) {
            c(this.l + 1);
        } else {
            if (this.i == 7) {
                this.o = true;
                return false;
            }
            a(this.i + 1);
            if (this.j == 0) {
                this.n--;
                return a();
            }
            c(0);
        }
        return true;
    }

    int b() {
        return this.l;
    }

    int c() {
        return this.m;
    }

    int d() {
        return this.i;
    }

    int e() {
        return this.j;
    }

    int f() {
        return this.k;
    }

    public int g() {
        return f();
    }

    public int h() {
        return ((this.a.i * g()) + 7) / 8;
    }
}
