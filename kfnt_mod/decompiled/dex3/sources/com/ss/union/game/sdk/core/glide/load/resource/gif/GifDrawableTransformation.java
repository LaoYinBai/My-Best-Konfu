package com.ss.union.game.sdk.core.glide.load.resource.gif;

import android.content.Context;
import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.resource.bitmap.BitmapResource;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GifDrawableTransformation implements Transformation<GifDrawable> {
    private final Transformation<Bitmap> a;

    public GifDrawableTransformation(Transformation<Bitmap> transformation) {
        this.a = (Transformation) Preconditions.checkNotNull(transformation);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Transformation
    public Resource<GifDrawable> transform(Context context, Resource<GifDrawable> resource, int i, int i2) {
        GifDrawable gifDrawable = resource.get();
        Resource<Bitmap> bitmapResource = new BitmapResource(gifDrawable.getFirstFrame(), Glide.get(context).getBitmapPool());
        Resource<Bitmap> resourceTransform = this.a.transform(context, bitmapResource, i, i2);
        if (!bitmapResource.equals(resourceTransform)) {
            bitmapResource.recycle();
        }
        gifDrawable.setFrameTransformation(this.a, resourceTransform.get());
        return resource;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof GifDrawableTransformation) {
            return this.a.equals(((GifDrawableTransformation) obj).a);
        }
        return false;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.a.updateDiskCacheKey(messageDigest);
    }
}
