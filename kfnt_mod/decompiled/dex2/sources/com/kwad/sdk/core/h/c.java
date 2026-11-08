package com.kwad.sdk.core.h;

import androidx.annotation.NonNull;
import androidx.annotation.WorkerThread;
import com.mbridge.msdk.playercommon.exoplayer2.upstream.cache.CacheDataSink;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class c extends InputStream {
    private InputStream d;
    private int e;
    private volatile float g;
    private volatile long h;
    private int a = -1;
    private int b = 10000;
    private long c = -1;
    private long f = -1;
    private int i = CacheDataSink.DEFAULT_BUFFER_SIZE;

    c(@NonNull InputStream inputStream, int i) {
        i = i < this.i ? this.i : i;
        this.d = inputStream;
        this.g = i / 1000.0f;
    }

    private long a(long j, long j2) {
        if (j <= 0) {
            return 0L;
        }
        if (j2 <= 0) {
            return -1L;
        }
        return j / j2;
    }

    @WorkerThread
    private void a(long j) throws InterruptedException {
        try {
            Thread.sleep(j);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void b() {
        this.a = 0;
        this.c = System.currentTimeMillis();
    }

    private void c() throws InterruptedException {
        if (this.a < this.b) {
            return;
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        long j = jCurrentTimeMillis - this.c;
        float f = this.a / this.g;
        this.h = a(this.e, jCurrentTimeMillis - this.f);
        float f2 = j;
        if (f > f2) {
            a((long) (f - f2));
        }
        b();
    }

    public long a() {
        return this.h;
    }

    @Override // java.io.InputStream
    public int available() {
        return this.d.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.d.close();
        b.a(this);
        this.f = -1L;
    }

    @Override // java.io.InputStream
    public synchronized void mark(int i) {
        this.d.mark(i);
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.d.markSupported();
    }

    @Override // java.io.InputStream
    public int read() throws InterruptedException, IOException {
        if (this.f <= 0) {
            this.f = System.currentTimeMillis();
        }
        this.e++;
        if (!(b.b && b.a)) {
            return this.d.read();
        }
        if (this.a < 0) {
            b();
        }
        int i = this.d.read();
        this.a++;
        c();
        return i;
    }

    @Override // java.io.InputStream
    public synchronized void reset() {
        this.d.reset();
    }

    @Override // java.io.InputStream
    public long skip(long j) {
        return this.d.skip(j);
    }
}
