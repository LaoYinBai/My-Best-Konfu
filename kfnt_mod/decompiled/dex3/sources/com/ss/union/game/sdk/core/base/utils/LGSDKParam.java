package com.ss.union.game.sdk.core.base.utils;

import android.text.TextUtils;
import com.ss.android.downloadlib.activity.TTDelegateActivity;
import com.ss.union.game.sdk.common.util.reflect.Reflect;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGSDKParam {
    private static final String KEY_SDK_PLATFORM_KEY = "lg_sdk_real_platform";
    private static final String KEY_SDK_PLATFORM_VERSION_KEY = "lg_sdk_real_platform_version";

    private static String sdkPlatform() {
        try {
            String str = (String) Reflect.onClass("com.ss.union.game.sdk.common.platform.a").call("platform").get();
            return !TextUtils.isEmpty(str) ? str : "Ohayoo_SDK";
        } catch (Throwable unused) {
            return "Ohayoo_SDK";
        }
    }

    private static String sdkVersion() {
        try {
            String str = (String) Reflect.onClass("com.ss.union.game.sdk.common.platform.a").call("version").get();
            return !TextUtils.isEmpty(str) ? str : "2333";
        } catch (Throwable unused) {
            return "2333";
        }
    }

    public static Map<String, String> commonParam() {
        HashMap map = new HashMap();
        map.put(KEY_SDK_PLATFORM_KEY, sdkPlatform());
        map.put(KEY_SDK_PLATFORM_VERSION_KEY, sdkVersion());
        try {
            Map map2 = (Map) Reflect.onClass("com.ss.union.game.sdk.common.platform.a").call(TTDelegateActivity.PARAM).get();
            if (map2 != null && map2.size() > 0) {
                for (Map.Entry entry : map2.entrySet()) {
                    if (entry != null) {
                        map.put(entry.getKey(), entry.getValue());
                    }
                }
            }
        } catch (Throwable unused) {
        }
        return map;
    }
}
