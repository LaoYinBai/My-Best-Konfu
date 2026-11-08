package com.bytedance.sdk.account.impl;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.bytedance.sdk.account.platform.api.IAccountSettingsService;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BDAccountSettingsManager implements IAccountSettingsService {
    private static final String KEY_ACCOUNT_CONFIG = "sdk_key_accountSDK";
    private static final String KEY_ACCOUNT_ONEKEY_LOGIN_CONFIG = "onekey_login_config";
    private static final String KEY_ACCOUNT_SETTINGS = "account_sdk_settings";
    private static final String KEY_ACCOUNT_SETTINGS_SP_NAME = "account_sdk_settings_sp";
    private static final String KEY_ACCOUNT_THIRD_PARTY_CONFIG = "third_party_config";
    private static final String KEY_SETTING_APP = "app";
    private static final String KEY_SETTING_DATA = "data";
    private static final String KEY_SETTING_SETTINGS = "settings";
    private static volatile IAccountSettingsService sInstance;
    private final Context mContext;
    private SharedPreferences mSettingsSp;

    private BDAccountSettingsManager(Context context) {
        this.mContext = context.getApplicationContext();
    }

    static IAccountSettingsService instance(Context context) {
        if (sInstance == null) {
            synchronized (BDAccountSettingsManager.class) {
                if (sInstance == null) {
                    sInstance = new BDAccountSettingsManager(context);
                }
            }
        }
        return sInstance;
    }

    @Override // com.bytedance.sdk.account.platform.api.IAccountSettingsService
    public void updateSettings(JSONObject jSONObject) {
        JSONObject jSONObjectOptJSONObject;
        if (jSONObject != null) {
            try {
                if (jSONObject.has("data")) {
                    jSONObject = jSONObject.optJSONObject("data");
                }
                if (jSONObject != null) {
                    if (jSONObject.has(KEY_SETTING_APP)) {
                        jSONObject = jSONObject.optJSONObject(KEY_SETTING_APP);
                    } else if (jSONObject.has(KEY_SETTING_SETTINGS)) {
                        jSONObject = jSONObject.optJSONObject(KEY_SETTING_SETTINGS);
                    }
                }
                if (jSONObject == null || !jSONObject.has(KEY_ACCOUNT_CONFIG) || (jSONObjectOptJSONObject = jSONObject.optJSONObject(KEY_ACCOUNT_CONFIG)) == null) {
                    return;
                }
                String string = jSONObjectOptJSONObject.toString();
                if (TextUtils.isEmpty(string)) {
                    return;
                }
                saveAccountSettings(string);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private SharedPreferences getSharedPreferences(Context context) {
        if (this.mSettingsSp == null && context != null) {
            this.mSettingsSp = context.getSharedPreferences(KEY_ACCOUNT_SETTINGS_SP_NAME, 0);
        }
        return this.mSettingsSp;
    }

    private SharedPreferences.Editor getSharedPreferencesEditor(Context context) {
        SharedPreferences sharedPreferences = getSharedPreferences(context);
        if (sharedPreferences == null) {
            return null;
        }
        return sharedPreferences.edit();
    }

    private void saveAccountSettings(String str) {
        SharedPreferences.Editor sharedPreferencesEditor = getSharedPreferencesEditor(this.mContext);
        if (sharedPreferencesEditor == null) {
            return;
        }
        sharedPreferencesEditor.putString(KEY_ACCOUNT_SETTINGS, str);
        sharedPreferencesEditor.apply();
    }

    @Override // com.bytedance.sdk.account.platform.api.IAccountSettingsService
    public String getAccountSettingsConfig() {
        SharedPreferences sharedPreferences = getSharedPreferences(this.mContext);
        return sharedPreferences != null ? sharedPreferences.getString(KEY_ACCOUNT_SETTINGS, "") : "";
    }

    @Override // com.bytedance.sdk.account.platform.api.IAccountSettingsService
    public JSONObject getOnekeyLoginConfig() {
        try {
            String accountSettingsConfig = getAccountSettingsConfig();
            if (TextUtils.isEmpty(accountSettingsConfig)) {
                return null;
            }
            return new JSONObject(accountSettingsConfig).optJSONObject(KEY_ACCOUNT_ONEKEY_LOGIN_CONFIG);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.bytedance.sdk.account.platform.api.IAccountSettingsService
    public JSONObject getThirdPartyConfig() {
        try {
            String accountSettingsConfig = getAccountSettingsConfig();
            if (TextUtils.isEmpty(accountSettingsConfig)) {
                return null;
            }
            return new JSONObject(accountSettingsConfig).optJSONObject(KEY_ACCOUNT_THIRD_PARTY_CONFIG);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
