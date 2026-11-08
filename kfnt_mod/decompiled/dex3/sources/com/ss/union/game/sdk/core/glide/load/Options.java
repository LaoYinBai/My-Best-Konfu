package com.ss.union.game.sdk.core.glide.load;

import androidx.collection.ArrayMap;
import com.ss.union.game.sdk.core.glide.util.CachedHashCodeArrayMap;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Options implements Key {
    private final ArrayMap<Option<?>, Object> a = new CachedHashCodeArrayMap();

    public void putAll(Options options) {
        this.a.putAll(options.a);
    }

    public <T> Options set(Option<T> option, T t) {
        this.a.put(option, t);
        return this;
    }

    public <T> T get(Option<T> option) {
        return this.a.containsKey(option) ? (T) this.a.get(option) : option.getDefaultValue();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof Options) {
            return this.a.equals(((Options) obj).a);
        }
        return false;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        for (int i = 0; i < this.a.size(); i++) {
            a((Option) this.a.keyAt(i), this.a.valueAt(i), messageDigest);
        }
    }

    public String toString() {
        return "Options{values=" + this.a + '}';
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static <T> void a(Option<T> option, Object obj, MessageDigest messageDigest) {
        option.update(obj, messageDigest);
    }
}
