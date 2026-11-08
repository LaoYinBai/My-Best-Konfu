package c;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class l implements y {

    /* renamed from: a, reason: collision with root package name */
    private static final byte f2150a = 1;

    /* renamed from: b, reason: collision with root package name */
    private static final byte f2151b = 2;

    /* renamed from: c, reason: collision with root package name */
    private static final byte f2152c = 3;
    private static final byte d = 4;
    private static final byte e = 0;
    private static final byte f = 1;
    private static final byte g = 2;
    private static final byte h = 3;
    private final e j;
    private final Inflater k;
    private final o l;
    private int i = 0;
    private final CRC32 m = new CRC32();

    public l(y yVar) {
        if (yVar == null) {
            throw new IllegalArgumentException("source == null");
        }
        this.k = new Inflater(true);
        this.j = p.a(yVar);
        this.l = new o(this.j, this.k);
    }

    @Override // c.y
    public long a(c cVar, long j) throws DataFormatException, IOException {
        if (j < 0) {
            throw new IllegalArgumentException("byteCount < 0: " + j);
        }
        if (j == 0) {
            return 0L;
        }
        if (this.i == 0) {
            b();
            this.i = 1;
        }
        if (this.i == 1) {
            long j2 = cVar.f2132c;
            long jA = this.l.a(cVar, j);
            if (jA != -1) {
                a(cVar, j2, jA);
                return jA;
            }
            this.i = 2;
        }
        if (this.i == 2) {
            c();
            this.i = 3;
            if (!this.j.g()) {
                throw new IOException("gzip finished without exhausting source");
            }
        }
        return -1L;
    }

    private void b() throws IOException {
        this.j.a(10L);
        byte bC = this.j.c().c(3L);
        boolean z = ((bC >> 1) & 1) == 1;
        if (z) {
            a(this.j.c(), 0L, 10L);
        }
        a("ID1ID2", 8075, this.j.k());
        this.j.i(8L);
        if (((bC >> 2) & 1) == 1) {
            this.j.a(2L);
            if (z) {
                a(this.j.c(), 0L, 2L);
            }
            long jN = this.j.c().n();
            this.j.a(jN);
            if (z) {
                a(this.j.c(), 0L, jN);
            }
            this.j.i(jN);
        }
        if (((bC >> 3) & 1) == 1) {
            long jA = this.j.a(e);
            if (jA == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.j.c(), 0L, jA + 1);
            }
            this.j.i(jA + 1);
        }
        if (((bC >> d) & 1) == 1) {
            long jA2 = this.j.a(e);
            if (jA2 == -1) {
                throw new EOFException();
            }
            if (z) {
                a(this.j.c(), 0L, jA2 + 1);
            }
            this.j.i(jA2 + 1);
        }
        if (z) {
            a("FHCRC", this.j.n(), (short) this.m.getValue());
            this.m.reset();
        }
    }

    private void c() throws IOException {
        a("CRC", this.j.o(), (int) this.m.getValue());
        a("ISIZE", this.j.o(), (int) this.k.getBytesWritten());
    }

    @Override // c.y
    public z a() {
        return this.j.a();
    }

    @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.l.close();
    }

    private void a(c cVar, long j, long j2) {
        u uVar = cVar.f2131b;
        while (j >= uVar.e - uVar.d) {
            j -= uVar.e - uVar.d;
            uVar = uVar.h;
        }
        while (j2 > 0) {
            int iMin = (int) Math.min(uVar.e - r6, j2);
            this.m.update(uVar.f2184c, (int) (uVar.d + j), iMin);
            j2 -= iMin;
            uVar = uVar.h;
            j = 0;
        }
    }

    private void a(String str, int i, int i2) throws IOException {
        if (i2 != i) {
            throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", str, Integer.valueOf(i2), Integer.valueOf(i)));
        }
    }
}
