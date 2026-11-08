package com.ss.union.game.sdk.ad.a;

import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.common.util.reflect.Reflect;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static final String a = "com.ss.union.game.sdk.core.cgsdk.CGSDKManager";

    public static void a(String str) {
        try {
            Reflect.onClass(a).call("loggerAdStart", Long.valueOf(Long.parseLong(str)), 0L);
        } catch (Throwable unused) {
            LogUtils.log("CGSdkUtil loggerAdStart failed.");
        }
    }

    public static void b(String str) {
        try {
            Reflect.onClass(a).call("loggerAdClose", Long.valueOf(Long.parseLong(str)));
        } catch (Throwable unused) {
            LogUtils.log("CGSdkUtil loggerAdClose failed.");
        }
    }
}
