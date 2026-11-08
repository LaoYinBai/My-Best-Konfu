package com.ss.union.game.sdk.common.util;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ArgumentCheckerUtils {
    public static void check(Object obj, String str) {
        if (obj != null) {
            return;
        }
        LogUtils.log("ArgumentCheckerUtils", str);
        throw new IllegalArgumentException(str);
    }

    public static void check(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            LogUtils.log("ArgumentCheckerUtils", str2);
            throw new IllegalArgumentException(str2);
        }
    }
}
