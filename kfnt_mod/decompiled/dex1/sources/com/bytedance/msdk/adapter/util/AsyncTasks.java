package com.bytedance.msdk.adapter.util;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AsyncTasks {

    /* renamed from: a, reason: collision with root package name */
    private static Executor f4234a;

    /* renamed from: b, reason: collision with root package name */
    private static Handler f4235b;

    static {
        b();
    }

    private static void b() {
        f4234a = Executors.newSingleThreadExecutor();
        f4235b = new Handler(Looper.getMainLooper());
    }

    public static <P> void safeExecuteOnExecutor(final AsyncTask<P, ?, ?> asyncTask, final P... pArr) {
        if (Looper.getMainLooper() == Looper.myLooper()) {
            asyncTask.executeOnExecutor(f4234a, pArr);
        } else {
            Logger.e(AsyncTasks.class.getSimpleName(), "Posting AsyncTask to main thread for execution.");
            f4235b.post(new Runnable() { // from class: com.bytedance.msdk.adapter.util.AsyncTasks.1
                @Override // java.lang.Runnable
                public void run() {
                    asyncTask.executeOnExecutor(AsyncTasks.f4234a, pArr);
                }
            });
        }
    }

    public static void setExecutor(Executor executor) {
        f4234a = executor;
    }
}
