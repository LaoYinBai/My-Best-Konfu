package com.apm.insight.runtime;

import android.os.Build;
import android.system.Os;
import android.system.OsConstants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class q {

    /* renamed from: a, reason: collision with root package name */
    private static long f3060a = -1;

    public static class a {

        /* renamed from: a, reason: collision with root package name */
        private static long f3061a = -1;

        public static long a() {
            if (q.f3060a == -1) {
                long unused = q.f3060a = 1000 / b();
            }
            return q.f3060a;
        }

        public static long a(long j) {
            if (f3061a > 0) {
                return f3061a;
            }
            long jSysconf = Build.VERSION.SDK_INT >= 21 ? Os.sysconf(OsConstants._SC_CLK_TCK) : Build.VERSION.SDK_INT >= 14 ? a("_SC_CLK_TCK", j) : j;
            if (jSysconf > 0) {
                j = jSysconf;
            }
            f3061a = j;
            return f3061a;
        }

        private static long a(String str, long j) {
            try {
                int i = Class.forName("libcore.io.OsConstants").getField(str).getInt(null);
                Class<?> cls = Class.forName("libcore.io.Libcore");
                Class<?> cls2 = Class.forName("libcore.io.Os");
                return ((Long) cls2.getMethod("sysconf", Integer.TYPE).invoke(cls.getField("os").get(null), Integer.valueOf(i))).longValue();
            } catch (Throwable th) {
                th.printStackTrace();
                return j;
            }
        }

        public static long b() {
            return a(100L);
        }
    }
}
