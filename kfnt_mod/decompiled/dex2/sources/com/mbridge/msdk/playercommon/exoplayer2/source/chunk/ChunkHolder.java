package com.mbridge.msdk.playercommon.exoplayer2.source.chunk;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class ChunkHolder {
    public Chunk chunk;
    public boolean endOfStream;

    public final void clear() {
        this.chunk = null;
        this.endOfStream = false;
    }
}
