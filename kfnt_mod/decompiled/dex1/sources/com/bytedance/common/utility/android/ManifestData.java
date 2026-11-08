package com.bytedance.common.utility.android;

import android.content.Context;
import androidx.annotation.Nullable;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ManifestData {
    private static Object readKey(Context context, String str) {
        if (context == null) {
            return null;
        }
        try {
            return context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.get(str);
        } catch (Exception unused) {
            return null;
        }
    }

    @Nullable
    public static String getString(Context context, String str) {
        Object key = readKey(context, str);
        if (key == null) {
            return null;
        }
        return (String) key;
    }

    public static int getInt(Context context, String str) {
        Object key = readKey(context, str);
        if (key == null) {
            return -1;
        }
        return ((Integer) key).intValue();
    }

    @Nullable
    public static Boolean getBoolean(Context context, String str) {
        Object key = readKey(context, str);
        if (key == null) {
            return null;
        }
        return (Boolean) key;
    }

    public static Object get(Context context, String str) {
        return readKey(context, str);
    }
}
