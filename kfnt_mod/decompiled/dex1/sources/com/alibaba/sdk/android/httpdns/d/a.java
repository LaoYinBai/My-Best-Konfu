package com.alibaba.sdk.android.httpdns.d;

import androidx.appcompat.widget.ActivityChooserView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
final class a {

    /* renamed from: b, reason: collision with root package name */
    private ExecutorService f2334b;

    /* renamed from: b, reason: collision with other field name */
    private final ThreadFactory f17b = new ThreadFactory() { // from class: com.alibaba.sdk.android.httpdns.d.a.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, "report_thread");
            thread.setDaemon(false);
            return thread;
        }
    };

    a() {
    }

    synchronized ExecutorService b() {
        if (this.f2334b == null) {
            this.f2334b = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 1L, TimeUnit.SECONDS, new SynchronousQueue(), this.f17b);
        }
        return this.f2334b;
    }
}
