package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class j implements s {

    /* renamed from: b, reason: collision with root package name */
    private final e f1471b;

    /* renamed from: c, reason: collision with root package name */
    private final Inflater f1472c;
    private final k d;

    /* renamed from: a, reason: collision with root package name */
    private int f1470a = 0;
    private final CRC32 e = new CRC32();

    public j(s sVar) {
        if (sVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.f1472c = new Inflater(true);
        this.f1471b = l.a(sVar);
        this.d = new k(this.f1471b, this.f1472c);
    }

    private void h() throws IOException {
        this.f1471b.a(10L);
        byte bF = this.f1471b.a().f(3L);
        boolean z = ((bF >> 1) & 1) == 1;
        if (z) {
            a(this.f1471b.a(), 0L, 10L);
        }
        a("ID1ID2", 8075, this.f1471b.readShort());
        this.f1471b.skip(8L);
        if (((bF >> 2) & 1) == 1) {
            this.f1471b.a(2L);
            if (z) {
                a(this.f1471b.a(), 0L, 2L);
            }
            long jC = this.f1471b.a().c();
            this.f1471b.a(jC);
            if (z) {
                a(this.f1471b.a(), 0L, jC);
            }
            this.f1471b.skip(jC);
        }
        if (((bF >> 3) & 1) == 1) {
            long jA = this.f1471b.a((byte) 0);
            if (jA == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.f1471b.a(), 0L, jA + 1);
            }
            this.f1471b.skip(jA + 1);
        }
        if (((bF >> 4) & 1) == 1) {
            long jA2 = this.f1471b.a((byte) 0);
            if (jA2 == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.f1471b.a(), 0L, jA2 + 1);
            }
            this.f1471b.skip(jA2 + 1);
        }
        if (z) {
            a("FHCRC", this.f1471b.c(), (short) this.e.getValue());
            this.e.reset();
        }
    }

    private void i() throws IOException {
        a("CRC", this.f1471b.e(), (int) this.e.getValue());
        a("ISIZE", this.f1471b.e(), (int) this.f1472c.getBytesWritten());
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
    public long a(c cVar, long j) throws DataFormatException, IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        if (this.f1470a == 0) {
            h();
            this.f1470a = 1;
        }
        if (this.f1470a == 1) {
            long j2 = cVar.f1464b;
            long jA = this.d.a(cVar, j);
            if (jA != -1) {
                a(cVar, j2, jA);
                return jA;
            }
            this.f1470a = 2;
        }
        if (this.f1470a == 2) {
            i();
            this.f1470a = 3;
            if (!this.f1471b.f()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
    public t b() {
        return this.f1471b.b();
    }

    @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.d.close();
    }

    private void a(c cVar, long j, long j2) {
        o oVar = cVar.f1463a;
        while (true) {
            long j3 = oVar.f1489c - oVar.f1488b;
            if (j < j3) {
                break;
            }
            j -= j3;
            oVar = oVar.f;
        }
        while (j2 > 0) {
            int iMin = (int) Math.min(oVar.f1489c - r6, j2);
            this.e.update(oVar.f1487a, (int) (oVar.f1488b + j), iMin);
            j2 -= iMin;
            oVar = oVar.f;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }
}
