package com.xiaomi.channel.commonutils.logger;

import android.content.Context;
import android.os.Process;
import android.util.Log;
import com.xiaomi.push.l;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public abstract class b {
    private static int a = 2;

    /* renamed from: a, reason: collision with other field name */
    private static Context f23a;

    /* renamed from: a, reason: collision with other field name */
    private static boolean f29a;

    /* renamed from: a, reason: collision with other field name */
    private static String f26a = "XMPush-" + Process.myPid();

    /* renamed from: a, reason: collision with other field name */
    private static LoggerInterface f24a = new a();

    /* renamed from: a, reason: collision with other field name */
    private static final HashMap<Integer, Long> f27a = new HashMap<>();
    private static final HashMap<Integer, String> b = new HashMap<>();

    /* renamed from: a, reason: collision with other field name */
    private static final Integer f25a = -1;

    /* renamed from: a, reason: collision with other field name */
    private static AtomicInteger f28a = new AtomicInteger(1);

    static class a implements LoggerInterface {
        private String a = b.f26a;

        a() {
        }

        @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
        public void log(String str) {
            Log.v(this.a, str);
        }

        @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
        public void log(String str, Throwable th) {
            Log.v(this.a, str, th);
        }

        @Override // com.xiaomi.channel.commonutils.logger.LoggerInterface
        public void setTag(String str) {
            this.a = str;
        }
    }

    public static int a() {
        return a;
    }

    public static Integer a(String str) {
        if (a > 1) {
            return f25a;
        }
        Integer numValueOf = Integer.valueOf(f28a.incrementAndGet());
        f27a.put(numValueOf, Long.valueOf(System.currentTimeMillis()));
        b.put(numValueOf, str);
        f24a.log(str + " starts");
        return numValueOf;
    }

    /* renamed from: a, reason: collision with other method in class */
    private static String m21a(String str) {
        return b() + str;
    }

    public static String a(String str, String str2) {
        return "[" + str + "] " + str2;
    }

    public static void a(int i) {
        if (i < 0 || i > 5) {
            a(2, "set log level as " + i);
        }
        a = i;
    }

    public static void a(int i, String str) {
        if (i >= a) {
            f24a.log(str);
        }
    }

    public static void a(int i, String str, Throwable th) {
        if (i >= a) {
            f24a.log(str, th);
        }
    }

    public static void a(int i, Throwable th) {
        if (i >= a) {
            f24a.log("", th);
        }
    }

    public static void a(Context context) {
        f23a = context;
        if (l.m548a(context)) {
            f29a = true;
        }
    }

    public static void a(LoggerInterface loggerInterface) {
        f24a = loggerInterface;
    }

    public static void a(Integer num) {
        if (a > 1 || !f27a.containsKey(num)) {
            return;
        }
        long jLongValue = f27a.remove(num).longValue();
        String strRemove = b.remove(num);
        long jCurrentTimeMillis = System.currentTimeMillis() - jLongValue;
        f24a.log(strRemove + " ends in " + jCurrentTimeMillis + " ms");
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m22a(String str) {
        a(2, m21a(str));
    }

    /* renamed from: a, reason: collision with other method in class */
    public static void m23a(String str, String str2) {
        a(2, b(str, str2));
    }

    public static void a(String str, Throwable th) {
        a(4, m21a(str), th);
    }

    public static void a(Throwable th) {
        a(4, th);
    }

    private static String b() {
        return "[Tid:" + Thread.currentThread().getId() + "] ";
    }

    private static String b(String str, String str2) {
        return b() + a(str, str2);
    }

    public static void b(String str) {
        a(0, m21a(str));
    }

    public static void c(String str) {
        a(1, m21a(str));
    }

    public static void d(String str) {
        a(4, m21a(str));
    }

    public static void e(String str) {
        if (f29a) {
            m22a(str);
        } else {
            Log.i(f26a, m21a(str));
        }
    }
}
