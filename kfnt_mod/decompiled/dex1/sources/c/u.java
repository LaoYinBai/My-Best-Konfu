package c;

import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class u {

    /* renamed from: a, reason: collision with root package name */
    static final int f2182a = 8192;

    /* renamed from: b, reason: collision with root package name */
    static final int f2183b = 1024;

    /* renamed from: c, reason: collision with root package name */
    final byte[] f2184c;
    int d;
    int e;
    boolean f;
    boolean g;
    u h;
    u i;

    u() {
        this.f2184c = new byte[8192];
        this.g = true;
        this.f = false;
    }

    u(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f2184c = bArr;
        this.d = i;
        this.e = i2;
        this.f = z;
        this.g = z2;
    }

    u a() {
        this.f = true;
        return new u(this.f2184c, this.d, this.e, true, false);
    }

    u b() {
        return new u((byte[]) this.f2184c.clone(), this.d, this.e, false, true);
    }

    @Nullable
    public u c() {
        u uVar = this.h != this ? this.h : null;
        this.i.h = this.h;
        this.h.i = this.i;
        this.h = null;
        this.i = null;
        return uVar;
    }

    public u a(u uVar) {
        uVar.i = this;
        uVar.h = this.h;
        this.h.i = uVar;
        this.h = uVar;
        return uVar;
    }

    public u a(int i) {
        u uVarA;
        if (i <= 0 || i > this.e - this.d) {
            throw new IllegalArgumentException();
        }
        if (i >= 1024) {
            uVarA = a();
        } else {
            uVarA = v.a();
            System.arraycopy(this.f2184c, this.d, uVarA.f2184c, 0, i);
        }
        uVarA.e = uVarA.d + i;
        this.d += i;
        this.i.a(uVarA);
        return uVarA;
    }

    public void d() {
        if (this.i == this) {
            throw new IllegalStateException();
        }
        if (this.i.g) {
            int i = this.e - this.d;
            if (i > (8192 - this.i.e) + (this.i.f ? 0 : this.i.d)) {
                return;
            }
            a(this.i, i);
            c();
            v.a(this);
        }
    }

    public void a(u uVar, int i) {
        if (!uVar.g) {
            throw new IllegalArgumentException();
        }
        if (uVar.e + i > 8192) {
            if (uVar.f) {
                throw new IllegalArgumentException();
            }
            if ((uVar.e + i) - uVar.d > 8192) {
                throw new IllegalArgumentException();
            }
            System.arraycopy(uVar.f2184c, uVar.d, uVar.f2184c, 0, uVar.e - uVar.d);
            uVar.e -= uVar.d;
            uVar.d = 0;
        }
        System.arraycopy(this.f2184c, this.d, uVar.f2184c, uVar.e, i);
        uVar.e += i;
        this.d += i;
    }
}
