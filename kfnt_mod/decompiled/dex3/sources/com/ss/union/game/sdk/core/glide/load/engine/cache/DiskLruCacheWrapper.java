package com.ss.union.game.sdk.core.glide.load.engine.cache;

import android.util.Log;
import com.ss.union.game.sdk.core.glide.disklrucache.DiskLruCache;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache;
import java.io.File;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DiskLruCacheWrapper implements DiskCache {
    private static final String a = "DiskLruCacheWrapper";
    private static final int b = 1;
    private static final int c = 1;
    private static DiskLruCacheWrapper d;
    private final File f;
    private final long g;
    private DiskLruCache i;
    private final a h = new a();
    private final SafeKeyGenerator e = new SafeKeyGenerator();

    @Deprecated
    public static synchronized DiskCache get(File file, long j) {
        if (d == null) {
            d = new DiskLruCacheWrapper(file, j);
        }
        return d;
    }

    public static DiskCache create(File file, long j) {
        return new DiskLruCacheWrapper(file, j);
    }

    @Deprecated
    protected DiskLruCacheWrapper(File file, long j) {
        this.f = file;
        this.g = j;
    }

    private synchronized DiskLruCache a() throws IOException {
        if (this.i == null) {
            this.i = DiskLruCache.open(this.f, 1, 1, this.g);
        }
        return this.i;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache
    public File get(Key key) {
        String safeKey = this.e.getSafeKey(key);
        if (Log.isLoggable(a, 2)) {
            Log.v(a, "Get: Obtained: " + safeKey + " for for Key: " + key);
        }
        try {
            DiskLruCache.Value value = a().get(safeKey);
            if (value != null) {
                return value.getFile(0);
            }
            return null;
        } catch (IOException e) {
            if (!Log.isLoggable(a, 5)) {
                return null;
            }
            Log.w(a, "Unable to get from disk cache", e);
            return null;
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache
    public void put(Key key, DiskCache.Writer writer) {
        DiskLruCache diskLruCacheA;
        String safeKey = this.e.getSafeKey(key);
        this.h.a(safeKey);
        try {
            if (Log.isLoggable(a, 2)) {
                Log.v(a, "Put: Obtained: " + safeKey + " for for Key: " + key);
            }
            try {
                diskLruCacheA = a();
            } catch (IOException e) {
                if (Log.isLoggable(a, 5)) {
                    Log.w(a, "Unable to put to disk cache", e);
                }
            }
            if (diskLruCacheA.get(safeKey) != null) {
                return;
            }
            DiskLruCache.Editor editorEdit = diskLruCacheA.edit(safeKey);
            if (editorEdit == null) {
                throw new IllegalStateException("Had two simultaneous puts for: " + safeKey);
            }
            try {
                if (writer.write(editorEdit.getFile(0))) {
                    editorEdit.commit();
                }
                editorEdit.abortUnlessCommitted();
            } catch (Throwable th) {
                editorEdit.abortUnlessCommitted();
                throw th;
            }
        } finally {
            this.h.b(safeKey);
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache
    public void delete(Key key) {
        try {
            a().remove(this.e.getSafeKey(key));
        } catch (IOException e) {
            if (Log.isLoggable(a, 5)) {
                Log.w(a, "Unable to delete from disk cache", e);
            }
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache
    public synchronized void clear() {
        try {
            try {
                a().delete();
            } catch (IOException e) {
                if (Log.isLoggable(a, 5)) {
                    Log.w(a, "Unable to clear disk cache or disk cache cleared externally", e);
                }
            }
        } finally {
            b();
        }
    }

    private synchronized void b() {
        this.i = null;
    }
}
