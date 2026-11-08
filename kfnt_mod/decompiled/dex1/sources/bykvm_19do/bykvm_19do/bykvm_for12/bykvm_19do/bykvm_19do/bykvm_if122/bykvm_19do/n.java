package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import android.support.v4.media.session.PlaybackStateCompat;
import com.bytedance.common.utility.UIUtils;
import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class n implements e {

    /* renamed from: a, reason: collision with root package name */
    public final c f1484a = new c();

    /* renamed from: b, reason: collision with root package name */
    public final s f1485b;

    /* renamed from: c, reason: collision with root package name */
    boolean f1486c;

    n(s sVar) {
        if (sVar == null) {
            throw new NullPointerException("source == null");
        }
        this.f1485b = sVar;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public c a() {
        return this.f1484a;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public f b(long j) throws IOException {
        a(j);
        return this.f1484a.b(j);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public String c(long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("limit < 0: " + j);
        }
        long j2 = j == Long.MAX_VALUE ? Long.MAX_VALUE : j + 1;
        long jA = a((byte) 10, 0L, j2);
        if (jA != -1) {
            return this.f1484a.h(jA);
        }
        if (j2 < Long.MAX_VALUE && e(j2) && this.f1484a.f(j2 - 1) == 13 && e(1 + j2) && this.f1484a.f(j2) == 10) {
            return this.f1484a.h(j2);
        }
        c cVar = new c();
        c cVar2 = this.f1484a;
        cVar2.a(cVar, 0L, Math.min(32L, cVar2.o()));
        throw new EOFException("\\n not found: limit=" + Math.min(this.f1484a.o(), j) + " content=" + cVar.m().b() + UIUtils.ELLIPSIS_CHAR);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.f1486c) {
            return;
        }
        this.f1486c = true;
        this.f1485b.close();
        this.f1484a.j();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public byte[] d(long j) throws IOException {
        a(j);
        return this.f1484a.d(j);
    }

    public boolean e(long j) throws IOException {
        c cVar;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f1486c) {
            throw new IllegalStateException("closed");
        }
        do {
            cVar = this.f1484a;
            if (cVar.f1464b >= j) {
                return true;
            }
        } while (this.f1485b.a(cVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) != -1);
        return false;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public boolean f() throws IOException {
        if (this.f1486c) {
            throw new IllegalStateException("closed");
        }
        return this.f1484a.f() && this.f1485b.a(this.f1484a, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public long g() throws IOException {
        byte bF;
        a(1L);
        int i = 0;
        while (true) {
            int i2 = i + 1;
            if (!e(i2)) {
                break;
            }
            bF = this.f1484a.f(i);
            if ((bF < 48 || bF > 57) && ((bF < 97 || bF > 102) && (bF < 65 || bF > 70))) {
                break;
            }
            i = i2;
        }
        if (i == 0) {
            throw new NumberFormatException(String.format("Expected leading [0-9a-fA-F] character but was %#x", Byte.valueOf(bF)));
        }
        return this.f1484a.g();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f1486c;
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        c cVar = this.f1484a;
        if (cVar.f1464b == 0 && this.f1485b.a(cVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1;
        }
        return this.f1484a.read(byteBuffer);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public byte readByte() throws IOException {
        a(1L);
        return this.f1484a.readByte();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public void readFully(byte[] bArr) throws IOException {
        try {
            a(bArr.length);
            this.f1484a.readFully(bArr);
        } catch (EOFException e) {
            int i = 0;
            while (true) {
                c cVar = this.f1484a;
                long j = cVar.f1464b;
                if (j <= 0) {
                    throw e;
                }
                int iA = cVar.a(bArr, i, (int) j);
                if (iA == -1) {
                    throw new AssertionError();
                }
                i += iA;
            }
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public int readInt() throws IOException {
        a(4L);
        return this.f1484a.readInt();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public short readShort() throws IOException {
        a(2L);
        return this.f1484a.readShort();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public void skip(long j) throws IOException {
        if (this.f1486c) {
            throw new IllegalStateException("closed");
        }
        while (j > 0) {
            c cVar = this.f1484a;
            if (cVar.f1464b == 0 && this.f1485b.a(cVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                throw new EOFException();
            }
            long jMin = Math.min(j, this.f1484a.o());
            this.f1484a.skip(jMin);
            j -= jMin;
        }
    }

    public String toString() {
        return "buffer(" + this.f1485b + ")";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
    public long a(c cVar, long j) throws IOException {
        if (cVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.f1486c) {
            throw new IllegalStateException("closed");
        }
        c cVar2 = this.f1484a;
        if (cVar2.f1464b == 0 && this.f1485b.a(cVar2, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
            return -1L;
        }
        return this.f1484a.a(cVar, Math.min(j, this.f1484a.f1464b));
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
    public t b() {
        return this.f1485b.b();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public String d() throws IOException {
        return c(Long.MAX_VALUE);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public int e() throws IOException {
        a(4L);
        return this.f1484a.e();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public short c() throws IOException {
        a(2L);
        return this.f1484a.c();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public void a(long j) throws IOException {
        if (!e(j)) {
            throw new EOFException();
        }
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public String a(Charset charset) throws IOException {
        if (charset != null) {
            this.f1484a.a(this.f1485b);
            return this.f1484a.a(charset);
        }
        throw new IllegalArgumentException("charset == null");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public long a(byte b2) throws IOException {
        return a(b2, 0L, Long.MAX_VALUE);
    }

    public long a(byte b2, long j, long j2) throws IOException {
        if (this.f1486c) {
            throw new IllegalStateException("closed");
        }
        if (j < 0 || j2 < j) {
            throw new IllegalArgumentException(String.format("fromIndex=%s toIndex=%s", Long.valueOf(j), Long.valueOf(j2)));
        }
        while (j < j2) {
            long jA = this.f1484a.a(b2, j, j2);
            if (jA != -1) {
                return jA;
            }
            c cVar = this.f1484a;
            long j3 = cVar.f1464b;
            if (j3 >= j2 || this.f1485b.a(cVar, PlaybackStateCompat.ACTION_PLAY_FROM_URI) == -1) {
                break;
            }
            j = Math.max(j, j3);
        }
        return -1L;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public boolean a(long j, f fVar) throws IOException {
        return a(j, fVar, 0, fVar.e());
    }

    public boolean a(long j, f fVar, int i, int i2) throws IOException {
        if (!this.f1486c) {
            if (j < 0 || i < 0 || i2 < 0 || fVar.e() - i < i2) {
                return false;
            }
            for (int i3 = 0; i3 < i2; i3++) {
                long j2 = i3 + j;
                if (!e(1 + j2) || this.f1484a.f(j2) != fVar.a(i + i3)) {
                    return false;
                }
            }
            return true;
        }
        throw new IllegalStateException("closed");
    }
}
