package com.ss.union.game.sdk.core.base.utils;

import com.ss.union.game.sdk.common.util.logger.LogConstant;
import com.ss.union.game.sdk.common.util.logger.LogUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LogCoreUtils {
    public static void logInit(String str) {
        LogUtils.log(LogConstant.TAG_CORE_INIT, str);
    }

    public static void logAutomaticDetection(String str) {
        LogUtils.log(LogConstant.TAG_CORE_AUTOMATIC_DETECTION, str);
    }
}
