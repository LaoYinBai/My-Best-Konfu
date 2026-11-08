package com.tachikoma.core.log;

import android.util.Log;
import com.tachikoma.core.Tachikoma;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class Logger {
    private static String TAG = "tachikoma";
    private static boolean enableLog;

    public static void d(String str, String str2) {
        if (enableLog) {
            return;
        }
        if (Tachikoma.getInstance().logger() != null) {
            Tachikoma.getInstance().logger().d(str, str2);
        } else {
            Log.d(str, str2);
        }
    }

    public static String getStackTraceString(Throwable th) {
        return Tachikoma.getInstance().logger() != null ? Tachikoma.getInstance().logger().getStackTraceString(th) : Log.getStackTraceString(th);
    }

    public static void logE(String str, Throwable th) {
        if (enableLog) {
            if (Tachikoma.getInstance().logger() != null) {
                Tachikoma.getInstance().logger().e(TAG, str, th);
            } else {
                Log.e(TAG, str, th);
            }
        }
    }

    public static void setShowLog(boolean z) {
        enableLog = enableLog || z;
    }
}
