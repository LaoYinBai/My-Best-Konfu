package com.kwai.sodler.lib;

import android.content.Context;
import androidx.annotation.NonNull;
import com.kwai.sodler.lib.k;
import com.kwai.sodler.lib.kwai.g;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class j extends k {
    private static volatile j a;
    private volatile boolean b;
    private k c;
    private com.kwai.sodler.lib.ext.a d;
    private ExecutorService e;
    private Map<String, b> f;
    private a g;
    private g.a h;

    public interface a {
        void a(String str, String str2);
    }

    public static class b {
        private final com.kwai.sodler.lib.kwai.f a;
        private final Future<com.kwai.sodler.lib.kwai.f> b;

        public b(com.kwai.sodler.lib.kwai.f fVar, Future<com.kwai.sodler.lib.kwai.f> future) {
            this.a = fVar;
            this.b = future;
        }

        public void a() {
            this.a.e();
            this.b.cancel(true);
        }
    }

    private j() {
        super(null, null, null, null, null, null);
        this.b = false;
    }

    public static j a() {
        if (a == null) {
            synchronized (j.class) {
                if (a == null) {
                    a = new j();
                }
            }
        }
        return a;
    }

    private static ExecutorService j() {
        return new ThreadPoolExecutor(0, 2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new ThreadFactory() { // from class: com.kwai.sodler.lib.j.1
            private final AtomicInteger a = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, "ksad-Sodler-" + this.a.getAndIncrement());
            }
        });
    }

    private void k() {
        if (!this.b) {
            throw new RuntimeException("Sodler has not yet been init.");
        }
    }

    public b a(@NonNull com.kwai.sodler.lib.kwai.f fVar, int i) {
        return b(fVar, k.a.a(this, i));
    }

    @Override // com.kwai.sodler.lib.k
    public com.kwai.sodler.lib.kwai.f a(@NonNull com.kwai.sodler.lib.kwai.f fVar, @NonNull k.a aVar) {
        if (!this.b) {
            throw new RuntimeException("Sodler has not yet been init.");
        }
        com.kwai.sodler.lib.kwai.e eVarB = fVar.b();
        k kVar = this.c;
        if (eVarB == null) {
            eVarB = this.c;
        }
        return kVar.a(fVar.a(eVarB), aVar);
    }

    public synchronized void a(Context context, @NonNull com.kwai.sodler.lib.ext.c cVar) {
        if (!this.b) {
            d dVar = new d(context);
            e eVar = new e(context);
            c cVar2 = new c(context, cVar);
            com.kwai.sodler.lib.b bVar = new com.kwai.sodler.lib.b();
            this.d = new com.kwai.sodler.lib.ext.a();
            this.e = j();
            this.c = new k(dVar, eVar, cVar2, bVar, cVar, new com.kwai.sodler.lib.ext.a());
            this.b = true;
            k();
        }
    }

    public void a(g.a aVar) {
        this.h = aVar;
    }

    @Override // com.kwai.sodler.lib.k, com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.ext.c b() {
        if (this.b) {
            return this.c.b();
        }
        throw new RuntimeException("Sodler has not yet been init.");
    }

    public b b(@NonNull final com.kwai.sodler.lib.kwai.f fVar, @NonNull final k.a aVar) {
        if (!this.b) {
            throw new RuntimeException("Sodler has not yet been init.");
        }
        this.f = a(this.f);
        b bVar = this.f.get(fVar.i());
        if (bVar != null) {
            bVar.a();
        }
        fVar.a(this);
        b bVar2 = new b(fVar, this.e.submit(new Callable<com.kwai.sodler.lib.kwai.f>() { // from class: com.kwai.sodler.lib.j.2
            @Override // java.util.concurrent.Callable
            /* renamed from: a, reason: merged with bridge method [inline-methods] */
            public com.kwai.sodler.lib.kwai.f call() {
                return j.this.a(fVar, aVar);
            }
        }));
        if (fVar.i() != null) {
            this.f.put(fVar.i(), bVar2);
        }
        return bVar2;
    }

    @Override // com.kwai.sodler.lib.k, com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.kwai.d c() {
        if (this.b) {
            return this.c.c();
        }
        throw new RuntimeException("Sodler has not yet been init.");
    }

    @Override // com.kwai.sodler.lib.k, com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.kwai.g d() {
        if (this.b) {
            return this.c.d();
        }
        throw new RuntimeException("Sodler has not yet been init.");
    }

    @Override // com.kwai.sodler.lib.k, com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.kwai.c e() {
        if (this.b) {
            return this.c.e();
        }
        throw new RuntimeException("Sodler has not yet been init.");
    }

    @Override // com.kwai.sodler.lib.k, com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.kwai.b f() {
        if (this.b) {
            return this.c.f();
        }
        throw new RuntimeException("Sodler has not yet been init.");
    }

    @Override // com.kwai.sodler.lib.k, com.kwai.sodler.lib.kwai.e
    public com.kwai.sodler.lib.ext.a g() {
        if (this.b) {
            return this.d;
        }
        throw new RuntimeException("Sodler has not yet been init.");
    }

    public a h() {
        return this.g;
    }

    public g.a i() {
        return this.h;
    }
}
