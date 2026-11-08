package com.huawei.agconnect;

import android.annotation.SuppressLint;
import android.content.Context;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public abstract class a {

    @SuppressLint({"StaticFieldLeak"})
    private static a a;

    public static void a(Context context) {
        Context applicationContext = context.getApplicationContext();
        if (applicationContext != null) {
            context = applicationContext;
        }
        a = new com.huawei.agconnect.core.a.a(context);
    }
}
