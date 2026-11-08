package com.ss.union.game.sdk.core.glide.load.engine;

import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.util.Preconditions;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class l<Z> implements Resource<Z> {
    private final boolean a;
    private final boolean b;
    private final Resource<Z> c;
    private a d;
    private Key e;
    private int f;
    private boolean g;

    interface a {
        void onResourceReleased(Key key, l<?> lVar);
    }

    l(Resource<Z> resource, boolean z, boolean z2) {
        this.c = (Resource) Preconditions.checkNotNull(resource);
        this.a = z;
        this.b = z2;
    }

    synchronized void a(Key key, a aVar) {
        this.e = key;
        this.d = aVar;
    }

    Resource<Z> a() {
        return this.c;
    }

    boolean b() {
        return this.a;
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
        if (this.f > 0) {
            throw new IllegalStateException("Cannot recycle a resource while it is still acquired");
        }
        if (this.g) {
            throw new IllegalStateException("Cannot recycle a resource that has already been recycled");
        }
        this.g = true;
        if (this.b) {
            this.c.recycle();
        }
    }

    synchronized void c() {
        if (this.g) {
            throw new IllegalStateException("Cannot acquire a recycled resource");
        }
        this.f++;
    }

    void d() {
        synchronized (this.d) {
            synchronized (this) {
                if (this.f <= 0) {
                    throw new IllegalStateException("Cannot release a recycled or not yet acquired resource");
                }
                int i = this.f - 1;
                this.f = i;
                if (i == 0) {
                    this.d.onResourceReleased(this.e, this);
                }
            }
        }
    }

    public synchronized String toString() {
        return "EngineResource{isCacheable=" + this.a + ", listener=" + this.d + ", key=" + this.e + ", acquired=" + this.f + ", isRecycled=" + this.g + ", resource=" + this.c + '}';
    }
}
