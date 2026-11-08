package c;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class s implements d {

    /* renamed from: a, reason: collision with root package name */
    public final c f2174a = new c();

    /* renamed from: b, reason: collision with root package name */
    public final x f2175b;

    /* renamed from: c, reason: collision with root package name */
    boolean f2176c;

    s(x xVar) {
        if (xVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.f2175b = xVar;
    }

    @Override // c.d, c.e
    public c c() {
        return this.f2174a;
    }

    @Override // c.x
    public void a_(c cVar, long j) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.a_(cVar, j);
        I();
    }

    @Override // c.d
    public d g(f fVar) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.g(fVar);
        return I();
    }

    @Override // c.d
    public d b(String str) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.b(str);
        return I();
    }

    @Override // c.d
    public d b(String str, int i, int i2) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.b(str, i, i2);
        return I();
    }

    @Override // c.d
    public d n(int i) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.n(i);
        return I();
    }

    @Override // c.d
    public d b(String str, Charset charset) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.b(str, charset);
        return I();
    }

    @Override // c.d
    public d b(String str, int i, int i2, Charset charset) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.b(str, i, i2, charset);
        return I();
    }

    @Override // c.d
    public d d(byte[] bArr) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.d(bArr);
        return I();
    }

    @Override // c.d
    public d c(byte[] bArr, int i, int i2) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.c(bArr, i, i2);
        return I();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        int iWrite = this.f2174a.write(byteBuffer);
        I();
        return iWrite;
    }

    @Override // c.d
    public long a(y yVar) throws IOException {
        if (yVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        long j = 0;
        while (true) {
            long jA = yVar.a(this.f2174a, PlaybackStateCompat.ACTION_PLAY_FROM_URI);
            if (jA == -1) {
                return j;
            }
            j += jA;
            I();
        }
    }

    @Override // c.d
    public d a(y yVar, long j) throws IOException {
        while (j > 0) {
            long jA = yVar.a(this.f2174a, j);
            if (jA == -1) {
                throw new EOFException();
            }
            j -= jA;
            I();
        }
        return this;
    }

    @Override // c.d
    public d m(int i) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.m(i);
        return I();
    }

    @Override // c.d
    public d l(int i) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.l(i);
        return I();
    }

    @Override // c.d
    public d k(int i) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.k(i);
        return I();
    }

    @Override // c.d
    public d j(int i) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.j(i);
        return I();
    }

    @Override // c.d
    public d i(int i) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.i(i);
        return I();
    }

    @Override // c.d
    public d q(long j) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.q(j);
        return I();
    }

    @Override // c.d
    public d p(long j) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.p(j);
        return I();
    }

    @Override // c.d
    public d o(long j) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.o(j);
        return I();
    }

    @Override // c.d
    public d n(long j) throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        this.f2174a.n(j);
        return I();
    }

    @Override // c.d
    public d I() throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        long jI = this.f2174a.i();
        if (jI > 0) {
            this.f2175b.a_(this.f2174a, jI);
        }
        return this;
    }

    @Override // c.d
    public d f() throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        long jB = this.f2174a.b();
        if (jB > 0) {
            this.f2175b.a_(this.f2174a, jB);
        }
        return this;
    }

    @Override // c.d
    public OutputStream d() {
        return new OutputStream() { // from class: c.s.1
            @Override // java.io.OutputStream
            public void write(int i) throws IOException {
                if (s.this.f2176c) {
                    throw new IOException("closed");
                }
                s.this.f2174a.m((int) ((byte) i));
                s.this.I();
            }

            @Override // java.io.OutputStream
            public void write(byte[] bArr, int i, int i2) throws IOException {
                if (s.this.f2176c) {
                    throw new IOException("closed");
                }
                s.this.f2174a.c(bArr, i, i2);
                s.this.I();
            }

            @Override // java.io.OutputStream, java.io.Flushable
            public void flush() throws IOException {
                if (s.this.f2176c) {
                    return;
                }
                s.this.flush();
            }

            @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws Throwable {
                s.this.close();
            }

            public String toString() {
                return s.this + ".outputStream()";
            }
        };
    }

    @Override // c.d, c.x, java.io.Flushable
    public void flush() throws IOException {
        if (this.f2176c) {
            throw new IllegalStateException("closed");
        }
        if (this.f2174a.f2132c > 0) {
            this.f2175b.a_(this.f2174a, this.f2174a.f2132c);
        }
        this.f2175b.flush();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f2176c;
    }

    @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f2176c) {
            return;
        }
        Throwable th = null;
        try {
            if (this.f2174a.f2132c > 0) {
                this.f2175b.a_(this.f2174a, this.f2174a.f2132c);
            }
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f2175b.close();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        this.f2176c = true;
        if (th != null) {
            ab.a(th);
        }
    }

    @Override // c.x
    public z a() {
        return this.f2175b.a();
    }

    public String toString() {
        return "buffer(" + this.f2175b + ")";
    }
}
