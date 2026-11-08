package anet.channel.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
class a extends ThreadPoolExecutor {
    public a(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Runnable runnable, T t) {
        return new C0008a(runnable, t);
    }

    @Override // java.util.concurrent.AbstractExecutorService
    protected <T> RunnableFuture<T> newTaskFor(Callable<T> callable) {
        return new C0008a(callable);
    }

    /* renamed from: anet.channel.thread.a$a, reason: collision with other inner class name */
    class C0008a<V> extends FutureTask<V> implements Comparable<C0008a<V>> {

        /* renamed from: b, reason: collision with root package name */
        private Object f600b;

        public C0008a(Callable<V> callable) {
            super(callable);
            this.f600b = callable;
        }

        public C0008a(Runnable runnable, V v) {
            super(runnable, v);
            this.f600b = runnable;
        }

        @Override // java.lang.Comparable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(C0008a<V> c0008a) {
            if (this == c0008a) {
                return 0;
            }
            if (c0008a == null) {
                return -1;
            }
            if (this.f600b == null || c0008a.f600b == null || !this.f600b.getClass().equals(c0008a.f600b.getClass()) || !(this.f600b instanceof Comparable)) {
                return 0;
            }
            return ((Comparable) this.f600b).compareTo(c0008a.f600b);
        }
    }
}
