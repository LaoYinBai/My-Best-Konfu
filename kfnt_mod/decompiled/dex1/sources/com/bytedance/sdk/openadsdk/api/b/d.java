package com.bytedance.sdk.openadsdk.api.b;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class d {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f5190a = false;

    /* renamed from: b, reason: collision with root package name */
    private static int f5191b = 4;

    public static void a(int i) {
        f5191b = i;
    }

    public static void a() {
        f5190a = true;
        a(3);
    }

    public static void a(String str, String str2) {
        if (f5190a && str2 != null && f5191b <= 3) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2) {
        if (f5190a && str2 != null && f5191b <= 4) {
            Log.i(str, str2);
        }
    }

    public static void a(String str) {
        if (f5190a) {
            c("TTLogger", str);
        }
    }

    public static void c(String str, String str2) {
        if (f5190a && str2 != null && f5191b <= 5) {
            Log.w(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (f5190a) {
            if (!(str2 == null && th == null) && f5191b <= 5) {
                Log.w(str, str2, th);
            }
        }
    }

    public static void d(String str, String str2) {
        if (f5190a && str2 != null && f5191b <= 6) {
            Log.e(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (f5190a) {
            if (!(str2 == null && th == null) && f5191b <= 6) {
                Log.e(str, str2, th);
            }
        }
    }
}
