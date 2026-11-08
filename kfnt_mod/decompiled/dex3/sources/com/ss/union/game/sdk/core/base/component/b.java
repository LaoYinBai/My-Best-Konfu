package com.ss.union.game.sdk.core.base.component;

import android.app.Activity;
import android.text.TextUtils;
import com.bytedance.applog.AppLog;
import com.ss.android.downloadad.api.download.AdDownloadModel;
import com.ss.android.pushmanager.PushCommonConstants;
import com.ss.union.game.sdk.common.net.CoreNetClient;
import com.ss.union.game.sdk.common.util.ActivityUtils;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener;
import com.ss.union.game.sdk.core.applog.AppLogManager;
import com.ss.union.game.sdk.core.applog.AppLogOaidListener;
import com.ss.union.game.sdk.core.applog.header.AppLogHeaderHelper;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.constant.SDKVersion;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.LGDetectionManager;
import com.ss.union.game.sdk.core.base.debug.automatic_detection.constants.LGDetectionConstant;
import com.ss.union.game.sdk.core.base.event.EventJSONHeaders;
import com.ss.union.game.sdk.core.base.utils.LGSDKParam;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;
import com.umeng.commonsdk.proguard.o;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class b {
    public static void a() {
        AppLogManager.getInstance().init(GlobalApplicationUtils.getContext(), AppIdManager.apAppID(), ConfigManager.AppConfig.appChannel(), true);
        AppLogManager.getInstance().registerIdUpdateListener(new AppLogIdUpdateListener() { // from class: com.ss.union.game.sdk.core.base.component.b.1
            @Override // com.ss.union.game.sdk.core.applog.AppLogIdUpdateListener
            public void onIdChanged(String str, String str2) throws JSONException {
                LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.APP_LOG, LGDetectionConstant.AppLogItem.DETECTION_ID_DID_RETURN, LGDetectionConstant.DetectionState.PASS);
                EventJSONHeaders.getInstance().updateDid(str);
                EventJSONHeaders.getInstance().updateIid(str2);
                b.f();
                b.e();
                b.g();
            }
        });
        AppLogManager.getInstance().registerOaidListener(new AppLogOaidListener() { // from class: com.ss.union.game.sdk.core.base.component.b.2
            @Override // com.ss.union.game.sdk.core.applog.AppLogOaidListener
            public void onOaidLoaded(String str) throws JSONException {
                EventJSONHeaders.getInstance().updateOaid(str);
            }
        });
        LGDetectionManager.getDetectionApi().detection(LGDetectionConstant.DetectionType.INIT, LGDetectionConstant.InitItem.DETECTION_ID_APP_LOG, LGDetectionConstant.DetectionState.PASS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void e() {
        try {
            Map<String, String> mapCommonParam = LGSDKParam.commonParam();
            if (mapCommonParam == null || mapCommonParam.size() <= 0) {
                return;
            }
            for (Map.Entry<String, String> entry : mapCommonParam.entrySet()) {
                if (entry != null) {
                    AppLogHeaderHelper.setCommonHeader(entry.getKey(), entry.getValue());
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void f() {
        CoreNetClient.commonParam("iid", AppLogManager.getInstance().getIid());
        CoreNetClient.commonParam("aid", AppIdManager.apAppID());
        CoreNetClient.commonParam("app_id", AppIdManager.lgAppID());
        CoreNetClient.commonParam("channel", ConfigManager.AppConfig.appChannel());
        CoreNetClient.commonParam("bd_did", AppLogManager.getInstance().getDid());
        CoreNetClient.commonParam("utm_campaign", PushCommonConstants.VALUE_OPEN);
        CoreNetClient.commonParam("utm_medium", "sdk");
        CoreNetClient.commonParam("sdk_version", "2333");
        CoreNetClient.commonParam("sdk_version_name", "2.3.3.3");
        CoreNetClient.commonParam(o.t, SDKVersion.LG_SDK_TYPE_U_SDK);
        if (!TextUtils.isEmpty(AppIdManager.dyAppKey())) {
            CoreNetClient.commonParam("client_key_douyin", AppIdManager.dyAppKey());
        }
        if (!TextUtils.isEmpty(AppIdManager.ttAppKey())) {
            CoreNetClient.commonParam("client_key", AppIdManager.dyAppKey());
        }
        CoreNetClient.commonParam("app_name", ConfigManager.AppConfig.appName());
        CoreNetClient.commonParam("app_name_display", DeviceUtils.getAppName());
        CoreNetClient.commonParam(com.umeng.message.common.a.u, DeviceUtils.getAppPackageName());
        CoreNetClient.commonParam("version_code", DeviceUtils.getVersionCode() + "");
        CoreNetClient.commonParam(AdDownloadModel.JsonKey.VERSION_NAME, DeviceUtils.getVersionName());
        CoreNetClient.commonParam("device_platform", "android");
        CoreNetClient.commonParam(o.ae, DeviceUtils.getBrand());
        CoreNetClient.commonParam(o.E, DeviceUtils.getBrand());
        CoreNetClient.commonParam(o.C, DeviceUtils.getModel());
        CoreNetClient.commonParam("os_api", DeviceUtils.getOsApi() + "");
        CoreNetClient.commonParam(o.x, DeviceUtils.getOsVersion());
        String imei = DeviceUtils.getImei();
        if (TextUtils.isEmpty(imei)) {
            return;
        }
        CoreNetClient.commonParam("uuid", imei);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void g() {
        try {
            LogCoreUtils.logInit("---------------LG AppLogOnResume--------------");
            Activity topActivity = ActivityUtils.getTopActivity();
            if (topActivity != null) {
                AppLog.onResume(topActivity);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
