package com.ss.union.game.sdk.core.glide.load.engine;

import androidx.core.util.Pools;
import com.ss.union.game.sdk.core.glide.load.DataSource;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor;
import com.ss.union.game.sdk.core.glide.load.engine.g;
import com.ss.union.game.sdk.core.glide.request.ResourceCallback;
import com.ss.union.game.sdk.core.glide.util.Executors;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import com.ss.union.game.sdk.core.glide.util.pool.FactoryPools;
import com.ss.union.game.sdk.core.glide.util.pool.StateVerifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class h<R> implements g.a<R>, FactoryPools.Poolable {
    private static final c e = new c();
    final e a;
    DataSource b;
    GlideException c;
    l<?> d;
    private final StateVerifier f;
    private final Pools.Pool<h<?>> g;
    private final c h;
    private final i i;
    private final GlideExecutor j;
    private final GlideExecutor k;
    private final GlideExecutor l;
    private final GlideExecutor m;
    private final AtomicInteger n;
    private Key o;
    private boolean p;
    private boolean q;
    private boolean r;
    private boolean s;
    private Resource<?> t;
    private boolean u;
    private boolean v;
    private g<R> w;
    private volatile boolean x;

    h(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, i iVar, Pools.Pool<h<?>> pool) {
        this(glideExecutor, glideExecutor2, glideExecutor3, glideExecutor4, iVar, pool, e);
    }

    h(GlideExecutor glideExecutor, GlideExecutor glideExecutor2, GlideExecutor glideExecutor3, GlideExecutor glideExecutor4, i iVar, Pools.Pool<h<?>> pool, c cVar) {
        this.a = new e();
        this.f = StateVerifier.newInstance();
        this.n = new AtomicInteger();
        this.j = glideExecutor;
        this.k = glideExecutor2;
        this.l = glideExecutor3;
        this.m = glideExecutor4;
        this.i = iVar;
        this.g = pool;
        this.h = cVar;
    }

    synchronized h<R> a(Key key, boolean z, boolean z2, boolean z3, boolean z4) {
        this.o = key;
        this.p = z;
        this.q = z2;
        this.r = z3;
        this.s = z4;
        return this;
    }

    public synchronized void b(g<R> gVar) {
        this.w = gVar;
        (gVar.a() ? this.j : g()).execute(gVar);
    }

    synchronized void a(ResourceCallback resourceCallback, Executor executor) {
        this.f.throwIfRecycled();
        this.a.a(resourceCallback, executor);
        if (this.u) {
            a(1);
            executor.execute(new b(resourceCallback));
        } else if (this.v) {
            a(1);
            executor.execute(new a(resourceCallback));
        } else {
            Preconditions.checkArgument(!this.x, "Cannot add callbacks to a cancelled EngineJob");
        }
    }

    synchronized void a(ResourceCallback resourceCallback) {
        com.ss.union.game.sdk.core.glide.load.engine.b bVar;
        try {
            resourceCallback.onResourceReady(this.d, this.b);
        } finally {
        }
    }

    synchronized void b(ResourceCallback resourceCallback) {
        com.ss.union.game.sdk.core.glide.load.engine.b bVar;
        try {
            resourceCallback.onLoadFailed(this.c);
        } finally {
        }
    }

    synchronized void c(ResourceCallback resourceCallback) {
        this.f.throwIfRecycled();
        this.a.a(resourceCallback);
        if (this.a.a()) {
            b();
            if ((this.u || this.v) && this.n.get() == 0) {
                i();
            }
        }
    }

    boolean a() {
        return this.s;
    }

    private GlideExecutor g() {
        return this.q ? this.l : this.r ? this.m : this.k;
    }

    void b() {
        if (h()) {
            return;
        }
        this.x = true;
        this.w.b();
        this.i.onEngineJobCancelled(this, this.o);
    }

    synchronized boolean c() {
        return this.x;
    }

    private boolean h() {
        return this.v || this.u || this.x;
    }

    void d() {
        synchronized (this) {
            this.f.throwIfRecycled();
            if (this.x) {
                this.t.recycle();
                i();
                return;
            }
            if (this.a.a()) {
                throw new IllegalStateException("Received a resource without any callbacks to notify");
            }
            if (this.u) {
                throw new IllegalStateException("Already have resource");
            }
            this.d = this.h.a(this.t, this.p);
            this.u = true;
            e eVarD = this.a.d();
            a(eVarD.b() + 1);
            this.i.onEngineJobComplete(this, this.o, this.d);
            Iterator<d> it = eVarD.iterator();
            while (it.hasNext()) {
                d next = it.next();
                next.b.execute(new b(next.a));
            }
            e();
        }
    }

    synchronized void a(int i) {
        Preconditions.checkArgument(h(), "Not yet complete!");
        if (this.n.getAndAdd(i) == 0 && this.d != null) {
            this.d.c();
        }
    }

    synchronized void e() {
        this.f.throwIfRecycled();
        Preconditions.checkArgument(h(), "Not yet complete!");
        int iDecrementAndGet = this.n.decrementAndGet();
        Preconditions.checkArgument(iDecrementAndGet >= 0, "Can't decrement below 0");
        if (iDecrementAndGet == 0) {
            if (this.d != null) {
                this.d.d();
            }
            i();
        }
    }

    private synchronized void i() {
        if (this.o == null) {
            throw new IllegalArgumentException();
        }
        this.a.c();
        this.o = null;
        this.d = null;
        this.t = null;
        this.v = false;
        this.x = false;
        this.u = false;
        this.w.a(false);
        this.w = null;
        this.c = null;
        this.b = null;
        this.g.release(this);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.ss.union.game.sdk.core.glide.load.engine.g.a
    public void a(Resource<R> resource, DataSource dataSource) {
        synchronized (this) {
            this.t = resource;
            this.b = dataSource;
        }
        d();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.g.a
    public void a(GlideException glideException) {
        synchronized (this) {
            this.c = glideException;
        }
        f();
    }

    @Override // com.ss.union.game.sdk.core.glide.load.engine.g.a
    public void a(g<?> gVar) {
        g().execute(gVar);
    }

    void f() {
        synchronized (this) {
            this.f.throwIfRecycled();
            if (this.x) {
                i();
                return;
            }
            if (this.a.a()) {
                throw new IllegalStateException("Received an exception without any callbacks to notify");
            }
            if (this.v) {
                throw new IllegalStateException("Already failed once");
            }
            this.v = true;
            Key key = this.o;
            e eVarD = this.a.d();
            a(eVarD.b() + 1);
            this.i.onEngineJobComplete(this, key, null);
            Iterator<d> it = eVarD.iterator();
            while (it.hasNext()) {
                d next = it.next();
                next.b.execute(new a(next.a));
            }
            e();
        }
    }

    @Override // com.ss.union.game.sdk.core.glide.util.pool.FactoryPools.Poolable
    public StateVerifier getVerifier() {
        return this.f;
    }

    private class a implements Runnable {
        private final ResourceCallback b;

        a(ResourceCallback resourceCallback) {
            this.b = resourceCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (h.this) {
                if (h.this.a.b(this.b)) {
                    h.this.b(this.b);
                }
                h.this.e();
            }
        }
    }

    private class b implements Runnable {
        private final ResourceCallback b;

        b(ResourceCallback resourceCallback) {
            this.b = resourceCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            synchronized (h.this) {
                if (h.this.a.b(this.b)) {
                    h.this.d.c();
                    h.this.a(this.b);
                    h.this.c(this.b);
                }
                h.this.e();
            }
        }
    }

    static final class e implements Iterable<d> {
        private final List<d> a;

        e() {
            this(new ArrayList(2));
        }

        e(List<d> list) {
            this.a = list;
        }

        void a(ResourceCallback resourceCallback, Executor executor) {
            this.a.add(new d(resourceCallback, executor));
        }

        void a(ResourceCallback resourceCallback) {
            this.a.remove(c(resourceCallback));
        }

        boolean b(ResourceCallback resourceCallback) {
            return this.a.contains(c(resourceCallback));
        }

        boolean a() {
            return this.a.isEmpty();
        }

        int b() {
            return this.a.size();
        }

        void c() {
            this.a.clear();
        }

        e d() {
            return new e(new ArrayList(this.a));
        }

        private static d c(ResourceCallback resourceCallback) {
            return new d(resourceCallback, Executors.directExecutor());
        }

        @Override // java.lang.Iterable
        public Iterator<d> iterator() {
            return this.a.iterator();
        }
    }

    static final class d {
        final ResourceCallback a;
        final Executor b;

        d(ResourceCallback resourceCallback, Executor executor) {
            this.a = resourceCallback;
            this.b = executor;
        }

        public boolean equals(Object obj) {
            if (obj instanceof d) {
                return this.a.equals(((d) obj).a);
            }
            return false;
        }

        public int hashCode() {
            return this.a.hashCode();
        }
    }

    static class c {
        c() {
        }

        public <R> l<R> a(Resource<R> resource, boolean z) {
            return new l<>(resource, z, true);
        }
    }
}
