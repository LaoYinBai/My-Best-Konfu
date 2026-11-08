package com.alipay.sdk.app;

import com.alipay.sdk.util.f;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static boolean f2560a = false;

    /* renamed from: b, reason: collision with root package name */
    private static String f2561b;

    public static void a(String str) {
        f2561b = str;
    }

    public static String a() {
        return f2561b;
    }

    public static boolean b() {
        return f2560a;
    }

    public static void a(boolean z) {
        f2560a = z;
    }

    public static String c() {
        c cVarB = c.b(c.CANCELED.a());
        return a(cVarB.a(), cVarB.b(), "");
    }

    public static String d() {
        c cVarB = c.b(c.DOUBLE_REQUEST.a());
        return a(cVarB.a(), cVarB.b(), "");
    }

    public static String e() {
        c cVarB = c.b(c.PARAMS_ERROR.a());
        return a(cVarB.a(), cVarB.b(), "");
    }

    public static String a(int i, String str, String str2) {
        return "resultStatus={" + i + "};memo={" + str + "};result={" + str2 + f.d;
    }
}
