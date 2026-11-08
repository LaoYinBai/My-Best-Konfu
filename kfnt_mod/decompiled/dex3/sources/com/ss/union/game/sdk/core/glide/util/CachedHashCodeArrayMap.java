package com.ss.union.game.sdk.core.glide.util;

import androidx.collection.ArrayMap;
import androidx.collection.SimpleArrayMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class CachedHashCodeArrayMap<K, V> extends ArrayMap<K, V> {
    private int a;

    public void clear() {
        this.a = 0;
        super.clear();
    }

    public V setValueAt(int i, V v) {
        this.a = 0;
        return (V) super.setValueAt(i, v);
    }

    public V put(K k, V v) {
        this.a = 0;
        return (V) super.put(k, v);
    }

    public void putAll(SimpleArrayMap<? extends K, ? extends V> simpleArrayMap) {
        this.a = 0;
        super.putAll(simpleArrayMap);
    }

    public V removeAt(int i) {
        this.a = 0;
        return (V) super.removeAt(i);
    }

    public int hashCode() {
        if (this.a == 0) {
            this.a = super.hashCode();
        }
        return this.a;
    }
}
