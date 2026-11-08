package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import com.ss.union.game.sdk.core.glide.Glide;
import com.ss.union.game.sdk.core.glide.load.engine.Initializable;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class LazyBitmapDrawableResource implements Initializable, Resource<BitmapDrawable> {
    private final Resources a;
    private final Resource<Bitmap> b;

    @Deprecated
    public static LazyBitmapDrawableResource obtain(Context context, Bitmap bitmap) {
        return (LazyBitmapDrawableResource) obtain(context.getResources(), BitmapResource.obtain(bitmap, Glide.get(context).getBitmapPool()));
    }

    @Deprecated
    public static LazyBitmapDrawableResource obtain(Resources resources, BitmapPool bitmapPool, Bitmap bitmap) {
        return (LazyBitmapDrawableResource) obtain(resources, BitmapResource.obtain(bitmap, bitmapPool));
    }

    public static Resource<BitmapDrawable> obtain(Resources resources, Resource<Bitmap> resource) {
        if (resource == null) {
            return null;
        }
        return new LazyBitmapDrawableResource(resources, resource);
    }

    private LazyBitmapDrawableResource(Resources resources, Resource<Bitmap> resource) {
        this.a = (Resources) Preconditions.checkNotNull(resources);
        this.b = (Resource) Preconditions.checkNotNull(resource);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Class<BitmapDrawable> getResourceClass() {
        return BitmapDrawable.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public BitmapDrawable get() {
        return new BitmapDrawable(this.a, this.b.get());
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public int getSize() {
        return this.b.getSize();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public void recycle() {
        this.b.recycle();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Initializable
    public void initialize() {
        if (this.b instanceof Initializable) {
            ((Initializable) this.b).initialize();
        }
    }
}
