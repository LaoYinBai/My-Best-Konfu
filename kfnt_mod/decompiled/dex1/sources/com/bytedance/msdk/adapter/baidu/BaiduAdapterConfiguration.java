package com.bytedance.msdk.adapter.baidu;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.baidu.mobads.sdk.api.AdSettings;
import com.baidu.mobads.sdk.api.BDAdConfig;
import com.baidu.mobads.sdk.api.MobadsPermissionSettings;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.error.InitSdkError;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaiduAdapterConfiguration extends TTBaseAdapterConfiguration {

    /* renamed from: a, reason: collision with root package name */
    private GMPrivacyConfig f3924a;

    private void a() {
        GMPrivacyConfig gMPrivacyConfig = this.f3924a;
        boolean z = false;
        MobadsPermissionSettings.setPermissionReadDeviceID(gMPrivacyConfig == null || gMPrivacyConfig.isCanUsePhoneState());
        GMPrivacyConfig gMPrivacyConfig2 = this.f3924a;
        MobadsPermissionSettings.setPermissionLocation(gMPrivacyConfig2 == null || gMPrivacyConfig2.isCanUseLocation());
        GMPrivacyConfig gMPrivacyConfig3 = this.f3924a;
        MobadsPermissionSettings.setPermissionStorage(gMPrivacyConfig3 == null || gMPrivacyConfig3.isCanUseWriteExternal());
        GMPrivacyConfig gMPrivacyConfig4 = this.f3924a;
        MobadsPermissionSettings.setPermissionAppList(gMPrivacyConfig4 == null || gMPrivacyConfig4.appList());
        GMPrivacyConfig gMPrivacyConfig5 = this.f3924a;
        if (gMPrivacyConfig5 != null && gMPrivacyConfig5.isLimitPersonalAds()) {
            z = true;
        }
        MobadsPermissionSettings.setLimitPersonalAds(z);
    }

    private boolean a(Context context, String str, boolean z) {
        try {
            if (context == null || str == null) {
                Logger.e("TTMediationSDK_SDK_Init", "init BaiduSdk fail ! context and appId can't be null !!");
                return false;
            }
            Logger.i("TTMediationSDK_SDK_Init", "init Baidu SDK start......");
            new BDAdConfig.Builder().setAppsid(str).setHttps(z).build(context).init();
            a();
            Logger.i("TTMediationSDK_SDK_Init", "init Baidu SDK  finish......");
            return true;
        } catch (Throwable th) {
            Logger.e("TTMediationSDK_SDK_Init", "init Baidu SDK error......e:" + th.toString());
            return false;
        }
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdNetworkName() {
        return "baidu";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdapterVersion() {
        return "9.17.0";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getBiddingToken(Context context, Map<String, Object> map) {
        return null;
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getGroMoreSdkVersion() {
        return "3.1.0.1";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getNetworkSdkVersion() {
        return AdSettings.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void initializeNetwork(@NonNull Context context, @Nullable Map<String, Object> map, @NonNull TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        InitSdkError initSdkError;
        if (isInitedSuccess() || map == null || map.isEmpty()) {
            return;
        }
        String str = (String) map.get("app_id");
        Boolean bool = (Boolean) map.get(TTBaseAdapterConfiguration.BAIDU_HTTPS);
        boolean zA = a(context, str, bool != null ? bool.booleanValue() : false);
        if (tTOnNetworkInitializationFinishedListener != null) {
            if (zA) {
                setInitedSuccess(true);
                initSdkError = new InitSdkError(AdError.ERROR_CODE_ADAPTER_INITIALIZATION_SUCCESS, "baidu");
            } else {
                initSdkError = new InitSdkError(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "baidu");
            }
            tTOnNetworkInitializationFinishedListener.onNetworkInitializationFinished(BaiduAdapterConfiguration.class, initSdkError);
        }
    }

    @Override // com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration, com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void setPrivacyConfig(GMPrivacyConfig gMPrivacyConfig) {
        this.f3924a = gMPrivacyConfig;
        a();
    }
}
