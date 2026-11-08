package com.bytedance.push.utils;

import android.os.Build;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class RomVersionParamHelper {

    /* renamed from: a, reason: collision with root package name */
    private static final String f4981a = String.valueOf(Build.VERSION.SDK);

    /* renamed from: b, reason: collision with root package name */
    private static final j f4982b = new j();

    /* renamed from: c, reason: collision with root package name */
    private static String f4983c;
    private static String d;

    static {
        String strB;
        f4983c = f4981a;
        try {
            strB = b();
        } catch (Throwable th) {
            th.printStackTrace();
            strB = null;
        }
        if (com.bytedance.common.push.utility.i.a(strB)) {
            strB = f4981a;
        }
        f4983c = strB;
    }

    public static String a() {
        return f4983c;
    }

    private static String b() {
        if (h()) {
            return i();
        }
        if (e()) {
            return f();
        }
        if (c()) {
            return d();
        }
        if (com.bytedance.common.push.b.a.a.a.a.a()) {
            return g();
        }
        return f4981a;
    }

    private static boolean c() {
        String str = Build.MANUFACTURER;
        if (com.bytedance.common.push.utility.i.a(str)) {
            return false;
        }
        return str.toLowerCase().contains("oppo");
    }

    private static String d() {
        if (c()) {
            return ("coloros_" + a("ro.build.version.opporom") + "_" + Build.DISPLAY).toLowerCase();
        }
        return f4981a;
    }

    private static boolean e() {
        String strA = a("ro.vivo.os.build.display.id");
        return !com.bytedance.common.push.utility.i.a(strA) && strA.toLowerCase().contains("funtouch");
    }

    private static String f() {
        return (a("ro.vivo.os.build.display.id") + "_" + a("ro.vivo.product.version")).toLowerCase();
    }

    private static String g() {
        return ("miui_" + a("ro.miui.ui.version.name") + "_" + Build.VERSION.INCREMENTAL).toLowerCase();
    }

    public static boolean isMiui12() {
        if (!com.bytedance.common.push.b.a.a.a.a.a()) {
            return false;
        }
        try {
            return Integer.parseInt(a("ro.miui.ui.version.name").substring(1)) >= 12;
        } catch (Exception unused) {
            return false;
        }
    }

    private static String a(String str) {
        return f4982b.a(str);
    }

    private static boolean h() {
        try {
            d = a("ro.build.version.emui");
            boolean zA = com.bytedance.common.push.utility.i.a(d);
            if (!zA) {
                d = d.toLowerCase();
            }
            return !zA;
        } catch (Exception e) {
            com.bytedance.common.push.utility.a.e(e.getMessage());
            return false;
        }
    }

    private static String i() {
        if (com.bytedance.common.push.utility.i.a(d)) {
            d = a("ro.build.version.emui");
        }
        String lowerCase = (d + "_" + Build.DISPLAY).toLowerCase();
        if (!com.bytedance.common.push.utility.i.a(lowerCase)) {
            return lowerCase.toLowerCase();
        }
        return f4981a;
    }
}
