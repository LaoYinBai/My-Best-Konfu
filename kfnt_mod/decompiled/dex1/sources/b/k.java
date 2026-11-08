package b;

import androidx.appcompat.widget.ActivityChooserView;
import b.a.c.g;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Socket;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class k {

    /* renamed from: c, reason: collision with root package name */
    static final /* synthetic */ boolean f1245c = !k.class.desiredAssertionStatus();
    private static final Executor d = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 60, TimeUnit.SECONDS, new SynchronousQueue(), b.a.c.a("OkHttp ConnectionPool", true));

    /* renamed from: a, reason: collision with root package name */
    final b.a.c.d f1246a;

    /* renamed from: b, reason: collision with root package name */
    boolean f1247b;
    private final int e;
    private final long f;
    private final Runnable g;
    private final Deque<b.a.c.c> h;

    public k() {
        this(5, 5L, TimeUnit.MINUTES);
    }

    public k(int i, long j, TimeUnit timeUnit) {
        this.g = new Runnable() { // from class: b.k.1
            @Override // java.lang.Runnable
            public void run() throws IOException {
                while (true) {
                    long jA = k.this.a(System.nanoTime());
                    if (jA == -1) {
                        return;
                    }
                    if (jA > 0) {
                        long j2 = jA / 1000000;
                        long j3 = jA - (1000000 * j2);
                        synchronized (k.this) {
                            try {
                                k.this.wait(j2, (int) j3);
                            } catch (InterruptedException unused) {
                            }
                        }
                    }
                }
            }
        };
        this.h = new ArrayDeque();
        this.f1246a = new b.a.c.d();
        this.e = i;
        this.f = timeUnit.toNanos(j);
        if (j > 0) {
            return;
        }
        throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
    }

    public synchronized int a() {
        int i;
        i = 0;
        Iterator<b.a.c.c> it = this.h.iterator();
        while (it.hasNext()) {
            if (it.next().d.isEmpty()) {
                i++;
            }
        }
        return i;
    }

    public synchronized int b() {
        return this.h.size();
    }

    @Nullable
    b.a.c.c a(a aVar, b.a.c.g gVar, ag agVar) {
        if (!f1245c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        for (b.a.c.c cVar : this.h) {
            if (cVar.a(aVar, agVar)) {
                gVar.a(cVar, true);
                return cVar;
            }
        }
        return null;
    }

    @Nullable
    Socket a(a aVar, b.a.c.g gVar) {
        if (!f1245c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        for (b.a.c.c cVar : this.h) {
            if (cVar.a(aVar, null) && cVar.f() && cVar != gVar.c()) {
                return gVar.a(cVar);
            }
        }
        return null;
    }

    void a(b.a.c.c cVar) {
        if (!f1245c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (!this.f1247b) {
            this.f1247b = true;
            d.execute(this.g);
        }
        this.h.add(cVar);
    }

    boolean b(b.a.c.c cVar) {
        if (!f1245c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        }
        if (cVar.f797a || this.e == 0) {
            this.h.remove(cVar);
            return true;
        }
        notifyAll();
        return false;
    }

    public void c() throws IOException {
        ArrayList arrayList = new ArrayList();
        synchronized (this) {
            Iterator<b.a.c.c> it = this.h.iterator();
            while (it.hasNext()) {
                b.a.c.c next = it.next();
                if (next.d.isEmpty()) {
                    next.f797a = true;
                    arrayList.add(next);
                    it.remove();
                }
            }
        }
        Iterator it2 = arrayList.iterator();
        while (it2.hasNext()) {
            b.a.c.a(((b.a.c.c) it2.next()).b());
        }
    }

    long a(long j) throws IOException {
        synchronized (this) {
            long j2 = Long.MIN_VALUE;
            b.a.c.c cVar = null;
            int i = 0;
            int i2 = 0;
            for (b.a.c.c cVar2 : this.h) {
                if (a(cVar2, j) > 0) {
                    i2++;
                } else {
                    i++;
                    long j3 = j - cVar2.e;
                    if (j3 > j2) {
                        cVar = cVar2;
                        j2 = j3;
                    }
                }
            }
            if (j2 < this.f && i <= this.e) {
                if (i > 0) {
                    return this.f - j2;
                }
                if (i2 > 0) {
                    return this.f;
                }
                this.f1247b = false;
                return -1L;
            }
            this.h.remove(cVar);
            b.a.c.a(cVar.b());
            return 0L;
        }
    }

    private int a(b.a.c.c cVar, long j) {
        List<Reference<b.a.c.g>> list = cVar.d;
        int i = 0;
        while (i < list.size()) {
            Reference<b.a.c.g> reference = list.get(i);
            if (reference.get() != null) {
                i++;
            } else {
                b.a.h.f.c().a("A connection to " + cVar.a().a().a() + " was leaked. Did you forget to close a response body?", ((g.a) reference).f813a);
                list.remove(i);
                cVar.f797a = true;
                if (list.isEmpty()) {
                    cVar.e = j - this.f;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
