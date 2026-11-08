package b;

import androidx.appcompat.widget.ActivityChooserView;
import b.ab;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class p {

    /* renamed from: c, reason: collision with root package name */
    @Nullable
    private Runnable f1264c;

    @Nullable
    private ExecutorService d;

    /* renamed from: a, reason: collision with root package name */
    private int f1262a = 64;

    /* renamed from: b, reason: collision with root package name */
    private int f1263b = 5;
    private final Deque<ab.a> e = new ArrayDeque();
    private final Deque<ab.a> f = new ArrayDeque();
    private final Deque<ab> g = new ArrayDeque();

    public p(ExecutorService executorService) {
        this.d = executorService;
    }

    public p() {
    }

    public synchronized ExecutorService a() {
        if (this.d == null) {
            this.d = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60L, TimeUnit.SECONDS, new SynchronousQueue(), b.a.c.a("OkHttp Dispatcher", false));
        }
        return this.d;
    }

    public synchronized void a(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        this.f1262a = i;
        i();
    }

    public synchronized int b() {
        return this.f1262a;
    }

    public synchronized void b(int i) {
        if (i < 1) {
            throw new IllegalArgumentException("max < 1: " + i);
        }
        this.f1263b = i;
        i();
    }

    public synchronized int c() {
        return this.f1263b;
    }

    public synchronized void a(@Nullable Runnable runnable) {
        this.f1264c = runnable;
    }

    synchronized void a(ab.a aVar) {
        if (this.f.size() < this.f1262a && c(aVar) < this.f1263b) {
            this.f.add(aVar);
            a().execute(aVar);
        } else {
            this.e.add(aVar);
        }
    }

    public synchronized void d() {
        Iterator<ab.a> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().c().c();
        }
        Iterator<ab.a> it2 = this.f.iterator();
        while (it2.hasNext()) {
            it2.next().c().c();
        }
        Iterator<ab> it3 = this.g.iterator();
        while (it3.hasNext()) {
            it3.next().c();
        }
    }

    private void i() {
        if (this.f.size() < this.f1262a && !this.e.isEmpty()) {
            Iterator<ab.a> it = this.e.iterator();
            while (it.hasNext()) {
                ab.a next = it.next();
                if (c(next) < this.f1263b) {
                    it.remove();
                    this.f.add(next);
                    a().execute(next);
                }
                if (this.f.size() >= this.f1262a) {
                    return;
                }
            }
        }
    }

    private int c(ab.a aVar) {
        int i = 0;
        for (ab.a aVar2 : this.f) {
            if (!aVar2.c().d && aVar2.a().equals(aVar.a())) {
                i++;
            }
        }
        return i;
    }

    synchronized void a(ab abVar) {
        this.g.add(abVar);
    }

    void b(ab.a aVar) {
        a(this.f, aVar, true);
    }

    void b(ab abVar) {
        a(this.g, abVar, false);
    }

    private <T> void a(Deque<T> deque, T t, boolean z) {
        int iH;
        Runnable runnable;
        synchronized (this) {
            if (!deque.remove(t)) {
                throw new AssertionError("Call wasn't in-flight!");
            }
            if (z) {
                i();
            }
            iH = h();
            runnable = this.f1264c;
        }
        if (iH != 0 || runnable == null) {
            return;
        }
        runnable.run();
    }

    public synchronized List<e> e() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        Iterator<ab.a> it = this.e.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().c());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized List<e> f() {
        ArrayList arrayList;
        arrayList = new ArrayList();
        arrayList.addAll(this.g);
        Iterator<ab.a> it = this.f.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().c());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public synchronized int g() {
        return this.e.size();
    }

    public synchronized int h() {
        return this.f.size() + this.g.size();
    }
}
