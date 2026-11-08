package com.ss.union.game.sdk.common.util;

import android.os.Handler;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MainThreadExecutor {
    private static final Handler a = new Handler(Looper.getMainLooper());

    public static void post(Runnable runnable) {
        a.post(runnable);
    }

    public static void postDelayed(Runnable runnable, long j) {
        a.postDelayed(runnable, j);
    }

    public static void remove(Runnable runnable) {
        a.removeCallbacks(runnable);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
}
