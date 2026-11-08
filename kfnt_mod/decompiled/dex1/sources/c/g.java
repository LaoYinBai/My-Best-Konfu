package c;

import java.io.IOException;
import java.util.zip.Deflater;
import org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class g implements x {

    /* renamed from: a, reason: collision with root package name */
    private final d f2141a;

    /* renamed from: b, reason: collision with root package name */
    private final Deflater f2142b;

    /* renamed from: c, reason: collision with root package name */
    private boolean f2143c;

    public g(x xVar, Deflater deflater) {
        this(p.a(xVar), deflater);
    }

    g(d dVar, Deflater deflater) {
        if (dVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (deflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f2141a = dVar;
        this.f2142b = deflater;
    }

    @Override // c.x
    public void a_(c cVar, long j) throws IOException {
        ab.a(cVar.f2132c, 0L, j);
        while (j > 0) {
            u uVar = cVar.f2131b;
            int iMin = (int) Math.min(j, uVar.e - uVar.d);
            this.f2142b.setInput(uVar.f2184c, uVar.d, iMin);
            a(false);
            long j2 = iMin;
            cVar.f2132c -= j2;
            uVar.d += iMin;
            if (uVar.d == uVar.e) {
                cVar.f2131b = uVar.c();
                v.a(uVar);
            }
            j -= j2;
        }
    }

    @IgnoreJRERequirement
    private void a(boolean z) throws IOException {
        u uVarG;
        int iDeflate;
        c cVarC = this.f2141a.c();
        while (true) {
            uVarG = cVarC.g(1);
            if (z) {
                iDeflate = this.f2142b.deflate(uVarG.f2184c, uVarG.e, 8192 - uVarG.e, 2);
            } else {
                iDeflate = this.f2142b.deflate(uVarG.f2184c, uVarG.e, 8192 - uVarG.e);
            }
            if (iDeflate > 0) {
                uVarG.e += iDeflate;
                cVarC.f2132c += iDeflate;
                this.f2141a.I();
            } else if (this.f2142b.needsInput()) {
                break;
            }
        }
        if (uVarG.d == uVarG.e) {
            cVarC.f2131b = uVarG.c();
            v.a(uVarG);
        }
    }

    @Override // c.x, java.io.Flushable
    public void flush() throws IOException {
        a(true);
        this.f2141a.flush();
    }

    void b() throws IOException {
        this.f2142b.finish();
        a(false);
    }

    @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Throwable {
        if (this.f2143c) {
            return;
        }
        Throwable th = null;
        try {
            b();
        } catch (Throwable th2) {
            th = th2;
        }
        try {
            this.f2142b.end();
        } catch (Throwable th3) {
            if (th == null) {
                th = th3;
            }
        }
        try {
            this.f2141a.close();
        } catch (Throwable th4) {
            if (th == null) {
                th = th4;
            }
        }
        this.f2143c = true;
        if (th != null) {
            ab.a(th);
        }
    }

    @Override // c.x
    public z a() {
        return this.f2141a.a();
    }

    public String toString() {
        return "DeflaterSink(" + this.f2141a + ")";
    }
}
