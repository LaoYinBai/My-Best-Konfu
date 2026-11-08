package com.bytedance.pangle.util;

import android.os.Handler;
import android.os.Looper;
import com.bytedance.pangle.log.ZeusLogger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    static final /* synthetic */ boolean f4714a = !i.class.desiredAssertionStatus();

    /* renamed from: b, reason: collision with root package name */
    private static final Object f4715b = new Object();

    /* renamed from: c, reason: collision with root package name */
    private static Handler f4716c = null;
    private static volatile ExecutorService d;

    private static Handler b() {
        Handler handler;
        synchronized (f4715b) {
            if (f4716c == null) {
                f4716c = new Handler(Looper.getMainLooper());
            }
            handler = f4716c;
        }
        return handler;
    }

    public static ExecutorService a() {
        if (d == null) {
            synchronized (i.class) {
                if (d == null) {
                    int iAvailableProcessors = Runtime.getRuntime().availableProcessors() + 1;
                    ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(iAvailableProcessors, iAvailableProcessors, 15L, TimeUnit.SECONDS, new LinkedBlockingQueue());
                    try {
                        threadPoolExecutor.allowCoreThreadTimeOut(true);
                    } catch (Throwable th) {
                        ZeusLogger.e(ZeusLogger.TAG, "allowCoreThreadTimeOut failed ", th);
                    }
                    d = threadPoolExecutor;
                }
            }
        }
        return d;
    }

    public static void a(Runnable runnable) {
        if (b().getLooper() == Looper.myLooper()) {
            runnable.run();
        } else {
            b().post(runnable);
        }
    }
}
