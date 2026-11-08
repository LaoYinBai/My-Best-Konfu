package com.kwai.library.widget.refresh;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class g {
    private static final Handler a = new Handler(Looper.getMainLooper());

    public static int a(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int a(Context context, float f) {
        return (int) ((f * context.getResources().getDisplayMetrics().density) + 0.5f);
    }

    public static <T extends View> T a(Context context, int i) {
        return (T) LayoutInflater.from(context).inflate(i, (ViewGroup) null);
    }

    public static void a(Runnable runnable) {
        a.removeCallbacks(runnable);
    }

    public static void a(Runnable runnable, long j) {
        a.postDelayed(runnable, j);
    }
}
