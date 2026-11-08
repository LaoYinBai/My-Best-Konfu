package com.umeng.commonsdk.proguard;

import android.content.Context;
import android.os.Build;
import android.os.Looper;
import android.text.TextUtils;
import com.ss.android.account.model2.BDAccountPlatformEntity;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class f {
    private static e a;
    private static boolean b;

    public static String a(Context context) {
        if (context == null) {
            throw new RuntimeException("Context is null");
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            throw new IllegalStateException("Cannot be called from the main thread");
        }
        b(context);
        if (a == null) {
            return null;
        }
        try {
            return a.a(context);
        } catch (Exception unused) {
            return null;
        }
    }

    private static void b(Context context) {
        if (a != null || b) {
            return;
        }
        synchronized (f.class) {
            if (a == null && !b) {
                a = c(context);
                b = true;
            }
        }
    }

    private static e c(Context context) {
        String str = Build.BRAND;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        if (!str.equalsIgnoreCase(BDAccountPlatformEntity.PLAT_NAME_HUAWEI) && !str.equalsIgnoreCase("honor") && !str.equalsIgnoreCase("华为")) {
            if (!str.equalsIgnoreCase("xiaomi") && !str.equalsIgnoreCase("小米")) {
                if (str.equalsIgnoreCase("vivo")) {
                    return new h();
                }
                if (str.equalsIgnoreCase("oppo") || str.equalsIgnoreCase("oneplus")) {
                    return new g(context);
                }
                return null;
            }
            return new i();
        }
        return new d();
    }
}
