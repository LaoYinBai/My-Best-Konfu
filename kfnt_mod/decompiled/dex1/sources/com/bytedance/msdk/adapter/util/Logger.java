package com.bytedance.msdk.adapter.util;

import android.util.Log;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.j;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class Logger {

    /* renamed from: a, reason: collision with root package name */
    static boolean f4239a = true;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f4240b = false;

    /* renamed from: c, reason: collision with root package name */
    private static int f4241c = 4;

    private static String a(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf < 0 ? str : str.substring(iLastIndexOf + 1);
    }

    public static void d(String str) {
        if (isDebug()) {
            d("Logger", str);
        }
    }

    public static void d_(String str) {
        if (a()) {
            d(str);
        }
    }

    public static void e(String str) {
        if (isDebug()) {
            e("Logger", str);
        }
    }

    public static int getLogLevel() {
        return f4241c;
    }

    public static void i(String str) {
        if (isDebug()) {
            i("Logger", str);
        }
    }

    public static void iEventLog(String str, String str2) {
        if (f4239a || str2 == null || f4241c > 4) {
            return;
        }
        Log.i(str, str2);
    }

    public static boolean isDebug() {
        return j.f2014a || f4240b;
    }

    public static void openDebugMode() {
        f4240b = true;
        setLogLevel(3);
    }

    public static void setLogLevel(int i) {
        f4241c = i;
    }

    public static void st(String str, int i) throws Exception {
        try {
            throw new Exception();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 1; i2 < Math.min(i, stackTrace.length); i2++) {
                if (i2 > 1) {
                    sb.append("\n");
                }
                sb.append(a(stackTrace[i2].getClassName()));
                sb.append(".");
                sb.append(stackTrace[i2].getMethodName());
            }
            v(str, sb.toString());
        }
    }

    public static void v(String str) {
        v("Logger", str);
    }

    public static void w(String str) {
        if (isDebug()) {
            w("Logger", str);
        }
    }

    public static void v(String str, String str2) {
        if (isDebug() && str2 != null && f4241c <= 2) {
            Log.v(str, str2);
        }
    }

    public static void d_(String str, String str2) {
        if (a()) {
            d(str, str2);
        }
    }

    public static void d(String str, String str2) {
        if (isDebug() && str2 != null && f4241c <= 3) {
            Log.d(str, str2);
        }
    }

    public static void e(String str, String str2) {
        if (isDebug() && str2 != null && f4241c <= 6) {
            Log.e(str, str2);
        }
    }

    public static void i(String str, String str2) {
        if (isDebug() && str2 != null && f4241c <= 4) {
            Log.i(str, str2);
        }
    }

    public static void w(String str, String str2) {
        if (isDebug() && str2 != null && f4241c <= 5) {
            Log.w(str, str2);
        }
    }

    private static boolean a() {
        return a.f().A();
    }

    public static void v(String str, String str2, Throwable th) {
        if (isDebug()) {
            if (!(str2 == null && th == null) && f4241c <= 2) {
                Log.v(str, str2, th);
            }
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (isDebug()) {
            if (!(str2 == null && th == null) && f4241c <= 3) {
                Log.d(str, str2, th);
            }
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (isDebug()) {
            if (!(str2 == null && th == null) && f4241c <= 6) {
                Log.e(str, str2, th);
            }
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (isDebug()) {
            if (!(str2 == null && th == null) && f4241c <= 4) {
                Log.i(str, str2, th);
            }
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (isDebug()) {
            if (!(str2 == null && th == null) && f4241c <= 5) {
                Log.w(str, str2, th);
            }
        }
    }
}
