package com.kwad.sdk.pngencrypt.chunk;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class PngChunk {
    public final String a;
    public final boolean b;
    public final boolean c;
    public final boolean d;
    protected final com.kwad.sdk.pngencrypt.k e;
    protected d f;
    private boolean h = false;
    protected int g = -1;

    public enum ChunkOrderingConstraint {
        NONE,
        BEFORE_PLTE_AND_IDAT,
        AFTER_PLTE_BEFORE_IDAT,
        AFTER_PLTE_BEFORE_IDAT_PLTE_REQUIRED,
        BEFORE_IDAT,
        AFTER_IDAT,
        NA;

        public boolean isOk(int i, boolean z) {
            if (this == NONE) {
                return true;
            }
            if (this == BEFORE_IDAT) {
                return i < 4;
            }
            if (this == BEFORE_PLTE_AND_IDAT) {
                return i < 2;
            }
            if (this != AFTER_PLTE_BEFORE_IDAT) {
                return this == AFTER_IDAT && i > 4;
            }
            if (z) {
                if (i < 4) {
                    return true;
                }
            } else if (i < 4 && i > 2) {
                return true;
            }
            return false;
        }

        public boolean mustGoAfterIDAT() {
            return this == AFTER_IDAT;
        }

        public boolean mustGoAfterPLTE() {
            return this == AFTER_PLTE_BEFORE_IDAT || this == AFTER_PLTE_BEFORE_IDAT_PLTE_REQUIRED;
        }

        public boolean mustGoBeforeIDAT() {
            return this == BEFORE_IDAT || this == BEFORE_PLTE_AND_IDAT || this == AFTER_PLTE_BEFORE_IDAT;
        }

        public boolean mustGoBeforePLTE() {
            return this == BEFORE_PLTE_AND_IDAT;
        }
    }

    public PngChunk(String str, com.kwad.sdk.pngencrypt.k kVar) {
        this.a = str;
        this.e = kVar;
        this.b = b.b(str);
        this.c = b.c(str);
        this.d = b.d(str);
    }

    public int a() {
        if (this.f != null) {
            return this.f.a;
        }
        return -1;
    }

    final void a(int i) {
        this.g = i;
    }

    protected abstract void a(d dVar);

    public long b() {
        if (this.f != null) {
            return this.f.c();
        }
        return -1L;
    }

    void b(d dVar) {
        this.f = dVar;
    }

    public String toString() {
        return "chunk id= " + this.a + " (len=" + a() + " offset=" + b() + ")";
    }
}
