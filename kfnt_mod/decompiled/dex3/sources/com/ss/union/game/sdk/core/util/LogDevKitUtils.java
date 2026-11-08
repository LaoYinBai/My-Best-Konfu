package com.ss.union.game.sdk.core.util;

import com.ss.union.game.sdk.common.util.logger.LogConstant;
import com.ss.union.game.sdk.common.util.logger.LogUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LogDevKitUtils {
    public static void log(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT, str);
    }

    public static void logAntiAddiction(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_ADDICTION, str);
    }

    public static void logDeviceAntiAddiction(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_ADDICTION_DEVICE, str);
    }

    public static void logUpgrade(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_UPGRADE, str);
    }

    public static void logBrowser(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_BROWSER, str);
    }

    public static void logRealName(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_REAL_NAME, str);
    }

    public static void logPermission(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_PERMISSION, str);
    }

    public static void logSplash(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_SPLASH, str);
    }

    public static void logPrivacy(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_PRIVACY, str);
    }

    public static void logSensitiveWord(String str) {
        LogUtils.log(LogConstant.TAG_DEVKIT_SENSITIVE_WORDS, str);
    }
}
