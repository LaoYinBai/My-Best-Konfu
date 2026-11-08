package com.kwad.sdk.core.videocache;

import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class k {
    private final m a;
    private final com.kwad.sdk.core.videocache.a b;
    private volatile Thread f;
    private volatile boolean g;
    private final Object c = new Object();
    private final Object d = new Object();
    private volatile int h = -1;
    private final AtomicInteger e = new AtomicInteger();

    private class a implements Runnable {
        private a() {
        }

        @Override // java.lang.Runnable
        public void run() throws Throwable {
            k.this.e();
        }
    }

    public k(m mVar, com.kwad.sdk.core.videocache.a aVar) {
        this.a = (m) j.a(mVar);
        this.b = (com.kwad.sdk.core.videocache.a) j.a(aVar);
    }

    private void b() throws ProxyCacheException {
        int i = this.e.get();
        if (i < 1) {
            return;
        }
        this.e.set(0);
        throw new ProxyCacheException("Error reading source " + i + " times");
    }

    private void b(long j, long j2) {
        a(j, j2);
        synchronized (this.c) {
            this.c.notifyAll();
        }
    }

    private synchronized void c() {
        boolean z = (this.f == null || this.f.getState() == Thread.State.TERMINATED) ? false : true;
        if (!this.g && !this.b.d() && !z) {
            this.f = new Thread(new a(), "Source reader for " + this.a);
            this.f.start();
        }
    }

    private void d() {
        synchronized (this.c) {
            try {
                try {
                    this.c.wait(1000L);
                } catch (InterruptedException e) {
                    throw new ProxyCacheException("Waiting source data is interrupted!", e);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void e() throws Throwable {
        long j;
        Throwable th;
        long jA;
        long j2;
        Throwable th2;
        long jA2;
        long j3 = 0;
        try {
            jA = this.b.a();
            try {
                this.a.a(jA);
                jA2 = this.a.a();
            } catch (Throwable th3) {
                j2 = -1;
                th2 = th3;
            }
            try {
                byte[] bArr = new byte[8192];
                while (true) {
                    int iA = this.a.a(bArr);
                    if (iA == -1) {
                        g();
                        f();
                        i();
                        b(jA, jA2);
                        return;
                    }
                    synchronized (this.d) {
                        if (h()) {
                            i();
                            b(jA, jA2);
                            return;
                        }
                        this.b.a(bArr, iA);
                    }
                    jA += iA;
                    b(jA, jA2);
                }
            } catch (Throwable th4) {
                th = th4;
                i();
                b(jA, jA2);
                throw th;
            }
        } catch (Throwable th5) {
            j = -1;
            th = th5;
            jA = 0;
        }
    }

    private void f() {
        this.h = 100;
        a(this.h);
    }

    private void g() {
        synchronized (this.d) {
            if (!h() && this.b.a() == this.a.a()) {
                this.b.c();
            }
        }
    }

    private boolean h() {
        return Thread.currentThread().isInterrupted() || this.g;
    }

    private void i() {
        try {
            this.a.b();
        } catch (ProxyCacheException e) {
            a(new ProxyCacheException("Error closing source " + this.a, e));
        }
    }

    public int a(byte[] bArr, long j, int i) throws ProxyCacheException {
        l.a(bArr, j, i);
        while (!this.b.d() && this.b.a() < i + j && !this.g) {
            c();
            d();
            b();
        }
        int iA = this.b.a(bArr, j, i);
        if (this.b.d() && this.h != 100) {
            this.h = 100;
            a(100);
        }
        return iA;
    }

    public void a() {
        synchronized (this.d) {
            com.kwad.sdk.core.d.a.a("ProxyCache", "Shutdown proxy for " + this.a);
            try {
                this.g = true;
                if (this.f != null) {
                    this.f.interrupt();
                }
                this.b.b();
            } catch (ProxyCacheException e) {
                a(e);
            }
        }
    }

    protected void a(int i) {
    }

    protected void a(long j, long j2) {
        int i = (j2 > 0L ? 1 : (j2 == 0L ? 0 : -1)) == 0 ? 100 : (int) ((j / j2) * 100.0f);
        boolean z = i != this.h;
        if ((j2 >= 0) && z) {
            a(i);
        }
        this.h = i;
    }

    protected final void a(Throwable th) {
        if (th instanceof InterruptedProxyCacheException) {
            com.kwad.sdk.core.d.a.a("ProxyCache", "ProxyCache is interrupted");
        } else {
            com.kwad.sdk.core.d.a.e("ProxyCache", "ProxyCache error");
        }
    }
}
