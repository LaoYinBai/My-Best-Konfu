package com.ss.union.game.sdk.core.glide.signature;

import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class ObjectKey implements Key {
    private final Object a;

    public ObjectKey(Object obj) {
        this.a = Preconditions.checkNotNull(obj);
    }

    public String toString() {
        return "ObjectKey{object=" + this.a + '}';
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof ObjectKey) {
            return this.a.equals(((ObjectKey) obj).a);
        }
        return false;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public int hashCode() {
        return this.a.hashCode();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(this.a.toString().getBytes(CHARSET));
    }
}
