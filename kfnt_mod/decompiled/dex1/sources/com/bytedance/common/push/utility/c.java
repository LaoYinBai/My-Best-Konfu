package com.bytedance.common.push.utility;

import android.os.Build;
import java.lang.reflect.Method;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static Boolean f3838a;

    public static boolean a() {
        if (f3838a != null) {
            return f3838a.booleanValue();
        }
        boolean z = false;
        try {
            Method declaredMethod = Build.class.getDeclaredMethod("getString", String.class);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, "ro.build.version.emui");
            if ((objInvoke instanceof String) && !i.a((String) objInvoke)) {
                if (!"unknown".equals((String) objInvoke)) {
                    z = true;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        f3838a = Boolean.valueOf(z);
        return z;
    }
}
