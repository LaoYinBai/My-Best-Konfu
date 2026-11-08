package com.kwad.sdk.pngencrypt;

import com.kwad.sdk.pngencrypt.ChunkReader;
import java.io.Closeable;
import java.util.Arrays;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class b implements f, Closeable {
    static final /* synthetic */ boolean d = !b.class.desiredAssertionStatus();
    protected boolean a;
    protected boolean b;
    protected boolean c;
    private final byte[] e;
    private final int f;
    private byte[] g;
    private int h;
    private int i;
    private long j;
    private DeflatedChunksSet k;
    private ChunkReader l;
    private long m;
    private ErrorBehaviour n;

    public b() {
        this(n.a());
    }

    public b(byte[] bArr) {
        this.g = new byte[8];
        this.h = 0;
        this.a = false;
        this.b = false;
        this.c = false;
        this.i = 0;
        this.j = 0L;
        this.n = ErrorBehaviour.STRICT;
        this.e = bArr;
        this.f = this.e == null ? 0 : this.e.length;
        this.a = this.f <= 0;
    }

    @Override // com.kwad.sdk.pngencrypt.f
    public int a(byte[] bArr, int i, int i2) {
        int i3;
        long j;
        long j2;
        if (this.c) {
            return -1;
        }
        if (i2 == 0) {
            return 0;
        }
        if (i2 < 0) {
            com.kwad.sdk.core.d.a.a(new PngjException("This should not happen. Bad length: " + i2));
        }
        if (!this.a) {
            int i4 = this.f - this.h;
            if (i4 <= i2) {
                i2 = i4;
            }
            System.arraycopy(bArr, i, this.g, this.h, i2);
            this.h += i2;
            if (this.h == this.f) {
                a(this.g);
                this.h = 0;
                this.a = true;
            }
            i3 = i2 + 0;
            j = this.j;
            j2 = i2;
        } else {
            if (this.l == null || this.l.b()) {
                int i5 = 8 - this.h;
                if (i5 <= i2) {
                    i2 = i5;
                }
                System.arraycopy(bArr, i, this.g, this.h, i2);
                this.h += i2;
                i3 = i2 + 0;
                this.j += i2;
                if (this.h == 8) {
                    this.i++;
                    a(n.c(this.g, 0), com.kwad.sdk.pngencrypt.chunk.b.a(this.g, 4), this.j - 8);
                    this.h = 0;
                }
                return i3;
            }
            int iA = this.l.a(bArr, i, i2);
            if (iA < 0) {
                return -1;
            }
            i3 = iA + 0;
            j = this.j;
            j2 = iA;
        }
        this.j = j + j2;
        return i3;
    }

    public long a() {
        return this.j;
    }

    protected ChunkReader a(String str, int i, long j, boolean z) {
        return new ChunkReader(i, str, j, z ? ChunkReader.ChunkReaderMode.SKIP : ChunkReader.ChunkReaderMode.BUFFER) { // from class: com.kwad.sdk.pngencrypt.b.2
            @Override // com.kwad.sdk.pngencrypt.ChunkReader
            protected void a(int i2, byte[] bArr, int i3, int i4) {
                com.kwad.sdk.core.d.a.a(new PngjException("should never happen"));
            }

            @Override // com.kwad.sdk.pngencrypt.ChunkReader
            protected void c() {
                b.this.a(this);
            }
        };
    }

    protected abstract DeflatedChunksSet a(String str);

    protected void a(int i, String str, long j) {
        com.kwad.sdk.core.d.a.a("PNG_ENCRYPT", "New chunk: " + str + " " + i + " off:" + j);
        if (str.length() != 4 || !com.kwad.sdk.pngencrypt.chunk.b.e.matcher(str).matches()) {
            com.kwad.sdk.core.d.a.a(new PngjException("Bad chunk id: " + str));
        }
        if (i < 0) {
            com.kwad.sdk.core.d.a.a(new PngjException("Bad chunk len: " + i));
        }
        if (str.equals("IDAT")) {
            this.m += i;
        }
        boolean zB = b(i, str);
        boolean zA = a(i, str);
        boolean zB2 = b(str);
        boolean zA2 = (this.k == null || this.k.e()) ? false : this.k.a(str);
        if (!zB2 || zA) {
            this.l = a(str, i, j, zA);
        } else {
            if (!zA2) {
                if (this.k != null && !this.k.d()) {
                    com.kwad.sdk.core.d.a.a(new PngjException("new IDAT-like chunk when previous was not done"));
                }
                this.k = a(str);
            }
            this.l = new d(i, str, zB, j, this.k) { // from class: com.kwad.sdk.pngencrypt.b.1
                @Override // com.kwad.sdk.pngencrypt.d, com.kwad.sdk.pngencrypt.ChunkReader
                protected void c() {
                    super.c();
                    b.this.a(this);
                }
            };
        }
        if (this.l == null || zB) {
            return;
        }
        this.l.a(false);
    }

    protected void a(ChunkReader chunkReader) {
        String strD;
        if (this.i == 1 && (strD = d()) != null && !strD.equals(chunkReader.a().c)) {
            String str = "Bad first chunk: " + chunkReader.a().c + " expected: " + d();
            if (this.n.c < ErrorBehaviour.SUPER_LENIENT.c) {
                com.kwad.sdk.core.d.a.a(new PngjException(str));
            } else {
                com.kwad.sdk.core.d.a.a("PNG_ENCRYPT", str);
            }
        }
        if (e() == null || !chunkReader.a().c.equals(e())) {
            return;
        }
        this.b = true;
        close();
    }

    protected void a(byte[] bArr) {
        if (Arrays.equals(bArr, n.a())) {
            return;
        }
        com.kwad.sdk.core.d.a.a(new PngjException("Bad signature:" + Arrays.toString(bArr)));
    }

    protected boolean a(int i, String str) {
        return false;
    }

    @Override // com.kwad.sdk.pngencrypt.f
    public boolean b() {
        return this.b;
    }

    protected boolean b(int i, String str) {
        return true;
    }

    protected boolean b(String str) {
        return false;
    }

    public DeflatedChunksSet c() {
        return this.k;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.k != null) {
            this.k.f();
        }
        this.c = true;
    }

    protected String d() {
        return "IHDR";
    }

    protected String e() {
        return "IEND";
    }
}
