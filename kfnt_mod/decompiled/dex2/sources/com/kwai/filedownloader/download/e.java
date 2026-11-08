package com.kwai.filedownloader.download;

import android.os.SystemClock;
import java.io.IOException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class e {
    long a;
    private final f b;
    private final int c;
    private final int d;
    private final c e;
    private final com.kwai.filedownloader.kwai.b f;
    private final boolean g;
    private final long h;
    private final long i;
    private final long j;
    private final String k;
    private com.kwai.filedownloader.d.a l;
    private volatile boolean m;
    private final com.kwai.filedownloader.a.a n;
    private volatile long o;
    private volatile long p;

    public static class a {
        c a;
        com.kwai.filedownloader.kwai.b b;
        com.kwai.filedownloader.download.a c;
        f d;
        String e;
        Boolean f;
        Integer g;
        Integer h;

        public a a(int i) {
            this.g = Integer.valueOf(i);
            return this;
        }

        public a a(com.kwai.filedownloader.download.a aVar) {
            this.c = aVar;
            return this;
        }

        public a a(c cVar) {
            this.a = cVar;
            return this;
        }

        public a a(f fVar) {
            this.d = fVar;
            return this;
        }

        public a a(com.kwai.filedownloader.kwai.b bVar) {
            this.b = bVar;
            return this;
        }

        public a a(String str) {
            this.e = str;
            return this;
        }

        public a a(boolean z) {
            this.f = Boolean.valueOf(z);
            return this;
        }

        public e a() {
            if (this.f == null || this.b == null || this.c == null || this.d == null || this.e == null || this.h == null || this.g == null) {
                throw new IllegalArgumentException();
            }
            return new e(this.b, this.c, this.a, this.h.intValue(), this.g.intValue(), this.f.booleanValue(), this.d, this.e);
        }

        public a b(int i) {
            this.h = Integer.valueOf(i);
            return this;
        }
    }

    private e(com.kwai.filedownloader.kwai.b bVar, com.kwai.filedownloader.download.a aVar, c cVar, int i, int i2, boolean z, f fVar, String str) {
        this.o = 0L;
        this.p = 0L;
        this.b = fVar;
        this.k = str;
        this.f = bVar;
        this.g = z;
        this.e = cVar;
        this.d = i2;
        this.c = i;
        this.n = b.a().c();
        this.h = aVar.a;
        this.i = aVar.c;
        this.a = aVar.b;
        this.j = aVar.d;
    }

    private void c() {
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        if (com.kwai.filedownloader.e.f.a(this.a - this.o, jElapsedRealtime - this.p)) {
            d();
            this.o = this.a;
            this.p = jElapsedRealtime;
        }
    }

    private void d() {
        boolean z;
        long jUptimeMillis = SystemClock.uptimeMillis();
        try {
            this.l.a();
            z = true;
        } catch (IOException e) {
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "Because of the system cannot guarantee that all the buffers have been synchronized with physical media, or write to filefailed, we just not flushAndSync process to database too %s", e);
            }
            z = false;
        }
        if (z) {
            if (this.e != null) {
                this.n.a(this.c, this.d, this.a);
            } else {
                this.b.c();
            }
            if (com.kwai.filedownloader.e.d.a) {
                com.kwai.filedownloader.e.d.c(this, "require flushAndSync id[%d] index[%d] offset[%d], consume[%d]", Integer.valueOf(this.c), Integer.valueOf(this.d), Long.valueOf(this.a), Long.valueOf(SystemClock.uptimeMillis() - jUptimeMillis));
            }
        }
    }

    public void a() {
        this.m = true;
    }

    /* JADX WARN: Code restructure failed: missing block: B:122:0x01e6, code lost:
    
        throw new com.kwai.filedownloader.exception.FileDownloadNetworkPolicyException();
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void b() throws java.lang.Throwable {
        /*
            Method dump skipped, instructions count: 573
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.kwai.filedownloader.download.e.b():void");
    }
}
