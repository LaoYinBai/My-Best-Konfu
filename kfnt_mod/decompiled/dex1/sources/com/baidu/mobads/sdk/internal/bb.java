package com.baidu.mobads.sdk.internal;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class bb {

    /* renamed from: a, reason: collision with root package name */
    public static final String f3149a = "logger";

    /* renamed from: b, reason: collision with root package name */
    public static final int f3150b = 2;

    /* renamed from: c, reason: collision with root package name */
    public static final int f3151c = 3;
    public static final int d = 4;
    public static final int e = 5;
    public static final int f = 6;
    public static final int g = 7;
    public static final int h = -1;
    private static volatile bb i;

    private bb() {
    }

    public static bb a() {
        if (i == null) {
            synchronized (bb.class) {
                if (i == null) {
                    i = new bb();
                }
            }
        }
        return i;
    }

    public boolean a(String str, int i2) {
        return i2 >= bt.f3185c;
    }

    public boolean a(int i2) {
        return a(f3149a, i2);
    }

    private String e(Object[] objArr) {
        StringBuilder sb = new StringBuilder();
        for (Object obj : objArr) {
            sb.append(obj);
            sb.append(' ');
        }
        return sb.toString();
    }

    public int a(Object... objArr) {
        if (a(3)) {
            return a(e(objArr));
        }
        return -1;
    }

    public int a(String str) {
        return a(f3149a, str);
    }

    public int a(String str, String str2) {
        if (!a(3)) {
            return -1;
        }
        try {
            return Log.d(str, str2);
        } catch (Exception unused) {
            return -1;
        }
    }

    public int a(Throwable th) {
        return a("", th);
    }

    public int a(String str, Throwable th) {
        if (!a(3)) {
            return -1;
        }
        try {
            return Log.d(f3149a, str, th);
        } catch (Exception unused) {
            return -1;
        }
    }

    public int b(String str) {
        if (!a(5)) {
            return -1;
        }
        try {
            return Log.w(f3149a, str);
        } catch (Exception unused) {
            return -1;
        }
    }

    public int b(Object... objArr) {
        if (a(5)) {
            return b(e(objArr));
        }
        return -1;
    }

    public int b(String str, Throwable th) {
        if (!a(5)) {
            return -1;
        }
        try {
            return Log.w(f3149a, str, th);
        } catch (Exception unused) {
            return -1;
        }
    }

    public int b(Throwable th) {
        return b("", th);
    }

    public int c(Object... objArr) {
        if (a(6)) {
            return c(e(objArr));
        }
        return -1;
    }

    public int c(String str) {
        if (!a(6)) {
            return -1;
        }
        try {
            return Log.e(f3149a, str);
        } catch (Exception unused) {
            return -1;
        }
    }

    public int c(Throwable th) {
        return c("", th);
    }

    public int c(String str, Throwable th) {
        if (!a(6)) {
            return -1;
        }
        try {
            return Log.e(f3149a, str, th);
        } catch (Exception unused) {
            return -1;
        }
    }

    public int d(String str) {
        return b(f3149a, str);
    }

    public int b(String str, String str2) {
        if (!a(4)) {
            return -1;
        }
        try {
            return Log.i(str, str2);
        } catch (Exception unused) {
            return -1;
        }
    }

    public int d(Object... objArr) {
        if (a(4)) {
            return d(e(objArr));
        }
        return -1;
    }

    public int d(String str, Throwable th) {
        if (!a(4)) {
            return -1;
        }
        try {
            return Log.i(f3149a, str, th);
        } catch (Exception unused) {
            return -1;
        }
    }
}
