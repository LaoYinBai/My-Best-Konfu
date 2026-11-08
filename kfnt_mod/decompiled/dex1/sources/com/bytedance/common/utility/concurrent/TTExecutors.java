package com.bytedance.common.utility.concurrent;

import android.os.Process;
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
public class TTExecutors {
    public static final int AVAILABLEPROCESSORS = Runtime.getRuntime().availableProcessors();
    public static final int CORE_POOL_SIZE_CPU;
    public static final int CORE_POOL_SIZE_NORMAL;
    public static final int CORE_POOL_SIZE_SCHEDULED = 3;
    public static final int CPU_COUNT;
    public static final int DOWNLOAD_THREAD_COUNT = 2;
    public static final int KEEP_ALIVE_SECONDS = 30;
    public static final int MAXIMUM_POOL_SIZE_CPU;
    public static final int MAXIMUM_POOL_SIZE_NORMAL;
    public static final int MAX_POOL_SIZE = 3;
    private static final BackgroundThreadFactory S_BACKGROUND_THREAD_FACTORY;
    private static final DefaultThreadFactory S_CPU_THREAD_FACTORY;
    private static final DefaultThreadFactory S_DEFAULT_THREAD_FACTORY;
    private static final DefaultThreadFactory S_DOWNLOAD_THREAD_FACTORY;
    private static final RejectedExecutionHandler S_HANDLER;
    private static final BlockingQueue<Runnable> S_POOLWORK_QUEUE;
    private static final BlockingQueue<Runnable> S_POOLWORK_QUEUE_CPU;
    private static final BlockingQueue<Runnable> S_POOLWORK_QUEUE_DOWNLOAD;
    private static final DefaultThreadFactory S_SCHEDULED_THREAD_FACTORY;
    private static final DefaultThreadFactory S_SERIAL_THREAD_FACTORY;
    private static ExecutorService sBackgroundThreadPool;
    private static ExecutorService sCPUThreadPool;
    private static ExecutorService sIOThreadPool;
    private static ExecutorService sNormalThreadPool;
    private static ScheduledExecutorService sScheduledThreadPool;
    private static ExecutorService sSerialThreadPool;

    static {
        CPU_COUNT = AVAILABLEPROCESSORS > 0 ? AVAILABLEPROCESSORS : 1;
        CORE_POOL_SIZE_NORMAL = Math.max(2, Math.min(CPU_COUNT - 1, 6)) * 2;
        MAXIMUM_POOL_SIZE_NORMAL = (CORE_POOL_SIZE_NORMAL * 2) + 1;
        CORE_POOL_SIZE_CPU = Math.max(2, Math.min(CPU_COUNT - 1, 3));
        MAXIMUM_POOL_SIZE_CPU = (CPU_COUNT * 2) + 1;
        S_DEFAULT_THREAD_FACTORY = new DefaultThreadFactory("TTDefaultExecutors");
        S_CPU_THREAD_FACTORY = new DefaultThreadFactory("TTCpuExecutors");
        S_SCHEDULED_THREAD_FACTORY = new DefaultThreadFactory("TTScheduledExecutors");
        S_DOWNLOAD_THREAD_FACTORY = new DefaultThreadFactory("TTDownLoadExecutors");
        S_SERIAL_THREAD_FACTORY = new DefaultThreadFactory("TTSerialExecutors");
        S_BACKGROUND_THREAD_FACTORY = new BackgroundThreadFactory("TTBackgroundExecutors");
        S_POOLWORK_QUEUE = new LinkedBlockingQueue();
        S_POOLWORK_QUEUE_CPU = new LinkedBlockingQueue();
        S_POOLWORK_QUEUE_DOWNLOAD = new LinkedBlockingQueue();
        S_HANDLER = new RejectedExecutionHandler() { // from class: com.bytedance.common.utility.concurrent.TTExecutors.1
            @Override // java.util.concurrent.RejectedExecutionHandler
            public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
                Executors.newCachedThreadPool().execute(runnable);
            }
        };
        sNormalThreadPool = new TTThreadPoolExecutor(CORE_POOL_SIZE_NORMAL, MAXIMUM_POOL_SIZE_NORMAL, 30L, TimeUnit.SECONDS, S_POOLWORK_QUEUE, S_DEFAULT_THREAD_FACTORY, S_HANDLER);
        ((TTThreadPoolExecutor) sNormalThreadPool).allowCoreThreadTimeOut(true);
        sCPUThreadPool = new TTThreadPoolExecutor(CORE_POOL_SIZE_CPU, MAXIMUM_POOL_SIZE_CPU, 30L, TimeUnit.SECONDS, S_POOLWORK_QUEUE_CPU, S_CPU_THREAD_FACTORY, S_HANDLER);
        ((TTThreadPoolExecutor) sCPUThreadPool).allowCoreThreadTimeOut(true);
        sScheduledThreadPool = Executors.newScheduledThreadPool(3, S_SCHEDULED_THREAD_FACTORY);
        sIOThreadPool = new TTThreadPoolExecutor(2, 2, 30L, TimeUnit.SECONDS, S_POOLWORK_QUEUE_DOWNLOAD, S_DOWNLOAD_THREAD_FACTORY, S_HANDLER);
        ((TTThreadPoolExecutor) sIOThreadPool).allowCoreThreadTimeOut(true);
        sSerialThreadPool = new TTThreadPoolExecutor(1, 1, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), S_SERIAL_THREAD_FACTORY);
        ((TTThreadPoolExecutor) sSerialThreadPool).allowCoreThreadTimeOut(true);
        sBackgroundThreadPool = new TTThreadPoolExecutor(0, 3, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(), S_BACKGROUND_THREAD_FACTORY);
        ((TTThreadPoolExecutor) sBackgroundThreadPool).allowCoreThreadTimeOut(true);
    }

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        DefaultThreadFactory(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = str + "-" + POOL_NUMBER.getAndIncrement() + "-Thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            if (thread.getPriority() != 5) {
                thread.setPriority(5);
            }
            return thread;
        }
    }

    private static class BackgroundThreadFactory implements ThreadFactory {
        private static final AtomicInteger POOL_NUMBER = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);

        BackgroundThreadFactory(String str) {
            SecurityManager securityManager = System.getSecurityManager();
            this.group = securityManager != null ? securityManager.getThreadGroup() : Thread.currentThread().getThreadGroup();
            this.namePrefix = str + "-" + POOL_NUMBER.getAndIncrement() + "-Thread-";
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0L) { // from class: com.bytedance.common.utility.concurrent.TTExecutors.BackgroundThreadFactory.1
                @Override // java.lang.Thread, java.lang.Runnable
                public void run() throws SecurityException, IllegalArgumentException {
                    Process.setThreadPriority(10);
                    super.run();
                }
            };
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            return thread;
        }
    }

    public static ExecutorService getNormalExecutor() {
        return sNormalThreadPool;
    }

    @Deprecated
    public static ExecutorService getDownLoadThreadPool() {
        return sIOThreadPool;
    }

    public static ScheduledExecutorService getScheduledThreadPool() {
        return sScheduledThreadPool;
    }

    public static ExecutorService getCPUThreadPool() {
        return sCPUThreadPool;
    }

    public static ExecutorService getIOThreadPool() {
        return sIOThreadPool;
    }

    public static ExecutorService getSerialThreadPool() {
        return sSerialThreadPool;
    }

    public static ExecutorService getBackgroundThreadPool() {
        return sBackgroundThreadPool;
    }

    public static void setNormalThreadPool(ExecutorService executorService) {
        sNormalThreadPool = executorService;
    }

    public static void setCPUThreadPool(ExecutorService executorService) {
        sCPUThreadPool = executorService;
    }

    public static void setIOThreadPool(ExecutorService executorService) {
        sIOThreadPool = executorService;
    }

    public static void setScheduledThreadPool(ScheduledExecutorService scheduledExecutorService) {
        sScheduledThreadPool = scheduledExecutorService;
    }

    public static void setSerialThreadPool(ExecutorService executorService) {
        sSerialThreadPool = executorService;
    }

    public static void setBackgroundThreadPool(ExecutorService executorService) {
        sBackgroundThreadPool = executorService;
    }
}
