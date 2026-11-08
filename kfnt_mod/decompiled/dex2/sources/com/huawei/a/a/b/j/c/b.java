package com.huawei.a.a.b.j.c;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class b {
    private ThreadPoolExecutor a = new ThreadPoolExecutor(0, 1, 60000, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(5000));
    private static b e = new b();
    private static b d = new b();
    private static b b = new b();
    private static b c = new b();

    private static class a implements Runnable {
        private Runnable a;

        public a(Runnable runnable) {
            this.a = runnable;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.a != null) {
                try {
                    this.a.run();
                } catch (Exception unused) {
                    com.huawei.a.a.b.e.a.c("TaskThread", "InnerTask : Exception has happened,From internal operations!");
                }
            }
        }
    }

    private b() {
    }

    public static b a() {
        return b;
    }

    public static b b() {
        return c;
    }

    public void a(com.huawei.a.a.b.j.c.a aVar) {
        try {
            this.a.execute(new a(aVar));
        } catch (RejectedExecutionException unused) {
            com.huawei.a.a.b.e.a.c("TaskThread", "addToQueue() Exception has happened!Form rejected execution");
        }
    }
}
