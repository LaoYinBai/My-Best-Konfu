package c;

import android.support.v4.media.session.PlaybackStateCompat;
import com.bytedance.common.utility.StringEncryptUtils;
import com.bytedance.common.utility.UIUtils;
import com.facebook.stetho.dumpapp.Framer;
import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class c implements d, e, Cloneable, ByteChannel {

    /* renamed from: a, reason: collision with root package name */
    static final int f2130a = 65533;
    private static final byte[] d = {48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102};

    /* renamed from: b, reason: collision with root package name */
    @Nullable
    u f2131b;

    /* renamed from: c, reason: collision with root package name */
    long f2132c;

    @Override // c.d, c.e
    public c c() {
        return this;
    }

    @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    @Override // c.d
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public c I() {
        return this;
    }

    @Override // c.d
    public d f() {
        return this;
    }

    @Override // c.d, c.x, java.io.Flushable
    public void flush() {
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return true;
    }

    public long b() {
        return this.f2132c;
    }

    @Override // c.d
    public OutputStream d() {
        return new OutputStream() { // from class: c.c.1
            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() {
            }

            @Override // java.io.OutputStream
            public void write(int i) {
                c.this.m((int) ((byte) i));
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) {
                c.this.c(bArr, i, i2);
            }

            public String toString() {
                return c.this + ".outputStream()";
            }
        };
    }

    @Override // c.e
    public boolean g() {
        return this.f2132c == 0;
    }

    @Override // c.e
    public void a(long j) throws EOFException {
        if (this.f2132c < j) {
            throw new EOFException();
        }
    }

    @Override // c.e
    public boolean b(long j) {
        return this.f2132c >= j;
    }

    @Override // c.e
    public InputStream h() {
        return new InputStream() { // from class: c.c.2
            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() {
            }

            @Override // java.io.InputStream
            public int read() {
                if (c.this.f2132c > 0) {
                    return c.this.j() & 255;
                }
                return -1;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) {
                return c.this.a(bArr, i, i2);
            }

            @Override // java.io.InputStream
            public int available() {
                return (int) Math.min(c.this.f2132c, 2147483647L);
            }

            public String toString() {
                return c.this + ".inputStream()";
            }
        };
    }

    public c a(OutputStream outputStream) throws IOException {
        return a(outputStream, 0L, this.f2132c);
    }

    public c a(OutputStream outputStream, long j, long j2) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        ab.a(this.f2132c, j, j2);
        if (j2 == 0) {
            return this;
        }
        u uVar = this.f2131b;
        while (j >= uVar.e - uVar.d) {
            j -= uVar.e - uVar.d;
            uVar = uVar.h;
        }
        while (j2 > 0) {
            int iMin = (int) Math.min(uVar.e - r8, j2);
            outputStream.write(uVar.f2184c, (int) (uVar.d + j), iMin);
            j2 -= iMin;
            uVar = uVar.h;
            j = 0;
        }
        return this;
    }

    public c a(c cVar, long j, long j2) {
        if (cVar == null) {
            throw new IllegalArgumentException("out == null");
        }
        ab.a(this.f2132c, j, j2);
        if (j2 == 0) {
            return this;
        }
        cVar.f2132c += j2;
        u uVar = this.f2131b;
        while (j >= uVar.e - uVar.d) {
            j -= uVar.e - uVar.d;
            uVar = uVar.h;
        }
        while (j2 > 0) {
            u uVarA = uVar.a();
            uVarA.d = (int) (uVarA.d + j);
            uVarA.e = Math.min(uVarA.d + ((int) j2), uVarA.e);
            if (cVar.f2131b == null) {
                uVarA.i = uVarA;
                uVarA.h = uVarA;
                cVar.f2131b = uVarA;
            } else {
                cVar.f2131b.i.a(uVarA);
            }
            j2 -= uVarA.e - uVarA.d;
            uVar = uVar.h;
            j = 0;
        }
        return this;
    }

    public c b(OutputStream outputStream) throws IOException {
        return a(outputStream, this.f2132c);
    }

    public c a(OutputStream outputStream, long j) throws IOException {
        if (outputStream == null) {
            throw new IllegalArgumentException("out == null");
        }
        ab.a(this.f2132c, 0L, j);
        u uVar = this.f2131b;
        while (j > 0) {
            int iMin = (int) Math.min(j, uVar.e - uVar.d);
            outputStream.write(uVar.f2184c, uVar.d, iMin);
            uVar.d += iMin;
            long j2 = iMin;
            this.f2132c -= j2;
            j -= j2;
            if (uVar.d == uVar.e) {
                u uVarC = uVar.c();
                this.f2131b = uVarC;
                v.a(uVar);
                uVar = uVarC;
            }
        }
        return this;
    }

    public c a(InputStream inputStream) throws IOException {
        a(inputStream, Long.MAX_VALUE, true);
        return this;
    }

    public c a(InputStream inputStream, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        a(inputStream, j, false);
        return this;
    }

    private void a(InputStream inputStream, long j, boolean z) throws IOException {
        if (inputStream == null) {
            throw new IllegalArgumentException("in == null");
        }
        while (true) {
            if (j <= 0 && !z) {
                return;
            }
            u uVarG = g(1);
            int i = inputStream.read(uVarG.f2184c, uVarG.e, (int) Math.min(j, 8192 - uVarG.e));
            if (i == -1) {
                if (!z) {
                    throw new EOFException();
                }
                return;
            } else {
                uVarG.e += i;
                long j2 = i;
                this.f2132c += j2;
                j -= j2;
            }
        }
    }

    public long i() {
        long j = this.f2132c;
        if (j == 0) {
            return 0L;
        }
        u uVar = this.f2131b.i;
        return (uVar.e >= 8192 || !uVar.g) ? j : j - (uVar.e - uVar.d);
    }

    @Override // c.e
    public byte j() {
        if (this.f2132c == 0) {
            throw new IllegalStateException("size == 0");
        }
        u uVar = this.f2131b;
        int i = uVar.d;
        int i2 = uVar.e;
        int i3 = i + 1;
        byte b2 = uVar.f2184c[i];
        this.f2132c--;
        if (i3 == i2) {
            this.f2131b = uVar.c();
            v.a(uVar);
        } else {
            uVar.d = i3;
        }
        return b2;
    }

    public byte c(long j) {
        ab.a(this.f2132c, j, 1L);
        if (this.f2132c - j > j) {
            u uVar = this.f2131b;
            while (true) {
                long j2 = uVar.e - uVar.d;
                if (j >= j2) {
                    j -= j2;
                    uVar = uVar.h;
                } else {
                    return uVar.f2184c[uVar.d + ((int) j)];
                }
            }
        } else {
            long j3 = j - this.f2132c;
            u uVar2 = this.f2131b;
            do {
                uVar2 = uVar2.i;
                j3 += uVar2.e - uVar2.d;
            } while (j3 < 0);
            return uVar2.f2184c[uVar2.d + ((int) j3)];
        }
    }

    @Override // c.e
    public short k() {
        if (this.f2132c < 2) {
            throw new IllegalStateException("size < 2: " + this.f2132c);
        }
        u uVar = this.f2131b;
        int i = uVar.d;
        int i2 = uVar.e;
        if (i2 - i < 2) {
            return (short) (((j() & 255) << 8) | (j() & 255));
        }
        byte[] bArr = uVar.f2184c;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 8) | (bArr[i3] & 255);
        this.f2132c -= 2;
        if (i4 == i2) {
            this.f2131b = uVar.c();
            v.a(uVar);
        } else {
            uVar.d = i4;
        }
        return (short) i5;
    }

    @Override // c.e
    public int l() {
        if (this.f2132c < 4) {
            throw new IllegalStateException("size < 4: " + this.f2132c);
        }
        u uVar = this.f2131b;
        int i = uVar.d;
        int i2 = uVar.e;
        if (i2 - i < 4) {
            return ((j() & 255) << 24) | ((j() & 255) << 16) | ((j() & 255) << 8) | (j() & 255);
        }
        byte[] bArr = uVar.f2184c;
        int i3 = i + 1;
        int i4 = i3 + 1;
        int i5 = ((bArr[i] & 255) << 24) | ((bArr[i3] & 255) << 16);
        int i6 = i4 + 1;
        int i7 = i5 | ((bArr[i4] & 255) << 8);
        int i8 = i6 + 1;
        int i9 = i7 | (bArr[i6] & 255);
        this.f2132c -= 4;
        if (i8 == i2) {
            this.f2131b = uVar.c();
            v.a(uVar);
        } else {
            uVar.d = i8;
        }
        return i9;
    }

    @Override // c.e
    public long m() {
        if (this.f2132c < 8) {
            throw new IllegalStateException("size < 8: " + this.f2132c);
        }
        u uVar = this.f2131b;
        int i = uVar.d;
        int i2 = uVar.e;
        if (i2 - i < 8) {
            return ((l() & 4294967295L) << 32) | (4294967295L & l());
        }
        byte[] bArr = uVar.f2184c;
        long j = (bArr[i] & 255) << 56;
        int i3 = i + 1 + 1 + 1;
        long j2 = j | ((bArr[r8] & 255) << 48) | ((bArr[r1] & 255) << 40);
        long j3 = j2 | ((bArr[i3] & 255) << 32) | ((bArr[r1] & 255) << 24);
        long j4 = j3 | ((bArr[r6] & 255) << 16);
        long j5 = j4 | ((bArr[r1] & 255) << 8);
        int i4 = i3 + 1 + 1 + 1 + 1 + 1;
        long j6 = (bArr[r6] & 255) | j5;
        this.f2132c -= 8;
        if (i4 == i2) {
            this.f2131b = uVar.c();
            v.a(uVar);
        } else {
            uVar.d = i4;
        }
        return j6;
    }

    @Override // c.e
    public short n() {
        return ab.a(k());
    }

    @Override // c.e
    public int o() {
        return ab.a(l());
    }

    @Override // c.e
    public long p() {
        return ab.a(m());
    }

    /* JADX WARN: Removed duplicated region for block: B:33:0x009a  */
    /* JADX WARN: Removed duplicated region for block: B:34:0x00a4  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00ac A[EDGE_INSN: B:48:0x00ac->B:38:0x00ac BREAK  A[LOOP:0: B:5:0x0010->B:50:?], SYNTHETIC] */
    @Override // c.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long q() {
        /*
            r17 = this;
            r0 = r17
            long r1 = r0.f2132c
            r3 = 0
            int r1 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r1 == 0) goto Lb7
            r5 = -7
            r7 = 0
            r8 = r5
            r5 = 0
            r6 = 0
        L10:
            c.u r10 = r0.f2131b
            byte[] r11 = r10.f2184c
            int r12 = r10.d
            int r13 = r10.e
        L18:
            if (r12 >= r13) goto L98
            r15 = r11[r12]
            r14 = 48
            if (r15 < r14) goto L6a
            r1 = 57
            if (r15 > r1) goto L6a
            int r14 = r14 - r15
            r1 = -922337203685477580(0xf333333333333334, double:-8.390303882365713E246)
            int r16 = (r3 > r1 ? 1 : (r3 == r1 ? 0 : -1))
            if (r16 < 0) goto L3d
            if (r16 != 0) goto L36
            long r1 = (long) r14
            int r1 = (r1 > r8 ? 1 : (r1 == r8 ? 0 : -1))
            if (r1 >= 0) goto L36
            goto L3d
        L36:
            r1 = 10
            long r3 = r3 * r1
            long r1 = (long) r14
            long r3 = r3 + r1
            goto L74
        L3d:
            c.c r1 = new c.c
            r1.<init>()
            c.c r1 = r1.o(r3)
            c.c r1 = r1.m(r15)
            if (r5 != 0) goto L4f
            r1.j()
        L4f:
            java.lang.NumberFormatException r2 = new java.lang.NumberFormatException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "Number too large: "
            r3.append(r4)
            java.lang.String r1 = r1.t()
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1)
            throw r2
        L6a:
            r1 = 45
            if (r15 != r1) goto L79
            if (r7 != 0) goto L79
            r1 = 1
            long r8 = r8 - r1
            r5 = 1
        L74:
            int r12 = r12 + 1
            int r7 = r7 + 1
            goto L18
        L79:
            if (r7 == 0) goto L7d
            r6 = 1
            goto L98
        L7d:
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Expected leading [0-9] or '-' character but was 0x"
            r2.append(r3)
            java.lang.String r3 = java.lang.Integer.toHexString(r15)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            r1.<init>(r2)
            throw r1
        L98:
            if (r12 != r13) goto La4
            c.u r1 = r10.c()
            r0.f2131b = r1
            c.v.a(r10)
            goto La6
        La4:
            r10.d = r12
        La6:
            if (r6 != 0) goto Lac
            c.u r1 = r0.f2131b
            if (r1 != 0) goto L10
        Lac:
            long r1 = r0.f2132c
            long r6 = (long) r7
            long r1 = r1 - r6
            r0.f2132c = r1
            if (r5 == 0) goto Lb5
            goto Lb6
        Lb5:
            long r3 = -r3
        Lb6:
            return r3
        Lb7:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "size == 0"
            r1.<init>(r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: c.c.q():long");
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0094  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00a6 A[EDGE_INSN: B:43:0x00a6->B:37:0x00a6 BREAK  A[LOOP:0: B:5:0x000b->B:45:?], SYNTHETIC] */
    @Override // c.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long r() {
        /*
            r14 = this;
            long r0 = r14.f2132c
            r2 = 0
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 == 0) goto Lad
            r0 = 0
            r4 = r2
            r1 = 0
        Lb:
            c.u r6 = r14.f2131b
            byte[] r7 = r6.f2184c
            int r8 = r6.d
            int r9 = r6.e
        L13:
            if (r8 >= r9) goto L92
            r10 = r7[r8]
            r11 = 48
            if (r10 < r11) goto L22
            r11 = 57
            if (r10 > r11) goto L22
            int r11 = r10 + (-48)
            goto L3b
        L22:
            r11 = 97
            if (r10 < r11) goto L2f
            r11 = 102(0x66, float:1.43E-43)
            if (r10 > r11) goto L2f
            int r11 = r10 + (-97)
            int r11 = r11 + 10
            goto L3b
        L2f:
            r11 = 65
            if (r10 < r11) goto L73
            r11 = 70
            if (r10 > r11) goto L73
            int r11 = r10 + (-65)
            int r11 = r11 + 10
        L3b:
            r12 = -1152921504606846976(0xf000000000000000, double:-3.105036184601418E231)
            long r12 = r12 & r4
            int r12 = (r12 > r2 ? 1 : (r12 == r2 ? 0 : -1))
            if (r12 != 0) goto L4b
            r10 = 4
            long r4 = r4 << r10
            long r10 = (long) r11
            long r4 = r4 | r10
            int r8 = r8 + 1
            int r0 = r0 + 1
            goto L13
        L4b:
            c.c r0 = new c.c
            r0.<init>()
            c.c r0 = r0.n(r4)
            c.c r0 = r0.m(r10)
            java.lang.NumberFormatException r1 = new java.lang.NumberFormatException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Number too large: "
            r2.append(r3)
            java.lang.String r0 = r0.t()
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r1.<init>(r0)
            throw r1
        L73:
            if (r0 == 0) goto L77
            r1 = 1
            goto L92
        L77:
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
        L92:
            if (r8 != r9) goto L9e
            c.u r7 = r6.c()
            r14.f2131b = r7
            c.v.a(r6)
            goto La0
        L9e:
            r6.d = r8
        La0:
            if (r1 != 0) goto La6
            c.u r6 = r14.f2131b
            if (r6 != 0) goto Lb
        La6:
            long r1 = r14.f2132c
            long r6 = (long) r0
            long r1 = r1 - r6
            r14.f2132c = r1
            return r4
        Lad:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "size == 0"
            r0.<init>(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: c.c.r():long");
    }

    @Override // c.e
    public f s() {
        return new f(x());
    }

    @Override // c.e
    public f d(long j) throws EOFException {
        return new f(h(j));
    }

    @Override // c.e
    public int a(q qVar) {
        u uVar = this.f2131b;
        if (uVar == null) {
            return qVar.indexOf(f.f2139b);
        }
        f[] fVarArr = qVar.f2166a;
        int length = fVarArr.length;
        for (int i = 0; i < length; i++) {
            f fVar = fVarArr[i];
            if (this.f2132c >= fVar.k() && a(uVar, uVar.d, fVar, 0, fVar.k())) {
                try {
                    i(fVar.k());
                    return i;
                } catch (EOFException e) {
                    throw new AssertionError(e);
                }
            }
        }
        return -1;
    }

    int b(q qVar) {
        u uVar = this.f2131b;
        f[] fVarArr = qVar.f2166a;
        int length = fVarArr.length;
        for (int i = 0; i < length; i++) {
            f fVar = fVarArr[i];
            int iMin = (int) Math.min(this.f2132c, fVar.k());
            if (iMin == 0 || a(uVar, uVar.d, fVar, 0, iMin)) {
                return i;
            }
        }
        return -1;
    }

    @Override // c.e
    public void b(c cVar, long j) throws EOFException {
        if (this.f2132c < j) {
            cVar.a_(this, this.f2132c);
            throw new EOFException();
        }
        cVar.a_(this, j);
    }

    @Override // c.e
    public long a(x xVar) throws IOException {
        long j = this.f2132c;
        if (j > 0) {
            xVar.a_(this, j);
        }
        return j;
    }

    @Override // c.e
    public String t() {
        try {
            return a(this.f2132c, ab.f2127a);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // c.e
    public String e(long j) throws EOFException {
        return a(j, ab.f2127a);
    }

    @Override // c.e
    public String a(Charset charset) {
        try {
            return a(this.f2132c, charset);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // c.e
    public String a(long j, Charset charset) throws EOFException {
        ab.a(this.f2132c, 0L, j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        if (j == 0) {
            return "";
        }
        u uVar = this.f2131b;
        if (uVar.d + j > uVar.e) {
            return new String(h(j), charset);
        }
        String str = new String(uVar.f2184c, uVar.d, (int) j, charset);
        uVar.d = (int) (uVar.d + j);
        this.f2132c -= j;
        if (uVar.d == uVar.e) {
            this.f2131b = uVar.c();
            v.a(uVar);
        }
        return str;
    }

    @Override // c.e
    @Nullable
    public String u() throws EOFException {
        long jA = a((byte) 10);
        if (jA != -1) {
            return g(jA);
        }
        if (this.f2132c != 0) {
            return e(this.f2132c);
        }
        return null;
    }

    @Override // c.e
    public String v() throws EOFException {
        return f(Long.MAX_VALUE);
    }

    @Override // c.e
    public String f(long j) throws EOFException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j != Long.MAX_VALUE ? j + 1 : Long.MAX_VALUE;
        long jA = a((byte) 10, 0L, j2);
        if (jA != -1) {
            return g(jA);
        }
        if (j2 < b() && c(j2 - 1) == 13 && c(j2) == 10) {
            return g(j2);
        }
        c cVar = new c();
        a(cVar, 0L, Math.min(32L, b()));
        throw new EOFException("\\n not found: limit=" + Math.min(b(), j) + " content=" + cVar.s().h() + UIUtils.ELLIPSIS_CHAR);
    }

    String g(long j) throws EOFException {
        if (j > 0) {
            long j2 = j - 1;
            if (c(j2) == 13) {
                String strE = e(j2);
                i(2L);
                return strE;
            }
        }
        String strE2 = e(j);
        i(1L);
        return strE2;
    }

    @Override // c.e
    public int w() throws EOFException {
        int i;
        int i2;
        int i3;
        if (this.f2132c == 0) {
            throw new EOFException();
        }
        byte bC = c(0L);
        if ((bC & 128) == 0) {
            i = bC & 127;
            i2 = 1;
            i3 = 0;
        } else if ((bC & 224) == 192) {
            i = bC & 31;
            i2 = 2;
            i3 = 128;
        } else if ((bC & 240) == 224) {
            i = bC & 15;
            i2 = 3;
            i3 = 2048;
        } else {
            if ((bC & 248) != 240) {
                i(1L);
                return f2130a;
            }
            i = bC & 7;
            i2 = 4;
            i3 = 65536;
        }
        long j = i2;
        if (this.f2132c < j) {
            throw new EOFException("size < " + i2 + ": " + this.f2132c + " (to read code point prefixed 0x" + Integer.toHexString(bC) + ")");
        }
        for (int i4 = 1; i4 < i2; i4++) {
            long j2 = i4;
            byte bC2 = c(j2);
            if ((bC2 & 192) != 128) {
                i(j2);
                return f2130a;
            }
            i = (i << 6) | (bC2 & 63);
        }
        i(j);
        return i > 1114111 ? f2130a : ((i < 55296 || i > 57343) && i >= i3) ? i : f2130a;
    }

    @Override // c.e
    public byte[] x() {
        try {
            return h(this.f2132c);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // c.e
    public byte[] h(long j) throws EOFException {
        ab.a(this.f2132c, 0L, j);
        if (j > 2147483647L) {
            throw new IllegalArgumentException("byteCount > Integer.MAX_VALUE: " + j);
        }
        byte[] bArr = new byte[(int) j];
        b(bArr);
        return bArr;
    }

    @Override // c.e
    public int a(byte[] bArr) {
        return a(bArr, 0, bArr.length);
    }

    @Override // c.e
    public void b(byte[] bArr) throws EOFException {
        int i = 0;
        while (i < bArr.length) {
            int iA = a(bArr, i, bArr.length - i);
            if (iA == -1) {
                throw new EOFException();
            }
            i += iA;
        }
    }

    @Override // c.e
    public int a(byte[] bArr, int i, int i2) {
        ab.a(bArr.length, i, i2);
        u uVar = this.f2131b;
        if (uVar == null) {
            return -1;
        }
        int iMin = Math.min(i2, uVar.e - uVar.d);
        System.arraycopy(uVar.f2184c, uVar.d, bArr, i, iMin);
        uVar.d += iMin;
        this.f2132c -= iMin;
        if (uVar.d == uVar.e) {
            this.f2131b = uVar.c();
            v.a(uVar);
        }
        return iMin;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        u uVar = this.f2131b;
        if (uVar == null) {
            return -1;
        }
        int iMin = Math.min(byteBuffer.remaining(), uVar.e - uVar.d);
        byteBuffer.put(uVar.f2184c, uVar.d, iMin);
        uVar.d += iMin;
        this.f2132c -= iMin;
        if (uVar.d == uVar.e) {
            this.f2131b = uVar.c();
            v.a(uVar);
        }
        return iMin;
    }

    public void y() {
        try {
            i(this.f2132c);
        } catch (EOFException e) {
            throw new AssertionError(e);
        }
    }

    @Override // c.e
    public void i(long j) throws EOFException {
        while (j > 0) {
            if (this.f2131b == null) {
                throw new EOFException();
            }
            int iMin = (int) Math.min(j, this.f2131b.e - this.f2131b.d);
            long j2 = iMin;
            this.f2132c -= j2;
            j -= j2;
            this.f2131b.d += iMin;
            if (this.f2131b.d == this.f2131b.e) {
                u uVar = this.f2131b;
                this.f2131b = uVar.c();
                v.a(uVar);
            }
        }
    }

    @Override // c.d
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c g(f fVar) {
        if (fVar == null) {
            throw new IllegalArgumentException("byteString == null");
        }
        fVar.a(this);
        return this;
    }

    @Override // c.d
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c b(String str) {
        return b(str, 0, str.length());
    }

    @Override // c.d
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c b(String str, int i, int i2) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalArgumentException("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 > str.length()) {
            throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
        }
        while (i < i2) {
            char cCharAt = str.charAt(i);
            if (cCharAt < 128) {
                u uVarG = g(1);
                byte[] bArr = uVarG.f2184c;
                int i3 = uVarG.e - i;
                int iMin = Math.min(i2, 8192 - i3);
                int i4 = i + 1;
                bArr[i + i3] = (byte) cCharAt;
                while (i4 < iMin) {
                    char cCharAt2 = str.charAt(i4);
                    if (cCharAt2 >= 128) {
                        break;
                    }
                    bArr[i4 + i3] = (byte) cCharAt2;
                    i4++;
                }
                int i5 = (i3 + i4) - uVarG.e;
                uVarG.e += i5;
                this.f2132c += i5;
                i = i4;
            } else if (cCharAt < 2048) {
                m((cCharAt >> 6) | 192);
                m((cCharAt & '?') | 128);
                i++;
            } else if (cCharAt < 55296 || cCharAt > 57343) {
                m((cCharAt >> '\f') | 224);
                m(((cCharAt >> 6) & 63) | 128);
                m((cCharAt & '?') | 128);
                i++;
            } else {
                int i6 = i + 1;
                char cCharAt3 = i6 < i2 ? str.charAt(i6) : (char) 0;
                if (cCharAt > 56319 || cCharAt3 < 56320 || cCharAt3 > 57343) {
                    m(63);
                    i = i6;
                } else {
                    int i7 = (((cCharAt & 10239) << 10) | (9215 & cCharAt3)) + 65536;
                    m((i7 >> 18) | 240);
                    m(((i7 >> 12) & 63) | 128);
                    m(((i7 >> 6) & 63) | 128);
                    m((i7 & 63) | 128);
                    i += 2;
                }
            }
        }
        return this;
    }

    @Override // c.d
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c n(int i) {
        if (i < 128) {
            m(i);
        } else if (i < 2048) {
            m((i >> 6) | 192);
            m((i & 63) | 128);
        } else if (i < 65536) {
            if (i >= 55296 && i <= 57343) {
                m(63);
            } else {
                m((i >> 12) | 224);
                m(((i >> 6) & 63) | 128);
                m((i & 63) | 128);
            }
        } else if (i <= 1114111) {
            m((i >> 18) | 240);
            m(((i >> 12) & 63) | 128);
            m(((i >> 6) & 63) | 128);
            m((i & 63) | 128);
        } else {
            throw new IllegalArgumentException("Unexpected code point: " + Integer.toHexString(i));
        }
        return this;
    }

    @Override // c.d
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c b(String str, Charset charset) {
        return b(str, 0, str.length(), charset);
    }

    @Override // c.d
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public c b(String str, int i, int i2, Charset charset) {
        if (str == null) {
            throw new IllegalArgumentException("string == null");
        }
        if (i < 0) {
            throw new IllegalAccessError("beginIndex < 0: " + i);
        }
        if (i2 < i) {
            throw new IllegalArgumentException("endIndex < beginIndex: " + i2 + " < " + i);
        }
        if (i2 <= str.length()) {
            if (charset == null) {
                throw new IllegalArgumentException("charset == null");
            }
            if (charset.equals(ab.f2127a)) {
                return b(str, i, i2);
            }
            byte[] bytes = str.substring(i, i2).getBytes(charset);
            return c(bytes, 0, bytes.length);
        }
        throw new IllegalArgumentException("endIndex > string.length: " + i2 + " > " + str.length());
    }

    @Override // c.d
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public c d(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        return c(bArr, 0, bArr.length);
    }

    @Override // c.d
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public c c(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = i2;
        ab.a(bArr.length, i, j);
        int i3 = i2 + i;
        while (i < i3) {
            u uVarG = g(1);
            int iMin = Math.min(i3 - i, 8192 - uVarG.e);
            System.arraycopy(bArr, i, uVarG.f2184c, uVarG.e, iMin);
            i += iMin;
            uVarG.e += iMin;
        }
        this.f2132c += j;
        return this;
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer == null) {
            throw new IllegalArgumentException("source == null");
        }
        int iRemaining = byteBuffer.remaining();
        int i = iRemaining;
        while (i > 0) {
            u uVarG = g(1);
            int iMin = Math.min(i, 8192 - uVarG.e);
            byteBuffer.get(uVarG.f2184c, uVarG.e, iMin);
            i -= iMin;
            uVarG.e += iMin;
        }
        this.f2132c += iRemaining;
        return iRemaining;
    }

    @Override // c.d
    public long a(y yVar) throws IOException {
        if (yVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jA = yVar.a(this, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (jA == -1) {
                return j;
            }
            j += jA;
        }
    }

    @Override // c.d
    public d a(y yVar, long j) throws IOException {
        while (j > 0) {
            long jA = yVar.a(this, j);
            if (jA == -1) {
                throw new EOFException();
            }
            j -= jA;
        }
        return this;
    }

    @Override // c.d
    /* renamed from: b, reason: merged with bridge method [inline-methods] */
    public c m(int i) {
        u uVarG = g(1);
        byte[] bArr = uVarG.f2184c;
        int i2 = uVarG.e;
        uVarG.e = i2 + 1;
        bArr[i2] = (byte) i;
        this.f2132c++;
        return this;
    }

    @Override // c.d
    /* renamed from: c, reason: merged with bridge method [inline-methods] */
    public c l(int i) {
        u uVarG = g(2);
        byte[] bArr = uVarG.f2184c;
        int i2 = uVarG.e;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 8) & 255);
        bArr[i3] = (byte) (i & 255);
        uVarG.e = i3 + 1;
        this.f2132c += 2;
        return this;
    }

    @Override // c.d
    /* renamed from: d, reason: merged with bridge method [inline-methods] */
    public c k(int i) {
        return l((int) ab.a((short) i));
    }

    @Override // c.d
    /* renamed from: e, reason: merged with bridge method [inline-methods] */
    public c j(int i) {
        u uVarG = g(4);
        byte[] bArr = uVarG.f2184c;
        int i2 = uVarG.e;
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((i >>> 24) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((i >>> 16) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((i >>> 8) & 255);
        bArr[i5] = (byte) (i & 255);
        uVarG.e = i5 + 1;
        this.f2132c += 4;
        return this;
    }

    @Override // c.d
    /* renamed from: f, reason: merged with bridge method [inline-methods] */
    public c i(int i) {
        return j(ab.a(i));
    }

    @Override // c.d
    /* renamed from: j, reason: merged with bridge method [inline-methods] */
    public c q(long j) {
        u uVarG = g(8);
        byte[] bArr = uVarG.f2184c;
        int i = uVarG.e;
        int i2 = i + 1;
        bArr[i] = (byte) ((j >>> 56) & 255);
        int i3 = i2 + 1;
        bArr[i2] = (byte) ((j >>> 48) & 255);
        int i4 = i3 + 1;
        bArr[i3] = (byte) ((j >>> 40) & 255);
        int i5 = i4 + 1;
        bArr[i4] = (byte) ((j >>> 32) & 255);
        int i6 = i5 + 1;
        bArr[i5] = (byte) ((j >>> 24) & 255);
        int i7 = i6 + 1;
        bArr[i6] = (byte) ((j >>> 16) & 255);
        int i8 = i7 + 1;
        bArr[i7] = (byte) ((j >>> 8) & 255);
        bArr[i8] = (byte) (j & 255);
        uVarG.e = i8 + 1;
        this.f2132c += 8;
        return this;
    }

    @Override // c.d
    /* renamed from: k, reason: merged with bridge method [inline-methods] */
    public c p(long j) {
        return q(ab.a(j));
    }

    @Override // c.d
    /* renamed from: l, reason: merged with bridge method [inline-methods] */
    public c o(long j) {
        if (j == 0) {
            return m(48);
        }
        boolean z = false;
        int i = 1;
        if (j < 0) {
            j = -j;
            if (j < 0) {
                return b("-9223372036854775808");
            }
            z = true;
        }
        if (j >= 100000000) {
            i = j < 1000000000000L ? j < 10000000000L ? j < 1000000000 ? 9 : 10 : j < 100000000000L ? 11 : 12 : j < 1000000000000000L ? j < 10000000000000L ? 13 : j < 100000000000000L ? 14 : 15 : j < 100000000000000000L ? j < 10000000000000000L ? 16 : 17 : j < 1000000000000000000L ? 18 : 19;
        } else if (j >= 10000) {
            i = j < 1000000 ? j < 100000 ? 5 : 6 : j < 10000000 ? 7 : 8;
        } else if (j >= 100) {
            i = j < 1000 ? 3 : 4;
        } else if (j >= 10) {
            i = 2;
        }
        if (z) {
            i++;
        }
        u uVarG = g(i);
        byte[] bArr = uVarG.f2184c;
        int i2 = uVarG.e + i;
        while (j != 0) {
            i2--;
            bArr[i2] = d[(int) (j % 10)];
            j /= 10;
        }
        if (z) {
            bArr[i2 - 1] = Framer.STDIN_FRAME_PREFIX;
        }
        uVarG.e += i;
        this.f2132c += i;
        return this;
    }

    @Override // c.d
    /* renamed from: m, reason: merged with bridge method [inline-methods] */
    public c n(long j) {
        if (j == 0) {
            return m(48);
        }
        int iNumberOfTrailingZeros = (Long.numberOfTrailingZeros(Long.highestOneBit(j)) / 4) + 1;
        u uVarG = g(iNumberOfTrailingZeros);
        byte[] bArr = uVarG.f2184c;
        int i = uVarG.e;
        for (int i2 = (uVarG.e + iNumberOfTrailingZeros) - 1; i2 >= i; i2--) {
            bArr[i2] = d[(int) (15 & j)];
            j >>>= 4;
        }
        uVarG.e += iNumberOfTrailingZeros;
        this.f2132c += iNumberOfTrailingZeros;
        return this;
    }

    u g(int i) {
        if (i < 1 || i > 8192) {
            throw new IllegalArgumentException();
        }
        if (this.f2131b == null) {
            this.f2131b = v.a();
            u uVar = this.f2131b;
            u uVar2 = this.f2131b;
            u uVar3 = this.f2131b;
            uVar2.i = uVar3;
            uVar.h = uVar3;
            return uVar3;
        }
        u uVar4 = this.f2131b.i;
        return (uVar4.e + i > 8192 || !uVar4.g) ? uVar4.a(v.a()) : uVar4;
    }

    @Override // c.x
    public void a_(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (cVar == this) {
            throw new IllegalArgumentException("source == this");
        }
        ab.a(cVar.f2132c, 0L, j);
        while (j > 0) {
            if (j < cVar.f2131b.e - cVar.f2131b.d) {
                u uVar = this.f2131b != null ? this.f2131b.i : null;
                if (uVar != null && uVar.g) {
                    if ((uVar.e + j) - (uVar.f ? 0 : uVar.d) <= PlaybackStateCompat.ACTION_PLAY_FROM_URI) {
                        cVar.f2131b.a(uVar, (int) j);
                        cVar.f2132c -= j;
                        this.f2132c += j;
                        return;
                    }
                }
                cVar.f2131b = cVar.f2131b.a((int) j);
            }
            u uVar2 = cVar.f2131b;
            long j2 = uVar2.e - uVar2.d;
            cVar.f2131b = uVar2.c();
            if (this.f2131b == null) {
                this.f2131b = uVar2;
                u uVar3 = this.f2131b;
                u uVar4 = this.f2131b;
                u uVar5 = this.f2131b;
                uVar4.i = uVar5;
                uVar3.h = uVar5;
            } else {
                this.f2131b.i.a(uVar2).d();
            }
            cVar.f2132c -= j2;
            this.f2132c += j2;
            j -= j2;
        }
    }

    @Override // c.y
    public long a(c cVar, long j) {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f2132c == 0) {
            return -1L;
        }
        if (j > this.f2132c) {
            j = this.f2132c;
        }
        cVar.a_(this, j);
        return j;
    }

    @Override // c.e
    public long a(byte b2) {
        return a(b2, 0L, Long.MAX_VALUE);
    }

    @Override // c.e
    public long a(byte b2, long j) {
        return a(b2, j, Long.MAX_VALUE);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0048  */
    @Override // c.e
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public long a(byte r11, long r12, long r14) {
        /*
            r10 = this;
            r0 = 0
            int r2 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r2 < 0) goto L78
            int r2 = (r14 > r12 ? 1 : (r14 == r12 ? 0 : -1))
            if (r2 < 0) goto L78
            long r2 = r10.f2132c
            int r2 = (r14 > r2 ? 1 : (r14 == r2 ? 0 : -1))
            if (r2 <= 0) goto L12
            long r14 = r10.f2132c
        L12:
            int r2 = (r12 > r14 ? 1 : (r12 == r14 ? 0 : -1))
            r3 = -1
            if (r2 != 0) goto L19
            return r3
        L19:
            c.u r2 = r10.f2131b
            if (r2 != 0) goto L1e
            return r3
        L1e:
            long r5 = r10.f2132c
            long r5 = r5 - r12
            int r5 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r5 >= 0) goto L35
            long r0 = r10.f2132c
        L27:
            int r5 = (r0 > r12 ? 1 : (r0 == r12 ? 0 : -1))
            if (r5 <= 0) goto L44
            c.u r2 = r2.i
            int r5 = r2.e
            int r6 = r2.d
            int r5 = r5 - r6
            long r5 = (long) r5
            long r0 = r0 - r5
            goto L27
        L35:
            int r5 = r2.e
            int r6 = r2.d
            int r5 = r5 - r6
            long r5 = (long) r5
            long r5 = r5 + r0
            int r7 = (r5 > r12 ? 1 : (r5 == r12 ? 0 : -1))
            if (r7 >= 0) goto L44
            c.u r2 = r2.h
            r0 = r5
            goto L35
        L44:
            int r5 = (r0 > r14 ? 1 : (r0 == r14 ? 0 : -1))
            if (r5 >= 0) goto L77
            byte[] r5 = r2.f2184c
            int r6 = r2.e
            long r6 = (long) r6
            int r8 = r2.d
            long r8 = (long) r8
            long r8 = r8 + r14
            long r8 = r8 - r0
            long r6 = java.lang.Math.min(r6, r8)
            int r6 = (int) r6
            int r7 = r2.d
            long r7 = (long) r7
            long r7 = r7 + r12
            long r7 = r7 - r0
            int r12 = (int) r7
        L5d:
            if (r12 >= r6) goto L6c
            r13 = r5[r12]
            if (r13 != r11) goto L69
            int r11 = r2.d
            int r12 = r12 - r11
            long r11 = (long) r12
            long r11 = r11 + r0
            return r11
        L69:
            int r12 = r12 + 1
            goto L5d
        L6c:
            int r12 = r2.e
            int r13 = r2.d
            int r12 = r12 - r13
            long r12 = (long) r12
            long r12 = r12 + r0
            c.u r2 = r2.h
            r0 = r12
            goto L44
        L77:
            return r3
        L78:
            java.lang.IllegalArgumentException r11 = new java.lang.IllegalArgumentException
            r0 = 3
            java.lang.Object[] r0 = new java.lang.Object[r0]
            r1 = 0
            long r2 = r10.f2132c
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            r0[r1] = r2
            java.lang.Long r12 = java.lang.Long.valueOf(r12)
            r13 = 1
            r0[r13] = r12
            r12 = 2
            java.lang.Long r13 = java.lang.Long.valueOf(r14)
            r0[r12] = r13
            java.lang.String r12 = "size=%s fromIndex=%s toIndex=%s"
            java.lang.String r12 = java.lang.String.format(r12, r0)
            r11.<init>(r12)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: c.c.a(byte, long, long):long");
    }

    @Override // c.e
    public long b(f fVar) throws IOException {
        return a(fVar, 0L);
    }

    @Override // c.e
    public long a(f fVar, long j) throws IOException {
        byte[] bArr;
        u uVar;
        if (fVar.k() == 0) {
            throw new IllegalArgumentException("bytes is empty");
        }
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        u uVar2 = this.f2131b;
        long j3 = -1;
        if (uVar2 == null) {
            return -1L;
        }
        if (this.f2132c - j >= j) {
            while (true) {
                long j4 = (uVar2.e - uVar2.d) + j2;
                if (j4 >= j) {
                    break;
                }
                uVar2 = uVar2.h;
                j2 = j4;
            }
        } else {
            j2 = this.f2132c;
            while (j2 > j) {
                uVar2 = uVar2.i;
                j2 -= uVar2.e - uVar2.d;
            }
        }
        byte b2 = fVar.b(0);
        int iK = fVar.k();
        long j5 = (this.f2132c - iK) + 1;
        long j6 = j;
        long j7 = j2;
        u uVar3 = uVar2;
        while (j7 < j5) {
            byte[] bArr2 = uVar3.f2184c;
            int iMin = (int) Math.min(uVar3.e, (uVar3.d + j5) - j7);
            int i = (int) ((uVar3.d + j6) - j7);
            while (i < iMin) {
                if (bArr2[i] == b2) {
                    bArr = bArr2;
                    uVar = uVar3;
                    if (a(uVar3, i + 1, fVar, 1, iK)) {
                        return (i - uVar.d) + j7;
                    }
                } else {
                    bArr = bArr2;
                    uVar = uVar3;
                }
                i++;
                uVar3 = uVar;
                bArr2 = bArr;
            }
            j6 = (r6.e - r6.d) + j7;
            uVar3 = uVar3.h;
            j7 = j6;
            j3 = -1;
        }
        return j3;
    }

    @Override // c.e
    public long c(f fVar) {
        return b(fVar, 0L);
    }

    @Override // c.e
    public long b(f fVar, long j) {
        long j2 = 0;
        if (j < 0) {
            throw new IllegalArgumentException("fromIndex < 0");
        }
        u uVar = this.f2131b;
        if (uVar == null) {
            return -1L;
        }
        if (this.f2132c - j >= j) {
            while (true) {
                long j3 = (uVar.e - uVar.d) + j2;
                if (j3 >= j) {
                    break;
                }
                uVar = uVar.h;
                j2 = j3;
            }
        } else {
            j2 = this.f2132c;
            while (j2 > j) {
                uVar = uVar.i;
                j2 -= uVar.e - uVar.d;
            }
        }
        if (fVar.k() == 2) {
            byte b2 = fVar.b(0);
            byte b3 = fVar.b(1);
            while (j2 < this.f2132c) {
                byte[] bArr = uVar.f2184c;
                int i = uVar.e;
                for (int i2 = (int) ((uVar.d + j) - j2); i2 < i; i2++) {
                    byte b4 = bArr[i2];
                    if (b4 == b2 || b4 == b3) {
                        return (i2 - uVar.d) + j2;
                    }
                }
                j = (uVar.e - uVar.d) + j2;
                uVar = uVar.h;
                j2 = j;
            }
        } else {
            byte[] bArrM = fVar.m();
            while (j2 < this.f2132c) {
                byte[] bArr2 = uVar.f2184c;
                int i3 = uVar.e;
                for (int i4 = (int) ((uVar.d + j) - j2); i4 < i3; i4++) {
                    byte b5 = bArr2[i4];
                    for (byte b6 : bArrM) {
                        if (b5 == b6) {
                            return (i4 - uVar.d) + j2;
                        }
                    }
                }
                j = (uVar.e - uVar.d) + j2;
                uVar = uVar.h;
                j2 = j;
            }
        }
        return -1L;
    }

    @Override // c.e
    public boolean a(long j, f fVar) {
        return a(j, fVar, 0, fVar.k());
    }

    @Override // c.e
    public boolean a(long j, f fVar, int i, int i2) {
        if (j < 0 || i < 0 || i2 < 0 || this.f2132c - j < i2 || fVar.k() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            if (c(i3 + j) != fVar.b(i + i3)) {
                return false;
            }
        }
        return true;
    }

    private boolean a(u uVar, int i, f fVar, int i2, int i3) {
        int i4 = uVar.e;
        byte[] bArr = uVar.f2184c;
        while (i2 < i3) {
            if (i == i4) {
                uVar = uVar.h;
                byte[] bArr2 = uVar.f2184c;
                bArr = bArr2;
                i = uVar.d;
                i4 = uVar.e;
            }
            if (bArr[i] != fVar.b(i2)) {
                return false;
            }
            i++;
            i2++;
        }
        return true;
    }

    @Override // c.x
    public z a() {
        return z.f2188c;
    }

    List<Integer> z() {
        if (this.f2131b == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(this.f2131b.e - this.f2131b.d));
        u uVar = this.f2131b;
        while (true) {
            uVar = uVar.h;
            if (uVar == this.f2131b) {
                return arrayList;
            }
            arrayList.add(Integer.valueOf(uVar.e - uVar.d));
        }
    }

    public f A() {
        return c("MD5");
    }

    public f B() {
        return c(StringEncryptUtils.SHA_1);
    }

    public f C() {
        return c(StringEncryptUtils.SHA_256);
    }

    public f D() {
        return c("SHA-512");
    }

    private f c(String str) throws NoSuchAlgorithmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str);
            if (this.f2131b != null) {
                messageDigest.update(this.f2131b.f2184c, this.f2131b.d, this.f2131b.e - this.f2131b.d);
                u uVar = this.f2131b;
                while (true) {
                    uVar = uVar.h;
                    if (uVar == this.f2131b) {
                        break;
                    }
                    messageDigest.update(uVar.f2184c, uVar.d, uVar.e - uVar.d);
                }
            }
            return f.a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public f d(f fVar) {
        return a("HmacSHA1", fVar);
    }

    public f e(f fVar) {
        return a("HmacSHA256", fVar);
    }

    public f f(f fVar) {
        return a("HmacSHA512", fVar);
    }

    private f a(String str, f fVar) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(fVar.l(), str));
            if (this.f2131b != null) {
                mac.update(this.f2131b.f2184c, this.f2131b.d, this.f2131b.e - this.f2131b.d);
                u uVar = this.f2131b;
                while (true) {
                    uVar = uVar.h;
                    if (uVar == this.f2131b) {
                        break;
                    }
                    mac.update(uVar.f2184c, uVar.d, uVar.e - uVar.d);
                }
            }
            return f.a(mac.doFinal());
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        } catch (NoSuchAlgorithmException unused) {
            throw new AssertionError();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.f2132c != cVar.f2132c) {
            return false;
        }
        long j = 0;
        if (this.f2132c == 0) {
            return true;
        }
        u uVar = this.f2131b;
        u uVar2 = cVar.f2131b;
        int i = uVar.d;
        int i2 = uVar2.d;
        while (j < this.f2132c) {
            long jMin = Math.min(uVar.e - i, uVar2.e - i2);
            int i3 = i2;
            int i4 = i;
            int i5 = 0;
            while (i5 < jMin) {
                int i6 = i4 + 1;
                int i7 = i3 + 1;
                if (uVar.f2184c[i4] != uVar2.f2184c[i3]) {
                    return false;
                }
                i5++;
                i4 = i6;
                i3 = i7;
            }
            if (i4 == uVar.e) {
                uVar = uVar.h;
                i = uVar.d;
            } else {
                i = i4;
            }
            if (i3 == uVar2.e) {
                uVar2 = uVar2.h;
                i2 = uVar2.d;
            } else {
                i2 = i3;
            }
            j += jMin;
        }
        return true;
    }

    public int hashCode() {
        u uVar = this.f2131b;
        if (uVar == null) {
            return 0;
        }
        int i = 1;
        do {
            int i2 = uVar.e;
            for (int i3 = uVar.d; i3 < i2; i3++) {
                i = (i * 31) + uVar.f2184c[i3];
            }
            uVar = uVar.h;
        } while (uVar != this.f2131b);
        return i;
    }

    public String toString() {
        return F().toString();
    }

    /* renamed from: E, reason: merged with bridge method [inline-methods] */
    public c clone() {
        c cVar = new c();
        if (this.f2132c == 0) {
            return cVar;
        }
        cVar.f2131b = this.f2131b.a();
        u uVar = cVar.f2131b;
        u uVar2 = cVar.f2131b;
        u uVar3 = cVar.f2131b;
        uVar2.i = uVar3;
        uVar.h = uVar3;
        u uVar4 = this.f2131b;
        while (true) {
            uVar4 = uVar4.h;
            if (uVar4 != this.f2131b) {
                cVar.f2131b.i.a(uVar4.a());
            } else {
                cVar.f2132c = this.f2132c;
                return cVar;
            }
        }
    }

    public f F() {
        if (this.f2132c > 2147483647L) {
            throw new IllegalArgumentException("size > Integer.MAX_VALUE: " + this.f2132c);
        }
        return h((int) this.f2132c);
    }

    public f h(int i) {
        if (i == 0) {
            return f.f2139b;
        }
        return new w(this, i);
    }

    public a G() {
        return a(new a());
    }

    public a a(a aVar) {
        if (aVar.f2135a != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        aVar.f2135a = this;
        aVar.f2136b = false;
        return aVar;
    }

    public a H() {
        return b(new a());
    }

    public a b(a aVar) {
        if (aVar.f2135a != null) {
            throw new IllegalStateException("already attached to a buffer");
        }
        aVar.f2135a = this;
        aVar.f2136b = true;
        return aVar;
    }

    public static final class a implements Closeable {

        /* renamed from: a, reason: collision with root package name */
        public c f2135a;

        /* renamed from: b, reason: collision with root package name */
        public boolean f2136b;
        public byte[] d;
        private u g;

        /* renamed from: c, reason: collision with root package name */
        public long f2137c = -1;
        public int e = -1;
        public int f = -1;

        public int a() {
            if (this.f2137c != this.f2135a.f2132c) {
                return this.f2137c == -1 ? a(0L) : a(this.f2137c + (this.f - this.e));
            }
            throw new IllegalStateException();
        }

        public int a(long j) {
            if (j < -1 || j > this.f2135a.f2132c) {
                throw new ArrayIndexOutOfBoundsException(String.format("offset=%s > size=%s", Long.valueOf(j), Long.valueOf(this.f2135a.f2132c)));
            }
            if (j == -1 || j == this.f2135a.f2132c) {
                this.g = null;
                this.f2137c = j;
                this.d = null;
                this.e = -1;
                this.f = -1;
                return -1;
            }
            long j2 = 0;
            long j3 = this.f2135a.f2132c;
            u uVarA = this.f2135a.f2131b;
            u uVar = this.f2135a.f2131b;
            if (this.g != null) {
                long j4 = this.f2137c - (this.e - this.g.d);
                if (j4 > j) {
                    uVar = this.g;
                    j3 = j4;
                } else {
                    uVarA = this.g;
                    j2 = j4;
                }
            }
            if (j3 - j > j - j2) {
                while (j >= (uVarA.e - uVarA.d) + j2) {
                    j2 += uVarA.e - uVarA.d;
                    uVarA = uVarA.h;
                }
            } else {
                j2 = j3;
                uVarA = uVar;
                while (j2 > j) {
                    uVarA = uVarA.i;
                    j2 -= uVarA.e - uVarA.d;
                }
            }
            if (this.f2136b && uVarA.f) {
                u uVarB = uVarA.b();
                if (this.f2135a.f2131b == uVarA) {
                    this.f2135a.f2131b = uVarB;
                }
                uVarA = uVarA.a(uVarB);
                uVarA.i.c();
            }
            this.g = uVarA;
            this.f2137c = j;
            this.d = uVarA.f2184c;
            this.e = uVarA.d + ((int) (j - j2));
            this.f = uVarA.e;
            return this.f - this.e;
        }

        public long b(long j) {
            if (this.f2135a == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.f2136b) {
                throw new IllegalStateException("resizeBuffer() only permitted for read/write buffers");
            }
            long j2 = this.f2135a.f2132c;
            if (j <= j2) {
                if (j < 0) {
                    throw new IllegalArgumentException("newSize < 0: " + j);
                }
                long j3 = j2 - j;
                while (true) {
                    if (j3 <= 0) {
                        break;
                    }
                    u uVar = this.f2135a.f2131b.i;
                    long j4 = uVar.e - uVar.d;
                    if (j4 <= j3) {
                        this.f2135a.f2131b = uVar.c();
                        v.a(uVar);
                        j3 -= j4;
                    } else {
                        uVar.e = (int) (uVar.e - j3);
                        break;
                    }
                }
                this.g = null;
                this.f2137c = j;
                this.d = null;
                this.e = -1;
                this.f = -1;
            } else if (j > j2) {
                long j5 = j - j2;
                boolean z = true;
                while (j5 > 0) {
                    u uVarG = this.f2135a.g(1);
                    int iMin = (int) Math.min(j5, 8192 - uVarG.e);
                    uVarG.e += iMin;
                    j5 -= iMin;
                    if (z) {
                        this.g = uVarG;
                        this.f2137c = j2;
                        this.d = uVarG.f2184c;
                        this.e = uVarG.e - iMin;
                        this.f = uVarG.e;
                        z = false;
                    }
                }
            }
            this.f2135a.f2132c = j;
            return j2;
        }

        public long a(int i) {
            if (i <= 0) {
                throw new IllegalArgumentException("minByteCount <= 0: " + i);
            }
            if (i > 8192) {
                throw new IllegalArgumentException("minByteCount > Segment.SIZE: " + i);
            }
            if (this.f2135a == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            if (!this.f2136b) {
                throw new IllegalStateException("expandBuffer() only permitted for read/write buffers");
            }
            long j = this.f2135a.f2132c;
            u uVarG = this.f2135a.g(i);
            int i2 = 8192 - uVarG.e;
            uVarG.e = 8192;
            long j2 = i2;
            this.f2135a.f2132c = j + j2;
            this.g = uVarG;
            this.f2137c = j;
            this.d = uVarG.f2184c;
            this.e = 8192 - i2;
            this.f = 8192;
            return j2;
        }

        @Override // java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            if (this.f2135a == null) {
                throw new IllegalStateException("not attached to a buffer");
            }
            this.f2135a = null;
            this.g = null;
            this.f2137c = -1L;
            this.d = null;
            this.e = -1;
            this.f = -1;
        }
    }
}
