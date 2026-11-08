package com.ss.union.game.sdk.core.glide.load.resource.transcode;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapResource;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifDrawable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class DrawableBytesTranscoder implements ResourceTranscoder<Drawable, byte[]> {
    private final BitmapPool a;
    private final ResourceTranscoder<Bitmap, byte[]> b;
    private final ResourceTranscoder<GifDrawable, byte[]> c;

    /* JADX WARN: Multi-variable type inference failed */
    private static Resource<GifDrawable> a(Resource<Drawable> resource) {
        return resource;
    }

    public DrawableBytesTranscoder(BitmapPool bitmapPool, ResourceTranscoder<Bitmap, byte[]> resourceTranscoder, ResourceTranscoder<GifDrawable, byte[]> resourceTranscoder2) {
        this.a = bitmapPool;
        this.b = resourceTranscoder;
        this.c = resourceTranscoder2;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.resource.transcode.ResourceTranscoder
    public Resource<byte[]> transcode(Resource<Drawable> resource, Options options) {
        Drawable drawable = resource.get();
        if (drawable instanceof BitmapDrawable) {
            return this.b.transcode(BitmapResource.obtain(((BitmapDrawable) drawable).getBitmap(), this.a), options);
        }
        if (drawable instanceof GifDrawable) {
            return this.c.transcode(a(resource), options);
        }
        return null;
    }
}
