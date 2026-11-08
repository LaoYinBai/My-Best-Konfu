package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class o {

    /* renamed from: a, reason: collision with root package name */
    final byte[] f1487a;

    /* renamed from: b, reason: collision with root package name */
    int f1488b;

    /* renamed from: c, reason: collision with root package name */
    int f1489c;
    boolean d;
    boolean e;
    o f;
    o g;

    o() {
        this.f1487a = new byte[8192];
        this.e = true;
        this.d = false;
    }

    public final o a(o oVar) {
        oVar.g = this;
        oVar.f = this.f;
        this.f.g = oVar;
        this.f = oVar;
        return oVar;
    }

    public final o b() {
        o oVar = this.f;
        if (oVar == this) {
            oVar = null;
        }
        o oVar2 = this.g;
        oVar2.f = this.f;
        this.f.g = oVar2;
        this.f = null;
        this.g = null;
        return oVar;
    }

    final o c() {
        this.d = true;
        return new o(this.f1487a, this.f1488b, this.f1489c, true, false);
    }

    o(byte[] bArr, int i, int i2, boolean z, boolean z2) {
        this.f1487a = bArr;
        this.f1488b = i;
        this.f1489c = i2;
        this.d = z;
        this.e = z2;
    }

    public final o a(int i) {
        o oVarA;
        if (i > 0 && i <= this.f1489c - this.f1488b) {
            if (i >= 1024) {
                oVarA = c();
            } else {
                oVarA = p.a();
                System.arraycopy(this.f1487a, this.f1488b, oVarA.f1487a, 0, i);
            }
            oVarA.f1489c = oVarA.f1488b + i;
            this.f1488b += i;
            this.g.a(oVarA);
            return oVarA;
        }
        throw new IllegalArgumentException();
    }

    public final void a() {
        o oVar = this.g;
        if (oVar != this) {
            if (oVar.e) {
                int i = this.f1489c - this.f1488b;
                if (i > (8192 - oVar.f1489c) + (oVar.d ? 0 : oVar.f1488b)) {
                    return;
                }
                a(this.g, i);
                b();
                p.a(this);
                return;
            }
            return;
        }
        throw new IllegalStateException();
    }

    public final void a(o oVar, int i) {
        if (oVar.e) {
            int i2 = oVar.f1489c;
            int i3 = i2 + i;
            if (i3 > 8192) {
                if (!oVar.d) {
                    int i4 = oVar.f1488b;
                    if (i3 - i4 <= 8192) {
                        byte[] bArr = oVar.f1487a;
                        System.arraycopy(bArr, i4, bArr, 0, i2 - i4);
                        oVar.f1489c -= oVar.f1488b;
                        oVar.f1488b = 0;
                    } else {
                        throw new IllegalArgumentException();
                    }
                } else {
                    throw new IllegalArgumentException();
                }
            }
            System.arraycopy(this.f1487a, this.f1488b, oVar.f1487a, oVar.f1489c, i);
            oVar.f1489c += i;
            this.f1488b += i;
            return;
        }
        throw new IllegalArgumentException();
    }
}
