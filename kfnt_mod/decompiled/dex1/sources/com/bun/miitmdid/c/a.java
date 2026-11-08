package com.bun.miitmdid.c;

import androidx.annotation.Keep;
import com.bytedance.common.utility.DeviceUtils;

@Keep
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public enum a {
    UNSUPPORT(-1, "unsupport"),
    HUA_WEI(0, "HUAWEI"),
    XIAOMI(1, "Xiaomi"),
    VIVO(2, "vivo"),
    OPPO(3, "oppo"),
    MOTO(4, "motorola"),
    LENOVO(5, "lenovo"),
    ASUS(6, "asus"),
    SAMSUNG(7, DeviceUtils.ROM_SAMSUNG),
    MEIZU(8, "meizu"),
    NUBIA(10, "nubia"),
    ZTE(11, "ZTE"),
    ONEPLUS(12, "OnePlus"),
    BLACKSHARK(13, "blackshark"),
    FREEMEOS(30, "freemeos"),
    SSUIOS(31, "ssui");


    /* renamed from: a, reason: collision with root package name */
    @Keep
    private String f3282a;

    @Keep
    a(int i, String str) {
        this.f3282a = str;
    }

    @Keep
    public static native a a(String str);

    @Keep
    public static native a valueOf(String str);

    @Keep
    public static native a[] values();
}
