package com.bytedance.sdk.account.platform.onekey;

import android.text.TextUtils;
import com.bytedance.sdk.account.platform.api.IAccountSettingsService;
import com.bytedance.sdk.account.platform.base.AuthorizeFramework;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class OnekeySettingManager {
    public static final long DEFAULT_OVER_TIME_SEC = 3;
    private static final String KEY_CM_CONFIG = "cm_config";
    private static final String KEY_CT_CONFIG = "ct_config";
    private static final String KEY_CU_CONFIG = "cu_config";
    private static final String KEY_DATA_MOBILE_ENABLE = "need_data_mobile";
    private static final String KEY_ENABLE = "is_enable";
    private static final String KEY_PHONE_PERMISSION_ENABLE = "need_read_phone_permission";
    private static final String KEY_TIMEOUT = "timeout_sec";
    private static volatile OnekeySettingManager sInstance;
    private IAccountSettingsService mSettingsService = (IAccountSettingsService) AuthorizeFramework.getService(IAccountSettingsService.class);

    private OnekeySettingManager() {
    }

    static OnekeySettingManager instance() {
        if (sInstance == null) {
            synchronized (OnekeySettingManager.class) {
                if (sInstance == null) {
                    sInstance = new OnekeySettingManager();
                }
            }
        }
        return sInstance;
    }

    public boolean isCMEnable() {
        try {
            JSONObject mobileConfig = getMobileConfig("1");
            if (mobileConfig != null) {
                return mobileConfig.optInt(KEY_ENABLE, 1) == 1;
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public boolean needCMDataMobile() {
        try {
            JSONObject mobileConfig = getMobileConfig("1");
            if (mobileConfig != null) {
                return mobileConfig.optInt(KEY_DATA_MOBILE_ENABLE, 0) == 1;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public boolean needCMPhonePermission() {
        try {
            JSONObject mobileConfig = getMobileConfig("1");
            if (mobileConfig != null) {
                return mobileConfig.optInt(KEY_PHONE_PERMISSION_ENABLE, 0) == 1;
            }
        } catch (Exception unused) {
        }
        return false;
    }

    public long getCMTimeout() {
        try {
            JSONObject mobileConfig = getMobileConfig("1");
            return mobileConfig != null ? mobileConfig.optLong(KEY_TIMEOUT, 3L) * 1000 : OnekeyLoginConfig.OVER_TIME;
        } catch (Exception unused) {
            return OnekeyLoginConfig.OVER_TIME;
        }
    }

    public boolean isCTEnable() {
        try {
            JSONObject mobileConfig = getMobileConfig(OnekeyLoginConstants.TELECOM_TYPE);
            if (mobileConfig != null) {
                return mobileConfig.optInt(KEY_ENABLE, 1) == 1;
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public boolean needCTDataMobile() {
        try {
            JSONObject mobileConfig = getMobileConfig(OnekeyLoginConstants.TELECOM_TYPE);
            if (mobileConfig != null) {
                return mobileConfig.optInt(KEY_DATA_MOBILE_ENABLE, 1) == 1;
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public long getCTTimeout() {
        try {
            JSONObject mobileConfig = getMobileConfig(OnekeyLoginConstants.TELECOM_TYPE);
            return mobileConfig != null ? mobileConfig.optLong(KEY_TIMEOUT, 3L) * 1000 : OnekeyLoginConfig.OVER_TIME;
        } catch (Exception unused) {
            return OnekeyLoginConfig.OVER_TIME;
        }
    }

    public boolean isCUEnable() {
        try {
            JSONObject mobileConfig = getMobileConfig(OnekeyLoginConstants.UNICOM_TYPE);
            if (mobileConfig != null) {
                return mobileConfig.optInt(KEY_ENABLE, 1) == 1;
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public boolean needCUDataMobile() {
        try {
            JSONObject mobileConfig = getMobileConfig(OnekeyLoginConstants.UNICOM_TYPE);
            if (mobileConfig != null) {
                return mobileConfig.optInt(KEY_DATA_MOBILE_ENABLE, 1) == 1;
            }
        } catch (Exception unused) {
        }
        return true;
    }

    public long getCUTimeout() {
        try {
            JSONObject mobileConfig = getMobileConfig(OnekeyLoginConstants.UNICOM_TYPE);
            return mobileConfig != null ? mobileConfig.optLong(KEY_TIMEOUT, 3L) * 1000 : OnekeyLoginConfig.OVER_TIME;
        } catch (Exception unused) {
            return OnekeyLoginConfig.OVER_TIME;
        }
    }

    public JSONObject getMobileConfig(String str) {
        String str2;
        JSONObject onekeyLoginConfig;
        try {
            str2 = "";
            switch (str) {
                case "1":
                    str2 = KEY_CM_CONFIG;
                    break;
                case "2":
                    str2 = KEY_CU_CONFIG;
                    break;
                case "3":
                    str2 = KEY_CT_CONFIG;
                    break;
            }
            if (this.mSettingsService == null || TextUtils.isEmpty(str2) || (onekeyLoginConfig = this.mSettingsService.getOnekeyLoginConfig()) == null || !onekeyLoginConfig.has(str2)) {
                return null;
            }
            return onekeyLoginConfig.optJSONObject(str2);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
