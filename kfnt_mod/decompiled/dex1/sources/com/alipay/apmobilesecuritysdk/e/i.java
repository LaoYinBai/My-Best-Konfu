package com.alipay.apmobilesecuritysdk.e;

import android.content.Context;
import com.bytedance.common.utility.date.DateDef;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class i {

    /* renamed from: a, reason: collision with root package name */
    private static String f2503a = "";

    /* renamed from: b, reason: collision with root package name */
    private static String f2504b = "";

    /* renamed from: c, reason: collision with root package name */
    private static String f2505c = "";
    private static String d = "";
    private static String e = "";
    private static Map<String, String> f = new HashMap();

    public static synchronized String a(String str) {
        String str2 = "apdidTokenCache" + str;
        if (f.containsKey(str2)) {
            String str3 = f.get(str2);
            if (com.alipay.security.mobile.module.a.a.b(str3)) {
                return str3;
            }
        }
        return "";
    }

    public static synchronized void a() {
    }

    public static synchronized void a(b bVar) {
        if (bVar != null) {
            f2503a = bVar.f2493a;
            f2504b = bVar.f2494b;
            f2505c = bVar.f2495c;
        }
    }

    public static synchronized void a(c cVar) {
        if (cVar != null) {
            f2503a = cVar.f2496a;
            f2504b = cVar.f2497b;
            d = cVar.d;
            e = cVar.e;
            f2505c = cVar.f2498c;
        }
    }

    public static synchronized void a(String str, String str2) {
        String str3 = "apdidTokenCache" + str;
        if (f.containsKey(str3)) {
            f.remove(str3);
        }
        f.put(str3, str2);
    }

    public static synchronized boolean a(Context context, String str) {
        boolean z;
        long j = DateDef.DAY;
        try {
            long jA = h.a(context);
            if (jA >= 0) {
                j = jA;
            }
        } catch (Throwable unused) {
        }
        try {
        } catch (Throwable th) {
            com.alipay.apmobilesecuritysdk.c.a.a(th);
        }
        z = Math.abs(System.currentTimeMillis() - h.h(context, str)) < j;
        return z;
    }

    public static synchronized String b() {
        return f2503a;
    }

    public static void b(String str) {
        f2503a = str;
    }

    public static synchronized String c() {
        return f2504b;
    }

    public static void c(String str) {
        f2504b = str;
    }

    public static synchronized String d() {
        return d;
    }

    public static void d(String str) {
        f2505c = str;
    }

    public static synchronized String e() {
        return e;
    }

    public static void e(String str) {
        d = str;
    }

    public static synchronized String f() {
        return f2505c;
    }

    public static void f(String str) {
        e = str;
    }

    public static synchronized c g() {
        return new c(f2503a, f2504b, f2505c, d, e);
    }

    public static void h() {
        f.clear();
        f2503a = "";
        f2504b = "";
        d = "";
        e = "";
        f2505c = "";
    }
}
