package com.bytedance.sdk.b.f;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {
    public static void a(String str, String str2, Object obj, Throwable th) {
        if (a.d) {
            Log.e(a.f5155c, str + " " + str2 + " " + obj, th);
        }
    }

    public static void a(String str, String str2, Object obj) {
        a(str, str2, obj, "");
    }

    public static void a(String str, String str2, Object obj, Object obj2) {
        a(str, str2, obj, obj2, "");
    }

    public static void a(String str, String str2, Object obj, Object obj2, Object obj3) {
        a(str, str2, obj, obj2, obj3, "");
    }

    public static void a(String str, String str2, Object obj, Object obj2, Object obj3, Object obj4) {
        if (a.d) {
            Log.e(a.f5155c, str + "# " + str2 + "(), " + obj + " " + obj2 + " " + obj3 + " " + obj4);
        }
    }
}
