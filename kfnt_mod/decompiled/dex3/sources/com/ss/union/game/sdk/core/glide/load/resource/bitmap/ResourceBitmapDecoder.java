package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.resource.drawable.ResourceDrawableDecoder;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ResourceBitmapDecoder implements ResourceDecoder<Uri, Bitmap> {
    private final ResourceDrawableDecoder a;
    private final BitmapPool b;

    public ResourceBitmapDecoder(ResourceDrawableDecoder resourceDrawableDecoder, BitmapPool bitmapPool) {
        this.a = resourceDrawableDecoder;
        this.b = bitmapPool;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(Uri uri, Options options) {
        return "android.resource".equals(uri.getScheme());
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<Bitmap> decode(Uri uri, int i, int i2, Options options) {
        Resource<Drawable> resourceDecode = this.a.decode(uri, i, i2, options);
        if (resourceDecode == null) {
            return null;
        }
        return a.a(this.b, resourceDecode.get(), i, i2);
    }
}
