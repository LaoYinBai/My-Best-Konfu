package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends t {
    private static final long h = TimeUnit.SECONDS.toMillis(60);
    private static final long i = TimeUnit.MILLISECONDS.toNanos(h);
    static a j;
    private boolean e;
    private a f;
    private long g;

    class b implements s {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ s f1459a;

        b(s sVar) {
            this.f1459a = sVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public long a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            a.this.g();
            try {
                try {
                    long jA = this.f1459a.a(cVar, j);
                    a.this.a(true);
                    return jA;
                } catch (IOException e) {
                    throw a.this.a(e);
                }
            } catch (Throwable th) {
                a.this.a(false);
                throw th;
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s
        public t b() {
            return a.this;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.s, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a.this.g();
            try {
                try {
                    this.f1459a.close();
                    a.this.a(true);
                } catch (IOException e) {
                    throw a.this.a(e);
                }
            } catch (Throwable th) {
                a.this.a(false);
                throw th;
            }
        }

        public String toString() {
            return "AsyncTimeout.source(" + this.f1459a + ")";
        }
    }

    private static final class c extends Thread {
        c() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
        
            r1.i();
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                r3 = this;
            L0:
                java.lang.Class<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a> r0 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a r1 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.j()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a r2 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.j     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.j = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.i()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                throw r1     // Catch: java.lang.InterruptedException -> L0
            */
            throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.c.run():void");
        }
    }

    private static synchronized void a(a aVar, long j2, boolean z) {
        if (j == null) {
            j = new a();
            c cVar = new c();
            cVar.setName("tt_pangle_thread_watch_dog");
            cVar.start();
        }
        long jNanoTime = System.nanoTime();
        if (j2 != 0 && z) {
            aVar.g = Math.min(j2, aVar.c() - jNanoTime) + jNanoTime;
        } else if (j2 != 0) {
            aVar.g = j2 + jNanoTime;
        } else {
            if (!z) {
                throw new AssertionError();
            }
            aVar.g = aVar.c();
        }
        long jB = aVar.b(jNanoTime);
        a aVar2 = j;
        while (true) {
            a aVar3 = aVar2.f;
            if (aVar3 == null || jB < aVar3.b(jNanoTime)) {
                break;
            } else {
                aVar2 = aVar2.f;
            }
        }
        aVar.f = aVar2.f;
        aVar2.f = aVar;
        if (aVar2 == j) {
            a.class.notify();
        }
    }

    private long b(long j2) {
        return this.g - j2;
    }

    static a j() throws InterruptedException {
        a aVar = j.f;
        if (aVar == null) {
            long jNanoTime = System.nanoTime();
            a.class.wait(h);
            if (j.f != null || System.nanoTime() - jNanoTime < i) {
                return null;
            }
            return j;
        }
        long jB = aVar.b(System.nanoTime());
        if (jB > 0) {
            long j2 = jB / 1000000;
            a.class.wait(j2, (int) (jB - (1000000 * j2)));
            return null;
        }
        j.f = aVar.f;
        aVar.f = null;
        return aVar;
    }

    public final void g() {
        if (this.e) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jF = f();
        boolean zD = d();
        if (jF != 0 || zD) {
            this.e = true;
            a(this, jF, zD);
        }
    }

    public final boolean h() {
        if (!this.e) {
            return false;
        }
        this.e = false;
        return a(this);
    }

    protected void i() {
    }

    protected IOException b(IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* renamed from: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a$a, reason: collision with other inner class name */
    class C0050a implements r {

        /* renamed from: a, reason: collision with root package name */
        final /* synthetic */ r f1457a;

        C0050a(r rVar) {
            this.f1457a = rVar;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public void b(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.c cVar, long j) throws IOException {
            u.a(cVar.f1464b, 0L, j);
            while (true) {
                long j2 = 0;
                if (j <= 0) {
                    return;
                }
                o oVar = cVar.f1463a;
                while (true) {
                    if (j2 >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        break;
                    }
                    j2 += oVar.f1489c - oVar.f1488b;
                    if (j2 >= j) {
                        j2 = j;
                        break;
                    }
                    oVar = oVar.f;
                }
                a.this.g();
                try {
                    try {
                        this.f1457a.b(cVar, j2);
                        j -= j2;
                        a.this.a(true);
                    } catch (IOException e) {
                        throw a.this.a(e);
                    }
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Closeable, java.lang.AutoCloseable
        public void close() throws IOException {
            a.this.g();
            try {
                try {
                    this.f1457a.close();
                    a.this.a(true);
                } catch (IOException e) {
                    throw a.this.a(e);
                }
            } catch (Throwable th) {
                a.this.a(false);
                throw th;
            }
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r, java.io.Flushable
        public void flush() throws IOException {
            a.this.g();
            try {
                try {
                    this.f1457a.flush();
                    a.this.a(true);
                } catch (IOException e) {
                    throw a.this.a(e);
                }
            } catch (Throwable th) {
                a.this.a(false);
                throw th;
            }
        }

        public String toString() {
            return "AsyncTimeout.sink(" + this.f1457a + ")";
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.r
        public t b() {
            return a.this;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x000b, code lost:
    
        r1.f = r3.f;
        r3.f = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0012, code lost:
    
        r3 = false;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    private static synchronized boolean a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a r3) {
        /*
            java.lang.Class<bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a> r0 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.class
            monitor-enter(r0)
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a r1 = bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.j     // Catch: java.lang.Throwable -> L19
        L5:
            if (r1 == 0) goto L17
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a r2 = r1.f     // Catch: java.lang.Throwable -> L19
            if (r2 != r3) goto L15
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a r2 = r3.f     // Catch: java.lang.Throwable -> L19
            r1.f = r2     // Catch: java.lang.Throwable -> L19
            r1 = 0
            r3.f = r1     // Catch: java.lang.Throwable -> L19
            r3 = 0
        L13:
            monitor-exit(r0)
            return r3
        L15:
            r1 = r2
            goto L5
        L17:
            r3 = 1
            goto L13
        L19:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a.a(bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a):boolean");
    }

    public final r a(r rVar) {
        return new C0050a(rVar);
    }

    public final s a(s sVar) {
        return new b(sVar);
    }

    final void a(boolean z) throws IOException {
        if (h() && z) {
            throw b((IOException) null);
        }
    }

    final IOException a(IOException iOException) throws IOException {
        return !h() ? iOException : b(iOException);
    }
}
