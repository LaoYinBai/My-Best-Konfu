package com.bytedance.common.push.utility;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: a, reason: collision with root package name */
    static int f3823a = 4;

    /* renamed from: b, reason: collision with root package name */
    static AbstractC0109a f3824b = null;

    /* renamed from: c, reason: collision with root package name */
    private static final String f3825c = "Logger";

    /* renamed from: com.bytedance.common.push.utility.a$a, reason: collision with other inner class name */
    public static abstract class AbstractC0109a {
        public void a(String str, String str2) {
        }

        public void a(String str, String str2, Throwable th) {
        }

        public void b(String str, String str2) {
        }

        public void b(String str, String str2, Throwable th) {
        }

        public void c(String str, String str2) {
        }

        public void c(String str, String str2, Throwable th) {
        }

        public void d(String str, String str2) {
        }
    }

    public static void a(int i) {
        f3823a = i;
    }

    public static int a() {
        return f3823a;
    }

    public static boolean b() {
        return f3823a <= 3;
    }

    public static void a(String str) {
        a(f3825c, str);
    }

    public static void a(String str, String str2) {
        if (str2 != null && f3823a <= 2) {
            Log.v(str, str2);
        }
    }

    public static void a(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && f3823a <= 2) {
            Log.v(str, str2, th);
        }
    }

    public static void b(String str) {
        b(f3825c, str);
    }

    public static void b(String str, String str2) {
        if (str2 != null && f3823a <= 3) {
            Log.d(str, str2);
        }
    }

    public static void b(String str, String str2, Throwable th) {
        if (!(str2 == null && th == null) && f3823a <= 3) {
            Log.d(str, str2, th);
        }
    }

    public static void c(String str) {
        c(f3825c, str);
    }

    public static void c(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (f3823a <= 4) {
            Log.i(str, str2);
        }
        if (f3824b != null) {
            f3824b.a(str, str2);
        }
    }

    public static void c(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (f3823a <= 4) {
            Log.i(str, str2, th);
        }
        if (f3824b != null) {
            f3824b.a(str, str2, th);
        }
    }

    public static void d(String str) {
        d(f3825c, str);
    }

    public static void d(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (f3823a <= 5) {
            Log.w(str, str2);
        }
        if (f3824b != null) {
            f3824b.c(str, str2);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (f3823a <= 5) {
            Log.w(str, str2, th);
        }
        if (f3824b != null) {
            f3824b.c(str, str2, th);
        }
    }

    public static void e(String str) {
        e(f3825c, str);
    }

    public static void e(String str, String str2) {
        if (str2 == null) {
            return;
        }
        if (f3823a <= 6) {
            Log.e(str, str2);
        }
        if (f3824b != null) {
            f3824b.b(str, str2);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (str2 == null && th == null) {
            return;
        }
        if (f3823a <= 6) {
            Log.e(str, str2, th);
        }
        if (f3824b != null) {
            f3824b.b(str, str2, th);
        }
    }

    public static void f(String str) {
        f(f3825c, str);
    }

    public static void f(String str, String str2) {
        if (f3823a <= 3) {
            Log.d(str, str2);
        }
        if (f3824b != null) {
            f3824b.d(str, str2);
        }
    }

    public static void a(String str, int i) throws Exception {
        try {
            throw new Exception();
        } catch (Exception e) {
            StackTraceElement[] stackTrace = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            for (int i2 = 1; i2 < Math.min(i, stackTrace.length); i2++) {
                if (i2 > 1) {
                    sb.append("\n");
                }
                sb.append(h(stackTrace[i2].getClassName()));
                sb.append(".");
                sb.append(stackTrace[i2].getMethodName());
            }
            a(str, sb.toString());
        }
    }

    private static String h(String str) {
        int iLastIndexOf = str.lastIndexOf(46);
        return iLastIndexOf < 0 ? str : str.substring(iLastIndexOf + 1);
    }

    public static void a(Throwable th) {
        if (th == null) {
            return;
        }
        th.printStackTrace();
        if (b()) {
            throw new RuntimeException("Error! Now in debug, we alert to you to correct it !", th);
        }
    }

    public static void g(String str) {
        if (b()) {
            throw new IllegalStateException(str);
        }
    }

    public static void a(AbstractC0109a abstractC0109a) {
        f3824b = abstractC0109a;
    }
}
