package c;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class z {

    /* renamed from: c, reason: collision with root package name */
    public static final z f2188c = new z() { // from class: c.z.1
        @Override // c.z
        public z a(long j) {
            return this;
        }

        @Override // c.z
        public z a(long j, TimeUnit timeUnit) {
            return this;
        }

        @Override // c.z
        public void g() throws IOException {
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private boolean f2189a;

    /* renamed from: b, reason: collision with root package name */
    private long f2190b;
    private long d;

    public z a(long j, TimeUnit timeUnit) {
        if (j >= 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            this.d = timeUnit.toNanos(j);
            return this;
        }
        throw new IllegalArgumentException("timeout < 0: " + j);
    }

    public long b_() {
        return this.d;
    }

    public boolean c_() {
        return this.f2189a;
    }

    public long d() {
        if (!this.f2189a) {
            throw new IllegalStateException("No deadline");
        }
        return this.f2190b;
    }

    public z a(long j) {
        this.f2189a = true;
        this.f2190b = j;
        return this;
    }

    public final z b(long j, TimeUnit timeUnit) {
        if (j > 0) {
            if (timeUnit == null) {
                throw new IllegalArgumentException("unit == null");
            }
            return a(System.nanoTime() + timeUnit.toNanos(j));
        }
        throw new IllegalArgumentException("duration <= 0: " + j);
    }

    public z d_() {
        this.d = 0L;
        return this;
    }

    public z f() {
        this.f2189a = false;
        return this;
    }

    public void g() throws IOException {
        if (Thread.interrupted()) {
            throw new InterruptedIOException("thread interrupted");
        }
        if (this.f2189a && this.f2190b - System.nanoTime() <= 0) {
            throw new InterruptedIOException("deadline reached");
        }
    }

    public final void a(Object obj) throws InterruptedException, InterruptedIOException {
        try {
            boolean zC_ = c_();
            long jB_ = b_();
            long jNanoTime = 0;
            if (!zC_ && jB_ == 0) {
                obj.wait();
                return;
            }
            long jNanoTime2 = System.nanoTime();
            if (zC_ && jB_ != 0) {
                jB_ = Math.min(jB_, d() - jNanoTime2);
            } else if (zC_) {
                jB_ = d() - jNanoTime2;
            }
            if (jB_ > 0) {
                long j = jB_ / 1000000;
                obj.wait(j, (int) (jB_ - (1000000 * j)));
                jNanoTime = System.nanoTime() - jNanoTime2;
            }
            if (jNanoTime >= jB_) {
                throw new InterruptedIOException("timeout");
            }
        } catch (InterruptedException unused) {
            throw new InterruptedIOException("interrupted");
        }
    }
}
