package com.ss.union.game.sdk.core.base.init;

import android.content.Context;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.FileUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.executeTime.impl.InitTime;
import com.ss.union.game.sdk.common.util.executeTime.in.IExecutorTimeLogger;
import com.ss.union.game.sdk.common.util.logger.LogConstant;
import com.ss.union.game.sdk.common.util.logger.LogUtils;
import com.ss.union.game.sdk.core.base.checker.ConfigChecker;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.EventJSONHeaders;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.base.utils.DanJuanUtils;
import com.ss.union.game.sdk.core.base.utils.LGSDKParam;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class GameSdkCoreInit {
    private static volatile boolean a;

    public static synchronized void init(Context context) {
        if (a) {
            return;
        }
        GlobalApplicationUtils.init(context);
        ConfigManager.init();
        LogUtils.isDebug = ConfigManager.AppConfig.isDebug();
        LogUtils.COMMON_TAG = ConfigManager.AppConfig.loggerPrefix() + Constants.ACCEPT_TIME_SEPARATOR_SERVER + 2333;
        InitTime.getInstance().setLogger(new IExecutorTimeLogger() { // from class: com.ss.union.game.sdk.core.base.init.GameSdkCoreInit.1
            @Override // com.ss.union.game.sdk.common.util.executeTime.in.IExecutorTimeLogger
            public void log(String str) {
                LogUtils.log(LogConstant.TAG_INIT, str);
            }
        });
        AppIdManager.init();
        new ConfigChecker().start(false);
        ActivityUtils.init(context);
        FileUtils.init(".LGSDK/", ".sys/");
        a(context);
        b(context);
        DanJuanUtils.init(context);
        a = true;
    }

    private static void a(Context context) {
        CoreNetClient.init(context, AppIdManager.sdkDemoAid());
        Map<String, String> mapCommonParam = LGSDKParam.commonParam();
        if (mapCommonParam == null || mapCommonParam.size() <= 0) {
            return;
        }
        for (Map.Entry<String, String> entry : mapCommonParam.entrySet()) {
            if (entry != null) {
                CoreNetClient.commonParam(entry.getKey(), entry.getValue());
            }
        }
    }

    private static void b(Context context) throws JSONException {
        PageStater.init(context, null);
        Map<String, String> mapCommonParam = LGSDKParam.commonParam();
        if (mapCommonParam == null || mapCommonParam.size() <= 0) {
            return;
        }
        for (Map.Entry<String, String> entry : mapCommonParam.entrySet()) {
            if (entry != null) {
                EventJSONHeaders.getInstance().update(entry.getKey(), entry.getValue());
            }
        }
    }
}
