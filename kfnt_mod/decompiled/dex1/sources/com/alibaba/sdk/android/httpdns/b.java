package com.alibaba.sdk.android.httpdns;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class b {

    /* renamed from: a, reason: collision with root package name */
    private static SharedPreferences f2322a = null;

    /* renamed from: a, reason: collision with other field name */
    private static boolean f9a = true;

    /* renamed from: b, reason: collision with root package name */
    private static boolean f2323b = true;

    static void a(Context context) {
        if (context != null) {
            f2322a = context.getSharedPreferences("httpdns_config_enable", 0);
            if (f2322a != null) {
                f9a = f2322a.getBoolean("key_enable", true);
            }
        }
    }

    public static void a(boolean z) {
        f9a = z;
        if (f2322a != null) {
            SharedPreferences.Editor editorEdit = f2322a.edit();
            editorEdit.putBoolean("key_enable", z);
            editorEdit.apply();
        }
    }

    public static boolean a() {
        return f9a && f2323b;
    }

    public static void b(boolean z) {
        f2323b = z;
    }
}
