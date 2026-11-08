package com.ss.union.game.sdk.core.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class a extends DrawableResource<Drawable> {
    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public void recycle() {
    }

    static Resource<Drawable> a(Drawable drawable) {
        if (drawable != null) {
            return new a(drawable);
        }
        return null;
    }

    private a(Drawable drawable) {
        super(drawable);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Class<Drawable> getResourceClass() {
        return this.drawable.getClass();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public int getSize() {
        return Math.max(1, this.drawable.getIntrinsicWidth() * this.drawable.getIntrinsicHeight() * 4);
    }
}
