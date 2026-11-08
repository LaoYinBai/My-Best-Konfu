package com.alibaba.sdk.android.utils;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: c, reason: collision with root package name */
    private static boolean f2425c = false;

    public static boolean c() {
        return f2425c;
    }

    public static void setLogEnabled(boolean z) {
        f2425c = z;
    }

    public static void a(String str, String str2) {
        if (f2425c) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (f2425c) {
            Log.i(str, str2);
        }
    }

    public static void c(String str, String str2) {
        if (f2425c) {
            Log.e(str, str2);
        }
    }

    public static void a(String str, Throwable th) {
        if (!f2425c || th == null) {
            return;
        }
        Log.e(str, th.toString(), th);
    }
}
