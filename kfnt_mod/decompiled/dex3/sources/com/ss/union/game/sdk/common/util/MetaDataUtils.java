package com.ss.union.game.sdk.common.util;

import android.content.Context;
import android.content.pm.PackageManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class MetaDataUtils {
    private static Object a(Context context, String str) {
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str);
        } catch (PackageManager.NameNotFoundException unused) {
            return null;
        }
    }

    public static String getString(Context context, String str) {
        return (String) a(context, str);
    }

    public static int getInt(Context context, String str) {
        return ((Integer) a(context, str)).intValue();
    }

    public static Boolean getBoolean(Context context, String str) {
        return (Boolean) a(context, str);
    }

    public static Object get(Context context, String str) {
        return a(context, str);
    }
}
