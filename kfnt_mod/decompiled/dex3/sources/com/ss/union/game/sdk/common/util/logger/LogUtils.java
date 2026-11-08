package com.ss.union.game.sdk.common.util.logger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LogUtils implements LogConstant {
    public static volatile String COMMON_TAG = "LG_LOGGER";
    public static volatile boolean isDebug;

    public static class Disk {
        public static void log(String str) {
            c.a((Object) str);
        }

        public static void log(String str, Object... objArr) {
            c.a(str, objArr);
        }

        public static void log(Object obj) {
            c.a(obj);
        }

        public static void log(Throwable th) {
            c.a(th, "", new Object[0]);
        }

        public static void log(Throwable th, String str, Object... objArr) {
            c.a(th, str, objArr);
        }

        public static void log(int i, String str, String str2, Throwable th) {
            c.a(i, str, str2, th);
        }

        public static void json(String str) {
            c.b(str);
        }

        public static void xml(String str) {
            c.c(str);
        }
    }

    public static void log(String str) {
        if (isDebug) {
            h.a((Object) str);
        }
    }

    public static void log(String str, String str2) {
        if (isDebug) {
            h.a(3, str, str2, null);
        }
    }

    public static void log(String str, Object... objArr) {
        if (isDebug) {
            h.a(str, objArr);
        }
    }

    public static void log(Object obj) {
        if (isDebug) {
            h.a(obj);
        }
    }

    public static void log(Throwable th) {
        if (isDebug) {
            h.a(th, "", new Object[0]);
        }
    }

    public static void log(Throwable th, String str, Object... objArr) {
        if (isDebug) {
            h.a(th, str, objArr);
        }
    }

    public static void log(int i, String str, String str2, Throwable th) {
        if (isDebug) {
            h.a(i, str, str2, th);
        }
    }

    public static void json(String str) {
        if (isDebug) {
            h.b(str);
        }
    }

    public static void xml(String str) {
        if (isDebug) {
            h.c(str);
        }
    }
}
