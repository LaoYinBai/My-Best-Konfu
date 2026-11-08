package com.ss.union.game.sdk.core.base.cps;

import android.util.Log;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CpsLog {
    private static String TAG = "Core_LGCPS";
    private static String PREFIX = TAG + Constants.COLON_SEPARATOR;
    private static boolean showLog = Log.isLoggable("LGCPS", 2);

    private static boolean isShowLog() {
        return LogUtils.isDebug || showLog;
    }

    public static void d(String str, String str2) {
        if (isShowLog()) {
            LogUtils.log(str, PREFIX + str2);
        }
    }

    public static void d(String str, String str2, Exception exc) {
        if (isShowLog()) {
            exc.printStackTrace();
            LogUtils.log(str, PREFIX + str2 + " " + exc.getMessage());
        }
    }

    public static void d(String str) {
        if (isShowLog()) {
            LogUtils.log(TAG, str);
        }
    }

    public static void e(String str, String str2) {
        LogUtils.log(str, PREFIX + str2);
    }

    public static void e(String str) {
        LogUtils.log(TAG, str);
    }

    public static void forceE(String str) {
        e(str);
        if (isShowLog()) {
            throw new RuntimeException(str);
        }
    }
}
