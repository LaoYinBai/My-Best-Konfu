package com.ss.union.game.sdk.core.glide.load.engine;

import androidx.core.util.Pools;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.pool.FactoryPools;
import com.ss.union.game.sdk.core.glide.util.pool.StateVerifier;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class n<Z> implements Resource<Z>, FactoryPools.Poolable {
    private static final Pools.Pool<n<?>> a = FactoryPools.threadSafe(20, new FactoryPools.Factory<n<?>>() { // from class: com.ss.union.game.sdk.core.glide.load.engine.n.1
        @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Factory
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public n<?> create() {
            return new n<>();
        }
    });
    private final StateVerifier b = StateVerifier.newInstance();
    private Resource<Z> c;
    private boolean d;
    private boolean e;

    static <Z> n<Z> a(Resource<Z> resource) {
        n<Z> nVar = (n) Preconditions.checkNotNull((n) a.acquire());
        nVar.b(resource);
        return nVar;
    }

    n() {
    }

    private void b(Resource<Z> resource) {
        this.e = false;
        this.d = true;
        this.c = resource;
    }

    private void b() {
        this.c = null;
        a.release(this);
    }

    synchronized void a() {
        this.b.throwIfRecycled();
        if (!this.d) {
            throw new IllegalStateException("Already unlocked");
        }
        this.d = false;
        if (this.e) {
            recycle();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Class<Z> getResourceClass() {
        return this.c.getResourceClass();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public Z get() {
        return this.c.get();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public int getSize() {
        return this.c.getSize();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.Resource
    public synchronized void recycle() {
        this.b.throwIfRecycled();
        this.e = true;
        if (!this.d) {
            this.c.recycle();
            b();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Poolable
    public StateVerifier getVerifier() {
        return this.b;
    }
}
