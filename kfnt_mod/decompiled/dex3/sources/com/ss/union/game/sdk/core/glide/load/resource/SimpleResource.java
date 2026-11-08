package com.ss.union.game.sdk.core.glide.load.resource;

import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SimpleResource<T> implements Resource<T> {
    protected final T data;

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public final int getSize() {
        return 1;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public void recycle() {
    }

    public SimpleResource(T t) {
        this.data = (T) Preconditions.checkNotNull(t);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Class<T> getResourceClass() {
        return (Class<T>) this.data.getClass();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public final T get() {
        return this.data;
    }
}
