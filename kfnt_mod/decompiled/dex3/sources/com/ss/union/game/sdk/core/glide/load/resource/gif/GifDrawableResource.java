package com.ss.union.game.sdk.core.glide.load.resource.gif;

import com.ss.union.game.sdk.core.glide.load.engine.Initializable;
import com.ss.union.game.sdk.core.glide.load.resource.drawable.DrawableResource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GifDrawableResource extends DrawableResource<GifDrawable> implements Initializable {
    public GifDrawableResource(GifDrawable gifDrawable) {
        super(gifDrawable);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Class<GifDrawable> getResourceClass() {
        return GifDrawable.class;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public int getSize() {
        return ((GifDrawable) this.drawable).getSize();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public void recycle() {
        ((GifDrawable) this.drawable).stop();
        ((GifDrawable) this.drawable).recycle();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.resource.drawable.DrawableResource, com.ss.union.game.sdk.core.glide.load.engine.Initializable
    public void initialize() {
        ((GifDrawable) this.drawable).getFirstFrame().prepareToDraw();
    }
}
