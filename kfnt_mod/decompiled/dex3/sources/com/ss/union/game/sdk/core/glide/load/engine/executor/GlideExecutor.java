package com.ss.union.game.sdk.core.glide.load.engine.executor;

import android.os.Process;
import android.os.StrictMode;
import android.util.Log;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class GlideExecutor implements ExecutorService {
    private static final String a = "source";
    private static final String b = "disk-cache";
    private static final int c = 1;
    private static final String d = "GlideExecutor";
    private static final String e = "source-unlimited";
    private static final String f = "animation";
    private static final long g = TimeUnit.SECONDS.toMillis(10);
    private static final int h = 4;
    private static volatile int i;
    private final ExecutorService j;

    public interface UncaughtThrowableStrategy {
        public static final UncaughtThrowableStrategy IGNORE = new UncaughtThrowableStrategy() { // from class: com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.1
            @Override // com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy
            public void handle(Throwable th) {
            }
        };
        public static final UncaughtThrowableStrategy LOG = new UncaughtThrowableStrategy() { // from class: com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.2
            @Override // com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy
            public void handle(Throwable th) {
                if (th == null || !Log.isLoggable(GlideExecutor.d, 6)) {
                    return;
                }
                Log.e(GlideExecutor.d, "Request threw uncaught throwable", th);
            }
        };
        public static final UncaughtThrowableStrategy THROW = new UncaughtThrowableStrategy() { // from class: com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.3
            @Override // com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy
            public void handle(Throwable th) {
                if (th != null) {
                    throw new RuntimeException("Request threw uncaught throwable", th);
                }
            }
        };
        public static final UncaughtThrowableStrategy DEFAULT = LOG;

        void handle(Throwable th);
    }

    public static GlideExecutor newDiskCacheExecutor() {
        return newDiskCacheExecutor(1, b, UncaughtThrowableStrategy.DEFAULT);
    }

    public static GlideExecutor newDiskCacheExecutor(UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return newDiskCacheExecutor(1, b, uncaughtThrowableStrategy);
    }

    public static GlideExecutor newDiskCacheExecutor(int i2, String str, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(i2, i2, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new a(str, uncaughtThrowableStrategy, true)));
    }

    public static GlideExecutor newSourceExecutor() {
        return newSourceExecutor(calculateBestThreadCount(), "source", UncaughtThrowableStrategy.DEFAULT);
    }

    public static GlideExecutor newSourceExecutor(UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return newSourceExecutor(calculateBestThreadCount(), "source", uncaughtThrowableStrategy);
    }

    public static GlideExecutor newSourceExecutor(int i2, String str, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(i2, i2, 0L, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new a(str, uncaughtThrowableStrategy, false)));
    }

    public static GlideExecutor newUnlimitedSourceExecutor() {
        return new GlideExecutor(new ThreadPoolExecutor(0, Integer.MAX_VALUE, g, TimeUnit.MILLISECONDS, new SynchronousQueue(), new a(e, UncaughtThrowableStrategy.DEFAULT, false)));
    }

    public static GlideExecutor newAnimationExecutor() {
        return newAnimationExecutor(calculateBestThreadCount() >= 4 ? 2 : 1, UncaughtThrowableStrategy.DEFAULT);
    }

    public static GlideExecutor newAnimationExecutor(int i2, UncaughtThrowableStrategy uncaughtThrowableStrategy) {
        return new GlideExecutor(new ThreadPoolExecutor(0, i2, g, TimeUnit.MILLISECONDS, new PriorityBlockingQueue(), new a(f, uncaughtThrowableStrategy, true)));
    }

    GlideExecutor(ExecutorService executorService) {
        this.j = executorService;
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        this.j.execute(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public Future<?> submit(Runnable runnable) {
        return this.j.submit(runnable);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) throws InterruptedException {
        return this.j.invokeAll(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws InterruptedException {
        return this.j.invokeAll(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection) throws ExecutionException, InterruptedException {
        return (T) this.j.invokeAny(collection);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) throws ExecutionException, InterruptedException, TimeoutException {
        return (T) this.j.invokeAny(collection, j, timeUnit);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Runnable runnable, T t) {
        return this.j.submit(runnable, t);
    }

    @Override // java.util.concurrent.ExecutorService
    public <T> Future<T> submit(Callable<T> callable) {
        return this.j.submit(callable);
    }

    @Override // java.util.concurrent.ExecutorService
    public void shutdown() {
        this.j.shutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public List<Runnable> shutdownNow() {
        return this.j.shutdownNow();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isShutdown() {
        return this.j.isShutdown();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean isTerminated() {
        return this.j.isTerminated();
    }

    @Override // java.util.concurrent.ExecutorService
    public boolean awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.j.awaitTermination(j, timeUnit);
    }

    public String toString() {
        return this.j.toString();
    }

    public static int calculateBestThreadCount() {
        if (i == 0) {
            i = Math.min(4, com.ss.union.game.sdk.core.glide.load.engine.executor.a.a());
        }
        return i;
    }

    private static final class a implements ThreadFactory {
        private static final int c = 9;
        final UncaughtThrowableStrategy a;
        final boolean b;
        private final String d;
        private int e;

        a(String str, UncaughtThrowableStrategy uncaughtThrowableStrategy, boolean z) {
            this.d = str;
            this.a = uncaughtThrowableStrategy;
            this.b = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public synchronized Thread newThread(Runnable runnable) {
            Thread thread;
            thread = new Thread(runnable, "glide-" + this.d + "-thread-" + this.e) { // from class: com.ss.union.game.sdk.core.glide.load.engine.executor.GlideExecutor.a.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() throws SecurityException, IllegalArgumentException {
                    Process.setThreadPriority(9);
                    if (a.this.b) {
                        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectNetwork().penaltyDeath().build());
                    }
                    try {
                        super.run();
                    } catch (Throwable th) {
                        a.this.a.handle(th);
                    }
                }
            };
            this.e = this.e + 1;
            return thread;
        }
    }
}
