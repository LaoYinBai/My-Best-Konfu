package com.kwad.sdk.pngencrypt.chunk;

import com.kwad.sdk.pngencrypt.PngjException;
import com.liulishuo.filedownloader.model.FileDownloadStatus;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class n extends aa {
    private String h;
    private byte[] i;

    public n(com.kwad.sdk.pngencrypt.k kVar) {
        super("iCCP", kVar);
    }

    @Override // com.kwad.sdk.pngencrypt.chunk.PngChunk
    public void a(d dVar) {
        int iC = b.c(dVar.d);
        this.h = b.a(dVar.d, 0, iC);
        if ((dVar.d[iC + 1] & FileDownloadStatus.error) != 0) {
            throw new PngjException("bad compression for ChunkTypeICCP");
        }
        int i = iC + 2;
        int length = dVar.d.length - i;
        this.i = new byte[length];
        System.arraycopy(dVar.d, i, this.i, 0, length);
    }
}
