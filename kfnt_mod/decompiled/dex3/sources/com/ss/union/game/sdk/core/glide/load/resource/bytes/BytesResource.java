package com.ss.union.game.sdk.core.glide.load.resource.bytes;

import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BytesResource implements Resource<byte[]> {
    private final byte[] a;

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public void recycle() {
    }

    public BytesResource(byte[] bArr) {
        this.a = (byte[]) Preconditions.checkNotNull(bArr);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Class<byte[]> getResourceClass() {
        return byte[].class;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public byte[] get() {
        return this.a;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public int getSize() {
        return this.a.length;
    }
}
