package com.sigmob.sdk.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import com.sigmob.sdk.common.utils.m;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class p {
    public static SharedPreferences a(Context context) {
        m.a.a(context);
        return context.getSharedPreferences("com.sigmob.Settings", 4);
    }

    public static SharedPreferences a(Context context, String str) {
        m.a.a(context);
        m.a.a(str);
        return context.getSharedPreferences(str, 4);
    }
}
