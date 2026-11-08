package b.a.k;

import android.support.v4.media.session.PlaybackStateCompat;
import c.c;
import c.f;
import c.x;
import c.z;
import java.io.IOException;
import java.util.Random;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class d {

    /* renamed from: a, reason: collision with root package name */
    final boolean f982a;

    /* renamed from: b, reason: collision with root package name */
    final Random f983b;

    /* renamed from: c, reason: collision with root package name */
    final c.d f984c;
    final c.c d;
    boolean e;
    final c.c f = new c.c();
    final a g = new a();
    boolean h;
    private final byte[] i;
    private final c.a j;

    d(boolean z, c.d dVar, Random random) {
        if (dVar == null) {
            throw new NullPointerException("sink == null");
        }
        if (random == null) {
            throw new NullPointerException("random == null");
        }
        this.f982a = z;
        this.f984c = dVar;
        this.d = dVar.c();
        this.f983b = random;
        this.i = z ? new byte[4] : null;
        this.j = z ? new c.a() : null;
    }

    void a(f fVar) throws IOException {
        b(9, fVar);
    }

    void b(f fVar) throws IOException {
        b(10, fVar);
    }

    void a(int i, f fVar) throws IOException {
        f fVarS = f.f2139b;
        if (i != 0 || fVar != null) {
            if (i != 0) {
                b.b(i);
            }
            c.c cVar = new c.c();
            cVar.l(i);
            if (fVar != null) {
                cVar.g(fVar);
            }
            fVarS = cVar.s();
        }
        try {
            b(8, fVarS);
        } finally {
            this.e = true;
        }
    }

    private void b(int i, f fVar) throws IOException {
        if (this.e) {
            throw new IOException("closed");
        }
        int iK = fVar.k();
        if (iK > 125) {
            throw new IllegalArgumentException("Payload size must be less than or equal to 125");
        }
        this.d.m(i | 128);
        if (this.f982a) {
            this.d.m(iK | 128);
            this.f983b.nextBytes(this.i);
            this.d.d(this.i);
            if (iK > 0) {
                long jB = this.d.b();
                this.d.g(fVar);
                this.d.b(this.j);
                this.j.a(jB);
                b.a(this.j, this.i);
                this.j.close();
            }
        } else {
            this.d.m(iK);
            this.d.g(fVar);
        }
        this.f984c.flush();
    }

    x a(int i, long j) {
        if (this.h) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.h = true;
        this.g.f985a = i;
        this.g.f986b = j;
        this.g.f987c = true;
        this.g.d = false;
        return this.g;
    }

    void a(int i, long j, boolean z, boolean z2) throws IOException {
        if (this.e) {
            throw new IOException("closed");
        }
        if (!z) {
            i = 0;
        }
        if (z2) {
            i |= 128;
        }
        this.d.m(i);
        int i2 = this.f982a ? 128 : 0;
        if (j <= 125) {
            this.d.m(((int) j) | i2);
        } else if (j <= 65535) {
            this.d.m(i2 | 126);
            this.d.l((int) j);
        } else {
            this.d.m(i2 | 127);
            this.d.q(j);
        }
        if (this.f982a) {
            this.f983b.nextBytes(this.i);
            this.d.d(this.i);
            if (j > 0) {
                long jB = this.d.b();
                this.d.a_(this.f, j);
                this.d.b(this.j);
                this.j.a(jB);
                b.a(this.j, this.i);
                this.j.close();
            }
        } else {
            this.d.a_(this.f, j);
        }
        this.f984c.f();
    }

    final class a implements x {

        /* renamed from: a, reason: collision with root package name */
        int f985a;

        /* renamed from: b, reason: collision with root package name */
        long f986b;

        /* renamed from: c, reason: collision with root package name */
        boolean f987c;
        boolean d;

        a() {
        }

        @Override // c.x
        public void a_(c.c cVar, long j) throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            d.this.f.a_(cVar, j);
            boolean z = this.f987c && this.f986b != -1 && d.this.f.b() > this.f986b - PlaybackStateCompat.ACTION_PLAY_FROM_URI;
            long jI = d.this.f.i();
            if (jI <= 0 || z) {
                return;
            }
            d.this.a(this.f985a, jI, this.f987c, false);
            this.f987c = false;
        }

        @Override // c.x, java.io.Flushable
        public void flush() throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            d.this.a(this.f985a, d.this.f.b(), this.f987c, false);
            this.f987c = false;
        }

        @Override // c.x
        public z a() {
            return d.this.f984c.a();
        }

        @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            if (this.d) {
                throw new IOException("closed");
            }
            d.this.a(this.f985a, d.this.f.b(), this.f987c, true);
            this.d = true;
            d.this.h = false;
        }
    }
}
