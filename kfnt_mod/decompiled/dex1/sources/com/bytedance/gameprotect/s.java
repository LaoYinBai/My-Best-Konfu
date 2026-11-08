package com.bytedance.gameprotect;

import android.content.Context;
import androidx.core.content.ContextCompat;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class s {

    /* renamed from: a, reason: collision with root package name */
    private static Context f3889a;

    public static void a(Context context) {
        f3889a = context.getApplicationContext();
    }

    public static boolean a() {
        return a("android.permission.READ_EXTERNAL_STORAGE");
    }

    public static boolean a(String str) {
        Context context = f3889a;
        return (context == null || -1 == ContextCompat.checkSelfPermission(context, str)) ? false : true;
    }
}
