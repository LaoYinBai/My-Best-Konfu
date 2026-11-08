package com.mbridge.msdk.foundation.tools;

import android.text.TextUtils;
import android.util.Log;
import com.mbridge.msdk.MBridgeConstans;

/* compiled from: SameLogTool.java */
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public final class s {
    public static boolean a = true;
    public static boolean b = true;
    public static boolean c = true;
    public static boolean d = true;
    public static boolean e = true;
    public static boolean f = false;
    public static boolean g = true;
    public static boolean h = false;

    static {
        if (MBridgeConstans.DEBUG) {
            return;
        }
        a = false;
        b = false;
        c = false;
        d = false;
        e = false;
        f = false;
        g = false;
        h = false;
    }

    public static void a(String str, String str2) {
        if (b) {
            Log.d(a(str), str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (b) {
            Log.d(a(str), str2, th);
        }
    }

    public static void b(String str, String str2) {
        if (c) {
            Log.i(a(str), str2);
        }
    }

    public static void c(String str, String str2) {
        if (d) {
            Log.w(a(str), str2);
        }
    }

    public static void d(String str, String str2) {
        if (!e || str2 == null) {
            return;
        }
        Log.e(a(str), str2);
    }

    public static void b(String str, String str2, Throwable th) {
        if (!e || str2 == null || th == null) {
            return;
        }
        Log.e(a(str), str2, th);
    }

    private static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        return "MBRIDGE_" + str;
    }
}
