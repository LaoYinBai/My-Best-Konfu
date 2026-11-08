package c;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class w extends f {
    final transient byte[][] f;
    final transient int[] g;

    w(c cVar, int i) {
        super(null);
        ab.a(cVar.f2132c, 0L, i);
        int i2 = 0;
        u uVar = cVar.f2131b;
        int i3 = 0;
        int i4 = 0;
        while (i3 < i) {
            if (uVar.e == uVar.d) {
                throw new AssertionError("s.limit == s.pos");
            }
            i3 += uVar.e - uVar.d;
            i4++;
            uVar = uVar.h;
        }
        this.f = new byte[i4][];
        this.g = new int[i4 * 2];
        u uVar2 = cVar.f2131b;
        int i5 = 0;
        while (i2 < i) {
            this.f[i5] = uVar2.f2184c;
            i2 += uVar2.e - uVar2.d;
            if (i2 > i) {
                i2 = i;
            }
            this.g[i5] = i2;
            this.g[this.f.length + i5] = uVar2.d;
            uVar2.f = true;
            i5++;
            uVar2 = uVar2.h;
        }
    }

    @Override // c.f
    public String a() {
        return o().a();
    }

    @Override // c.f
    public String a(Charset charset) {
        return o().a(charset);
    }

    @Override // c.f
    public String b() {
        return o().b();
    }

    @Override // c.f
    public String h() {
        return o().h();
    }

    @Override // c.f
    public f i() {
        return o().i();
    }

    @Override // c.f
    public f j() {
        return o().j();
    }

    @Override // c.f
    public f c() {
        return o().c();
    }

    @Override // c.f
    public f d() {
        return o().d();
    }

    @Override // c.f
    public f e() {
        return o().e();
    }

    @Override // c.f
    public f a(f fVar) {
        return o().a(fVar);
    }

    @Override // c.f
    public f b(f fVar) {
        return o().b(fVar);
    }

    @Override // c.f
    public String g() {
        return o().g();
    }

    @Override // c.f
    public f a(int i) {
        return o().a(i);
    }

    @Override // c.f
    public f a(int i, int i2) {
        return o().a(i, i2);
    }

    @Override // c.f
    public byte b(int i) {
        ab.a(this.g[this.f.length - 1], i, 1L);
        int iC = c(i);
        return this.f[iC][(i - (iC == 0 ? 0 : this.g[iC - 1])) + this.g[this.f.length + iC]];
    }

    private int c(int i) {
        int iBinarySearch = Arrays.binarySearch(this.g, 0, this.f.length, i + 1);
        return iBinarySearch >= 0 ? iBinarySearch : ~iBinarySearch;
    }

    @Override // c.f
    public int k() {
        return this.g[this.f.length - 1];
    }

    @Override // c.f
    public byte[] l() {
        byte[] bArr = new byte[this.g[this.f.length - 1]];
        int length = this.f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            System.arraycopy(this.f[i], i3, bArr, i2, i4 - i2);
            i++;
            i2 = i4;
        }
        return bArr;
    }

    @Override // c.f
    public ByteBuffer n() {
        return ByteBuffer.wrap(l()).asReadOnlyBuffer();
    }

    @Override // c.f
    public void a(OutputStream outputStream) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        int length = this.f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            outputStream.write(this.f[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    @Override // c.f
    void a(c cVar) {
        int length = this.f.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = this.g[length + i];
            int i4 = this.g[i];
            u uVar = new u(this.f[i], i3, (i3 + i4) - i2, true, false);
            if (cVar.f2131b == null) {
                uVar.i = uVar;
                uVar.h = uVar;
                cVar.f2131b = uVar;
            } else {
                cVar.f2131b.i.a(uVar);
            }
            i++;
            i2 = i4;
        }
        cVar.f2132c += i2;
    }

    @Override // c.f
    public boolean a(int i, f fVar, int i2, int i3) {
        if (i < 0 || i > k() - i3) {
            return false;
        }
        int iC = c(i);
        while (i3 > 0) {
            int i4 = iC == 0 ? 0 : this.g[iC - 1];
            int iMin = Math.min(i3, ((this.g[iC] - i4) + i4) - i);
            if (!fVar.a(i2, this.f[iC], (i - i4) + this.g[this.f.length + iC], iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iC++;
        }
        return true;
    }

    @Override // c.f
    public boolean a(int i, byte[] bArr, int i2, int i3) {
        if (i < 0 || i > k() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int iC = c(i);
        while (i3 > 0) {
            int i4 = iC == 0 ? 0 : this.g[iC - 1];
            int iMin = Math.min(i3, ((this.g[iC] - i4) + i4) - i);
            if (!ab.a(this.f[iC], (i - i4) + this.g[this.f.length + iC], bArr, i2, iMin)) {
                return false;
            }
            i += iMin;
            i2 += iMin;
            i3 -= iMin;
            iC++;
        }
        return true;
    }

    @Override // c.f
    public int a(byte[] bArr, int i) {
        return o().a(bArr, i);
    }

    @Override // c.f
    public int b(byte[] bArr, int i) {
        return o().b(bArr, i);
    }

    private f o() {
        return new f(l());
    }

    @Override // c.f
    byte[] m() {
        return l();
    }

    @Override // c.f
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof f) {
            f fVar = (f) obj;
            if (fVar.k() == k() && a(0, fVar, 0, k())) {
                return true;
            }
        }
        return false;
    }

    @Override // c.f
    public int hashCode() {
        int i = this.d;
        if (i != 0) {
            return i;
        }
        int length = this.f.length;
        int i2 = 0;
        int i3 = 0;
        int i4 = 1;
        while (i2 < length) {
            byte[] bArr = this.f[i2];
            int i5 = this.g[length + i2];
            int i6 = this.g[i2];
            int i7 = (i6 - i3) + i5;
            while (i5 < i7) {
                i4 = (i4 * 31) + bArr[i5];
                i5++;
            }
            i2++;
            i3 = i6;
        }
        this.d = i4;
        return i4;
    }

    @Override // c.f
    public String toString() {
        return o().toString();
    }

    private Object writeReplace() {
        return o();
    }
}
