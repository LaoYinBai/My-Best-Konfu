package com.bytedance.msdk.adapter.util;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ThreadHelper {

    /* renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f4246a = !ThreadHelper.class.desiredAssertionStatus();

    /* renamed from: b, reason: collision with root package name */
    private static final Object f4247b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private static boolean f4248c = false;
    private static Handler d = null;
    private static HandlerThread e;
    private static Handler f;
    private static final int g;
    private static final int h;
    private static Executor i;

    static {
        a();
        g = Runtime.getRuntime().availableProcessors();
        h = Math.min(g, 2);
        i = c();
    }

    private static void a() {
        try {
            e = new HandlerThread("tt-msdk-thread", -19);
            e.start();
            f = new Handler(e.getLooper());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public static void assertOnUiThread() {
        if (!f4246a && !runningOnUiThread()) {
            throw new AssertionError();
        }
    }

    private static boolean b() {
        HandlerThread handlerThread = e;
        return (handlerThread == null || !handlerThread.isAlive() || f == null) ? false : true;
    }

    private static Executor c() {
        int i2 = h;
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i2, i2, 30L, TimeUnit.SECONDS, new LinkedBlockingQueue(128), createNamedThreadFactory("ThreadHelper:"));
        try {
            threadPoolExecutor.allowCoreThreadTimeOut(true);
        } catch (Exception e2) {
            Log.e("ThreadHelper", "stackerror:", e2);
        } catch (NoSuchMethodError e3) {
            e3.printStackTrace();
        }
        return threadPoolExecutor;
    }

    public static ThreadFactory createNamedThreadFactory(final String str) {
        return new ThreadFactory() { // from class: com.bytedance.msdk.adapter.util.ThreadHelper.1

            /* renamed from: b, reason: collision with root package name */
            private final AtomicInteger f4250b = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public Thread newThread(Runnable runnable) {
                return new Thread(runnable, str + " # " + this.f4250b.getAndIncrement());
            }
        };
    }

    public static Looper getMSDKThreadLooper() {
        if (b()) {
            return e.getLooper();
        }
        return null;
    }

    public static Handler getUiThreadHandler() {
        Handler handler;
        synchronized (f4247b) {
            if (d == null) {
                if (f4248c) {
                    throw new RuntimeException("Did not yet override the UI thread");
                }
                d = new Handler(Looper.getMainLooper());
            }
            handler = d;
        }
        return handler;
    }

    public static void postDelayOnMSDKThread(Runnable runnable, long j) {
        if (b()) {
            f.postDelayed(runnable, j);
        } else {
            a();
        }
    }

    public static <T> FutureTask<T> postOnUiThread(FutureTask<T> futureTask) {
        getUiThreadHandler().post(futureTask);
        return futureTask;
    }

    public static void postOnUiThreadDelayed(Runnable runnable, long j) {
        getUiThreadHandler().postDelayed(runnable, j);
    }

    public static void revokeOnUiThread(Runnable runnable) {
        getUiThreadHandler().removeCallbacks(runnable);
    }

    public static void runOnMSDKThread(Runnable runnable) {
        if (!b()) {
            a();
        } else if (runningOnMSDKThread()) {
            runnable.run();
        } else {
            f.post(runnable);
        }
    }

    public static void runOnThreadPool(Runnable runnable) {
        a(i, runnable);
    }

    public static <T> FutureTask<T> runOnUiThread(FutureTask<T> futureTask) {
        if (runningOnUiThread()) {
            futureTask.run();
        } else {
            postOnUiThread((FutureTask) futureTask);
        }
        return futureTask;
    }

    public static void runOnUiThreadBlocking(Runnable runnable) throws ExecutionException, InterruptedException {
        if (runningOnUiThread()) {
            runnable.run();
            return;
        }
        FutureTask futureTask = new FutureTask(runnable, null);
        postOnUiThread(futureTask);
        try {
            futureTask.get();
        } catch (Exception e2) {
            throw new RuntimeException("Exception occured while waiting for runnable", e2);
        }
    }

    public static <T> T runOnUiThreadBlockingNoException(Callable<T> callable) {
        try {
            return (T) runOnUiThreadBlocking(callable);
        } catch (ExecutionException e2) {
            throw new RuntimeException("Error occured waiting for callable", e2);
        }
    }

    public static boolean runningOnMSDKThread() {
        return b() && e.getLooper() == Looper.myLooper();
    }

    public static boolean runningOnUiThread() {
        return getUiThreadHandler().getLooper() == Looper.myLooper();
    }

    public static void postOnUiThread(Runnable runnable) {
        getUiThreadHandler().post(runnable);
    }

    public static void runOnUiThread(Runnable runnable) {
        if (runningOnUiThread()) {
            runnable.run();
        } else {
            getUiThreadHandler().post(runnable);
        }
    }

    public static <T> T runOnUiThreadBlocking(Callable<T> callable) throws ExecutionException {
        FutureTask futureTask = new FutureTask(callable);
        runOnUiThread(futureTask);
        try {
            return (T) futureTask.get();
        } catch (InterruptedException e2) {
            throw new RuntimeException("Interrupted waiting for callable", e2);
        }
    }

    private static boolean a(Executor executor, Runnable runnable) {
        try {
            executor.execute(runnable);
            return true;
        } catch (Exception e2) {
            Log.e("ThreadHelper", "stackerror:", e2);
            return false;
        }
    }
}
