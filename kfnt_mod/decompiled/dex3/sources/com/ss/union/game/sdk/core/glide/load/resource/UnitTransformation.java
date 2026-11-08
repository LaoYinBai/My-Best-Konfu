package com.ss.union.game.sdk.core.glide.load.resource;

import android.content.Context;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class UnitTransformation<T> implements Transformation<T> {
    private static final Transformation<?> a = new UnitTransformation();

    @Override // com.ss.union.game.sdk.core.glide.load.Transformation
    public Resource<T> transform(Context context, Resource<T> resource, int i, int i2) {
        return resource;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
    }

    public static <T> UnitTransformation<T> get() {
        return (UnitTransformation) a;
    }

    private UnitTransformation() {
    }
}
