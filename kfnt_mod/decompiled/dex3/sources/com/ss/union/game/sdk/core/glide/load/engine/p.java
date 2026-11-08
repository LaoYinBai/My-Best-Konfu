package com.ss.union.game.sdk.core.glide.load.engine;

import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.bitmap_recycle.ArrayPool;
import com.ss.union.game.sdk.core.glide.util.LruCache;
import com.ss.union.game.sdk.core.glide.util.Util;
import java.nio.ByteBuffer;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class p implements Key {
    private static final LruCache<Class<?>, byte[]> a = new LruCache<>(50);
    private final ArrayPool b;
    private final Key c;
    private final Key d;
    private final int e;
    private final int f;
    private final Class<?> g;
    private final Options h;
    private final Transformation<?> i;

    p(ArrayPool arrayPool, Key key, Key key2, int i, int i2, Transformation<?> transformation, Class<?> cls, Options options) {
        this.b = arrayPool;
        this.c = key;
        this.d = key2;
        this.e = i;
        this.f = i2;
        this.i = transformation;
        this.g = cls;
        this.h = options;
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public boolean equals(Object obj) {
        if (!(obj instanceof p)) {
            return false;
        }
        p pVar = (p) obj;
        return this.f == pVar.f && this.e == pVar.e && Util.bothNullOrEqual(this.i, pVar.i) && this.g.equals(pVar.g) && this.c.equals(pVar.c) && this.d.equals(pVar.d) && this.h.equals(pVar.h);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public int hashCode() {
        int iHashCode = (((((this.c.hashCode() * 31) + this.d.hashCode()) * 31) + this.e) * 31) + this.f;
        if (this.i != null) {
            iHashCode = (iHashCode * 31) + this.i.hashCode();
        }
        return (((iHashCode * 31) + this.g.hashCode()) * 31) + this.h.hashCode();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.Key
    public void updateDiskCacheKey(MessageDigest messageDigest) {
        byte[] bArr = (byte[]) this.b.getExact(8, byte[].class);
        ByteBuffer.wrap(bArr).putInt(this.e).putInt(this.f).array();
        this.d.updateDiskCacheKey(messageDigest);
        this.c.updateDiskCacheKey(messageDigest);
        messageDigest.update(bArr);
        if (this.i != null) {
            this.i.updateDiskCacheKey(messageDigest);
        }
        this.h.updateDiskCacheKey(messageDigest);
        messageDigest.update(a());
        this.b.put(bArr);
    }

    private byte[] a() {
        byte[] bArr = a.get(this.g);
        if (bArr != null) {
            return bArr;
        }
        byte[] bytes = this.g.getName().getBytes(CHARSET);
        a.put(this.g, bytes);
        return bytes;
    }

    public String toString() {
        return "ResourceCacheKey{sourceKey=" + this.c + ", signature=" + this.d + ", width=" + this.e + ", height=" + this.f + ", decodedResourceClass=" + this.g + ", transformation='" + this.i + "', options=" + this.h + '}';
    }
}
