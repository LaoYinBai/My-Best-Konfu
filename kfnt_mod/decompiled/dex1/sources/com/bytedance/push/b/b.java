package com.bytedance.push.b;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class b extends InputStream {

    /* renamed from: a, reason: collision with root package name */
    private final InputStream f4753a;

    /* renamed from: b, reason: collision with root package name */
    private long f4754b;

    /* renamed from: c, reason: collision with root package name */
    private long f4755c;
    private long d;
    private long e;

    public b(InputStream inputStream) {
        this(inputStream, 4096);
    }

    public b(InputStream inputStream, int i) {
        this.e = -1L;
        this.f4753a = inputStream.markSupported() ? inputStream : new BufferedInputStream(inputStream, i);
    }

    @Override // java.io.InputStream
    public void mark(int i) {
        this.e = a(i);
    }

    public long a(int i) throws IOException {
        long j = this.f4754b + i;
        if (this.d < j) {
            b(j);
        }
        return this.f4754b;
    }

    private void b(long j) throws IOException {
        try {
            if (this.f4755c < this.f4754b && this.f4754b <= this.d) {
                this.f4753a.reset();
                this.f4753a.mark((int) (j - this.f4755c));
                a(this.f4755c, this.f4754b);
            } else {
                this.f4755c = this.f4754b;
                this.f4753a.mark((int) (j - this.f4754b));
            }
            this.d = j;
        } catch (IOException e) {
            throw new IllegalStateException("Unable to mark: " + e);
        }
    }

    @Override // java.io.InputStream
    public void reset() throws IOException {
        a(this.e);
    }

    public void a(long j) throws IOException {
        if (this.f4754b > this.d || j < this.f4755c) {
            throw new IOException("Cannot reset");
        }
        this.f4753a.reset();
        a(this.f4755c, j);
        this.f4754b = j;
    }

    private void a(long j, long j2) throws IOException {
        while (j < j2) {
            long jSkip = this.f4753a.skip(j2 - j);
            if (jSkip == 0) {
                if (read() == -1) {
                    return;
                } else {
                    jSkip = 1;
                }
            }
            j += jSkip;
        }
    }

    @Override // java.io.InputStream
    public int read() throws IOException {
        int i = this.f4753a.read();
        if (i != -1) {
            this.f4754b++;
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr) throws IOException {
        int i = this.f4753a.read(bArr);
        if (i != -1) {
            this.f4754b += i;
        }
        return i;
    }

    @Override // java.io.InputStream
    public int read(byte[] bArr, int i, int i2) throws IOException {
        int i3 = this.f4753a.read(bArr, i, i2);
        if (i3 != -1) {
            this.f4754b += i3;
        }
        return i3;
    }

    @Override // java.io.InputStream
    public long skip(long j) throws IOException {
        long jSkip = this.f4753a.skip(j);
        this.f4754b += jSkip;
        return jSkip;
    }

    @Override // java.io.InputStream
    public int available() throws IOException {
        return this.f4753a.available();
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.f4753a.close();
    }

    @Override // java.io.InputStream
    public boolean markSupported() {
        return this.f4753a.markSupported();
    }
}
