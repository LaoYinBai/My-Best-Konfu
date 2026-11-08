package com.ss.union.game.sdk.core.cgsdk;

import android.app.Activity;
import android.os.Bundle;
import com.cgs.out.CGConfig;
import com.cgs.out.CGManager;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class CGSDKManager {
    private static boolean a;

    public static void init() {
        if (a) {
            return;
        }
        try {
            CGManager.init(GlobalApplicationUtils.getContext(), new CGConfig.Builder().setApplicationId(AppIdManager.apAppID()).setChannel(ConfigManager.AppConfig.appChannel()).setDeviceId(AppLogManager.getInstance().getDid()).setInstallId(AppLogManager.getInstance().getIid()).setUserId(AppLogManager.getInstance().getUserID()).setRegionType(100).build());
            a = true;
            LogUtils.log("CGSDK init finish.");
            Activity topActivity = ActivityUtils.getTopActivity();
            if (topActivity != null) {
                CGManager.getInstance().callbacksLogger(topActivity);
            } else {
                ActivityUtils.registerActivityLifecycleCallbacks(new ActivityUtils.LifecycleCallbacks() { // from class: com.ss.union.game.sdk.core.cgsdk.CGSDKManager.1
                    @Override // com.ss.union.game.sdk.common.util.ActivityUtils.LifecycleCallbacks, android.app.Application.ActivityLifecycleCallbacks
                    public void onActivityCreated(Activity activity, Bundle bundle) {
                        super.onActivityCreated(activity, bundle);
                        ActivityUtils.unregisterActivityLifecycleCallbacks(this);
                        CGManager.getInstance().callbacksLogger(activity);
                    }
                });
            }
        } catch (Throwable unused) {
        }
    }

    public static void loggerAdStart(long j, long j2) {
        if (a) {
            LogUtils.log("CGSDK loggerAdStart. codeId = " + j);
            CGManager.getInstance().loggerAdStart(j, j2);
        }
    }

    public static void loggerAdClose(long j) {
        if (a) {
            LogUtils.log("CGSDK loggerAdClose. codeId = " + j);
            CGManager.getInstance().loggerAdClose(j);
        }
    }
}
