package com.sigmob.sdk.common.b;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class c {
    private static final int a = Runtime.getRuntime().availableProcessors();
    private static final TimeUnit b = TimeUnit.SECONDS;
    private static Handler c = new Handler(Looper.getMainLooper());

    public static class a {
        private static a d;
        final int a = 2;
        final BlockingQueue<Runnable> b = new LinkedBlockingQueue();
        final ExecutorService c = new ThreadPoolExecutor(2, c.a * 2, 2, c.b, this.b, new com.sigmob.sdk.common.b.a());

        a() {
        }

        public static a b() {
            if (d == null) {
                synchronized (a.class) {
                    if (d == null) {
                        d = new a();
                    }
                }
            }
            return d;
        }

        public ExecutorService a() {
            return this.c;
        }

        public void a(Runnable runnable) {
            try {
                this.c.submit(runnable);
            } catch (Throwable unused) {
            }
        }
    }

    public static void a(Runnable runnable) {
        c.post(runnable);
    }
}
