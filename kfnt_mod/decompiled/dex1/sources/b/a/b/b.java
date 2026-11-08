package b.a.b;

import c.c;
import c.f;
import c.y;
import c.z;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b {

    /* renamed from: a, reason: collision with root package name */
    static final f f782a = f.a("OkHttp cache v1\n");

    /* renamed from: b, reason: collision with root package name */
    static final f f783b = f.a("OkHttp DIRTY :(\n");
    private static final int l = 1;
    private static final int m = 2;
    private static final long n = 32;

    /* renamed from: c, reason: collision with root package name */
    RandomAccessFile f784c;
    Thread d;
    y e;
    long g;
    boolean h;
    final long j;
    int k;
    private final f o;
    final c f = new c();
    final c i = new c();

    private b(RandomAccessFile randomAccessFile, y yVar, long j, f fVar, long j2) {
        this.f784c = randomAccessFile;
        this.e = yVar;
        this.h = yVar == null;
        this.g = j;
        this.o = fVar;
        this.j = j2;
    }

    public static b a(File file, y yVar, f fVar, long j) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        b bVar = new b(randomAccessFile, yVar, 0L, fVar, j);
        randomAccessFile.setLength(0L);
        bVar.a(f783b, -1L, -1L);
        return bVar;
    }

    public static b a(File file) throws IOException {
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
        b.a.b.a aVar = new b.a.b.a(randomAccessFile.getChannel());
        c cVar = new c();
        aVar.b(0L, cVar, 32L);
        if (!cVar.d(f782a.k()).equals(f782a)) {
            throw new IOException("unreadable cache file");
        }
        long jM = cVar.m();
        long jM2 = cVar.m();
        c cVar2 = new c();
        aVar.b(jM + 32, cVar2, jM2);
        return new b(randomAccessFile, null, jM, cVar2.s(), 0L);
    }

    private void a(f fVar, long j, long j2) throws IOException {
        c cVar = new c();
        cVar.g(fVar);
        cVar.q(j);
        cVar.q(j2);
        if (cVar.b() != 32) {
            throw new IllegalArgumentException();
        }
        new b.a.b.a(this.f784c.getChannel()).a(0L, cVar, 32L);
    }

    private void b(long j) throws IOException {
        c cVar = new c();
        cVar.g(this.o);
        new b.a.b.a(this.f784c.getChannel()).a(32 + j, cVar, this.o.k());
    }

    void a(long j) throws IOException {
        b(j);
        this.f784c.getChannel().force(false);
        a(f782a, j, this.o.k());
        this.f784c.getChannel().force(false);
        synchronized (this) {
            this.h = true;
        }
        b.a.c.a(this.e);
        this.e = null;
    }

    boolean a() {
        return this.f784c == null;
    }

    public f b() {
        return this.o;
    }

    public y c() {
        synchronized (this) {
            if (this.f784c == null) {
                return null;
            }
            this.k++;
            return new a();
        }
    }

    class a implements y {

        /* renamed from: b, reason: collision with root package name */
        private final z f786b = new z();

        /* renamed from: c, reason: collision with root package name */
        private b.a.b.a f787c;
        private long d;

        a() {
            this.f787c = new b.a.b.a(b.this.f784c.getChannel());
        }

        @Override // c.y
        public long a(c cVar, long j) throws IOException {
            long j2;
            char c2;
            if (this.f787c == null) {
                throw new IllegalStateException("closed");
            }
            synchronized (b.this) {
                while (true) {
                    long j3 = this.d;
                    j2 = b.this.g;
                    if (j3 == j2) {
                        if (!b.this.h) {
                            if (b.this.d != null) {
                                this.f786b.a(b.this);
                            } else {
                                b.this.d = Thread.currentThread();
                                c2 = 1;
                                break;
                            }
                        } else {
                            return -1L;
                        }
                    } else {
                        long jB = j2 - b.this.i.b();
                        if (this.d >= jB) {
                            long jMin = Math.min(j, j2 - this.d);
                            b.this.i.a(cVar, this.d - jB, jMin);
                            this.d += jMin;
                            return jMin;
                        }
                        c2 = 2;
                    }
                }
                if (c2 == 2) {
                    long jMin2 = Math.min(j, j2 - this.d);
                    this.f787c.b(this.d + 32, cVar, jMin2);
                    this.d += jMin2;
                    return jMin2;
                }
                try {
                    long jA = b.this.e.a(b.this.f, b.this.j);
                    if (jA == -1) {
                        b.this.a(j2);
                        synchronized (b.this) {
                            b.this.d = null;
                            b.this.notifyAll();
                        }
                        return -1L;
                    }
                    long jMin3 = Math.min(jA, j);
                    b.this.f.a(cVar, 0L, jMin3);
                    this.d += jMin3;
                    this.f787c.a(j2 + 32, b.this.f.clone(), jA);
                    synchronized (b.this) {
                        b.this.i.a_(b.this.f, jA);
                        if (b.this.i.b() > b.this.j) {
                            b.this.i.i(b.this.i.b() - b.this.j);
                        }
                        b.this.g += jA;
                    }
                    synchronized (b.this) {
                        b.this.d = null;
                        b.this.notifyAll();
                    }
                    return jMin3;
                } catch (Throwable th) {
                    synchronized (b.this) {
                        b.this.d = null;
                        b.this.notifyAll();
                        throw th;
                    }
                }
            }
        }

        @Override // c.y
        public z a() {
            return this.f786b;
        }

        @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.f787c == null) {
                return;
            }
            RandomAccessFile randomAccessFile = null;
            this.f787c = null;
            synchronized (b.this) {
                b bVar = b.this;
                bVar.k--;
                if (b.this.k == 0) {
                    RandomAccessFile randomAccessFile2 = b.this.f784c;
                    b.this.f784c = null;
                    randomAccessFile = randomAccessFile2;
                }
            }
            if (randomAccessFile != null) {
                b.a.c.a(randomAccessFile);
            }
        }
    }
}
