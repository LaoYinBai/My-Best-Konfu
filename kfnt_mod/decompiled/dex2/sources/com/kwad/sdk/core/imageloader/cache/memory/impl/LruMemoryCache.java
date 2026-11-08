package com.kwad.sdk.core.imageloader.cache.memory.impl;

import com.kwad.sdk.core.imageloader.cache.memory.MemoryCache;
import com.kwad.sdk.core.imageloader.core.decode.DecodedResult;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class LruMemoryCache implements MemoryCache {
    private final LinkedHashMap<String, DecodedResult> map;
    private final int maxSize;
    private int size;

    public LruMemoryCache(int i) {
        if (i <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.maxSize = i;
        this.map = new LinkedHashMap<>(0, 0.75f, true);
    }

    private int sizeOf(String str, DecodedResult decodedResult) {
        return decodedResult.getByteSize();
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x006d, code lost:
    
        throw new java.lang.IllegalStateException(getClass().getName() + ".sizeOf() is reporting inconsistent results!");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private void trimToSize(int r4) {
        /*
            r3 = this;
        L0:
            monitor-enter(r3)
            int r0 = r3.size     // Catch: java.lang.Throwable -> L6e
            if (r0 < 0) goto L4f
            java.util.LinkedHashMap<java.lang.String, com.kwad.sdk.core.imageloader.core.decode.DecodedResult> r0 = r3.map     // Catch: java.lang.Throwable -> L6e
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L6e
            if (r0 == 0) goto L11
            int r0 = r3.size     // Catch: java.lang.Throwable -> L6e
            if (r0 != 0) goto L4f
        L11:
            int r0 = r3.size     // Catch: java.lang.Throwable -> L6e
            if (r0 <= r4) goto L30
            java.util.LinkedHashMap<java.lang.String, com.kwad.sdk.core.imageloader.core.decode.DecodedResult> r0 = r3.map     // Catch: java.lang.Throwable -> L6e
            boolean r0 = r0.isEmpty()     // Catch: java.lang.Throwable -> L6e
            if (r0 == 0) goto L1e
            goto L30
        L1e:
            java.util.LinkedHashMap<java.lang.String, com.kwad.sdk.core.imageloader.core.decode.DecodedResult> r0 = r3.map     // Catch: java.lang.Throwable -> L6e
            java.util.Set r0 = r0.entrySet()     // Catch: java.lang.Throwable -> L6e
            java.util.Iterator r0 = r0.iterator()     // Catch: java.lang.Throwable -> L6e
            java.lang.Object r0 = r0.next()     // Catch: java.lang.Throwable -> L6e
            java.util.Map$Entry r0 = (java.util.Map.Entry) r0     // Catch: java.lang.Throwable -> L6e
            if (r0 != 0) goto L32
        L30:
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L6e
            goto L4e
        L32:
            java.lang.Object r1 = r0.getKey()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r1 = (java.lang.String) r1     // Catch: java.lang.Throwable -> L6e
            java.lang.Object r0 = r0.getValue()     // Catch: java.lang.Throwable -> L6e
            com.kwad.sdk.core.imageloader.core.decode.DecodedResult r0 = (com.kwad.sdk.core.imageloader.core.decode.DecodedResult) r0     // Catch: java.lang.Throwable -> L6e
            java.util.LinkedHashMap<java.lang.String, com.kwad.sdk.core.imageloader.core.decode.DecodedResult> r2 = r3.map     // Catch: java.lang.Throwable -> L6e
            r2.remove(r1)     // Catch: java.lang.Throwable -> L6e
            int r2 = r3.size     // Catch: java.lang.Throwable -> L6e
            int r0 = r3.sizeOf(r1, r0)     // Catch: java.lang.Throwable -> L6e
            int r2 = r2 - r0
            r3.size = r2     // Catch: java.lang.Throwable -> L6e
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L6e
            goto L0
        L4e:
            return
        L4f:
            java.lang.IllegalStateException r4 = new java.lang.IllegalStateException     // Catch: java.lang.Throwable -> L6e
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L6e
            r0.<init>()     // Catch: java.lang.Throwable -> L6e
            java.lang.Class r1 = r3.getClass()     // Catch: java.lang.Throwable -> L6e
            java.lang.String r1 = r1.getName()     // Catch: java.lang.Throwable -> L6e
            r0.append(r1)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r1 = ".sizeOf() is reporting inconsistent results!"
            r0.append(r1)     // Catch: java.lang.Throwable -> L6e
            java.lang.String r0 = r0.toString()     // Catch: java.lang.Throwable -> L6e
            r4.<init>(r0)     // Catch: java.lang.Throwable -> L6e
            throw r4     // Catch: java.lang.Throwable -> L6e
        L6e:
            r4 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L6e
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwad.sdk.core.imageloader.cache.memory.impl.LruMemoryCache.trimToSize(int):void");
    }

    @Override // com.kwad.sdk.core.imageloader.cache.memory.MemoryCache
    public void clear() {
        trimToSize(-1);
    }

    @Override // com.kwad.sdk.core.imageloader.cache.memory.MemoryCache
    public final DecodedResult get(String str) {
        DecodedResult decodedResult;
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            decodedResult = this.map.get(str);
        }
        return decodedResult;
    }

    @Override // com.kwad.sdk.core.imageloader.cache.memory.MemoryCache
    public Collection<String> keys() {
        HashSet hashSet;
        synchronized (this) {
            hashSet = new HashSet(this.map.keySet());
        }
        return hashSet;
    }

    @Override // com.kwad.sdk.core.imageloader.cache.memory.MemoryCache
    public final boolean put(String str, DecodedResult decodedResult) {
        if (str == null || decodedResult == null) {
            throw new NullPointerException("key == null || value == null");
        }
        synchronized (this) {
            this.size += sizeOf(str, decodedResult);
            DecodedResult decodedResultPut = this.map.put(str, decodedResult);
            if (decodedResultPut != null) {
                this.size -= sizeOf(str, decodedResultPut);
            }
        }
        trimToSize(this.maxSize);
        return true;
    }

    @Override // com.kwad.sdk.core.imageloader.cache.memory.MemoryCache
    public final DecodedResult remove(String str) {
        DecodedResult decodedResultRemove;
        if (str == null) {
            throw new NullPointerException("key == null");
        }
        synchronized (this) {
            decodedResultRemove = this.map.remove(str);
            if (decodedResultRemove != null) {
                this.size -= sizeOf(str, decodedResultRemove);
            }
        }
        return decodedResultRemove;
    }

    public final synchronized String toString() {
        return String.format("LruCache[maxSize=%d]", Integer.valueOf(this.maxSize));
    }
}
