package com.appsflyer;

import android.os.AsyncTask;
import android.os.Build;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AFExecutor {

    /* renamed from: ˋ, reason: contains not printable characters */
    private static AFExecutor f64;

    /* renamed from: ˊ, reason: contains not printable characters */
    private ScheduledExecutorService f65;

    /* renamed from: ˎ, reason: contains not printable characters */
    private Executor f66;

    /* renamed from: ˏ, reason: contains not printable characters */
    private Executor f67;

    private AFExecutor() {
    }

    public static AFExecutor getInstance() {
        if (f64 == null) {
            f64 = new AFExecutor();
        }
        return f64;
    }

    public Executor getSerialExecutor() {
        if (this.f67 == null) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.f67 = AsyncTask.SERIAL_EXECUTOR;
            } else {
                return Executors.newSingleThreadExecutor();
            }
        }
        return this.f67;
    }

    public Executor getThreadPoolExecutor() {
        if (this.f66 == null || ((this.f66 instanceof ThreadPoolExecutor) && (((ThreadPoolExecutor) this.f66).isShutdown() || ((ThreadPoolExecutor) this.f66).isTerminated() || ((ThreadPoolExecutor) this.f66).isTerminating()))) {
            if (Build.VERSION.SDK_INT >= 11) {
                this.f66 = Executors.newFixedThreadPool(2);
            } else {
                return Executors.newSingleThreadExecutor();
            }
        }
        return this.f66;
    }

    /* renamed from: ॱ, reason: contains not printable characters */
    final ScheduledThreadPoolExecutor m39() {
        if (this.f65 == null || this.f65.isShutdown() || this.f65.isTerminated()) {
            this.f65 = Executors.newScheduledThreadPool(2);
        }
        return (ScheduledThreadPoolExecutor) this.f65;
    }

    /* renamed from: ˏ, reason: contains not printable characters */
    final void m38() {
        try {
            m37(this.f65);
            if (this.f66 instanceof ThreadPoolExecutor) {
                m37((ThreadPoolExecutor) this.f66);
            }
        } catch (Throwable th) {
            AFLogger.afErrorLog("failed to stop Executors", th);
        }
    }

    /* renamed from: ˎ, reason: contains not printable characters */
    private static void m37(ExecutorService executorService) {
        try {
            try {
                AFLogger.afRDLog("shut downing executor ...");
                executorService.shutdown();
                executorService.awaitTermination(10L, TimeUnit.SECONDS);
                if (!executorService.isTerminated()) {
                    AFLogger.afRDLog("killing non-finished tasks");
                }
                executorService.shutdownNow();
            } catch (InterruptedException unused) {
                AFLogger.afRDLog("InterruptedException!!!");
                if (!executorService.isTerminated()) {
                    AFLogger.afRDLog("killing non-finished tasks");
                }
                executorService.shutdownNow();
            }
        } catch (Throwable th) {
            if (!executorService.isTerminated()) {
                AFLogger.afRDLog("killing non-finished tasks");
            }
            executorService.shutdownNow();
            throw th;
        }
    }
}
