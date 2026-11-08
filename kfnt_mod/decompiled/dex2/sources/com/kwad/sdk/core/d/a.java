package com.kwad.sdk.core.d;

import android.util.Log;
import com.kwad.v8.Platform;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static boolean a = true;
    private static boolean b = false;

    private static String a() {
        return "KSAdSDK";
    }

    public static void a(String str) {
        Log.v(a(), str);
    }

    public static void a(String str, String str2) {
        if (a && com.kwad.kwai.kwai.a.a.booleanValue()) {
            b(a(), f(str, str2));
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (a) {
            Log.e(str, str2, th);
        }
    }

    public static void a(String str, Throwable th) {
        if (a && com.kwad.kwai.kwai.a.a.booleanValue()) {
            Log.w(str, th);
        }
    }

    public static void a(Throwable th) {
        if (a && th != null) {
            th.printStackTrace();
        }
        if (com.kwad.kwai.kwai.a.a.booleanValue()) {
            throw new RuntimeException(th);
        }
    }

    public static void a(boolean z) {
        a = z;
    }

    private static String b() {
        if (!b) {
            return "";
        }
        int lineNumber = -1;
        String fileName = Platform.UNKNOWN;
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();
        if (stackTrace.length > 3) {
            fileName = stackTrace[3].getFileName();
            lineNumber = stackTrace[3].getLineNumber();
        }
        return "(" + fileName + ':' + lineNumber + ')';
    }

    public static void b(String str, String str2) {
        if (str2.length() <= 4000) {
            Log.d(str, str2);
        } else {
            Log.d(str, str2.substring(0, 4000));
            b(str, str2.substring(4000));
        }
    }

    public static void b(Throwable th) {
        if (!com.kwad.kwai.kwai.a.a.booleanValue() || th == null) {
            return;
        }
        th.printStackTrace();
    }

    public static void c(String str, String str2) {
        if (a) {
            Log.i(a(), f(str, str2));
        }
    }

    public static void d(String str, String str2) {
        if (a) {
            Log.w(a(), f(str, str2));
        }
    }

    public static void e(String str, String str2) {
        if (a) {
            Log.e(a(), f(str, str2));
        }
    }

    private static String f(String str, String str2) {
        return "[" + str + "]: " + str2 + " " + b();
    }
}
