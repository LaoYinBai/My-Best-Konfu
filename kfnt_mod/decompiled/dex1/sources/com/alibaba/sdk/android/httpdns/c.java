package com.alibaba.sdk.android.httpdns;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ActivityChooserView;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with other field name */
    private static final TimeUnit f13a = TimeUnit.SECONDS;

    /* renamed from: a, reason: collision with other field name */
    private static final ThreadFactory f12a = new ThreadFactory() { // from class: com.alibaba.sdk.android.httpdns.c.1
        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(@NonNull Runnable runnable) {
            Thread thread = new Thread(runnable);
            thread.setName("httpdns worker");
            thread.setDaemon(false);
            thread.setUncaughtExceptionHandler(new j());
            return thread;
        }
    };

    /* renamed from: a, reason: collision with root package name */
    private static final ExecutorService f2330a = new ThreadPoolExecutor(0, ActivityChooserView.ActivityChooserViewAdapter.MAX_ACTIVITY_COUNT_UNLIMITED, 1, f13a, new SynchronousQueue(), f12a);

    public static ExecutorService a() {
        return f2330a;
    }
}
