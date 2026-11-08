package com.ss.union.game.sdk.core.glide.load.engine.cache;

import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MemoryCacheAdapter implements MemoryCache {
    private MemoryCache.ResourceRemovedListener a;

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public void clearMemory() {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public long getCurrentSize() {
        return 0L;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public long getMaxSize() {
        return 0L;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public Resource<?> remove(Key key) {
        return null;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public void setSizeMultiplier(float f) {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public void trimMemory(int i) {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public Resource<?> put(Key key, Resource<?> resource) {
        if (resource == null) {
            return null;
        }
        this.a.onResourceRemoved(resource);
        return null;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public void setResourceRemovedListener(MemoryCache.ResourceRemovedListener resourceRemovedListener) {
        this.a = resourceRemovedListener;
    }
}
