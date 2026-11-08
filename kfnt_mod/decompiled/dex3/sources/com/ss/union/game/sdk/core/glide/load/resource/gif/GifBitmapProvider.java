package com.ss.union.game.sdk.core.glide.load.resource.gif;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class GifBitmapProvider implements GifDecoder.BitmapProvider {
    private final BitmapPool a;
    private final ArrayPool b;

    public GifBitmapProvider(BitmapPool bitmapPool) {
        this(bitmapPool, null);
    }

    public GifBitmapProvider(BitmapPool bitmapPool, ArrayPool arrayPool) {
        this.a = bitmapPool;
        this.b = arrayPool;
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder.BitmapProvider
    public Bitmap obtain(int i, int i2, Bitmap.Config config) {
        return this.a.getDirty(i, i2, config);
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder.BitmapProvider
    public void release(Bitmap bitmap) {
        this.a.put(bitmap);
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder.BitmapProvider
    public byte[] obtainByteArray(int i) {
        if (this.b == null) {
            return new byte[i];
        }
        return (byte[]) this.b.get(i, byte[].class);
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder.BitmapProvider
    public void release(byte[] bArr) {
        if (this.b == null) {
            return;
        }
        this.b.put(bArr);
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder.BitmapProvider
    public int[] obtainIntArray(int i) {
        if (this.b == null) {
            return new int[i];
        }
        return (int[]) this.b.get(i, int[].class);
    }

    @Override // com.ss.union.game.sdk.core.glide.gifdecoder.GifDecoder.BitmapProvider
    public void release(int[] iArr) {
        if (this.b == null) {
            return;
        }
        this.b.put(iArr);
    }
}
