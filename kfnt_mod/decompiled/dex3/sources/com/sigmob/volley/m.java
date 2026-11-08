package com.sigmob.volley;

import android.os.Handler;
import android.os.Looper;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class m {
    private final AtomicInteger a;
    private final Set<l<?>> b;
    private LinkedBlockingDeque<Runnable> c;
    private final f d;
    private final o e;
    private final List<b> f;
    private ThreadPoolExecutor g;

    public interface a {
        boolean a(l<?> lVar);
    }

    public interface b<T> {
        void a(l<T> lVar);
    }

    public m(f fVar, int i) {
        this(fVar, i, new d(new Handler(Looper.getMainLooper())));
    }

    public m(f fVar, int i, o oVar) {
        this.a = new AtomicInteger();
        this.b = new HashSet();
        this.c = new LinkedBlockingDeque<>(1);
        this.f = new ArrayList();
        this.g = null;
        this.d = fVar;
        this.e = oVar;
        if (this.g == null) {
            this.g = new ThreadPoolExecutor(i, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, this.c);
            this.g.allowCoreThreadTimeOut(true);
        }
    }

    public <T> l<T> a(l<T> lVar) {
        if (lVar == null) {
            return null;
        }
        this.g.submit(new g(this.d, lVar, this.e));
        return lVar;
    }

    public void a(a aVar) {
        synchronized (this.b) {
            for (l<?> lVar : this.b) {
                if (aVar.a(lVar)) {
                    lVar.j();
                }
            }
        }
    }

    public void b() {
        c();
    }

    <T> void b(l<T> lVar) {
        synchronized (this.b) {
            this.b.remove(lVar);
        }
        synchronized (this.f) {
            Iterator<b> it = this.f.iterator();
            while (it.hasNext()) {
                it.next().a(lVar);
            }
        }
    }

    public void c() {
    }
}
