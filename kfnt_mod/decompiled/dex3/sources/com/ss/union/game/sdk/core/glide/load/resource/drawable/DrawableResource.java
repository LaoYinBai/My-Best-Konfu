package com.ss.union.game.sdk.core.glide.load.resource.drawable;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.load.engine.Initializable;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.resource.gif.GifDrawable;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class DrawableResource<T extends Drawable> implements Initializable, Resource<T> {
    protected final T drawable;

    public DrawableResource(T t) {
        this.drawable = (T) Preconditions.checkNotNull(t);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public final T get() {
        Drawable.ConstantState constantState = this.drawable.getConstantState();
        if (constantState == null) {
            return this.drawable;
        }
        return (T) constantState.newDrawable();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Initializable
    public void initialize() {
        if (this.drawable instanceof BitmapDrawable) {
            ((BitmapDrawable) this.drawable).getBitmap().prepareToDraw();
        } else if (this.drawable instanceof GifDrawable) {
            ((GifDrawable) this.drawable).getFirstFrame().prepareToDraw();
        }
    }
}
