package com.sigmob.volley;

import android.os.SystemClock;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class t {
    public static String a = "Sigmob-Volley";
    public static boolean b = false;
    private static final String c = "com.sigmob.volley.t";

    static class a {
        public static final boolean a = t.b;
        private final List<C0024a> b = new ArrayList();
        private boolean c = false;

        /* renamed from: com.sigmob.volley.t$a$a, reason: collision with other inner class name */
        private static class C0024a {
            public final String a;
            public final long b;
            public final long c;

            public C0024a(String str, long j, long j2) {
                this.a = str;
                this.b = j;
                this.c = j2;
            }
        }

        a() {
        }

        private long a() {
            if (this.b.size() == 0) {
                return 0L;
            }
            return this.b.get(this.b.size() - 1).c - this.b.get(0).c;
        }

        public synchronized void a(String str) {
            this.c = true;
            long jA = a();
            if (jA <= 0) {
                return;
            }
            long j = this.b.get(0).c;
            t.b("(%-4d ms) %s", Long.valueOf(jA), str);
            for (C0024a c0024a : this.b) {
                long j2 = c0024a.c;
                t.b("(+%-4d) [%2d] %s", Long.valueOf(j2 - j), Long.valueOf(c0024a.b), c0024a.a);
                j = j2;
            }
        }

        public synchronized void a(String str, long j) {
            if (this.c) {
                throw new IllegalStateException("Marker added to finished log");
            }
            this.b.add(new C0024a(str, j, SystemClock.elapsedRealtime()));
        }

        protected void finalize() {
            if (this.c) {
                return;
            }
            a("Request on the loose");
            t.c("Marker log finalized without finish() - uncaught exit point for request", new Object[0]);
        }
    }

    public static void a(String str, Object... objArr) {
        if (b) {
            Log.v(a, d(str, objArr));
        }
    }

    public static void a(Throwable th, String str, Object... objArr) {
        Log.e(a, d(str, objArr), th);
    }

    public static void b(String str, Object... objArr) {
        if (b) {
            Log.d(a, d(str, objArr));
        }
    }

    public static void c(String str, Object... objArr) {
        Log.e(a, d(str, objArr));
    }

    private static String d(String str, Object... objArr) {
        if (objArr != null && objArr.length != 0) {
            str = String.format(Locale.US, str, objArr);
        }
        StackTraceElement[] stackTrace = new Throwable().fillInStackTrace().getStackTrace();
        String str2 = "<unknown>";
        int i = 2;
        while (true) {
            if (i >= stackTrace.length) {
                break;
            }
            if (!stackTrace[i].getClassName().equals(c)) {
                String className = stackTrace[i].getClassName();
                String strSubstring = className.substring(className.lastIndexOf(46) + 1);
                str2 = strSubstring.substring(strSubstring.lastIndexOf(36) + 1) + "." + stackTrace[i].getMethodName();
                break;
            }
            i++;
        }
        return String.format(Locale.US, "[%d] %s: %s", Long.valueOf(Thread.currentThread().getId()), str2, str);
    }
}
