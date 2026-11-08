package com.ss.union.game.sdk.core.glide.load.engine;

import android.util.Log;
import androidx.core.util.Pools;
import com.ss.union.game.sdk.core.glide.GlideContext;
import com.ss.union.game.sdk.core.glide.Priority;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.Options;
import com.ss.union.game.sdk.core.glide.load.Transformation;
import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCache;
import com.ss.union.game.sdk.core.glide.load.engine.cache.DiskCacheAdapter;
import com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache;
import com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor;
import com.ss.union.game.sdk.core.glide.load.engine.g;
import com.ss.union.game.sdk.core.glide.load.engine.l;
import com.ss.union.game.sdk.core.glide.request.ResourceCallback;
import com.ss.union.game.sdk.core.glide.util.Executors;
import com.ss.union.game.sdk.core.glide.util.LogTime;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.pool.FactoryPools;
import java.util.Map;
import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Engine implements MemoryCache.ResourceRemovedListener, i, l.a {
    private static final int b = 150;
    private final m d;
    private final k e;
    private final MemoryCache f;
    private final b g;
    private final q h;
    private final c i;
    private final a j;
    private final com.ss.union.game.sdk.core.glide.load.engine.a k;
    private static final String a = "Engine";
    private static final boolean c = Log.isLoggable(a, 2);

    public Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, boolean z) {
        this(memoryCache, factory, glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, null, null, null, null, null, null, z);
    }

    Engine(MemoryCache memoryCache, DiskCache.Factory factory, GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, m mVar, k kVar, com.ss.union.game.sdk.core.glide.load.engine.a aVar, b bVar, a aVar2, q qVar, boolean z) {
        this.f = memoryCache;
        this.i = new c(factory);
        com.ss.union.game.sdk.core.glide.load.engine.a aVar3 = aVar == null ? new com.ss.union.game.sdk.core.glide.load.engine.a(z) : aVar;
        this.k = aVar3;
        aVar3.a(this);
        this.e = kVar == null ? new k() : kVar;
        this.d = mVar == null ? new m() : mVar;
        this.g = bVar == null ? new b(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, this) : bVar;
        this.j = aVar2 == null ? new a(this.i) : aVar2;
        this.h = qVar == null ? new q() : qVar;
        memoryCache.setResourceRemovedListener(this);
    }

    public synchronized <R> LoadStatus load(GlideContext glideContext, Object obj, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, Options options, boolean z3, boolean z4, boolean z5, boolean z6, ResourceCallback resourceCallback, Executor executor) {
        long logTime = c ? LogTime.getLogTime() : 0L;
        j jVarA = this.e.a(obj, key, i, i2, map, cls, cls2, options);
        l<?> lVarA = a(jVarA, z3);
        if (lVarA != null) {
            resourceCallback.onResourceReady(lVarA, DataSource.MEMORY_CACHE);
            if (c) {
                a("Loaded resource from active resources", logTime, jVarA);
            }
            return null;
        }
        l<?> lVarB = b(jVarA, z3);
        if (lVarB != null) {
            resourceCallback.onResourceReady(lVarB, DataSource.MEMORY_CACHE);
            if (c) {
                a("Loaded resource from cache", logTime, jVarA);
            }
            return null;
        }
        h<?> hVarA = this.d.a(jVarA, z6);
        if (hVarA != null) {
            hVarA.a(resourceCallback, executor);
            if (c) {
                a("Added to existing load", logTime, jVarA);
            }
            return new LoadStatus(resourceCallback, hVarA);
        }
        h<R> hVarA2 = this.g.a(jVarA, z3, z4, z5, z6);
        g<R> gVarA = this.j.a(glideContext, obj, jVarA, key, i, i2, cls, cls2, priority, diskCacheStrategy, map, z, z2, z6, options, hVarA2);
        this.d.a((Key) jVarA, (h<?>) hVarA2);
        hVarA2.a(resourceCallback, executor);
        hVarA2.b(gVarA);
        if (c) {
            a("Started new load", logTime, jVarA);
        }
        return new LoadStatus(resourceCallback, hVarA2);
    }

    private static void a(String str, long j, Key key) {
        Log.v(a, str + " in " + LogTime.getElapsedMillis(j) + "ms, key: " + key);
    }

    private l<?> a(Key key, boolean z) {
        if (!z) {
            return null;
        }
        l<?> lVarB = this.k.b(key);
        if (lVarB != null) {
            lVarB.c();
        }
        return lVarB;
    }

    private l<?> b(Key key, boolean z) {
        if (!z) {
            return null;
        }
        l<?> lVarA = a(key);
        if (lVarA != null) {
            lVarA.c();
            this.k.a(key, lVarA);
        }
        return lVarA;
    }

    private l<?> a(Key key) {
        Resource<?> resourceRemove = this.f.remove(key);
        if (resourceRemove == null) {
            return null;
        }
        if (resourceRemove instanceof l) {
            return (l) resourceRemove;
        }
        return new l<>(resourceRemove, true, true);
    }

    public void release(Resource<?> resource) {
        if (resource instanceof l) {
            ((l) resource).d();
            return;
        }
        throw new IllegalArgumentException("Cannot release anything but an EngineResource");
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.i
    public synchronized void onEngineJobComplete(h<?> hVar, Key key, l<?> lVar) {
        if (lVar != null) {
            try {
                lVar.a(key, this);
                if (lVar.b()) {
                    this.k.a(key, lVar);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.d.b(key, hVar);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.i
    public synchronized void onEngineJobCancelled(h<?> hVar, Key key) {
        this.d.b(key, hVar);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.cache.MemoryCache.ResourceRemovedListener
    public void onResourceRemoved(Resource<?> resource) {
        this.h.a(resource);
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.l.a
    public synchronized void onResourceReleased(Key key, l<?> lVar) {
        this.k.a(key);
        if (lVar.b()) {
            this.f.put(key, lVar);
        } else {
            this.h.a(lVar);
        }
    }

    public void clearDiskCache() {
        this.i.a().clear();
    }

    public void shutdown() {
        this.g.a();
        this.i.b();
        this.k.b();
    }

    public class LoadStatus {
        private final h<?> b;
        private final ResourceCallback c;

        LoadStatus(ResourceCallback resourceCallback, h<?> hVar) {
            this.c = resourceCallback;
            this.b = hVar;
        }

        public void cancel() {
            synchronized (Engine.this) {
                this.b.c(this.c);
            }
        }
    }

    private static class c implements g.d {
        private final DiskCache.Factory a;
        private volatile DiskCache b;

        c(DiskCache.Factory factory) {
            this.a = factory;
        }

        synchronized void b() {
            if (this.b == null) {
                return;
            }
            this.b.clear();
        }

        @Override // com.ss.union.game.sdk.core.glide.load.engine.g.d
        public DiskCache a() {
            if (this.b == null) {
                synchronized (this) {
                    if (this.b == null) {
                        this.b = this.a.build();
                    }
                    if (this.b == null) {
                        this.b = new DiskCacheAdapter();
                    }
                }
            }
            return this.b;
        }
    }

    static class a {
        final g.d a;
        final Pools.Pool<g<?>> b = FactoryPools.threadSafe(150, new FactoryPools.Factory<g<?>>() { // from class: com.ss.union.game.sdk.core.glide.load.engine.Engine.a.1
            @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Factory
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public g<?> create() {
                return new g<>(a.this.a, a.this.b);
            }
        });
        private int c;

        a(g.d dVar) {
            this.a = dVar;
        }

        <R> g<R> a(GlideContext glideContext, Object obj, j jVar, Key key, int i, int i2, Class<?> cls, Class<R> cls2, Priority priority, DiskCacheStrategy diskCacheStrategy, Map<Class<?>, Transformation<?>> map, boolean z, boolean z2, boolean z3, Options options, g.a<R> aVar) {
            g gVar = (g) Preconditions.checkNotNull((g) this.b.acquire());
            int i3 = this.c;
            this.c = i3 + 1;
            return gVar.a(glideContext, obj, jVar, key, i, i2, cls, cls2, priority, diskCacheStrategy, map, z, z2, z3, options, aVar, i3);
        }
    }

    static class b {
        final GlideExecutor a;
        final GlideExecutor b;
        final GlideExecutor c;
        final GlideExecutor d;
        final i e;
        final Pools.Pool<h<?>> f = FactoryPools.threadSafe(150, new FactoryPools.Factory<h<?>>() { // from class: com.ss.union.game.sdk.core.glide.load.engine.Engine.b.1
            @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Factory
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public h<?> create() {
                return new h<>(b.this.a, b.this.b, b.this.c, b.this.d, b.this.e, b.this.f);
            }
        });

        b(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, i iVar) {
            this.a = glideExecutor;
            this.b = glideExecutor2;
            this.c = glideExecutor3;
            this.d = glideExecutor4;
            this.e = iVar;
        }

        void a() {
            Executors.shutdownAndAwaitTermination(this.a);
            Executors.shutdownAndAwaitTermination(this.b);
            Executors.shutdownAndAwaitTermination(this.c);
            Executors.shutdownAndAwaitTermination(this.d);
        }

        <R> h<R> a(Key key, boolean z, boolean z2, boolean z3, boolean z4) {
            return ((h) Preconditions.checkNotNull((h) this.f.acquire())).a(key, z, z2, z3, z4);
        }
    }
}
