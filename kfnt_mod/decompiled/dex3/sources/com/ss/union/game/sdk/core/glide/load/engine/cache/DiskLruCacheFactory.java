package com.ss.union.game.sdk.core.glide.load.engine.cache;

import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DiskLruCacheFactory implements DiskCache.Factory {
    private final long a;
    private final CacheDirectoryGetter b;

    public interface CacheDirectoryGetter {
        File getCacheDirectory();
    }

    public DiskLruCacheFactory(final String str, long j) {
        this(new CacheDirectoryGetter() { // from class: com.ss.union.game.sdk.core.glide.load.engine.cache.DiskLruCacheFactory.1
            @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
            public File getCacheDirectory() {
                return new File(str);
            }
        }, j);
    }

    public DiskLruCacheFactory(final String str, final String str2, long j) {
        this(new CacheDirectoryGetter() { // from class: com.ss.union.game.sdk.core.glide.load.engine.cache.DiskLruCacheFactory.2
            @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter
            public File getCacheDirectory() {
                return new File(str, str2);
            }
        }, j);
    }

    public DiskLruCacheFactory(CacheDirectoryGetter cacheDirectoryGetter, long j) {
        this.a = j;
        this.b = cacheDirectoryGetter;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache.Factory
    public DiskCache build() {
        File cacheDirectory = this.b.getCacheDirectory();
        if (cacheDirectory == null) {
            return null;
        }
        if (cacheDirectory.mkdirs() || (cacheDirectory.exists() && cacheDirectory.isDirectory())) {
            return DiskLruCacheWrapper.create(cacheDirectory, this.a);
        }
        return null;
    }
}
