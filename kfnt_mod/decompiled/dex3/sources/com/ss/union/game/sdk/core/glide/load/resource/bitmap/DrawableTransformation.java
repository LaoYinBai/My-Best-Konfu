package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DrawableTransformation implements Transformation<Drawable> {
    private final Transformation<Bitmap> a;
    private final boolean b;

    public Transformation<BitmapDrawable> asBitmapDrawable() {
        return this;
    }

    public DrawableTransformation(Transformation<Bitmap> transformation, boolean z) {
        this.a = transformation;
        this.b = z;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Transformation
    public Resource<Drawable> transform(Context context, Resource<Drawable> resource, int i, int i2) {
        BitmapPool bitmapPool = Glide.get(context).getBitmapPool();
        Drawable drawable = resource.get();
        Resource<Bitmap> resourceA = a.a(bitmapPool, drawable, i, i2);
        if (resourceA == null) {
            if (!this.b) {
                return resource;
            }
            throw new IllegalArgumentException("Unable to convert " + drawable + " to a Bitmap");
        }
        Resource<Bitmap> resourceTransform = this.a.transform(context, resourceA, i, i2);
        if (resourceTransform.equals(resourceA)) {
            resourceTransform.recycle();
            return resource;
        }
        return a(context, resourceTransform);
    }

    private Resource<Drawable> a(Context context, Resource<Bitmap> resource) {
        return LazyBitmapDrawableResource.obtain(context.getResources(), resource);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof DrawableTransformation) {
            return this.a.equals(((DrawableTransformation) obj).a);
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
