package com.ss.union.game.sdk.core.splashEffect.a;

import com.ss.union.game.sdk.core.util.LogDevKitUtils;
import com.xiaomi.mipush.sdk.Constants;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "开屏特效";

    public static void a(String str) {
        LogDevKitUtils.logSplash(str);
    }

    public static void a(String str, String str2) {
        LogDevKitUtils.logSplash(str + " : " + str2);
    }

    public static void b(String str) {
        LogDevKitUtils.logSplash(str);
    }

    public static void b(String str, String str2) {
        LogDevKitUtils.logSplash(str + Constants.COLON_SEPARATOR + str2);
    }
}
