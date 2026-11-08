package com.ss.union.game.sdk.core.base.config;

import android.text.TextUtils;
import android.util.Log;
import com.ss.union.game.sdk.common.net.tnc.TncConstant;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.XORUtils;
import com.ss.union.game.sdk.core.base.event.EventJSONHeaders;
import com.ss.union.game.sdk.core.base.utils.LogCoreUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AppIdManager {
    public static final String KEY_APP_ID_IN_SP = "appID";
    public static final String KEY_LG_APP_ID = "lg_app_id";
    public static final String SP_NAME_FOR_APP_ID = "game_applog_stats";
    private static volatile AppIdEntity appIdEntity;

    public static String sdkAid() {
        return "193123";
    }

    public static String sdkDemoAid() {
        return TncConstant.TNC_SDK_APP_ID;
    }

    public static synchronized void init() {
        try {
            boolean z = false;
            SPUtils sPUtils = SPUtils.getInstance(SP_NAME_FOR_APP_ID, 0);
            String string = sPUtils != null ? sPUtils.getString(KEY_APP_ID_IN_SP, "") : null;
            if (TextUtils.isEmpty(string)) {
                z = true;
                string = ResourceUtils.getString(KEY_LG_APP_ID);
            }
            if (TextUtils.isEmpty(string)) {
                LogCoreUtils.logInit("secretAppId is null，please check 【lg_app_id】in string.xml ");
            } else {
                appIdEntity = new AppIdEntity(string);
                if (z) {
                    sPUtils.put(KEY_APP_ID_IN_SP, string);
                }
            }
        } catch (Throwable th) {
            LogCoreUtils.logInit("AppIdManager init Error " + Log.getStackTraceString(th));
        }
    }

    public static synchronized void syncAppId(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        EventJSONHeaders.getInstance().updateOnlineAppId(str);
        if (appIdEntity == null || !str.equals(appIdEntity.mSecretAppID)) {
            try {
                appIdEntity = new AppIdEntity(str);
                SPUtils.getInstance(SP_NAME_FOR_APP_ID, 0).put(KEY_APP_ID_IN_SP, str);
            } catch (Throwable unused) {
            }
        }
    }

    public static synchronized String secretAppId() {
        if (appIdEntity == null) {
            return "";
        }
        return appIdEntity.mSecretAppID;
    }

    public static synchronized String lgAppID() {
        if (appIdEntity == null) {
            return "";
        }
        return appIdEntity.mLgAppID;
    }

    public static synchronized String apAppID() {
        if (appIdEntity == null) {
            return "";
        }
        return appIdEntity.mApAppID;
    }

    public static synchronized String adAppID() {
        if (appIdEntity == null) {
            return "";
        }
        return appIdEntity.mAdAppID;
    }

    public static synchronized String dyAppKey() {
        if (appIdEntity == null) {
            return "";
        }
        return appIdEntity.mDyAppKey;
    }

    public static synchronized String ttAppKey() {
        if (appIdEntity == null) {
            return "";
        }
        return appIdEntity.mTtAppKey;
    }

    public static synchronized String dyPlatformAppId() {
        if (appIdEntity == null) {
            return "";
        }
        return appIdEntity.mDyPlatformAppId;
    }

    public static synchronized String appLogScheme() {
        if (appIdEntity == null) {
            return "";
        }
        return appIdEntity.mAppLogScheme;
    }

    private static final class AppIdEntity {
        private static final String KEY_AD_APP_ID = "ad_appID";
        private static final String KEY_APP_LOG_SCHEME = "applog_schema_url";
        private static final String KEY_AP_APP_ID = "ap_appID";
        private static final String KEY_DY_APP_ID = "dy_appID";
        private static final String KEY_DY_PLATFORM_APP_ID = "douyin_platform_app_id";
        private static final String KEY_LG_APP_ID = "appID";
        private static final String KEY_TT_APP_ID = "tt_appID";
        public String mAdAppID;
        public String mApAppID;
        public String mAppLogScheme;
        public String mDyAppKey;
        public String mDyPlatformAppId;
        public String mLgAppID;
        private String mSecretAppID;
        public String mTtAppKey;

        public AppIdEntity(String str) throws Throwable {
            this.mSecretAppID = str;
            JSONObject jSONObject = new JSONObject(XORUtils.decrypt(str));
            this.mLgAppID = jSONObject.optString("appID");
            this.mApAppID = jSONObject.optString(KEY_AP_APP_ID);
            this.mAdAppID = jSONObject.optString(KEY_AD_APP_ID);
            this.mDyAppKey = jSONObject.optString(KEY_DY_APP_ID);
            this.mTtAppKey = jSONObject.optString(KEY_TT_APP_ID);
            this.mDyPlatformAppId = jSONObject.optString(KEY_DY_PLATFORM_APP_ID);
            this.mAppLogScheme = jSONObject.optString(KEY_APP_LOG_SCHEME);
        }
    }
}
