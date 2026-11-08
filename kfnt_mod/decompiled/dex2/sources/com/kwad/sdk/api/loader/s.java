package com.kwad.sdk.api.loader;

import android.os.Build;
import android.os.Process;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
class s {
    static boolean a() {
        if (Build.VERSION.SDK_INT >= 23) {
            return Process.is64Bit();
        }
        if (Build.VERSION.SDK_INT >= 21) {
            try {
                return ((Boolean) Reflect.a("dalvik.system.VMRuntime").d("getRuntime").d("is64Bit").a()).booleanValue();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static String b() {
        return a() ? "arm64-v8a" : "armeabi-v7a";
    }
}
