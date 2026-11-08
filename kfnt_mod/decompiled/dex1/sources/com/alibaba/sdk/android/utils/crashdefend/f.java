package com.alibaba.sdk.android.utils.crashdefend;

import androidx.appcompat.widget.ActivityChooserView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class f {

    /* renamed from: a, reason: collision with root package name */
    private final ThreadFactory f2422a = new ThreadFactory() { // from class: com.alibaba.sdk.android.utils.crashdefend.f.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "safe_thread");
            thread.setDaemon(false);
            return thread;
        }
    };

    /* renamed from: c, reason: collision with root package name */
    private ExecutorService f2423c;

    f() {
    }

    synchronized ExecutorService a() {
        if (this.f2423c == null) {
            this.f2423c = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 1L, TimeUnit.SECONDS, new SynchronousQueue(), this.f2422a);
        }
        return this.f2423c;
    }
}
