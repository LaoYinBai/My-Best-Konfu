package com.baidu.mobads.sdk.internal;

import android.util.Log;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class aq implements RejectedExecutionHandler {
    aq() {
    }

    @Override // java.util.concurrent.RejectedExecutionHandler
    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
        Log.w("ThreadPoolFactory", "Exceeded ThreadPoolExecutor pool size");
        synchronized (this) {
            if (an.d == null) {
                LinkedBlockingQueue unused = an.e = new LinkedBlockingQueue();
                ThreadPoolExecutor unused2 = an.d = new ThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS, an.e, an.f);
            }
        }
        an.d.execute(runnable);
    }
}
