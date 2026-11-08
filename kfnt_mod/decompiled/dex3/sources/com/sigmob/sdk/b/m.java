package com.sigmob.sdk.b;

import com.sigmob.logger.SigmobLog;
import java.lang.Thread;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class m {
    private final p a;
    private final com.sigmob.sdk.b.a b;
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
            m.this.e();
        }
    }

    public m(p pVar, com.sigmob.sdk.b.a aVar) {
        this.a = (p) l.a(pVar);
        this.b = (com.sigmob.sdk.b.a) l.a(aVar);
    }

    private void b() throws n {
        int i = this.e.get();
        if (i < 3) {
            return;
        }
        this.e.set(0);
        throw new n("Error reading source " + i + " times");
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
                    throw new n("Waiting source data is interrupted!", e);
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
        long j2;
        Throwable th2;
        long jA;
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
                jA2 = j2;
                i();
                b(jA, jA2);
                throw th2;
            }
        } catch (Throwable th4) {
            j = -1;
            th = th4;
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
                        SigmobLog.d("SourceReaderRunnable停止从服务器下载视频数据");
                        i();
                        b(jA, jA2);
                        return;
                    }
                    this.b.a(bArr, iA);
                }
                jA += iA;
                b(jA, jA2);
            }
        } catch (Throwable th5) {
            th = th5;
            j = jA2;
            j3 = jA;
            long j4 = j;
            try {
                this.e.incrementAndGet();
                a(th);
                i();
                b(j3, j4);
            } catch (Throwable th6) {
                th2 = th6;
                long j5 = j3;
                jA2 = j4;
                jA = j5;
                i();
                b(jA, jA2);
                throw th2;
            }
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
        } catch (n e) {
            a(new n("Error closing source " + this.a, e));
        }
    }

    public int a(byte[] bArr, long j, int i) throws n {
        o.a(bArr, j, i);
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
            SigmobLog.d("Shutdown proxy for " + this.a);
            try {
                this.g = true;
                if (this.f != null) {
                    this.f.interrupt();
                }
                this.b.b();
            } catch (n e) {
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
        if (th instanceof j) {
            SigmobLog.d("ProxyCache is interrupted");
        } else {
            SigmobLog.e("ProxyCache error", th);
        }
    }
}
