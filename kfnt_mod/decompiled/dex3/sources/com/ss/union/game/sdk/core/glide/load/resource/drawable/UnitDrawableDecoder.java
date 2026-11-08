package com.ss.union.game.sdk.core.glide.load.resource.drawable;

import android.graphics.drawable.Drawable;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.ResourceDecoder;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UnitDrawableDecoder implements ResourceDecoder<Drawable, Drawable> {
    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public boolean handles(Drawable drawable, Options options) {
        return true;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.ResourceDecoder
    public Resource<Drawable> decode(Drawable drawable, int i, int i2, Options options) {
        return a.a(drawable);
    }
}
