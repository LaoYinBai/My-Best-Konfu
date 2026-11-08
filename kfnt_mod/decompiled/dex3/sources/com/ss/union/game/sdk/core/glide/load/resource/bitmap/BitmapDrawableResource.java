package com.ss.union.game.sdk.core.glide.load.resource.bitmap;

import android.graphics.drawable.BitmapDrawable;
import com.ss.union.game.sdk.core.glide.load.engine.Initializable;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.BitmapPool;
import com.ss.union.game.sdk.core.glide.load.resource.drawable.DrawableResource;
import com.ss.union.game.sdk.core.glide.util.Util;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class BitmapDrawableResource extends DrawableResource<BitmapDrawable> implements Initializable {
    private final BitmapPool a;

    public BitmapDrawableResource(BitmapDrawable bitmapDrawable, BitmapPool bitmapPool) {
        super(bitmapDrawable);
        this.a = bitmapPool;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Class<BitmapDrawable> getResourceClass() {
        return BitmapDrawable.class;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public int getSize() {
        return Util.getBitmapByteSize(((BitmapDrawable) this.drawable).getBitmap());
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public void recycle() {
        this.a.put(((BitmapDrawable) this.drawable).getBitmap());
    }

    @Override // com.ss.union.game.sdk.core.glide.load.resource.drawable.DrawableResource, com.ss.union.game.sdk.core.glide.load.engine.Initializable
    public void initialize() {
        ((BitmapDrawable) this.drawable).getBitmap().prepareToDraw();
    }
}
