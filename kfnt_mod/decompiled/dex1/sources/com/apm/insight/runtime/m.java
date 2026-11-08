package com.apm.insight.runtime;

import com.apm.insight.MonitorCrash;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class m {

    /* renamed from: a, reason: collision with root package name */
    private static MonitorCrash f3047a = null;

    /* renamed from: b, reason: collision with root package name */
    private static int f3048b = -1;

    /* renamed from: c, reason: collision with root package name */
    private static int f3049c;

    public static MonitorCrash a() {
        if (f3047a == null) {
            f3047a = MonitorCrash.initSDK(com.apm.insight.h.g(), "240740", 1040090L, "1.4.0", "com.apm.insight");
            f3047a.config().setChannel("release");
        }
        return f3047a;
    }

    public static void a(Throwable th, String str) {
        if (com.apm.insight.h.g() != null && b()) {
            a().reportCustomErr(str, "INNER", th);
        }
    }

    private static boolean b() {
        if (f3048b == -1) {
            f3048b = 5;
        }
        if (f3049c >= f3048b) {
            return false;
        }
        f3049c++;
        return true;
    }
}
