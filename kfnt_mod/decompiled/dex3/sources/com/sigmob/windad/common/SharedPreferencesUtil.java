package com.sigmob.windad.common;

import android.content.Context;
import android.content.SharedPreferences;
import com.sigmob.sdk.common.utils.m;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public final class SharedPreferencesUtil {
    public static final String DEFAULT_PREFERENCE_NAME = "WindAdSettings";

    private SharedPreferencesUtil() {
    }

    public static SharedPreferences getSharedPreferences(Context context) {
        m.a.a(context);
        return context.getSharedPreferences(DEFAULT_PREFERENCE_NAME, 0);
    }

    public static SharedPreferences getSharedPreferences(Context context, String str) {
        m.a.a(context);
        m.a.a(str);
        return context.getSharedPreferences(str, 0);
    }
}
