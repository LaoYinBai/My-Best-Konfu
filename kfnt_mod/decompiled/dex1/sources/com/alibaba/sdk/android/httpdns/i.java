package com.alibaba.sdk.android.httpdns;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class i {

    /* renamed from: a, reason: collision with root package name */
    private static ILogger f2375a = null;

    /* renamed from: c, reason: collision with root package name */
    private static int f2376c = -1;

    /* renamed from: c, reason: collision with other field name */
    private static boolean f23c = false;

    public static void a(Throwable th) {
        if (!f23c || th == null) {
            return;
        }
        th.printStackTrace();
    }

    private static String b() {
        try {
            if (f2376c == -1) {
                StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
                int length = stackTrace.length;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= length) {
                        break;
                    }
                    if (stackTrace[i].getMethodName().equals("getTraceInfo")) {
                        f2376c = i2 + 1;
                        break;
                    }
                    i2++;
                    i++;
                }
            }
            StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[f2376c + 1];
            return stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + " - [" + stackTraceElement.getMethodName() + "]";
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static void d(String str) {
        if (f23c && str != null) {
            Log.d("HttpDnsSDK", Thread.currentThread().getId() + " - " + b() + " - " + str);
        }
        if (f2375a != null) {
            f2375a.log(str);
        }
    }

    public static void e(String str) {
        if (f23c && str != null) {
            Log.i("HttpDnsSDK", Thread.currentThread().getId() + " - " + b() + " - " + str);
        }
        if (f2375a != null) {
            f2375a.log(str);
        }
    }

    public static void f(String str) {
        if (f23c && str != null) {
            Log.e("HttpDnsSDK", Thread.currentThread().getId() + " - " + b() + " - " + str);
        }
        if (f2375a != null) {
            f2375a.log(str);
        }
    }

    static synchronized void setLogEnabled(boolean z) {
        f23c = z;
    }

    static void setLogger(ILogger iLogger) {
        f2375a = iLogger;
    }
}
