package com.bytedance.a;

import android.content.Context;
import android.content.SharedPreferences;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ca {

    /* renamed from: a, reason: collision with root package name */
    public static boolean f3450a;

    /* renamed from: b, reason: collision with root package name */
    public static d<SharedPreferences> f3451b = new a();

    public static class a extends d<SharedPreferences> {
        @Override // com.bytedance.a.d
        public SharedPreferences a(Object[] objArr) {
            return ((Context) objArr[0]).getSharedPreferences("ug_install_settings_pref", 0);
        }
    }

    public static boolean a(Context context) {
        if (context == null) {
            return false;
        }
        if (f3450a) {
            return true;
        }
        return f3451b.b(context).getBoolean("_install_started_v2", false);
    }
}
