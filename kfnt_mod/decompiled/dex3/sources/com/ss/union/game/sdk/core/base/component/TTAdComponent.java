package com.ss.union.game.sdk.core.base.component;

import android.util.Log;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.common.util.reflect.Reflect;
import com.ss.union.game.sdk.common.util.reflect.ReflectException;
import com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class TTAdComponent {
    private static final String CLASS_LG_AD_INIT = "com.ss.union.game.sdk.ad.LGAdInit";
    private static final String CLASS_LG_AD_MANAGER = "com.ss.union.game.sdk.ad.LGAdManager";

    public static void init() {
        try {
            Reflect.onClass(CLASS_LG_AD_INIT).call(PointCategory.INIT, AppIdManager.adAppID(), ConfigManager.AppConfig.appName(), AppLogManager.getInstance().getDid(), Boolean.valueOf(ConfigManager.AppConfig.isDebug()));
        } catch (ReflectException e) {
            LogCoreUtils.logInit("Ad component reflect exception. init() " + Log.getStackTraceString(e));
        }
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.INIT, LGDetectionConstant.InitItem.DETECTION_ID_PANGOLIN, LGDetectionConstant.DetectionState.PASS);
        AppLogManager.getInstance().registerIdUpdateListener(new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.core.base.component.TTAdComponent.1
            @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
            public void onIdChanged(String str, String str2) {
                TTAdComponent.syncDidToAd(str);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void syncDidToAd(String str) {
        try {
            Reflect.onClass(CLASS_LG_AD_INIT).call("syncDidToAd", str);
        } catch (ReflectException e) {
            LogCoreUtils.logInit("Ad component reflect exception. syncDidToAd() " + Log.getStackTraceString(e));
        }
    }

    public static String getSDKVersion() {
        try {
            return (String) Reflect.onClass(CLASS_LG_AD_MANAGER).call("getMediationAdService").call("getSDKVersion").get();
        } catch (ReflectException e) {
            LogCoreUtils.logInit("Ad component reflect exception. getSDKVersion()" + Log.getStackTraceString(e));
            return "";
        }
    }
}
