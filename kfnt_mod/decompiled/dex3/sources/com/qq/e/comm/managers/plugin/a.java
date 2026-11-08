package com.qq.e.comm.managers.plugin;

import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
class a {
    private static Method a;
    private static boolean b;

    static void a(Throwable th, String str) {
        try {
            Exception exc = new Exception("插件错误：" + str, th);
            if (b) {
                return;
            }
            if (a == null) {
                Method declaredMethod = Class.forName("com.tencent.bugly.crashreport.CrashReport").getDeclaredMethod("postCatchedException", Throwable.class);
                a = declaredMethod;
                declaredMethod.setAccessible(true);
            }
            a.invoke(null, exc);
        } catch (Throwable unused) {
            b = true;
        }
    }
}
