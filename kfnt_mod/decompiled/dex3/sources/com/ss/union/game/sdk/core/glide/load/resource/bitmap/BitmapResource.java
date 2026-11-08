package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.ss.union.game.sdk.core.glide.load.engine.Initializable;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BitmapResource implements Initializable, Resource<Bitmap> {
    private final Bitmap a;
    private final BitmapPool b;

    public static BitmapResource obtain(Bitmap bitmap, BitmapPool bitmapPool) {
        if (bitmap == null) {
            return null;
        }
        return new BitmapResource(bitmap, bitmapPool);
    }

    public BitmapResource(Bitmap bitmap, BitmapPool bitmapPool) {
        this.a = (Bitmap) Preconditions.checkNotNull(bitmap, "Bitmap must not be null");
        this.b = (BitmapPool) Preconditions.checkNotNull(bitmapPool, "BitmapPool must not be null");
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Class<Bitmap> getResourceClass() {
        return Bitmap.class;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Bitmap get() {
        return this.a;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public int getSize() {
        return Util.getBitmapByteSize(this.a);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public void recycle() {
        this.b.put(this.a);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Initializable
    public void initialize() {
        this.a.prepareToDraw();
    }
}
