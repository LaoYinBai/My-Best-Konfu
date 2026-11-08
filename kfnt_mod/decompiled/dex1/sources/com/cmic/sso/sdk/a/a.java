package com.cmic.sso.sdk.a;

import android.annotation.SuppressLint;
import android.os.Build;
import com.bytedance.common.utility.DeviceUtils;

@SuppressLint({"NewApi"})
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class a {

    /* renamed from: com.cmic.sso.sdk.a.a$a, reason: collision with other inner class name */
    public enum EnumC0160a {
        UNKNOWN,
        SAMSUNG,
        HUAWEI
    }

    private static EnumC0160a b() {
        String str = Build.BRAND;
        if (str.equalsIgnoreCase(DeviceUtils.ROM_SAMSUNG)) {
            return EnumC0160a.SAMSUNG;
        }
        if (str.equalsIgnoreCase("Huawei")) {
            return EnumC0160a.HUAWEI;
        }
        return EnumC0160a.UNKNOWN;
    }

    static int a() {
        return a(b());
    }

    private static int a(EnumC0160a enumC0160a) {
        switch (enumC0160a) {
            case HUAWEI:
                return 0;
            case SAMSUNG:
                return 1;
            default:
                return -1;
        }
    }
}
