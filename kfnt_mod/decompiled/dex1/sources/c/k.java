package c;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Deflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k implements x {

    /* renamed from: a, reason: collision with root package name */
    private final d f2147a;

    /* renamed from: b, reason: collision with root package name */
    private final Deflater f2148b;

    /* renamed from: c, reason: collision with root package name */
    private final g f2149c;
    private boolean d;
    private final CRC32 e = new CRC32();

    public k(x xVar) {
        if (xVar == null) {
            throw new IllegalArgumentException("sink == null");
        }
        this.f2148b = new Deflater(-1, true);
        this.f2147a = p.a(xVar);
        this.f2149c = new g(this.f2147a, this.f2148b);
        c();
    }

    @Override // c.x
    public void a_(c cVar, long j) throws IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return;
        }
        b(cVar, j);
        this.f2149c.a_(cVar, j);
    }

    @Override // c.x, java.io.Flushable
    public void flush() throws IOException {
        this.f2149c.flush();
    }

    @Override // c.x
    public z a() {
        return this.f2147a.a();
    }

    @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.d) {
            return;
        }
        Throwable th = null;
        try {
            this.f2149c.b();
            d();
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f2148b.end();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        try {
            this.f2147a.close();
        } catch (Throwable th4) {
            if (th == null) {
                th = th4;
            }
        }
        this.d = true;
        if (th != null) {
            ab.a(th);
        }
    }

    public Deflater b() {
        return this.f2148b;
    }

    private void c() {
        c cVarC = this.f2147a.c();
        cVarC.l(8075);
        cVarC.m(8);
        cVarC.m(0);
        cVarC.j(0);
        cVarC.m(0);
        cVarC.m(0);
    }

    private void d() throws IOException {
        this.f2147a.i((int) this.e.getValue());
        this.f2147a.i((int) this.f2148b.getBytesRead());
    }

    private void b(c cVar, long j) {
        u uVar = cVar.f2131b;
        while (j > 0) {
            int iMin = (int) Math.min(j, uVar.e - uVar.d);
            this.e.update(uVar.f2184c, uVar.d, iMin);
            j -= iMin;
            uVar = uVar.h;
        }
    }
}
