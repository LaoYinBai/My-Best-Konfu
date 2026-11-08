package c;

import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class r {

    /* renamed from: a, reason: collision with root package name */
    final long f2167a;

    /* renamed from: c, reason: collision with root package name */
    boolean f2169c;
    boolean d;

    /* renamed from: b, reason: collision with root package name */
    final c f2168b = new c();
    private final x e = new a();
    private final y f = new b();

    public r(long j) {
        if (j < 1) {
            throw new IllegalArgumentException("maxBufferSize < 1: " + j);
        }
        this.f2167a = j;
    }

    public y a() {
        return this.f;
    }

    public x b() {
        return this.e;
    }

    final class a implements x {

        /* renamed from: a, reason: collision with root package name */
        final z f2170a = new z();

        a() {
        }

        @Override // c.x
        public void a_(c cVar, long j) throws IOException {
            synchronized (r.this.f2168b) {
                if (r.this.f2169c) {
                    throw new IllegalStateException("closed");
                }
                while (j > 0) {
                    if (r.this.d) {
                        throw new IOException("source is closed");
                    }
                    long jB = r.this.f2167a - r.this.f2168b.b();
                    if (jB == 0) {
                        this.f2170a.a(r.this.f2168b);
                    } else {
                        long jMin = Math.min(jB, j);
                        r.this.f2168b.a_(cVar, jMin);
                        j -= jMin;
                        r.this.f2168b.notifyAll();
                    }
                }
            }
        }

        @Override // c.x, java.io.Flushable
        public void flush() throws IOException {
            synchronized (r.this.f2168b) {
                if (r.this.f2169c) {
                    throw new IllegalStateException("closed");
                }
                if (r.this.d && r.this.f2168b.b() > 0) {
                    throw new IOException("source is closed");
                }
            }
        }

        @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (r.this.f2168b) {
                if (r.this.f2169c) {
                    return;
                }
                if (r.this.d && r.this.f2168b.b() > 0) {
                    throw new IOException("source is closed");
                }
                r.this.f2169c = true;
                r.this.f2168b.notifyAll();
            }
        }

        @Override // c.x
        public z a() {
            return this.f2170a;
        }
    }

    final class b implements y {

        /* renamed from: a, reason: collision with root package name */
        final z f2172a = new z();

        b() {
        }

        @Override // c.y
        public long a(c cVar, long j) throws IOException {
            synchronized (r.this.f2168b) {
                if (r.this.d) {
                    throw new IllegalStateException("closed");
                }
                while (r.this.f2168b.b() == 0) {
                    if (r.this.f2169c) {
                        return -1L;
                    }
                    this.f2172a.a(r.this.f2168b);
                }
                long jA = r.this.f2168b.a(cVar, j);
                r.this.f2168b.notifyAll();
                return jA;
            }
        }

        @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            synchronized (r.this.f2168b) {
                r.this.d = true;
                r.this.f2168b.notifyAll();
            }
        }

        @Override // c.y
        public z a() {
            return this.f2172a;
        }
    }
}
