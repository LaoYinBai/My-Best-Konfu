package com.kwad.sdk.pngencrypt;

import com.kwad.sdk.pngencrypt.ChunkReader;
import com.kwad.sdk.pngencrypt.chunk.ChunkLoadBehaviour;
import com.kwad.sdk.pngencrypt.chunk.q;
import java.util.HashSet;
import java.util.Set;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends b {
    protected k e;
    protected k f;
    protected e g;
    protected final boolean j;
    protected int h = -1;
    protected com.kwad.sdk.pngencrypt.chunk.e i = null;
    private long k = 0;
    private boolean l = true;
    private boolean m = false;
    private Set<String> n = new HashSet();
    private long o = 0;
    private long p = 0;
    private long q = 0;
    private ChunkLoadBehaviour s = ChunkLoadBehaviour.LOAD_CHUNK_ALWAYS;
    private g r = new com.kwad.sdk.pngencrypt.chunk.a();

    public c(boolean z) {
        this.j = z;
    }

    private void e(String str) {
        int i;
        PngjException pngjException;
        if (str.equals("IHDR")) {
            if (this.h < 0) {
                i = 0;
                this.h = i;
                return;
            }
            pngjException = new PngjException("unexpected chunk " + str);
            com.kwad.sdk.core.d.a.a(pngjException);
        }
        if (str.equals("PLTE")) {
            if (this.h == 0 || this.h == 1) {
                i = 2;
                this.h = i;
                return;
            }
            pngjException = new PngjException("unexpected chunk here " + str);
            com.kwad.sdk.core.d.a.a(pngjException);
        }
        if (!str.equals("IDAT")) {
            if (str.equals("IEND")) {
                if (this.h >= 4) {
                    i = 6;
                } else {
                    pngjException = new PngjException("unexpected chunk " + str);
                }
            } else if (this.h <= 1) {
                this.h = 1;
                return;
            } else {
                if (this.h <= 3) {
                    this.h = 3;
                    return;
                }
                i = 5;
            }
            this.h = i;
            return;
        }
        if (this.h >= 0 && this.h <= 4) {
            this.h = 4;
            return;
        }
        pngjException = new PngjException("unexpected chunk " + str);
        com.kwad.sdk.core.d.a.a(pngjException);
    }

    @Override // com.kwad.sdk.pngencrypt.b, com.kwad.sdk.pngencrypt.f
    public int a(byte[] bArr, int i, int i2) {
        return super.a(bArr, i, i2);
    }

    @Override // com.kwad.sdk.pngencrypt.b
    protected DeflatedChunksSet a(String str) {
        return new j(str, this.j, l(), this.g);
    }

    @Override // com.kwad.sdk.pngencrypt.b
    protected void a(int i, String str, long j) {
        e(str);
        super.a(i, str, j);
    }

    public void a(long j) {
        this.o = j;
    }

    @Override // com.kwad.sdk.pngencrypt.b
    protected void a(ChunkReader chunkReader) {
        super.a(chunkReader);
        if (chunkReader.a().c.equals("IHDR")) {
            q qVar = new q(null);
            qVar.a(chunkReader.a());
            this.e = qVar.i();
            this.f = this.e;
            if (qVar.h()) {
                this.g = new e(this.f);
            }
            this.i = new com.kwad.sdk.pngencrypt.chunk.e(this.e);
        }
        if (chunkReader.a == ChunkReader.ChunkReaderMode.BUFFER && d(chunkReader.a().c)) {
            this.k += chunkReader.a().a;
        }
        if (chunkReader.a == ChunkReader.ChunkReaderMode.BUFFER || this.m) {
            try {
                this.i.a(this.r.a(chunkReader.a(), j()), this.h);
            } catch (PngjException e) {
                throw e;
            }
        }
        if (b()) {
            i();
        }
    }

    public void a(boolean z) {
        this.l = z;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Removed duplicated region for block: B:33:0x0091 A[RETURN] */
    @Override // com.kwad.sdk.pngencrypt.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean a(int r11, java.lang.String r12) {
        /*
            r10 = this;
            boolean r0 = super.a(r11, r12)
            r1 = 1
            if (r0 == 0) goto L8
            return r1
        L8:
            long r2 = r10.o
            r4 = 0
            int r0 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r0 <= 0) goto L4b
            long r2 = (long) r11
            long r6 = r10.a()
            long r2 = r2 + r6
            long r6 = r10.o
            int r0 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r0 <= 0) goto L4b
            com.kwad.sdk.pngencrypt.PngjException r0 = new com.kwad.sdk.pngencrypt.PngjException
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "Maximum total bytes to read exceeeded: "
            r2.append(r3)
            long r6 = r10.o
            r2.append(r6)
            java.lang.String r3 = " offset:"
            r2.append(r3)
            long r6 = r10.a()
            r2.append(r6)
            java.lang.String r3 = " len="
            r2.append(r3)
            r2.append(r11)
            java.lang.String r2 = r2.toString()
            r0.<init>(r2)
            com.kwad.sdk.core.d.a.a(r0)
        L4b:
            java.util.Set<java.lang.String> r0 = r10.n
            boolean r0 = r0.contains(r12)
            if (r0 == 0) goto L54
            return r1
        L54:
            boolean r0 = com.kwad.sdk.pngencrypt.chunk.b.b(r12)
            r2 = 0
            if (r0 == 0) goto L5c
            return r2
        L5c:
            long r6 = r10.p
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 <= 0) goto L6a
            long r6 = (long) r11
            long r8 = r10.p
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 <= 0) goto L6a
            return r1
        L6a:
            long r6 = r10.q
            int r0 = (r6 > r4 ? 1 : (r6 == r4 ? 0 : -1))
            if (r0 <= 0) goto L7b
            long r3 = (long) r11
            long r5 = r10.q
            long r7 = r10.k
            long r5 = r5 - r7
            int r11 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r11 <= 0) goto L7b
            return r1
        L7b:
            int[] r11 = com.kwad.sdk.pngencrypt.c.AnonymousClass1.a
            com.kwad.sdk.pngencrypt.chunk.ChunkLoadBehaviour r0 = r10.s
            int r0 = r0.ordinal()
            r11 = r11[r0]
            switch(r11) {
                case 1: goto L8a;
                case 2: goto L89;
                default: goto L88;
            }
        L88:
            goto L91
        L89:
            return r1
        L8a:
            boolean r11 = com.kwad.sdk.pngencrypt.chunk.b.d(r12)
            if (r11 != 0) goto L91
            return r1
        L91:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.pngencrypt.c.a(int, java.lang.String):boolean");
    }

    public void b(long j) {
        this.p = j;
    }

    @Override // com.kwad.sdk.pngencrypt.b
    protected boolean b(int i, String str) {
        return this.l;
    }

    @Override // com.kwad.sdk.pngencrypt.b
    protected boolean b(String str) {
        return str.equals("IDAT");
    }

    public void c(long j) {
        this.q = j;
    }

    public void c(String str) {
        this.n.add(str);
    }

    @Override // com.kwad.sdk.pngencrypt.b, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.h != 6) {
            this.h = 6;
        }
        super.close();
    }

    protected boolean d(String str) {
        return !com.kwad.sdk.pngencrypt.chunk.b.b(str);
    }

    public int f() {
        return this.h;
    }

    public boolean g() {
        return f() < 4;
    }

    public j h() {
        DeflatedChunksSet deflatedChunksSetC = c();
        if (deflatedChunksSetC instanceof j) {
            return (j) deflatedChunksSetC;
        }
        return null;
    }

    protected void i() {
    }

    public k j() {
        return this.e;
    }

    public e k() {
        return this.g;
    }

    public k l() {
        return this.f;
    }
}
