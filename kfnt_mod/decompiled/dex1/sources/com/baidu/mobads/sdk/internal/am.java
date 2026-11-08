package com.baidu.mobads.sdk.internal;

import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class am {

    /* renamed from: a, reason: collision with root package name */
    private static final String f3116a = "TaskScheduler";
    private static volatile am d;

    /* renamed from: b, reason: collision with root package name */
    private ThreadPoolExecutor f3117b;

    /* renamed from: c, reason: collision with root package name */
    private ScheduledThreadPoolExecutor f3118c;

    private am() {
        b();
    }

    public static am a() {
        if (d == null) {
            synchronized (am.class) {
                if (d == null) {
                    d = new am();
                }
            }
        }
        return d;
    }

    private void b() {
        this.f3117b = an.a(1, 5);
        this.f3118c = an.a(2);
    }

    public void a(Runnable runnable) {
        if (runnable == null || this.f3117b == null || this.f3117b.isShutdown()) {
            return;
        }
        try {
            this.f3117b.submit(runnable);
        } catch (Throwable unused) {
        }
    }

    public void a(h hVar) {
        if (hVar == null || this.f3117b == null || this.f3117b.isShutdown()) {
            return;
        }
        try {
            hVar.a(System.currentTimeMillis());
            FutureTask futureTask = null;
            if (this.f3117b != null && !this.f3117b.isShutdown()) {
                futureTask = (FutureTask) this.f3117b.submit(hVar);
            }
            hVar.a((Future) futureTask);
        } catch (Throwable unused) {
        }
    }

    public void a(h hVar, long j, TimeUnit timeUnit) {
        if (hVar == null || this.f3118c == null || this.f3118c.isShutdown()) {
            return;
        }
        try {
            hVar.a(System.currentTimeMillis());
            hVar.a((Future) this.f3118c.schedule(hVar, j, timeUnit));
        } catch (Throwable unused) {
        }
    }

    public void a(h hVar, long j, long j2, TimeUnit timeUnit) {
        if (hVar == null || this.f3118c == null || this.f3118c.isShutdown()) {
            return;
        }
        try {
            hVar.a(System.currentTimeMillis());
            hVar.a((Future) this.f3118c.scheduleAtFixedRate(hVar, j, j2, timeUnit));
        } catch (Throwable unused) {
        }
    }
}
