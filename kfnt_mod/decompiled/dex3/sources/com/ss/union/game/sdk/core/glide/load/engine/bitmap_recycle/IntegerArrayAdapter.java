package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class IntegerArrayAdapter implements a<int[]> {
    private static final String a = "IntegerArrayPool";

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a
    public int getElementSizeInBytes() {
        return 4;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a
    public String getTag() {
        return a;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a
    public int getArrayLength(int[] iArr) {
        return iArr.length;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.a
    public int[] newArray(int i) {
        return new int[i];
    }
}
