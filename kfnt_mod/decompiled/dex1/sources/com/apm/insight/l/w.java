package com.apm.insight.l;

import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import java.io.File;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class w {
    public static long a(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getTotalBytes();
            }
            return statFs.getBlockCount() * statFs.getBlockSize();
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static JSONObject a(Context context) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("inner_free", b());
            jSONObject.put("inner_total", c());
            jSONObject.put("sdcard_free", f());
            jSONObject.put("sdcard_total", g());
            jSONObject.put("inner_free_real", d());
            jSONObject.put("inner_total_real", e());
        } catch (Throwable unused) {
        }
        return jSONObject;
    }

    public static boolean a() {
        return "mounted".equals(Environment.getExternalStorageState());
    }

    private static long b() {
        try {
            return b(Environment.getRootDirectory());
        } catch (Throwable unused) {
            return 0L;
        }
    }

    public static long b(File file) {
        try {
            StatFs statFs = new StatFs(file.getPath());
            if (Build.VERSION.SDK_INT >= 18) {
                return statFs.getFreeBytes();
            }
            return statFs.getAvailableBlocks() * statFs.getBlockSize();
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private static long c() {
        try {
            return a(Environment.getRootDirectory());
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private static long d() {
        try {
            return b(com.apm.insight.h.g().getFilesDir());
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private static long e() {
        try {
            return a(com.apm.insight.h.g().getFilesDir());
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private static long f() {
        try {
            if (a()) {
                return Environment.getExternalStorageDirectory().getFreeSpace();
            }
            return 0L;
        } catch (Throwable unused) {
            return 0L;
        }
    }

    private static long g() {
        try {
            if (a()) {
                return Environment.getExternalStorageDirectory().getTotalSpace();
            }
            return 0L;
        } catch (Throwable unused) {
            return 0L;
        }
    }
}
