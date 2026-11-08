package com.ss.union.game.sdk.core.glide.signature;

import com.ss.union.game.sdk.core.glide.load.Key;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MediaStoreSignature implements Key {
    private final String a;
    private final long b;
    private final int c;

    public MediaStoreSignature(String str, long j, int i) {
        this.a = str == null ? "" : str;
        this.b = j;
        this.c = i;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaStoreSignature mediaStoreSignature = (MediaStoreSignature) obj;
        return this.b == mediaStoreSignature.b && this.c == mediaStoreSignature.c && this.a.equals(mediaStoreSignature.a);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public int hashCode() {
        return (((this.a.hashCode() * 31) + ((int) (this.b ^ (this.b >>> 32)))) * 31) + this.c;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        messageDigest.update(ByteBuffer.allocate(12).putLong(this.b).putInt(this.c).array());
        messageDigest.update(this.a.getBytes(CHARSET));
    }
}
