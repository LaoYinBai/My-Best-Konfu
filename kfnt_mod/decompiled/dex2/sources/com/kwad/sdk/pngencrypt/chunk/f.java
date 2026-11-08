package com.kwad.sdk.pngencrypt.chunk;

import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class f extends e {
    private final List<PngChunk> d;

    @Override // com.kwad.sdk.pngencrypt.chunk.e
    public String toString() {
        return "ChunkList: written: " + a().size() + " queue: " + this.d.size();
    }
}
