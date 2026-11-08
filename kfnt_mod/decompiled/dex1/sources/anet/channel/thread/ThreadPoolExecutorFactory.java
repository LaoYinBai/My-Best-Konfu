package anet.channel.thread;

import anet.channel.util.ALog;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ThreadPoolExecutorFactory {

    /* renamed from: a, reason: collision with root package name */
    private static ScheduledThreadPoolExecutor f591a = new ScheduledThreadPoolExecutor(1, new b("AWCN Scheduler"));

    /* renamed from: b, reason: collision with root package name */
    private static ThreadPoolExecutor f592b = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingDeque(), new b("AWCN Worker(H)"));

    /* renamed from: c, reason: collision with root package name */
    private static ThreadPoolExecutor f593c = new anet.channel.thread.a(16, 16, 60, TimeUnit.SECONDS, new PriorityBlockingQueue(), new b("AWCN Worker(M)"));
    private static ThreadPoolExecutor d = new ThreadPoolExecutor(2, 2, 60, TimeUnit.SECONDS, new LinkedBlockingDeque(), new b("AWCN Worker(L)"));
    private static ThreadPoolExecutor e = new ThreadPoolExecutor(32, 32, 60, TimeUnit.SECONDS, new LinkedBlockingDeque(), new b("AWCN Worker(Backup)"));

    public static class Priority {
        public static int HIGH = 0;
        public static int LOW = 9;
        public static int NORMAL = 1;
    }

    static {
        f592b.allowCoreThreadTimeOut(true);
        f593c.allowCoreThreadTimeOut(true);
        d.allowCoreThreadTimeOut(true);
        e.allowCoreThreadTimeOut(true);
    }

    private static class b implements ThreadFactory {

        /* renamed from: a, reason: collision with root package name */
        AtomicInteger f597a = new AtomicInteger(0);

        /* renamed from: b, reason: collision with root package name */
        String f598b;

        b(String str) {
            this.f598b = str;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.f598b + this.f597a.incrementAndGet());
            ALog.i("awcn.ThreadPoolExecutorFactory", "thread created!", null, "name", thread.getName());
            thread.setPriority(5);
            return thread;
        }
    }

    public static Future<?> submitScheduledTask(Runnable runnable) {
        return f591a.submit(runnable);
    }

    public static Future<?> submitScheduledTask(Runnable runnable, long j, TimeUnit timeUnit) {
        return f591a.schedule(runnable, j, timeUnit);
    }

    public static void removeScheduleTask(Runnable runnable) {
        f591a.remove(runnable);
    }

    public static Future<?> submitPriorityTask(Runnable runnable, int i) {
        if (ALog.isPrintLog(1)) {
            ALog.d("awcn.ThreadPoolExecutorFactory", "submit priority task", null, "priority", Integer.valueOf(i));
        }
        if (i < Priority.HIGH || i > Priority.LOW) {
            i = Priority.LOW;
        }
        if (i == Priority.HIGH) {
            return f592b.submit(runnable);
        }
        if (i == Priority.LOW) {
            return d.submit(runnable);
        }
        return f593c.submit(new a(runnable, i));
    }

    public static Future<?> submitBackupTask(Runnable runnable) {
        return e.submit(runnable);
    }

    static class a implements Comparable<a>, Runnable {

        /* renamed from: a, reason: collision with root package name */
        Runnable f594a;

        /* renamed from: b, reason: collision with root package name */
        int f595b;

        /* renamed from: c, reason: collision with root package name */
        long f596c;

        public a(Runnable runnable, int i) {
            this.f594a = null;
            this.f595b = 0;
            this.f596c = System.currentTimeMillis();
            this.f594a = runnable;
            this.f595b = i;
            this.f596c = System.currentTimeMillis();
        }

        @Override // java.lang.Comparable
        /* renamed from: a, reason: merged with bridge method [inline-methods] */
        public int compareTo(a aVar) {
            if (this.f595b != aVar.f595b) {
                return this.f595b - aVar.f595b;
            }
            return (int) (aVar.f596c - this.f596c);
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f594a.run();
        }
    }

    public static synchronized void setNormalExecutorPoolSize(int i) {
        if (i < 6) {
            i = 6;
        }
        f593c.setCorePoolSize(i);
        f593c.setMaximumPoolSize(i);
    }
}
