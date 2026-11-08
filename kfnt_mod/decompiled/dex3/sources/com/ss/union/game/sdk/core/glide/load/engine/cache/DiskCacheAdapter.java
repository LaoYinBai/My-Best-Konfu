package com.ss.union.game.sdk.core.glide.load.engine.cache;

import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DiskCacheAdapter implements DiskCache {
    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache
    public void clear() {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache
    public void delete(Key key) {
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache
    public File get(Key key) {
        return null;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache
    public void put(Key key, DiskCache.Writer writer) {
    }

    public static final class Factory implements DiskCache.Factory {
        @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache.Factory
        public DiskCache build() {
            return new DiskCacheAdapter();
        }
    }
}
