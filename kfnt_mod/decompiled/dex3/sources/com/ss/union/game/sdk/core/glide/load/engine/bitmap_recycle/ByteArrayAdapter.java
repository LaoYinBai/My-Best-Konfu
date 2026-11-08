package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ByteArrayAdapter implements a<byte[]> {
    private static final String a = "ByteArrayPool";

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a
    public int getElementSizeInBytes() {
        return 1;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a
    public String getTag() {
        return a;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a
    public int getArrayLength(byte[] bArr) {
        return bArr.length;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a
    public byte[] newArray(int i) {
        return new byte[i];
    }
}
