package com.ss.union.game.sdk.core.glide.load.engine;

import android.os.Process;
import com.ss.union.game.sdk.core.glide.load.Key;
import com.ss.union.game.sdk.core.glide.load.engine.l;
import com.ss.union.game.sdk.core.glide.util.Preconditions;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
final class a {
    final Map<Key, b> a;
    private final boolean b;
    private final Executor c;
    private final ReferenceQueue<l<?>> d;
    private l.a e;
    private volatile boolean f;
    private volatile InterfaceC0078a g;

    /* renamed from: com.ss.union.game.sdk.core.glide.load.engine.a$a, reason: collision with other inner class name */
    interface InterfaceC0078a {
        void a();
    }

    a(boolean z) {
        this(z, Executors.newSingleThreadExecutor(new ThreadFactory() { // from class: com.ss.union.game.sdk.core.glide.load.engine.a.1
            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(final Runnable runnable) {
                return new Thread(new Runnable() { // from class: com.ss.union.game.sdk.core.glide.load.engine.a.1.1
                    @Override // java.lang.Runnable
                    public void run() throws SecurityException, IllegalArgumentException {
                        Process.setThreadPriority(10);
                        runnable.run();
                    }
                }, "glide-active-resources");
            }
        }));
    }

    a(boolean z, Executor executor) {
        this.a = new HashMap();
        this.d = new ReferenceQueue<>();
        this.b = z;
        this.c = executor;
        executor.execute(new Runnable() { // from class: com.ss.union.game.sdk.core.glide.load.engine.a.2
            @Override // java.lang.Runnable
            public void run() {
                a.this.a();
            }
        });
    }

    void a(l.a aVar) {
        synchronized (aVar) {
            synchronized (this) {
                this.e = aVar;
            }
        }
    }

    synchronized void a(Key key, l<?> lVar) {
        b bVarPut = this.a.put(key, new b(key, lVar, this.d, this.b));
        if (bVarPut != null) {
            bVarPut.a();
        }
    }

    synchronized void a(Key key) {
        b bVarRemove = this.a.remove(key);
        if (bVarRemove != null) {
            bVarRemove.a();
        }
    }

    synchronized l<?> b(Key key) {
        b bVar = this.a.get(key);
        if (bVar == null) {
            return null;
        }
        l<?> lVar = (l) bVar.get();
        if (lVar == null) {
            a(bVar);
        }
        return lVar;
    }

    void a(b bVar) {
        synchronized (this.e) {
            synchronized (this) {
                this.a.remove(bVar.a);
                if (bVar.b && bVar.c != null) {
                    l<?> lVar = new l<>(bVar.c, true, false);
                    lVar.a(bVar.a, this.e);
                    this.e.onResourceReleased(bVar.a, lVar);
                }
            }
        }
    }

    void a() {
        while (!this.f) {
            try {
                a((b) this.d.remove());
                InterfaceC0078a interfaceC0078a = this.g;
                if (interfaceC0078a != null) {
                    interfaceC0078a.a();
                }
            } catch (InterruptedException unused) {
                Thread.currentThread().interrupt();
            }
        }
    }

    void a(InterfaceC0078a interfaceC0078a) {
        this.g = interfaceC0078a;
    }

    void b() {
        this.f = true;
        if (this.c instanceof ExecutorService) {
            com.ss.union.game.sdk.core.glide.util.Executors.shutdownAndAwaitTermination((ExecutorService) this.c);
        }
    }

    static final class b extends WeakReference<l<?>> {
        final Key a;
        final boolean b;
        Resource<?> c;

        b(Key key, l<?> lVar, ReferenceQueue<? super l<?>> referenceQueue, boolean z) {
            super(lVar, referenceQueue);
            this.a = (Key) Preconditions.checkNotNull(key);
            this.c = (lVar.b() && z) ? (Resource) Preconditions.checkNotNull(lVar.a()) : null;
            this.b = lVar.b();
        }

        void a() {
            this.c = null;
            clear();
        }
    }
}
