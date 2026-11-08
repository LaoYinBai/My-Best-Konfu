package com.kwad.sdk.pngencrypt.chunk;

import com.kwad.sdk.pngencrypt.PngjException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class x extends s {
    private String h;
    private int i;
    private int[] j;

    public x(com.kwad.sdk.pngencrypt.k kVar) {
        super("sPLT", kVar);
    }

    @Override // com.kwad.sdk.pngencrypt.chunk.PngChunk
    public void a(d dVar) {
        int i;
        int iA;
        int iA2;
        int iA3;
        int iA4;
        int i2 = 0;
        int i3 = 0;
        while (true) {
            if (i3 >= dVar.d.length) {
                i3 = -1;
                break;
            } else if (dVar.d[i3] == 0) {
                break;
            } else {
                i3++;
            }
        }
        if (i3 <= 0 || i3 > dVar.d.length - 2) {
            throw new PngjException("bad sPLT chunk: no separator found");
        }
        this.h = b.a(dVar.d, 0, i3);
        this.i = com.kwad.sdk.pngencrypt.n.a(dVar.d, i3 + 1);
        int i4 = i3 + 2;
        int length = (dVar.d.length - i4) / (this.i == 8 ? 6 : 10);
        this.j = new int[length * 5];
        int i5 = 0;
        while (i2 < length) {
            if (this.i == 8) {
                int i6 = i4 + 1;
                iA = com.kwad.sdk.pngencrypt.n.a(dVar.d, i4);
                int i7 = i6 + 1;
                iA2 = com.kwad.sdk.pngencrypt.n.a(dVar.d, i6);
                int i8 = i7 + 1;
                iA3 = com.kwad.sdk.pngencrypt.n.a(dVar.d, i7);
                i = i8 + 1;
                iA4 = com.kwad.sdk.pngencrypt.n.a(dVar.d, i8);
            } else {
                int iB = com.kwad.sdk.pngencrypt.n.b(dVar.d, i4);
                int i9 = i4 + 2;
                int iB2 = com.kwad.sdk.pngencrypt.n.b(dVar.d, i9);
                int i10 = i9 + 2;
                int iB3 = com.kwad.sdk.pngencrypt.n.b(dVar.d, i10);
                int i11 = i10 + 2;
                int iB4 = com.kwad.sdk.pngencrypt.n.b(dVar.d, i11);
                i = i11 + 2;
                iA = iB;
                iA2 = iB2;
                iA3 = iB3;
                iA4 = iB4;
            }
            int iB5 = com.kwad.sdk.pngencrypt.n.b(dVar.d, i);
            int i12 = i5 + 1;
            this.j[i5] = iA;
            int i13 = i12 + 1;
            this.j[i12] = iA2;
            int i14 = i13 + 1;
            this.j[i13] = iA3;
            int i15 = i14 + 1;
            this.j[i14] = iA4;
            this.j[i15] = iB5;
            i2++;
            i5 = i15 + 1;
            i4 = i + 2;
        }
    }

    public String c() {
        return this.h;
    }
}
