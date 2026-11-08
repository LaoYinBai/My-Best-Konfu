package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k implements s {

    /* renamed from: a, reason: collision with root package name */
    private final e f1473a;

    /* renamed from: b, reason: collision with root package name */
    private final Inflater f1474b;

    /* renamed from: c, reason: collision with root package name */
    private int f1475c;
    private boolean d;

    k(e eVar, Inflater inflater) {
        if (eVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        if (inflater == null) {
            throw new IllegalArgumentException("inflater == null");
        }
        this.f1473a = eVar;
        this.f1474b = inflater;
    }

    private void i() throws IOException {
        int i = this.f1475c;
        if (i == 0) {
            return;
        }
        int remaining = i - this.f1474b.getRemaining();
        this.f1475c -= remaining;
        this.f1473a.skip(remaining);
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
    public long a(c cVar, long j) throws DataFormatException, IOException {
        boolean zH;
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
            zH = h();
            try {
                o oVarB = cVar.b(1);
                int iInflate = this.f1474b.inflate(oVarB.f1487a, oVarB.f1489c, (int) Math.min(j, 8192 - oVarB.f1489c));
                if (iInflate > 0) {
                    oVarB.f1489c += iInflate;
                    long j2 = iInflate;
                    cVar.f1464b += j2;
                    return j2;
                }
                if (!this.f1474b.finished() && !this.f1474b.needsDictionary()) {
                }
                i();
                if (oVarB.f1488b != oVarB.f1489c) {
                    return -1L;
                }
                cVar.f1463a = oVarB.b();
                p.a(oVarB);
                return -1L;
            } catch (DataFormatException e) {
                throw new IOException(e);
            }
        } while (!zH);
        throw new EOFException("source exhausted prematurely");
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
    public t b() {
        return this.f1473a.b();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        if (this.d) {
            return;
        }
        this.f1474b.end();
        this.d = true;
        this.f1473a.close();
    }

    public final boolean h() throws IOException {
        if (!this.f1474b.needsInput()) {
            return false;
        }
        i();
        if (this.f1474b.getRemaining() != 0) {
            throw new IllegalStateException("?");
        }
        if (this.f1473a.f()) {
            return true;
        }
        o oVar = this.f1473a.a().f1463a;
        int i = oVar.f1489c;
        int i2 = oVar.f1488b;
        this.f1475c = i - i2;
        this.f1474b.setInput(oVar.f1487a, i2, this.f1475c);
        return false;
    }
}
