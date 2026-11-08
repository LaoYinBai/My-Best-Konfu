package com.huawei.hmf.tasks.a;

import com.huawei.hmf.tasks.h;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class e<TResult> extends com.huawei.hmf.tasks.f<TResult> {
    private boolean b;
    private volatile boolean c;
    private TResult d;
    private Exception e;
    private final Object a = new Object();
    private List<com.huawei.hmf.tasks.b<TResult>> f = new ArrayList();

    private com.huawei.hmf.tasks.f<TResult> a(com.huawei.hmf.tasks.b<TResult> bVar) {
        boolean zA;
        synchronized (this.a) {
            zA = a();
            if (!zA) {
                this.f.add(bVar);
            }
        }
        if (zA) {
            bVar.a(this);
        }
        return this;
    }

    private void g() {
        synchronized (this.a) {
            Iterator<com.huawei.hmf.tasks.b<TResult>> it = this.f.iterator();
            while (it.hasNext()) {
                try {
                    it.next().a(this);
                } catch (RuntimeException e) {
                    throw e;
                } catch (Exception e2) {
                    throw new RuntimeException(e2);
                }
            }
            this.f = null;
        }
    }

    @Override // com.huawei.hmf.tasks.f
    public final com.huawei.hmf.tasks.f<TResult> a(com.huawei.hmf.tasks.c<TResult> cVar) {
        return a(h.a(), cVar);
    }

    @Override // com.huawei.hmf.tasks.f
    public final com.huawei.hmf.tasks.f<TResult> a(com.huawei.hmf.tasks.d dVar) {
        return a(h.a(), dVar);
    }

    @Override // com.huawei.hmf.tasks.f
    public final com.huawei.hmf.tasks.f<TResult> a(com.huawei.hmf.tasks.e<TResult> eVar) {
        return a(h.a(), eVar);
    }

    @Override // com.huawei.hmf.tasks.f
    public final com.huawei.hmf.tasks.f<TResult> a(Executor executor, com.huawei.hmf.tasks.c<TResult> cVar) {
        return a((com.huawei.hmf.tasks.b) new b(executor, cVar));
    }

    public final com.huawei.hmf.tasks.f<TResult> a(Executor executor, com.huawei.hmf.tasks.d dVar) {
        return a((com.huawei.hmf.tasks.b) new c(executor, dVar));
    }

    public final com.huawei.hmf.tasks.f<TResult> a(Executor executor, com.huawei.hmf.tasks.e<TResult> eVar) {
        return a((com.huawei.hmf.tasks.b) new d(executor, eVar));
    }

    public final void a(Exception exc) {
        synchronized (this.a) {
            if (this.b) {
                return;
            }
            this.b = true;
            this.e = exc;
            this.a.notifyAll();
            g();
        }
    }

    public final void a(TResult tresult) {
        synchronized (this.a) {
            if (this.b) {
                return;
            }
            this.b = true;
            this.d = tresult;
            this.a.notifyAll();
            g();
        }
    }

    @Override // com.huawei.hmf.tasks.f
    public final boolean a() {
        boolean z;
        synchronized (this.a) {
            z = this.b;
        }
        return z;
    }

    @Override // com.huawei.hmf.tasks.f
    public final boolean b() {
        return this.c;
    }

    @Override // com.huawei.hmf.tasks.f
    public final boolean c() {
        boolean z;
        synchronized (this.a) {
            z = this.b && !b() && this.e == null;
        }
        return z;
    }

    @Override // com.huawei.hmf.tasks.f
    public final TResult d() {
        TResult tresult;
        synchronized (this.a) {
            if (this.e != null) {
                throw new RuntimeException(this.e);
            }
            tresult = this.d;
        }
        return tresult;
    }

    public final boolean e() {
        synchronized (this.a) {
            if (this.b) {
                return false;
            }
            this.b = true;
            this.c = true;
            this.a.notifyAll();
            g();
            return true;
        }
    }

    @Override // com.huawei.hmf.tasks.f
    public final Exception f() {
        Exception exc;
        synchronized (this.a) {
            exc = this.e;
        }
        return exc;
    }
}
