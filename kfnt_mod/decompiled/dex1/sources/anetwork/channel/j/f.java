package anetwork.channel.j;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class f implements Future<anetwork.channel.f> {

    /* renamed from: a, reason: collision with root package name */
    private m f718a;

    /* renamed from: b, reason: collision with root package name */
    private boolean f719b;

    @Override // java.util.concurrent.Future
    public /* synthetic */ anetwork.channel.f get(long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return b();
    }

    public f(m mVar) {
        this.f718a = mVar;
    }

    @Override // java.util.concurrent.Future
    public boolean cancel(boolean z) {
        if (!this.f719b) {
            this.f718a.b();
            this.f719b = true;
        }
        return true;
    }

    @Override // java.util.concurrent.Future
    public boolean isCancelled() {
        return this.f719b;
    }

    @Override // java.util.concurrent.Future
    public boolean isDone() {
        throw new RuntimeException("NOT SUPPORT!");
    }

    @Override // java.util.concurrent.Future
    /* renamed from: a, reason: merged with bridge method [inline-methods] */
    public anetwork.channel.f get() throws ExecutionException, InterruptedException {
        throw new RuntimeException("NOT SUPPORT!");
    }

    public anetwork.channel.f b() throws ExecutionException, InterruptedException, TimeoutException {
        throw new RuntimeException("NOT SUPPORT!");
    }
}
