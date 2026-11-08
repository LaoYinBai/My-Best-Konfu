package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.ss.union.game.sdk.core.glide.load.EncodeStrategy;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceEncoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BitmapDrawableEncoder implements ResourceEncoder<BitmapDrawable> {
    private final BitmapPool a;
    private final ResourceEncoder<Bitmap> b;

    public BitmapDrawableEncoder(BitmapPool bitmapPool, ResourceEncoder<Bitmap> resourceEncoder) {
        this.a = bitmapPool;
        this.b = resourceEncoder;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Encoder
    public boolean encode(Resource<BitmapDrawable> resource, File file, Options options) {
        return this.b.encode(new BitmapResource(resource.get().getBitmap(), this.a), file, options);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceEncoder
    public EncodeStrategy getEncodeStrategy(Options options) {
        return this.b.getEncodeStrategy(options);
    }
}
