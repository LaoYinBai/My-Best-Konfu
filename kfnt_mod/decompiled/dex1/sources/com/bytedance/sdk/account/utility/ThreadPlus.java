package com.bytedance.sdk.account.utility;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ThreadPlus implements Runnable {
    static final ExecutorService CACHED_EXECUTOR = Executors.newCachedThreadPool(new SimpleThreadFactory("ThreadPlus-cached", true));
    static final ExecutorService FIXED_EXECUTOR = Executors.newFixedThreadPool(5, new SimpleThreadFactory("ThreadPlus-fixed", true));
    protected static final AtomicInteger sCount = new AtomicInteger();
    private final boolean mBackground;
    private Runnable runnable;

    public ThreadPlus(Runnable runnable, String str, boolean z) {
        this.runnable = runnable;
        this.mBackground = z;
    }

    public ThreadPlus(String str) {
        this(false);
    }

    public ThreadPlus() {
        this(false);
    }

    public ThreadPlus(boolean z) {
        this.mBackground = z;
    }

    @Override // java.lang.Runnable
    public void run() {
        if (this.runnable != null) {
            this.runnable.run();
        }
    }

    public void start() {
        Runnable runnable = Logger.debug() ? new Runnable() { // from class: com.bytedance.sdk.account.utility.ThreadPlus.1
            @Override // java.lang.Runnable
            public void run() {
                Logger.d("ThreadPlus", "thread count: " + ThreadPlus.sCount.incrementAndGet());
                try {
                    ThreadPlus.this.run();
                } catch (Exception e) {
                    Logger.w("ThreadPlus", "Thread crashed!", e);
                }
                Logger.d("ThreadPlus", "thread count: " + ThreadPlus.sCount.decrementAndGet());
            }
        } : this;
        if (this.mBackground) {
            FIXED_EXECUTOR.submit(runnable);
        } else {
            CACHED_EXECUTOR.submit(runnable);
        }
    }

    public static void shutdown() {
        CACHED_EXECUTOR.shutdown();
        FIXED_EXECUTOR.shutdown();
    }

    public static void submitRunnable(Runnable runnable) {
        if (runnable != null) {
            CACHED_EXECUTOR.submit(runnable);
        }
    }
}
