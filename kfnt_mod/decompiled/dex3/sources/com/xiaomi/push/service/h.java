package com.xiaomi.push.service;

import android.os.SystemClock;
import com.qq.e.comm.compliance.ApkDownloadComplianceInterface;
import com.ss.android.downloadlib.constants.DownloadConstants;
import java.util.concurrent.RejectedExecutionException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class h {
    private static long a;
    private static long b;
    private static long c;

    /* renamed from: a, reason: collision with other field name */
    private final a f948a;

    /* renamed from: a, reason: collision with other field name */
    private final c f949a;

    private static final class a {
        private final c a;

        a(c cVar) {
            this.a = cVar;
        }

        protected void finalize() throws Throwable {
            try {
                synchronized (this.a) {
                    this.a.c = true;
                    this.a.notify();
                }
            } finally {
                super.finalize();
            }
        }
    }

    public static abstract class b implements Runnable {
        protected int a;

        public b(int i) {
            this.a = i;
        }
    }

    private static final class c extends Thread {

        /* renamed from: b, reason: collision with other field name */
        private boolean f952b;
        private boolean c;
        private volatile long a = 0;

        /* renamed from: a, reason: collision with other field name */
        private volatile boolean f951a = false;
        private long b = 50;

        /* renamed from: a, reason: collision with other field name */
        private a f950a = new a();

        private static final class a {
            private int a;

            /* renamed from: a, reason: collision with other field name */
            private d[] f953a;
            private int b;
            private int c;

            private a() {
                this.a = ApkDownloadComplianceInterface.INSTALL_BITS;
                this.f953a = new d[this.a];
                this.b = 0;
                this.c = 0;
            }

            /* JADX INFO: Access modifiers changed from: private */
            public int a(d dVar) {
                for (int i = 0; i < this.f953a.length; i++) {
                    if (this.f953a[i] == dVar) {
                        return i;
                    }
                }
                return -1;
            }

            private void c() {
                int i = this.b - 1;
                int i2 = (i - 1) / 2;
                while (this.f953a[i].f954a < this.f953a[i2].f954a) {
                    d dVar = this.f953a[i];
                    this.f953a[i] = this.f953a[i2];
                    this.f953a[i2] = dVar;
                    int i3 = i2;
                    i2 = (i2 - 1) / 2;
                    i = i3;
                }
            }

            private void c(int i) {
                int i2 = (i * 2) + 1;
                while (i2 < this.b && this.b > 0) {
                    int i3 = i2 + 1;
                    if (i3 < this.b && this.f953a[i3].f954a < this.f953a[i2].f954a) {
                        i2 = i3;
                    }
                    if (this.f953a[i].f954a < this.f953a[i2].f954a) {
                        return;
                    }
                    d dVar = this.f953a[i];
                    this.f953a[i] = this.f953a[i2];
                    this.f953a[i2] = dVar;
                    int i4 = i2;
                    i2 = (i2 * 2) + 1;
                    i = i4;
                }
            }

            public d a() {
                return this.f953a[0];
            }

            /* renamed from: a, reason: collision with other method in class */
            public void m633a() {
                this.f953a = new d[this.a];
                this.b = 0;
            }

            public void a(int i) {
                for (int i2 = 0; i2 < this.b; i2++) {
                    if (this.f953a[i2].a == i) {
                        this.f953a[i2].a();
                    }
                }
                b();
            }

            public void a(int i, b bVar) {
                for (int i2 = 0; i2 < this.b; i2++) {
                    if (this.f953a[i2].f955a == bVar) {
                        this.f953a[i2].a();
                    }
                }
                b();
            }

            /* renamed from: a, reason: collision with other method in class */
            public void m634a(d dVar) {
                if (this.f953a.length == this.b) {
                    d[] dVarArr = new d[this.b * 2];
                    System.arraycopy(this.f953a, 0, dVarArr, 0, this.b);
                    this.f953a = dVarArr;
                }
                d[] dVarArr2 = this.f953a;
                int i = this.b;
                this.b = i + 1;
                dVarArr2[i] = dVar;
                c();
            }

            /* renamed from: a, reason: collision with other method in class */
            public boolean m635a() {
                return this.b == 0;
            }

            /* renamed from: a, reason: collision with other method in class */
            public boolean m636a(int i) {
                for (int i2 = 0; i2 < this.b; i2++) {
                    if (this.f953a[i2].a == i) {
                        return true;
                    }
                }
                return false;
            }

            public void b() {
                int i = 0;
                while (i < this.b) {
                    if (this.f953a[i].f957a) {
                        this.c++;
                        b(i);
                        i--;
                    }
                    i++;
                }
            }

            public void b(int i) {
                if (i < 0 || i >= this.b) {
                    return;
                }
                d[] dVarArr = this.f953a;
                d[] dVarArr2 = this.f953a;
                int i2 = this.b - 1;
                this.b = i2;
                dVarArr[i] = dVarArr2[i2];
                this.f953a[this.b] = null;
                c(i);
            }
        }

        c(String str, boolean z) {
            setName(str);
            setDaemon(z);
            start();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void a(d dVar) {
            this.f950a.m634a(dVar);
            notify();
        }

        public synchronized void a() {
            this.f952b = true;
            this.f950a.m633a();
            notify();
        }

        /* renamed from: a, reason: collision with other method in class */
        public boolean m632a() {
            return this.f951a && SystemClock.uptimeMillis() - this.a > DownloadConstants.DOWNLOAD_OPTIMIZATION_SPACE_INTERNAL;
        }

        /* JADX WARN: Code restructure failed: missing block: B:50:0x0097, code lost:
        
            r10.a = android.os.SystemClock.uptimeMillis();
            r10.f951a = true;
            r2.f955a.run();
            r10.f951a = false;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x00a8, code lost:
        
            r1 = move-exception;
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x00a9, code lost:
        
            monitor-enter(r10);
         */
        /* JADX WARN: Code restructure failed: missing block: B:54:0x00aa, code lost:
        
            r10.f952b = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x00ad, code lost:
        
            throw r1;
         */
        @Override // java.lang.Thread, java.lang.Runnable
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public void run() {
            /*
                Method dump skipped, instructions count: 186
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.service.h.c.run():void");
        }
    }

    static class d {
        int a;

        /* renamed from: a, reason: collision with other field name */
        long f954a;

        /* renamed from: a, reason: collision with other field name */
        b f955a;

        /* renamed from: a, reason: collision with other field name */
        final Object f956a = new Object();

        /* renamed from: a, reason: collision with other field name */
        boolean f957a;
        private long b;

        d() {
        }

        void a(long j) {
            synchronized (this.f956a) {
                this.b = j;
            }
        }

        public boolean a() {
            boolean z;
            synchronized (this.f956a) {
                z = !this.f957a && this.f954a > 0;
                this.f957a = true;
            }
            return z;
        }
    }

    static {
        a = SystemClock.elapsedRealtime() > 0 ? SystemClock.elapsedRealtime() : 0L;
        b = a;
    }

    public h() {
        this(false);
    }

    public h(String str) {
        this(str, false);
    }

    public h(String str, boolean z) {
        if (str == null) {
            throw new NullPointerException("name == null");
        }
        this.f949a = new c(str, z);
        this.f948a = new a(this.f949a);
    }

    public h(boolean z) {
        this("Timer-" + b(), z);
    }

    static synchronized long a() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (jElapsedRealtime > b) {
            a += jElapsedRealtime - b;
        }
        b = jElapsedRealtime;
        return a;
    }

    private static synchronized long b() {
        long j;
        j = c;
        c = 1 + j;
        return j;
    }

    private void b(b bVar, long j) {
        synchronized (this.f949a) {
            if (this.f949a.f952b) {
                throw new IllegalStateException("Timer was canceled");
            }
            long jA = j + a();
            if (jA < 0) {
                throw new IllegalArgumentException("Illegal delay to start the TimerTask: " + jA);
            }
            d dVar = new d();
            dVar.a = bVar.a;
            dVar.f955a = bVar;
            dVar.f954a = jA;
            this.f949a.a(dVar);
        }
    }

    /* renamed from: a, reason: collision with other method in class */
    public void m627a() {
        com.xiaomi.channel.commonutils.logger.b.m22a("quit. finalizer:" + this.f948a);
        this.f949a.a();
    }

    public void a(int i) {
        synchronized (this.f949a) {
            this.f949a.f950a.a(i);
        }
    }

    public void a(int i, b bVar) {
        synchronized (this.f949a) {
            this.f949a.f950a.a(i, bVar);
        }
    }

    public void a(b bVar) {
        if (com.xiaomi.channel.commonutils.logger.b.a() >= 1 || Thread.currentThread() == this.f949a) {
            bVar.run();
        } else {
            com.xiaomi.channel.commonutils.logger.b.d("run job outside job job thread");
            throw new RejectedExecutionException("Run job outside job thread");
        }
    }

    public void a(b bVar, long j) {
        if (j >= 0) {
            b(bVar, j);
            return;
        }
        throw new IllegalArgumentException("delay < 0: " + j);
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m628a() {
        return this.f949a.m632a();
    }

    /* renamed from: a, reason: collision with other method in class */
    public boolean m629a(int i) {
        boolean zM636a;
        synchronized (this.f949a) {
            zM636a = this.f949a.f950a.m636a(i);
        }
        return zM636a;
    }

    /* renamed from: b, reason: collision with other method in class */
    public void m630b() {
        synchronized (this.f949a) {
            this.f949a.f950a.m633a();
        }
    }
}
