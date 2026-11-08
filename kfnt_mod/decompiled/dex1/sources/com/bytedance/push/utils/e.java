package com.bytedance.push.utils;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class e {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f4995a = false;

    public static void a(boolean z) {
        f4995a = z;
    }

    public static void a(String str) {
        a("", str);
    }

    public static void a(String str, String str2) {
        Log.d("BDPush", str + "\t>>>\t" + str2);
    }

    public static void b(String str) {
        b("", str);
    }

    public static void b(String str, String str2) {
        Log.e("BDPush", str + "\t>>> " + str2);
    }

    public static boolean a() {
        return f4995a;
    }

    public static void c(String str) {
        c("", str);
    }

    public static void c(String str, String str2) {
        Log.i("BDPush", str + "\t>>>\t" + str2);
    }

    public static void a(String str, String str2, Throwable th) {
        Log.i(str, str2, th);
    }

    public static void d(String str, String str2) {
        Log.v("BDPush", str + "\t>>>\t" + str2);
    }
}
