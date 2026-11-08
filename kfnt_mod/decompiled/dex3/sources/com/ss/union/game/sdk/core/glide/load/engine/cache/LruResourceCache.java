package com.ss.union.game.sdk.core.glide.load.engine.cache;

import android.annotation.SuppressLint;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.engine.Resource;
import com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache;
import com.ss.union.game.sdk.core.glide.util.LruCache;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LruResourceCache extends LruCache<Key, Resource<?>> implements MemoryCache {
    private MemoryCache.ResourceRemovedListener a;

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public /* bridge */ /* synthetic */ Resource put(Key key, Resource resource) {
        return (Resource) super.put((LruResourceCache) key, (Key) resource);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public /* bridge */ /* synthetic */ Resource remove(Key key) {
        return (Resource) super.remove((LruResourceCache) key);
    }

    public LruResourceCache(long j) {
        super(j);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    public void setResourceRemovedListener(MemoryCache.ResourceRemovedListener resourceRemovedListener) {
        this.a = resourceRemovedListener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.core.glide.util.LruCache
    public void onItemEvicted(Key key, Resource<?> resource) {
        if (this.a == null || resource == null) {
            return;
        }
        this.a.onResourceRemoved(resource);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.ss.union.game.sdk.core.glide.util.LruCache
    public int getSize(Resource<?> resource) {
        if (resource == null) {
            return super.getSize((LruResourceCache) null);
        }
        return resource.getSize();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache
    @SuppressLint({"InlinedApi"})
    public void trimMemory(int i) {
        if (i >= 40) {
            clearMemory();
        } else if (i >= 20 || i == 15) {
            trimToSize(getMaxSize() / 2);
        }
    }
}
