package com.ss.union.game.sdk.core.base.utils;

import android.content.Context;
import android.text.TextUtils;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class DanJuanUtils {
    public static final String TAG = "DAN_JUAN";
    private static boolean isRunningVirtual;

    public static void init(Context context) {
        try {
            String string = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128).metaData.getString("danjuan_virtual_machine_mark");
            if (string != null && string.equals("com.bd.ad.v.game.center")) {
                isRunningVirtual = true;
            }
            LogCoreUtils.logInit("is running in danjuan virtual = " + isRunningVirtual);
            reportEvent();
        } catch (Throwable unused) {
        }
    }

    public static boolean isRunningDanJuanVirtual() {
        return isRunningVirtual;
    }

    private static void reportEvent() {
        if (isRunningVirtual) {
            String strAppName = ConfigManager.AppConfig.appName();
            if (TextUtils.isEmpty(strAppName)) {
                return;
            }
            HashMap map = new HashMap();
            map.put("ohayoo_sdk_danjuan", strAppName);
            PageStater.onEvent("danjuan_request", map);
        }
    }
}
