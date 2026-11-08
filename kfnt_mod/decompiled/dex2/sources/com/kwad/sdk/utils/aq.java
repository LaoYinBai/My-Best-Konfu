package com.kwad.sdk.utils;

import android.content.Context;
import android.os.Environment;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class aq {
    @NonNull
    public static String a(Context context) {
        String externalStorageState;
        File externalFilesDir;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (IncompatibleClassChangeError | NullPointerException unused) {
            externalStorageState = "";
        }
        String path = null;
        if ("mounted".equals(externalStorageState) || !Environment.isExternalStorageRemovable()) {
            try {
                externalFilesDir = context.getExternalFilesDir(null);
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
                externalFilesDir = null;
            }
            if (externalFilesDir != null) {
                path = externalFilesDir.getPath();
            }
        }
        if (TextUtils.isEmpty(path)) {
            path = context.getFilesDir().getPath();
        }
        return path + File.separator + "ksadsdk";
    }

    public static File b(Context context) {
        String externalStorageState;
        File externalCacheDir;
        try {
            externalStorageState = Environment.getExternalStorageState();
        } catch (IncompatibleClassChangeError | NullPointerException unused) {
            externalStorageState = "";
        }
        String path = null;
        if ("mounted".equals(externalStorageState) || !Environment.isExternalStorageRemovable()) {
            try {
                externalCacheDir = context.getExternalCacheDir();
            } catch (Exception e) {
                com.kwad.sdk.core.d.a.a(e);
                externalCacheDir = null;
            }
            if (externalCacheDir != null) {
                path = externalCacheDir.getPath();
            }
        }
        if (TextUtils.isEmpty(path)) {
            path = context.getCacheDir().getPath();
        }
        return new File(path + File.separator + "ksadsdk");
    }

    public static File c(Context context) {
        return new File(a(context) + File.separator + "Download");
    }

    public static String d(Context context) {
        if (context == null) {
            return "";
        }
        return context.getFilesDir().getPath() + File.separator + "ksadsdk";
    }

    public static String e(Context context) {
        if (context == null) {
            return "";
        }
        return a(context) + File.separator + "ksad/download/js";
    }
}
