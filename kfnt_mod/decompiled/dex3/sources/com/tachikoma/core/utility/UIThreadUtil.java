package com.tachikoma.core.utility;

import android.os.Handler;
import android.os.Looper;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class UIThreadUtil {
    private static Handler sMainHandler;

    public static boolean isOnMainThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }

    public static void postDelay(Runnable runnable, long j) {
        synchronized (UIThreadUtil.class) {
            if (sMainHandler == null) {
                sMainHandler = new Handler(Looper.getMainLooper());
            }
        }
        sMainHandler.postDelayed(runnable, j);
    }

    public static void removeAllCallbacks() {
        synchronized (UIThreadUtil.class) {
            if (sMainHandler == null) {
                sMainHandler = new Handler(Looper.getMainLooper());
            }
        }
        sMainHandler.removeCallbacksAndMessages(null);
    }

    public static void removeCallback(Runnable runnable) {
        synchronized (UIThreadUtil.class) {
            if (sMainHandler == null) {
                sMainHandler = new Handler(Looper.getMainLooper());
            }
        }
        sMainHandler.removeCallbacks(runnable);
    }

    public static void runOnUiThread(Runnable runnable) {
        synchronized (UIThreadUtil.class) {
            if (sMainHandler == null) {
                sMainHandler = new Handler(Looper.getMainLooper());
            }
        }
        sMainHandler.post(runnable);
    }
}
