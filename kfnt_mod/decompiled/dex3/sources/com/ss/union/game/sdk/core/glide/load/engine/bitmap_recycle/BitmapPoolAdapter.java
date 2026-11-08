package com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle;

import android.graphics.Bitmap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BitmapPoolAdapter implements BitmapPool {
    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public void clearMemory() {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public long getMaxSize() {
        return 0L;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public void setSizeMultiplier(float f) {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public void trimMemory(int i) {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public void put(Bitmap bitmap) {
        bitmap.recycle();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public Bitmap get(int i, int i2, Bitmap.Config config) {
        return Bitmap.createBitmap(i, i2, config);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool
    public Bitmap getDirty(int i, int i2, Bitmap.Config config) {
        return get(i, i2, config);
    }
}
