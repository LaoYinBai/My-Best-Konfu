package com.ss.union.game.sdk.core.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapResource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class GifFrameResourceDecoder implements ResourceDecoder<GifDecoder, Bitmap> {
    private final BitmapPool a;

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(GifDecoder gifDecoder, Options options) {
        return true;
    }

    public GifFrameResourceDecoder(BitmapPool bitmapPool) {
        this.a = bitmapPool;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(GifDecoder gifDecoder, int i, int i2, Options options) {
        return BitmapResource.obtain(gifDecoder.getNextFrame(), this.a);
    }
}
