package c;

import android.support.v4.media.session.PlaybackStateCompat;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a extends z {

    /* renamed from: a, reason: collision with root package name */
    private static final int f2038a = 65536;

    /* renamed from: b, reason: collision with root package name */
    @Nullable
    static a f2039b;
    private static final long d = TimeUnit.SECONDS.toMillis(60);
    private static final long e = TimeUnit.MILLISECONDS.toNanos(d);
    private boolean f;

    @Nullable
    private a g;
    private long h;

    protected void a() {
    }

    public final void c() {
        if (this.f) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long jB_ = b_();
        boolean zC_ = c_();
        if (jB_ != 0 || zC_) {
            this.f = true;
            a(this, jB_, zC_);
        }
    }

    private static synchronized void a(a aVar, long j, boolean z) {
        if (f2039b == null) {
            f2039b = new a();
            new C0070a().start();
        }
        long jNanoTime = System.nanoTime();
        if (j != 0 && z) {
            aVar.h = Math.min(j, aVar.d() - jNanoTime) + jNanoTime;
        } else if (j != 0) {
            aVar.h = j + jNanoTime;
        } else if (z) {
            aVar.h = aVar.d();
        } else {
            throw new AssertionError();
        }
        long jB = aVar.b(jNanoTime);
        a aVar2 = f2039b;
        while (aVar2.g != null && jB >= aVar2.g.b(jNanoTime)) {
            aVar2 = aVar2.g;
        }
        aVar.g = aVar2.g;
        aVar2.g = aVar;
        if (aVar2 == f2039b) {
            a.class.notify();
        }
    }

    public final boolean a_() {
        if (!this.f) {
            return false;
        }
        this.f = false;
        return a(this);
    }

    private static synchronized boolean a(a aVar) {
        for (a aVar2 = f2039b; aVar2 != null; aVar2 = aVar2.g) {
            if (aVar2.g == aVar) {
                aVar2.g = aVar.g;
                aVar.g = null;
                return false;
            }
        }
        return true;
    }

    private long b(long j) {
        return this.h - j;
    }

    public final x a(final x xVar) {
        return new x() { // from class: c.a.1
            @Override // c.x
            public void a_(c cVar, long j) throws IOException {
                ab.a(cVar.f2132c, 0L, j);
                while (true) {
                    long j2 = 0;
                    if (j <= 0) {
                        return;
                    }
                    u uVar = cVar.f2131b;
                    while (true) {
                        if (j2 >= PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                            break;
                        }
                        j2 += uVar.e - uVar.d;
                        if (j2 >= j) {
                            j2 = j;
                            break;
                        }
                        uVar = uVar.h;
                    }
                    a.this.c();
                    try {
                        try {
                            xVar.a_(cVar, j2);
                            j -= j2;
                            a.this.a(true);
                        } catch (IOException e2) {
                            throw a.this.b(e2);
                        }
                    } catch (Throwable th) {
                        a.this.a(false);
                        throw th;
                    }
                }
            }

            @Override // c.x, java.io.Flushable
            public void flush() throws IOException {
                a.this.c();
                try {
                    try {
                        xVar.flush();
                        a.this.a(true);
                    } catch (IOException e2) {
                        throw a.this.b(e2);
                    }
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }

            @Override // c.x, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                a.this.c();
                try {
                    try {
                        xVar.close();
                        a.this.a(true);
                    } catch (IOException e2) {
                        throw a.this.b(e2);
                    }
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }

            @Override // c.x
            public z a() {
                return a.this;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + xVar + ")";
            }
        };
    }

    public final y a(final y yVar) {
        return new y() { // from class: c.a.2
            @Override // c.y
            public long a(c cVar, long j) throws IOException {
                a.this.c();
                try {
                    try {
                        long jA = yVar.a(cVar, j);
                        a.this.a(true);
                        return jA;
                    } catch (IOException e2) {
                        throw a.this.b(e2);
                    }
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }

            @Override // c.y, java.io.Closeable, java.lang.AutoCloseable
            public void close() throws IOException {
                try {
                    try {
                        yVar.close();
                        a.this.a(true);
                    } catch (IOException e2) {
                        throw a.this.b(e2);
                    }
                } catch (Throwable th) {
                    a.this.a(false);
                    throw th;
                }
            }

            @Override // c.y
            public z a() {
                return a.this;
            }

            public String toString() {
                return "AsyncTimeout.source(" + yVar + ")";
            }
        };
    }

    final void a(boolean z) throws IOException {
        if (a_() && z) {
            throw a((IOException) null);
        }
    }

    final IOException b(IOException iOException) throws IOException {
        return !a_() ? iOException : a(iOException);
    }

    protected IOException a(@Nullable IOException iOException) {
        InterruptedIOException interruptedIOException = new InterruptedIOException("timeout");
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* renamed from: c.a$a, reason: collision with other inner class name */
    private static final class C0070a extends Thread {
        C0070a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        /* JADX WARN: Code restructure failed: missing block: B:14:0x0015, code lost:
        
            r1.a();
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
                java.lang.Class<c.a> r0 = c.a.class
                monitor-enter(r0)     // Catch: java.lang.InterruptedException -> L0
                c.a r1 = c.a.e()     // Catch: java.lang.Throwable -> L19
                if (r1 != 0) goto Lb
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                goto L0
            Lb:
                c.a r2 = c.a.f2039b     // Catch: java.lang.Throwable -> L19
                if (r1 != r2) goto L14
                r1 = 0
                c.a.f2039b = r1     // Catch: java.lang.Throwable -> L19
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                return
            L14:
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                r1.a()     // Catch: java.lang.InterruptedException -> L0
                goto L0
            L19:
                r1 = move-exception
                monitor-exit(r0)     // Catch: java.lang.Throwable -> L19
                throw r1     // Catch: java.lang.InterruptedException -> L0
            */
            throw new UnsupportedOperationException("Method not decompiled: c.a.C0070a.run():void");
        }
    }

    @Nullable
    static a e() throws InterruptedException {
        a aVar = f2039b.g;
        if (aVar == null) {
            long jNanoTime = System.nanoTime();
            a.class.wait(d);
            if (f2039b.g != null || System.nanoTime() - jNanoTime < e) {
                return null;
            }
            return f2039b;
        }
        long jB = aVar.b(System.nanoTime());
        if (jB > 0) {
            long j = jB / 1000000;
            a.class.wait(j, (int) (jB - (1000000 * j)));
            return null;
        }
        f2039b.g = aVar.g;
        aVar.g = null;
        return aVar;
    }
}
