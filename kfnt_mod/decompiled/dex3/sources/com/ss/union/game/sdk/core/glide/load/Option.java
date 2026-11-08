package com.ss.union.game.sdk.core.glide.load;

import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.security.MessageDigest;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class Option<T> {
    private static final CacheKeyUpdater<Object> a = new CacheKeyUpdater<Object>() { // from class: com.ss.union.game.sdk.core.glide.load.Option.1
        @Override // com.ss.union.game.sdk.core.glide.load.Option.CacheKeyUpdater
        public void update(byte[] bArr, Object obj, MessageDigest messageDigest) {
        }
    };
    private final T b;
    private final CacheKeyUpdater<T> c;
    private final String d;
    private volatile byte[] e;

    public interface CacheKeyUpdater<T> {
        void update(byte[] bArr, T t, MessageDigest messageDigest);
    }

    public static <T> Option<T> memory(String str) {
        return new Option<>(str, null, b());
    }

    public static <T> Option<T> memory(String str, T t) {
        return new Option<>(str, t, b());
    }

    public static <T> Option<T> disk(String str, CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, null, cacheKeyUpdater);
    }

    public static <T> Option<T> disk(String str, T t, CacheKeyUpdater<T> cacheKeyUpdater) {
        return new Option<>(str, t, cacheKeyUpdater);
    }

    private Option(String str, T t, CacheKeyUpdater<T> cacheKeyUpdater) {
        this.d = Preconditions.checkNotEmpty(str);
        this.b = t;
        this.c = (CacheKeyUpdater) Preconditions.checkNotNull(cacheKeyUpdater);
    }

    public T getDefaultValue() {
        return this.b;
    }

    public void update(T t, MessageDigest messageDigest) {
        this.c.update(a(), t, messageDigest);
    }

    private byte[] a() {
        if (this.e == null) {
            this.e = this.d.getBytes(Key.CHARSET);
        }
        return this.e;
    }

    public boolean equals(Object obj) {
        if (obj instanceof Option) {
            return this.d.equals(((Option) obj).d);
        }
        return false;
    }

    public int hashCode() {
        return this.d.hashCode();
    }

    private static <T> CacheKeyUpdater<T> b() {
        return (CacheKeyUpdater<T>) a;
    }

    public String toString() {
        return "Option{key='" + this.d + "'}";
    }
}
