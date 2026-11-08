package com.kwad.sdk.pngencrypt.chunk;

import com.liulishuo.filedownloader.model.FileDownloadStatus;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class ad extends aa {
    private int h;
    private int i;
    private int j;
    private int k;
    private int[] l;

    public ad(com.kwad.sdk.pngencrypt.k kVar) {
        super("tRNS", kVar);
        this.l = new int[0];
    }

    @Override // com.kwad.sdk.pngencrypt.chunk.PngChunk
    public void a(d dVar) {
        if (this.e.f) {
            this.h = com.kwad.sdk.pngencrypt.n.b(dVar.d, 0);
            return;
        }
        if (!this.e.g) {
            this.i = com.kwad.sdk.pngencrypt.n.b(dVar.d, 0);
            this.j = com.kwad.sdk.pngencrypt.n.b(dVar.d, 2);
            this.k = com.kwad.sdk.pngencrypt.n.b(dVar.d, 4);
        } else {
            int length = dVar.d.length;
            this.l = new int[length];
            for (int i = 0; i < length; i++) {
                this.l[i] = dVar.d[i] & FileDownloadStatus.error;
            }
        }
    }
}
