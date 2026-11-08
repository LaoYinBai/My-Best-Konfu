package com.ss.union.game.sdk.core.glide.provider;

import androidx.collection.ArrayMap;
import com.ss.union.game.sdk.core.glide.load.engine.DecodePath;
import com.ss.union.game.sdk.core.glide.load.engine.LoadPath;
import com.ss.union.game.sdk.core.glide.load.resource.transcode.UnitTranscoder;
import com.ss.union.game.sdk.core.glide.util.MultiClassKey;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicReference;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LoadPathCache {
    private static final LoadPath<?, ?, ?> a = new LoadPath<>(Object.class, Object.class, Object.class, Collections.singletonList(new DecodePath(Object.class, Object.class, Object.class, Collections.emptyList(), new UnitTranscoder(), null)), null);
    private final ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> b = new ArrayMap<>();
    private final AtomicReference<MultiClassKey> c = new AtomicReference<>();

    public boolean isEmptyLoadPath(LoadPath<?, ?, ?> loadPath) {
        return a.equals(loadPath);
    }

    public <Data, TResource, Transcode> LoadPath<Data, TResource, Transcode> get(Class<Data> cls, Class<TResource> cls2, Class<Transcode> cls3) {
        LoadPath<Data, TResource, Transcode> loadPath;
        MultiClassKey multiClassKeyA = a(cls, cls2, cls3);
        synchronized (this.b) {
            loadPath = (LoadPath) this.b.get(multiClassKeyA);
        }
        this.c.set(multiClassKeyA);
        return loadPath;
    }

    public void put(Class<?> cls, Class<?> cls2, Class<?> cls3, LoadPath<?, ?, ?> loadPath) {
        synchronized (this.b) {
            ArrayMap<MultiClassKey, LoadPath<?, ?, ?>> arrayMap = this.b;
            MultiClassKey multiClassKey = new MultiClassKey(cls, cls2, cls3);
            if (loadPath == null) {
                loadPath = a;
            }
            arrayMap.put(multiClassKey, loadPath);
        }
    }

    private MultiClassKey a(Class<?> cls, Class<?> cls2, Class<?> cls3) {
        MultiClassKey andSet = this.c.getAndSet(null);
        if (andSet == null) {
            andSet = new MultiClassKey();
        }
        andSet.set(cls, cls2, cls3);
        return andSet;
    }
}
