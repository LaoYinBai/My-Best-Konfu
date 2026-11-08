package c;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class o implements y {

    /* renamed from: a, reason: collision with root package name */
    private final e f2157a;

    /* renamed from: b, reason: collision with root package name */
    private final Inflater f2158b;

    /* renamed from: c, reason: collision with root package name */
    private int f2159c;
    private boolean d;

    public o(y yVar, Inflater inflater) {
        this(p.a(yVar), inflater);
    }

    o(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f2157a = eVar;
        this.f2158b = inflater;
    }

    @Override // c.y
    public long a(c cVar, long j) throws DataFormatException, IOException {
        boolean zB;
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (this.d) {
            throw new IllegalStateException("closed");
        }
        if (j == 0) {
            return 0L;
        }
        do {
            zB = b();
            try {
                u uVarG = cVar.g(1);
                int iInflate = this.f2158b.inflate(uVarG.f2184c, uVarG.e, (int) Math.min(j, 8192 - uVarG.e));
                if (iInflate > 0) {
                    uVarG.e += iInflate;
                    long j2 = iInflate;
                    cVar.f2132c += j2;
                    return j2;
                }
                if (!this.f2158b.finished() && !this.f2158b.needsDictionary()) {
                }
                c();
                if (uVarG.d != uVarG.e) {
                    return -1L;
                }
                cVar.f2131b = uVarG.c();
                v.a(uVarG);
                return -1L;
            } catch (DataFormatException e) {
                throw new IOException(e);
            }
        } while (!zB);
        throw new EOFException("source exhausted prematurely");
    }

    public boolean b() throws IOException {
        if (!this.f2158b.needsInput()) {
            return false;
        }
        c();
        if (this.f2158b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f2157a.g()) {
            return true;
        }
        u uVar = this.f2157a.c().f2131b;
        this.f2159c = uVar.e - uVar.d;
        this.f2158b.setInput(uVar.f2184c, uVar.d, this.f2159c);
        return false;
    }

    private void c() throws IOException {
        if (this.f2159c == 0) {
            return;
        }
        int remaining = this.f2159c - this.f2158b.getRemaining();
        this.f2159c -= remaining;
        this.f2157a.i(remaining);
    }

    @Override // c.y
    public z a() {
        return this.f2157a.a();
    }

    @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.d) {
            return;
        }
        this.f2158b.end();
        this.d = true;
        this.f2157a.close();
    }
}
