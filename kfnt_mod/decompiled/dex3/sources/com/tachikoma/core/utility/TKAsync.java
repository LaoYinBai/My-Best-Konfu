package com.tachikoma.core.utility;

import android.util.Log;
import androidx.annotation.NonNull;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TKAsync {
    private static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final int MAXIMUM_POOL_SIZE;
    private static final ThreadPoolExecutor REJECT_HANDLER;
    private static final String TAG = "TKAsync";
    public static final ThreadPoolExecutor TK_EXECUTOR;

    private static class TKRejectedExecutionHandler implements RejectedExecutionHandler {
        private TKRejectedExecutionHandler() {
        }

        @Override // java.util.concurrent.RejectedExecutionHandler
        public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            TKAsync.REJECT_HANDLER.execute(runnable);
            Log.w(TKAsync.TAG, "reject work, put into tk-backup-pool, queueSize=" + TKAsync.REJECT_HANDLER.getQueue().size());
        }
    }

    private static class TKThreadFactory implements ThreadFactory {
        private final AtomicInteger mCount = new AtomicInteger(1);
        private final String mThreadTag;

        public TKThreadFactory(String str) {
            this.mThreadTag = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            String str = "TKExecutor-->" + this.mThreadTag + "#" + this.mCount.getAndIncrement();
            Log.d("TKExecutor", "threadName=" + str);
            return new Thread(runnable, str);
        }
    }

    static {
        CORE_POOL_SIZE = CPU_COUNT == 1 ? CPU_COUNT : Math.max(2, Math.min(CPU_COUNT - 1, 4));
        MAXIMUM_POOL_SIZE = (CPU_COUNT * 2) + 1;
        REJECT_HANDLER = new ThreadPoolExecutor(1, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), new TKThreadFactory("tk-backup-pool"));
        TK_EXECUTOR = new ThreadPoolExecutor(CORE_POOL_SIZE, MAXIMUM_POOL_SIZE, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), new TKThreadFactory("tk-async-pool"));
        TK_EXECUTOR.setRejectedExecutionHandler(new TKRejectedExecutionHandler());
    }

    public static void execute(Runnable runnable) {
        try {
            TK_EXECUTOR.execute(runnable);
        } catch (Exception e) {
            Log.w(TAG, e.getMessage());
        }
    }
}
