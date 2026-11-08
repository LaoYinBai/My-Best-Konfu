package com.bytedance.b.a.a.a;

import android.text.TextUtils;
import com.bytedance.b.a.a.d;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static b f3585a = new a();

    /* renamed from: b, reason: collision with root package name */
    private static ConcurrentHashMap<String, b> f3586b = new ConcurrentHashMap<>();

    static {
        f3586b.put(d.m, f3585a);
    }

    public static void a(b bVar) {
        f3585a = bVar;
        a(d.m, bVar);
    }

    public static void a(String str, b bVar) {
        if (bVar == null) {
            return;
        }
        f3586b.put(str, bVar);
    }

    public static int a(String str) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).a();
        }
        return f3585a.a();
    }

    public static int b(String str) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).b();
        }
        return f3585a.b();
    }

    public static List<String> a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).a(str2);
        }
        return f3585a.a(str2);
    }

    public static JSONObject c(String str) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).c();
        }
        return f3585a.c();
    }

    public static int d(String str) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).d();
        }
        return f3585a.d();
    }

    public static int e(String str) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).e() * 1000;
        }
        return f3585a.e() * 1000;
    }

    public static long f(String str) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).f();
        }
        return f3585a.f();
    }

    public static boolean g(String str) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).g();
        }
        return f3585a.g();
    }

    public static long h(String str) {
        if (!TextUtils.isEmpty(str) && f3586b.get(str) != null) {
            return f3586b.get(str).i();
        }
        return f3585a.i();
    }
}
