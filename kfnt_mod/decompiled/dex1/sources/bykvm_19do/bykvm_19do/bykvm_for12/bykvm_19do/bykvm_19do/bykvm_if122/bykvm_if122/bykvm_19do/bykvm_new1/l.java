package bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_if122.bykvm_if122.bykvm_19do.bykvm_new1;

import java.util.concurrent.CountDownLatch;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class l {

    /* renamed from: a, reason: collision with root package name */
    private final CountDownLatch f1651a = new CountDownLatch(1);

    /* renamed from: b, reason: collision with root package name */
    private long f1652b = -1;

    /* renamed from: c, reason: collision with root package name */
    private long f1653c = -1;

    l() {
    }

    void a() {
        if (this.f1653c == -1) {
            long j = this.f1652b;
            if (j != -1) {
                this.f1653c = j - 1;
                this.f1651a.countDown();
                return;
            }
        }
        throw new IllegalStateException();
    }

    void b() {
        if (this.f1653c != -1 || this.f1652b == -1) {
            throw new IllegalStateException();
        }
        this.f1653c = System.nanoTime();
        this.f1651a.countDown();
    }

    void c() {
        if (this.f1652b != -1) {
            throw new IllegalStateException();
        }
        this.f1652b = System.nanoTime();
    }
}
