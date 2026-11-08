package com.ksad.download.c;

import android.os.Build;
import android.os.StatFs;
import android.text.TextUtils;
import java.io.File;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes2.dex */
public class a {
    public static long a(String str) {
        StatFs statFs = new StatFs(str);
        if (Build.VERSION.SDK_INT >= 18) {
            try {
                return statFs.getAvailableBytes();
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        return statFs.getBlockSize() * statFs.getAvailableBlocks();
    }

    public static boolean a(File file) {
        return file != null && file.exists();
    }

    public static String b(String str) {
        int iLastIndexOf;
        return (!TextUtils.isEmpty(str) && (iLastIndexOf = str.lastIndexOf(46)) >= 0) ? str.substring(iLastIndexOf + 1) : "";
    }
}
