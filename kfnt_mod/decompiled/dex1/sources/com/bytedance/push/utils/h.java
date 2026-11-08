package com.bytedance.push.utils;

import android.os.Build;
import android.text.TextUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class h {
    public static boolean a(String str) {
        if (com.bytedance.common.push.utility.i.a(str) || str.equalsIgnoreCase("unknown")) {
            return true;
        }
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) != '0') {
                return false;
            }
        }
        return true;
    }

    public static boolean a() {
        String str = Build.BRAND;
        if (!TextUtils.isEmpty(str) && str.toLowerCase().contains("vivo")) {
            return true;
        }
        String str2 = Build.MODEL;
        if (!TextUtils.isEmpty(str2) && str2.toLowerCase().contains("vivo")) {
            return true;
        }
        String str3 = Build.MANUFACTURER;
        return !TextUtils.isEmpty(str3) && str3.toLowerCase().contains("vivo");
    }

    public static boolean b() {
        return 22 == Build.VERSION.SDK_INT;
    }
}
