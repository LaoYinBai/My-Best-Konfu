package com.kwad.sdk.pngencrypt.chunk;

import com.kwad.sdk.pngencrypt.PngjException;
import com.liulishuo.filedownloader.model.FileDownloadStatus;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class v extends aa {
    private int h;
    private int[] i;

    public v(com.kwad.sdk.pngencrypt.k kVar) {
        super("PLTE", kVar);
        this.h = 0;
    }

    public void a(int i, int i2, int i3, int i4) {
        this.i[i] = (i2 << 16) | (i3 << 8) | i4;
    }

    @Override // com.kwad.sdk.pngencrypt.chunk.PngChunk
    public void a(d dVar) {
        b(dVar.a / 3);
        int i = 0;
        int i2 = 0;
        while (i < this.h) {
            int i3 = i2 + 1;
            int i4 = i3 + 1;
            a(i, dVar.d[i2] & FileDownloadStatus.error, dVar.d[i3] & FileDownloadStatus.error, dVar.d[i4] & FileDownloadStatus.error);
            i++;
            i2 = i4 + 1;
        }
    }

    public void b(int i) {
        this.h = i;
        if (this.h < 1 || this.h > 256) {
            throw new PngjException("invalid pallette - nentries=" + this.h);
        }
        if (this.i == null || this.i.length != this.h) {
            this.i = new int[this.h];
        }
    }
}
