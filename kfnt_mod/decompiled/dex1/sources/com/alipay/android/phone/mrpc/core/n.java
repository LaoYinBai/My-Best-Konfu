package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class n implements ThreadFactory {

    /* renamed from: a, reason: collision with root package name */
    private final AtomicInteger f2466a = new AtomicInteger(1);

    n() {
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.f2466a.getAndIncrement());
        thread.setPriority(4);
        return thread;
    }
}
