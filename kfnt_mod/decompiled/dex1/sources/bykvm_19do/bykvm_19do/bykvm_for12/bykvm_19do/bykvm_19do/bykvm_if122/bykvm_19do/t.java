package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class t {
    public static final t d = new a();

    /* renamed from: a, reason: collision with root package name */
    private boolean f1492a;

    /* renamed from: b, reason: collision with root package name */
    private long f1493b;

    /* renamed from: c, reason: collision with root package name */
    private long f1494c;

    static class a extends t {
        a() {
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.t
        public t a(long j) {
            return this;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.t
        public t a(long j, TimeUnit timeUnit) {
            return this;
        }

        @Override // bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.t
        public void e() throws IOException {
        }
    }

    public t a(long j, TimeUnit timeUnit) {
        if (j >= 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            this.f1494c = timeUnit.toNanos(j);
            return this;
        }
        throw new IllegalArgumentException("timeout < 0: " + j);
    }

    public t b() {
        this.f1494c = 0L;
        return this;
    }

    public long c() {
        if (this.f1492a) {
            return this.f1493b;
        }
        throw new IllegalStateException("No deadline");
    }

    public boolean d() {
        return this.f1492a;
    }

    public void e() throws IOException {
        if (Thread.interrupted()) {
            Thread.currentThread().interrupt();
            throw new InterruptedIOException("interrupted");
        }
        if (this.f1492a && this.f1493b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public long f() {
        return this.f1494c;
    }

    public t a(long j) {
        this.f1492a = true;
        this.f1493b = j;
        return this;
    }

    public t a() {
        this.f1492a = false;
        return this;
    }
}
