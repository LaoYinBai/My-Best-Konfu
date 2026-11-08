package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BitmapDrawableDecoder<DataType> implements ResourceDecoder<DataType, BitmapDrawable> {
    private final ResourceDecoder<DataType, Bitmap> a;
    private final Resources b;

    public BitmapDrawableDecoder(Context context, ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        this(context.getResources(), resourceDecoder);
    }

    @Deprecated
    public BitmapDrawableDecoder(Resources resources, BitmapPool bitmapPool, ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        this(resources, resourceDecoder);
    }

    public BitmapDrawableDecoder(Resources resources, ResourceDecoder<DataType, Bitmap> resourceDecoder) {
        this.b = (Resources) Preconditions.checkNotNull(resources);
        this.a = (ResourceDecoder) Preconditions.checkNotNull(resourceDecoder);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(DataType datatype, Options options) throws IOException {
        return this.a.handles(datatype, options);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<BitmapDrawable> decode(DataType datatype, int i, int i2, Options options) throws IOException {
        return LazyBitmapDrawableResource.obtain(this.b, this.a.decode(datatype, i, i2, options));
    }
}
