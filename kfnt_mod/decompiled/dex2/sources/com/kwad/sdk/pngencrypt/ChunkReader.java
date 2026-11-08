package com.kwad.sdk.pngencrypt;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class ChunkReader implements f {
    public final ChunkReaderMode a;
    private final com.kwad.sdk.pngencrypt.chunk.d d;
    private boolean f;
    protected int b = 0;
    private int e = 0;
    protected ErrorBehaviour c = ErrorBehaviour.STRICT;

    public enum ChunkReaderMode {
        BUFFER,
        PROCESS,
        SKIP
    }

    public ChunkReader(int i, String str, long j, ChunkReaderMode chunkReaderMode) {
        if (chunkReaderMode == null || str.length() != 4 || i < 0) {
            com.kwad.sdk.core.d.a.a(new PngjException("Bad chunk paramenters: " + chunkReaderMode));
        }
        this.a = chunkReaderMode;
        this.d = new com.kwad.sdk.pngencrypt.chunk.d(i, str, chunkReaderMode == ChunkReaderMode.BUFFER);
        this.d.a(j);
        this.f = chunkReaderMode != ChunkReaderMode.SKIP;
    }

    @Override // com.kwad.sdk.pngencrypt.f
    public final int a(byte[] bArr, int i, int i2) {
        if (i2 == 0) {
            return 0;
        }
        if (i2 < 0) {
            com.kwad.sdk.core.d.a.a(new PngjException("negative length??"));
        }
        if (this.b == 0 && this.e == 0 && this.f) {
            this.d.a(this.d.b, 0, 4);
        }
        int i3 = this.d.a - this.b;
        if (i3 > i2) {
            i3 = i2;
        }
        if (i3 > 0 || this.e == 0) {
            if (this.f && this.a != ChunkReaderMode.BUFFER && i3 > 0) {
                this.d.a(bArr, i, i3);
            }
            if (this.a == ChunkReaderMode.BUFFER) {
                if (this.d.d != bArr && i3 > 0) {
                    System.arraycopy(bArr, i, this.d.d, this.b, i3);
                }
            } else if (this.a == ChunkReaderMode.PROCESS) {
                a(this.b, bArr, i, i3);
            }
            this.b += i3;
            i += i3;
            i2 -= i3;
        }
        if (this.b == this.d.a) {
            int i4 = 4 - this.e;
            if (i4 <= i2) {
                i2 = i4;
            }
            if (i2 > 0) {
                if (bArr != this.d.e) {
                    System.arraycopy(bArr, i, this.d.e, this.e, i2);
                }
                this.e += i2;
                if (this.e == 4) {
                    if (this.f) {
                        if (this.a == ChunkReaderMode.BUFFER) {
                            this.d.a(this.d.d, 0, this.d.a);
                        }
                        this.d.a(this.c == ErrorBehaviour.STRICT);
                    }
                    com.kwad.sdk.core.d.a.a("PNG_ENCRYPT", "Chunk done");
                    c();
                }
            }
        } else {
            i2 = 0;
        }
        if (i3 > 0 || i2 > 0) {
            return i3 + i2;
        }
        return -1;
    }

    public com.kwad.sdk.pngencrypt.chunk.d a() {
        return this.d;
    }

    protected abstract void a(int i, byte[] bArr, int i2, int i3);

    public void a(boolean z) {
        if (this.b != 0 && z && !this.f) {
            com.kwad.sdk.core.d.a.a(new PngjException("too late!"));
        }
        this.f = z;
    }

    @Override // com.kwad.sdk.pngencrypt.f
    public final boolean b() {
        return this.e == 4;
    }

    protected abstract void c();

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ChunkReader chunkReader = (ChunkReader) obj;
        if (this.d == null) {
            if (chunkReader.d != null) {
                return false;
            }
        } else if (!this.d.equals(chunkReader.d)) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return 31 + (this.d == null ? 0 : this.d.hashCode());
    }

    public String toString() {
        return this.d.toString();
    }
}
