package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import android.support.v4.media.session.PlaybackStateCompat;
import com.bytedance.common.utility.UIUtils;
import com.facebook.stetho.dumpapp.Framer;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c implements d, e, Cloneable, ByteChannel {

    /* renamed from: c, reason: collision with root package name */
    private static final byte[] f1462c = {48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: a, reason: collision with root package name */
    o f1463a;

    /* renamed from: b, reason: collision with root package name */
    long f1464b;

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public c a() {
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public f b(long j) throws EOFException {
        return new f(d(j));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public short c() {
        return u.a(readShort());
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public String d() throws EOFException {
        return c(Long.MAX_VALUE);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        long j = this.f1464b;
        if (j != cVar.f1464b) {
            return false;
        }
        long j2 = 0;
        if (j == 0) {
            return true;
        }
        o oVar = this.f1463a;
        o oVar2 = cVar.f1463a;
        int i = oVar.f1488b;
        int i2 = oVar2.f1488b;
        while (j2 < this.f1464b) {
            long jMin = Math.min(oVar.f1489c - i, oVar2.f1489c - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (i5 < jMin) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (oVar.f1487a[i4] != oVar2.f1487a[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == oVar.f1489c) {
                oVar = oVar.f;
                i = oVar.f1488b;
            } else {
                i = i4;
            }
            if (i3 == oVar2.f1489c) {
                oVar2 = oVar2.f;
                i2 = oVar2.f1488b;
            } else {
                i2 = i3;
            }
            j2 += jMin;
        }
        return true;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public boolean f() {
        return this.f1464b == 0;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Flushable
    public void flush() {
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x009d  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00a5 A[EDGE_INSN: B:44:0x00a5->B:38:0x00a5 BREAK  A[LOOP:0: B:5:0x000b->B:46:?], SYNTHETIC] */
    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long g() {
        /*
            r14 = this;
            long r0 = r14.f1464b
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lac
            r0 = 0
            r4 = r2
            r1 = 0
        Lb:
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.o r6 = r14.f1463a
            byte[] r7 = r6.f1487a
            int r8 = r6.f1488b
            int r9 = r6.f1489c
        L13:
            if (r8 >= r9) goto L91
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L22
            r11 = 57
            if (r10 > r11) goto L22
            int r11 = r10 + (-48)
            goto L3a
        L22:
            r11 = 97
            if (r10 < r11) goto L2f
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L2f
            int r11 = r10 + (-97)
        L2c:
            int r11 = r11 + 10
            goto L3a
        L2f:
            r11 = 65
            if (r10 < r11) goto L72
            r11 = 70
            if (r10 > r11) goto L72
            int r11 = r10 + (-65)
            goto L2c
        L3a:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L4a
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L13
        L4a:
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c r0 = new bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c
            r0.<init>()
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c r0 = r0.e(r4)
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c r0 = r0.writeByte(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Number too large: "
            r2.append(r3)
            java.lang.String r0 = r0.n()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L72:
            if (r0 == 0) goto L76
            r1 = 1
            goto L91
        L76:
            java.lang.NumberFormatException r0 = new java.lang.NumberFormatException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "Expected leading [0-9a-fA-F] character but was 0x"
            r1.append(r2)
            java.lang.String r2 = java.lang.Integer.toHexString(r10)
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L91:
            if (r8 != r9) goto L9d
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.o r7 = r6.b()
            r14.f1463a = r7
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.p.a(r6)
            goto L9f
        L9d:
            r6.f1488b = r8
        L9f:
            if (r1 != 0) goto La5
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.o r6 = r14.f1463a
            if (r6 != 0) goto Lb
        La5:
            long r1 = r14.f1464b
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.f1464b = r1
            return r4
        Lac:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c.g():long");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public c h() {
        return this;
    }

    public int hashCode() {
        o oVar = this.f1463a;
        if (oVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = oVar.f1489c;
            for (int i3 = oVar.f1488b; i3 < i2; i3++) {
                i = (i * 31) + oVar.f1487a[i3];
            }
            oVar = oVar.f;
        } while (oVar != this.f1463a);
        return i;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public final void j() {
        try {
            skip(this.f1464b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final long k() {
        long j = this.f1464b;
        if (j == 0) {
            return 0L;
        }
        o oVar = this.f1463a.g;
        return (oVar.f1489c >= 8192 || !oVar.e) ? j : j - (r3 - oVar.f1488b);
    }

    public byte[] l() {
        try {
            return d(this.f1464b);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public f m() {
        return new f(l());
    }

    public String n() {
        try {
            return a(this.f1464b, u.f1495a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public final long o() {
        return this.f1464b;
    }

    public final f p() {
        long j = this.f1464b;
        if (j <= 2147483647L) {
            return a((int) j);
        }
        throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f1464b);
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        o oVar = this.f1463a;
        if (oVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), oVar.f1489c - oVar.f1488b);
        byteBuffer.put(oVar.f1487a, oVar.f1488b, iMin);
        oVar.f1488b += iMin;
        this.f1464b -= iMin;
        if (oVar.f1488b == oVar.f1489c) {
            this.f1463a = oVar.b();
            p.a(oVar);
        }
        return iMin;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public byte readByte() {
        long j = this.f1464b;
        if (j == 0) {
            throw new IllegalStateException("size == 0");
        }
        o oVar = this.f1463a;
        int i = oVar.f1488b;
        int i2 = oVar.f1489c;
        int i3 = i + 1;
        byte b2 = oVar.f1487a[i];
        this.f1464b = j - 1;
        if (i3 == i2) {
            this.f1463a = oVar.b();
            p.a(oVar);
        } else {
            oVar.f1488b = i3;
        }
        return b2;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public void readFully(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int iA = a(bArr, i, bArr.length - i);
            if (iA == -1) {
                throw new EOFException();
            }
            i += iA;
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public int readInt() {
        long j = this.f1464b;
        if (j < 4) {
            throw new IllegalStateException("size < 4: " + this.f1464b);
        }
        o oVar = this.f1463a;
        int i = oVar.f1488b;
        int i2 = oVar.f1489c;
        if (i2 - i < 4) {
            return ((readByte() & 255) << 24) | ((readByte() & 255) << 16) | ((readByte() & 255) << 8) | (readByte() & 255);
        }
        byte[] bArr = oVar.f1487a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.f1464b = j - 4;
        if (i8 == i2) {
            this.f1463a = oVar.b();
            p.a(oVar);
        } else {
            oVar.f1488b = i8;
        }
        return i9;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public short readShort() {
        long j = this.f1464b;
        if (j < 2) {
            throw new IllegalStateException("size < 2: " + this.f1464b);
        }
        o oVar = this.f1463a;
        int i = oVar.f1488b;
        int i2 = oVar.f1489c;
        if (i2 - i < 2) {
            return (short) (((readByte() & 255) << 8) | (readByte() & 255));
        }
        byte[] bArr = oVar.f1487a;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.f1464b = j - 2;
        if (i4 == i2) {
            this.f1463a = oVar.b();
            p.a(oVar);
        } else {
            oVar.f1488b = i4;
        }
        return (short) i5;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public void skip(long j) throws EOFException {
        while (j > 0) {
            if (this.f1463a == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, r0.f1489c - r0.f1488b);
            long j2 = iMin;
            this.f1464b -= j2;
            j -= j2;
            o oVar = this.f1463a;
            oVar.f1488b += iMin;
            if (oVar.f1488b == oVar.f1489c) {
                this.f1463a = oVar.b();
                p.a(oVar);
            }
        }
    }

    public String toString() {
        return p().toString();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public void a(long j) throws EOFException {
        if (this.f1464b < j) {
            throw new EOFException();
        }
    }

    o b(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        o oVar = this.f1463a;
        if (oVar != null) {
            o oVar2 = oVar.g;
            return (oVar2.f1489c + i > 8192 || !oVar2.e) ? oVar2.a(p.a()) : oVar2;
        }
        this.f1463a = p.a();
        o oVar3 = this.f1463a;
        oVar3.g = oVar3;
        oVar3.f = oVar3;
        return oVar3;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public String c(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        long jA = a((byte) 10, 0L, j2);
        if (jA != -1) {
            return h(jA);
        }
        if (j2 < o() && f(j2 - 1) == 13 && f(j2) == 10) {
            return h(j2);
        }
        c cVar = new c();
        a(cVar, 0L, Math.min(32L, o()));
        throw new EOFException("\\n not found: limit=" + Math.min(o(), j) + " content=" + cVar.m().b() + UIUtils.ELLIPSIS_CHAR);
    }

    /* renamed from: clone, reason: merged with bridge method [inline-methods] */
    public c m4clone() {
        c cVar = new c();
        if (this.f1464b == 0) {
            return cVar;
        }
        cVar.f1463a = this.f1463a.c();
        o oVar = cVar.f1463a;
        oVar.g = oVar;
        oVar.f = oVar;
        for (o oVar2 = this.f1463a.f; oVar2 != this.f1463a; oVar2 = oVar2.f) {
            cVar.f1463a.g.a(oVar2.c());
        }
        cVar.f1464b = this.f1464b;
        return cVar;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public byte[] d(long j) throws EOFException {
        u.a(this.f1464b, 0L, j);
        if (j <= 2147483647L) {
            byte[] bArr = new byte[(int) j];
            readFully(bArr);
            return bArr;
        }
        throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public int e() {
        return u.a(readInt());
    }

    public final byte f(long j) {
        u.a(this.f1464b, j, 1L);
        long j2 = this.f1464b;
        if (j2 - j > j) {
            o oVar = this.f1463a;
            while (true) {
                int i = oVar.f1489c;
                int i2 = oVar.f1488b;
                long j3 = i - i2;
                if (j < j3) {
                    return oVar.f1487a[i2 + ((int) j)];
                }
                j -= j3;
                oVar = oVar.f;
            }
        } else {
            long j4 = j - j2;
            o oVar2 = this.f1463a.g;
            while (true) {
                int i3 = oVar2.f1489c;
                int i4 = oVar2.f1488b;
                j4 += i3 - i4;
                if (j4 >= 0) {
                    return oVar2.f1487a[i4 + ((int) j4)];
                }
                oVar2 = oVar2.g;
            }
        }
    }

    String h(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (f(j2) == 13) {
                String strG = g(j2);
                skip(2L);
                return strG;
            }
        }
        String strG2 = g(j);
        skip(1L);
        return strG2;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public c writeByte(int i) {
        o oVarB = b(1);
        byte[] bArr = oVarB.f1487a;
        int i2 = oVarB.f1489c;
        oVarB.f1489c = i2 + 1;
        bArr[i2] = (byte) i;
        this.f1464b++;
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public c writeInt(int i) {
        o oVarB = b(4);
        byte[] bArr = oVarB.f1487a;
        int i2 = oVarB.f1489c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        oVarB.f1489c = i5 + 1;
        this.f1464b += 4;
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public c writeShort(int i) {
        o oVarB = b(2);
        byte[] bArr = oVarB.f1487a;
        int i2 = oVarB.f1489c;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        oVarB.f1489c = i3 + 1;
        this.f1464b += 2;
        return this;
    }

    public final c a(c cVar, long j, long j2) {
        if (cVar != null) {
            u.a(this.f1464b, j, j2);
            if (j2 == 0) {
                return this;
            }
            cVar.f1464b += j2;
            o oVar = this.f1463a;
            while (true) {
                long j3 = oVar.f1489c - oVar.f1488b;
                if (j < j3) {
                    break;
                }
                j -= j3;
                oVar = oVar.f;
            }
            while (j2 > 0) {
                o oVarC = oVar.c();
                oVarC.f1488b = (int) (oVarC.f1488b + j);
                oVarC.f1489c = Math.min(oVarC.f1488b + ((int) j2), oVarC.f1489c);
                o oVar2 = cVar.f1463a;
                if (oVar2 == null) {
                    oVarC.g = oVarC;
                    oVarC.f = oVarC;
                    cVar.f1463a = oVarC;
                } else {
                    oVar2.g.a(oVarC);
                }
                j2 -= oVarC.f1489c - oVarC.f1488b;
                oVar = oVar.f;
                j = 0;
            }
            return this;
        }
        throw new IllegalArgumentException("out == null");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public c e(long j) {
        if (j == 0) {
            return writeByte(48);
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        o oVarB = b(iNumberOfTrailingZeros);
        byte[] bArr = oVarB.f1487a;
        int i = oVarB.f1489c;
        for (int i2 = (i + iNumberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = f1462c[(int) (15 & j)];
            j >>>= 4;
        }
        oVarB.f1489c += iNumberOfTrailingZeros;
        this.f1464b += iNumberOfTrailingZeros;
        return this;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public c write(byte[] bArr) {
        if (bArr != null) {
            return write(bArr, 0, bArr.length);
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public c write(byte[] bArr, int i, int i2) {
        if (bArr != null) {
            long j = i2;
            u.a(bArr.length, i, j);
            int i3 = i2 + i;
            while (i < i3) {
                o oVarB = b(1);
                int iMin = Math.min(i3 - i, 8192 - oVarB.f1489c);
                System.arraycopy(bArr, i, oVarB.f1487a, oVarB.f1489c, iMin);
                i += iMin;
                oVarB.f1489c += iMin;
            }
            this.f1464b += j;
            return this;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
    public void b(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (cVar != this) {
            u.a(cVar.f1464b, 0L, j);
            while (j > 0) {
                o oVar = cVar.f1463a;
                if (j < oVar.f1489c - oVar.f1488b) {
                    o oVar2 = this.f1463a;
                    o oVar3 = oVar2 != null ? oVar2.g : null;
                    if (oVar3 != null && oVar3.e) {
                        if ((oVar3.f1489c + j) - (oVar3.d ? 0 : oVar3.f1488b) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                            cVar.f1463a.a(oVar3, (int) j);
                            cVar.f1464b -= j;
                            this.f1464b += j;
                            return;
                        }
                    }
                    cVar.f1463a = cVar.f1463a.a((int) j);
                }
                o oVar4 = cVar.f1463a;
                long j2 = oVar4.f1489c - oVar4.f1488b;
                cVar.f1463a = oVar4.b();
                o oVar5 = this.f1463a;
                if (oVar5 == null) {
                    this.f1463a = oVar4;
                    o oVar6 = this.f1463a;
                    oVar6.g = oVar6;
                    oVar6.f = oVar6;
                } else {
                    oVar5.g.a(oVar4).a();
                }
                cVar.f1464b -= j2;
                this.f1464b += j2;
                j -= j2;
            }
            return;
        }
        throw new IllegalArgumentException("source == this");
    }

    public c c(int i) {
        if (i < 128) {
            writeByte(i);
        } else if (i < 2048) {
            writeByte((i >> 6) | 192);
            writeByte((i & 63) | 128);
        } else if (i < 65536) {
            if (i >= 55296 && i <= 57343) {
                writeByte(63);
            } else {
                writeByte((i >> 12) | 224);
                writeByte(((i >> 6) & 63) | 128);
                writeByte((i & 63) | 128);
            }
        } else if (i <= 1114111) {
            writeByte((i >> 18) | 240);
            writeByte(((i >> 12) & 63) | 128);
            writeByte(((i >> 6) & 63) | 128);
            writeByte((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer != null) {
            int iRemaining = byteBuffer.remaining();
            int i = iRemaining;
            while (i > 0) {
                o oVarB = b(1);
                int iMin = Math.min(i, 8192 - oVarB.f1489c);
                byteBuffer.get(oVarB.f1487a, oVarB.f1489c, iMin);
                i -= iMin;
                oVarB.f1489c += iMin;
            }
            this.f1464b += iRemaining;
            return iRemaining;
        }
        throw new IllegalArgumentException("source == null");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public String a(Charset charset) {
        try {
            return a(this.f1464b, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    public String a(long j, Charset charset) throws EOFException {
        u.a(this.f1464b, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        o oVar = this.f1463a;
        int i = oVar.f1488b;
        if (i + j > oVar.f1489c) {
            return new String(d(j), charset);
        }
        String str = new String(oVar.f1487a, i, (int) j, charset);
        oVar.f1488b = (int) (oVar.f1488b + j);
        this.f1464b -= j;
        if (oVar.f1488b == oVar.f1489c) {
            this.f1463a = oVar.b();
            p.a(oVar);
        }
        return str;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
    public t b() {
        return t.d;
    }

    public int a(byte[] bArr, int i, int i2) {
        u.a(bArr.length, i, i2);
        o oVar = this.f1463a;
        if (oVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, oVar.f1489c - oVar.f1488b);
        System.arraycopy(oVar.f1487a, oVar.f1488b, bArr, i, iMin);
        oVar.f1488b += iMin;
        this.f1464b -= iMin;
        if (oVar.f1488b == oVar.f1489c) {
            this.f1463a = oVar.b();
            p.a(oVar);
        }
        return iMin;
    }

    public c a(f fVar) {
        if (fVar != null) {
            fVar.a(this);
            return this;
        }
        throw new IllegalArgumentException("byteString == null");
    }

    public String g(long j) throws EOFException {
        return a(j, u.f1495a);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public c a(String str) {
        return a(str, 0, str.length());
    }

    public c a(String str, int i, int i2) {
        char cCharAt;
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        }
        if (i2 >= i) {
            if (i2 > str.length()) {
                throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
            }
            while (i < i2) {
                char cCharAt2 = str.charAt(i);
                if (cCharAt2 < 128) {
                    o oVarB = b(1);
                    byte[] bArr = oVarB.f1487a;
                    int i3 = oVarB.f1489c - i;
                    int iMin = Math.min(i2, 8192 - i3);
                    int i4 = i + 1;
                    bArr[i + i3] = (byte) cCharAt2;
                    while (true) {
                        i = i4;
                        if (i >= iMin || (cCharAt = str.charAt(i)) >= 128) {
                            break;
                        }
                        i4 = i + 1;
                        bArr[i + i3] = (byte) cCharAt;
                    }
                    int i5 = oVarB.f1489c;
                    int i6 = (i3 + i) - i5;
                    oVarB.f1489c = i5 + i6;
                    this.f1464b += i6;
                } else if (cCharAt2 < 2048) {
                    writeByte((cCharAt2 >> 6) | 192);
                    writeByte((cCharAt2 & '?') | 128);
                    i++;
                } else if (cCharAt2 >= 55296 && cCharAt2 <= 57343) {
                    int i7 = i + 1;
                    char cCharAt3 = i7 < i2 ? str.charAt(i7) : (char) 0;
                    if (cCharAt2 <= 56319 && cCharAt3 >= 56320 && cCharAt3 <= 57343) {
                        int i8 = (((cCharAt2 & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                        writeByte((i8 >> 18) | 240);
                        writeByte(((i8 >> 12) & 63) | 128);
                        writeByte(((i8 >> 6) & 63) | 128);
                        writeByte((i8 & 63) | 128);
                        i += 2;
                    } else {
                        writeByte(63);
                        i = i7;
                    }
                } else {
                    writeByte((cCharAt2 >> '\f') | 224);
                    writeByte(((cCharAt2 >> 6) & 63) | 128);
                    writeByte((cCharAt2 & '?') | 128);
                    i++;
                }
            }
            return this;
        }
        throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
    }

    public c a(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        }
        if (i2 >= i) {
            if (i2 <= str.length()) {
                if (charset != null) {
                    if (charset.equals(u.f1495a)) {
                        return a(str, i, i2);
                    }
                    byte[] bytes = str.substring(i, i2).getBytes(charset);
                    return write(bytes, 0, bytes.length);
                }
                throw new IllegalArgumentException("charset == null");
            }
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
    }

    public long a(s sVar) throws IOException {
        if (sVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jA = sVar.a(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (jA == -1) {
                return j;
            }
            j += jA;
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
    public long a(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j >= 0) {
            long j2 = this.f1464b;
            if (j2 == 0) {
                return -1L;
            }
            if (j > j2) {
                j = j2;
            }
            cVar.b(this, j);
            return j;
        }
        throw new IllegalArgumentException("byteCount < 0: " + j);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public long a(byte b2) {
        return a(b2, 0L, Long.MAX_VALUE);
    }

    public long a(byte b2, long j, long j2) {
        o oVar;
        long j3 = 0;
        if (j >= 0 && j2 >= j) {
            long j4 = this.f1464b;
            long j5 = j2 > j4 ? j4 : j2;
            if (j == j5 || (oVar = this.f1463a) == null) {
                return -1L;
            }
            long j6 = this.f1464b;
            if (j6 - j >= j) {
                while (true) {
                    j6 = j3;
                    j3 = (oVar.f1489c - oVar.f1488b) + j6;
                    if (j3 >= j) {
                        break;
                    }
                    oVar = oVar.f;
                }
            } else {
                while (j6 > j) {
                    oVar = oVar.g;
                    j6 -= oVar.f1489c - oVar.f1488b;
                }
            }
            long j7 = j;
            while (j6 < j5) {
                byte[] bArr = oVar.f1487a;
                int iMin = (int) Math.min(oVar.f1489c, (oVar.f1488b + j5) - j6);
                for (int i = (int) ((oVar.f1488b + j7) - j6); i < iMin; i++) {
                    if (bArr[i] == b2) {
                        return (i - oVar.f1488b) + j6;
                    }
                }
                j7 = (oVar.f1489c - oVar.f1488b) + j6;
                oVar = oVar.f;
                j6 = j7;
            }
            return -1L;
        }
        throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", Long.valueOf(this.f1464b), Long.valueOf(j), Long.valueOf(j2)));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public boolean a(long j, f fVar) {
        return a(j, fVar, 0, fVar.e());
    }

    public boolean a(long j, f fVar, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.f1464b - j < i2 || fVar.e() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (f(i3 + j) != fVar.a(i + i3)) {
                return false;
            }
        }
        return true;
    }

    public final f a(int i) {
        if (i == 0) {
            return f.e;
        }
        return new q(this, i);
    }
}
