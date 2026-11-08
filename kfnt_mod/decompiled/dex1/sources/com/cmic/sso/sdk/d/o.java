package com.cmic.sso.sdk.d;

import android.content.Context;
import com.cmic.sso.sdk.auth.AuthnHelper;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class o {

    /* renamed from: a, reason: collision with root package name */
    private static ExecutorService f5463a = new ThreadPoolExecutor(0, 30, 60, TimeUnit.SECONDS, new SynchronousQueue());

    public static void a(a aVar) {
        try {
            f5463a.execute(aVar);
        } catch (Exception e) {
            aVar.f5464a.uncaughtException(Thread.currentThread(), e);
        }
    }

    public static abstract class a implements Runnable {

        /* renamed from: a, reason: collision with root package name */
        private Thread.UncaughtExceptionHandler f5464a;

        protected abstract void a();

        protected a() {
            this.f5464a = new Thread.UncaughtExceptionHandler() { // from class: com.cmic.sso.sdk.d.o.a.1
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    th.printStackTrace();
                }
            };
        }

        protected a(final Context context, final com.cmic.sso.sdk.a aVar) {
            this.f5464a = new Thread.UncaughtExceptionHandler() { // from class: com.cmic.sso.sdk.d.o.a.2
                @Override // java.lang.Thread.UncaughtExceptionHandler
                public void uncaughtException(Thread thread, Throwable th) {
                    com.cmic.sso.sdk.c.a.f5440a.add(th);
                    AuthnHelper.getInstance(context).callBackResult("200025", "发生未知错误", aVar, null);
                }
            };
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(this.f5464a);
            a();
            Thread.currentThread().setUncaughtExceptionHandler(null);
        }
    }
}
