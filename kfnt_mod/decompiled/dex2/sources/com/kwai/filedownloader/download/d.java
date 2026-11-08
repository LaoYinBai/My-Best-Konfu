package com.kwai.filedownloader.download;

import android.database.sqlite.SQLiteFullException;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.os.SystemClock;
import com.kwai.filedownloader.exception.FileDownloadGiveUpRetryException;
import com.kwai.filedownloader.exception.FileDownloadOutOfSpaceException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class d implements Handler.Callback {
    private final com.kwai.filedownloader.c.c a;
    private final a c;
    private final int d;
    private final int e;
    private final int f;
    private long g;
    private Handler h;
    private HandlerThread i;
    private volatile Thread k;
    private volatile boolean n;
    private volatile boolean j = false;
    private volatile long l = 0;
    private final AtomicLong m = new AtomicLong();
    private boolean o = true;
    private final com.kwai.filedownloader.a.a b = b.a().c();

    public static class a {
        private boolean a;
        private Exception b;
        private int c;

        void a(int i) {
            this.c = i;
        }

        void a(Exception exc) {
            this.b = exc;
        }

        void a(boolean z) {
            this.a = z;
        }

        public boolean a() {
            return this.a;
        }

        public Exception b() {
            return this.b;
        }

        public int c() {
            return this.c;
        }
    }

    d(com.kwai.filedownloader.c.c cVar, int i, int i2, int i3) {
        this.a = cVar;
        this.e = i2 >= 5 ? i2 : 5;
        this.f = i3;
        this.c = new a();
        this.d = i;
    }

    private static long a(long j, long j2) {
        if (j2 <= 0) {
            return -1L;
        }
        if (j == -1) {
            return 1L;
        }
        long j3 = j / (j2 + 1);
        if (j3 <= 0) {
            return 1L;
        }
        return j3;
    }

    private void a(byte b) {
        if (b != -2) {
            com.kwai.filedownloader.message.e.a().a(com.kwai.filedownloader.message.f.a(b, this.a, this.c));
        } else if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "High concurrent cause, Already paused and we don't need to call-back to Task in here, %d", Integer.valueOf(this.a.a()));
        }
    }

    private void a(long j, boolean z) {
        if (this.a.g() == this.a.h()) {
            this.b.a(this.a.a(), this.a.g());
            return;
        }
        if (this.n) {
            this.n = false;
            this.a.a((byte) 3);
        }
        if (z) {
            this.l = j;
            a((byte) 3);
            this.m.set(0L);
        }
    }

    private void a(SQLiteFullException sQLiteFullException) {
        int iA = this.a.a();
        if (com.kwai.filedownloader.e.d.a) {
            com.kwai.filedownloader.e.d.c(this, "the data of the task[%d] is dirty, because the SQLite full exception[%s], so remove it from the database directly.", Integer.valueOf(iA), sQLiteFullException.toString());
        }
        this.a.c(sQLiteFullException.toString());
        this.a.a((byte) -1);
        this.b.e(iA);
        this.b.d(iA);
    }

    private synchronized void a(Message message) {
        if (!this.i.isAlive()) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread.", Integer.valueOf(message.what));
            }
            return;
        }
        try {
            this.h.sendMessage(message);
        } catch (IllegalStateException e) {
            if (this.i.isAlive()) {
                throw e;
            }
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "require callback %d but the host thread of the flow has already dead, what is occurred because of there are several reason can final this flow on different thread.", Integer.valueOf(message.what));
            }
        }
    }

    private void a(Exception exc, int i) {
        Exception excB = b(exc);
        this.c.a(excB);
        this.c.a(this.d - i);
        this.a.a((byte) 5);
        this.a.c(excB.toString());
        this.b.a(this.a.a(), excB);
        a((byte) 5);
    }

    private Exception b(Exception exc) {
        long length;
        String strE = this.a.e();
        if ((!this.a.i() && !com.kwai.filedownloader.e.e.a().f) || !(exc instanceof IOException) || !new File(strE).exists()) {
            return exc;
        }
        long jF = com.kwai.filedownloader.e.f.f(strE);
        if (jF > 4096) {
            return exc;
        }
        File file = new File(strE);
        if (file.exists()) {
            length = file.length();
        } else {
            com.kwai.filedownloader.e.d.a(this, exc, "Exception with: free space isn't enough, and the target file not exist.", new Object[0]);
            length = 0;
        }
        return Build.VERSION.SDK_INT >= 9 ? new FileDownloadOutOfSpaceException(jF, 4096L, length, exc) : new FileDownloadOutOfSpaceException(jF, 4096L, length);
    }

    private boolean b(long j) {
        if (!this.o) {
            return this.g != -1 && this.m.get() >= this.g && j - this.l >= ((long) this.e);
        }
        this.o = false;
        return true;
    }

    private void c(Exception exc) {
        Exception excB = b(exc);
        if (excB instanceof SQLiteFullException) {
            a((SQLiteFullException) excB);
        } else {
            try {
                this.a.a((byte) -1);
                this.a.c(exc.toString());
                this.b.a(this.a.a(), excB, this.a.g());
            } catch (SQLiteFullException e) {
                excB = e;
            }
        }
        this.c.a(excB);
        a((byte) -1);
    }

    private void h() {
        String strE = this.a.e();
        String strD = this.a.d();
        File file = new File(strE);
        try {
            File file2 = new File(strD);
            if (file2.exists()) {
                long length = file2.length();
                if (!file2.delete()) {
                    throw new IOException(com.kwai.filedownloader.e.f.a("Can't delete the old file([%s], [%d]), so can't replace it with the new downloaded one.", strD, Long.valueOf(length)));
                }
                com.kwai.filedownloader.e.d.d(this, "The target file([%s], [%d]) will be replaced with the new downloaded file[%d]", strD, Long.valueOf(length), Long.valueOf(file.length()));
            }
            if (!file.renameTo(file2)) {
                throw new IOException(com.kwai.filedownloader.e.f.a("Can't rename the  temp downloaded file(%s) to the target file(%s)", strE, strD));
            }
            if (!file.exists() || file.delete()) {
                return;
            }
            com.kwai.filedownloader.e.d.d(this, "delete the temp file(%s) failed, on completed downloading.", strE);
        } catch (Throwable th) {
            if (file.exists() && !file.delete()) {
                com.kwai.filedownloader.e.d.d(this, "delete the temp file(%s) failed, on completed downloading.", strE);
            }
            throw th;
        }
    }

    private void i() {
        h();
        this.a.a((byte) -3);
        this.b.b(this.a.a(), this.a.h());
        this.b.d(this.a.a());
        a((byte) -3);
        if (com.kwai.filedownloader.e.e.a().g) {
            com.kwai.filedownloader.services.f.a(this.a);
        }
    }

    private boolean j() {
        if (this.a.i()) {
            this.a.c(this.a.g());
        } else if (this.a.g() != this.a.h()) {
            a(new FileDownloadGiveUpRetryException(com.kwai.filedownloader.e.f.a("sofar[%d] not equal total[%d]", Long.valueOf(this.a.g()), Long.valueOf(this.a.h()))));
            return true;
        }
        return false;
    }

    private void k() {
        this.a.a((byte) -2);
        this.b.c(this.a.a(), this.a.g());
        a((byte) -2);
    }

    void a(long j) {
        this.m.addAndGet(j);
        this.a.b(j);
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        boolean zB = b(jElapsedRealtime);
        if (this.h == null) {
            a(jElapsedRealtime, zB);
        } else if (zB) {
            a(this.h.obtainMessage(3));
        }
    }

    void a(Exception exc) {
        c(exc);
    }

    void a(Exception exc, int i, long j) {
        this.m.set(0L);
        this.a.b(-j);
        if (this.h == null) {
            a(exc, i);
        } else {
            a(this.h.obtainMessage(5, i, 0, exc));
        }
    }

    void a(boolean z, long j, String str, String str2) {
        String strJ = this.a.j();
        if (strJ != null && !strJ.equals(str)) {
            throw new IllegalArgumentException(com.kwai.filedownloader.e.f.a("callback onConnected must with precondition succeed, but the etag is changes(%s != %s)", str, strJ));
        }
        this.c.a(z);
        this.a.a((byte) 2);
        this.a.c(j);
        this.a.b(str);
        this.a.d(str2);
        this.b.a(this.a.a(), j, str, str2);
        a((byte) 2);
        this.g = a(j, this.f);
        this.n = true;
    }

    public boolean a() {
        return this.i != null && this.i.isAlive();
    }

    void b() {
        if (this.h != null) {
            this.h.removeCallbacksAndMessages(null);
            this.i.quit();
            this.k = Thread.currentThread();
            while (this.j) {
                LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(100L));
            }
            this.k = null;
        }
    }

    public void c() {
        this.a.a((byte) 1);
        this.b.f(this.a.a());
        a((byte) 1);
    }

    void d() {
        this.a.a((byte) 6);
        a((byte) 6);
        this.b.a(this.a.a());
    }

    void e() {
        this.i = new HandlerThread("source-status-callback", 10);
        this.i.start();
        this.h = new Handler(this.i.getLooper(), this);
    }

    void f() {
        k();
    }

    void g() {
        if (j()) {
            return;
        }
        i();
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x0024 A[DONT_GENERATE] */
    @Override // android.os.Handler.Callback
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean handleMessage(android.os.Message r5) {
        /*
            r4 = this;
            r0 = 1
            r4.j = r0
            int r1 = r5.what
            r2 = 3
            r3 = 0
            if (r1 == r2) goto L17
            r2 = 5
            if (r1 == r2) goto Ld
            goto L1e
        Ld:
            java.lang.Object r1 = r5.obj     // Catch: java.lang.Throwable -> L2a
            java.lang.Exception r1 = (java.lang.Exception) r1     // Catch: java.lang.Throwable -> L2a
            int r5 = r5.arg1     // Catch: java.lang.Throwable -> L2a
            r4.a(r1, r5)     // Catch: java.lang.Throwable -> L2a
            goto L1e
        L17:
            long r1 = android.os.SystemClock.elapsedRealtime()     // Catch: java.lang.Throwable -> L2a
            r4.a(r1, r0)     // Catch: java.lang.Throwable -> L2a
        L1e:
            r4.j = r3
            java.lang.Thread r5 = r4.k
            if (r5 == 0) goto L29
            java.lang.Thread r5 = r4.k
            java.util.concurrent.locks.LockSupport.unpark(r5)
        L29:
            return r0
        L2a:
            r5 = move-exception
            r4.j = r3
            java.lang.Thread r0 = r4.k
            if (r0 == 0) goto L36
            java.lang.Thread r0 = r4.k
            java.util.concurrent.locks.LockSupport.unpark(r0)
        L36:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.filedownloader.download.d.handleMessage(android.os.Message):boolean");
    }
}
