package com.ss.union.game.sdk.core.glide.provider;

import androidx.collection.ArrayMap;
import com.ss.union.game.sdk.core.glide.util.MultiClassKey;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ModelToResourceClassCache {
    private final AtomicReference<MultiClassKey> a = new AtomicReference<>();
    private final ArrayMap<MultiClassKey, List<Class<?>>> b = new ArrayMap<>();

    public List<Class<?>> get(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        List<Class<?>> list;
        MultiClassKey andSet = this.a.getAndSet(null);
        if (andSet == null) {
            andSet = new MultiClassKey(cls, cls2, cls3);
        } else {
            andSet.set(cls, cls2, cls3);
        }
        synchronized (this.b) {
            list = (List) this.b.get(andSet);
        }
        this.a.set(andSet);
        return list;
    }

    public void put(Class<?> cls, Class<?> cls2, Class<?> cls3, List<Class<?>> list) {
        synchronized (this.b) {
            this.b.put(new MultiClassKey(cls, cls2, cls3), list);
        }
    }

    public void clear() {
        synchronized (this.b) {
            this.b.clear();
        }
    }
}
