package com.kwad.sdk.pngencrypt.chunk;

import com.kwad.sdk.pngencrypt.PngjException;
import java.io.ByteArrayInputStream;
import java.util.zip.CRC32;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d {
    public final int a;
    public final byte[] b;
    public final String c;
    private CRC32 g;
    public byte[] d = null;
    private long f = 0;
    public byte[] e = new byte[4];

    public d(int i, String str, boolean z) {
        this.a = i;
        this.c = str;
        this.b = b.a(str);
        for (int i2 = 0; i2 < 4; i2++) {
            if (this.b[i2] < 65 || this.b[i2] > 122 || (this.b[i2] > 90 && this.b[i2] < 97)) {
                com.kwad.sdk.core.d.a.a(new PngjException("Bad id chunk: must be ascii letters " + str));
            }
        }
        if (z) {
            a();
        }
    }

    public void a() {
        if (this.d == null || this.d.length < this.a) {
            this.d = new byte[this.a];
        }
    }

    public void a(long j) {
        this.f = j;
    }

    public void a(boolean z) {
        int value = (int) this.g.getValue();
        int iC = com.kwad.sdk.pngencrypt.n.c(this.e, 0);
        if (value != iC) {
            String str = String.format("Bad CRC in chunk: %s (offset:%d). Expected:%x Got:%x", this.c, Long.valueOf(this.f), Integer.valueOf(iC), Integer.valueOf(value));
            if (z) {
                com.kwad.sdk.core.d.a.a(new PngjException(str));
            } else {
                com.kwad.sdk.core.d.a.a("PNG_ENCRYPT", str);
            }
        }
    }

    public void a(byte[] bArr, int i, int i2) {
        if (this.g == null) {
            this.g = new CRC32();
        }
        this.g.update(bArr, i, i2);
    }

    ByteArrayInputStream b() {
        return new ByteArrayInputStream(this.d);
    }

    public long c() {
        return this.f;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        d dVar = (d) obj;
        if (this.c == null) {
            if (dVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(dVar.c)) {
            return false;
        }
        return this.f == dVar.f;
    }

    public int hashCode() {
        return (((this.c == null ? 0 : this.c.hashCode()) + 31) * 31) + ((int) (this.f ^ (this.f >>> 32)));
    }

    public String toString() {
        return "chunkid=" + b.a(this.b) + " len=" + this.a;
    }
}
