package com.baidu.mobads.sdk.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class ao implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private final AtomicInteger f3122a = new AtomicInteger(1);

    ao() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "TaskScheduler #" + this.f3122a.getAndIncrement());
        thread.setUncaughtExceptionHandler(new ap(this));
        return thread;
    }
}
