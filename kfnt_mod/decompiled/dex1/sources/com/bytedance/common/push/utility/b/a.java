package com.bytedance.common.push.utility.b;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: b, reason: collision with root package name */
    private static ScheduledExecutorService f3829b;

    /* renamed from: c, reason: collision with root package name */
    private static final int f3830c = Runtime.getRuntime().availableProcessors();
    private static final int d = Math.max(2, Math.min(f3830c - 1, 5));
    private static final int e = (f3830c * 2) + 1;
    private static final ThreadFactoryC0110a f = new ThreadFactoryC0110a("TTDefaultExecutors");
    private static final ThreadFactoryC0110a g = new ThreadFactoryC0110a("TTScheduledExecutors");
    private static final BlockingQueue<Runnable> h = new LinkedBlockingQueue();
    private static final RejectedExecutionHandler i = new RejectedExecutionHandler() { // from class: com.bytedance.common.push.utility.b.a.1
        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            Executors.newCachedThreadPool().execute(runnable);
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static ExecutorService f3828a = new ThreadPoolExecutor(d, e, 30, TimeUnit.SECONDS, h, f, i);

    static {
        ((ThreadPoolExecutor) f3828a).allowCoreThreadTimeOut(true);
        f3829b = Executors.newScheduledThreadPool(3, g);
    }

    /* renamed from: com.bytedance.common.push.utility.b.a$a, reason: collision with other inner class name */
    private static class ThreadFactoryC0110a implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        private static final AtomicInteger f3831a = new AtomicInteger(1);

        /* renamed from: b, reason: collision with root package name */
        private final ThreadGroup f3832b;

        /* renamed from: c, reason: collision with root package name */
        private final AtomicInteger f3833c = new AtomicInteger(1);
        private final String d;

        ThreadFactoryC0110a(String str) {
            ThreadGroup threadGroup;
            SecurityManager securityManager = System.getSecurityManager();
            if (securityManager != null) {
                threadGroup = securityManager.getThreadGroup();
            } else {
                threadGroup = Thread.currentThread().getThreadGroup();
            }
            this.f3832b = threadGroup;
            this.d = str + "-" + f3831a.getAndIncrement() + "-Thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.f3832b, runnable, this.d + this.f3833c.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    public static ExecutorService a() {
        return f3828a;
    }
}
