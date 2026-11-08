package com.ss.union.game.sdk.core.base.config;

import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.core.base.component.a;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class ConfigManager {
    public static synchronized void init() {
        AppConfig.parse();
        LoginConfig.parse();
        DanJuanConfig.parse();
        PushConfig.parse();
        PayConfig.parse();
    }

    public static final class AppConfig {
        private static int mAgeTipsType = -1;
        private static String mAppCompanyName = "";
        private static String mAppCompanyRegisterAddress = "";
        private static String mAppLogSchema = "";
        private static String mAppName = "";
        private static String mAppPrivacyUpdateTime = "";
        private static String mAppPrivacyValidTime = "";
        private static String mChannel = "";
        private static boolean mIsDebug = false;
        private static boolean mIsGamePlatformSdk = false;
        private static boolean mIsOhayooProduct = false;
        private static boolean mIsOpenAdFunction = true;
        private static boolean mIsOpenEnvCheck = true;
        private static boolean mIsOpenSplashEffect = false;
        private static String mLoggerPrefix = "LG_LOGGER";

        /* JADX INFO: Access modifiers changed from: private */
        public static void parse() {
            mChannel = ResourceUtils.getString("lg_app_channel", mChannel);
            mAppName = ResourceUtils.getString("lg_app_name", mAppName);
            mAppCompanyName = ResourceUtils.getString("lg_app_company_name", mAppCompanyName);
            mAppCompanyRegisterAddress = ResourceUtils.getString("lg_app_company_register_address", mAppCompanyRegisterAddress);
            mAppPrivacyUpdateTime = ResourceUtils.getString("lg_privacy_time_update", mAppPrivacyUpdateTime);
            mAppPrivacyValidTime = ResourceUtils.getString("lg_privacy_time_valid", mAppPrivacyValidTime);
            mIsOhayooProduct = ResourceUtils.getBoolean("lg_app_is_ohayoo_product", mIsOhayooProduct);
            mIsDebug = ResourceUtils.getBoolean("lg_debug_switch", mIsDebug);
            mAppLogSchema = ResourceUtils.getString("lg_applog_scheme", mAppLogSchema);
            mIsOpenSplashEffect = ResourceUtils.getBoolean("lg_splash_effect_switch", mIsOpenSplashEffect);
            mAgeTipsType = ResourceUtils.getInteger("lg_age_tips_type", mAgeTipsType);
            mIsOpenAdFunction = ResourceUtils.getBoolean("lg_ad_function_switch", mIsOpenAdFunction);
            mIsOpenEnvCheck = ResourceUtils.getBoolean("lg_env_check_switch", mIsOpenEnvCheck);
            mLoggerPrefix = ResourceUtils.getString("lg_logger_prefix", mLoggerPrefix);
            mIsGamePlatformSdk = ResourceUtils.getBoolean("lg_is_game_platform_sdk", mIsGamePlatformSdk);
        }

        public static boolean appIsOhayooProduct() {
            return mIsOhayooProduct;
        }

        public static String appName() {
            return mAppName;
        }

        public static String appChannel() {
            return mChannel;
        }

        public static String appCompanyName() {
            return mAppCompanyName;
        }

        public static String appCompanyRegisterAddress() {
            return mAppCompanyRegisterAddress;
        }

        public static String appPrivacyUpdateTime() {
            return mAppPrivacyUpdateTime;
        }

        public static String appPrivacyValidTime() {
            return mAppPrivacyValidTime;
        }

        public static boolean isDebug() {
            return mIsDebug;
        }

        public static String appLogSchema() {
            return mAppLogSchema;
        }

        public static boolean isOpenSplashEffect() {
            return mIsOpenSplashEffect;
        }

        public static int ageTipsType() {
            return mAgeTipsType;
        }

        public static boolean isOpenEnvCheck() {
            return mIsOpenEnvCheck;
        }

        public static boolean isOpenAdFunction() {
            return mIsOpenAdFunction;
        }

        public static String loggerPrefix() {
            return mLoggerPrefix;
        }

        public static boolean isGamePlatformSdk() {
            return mIsGamePlatformSdk;
        }
    }

    public static final class LoginConfig {
        private static boolean mAllowGuestLoginSwitch = true;
        private static boolean mCanShowGuestLoginButton = true;
        private static int mLoginMode = 2;
        private static boolean mNickNameSystemSwitch = true;
        private static String mOneKeyCMAppID = "";
        private static String mOneKeyCMAppKey = "";
        private static String mOneKeyCTAppID = "";
        private static String mOneKeyCTAppKey = "";
        private static String mOneKeyCUAppID = "";
        private static String mOneKeyCUAppKey = "";
        private static boolean mSilentLoginFailShowToastSwitch = true;

        private interface LoginMode {
            public static final int LOGIN_NORMAL = 2;
            public static final int LOGIN_SILENT = 1;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void parse() {
            if (a.b()) {
                mLoginMode = ResourceUtils.getInteger("lg_account_login_mode", 2);
                if (mLoginMode != 2 && mLoginMode != 1) {
                    mLoginMode = 2;
                }
            }
            mSilentLoginFailShowToastSwitch = ResourceUtils.getBoolean("lg_account_silent_login_fail_show_toast_switch", mSilentLoginFailShowToastSwitch);
            mNickNameSystemSwitch = ResourceUtils.getBoolean("lg_account_nick_name_system_switch", mNickNameSystemSwitch);
            mAllowGuestLoginSwitch = ResourceUtils.getBoolean("lg_account_guest_login_switch", mAllowGuestLoginSwitch);
            mCanShowGuestLoginButton = ResourceUtils.getBoolean("lg_account_can_show_guest_login_button", mCanShowGuestLoginButton);
            mOneKeyCMAppID = ResourceUtils.getString("lg_account_one_key_app_id_cm", mOneKeyCMAppID);
            mOneKeyCMAppKey = ResourceUtils.getString("lg_account_one_key_app_key_cm", mOneKeyCMAppKey);
            mOneKeyCUAppID = ResourceUtils.getString("lg_account_one_key_app_id_cu", mOneKeyCUAppID);
            mOneKeyCUAppKey = ResourceUtils.getString("lg_account_one_key_app_key_cu", mOneKeyCUAppKey);
            mOneKeyCTAppID = ResourceUtils.getString("lg_account_one_key_app_id_ct", mOneKeyCTAppID);
            mOneKeyCTAppKey = ResourceUtils.getString("lg_account_one_key_app_key_ct", mOneKeyCTAppKey);
        }

        public static boolean isNoUserLogin() {
            return !a.b();
        }

        public static boolean isSilentLogin() {
            return !isNoUserLogin() && mLoginMode == 1;
        }

        public static boolean isDialogLogin() {
            return !isNoUserLogin() && mLoginMode == 2;
        }

        public static int loginMode() {
            return mLoginMode;
        }

        public static boolean canShowToastWhenSilentLoginFail() {
            return mSilentLoginFailShowToastSwitch;
        }

        public static boolean isUseNickNameSystem() {
            return mNickNameSystemSwitch;
        }

        public static boolean isAllowGuestLoginSwitch() {
            return mAllowGuestLoginSwitch;
        }

        public static boolean canShowGuestLoginButton() {
            return mCanShowGuestLoginButton;
        }

        public static String oneKeyCMAppID() {
            return mOneKeyCMAppID;
        }

        public static String getOneKeyCMAppKey() {
            return mOneKeyCMAppKey;
        }

        public static String oneKeyCUAppID() {
            return mOneKeyCUAppID;
        }

        public static String getOneKeyCUAppKey() {
            return mOneKeyCUAppKey;
        }

        public static String oneKeyCTAppID() {
            return mOneKeyCTAppID;
        }

        public static String getOneKeyCTAppKey() {
            return mOneKeyCTAppKey;
        }
    }

    public static final class DanJuanConfig {
        private static boolean mDanJuanFloatBallSwitch;

        /* JADX INFO: Access modifiers changed from: private */
        public static void parse() {
            mDanJuanFloatBallSwitch = ResourceUtils.getBoolean("lg_dan_juan_float_ball_switch", mDanJuanFloatBallSwitch);
        }

        public static boolean isOpenFloatBall() {
            return mDanJuanFloatBallSwitch;
        }
    }

    public static final class PushConfig {
        private static String hw_app_id = "";
        private static String hw_app_key = "";
        private static boolean isOpenDebug = false;
        private static String mz_app_id = "";
        private static String mz_app_key = "";
        private static String oppo_app_key = "";
        private static String oppo_app_secret = "";
        private static String umeng_app_key = "";
        private static String umeng_message_secret = "";
        private static String vivo_app_id = "";
        private static String vivo_app_key = "";
        private static String xm_app_id = "";
        private static String xm_app_key = "";

        /* JADX INFO: Access modifiers changed from: private */
        public static void parse() {
            isOpenDebug = ResourceUtils.getBoolean("lg_push_debug_switch", false);
            hw_app_id = ResourceUtils.getString("huawei_appId", hw_app_id);
            hw_app_key = ResourceUtils.getString("huawei_app_key", hw_app_key);
            umeng_app_key = ResourceUtils.getString("umeng_app_key", umeng_app_key);
            umeng_message_secret = ResourceUtils.getString("umeng_message_secret", umeng_message_secret);
            xm_app_id = ResourceUtils.getString("xiaomi_push_app_id", xm_app_id);
            xm_app_key = ResourceUtils.getString("xiaomi_push_app_key", xm_app_key);
            mz_app_id = ResourceUtils.getString("meizu_push_app_id", mz_app_id);
            mz_app_key = ResourceUtils.getString("meizu_push_app_key", mz_app_key);
            oppo_app_key = ResourceUtils.getString("oppo_push_app_key", oppo_app_key);
            oppo_app_secret = ResourceUtils.getString("oppo_push_app_secret", oppo_app_secret);
            vivo_app_id = ResourceUtils.getString("vivo_app_id", vivo_app_id);
            vivo_app_key = ResourceUtils.getString("vivo_api_key", vivo_app_key);
        }

        public static boolean isDebug() {
            return isOpenDebug;
        }

        public static String getHw_app_id() {
            return hw_app_id;
        }

        public static String getHw_app_key() {
            return hw_app_key;
        }

        public static String getUmeng_app_key() {
            return umeng_app_key;
        }

        public static String getUmeng_message_secret() {
            return umeng_message_secret;
        }

        public static String getXm_app_id() {
            return xm_app_id;
        }

        public static String getXm_app_key() {
            return xm_app_key;
        }

        public static String getMz_app_id() {
            return mz_app_id;
        }

        public static String getMz_app_key() {
            return mz_app_key;
        }

        public static String getOppo_app_secret() {
            return oppo_app_secret;
        }

        public static String getOppo_app_key() {
            return oppo_app_key;
        }

        public static String getVivo_app_id() {
            return vivo_app_id;
        }

        public static String getVivo_app_key() {
            return vivo_app_key;
        }
    }

    public static final class PayConfig {
        private static String payKey = "";

        /* JADX INFO: Access modifiers changed from: private */
        public static void parse() {
            payKey = ResourceUtils.getString("lg_pay_key", payKey);
        }

        public static String getPayKey() {
            return payKey;
        }
    }
}
