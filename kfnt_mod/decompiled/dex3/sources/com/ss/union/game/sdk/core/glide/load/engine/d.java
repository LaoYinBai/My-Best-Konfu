package com.ss.union.game.sdk.core.glide.load.engine;

import com.ss.union.game.sdk.core.glide.load.Key;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class d implements Key {
    private final Key a;
    private final Key b;

    d(Key key, Key key2) {
        this.a = key;
        this.b = key2;
    }

    Key a() {
        return this.a;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public boolean equals(Object obj) {
        if (!(obj instanceof d)) {
            return false;
        }
        d dVar = (d) obj;
        return this.a.equals(dVar.a) && this.b.equals(dVar.b);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public int hashCode() {
        return (this.a.hashCode() * 31) + this.b.hashCode();
    }

    public String toString() {
        return "DataCacheKey{sourceKey=" + this.a + ", signature=" + this.b + '}';
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        this.a.updateDiskCacheKey(messageDigest);
        this.b.updateDiskCacheKey(messageDigest);
    }
}
