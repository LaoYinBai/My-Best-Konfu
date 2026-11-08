package com.meizu.cloud.pushinternal;

import android.content.Context;
import com.meizu.cloud.pushsdk.base.h;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class DebugLogger {
    public static boolean debug = false;

    public static void d(String str, String str2) {
        h.b().a(str, str2);
    }

    public static void e(String str, String str2) {
        h.b().d(str, str2);
    }

    public static void e(String str, String str2, Throwable th) {
        h.b().a(str, str2, th);
    }

    public static void flush() {
        h.b().a(false);
    }

    public static void i(String str, String str2) {
        h.b().b(str, str2);
    }

    public static void init(Context context) {
        h.b().a(context);
    }

    public static void init(Context context, String str) {
        h.b().a(context, str);
    }

    public static boolean isDebuggable() {
        return h.b().a();
    }

    public static void switchDebug(boolean z) {
        h.b().b(z);
    }

    public static void w(String str, String str2) {
        h.b().c(str, str2);
    }
}
