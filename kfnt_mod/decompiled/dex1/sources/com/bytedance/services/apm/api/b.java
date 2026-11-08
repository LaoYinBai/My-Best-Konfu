package com.bytedance.services.apm.api;

import java.util.Collection;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static IEnsure f5317a;

    private b() {
    }

    public static void a(IEnsure iEnsure) {
        f5317a = iEnsure;
    }

    public static IEnsure a() {
        return f5317a;
    }

    public static boolean a(boolean z) {
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureTrue(z);
        return z;
    }

    public static boolean b(boolean z) {
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureFalse(z);
        return z;
    }

    public static boolean a(boolean z, String str) {
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureTrue(z, str);
        return z;
    }

    public static boolean a(boolean z, String str, Map<String, String> map) {
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureTrue(z, str, map);
        return z;
    }

    public static boolean b(boolean z, String str) {
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureFalse(z, str);
        return z;
    }

    public static boolean b(boolean z, String str, Map<String, String> map) {
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureFalse(z, str, map);
        return z;
    }

    public static void b() {
        if (f5317a == null) {
            return;
        }
        f5317a.ensureNotReachHere();
    }

    public static void a(String str) {
        if (f5317a == null) {
            return;
        }
        f5317a.ensureNotReachHere(str);
    }

    public static void a(Throwable th) {
        if (f5317a == null) {
            return;
        }
        f5317a.ensureNotReachHere(th);
    }

    public static void a(int i, Throwable th, String str, boolean z) {
        if (f5317a == null) {
            return;
        }
        f5317a.reportLogException(i, th, str);
    }

    public static void a(Throwable th, String str) {
        if (f5317a == null) {
            return;
        }
        f5317a.ensureNotReachHere(th, str);
    }

    public static void a(String str, Map<String, String> map) {
        if (f5317a == null) {
            return;
        }
        f5317a.ensureNotReachHere(str, map);
    }

    public static void a(Throwable th, String str, Map<String, String> map) {
        if (f5317a == null) {
            return;
        }
        f5317a.ensureNotReachHere(th, str, map);
    }

    public static boolean a(Collection collection) {
        boolean z = (collection == null || collection.isEmpty()) ? false : true;
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureNotEmpty(collection);
        return z;
    }

    public static boolean a(Object obj) {
        boolean z = obj != null;
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureNotNull(obj);
        return z;
    }

    public static boolean a(Object obj, String str) {
        boolean z = obj != null;
        if (f5317a == null) {
            return z;
        }
        f5317a.ensureNotNull(obj, str);
        return z;
    }
}
