package com.ss.union.game.sdk.core.glide.util;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LruCache<T, Y> {
    private final Map<T, Y> a = new LinkedHashMap(100, 0.75f, true);
    private final long b;
    private long c;
    private long d;

    protected int getSize(Y y) {
        return 1;
    }

    protected void onItemEvicted(T t, Y y) {
    }

    public LruCache(long j) {
        this.b = j;
        this.c = j;
    }

    public synchronized void setSizeMultiplier(float f) {
        if (f < 0.0f) {
            throw new IllegalArgumentException("Multiplier must be >= 0");
        }
        this.c = Math.round(this.b * f);
        a();
    }

    protected synchronized int getCount() {
        return this.a.size();
    }

    public synchronized long getMaxSize() {
        return this.c;
    }

    public synchronized long getCurrentSize() {
        return this.d;
    }

    public synchronized boolean contains(T t) {
        return this.a.containsKey(t);
    }

    public synchronized Y get(T t) {
        return this.a.get(t);
    }

    public synchronized Y put(T t, Y y) {
        long size = getSize(y);
        if (size >= this.c) {
            onItemEvicted(t, y);
            return null;
        }
        if (y != null) {
            this.d += size;
        }
        Y yPut = this.a.put(t, y);
        if (yPut != null) {
            this.d -= getSize(yPut);
            if (!yPut.equals(y)) {
                onItemEvicted(t, yPut);
            }
        }
        a();
        return yPut;
    }

    public synchronized Y remove(T t) {
        Y yRemove;
        yRemove = this.a.remove(t);
        if (yRemove != null) {
            this.d -= getSize(yRemove);
        }
        return yRemove;
    }

    public void clearMemory() {
        trimToSize(0L);
    }

    protected synchronized void trimToSize(long j) {
        while (this.d > j) {
            Iterator<Map.Entry<T, Y>> it = this.a.entrySet().iterator();
            Map.Entry<T, Y> next = it.next();
            Y value = next.getValue();
            this.d -= getSize(value);
            T key = next.getKey();
            it.remove();
            onItemEvicted(key, value);
        }
    }

    private void a() {
        trimToSize(this.c);
    }
}
