package com.ss.union.game.sdk.core.glide.load.engine;

import com.ss.union.game.sdk.core.glide.load.Key;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class m {
    private final Map<Key, h<?>> a = new HashMap();
    private final Map<Key, h<?>> b = new HashMap();

    m() {
    }

    Map<Key, h<?>> a() {
        return Collections.unmodifiableMap(this.a);
    }

    h<?> a(Key key, boolean z) {
        return a(z).get(key);
    }

    void a(Key key, h<?> hVar) {
        a(hVar.a()).put(key, hVar);
    }

    void b(Key key, h<?> hVar) {
        Map<Key, h<?>> mapA = a(hVar.a());
        if (hVar.equals(mapA.get(key))) {
            mapA.remove(key);
        }
    }

    private Map<Key, h<?>> a(boolean z) {
        return z ? this.b : this.a;
    }
}
