package cn.com.chinatelecom.account.api.c;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d extends ThreadPoolExecutor {

    /* renamed from: a, reason: collision with root package name */
    private static final BlockingQueue<Runnable> f2251a = new LinkedBlockingQueue(256);

    /* renamed from: b, reason: collision with root package name */
    private static final ThreadFactory f2252b = new ThreadFactory() { // from class: cn.com.chinatelecom.account.api.c.d.1

        /* renamed from: a, reason: collision with root package name */
        private final AtomicInteger f2253a = new AtomicInteger(1);

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable);
        }
    };

    public d() {
        this(5);
    }

    public d(int i) {
        this(i, i * 2, 1L, TimeUnit.SECONDS, f2251a, f2252b);
    }

    public d(int i, int i2, long j, TimeUnit timeUnit, BlockingQueue<Runnable> blockingQueue, ThreadFactory threadFactory) {
        super(i, i2, j, timeUnit, blockingQueue, threadFactory);
    }

    public void a(e eVar) {
        execute(eVar);
    }
}
