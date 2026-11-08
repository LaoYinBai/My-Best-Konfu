package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122;

import androidx.appcompat.widget.ActivityChooserView;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.x;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class n {

    /* renamed from: c, reason: collision with root package name */
    private Runnable f1720c;
    private ExecutorService d;

    /* renamed from: a, reason: collision with root package name */
    private int f1718a = 64;

    /* renamed from: b, reason: collision with root package name */
    private int f1719b = 5;
    private final Deque<x.a> e = new ArrayDeque();
    private final Deque<x.a> f = new ArrayDeque();
    private final Deque<x> g = new ArrayDeque();

    private void c() {
        if (this.f.size() < this.f1718a && !this.e.isEmpty()) {
            Iterator<x.a> it = this.e.iterator();
            while (it.hasNext()) {
                x.a next = it.next();
                if (c(next) < this.f1719b) {
                    it.remove();
                    this.f.add(next);
                    a().execute(next);
                }
                if (this.f.size() >= this.f1718a) {
                    return;
                }
            }
        }
    }

    public synchronized ExecutorService a() {
        if (this.d == null) {
            this.d = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60L, TimeUnit.SECONDS, new SynchronousQueue(), bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.c.a("OkHttp Dispatcher", false));
        }
        return this.d;
    }

    void b(x.a aVar) {
        a(this.f, aVar, true);
    }

    void b(x xVar) {
        a(this.g, xVar, false);
    }

    public synchronized int b() {
        return this.f.size() + this.g.size();
    }

    synchronized void a(x.a aVar) {
        if (this.f.size() < this.f1718a && c(aVar) < this.f1719b) {
            this.f.add(aVar);
            a().execute(aVar);
        } else {
            this.e.add(aVar);
        }
    }

    synchronized void a(x xVar) {
        this.g.add(xVar);
    }

    private <T> void a(Deque<T> deque, T t, boolean z) {
        int iB;
        Runnable runnable;
        synchronized (this) {
            if (deque.remove(t)) {
                if (z) {
                    c();
                }
                iB = b();
                runnable = this.f1720c;
            } else {
                throw new AssertionError("Call wasn't in-flight!");
            }
        }
        if (iB != 0 || runnable == null) {
            return;
        }
        runnable.run();
    }

    private int c(x.a aVar) {
        Iterator<x.a> it = this.f.iterator();
        int i = 0;
        while (it.hasNext()) {
            if (it.next().c().equals(aVar.c())) {
                i++;
            }
        }
        return i;
    }
}
