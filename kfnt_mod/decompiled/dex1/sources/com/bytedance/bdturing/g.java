package com.bytedance.bdturing;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class g {

    /* renamed from: a, reason: collision with root package name */
    private static int f3795a = 1;

    static void a() {
        f3795a = 7;
    }

    static void b() {
        f3795a = 1;
    }

    public static boolean c() {
        return f3795a > 6;
    }

    public static void a(String str, String str2) {
        if (f3795a < 4) {
            return;
        }
        Log.i(str, str2);
    }

    public static void b(String str, String str2) {
        if (f3795a < 5) {
            return;
        }
        Log.w(str, str2);
    }

    public static void c(String str, String str2) {
        if (f3795a < 6) {
            return;
        }
        Log.e(str, str2);
    }

    public static void d(String str, String str2) {
        if (f3795a < 3) {
            return;
        }
        Log.d(str, str2);
    }

    public static void e(String str, String str2) {
        if (f3795a < 2) {
            return;
        }
        Log.v(str, str2);
    }

    public static void a(Exception exc) {
        if (f3795a < 3) {
            exc.printStackTrace();
        }
    }

    private static void d() {
        for (StackTraceElement stackTraceElement : Thread.currentThread().getStackTrace()) {
            System.out.println(stackTraceElement);
        }
    }
}
