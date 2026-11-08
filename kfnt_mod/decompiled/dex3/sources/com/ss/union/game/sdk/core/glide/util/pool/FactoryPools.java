package com.ss.union.game.sdk.core.glide.util.pool;

import android.util.Log;
import androidx.core.util.Pools;
import java.util.ArrayList;
import java.util.List;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class FactoryPools {
    private static final String a = "FactoryPools";
    private static final int b = 20;
    private static final Resetter<Object> c = new Resetter<Object>() { // from class: com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.1
        @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Resetter
        public void reset(Object obj) {
        }
    };

    public interface Factory<T> {
        T create();
    }

    public interface Poolable {
        StateVerifier getVerifier();
    }

    public interface Resetter<T> {
        void reset(T t);
    }

    private FactoryPools() {
    }

    public static <T extends Poolable> Pools.Pool<T> simple(int i, Factory<T> factory) {
        return a(new Pools.SimplePool(i), factory);
    }

    public static <T extends Poolable> Pools.Pool<T> threadSafe(int i, Factory<T> factory) {
        return a(new Pools.SynchronizedPool(i), factory);
    }

    public static <T> Pools.Pool<List<T>> threadSafeList() {
        return threadSafeList(20);
    }

    public static <T> Pools.Pool<List<T>> threadSafeList(int i) {
        return a(new Pools.SynchronizedPool(i), new Factory<List<T>>() { // from class: com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.2
            @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Factory
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public List<T> create() {
                return new ArrayList();
            }
        }, new Resetter<List<T>>() { // from class: com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.3
            @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Resetter
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public void reset(List<T> list) {
                list.clear();
            }
        });
    }

    private static <T extends Poolable> Pools.Pool<T> a(Pools.Pool<T> pool, Factory<T> factory) {
        return a(pool, factory, a());
    }

    private static <T> Pools.Pool<T> a(Pools.Pool<T> pool, Factory<T> factory, Resetter<T> resetter) {
        return new a(pool, factory, resetter);
    }

    private static <T> Resetter<T> a() {
        return (Resetter<T>) c;
    }

    private static final class a<T> implements Pools.Pool<T> {
        private final Factory<T> a;
        private final Resetter<T> b;
        private final Pools.Pool<T> c;

        a(Pools.Pool<T> pool, Factory<T> factory, Resetter<T> resetter) {
            this.c = pool;
            this.a = factory;
            this.b = resetter;
        }

        public T acquire() {
            T tCreate = (T) this.c.acquire();
            if (tCreate == null) {
                tCreate = this.a.create();
                if (Log.isLoggable(FactoryPools.a, 2)) {
                    Log.v(FactoryPools.a, "Created new " + tCreate.getClass());
                }
            }
            if (tCreate instanceof Poolable) {
                tCreate.getVerifier().a(false);
            }
            return (T) tCreate;
        }

        public boolean release(T t) {
            if (t instanceof Poolable) {
                ((Poolable) t).getVerifier().a(true);
            }
            this.b.reset(t);
            return this.c.release(t);
        }
    }
}
