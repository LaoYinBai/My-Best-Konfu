package com.meizu.cloud.pushsdk.b.g;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
final class j {
    final byte[] a;
    int b;
    int c;
    boolean d;
    final boolean e;
    j f;
    j g;

    j() {
        this.a = new byte[2048];
        this.e = true;
        this.d = false;
    }

    j(j jVar) {
        this(jVar.a, jVar.b, jVar.c);
    }

    j(byte[] bArr, int i, int i2) {
        this.a = bArr;
        this.b = i;
        this.c = i2;
        this.e = false;
        this.d = true;
    }

    public j a() {
        j jVar = this.f != this ? this.f : null;
        this.g.f = this.f;
        this.f.g = this.g;
        this.f = null;
        this.g = null;
        return jVar;
    }

    public j a(int i) {
        if (i <= 0 || i > this.c - this.b) {
            throw new IllegalArgumentException();
        }
        j jVar = new j(this);
        jVar.c = jVar.b + i;
        this.b += i;
        this.g.a(jVar);
        return jVar;
    }

    public j a(j jVar) {
        jVar.g = this;
        jVar.f = this.f;
        this.f.g = jVar;
        this.f = jVar;
        return jVar;
    }

    public void a(j jVar, int i) {
        if (!jVar.e) {
            throw new IllegalArgumentException();
        }
        if (jVar.c + i > 2048) {
            if (jVar.d) {
                throw new IllegalArgumentException();
            }
            if ((jVar.c + i) - jVar.b > 2048) {
                throw new IllegalArgumentException();
            }
            System.arraycopy(jVar.a, jVar.b, jVar.a, 0, jVar.c - jVar.b);
            jVar.c -= jVar.b;
            jVar.b = 0;
        }
        System.arraycopy(this.a, this.b, jVar.a, jVar.c, i);
        jVar.c += i;
        this.b += i;
    }

    public void b() {
        if (this.g == this) {
            throw new IllegalStateException();
        }
        if (this.g.e) {
            int i = this.c - this.b;
            if (i > (2048 - this.g.c) + (this.g.d ? 0 : this.g.b)) {
                return;
            }
            a(this.g, i);
            a();
            k.a(this);
        }
    }
}
