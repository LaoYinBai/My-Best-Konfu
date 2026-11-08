package com.baidu.mobads.sdk.internal;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class an {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3119a = "ThreadPoolFactory";

    /* renamed from: b, reason: collision with root package name */
    private static final int f3120b = 5;

    /* renamed from: c, reason: collision with root package name */
    private static final int f3121c = 60;
    private static ThreadPoolExecutor d;
    private static LinkedBlockingQueue<Runnable> e;
    private static final ThreadFactory f = new ao();
    private static final RejectedExecutionHandler g = new aq();

    public static ThreadPoolExecutor a(int i, int i2) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(i, i2, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), f);
        threadPoolExecutor.setRejectedExecutionHandler(g);
        return threadPoolExecutor;
    }

    public static ScheduledThreadPoolExecutor a(int i) {
        return new ScheduledThreadPoolExecutor(i, f);
    }
}
