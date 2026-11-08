package com.bytedance.pangle.helper;

import com.bytedance.pangle.ZeusConstants;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class e {

    /* renamed from: a, reason: collision with root package name */
    public static Executor f4645a = Executors.newCachedThreadPool();

    /* renamed from: b, reason: collision with root package name */
    public static ScheduledExecutorService f4646b = Executors.newSingleThreadScheduledExecutor();

    public static ExecutorService a(int i) {
        return Executors.newFixedThreadPool(i, new ThreadFactory() { // from class: com.bytedance.pangle.helper.e.1

            /* renamed from: a, reason: collision with root package name */
            private AtomicInteger f4647a = new AtomicInteger(1);

            @Override // java.util.concurrent.ThreadFactory
            public final Thread newThread(Runnable runnable) {
                return new Thread(runnable, ZeusConstants.BASE_LIB_NAME + "-Install-" + this.f4647a.getAndIncrement());
            }
        });
    }
}
