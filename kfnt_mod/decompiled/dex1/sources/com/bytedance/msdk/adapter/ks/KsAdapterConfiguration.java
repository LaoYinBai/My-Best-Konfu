package com.bytedance.msdk.adapter.ks;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.error.InitSdkError;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import com.kwad.sdk.api.KsAdSDK;
import com.kwad.sdk.api.SdkConfig;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class KsAdapterConfiguration extends TTBaseAdapterConfiguration {

    /* renamed from: a, reason: collision with root package name */
    private GMPrivacyConfig f4026a;

    private boolean a(Context context, String str, String str2, TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        Logger.i("TTMediationSDK_SDK_Init", "init Ks SDK start ..........appId=" + str + "   appName=" + str2);
        try {
            if (context == null || str == null) {
                Logger.e("TTMediationSDK_SDK_Init", "init ks SDK fail ! context or appId can't be null !!");
                return false;
            }
            boolean zInit = KsAdSDK.init(context, new SdkConfig.Builder().appId(str).appName(str2).build());
            KsAdSDK.setPersonalRecommend(this.f4026a == null || !this.f4026a.isLimitPersonalAds());
            Logger.i("TTMediationSDK_SDK_Init", "init Ks SDK finish .....hasSuccess:" + zInit);
            return zInit;
        } catch (Throwable th) {
            Logger.e("TTMediationSDK_SDK_Init", "init Ks SDK fail .....error msg:" + th.toString());
            return false;
        }
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdNetworkName() {
        return "ks";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdapterVersion() {
        return "3.3.17.1";
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
        return KsAdSDK.getSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void initializeNetwork(@NonNull Context context, @Nullable Map<String, Object> map, @NonNull TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        InitSdkError initSdkError;
        if (isInitedSuccess() || map == null || map.isEmpty()) {
            return;
        }
        boolean zA = a(context, (String) map.get("app_id"), (String) map.get(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY), tTOnNetworkInitializationFinishedListener);
        if (tTOnNetworkInitializationFinishedListener != null) {
            if (zA) {
                setInitedSuccess(true);
                initSdkError = new InitSdkError(AdError.ERROR_CODE_ADAPTER_INITIALIZATION_SUCCESS, "ks");
            } else {
                initSdkError = new InitSdkError(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "ks");
            }
            tTOnNetworkInitializationFinishedListener.onNetworkInitializationFinished(KsAdapterConfiguration.class, initSdkError);
        }
    }

    @Override // com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration, com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void setPrivacyConfig(GMPrivacyConfig gMPrivacyConfig) {
        this.f4026a = gMPrivacyConfig;
        if (isInitedSuccess()) {
            GMPrivacyConfig gMPrivacyConfig2 = this.f4026a;
            KsAdSDK.setPersonalRecommend(gMPrivacyConfig2 == null || !gMPrivacyConfig2.isLimitPersonalAds());
        }
    }
}
