package c;

import android.support.v4.media.session.PlaybackStateCompat;
import com.bytedance.common.utility.UIUtils;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class t implements e {

    /* renamed from: a, reason: collision with root package name */
    public final c f2178a = new c();

    /* renamed from: b, reason: collision with root package name */
    public final y f2179b;

    /* renamed from: c, reason: collision with root package name */
    boolean f2180c;

    t(y yVar) {
        if (yVar == null) {
            throw new NullPointerException("source == null");
        }
        this.f2179b = yVar;
    }

    @Override // c.e
    public c c() {
        return this.f2178a;
    }

    @Override // c.y
    public long a(c cVar, long j) throws IOException {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        if (this.f2178a.f2132c == 0 && this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return this.f2178a.a(cVar, Math.min(j, this.f2178a.f2132c));
    }

    @Override // c.e
    public boolean g() throws IOException {
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        return this.f2178a.g() && this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
    }

    @Override // c.e
    public void a(long j) throws IOException {
        if (!b(j)) {
            throw new EOFException();
        }
    }

    @Override // c.e
    public boolean b(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        while (this.f2178a.f2132c < j) {
            if (this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return false;
            }
        }
        return true;
    }

    @Override // c.e
    public byte j() throws IOException {
        a(1L);
        return this.f2178a.j();
    }

    @Override // c.e
    public f s() throws IOException {
        this.f2178a.a(this.f2179b);
        return this.f2178a.s();
    }

    @Override // c.e
    public f d(long j) throws IOException {
        a(j);
        return this.f2178a.d(j);
    }

    @Override // c.e
    public int a(q qVar) throws IOException {
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        do {
            int iB = this.f2178a.b(qVar);
            if (iB == -1) {
                return -1;
            }
            long jK = qVar.f2166a[iB].k();
            if (jK <= this.f2178a.f2132c) {
                this.f2178a.i(jK);
                return iB;
            }
        } while (this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return -1;
    }

    @Override // c.e
    public byte[] x() throws IOException {
        this.f2178a.a(this.f2179b);
        return this.f2178a.x();
    }

    @Override // c.e
    public byte[] h(long j) throws IOException {
        a(j);
        return this.f2178a.h(j);
    }

    @Override // c.e
    public int a(byte[] bArr) throws IOException {
        return a(bArr, 0, bArr.length);
    }

    @Override // c.e
    public void b(byte[] bArr) throws IOException {
        try {
            a(bArr.length);
            this.f2178a.b(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (this.f2178a.f2132c > 0) {
                int iA = this.f2178a.a(bArr, i, (int) this.f2178a.f2132c);
                if (iA == -1) {
                    throw new AssertionError();
                }
                i += iA;
            }
            throw e;
        }
    }

    @Override // c.e
    public int a(byte[] bArr, int i, int i2) throws IOException {
        long j = i2;
        ab.a(bArr.length, i, j);
        if (this.f2178a.f2132c == 0 && this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.f2178a.a(bArr, i, (int) Math.min(j, this.f2178a.f2132c));
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        if (this.f2178a.f2132c == 0 && this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.f2178a.read(byteBuffer);
    }

    @Override // c.e
    public void b(c cVar, long j) throws IOException {
        try {
            a(j);
            this.f2178a.b(cVar, j);
        } catch (EOFException e) {
            cVar.a((y) this.f2178a);
            throw e;
        }
    }

    @Override // c.e
    public long a(x xVar) throws IOException {
        if (xVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        long j = 0;
        while (this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1) {
            long jI = this.f2178a.i();
            if (jI > 0) {
                j += jI;
                xVar.a_(this.f2178a, jI);
            }
        }
        if (this.f2178a.b() <= 0) {
            return j;
        }
        long jB = j + this.f2178a.b();
        xVar.a_(this.f2178a, this.f2178a.b());
        return jB;
    }

    @Override // c.e
    public String t() throws IOException {
        this.f2178a.a(this.f2179b);
        return this.f2178a.t();
    }

    @Override // c.e
    public String e(long j) throws IOException {
        a(j);
        return this.f2178a.e(j);
    }

    @Override // c.e
    public String a(Charset charset) throws IOException {
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        this.f2178a.a(this.f2179b);
        return this.f2178a.a(charset);
    }

    @Override // c.e
    public String a(long j, Charset charset) throws IOException {
        a(j);
        if (charset == null) {
            throw new IllegalArgumentException("charset == null");
        }
        return this.f2178a.a(j, charset);
    }

    @Override // c.e
    @Nullable
    public String u() throws IOException {
        long jA = a((byte) 10);
        if (jA != -1) {
            return this.f2178a.g(jA);
        }
        if (this.f2178a.f2132c != 0) {
            return e(this.f2178a.f2132c);
        }
        return null;
    }

    @Override // c.e
    public String v() throws IOException {
        return f(Long.MAX_VALUE);
    }

    @Override // c.e
    public String f(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jA = a((byte) 10, 0L, j2);
        if (jA != -1) {
            return this.f2178a.g(jA);
        }
        if (j2 < Long.MAX_VALUE && b(j2) && this.f2178a.c(j2 - 1) == 13 && b(1 + j2) && this.f2178a.c(j2) == 10) {
            return this.f2178a.g(j2);
        }
        c cVar = new c();
        this.f2178a.a(cVar, 0L, Math.min(32L, this.f2178a.b()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f2178a.b(), j) + " content=" + cVar.s().h() + UIUtils.ELLIPSIS_CHAR);
    }

    @Override // c.e
    public int w() throws IOException {
        a(1L);
        byte bC = this.f2178a.c(0L);
        if ((bC & 224) == 192) {
            a(2L);
        } else if ((bC & 240) == 224) {
            a(3L);
        } else if ((bC & 248) == 240) {
            a(4L);
        }
        return this.f2178a.w();
    }

    @Override // c.e
    public short k() throws IOException {
        a(2L);
        return this.f2178a.k();
    }

    @Override // c.e
    public short n() throws IOException {
        a(2L);
        return this.f2178a.n();
    }

    @Override // c.e
    public int l() throws IOException {
        a(4L);
        return this.f2178a.l();
    }

    @Override // c.e
    public int o() throws IOException {
        a(4L);
        return this.f2178a.o();
    }

    @Override // c.e
    public long m() throws IOException {
        a(8L);
        return this.f2178a.m();
    }

    @Override // c.e
    public long p() throws IOException {
        a(8L);
        return this.f2178a.p();
    }

    @Override // c.e
    public long q() throws IOException {
        byte bC;
        a(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!b(i2)) {
                break;
            }
            bC = this.f2178a.c(i);
            if ((bC < 48 || bC > 57) && !(i == 0 && bC == 45)) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9] or '-' character but was %#x", Byte.valueOf(bC)));
        }
        return this.f2178a.q();
    }

    @Override // c.e
    public long r() throws IOException {
        byte bC;
        a(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!b(i2)) {
                break;
            }
            bC = this.f2178a.c(i);
            if ((bC < 48 || bC > 57) && ((bC < 97 || bC > 102) && (bC < 65 || bC > 70))) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bC)));
        }
        return this.f2178a.r();
    }

    @Override // c.e
    public void i(long j) throws IOException {
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            if (this.f2178a.f2132c == 0 && this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f2178a.b());
            this.f2178a.i(jMin);
            j -= jMin;
        }
    }

    @Override // c.e
    public long a(byte b2) throws IOException {
        return a(b2, 0L, Long.MAX_VALUE);
    }

    @Override // c.e
    public long a(byte b2, long j) throws IOException {
        return a(b2, j, Long.MAX_VALUE);
    }

    @Override // c.e
    public long a(byte b2, long j, long j2) throws IOException {
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jA = this.f2178a.a(b2, j, j2);
            if (jA != -1) {
                return jA;
            }
            long j3 = this.f2178a.f2132c;
            if (j3 >= j2 || this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // c.e
    public long b(f fVar) throws IOException {
        return a(fVar, 0L);
    }

    @Override // c.e
    public long a(f fVar, long j) throws IOException {
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jA = this.f2178a.a(fVar, j);
            if (jA != -1) {
                return jA;
            }
            long j2 = this.f2178a.f2132c;
            if (this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, (j2 - fVar.k()) + 1);
        }
    }

    @Override // c.e
    public long c(f fVar) throws IOException {
        return b(fVar, 0L);
    }

    @Override // c.e
    public long b(f fVar, long j) throws IOException {
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        while (true) {
            long jB = this.f2178a.b(fVar, j);
            if (jB != -1) {
                return jB;
            }
            long j2 = this.f2178a.f2132c;
            if (this.f2179b.a(this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                return -1L;
            }
            j = Math.max(j, j2);
        }
    }

    @Override // c.e
    public boolean a(long j, f fVar) throws IOException {
        return a(j, fVar, 0, fVar.k());
    }

    @Override // c.e
    public boolean a(long j, f fVar, int i, int i2) throws IOException {
        if (this.f2180c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || i < 0 || i2 < 0 || fVar.k() - i < i2) {
            return false;
        }
        for (int i3 = 0; i3 < i2; i3++) {
            long j2 = i3 + j;
            if (!b(1 + j2) || this.f2178a.c(j2) != fVar.b(i + i3)) {
                return false;
            }
        }
        return true;
    }

    @Override // c.e
    public InputStream h() {
        return new InputStream() { // from class: c.t.1
            @Override // java.io.InputStream
            public int read() throws IOException {
                if (t.this.f2180c) {
                    throw new IOException("closed");
                }
                if (t.this.f2178a.f2132c == 0 && t.this.f2179b.a(t.this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return t.this.f2178a.j() & 255;
            }

            @Override // java.io.InputStream
            public int read(byte[] bArr, int i, int i2) throws IOException {
                if (t.this.f2180c) {
                    throw new IOException("closed");
                }
                ab.a(bArr.length, i, i2);
                if (t.this.f2178a.f2132c == 0 && t.this.f2179b.a(t.this.f2178a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                    return -1;
                }
                return t.this.f2178a.a(bArr, i, i2);
            }

            @Override // java.io.InputStream
            public int available() throws IOException {
                if (t.this.f2180c) {
                    throw new IOException("closed");
                }
                return (int) Math.min(t.this.f2178a.f2132c, 2147483647L);
            }

            @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                t.this.close();
            }

            public String toString() {
                return t.this + ".inputStream()";
            }
        };
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f2180c;
    }

    @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f2180c) {
            return;
        }
        this.f2180c = true;
        this.f2179b.close();
        this.f2178a.y();
    }

    @Override // c.y
    public z a() {
        return this.f2179b.a();
    }

    public String toString() {
        return "buffer(" + this.f2179b + ")";
    }
}
