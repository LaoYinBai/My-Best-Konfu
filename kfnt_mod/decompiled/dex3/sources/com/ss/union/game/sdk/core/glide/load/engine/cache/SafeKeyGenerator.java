package com.ss.union.game.sdk.core.glide.load.engine.cache;

import androidx.core.util.Pools;
import com.ss.union.game.sdk.common.util.StringEncryptUtils;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.util.LruCache;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.Util;
import com.ss.union.game.sdk.core.glide.util.pool.FactoryPools;
import com.ss.union.game.sdk.core.glide.util.pool.StateVerifier;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class SafeKeyGenerator {
    private final LruCache<Key, String> a = new LruCache<>(1000);
    private final Pools.Pool<a> b = FactoryPools.threadSafe(10, new FactoryPools.Factory<a>() { // from class: com.ss.union.game.sdk.core.glide.load.engine.cache.SafeKeyGenerator.1
        @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Factory
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public a create() {
            try {
                return new a(MessageDigest.getInstance(StringEncryptUtils.SHA_256));
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
    });

    public String getSafeKey(Key key) {
        String strA;
        synchronized (this.a) {
            strA = this.a.get(key);
        }
        if (strA == null) {
            strA = a(key);
        }
        synchronized (this.a) {
            this.a.put(key, strA);
        }
        return strA;
    }

    private String a(Key key) {
        a aVar = (a) Preconditions.checkNotNull(this.b.acquire());
        try {
            key.updateDiskCacheKey(aVar.a);
            return Util.sha256BytesToHex(aVar.a.digest());
        } finally {
            this.b.release(aVar);
        }
    }

    private static final class a implements FactoryPools.Poolable {
        final MessageDigest a;
        private final StateVerifier b = StateVerifier.newInstance();

        a(MessageDigest messageDigest) {
            this.a = messageDigest;
        }

        @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Poolable
        public StateVerifier getVerifier() {
            return this.b;
        }
    }
}
