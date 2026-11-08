package com.cmic.sso.sdk.d;

import android.util.Log;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class c {

    /* renamed from: a, reason: collision with root package name */
    private static final c f5447a = new c();

    /* renamed from: b, reason: collision with root package name */
    private static boolean f5448b = false;

    public static void a(boolean z) {
        f5448b = z;
    }

    public static void a(String str, String str2) {
        if (f5448b) {
            Log.e("CMCC-SDK:" + str, "" + str2);
        }
    }

    public static void b(String str, String str2) {
        if (f5448b) {
            Log.d("CMCC-SDK:" + str, "" + str2);
        }
    }

    public static void c(String str, String str2) {
        if (f5448b) {
            Log.i("CMCC-SDK:" + str, "" + str2);
        }
    }
}
