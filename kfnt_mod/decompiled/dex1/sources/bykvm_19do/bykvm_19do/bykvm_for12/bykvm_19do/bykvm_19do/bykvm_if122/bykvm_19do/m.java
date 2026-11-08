package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class m implements d {

    /* renamed from: a, reason: collision with root package name */
    public final c f1481a = new c();

    /* renamed from: b, reason: collision with root package name */
    public final r f1482b;

    /* renamed from: c, reason: collision with root package name */
    boolean f1483c;

    m(r rVar) {
        if (rVar == null) {
            throw new NullPointerException("sink == null");
        }
        this.f1482b = rVar;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.e
    public c a() {
        return this.f1481a;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
    public void b(c cVar, long j) throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        this.f1481a.b(cVar, j);
        h();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f1483c) {
            return;
        }
        try {
            if (this.f1481a.f1464b > 0) {
                this.f1482b.b(this.f1481a, this.f1481a.f1464b);
            }
            th = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            this.f1482b.close();
        } catch (Throwable th2) {
            if (th == null) {
                th = th2;
            }
        }
        this.f1483c = true;
        if (th == null) {
            return;
        }
        u.a(th);
        throw null;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public d e(long j) throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        this.f1481a.e(j);
        return h();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d, bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Flushable
    public void flush() throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        c cVar = this.f1481a;
        long j = cVar.f1464b;
        if (j > 0) {
            this.f1482b.b(cVar, j);
        }
        this.f1482b.flush();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public d h() throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        long jK = this.f1481a.k();
        if (jK > 0) {
            this.f1482b.b(this.f1481a, jK);
        }
        return this;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.f1483c;
    }

    public String toString() {
        return "buffer(" + this.f1482b + ")";
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public d write(byte[] bArr) throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        this.f1481a.write(bArr);
        return h();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public d writeByte(int i) throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        this.f1481a.writeByte(i);
        return h();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public d writeInt(int i) throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        this.f1481a.writeInt(i);
        return h();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public d writeShort(int i) throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        this.f1481a.writeShort(i);
        return h();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public d a(String str) throws IOException {
        if (this.f1483c) {
            throw new IllegalStateException("closed");
        }
        this.f1481a.a(str);
        return h();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
    public t b() {
        return this.f1482b.b();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.d
    public d write(byte[] bArr, int i, int i2) throws IOException {
        if (!this.f1483c) {
            this.f1481a.write(bArr, i, i2);
            return h();
        }
        throw new IllegalStateException("closed");
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (!this.f1483c) {
            int iWrite = this.f1481a.write(byteBuffer);
            h();
            return iWrite;
        }
        throw new IllegalStateException("closed");
    }
}
