package com.bytedance.msdk.adapter.sigmob;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.error.InitSdkError;
import com.sigmob.windad.WindAdOptions;
import com.sigmob.windad.WindAds;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SigmobAdapterConfiguration extends TTBaseAdapterConfiguration {
    private boolean a(Context context, String str, String str2, TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        Logger.i("TTMediationSDK_SDK_Init", "init Sigmob SDK start.....appId=" + str);
        try {
            if (context == null || str == null) {
                Logger.e("TTMediationSDK_SDK_Init", "init Sigmob SDK fail ! context or appId can't be null !!");
                return false;
            }
            boolean zStartWithOptions = WindAds.sharedAds().startWithOptions(context, new WindAdOptions(str, str2, false));
            Logger.i("TTMediationSDK_SDK_Init", "init Sigmob SDK finish ......");
            return zStartWithOptions;
        } catch (Throwable th) {
            Logger.e("TTMediationSDK_SDK_Init", "init Sigmob SDK fail .......error msg :" + th.toString());
            return false;
        }
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdNetworkName() {
        return "sigmob";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdapterVersion() {
        return "3.5.1.0";
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
        return WindAds.getVersion();
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void initializeNetwork(@NonNull Context context, @Nullable Map<String, Object> map, @NonNull TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        InitSdkError initSdkError;
        if (isInitedSuccess() || map == null || map.isEmpty()) {
            return;
        }
        boolean zA = a(context, (String) map.get("app_id"), (String) map.get("app_key"), tTOnNetworkInitializationFinishedListener);
        if (tTOnNetworkInitializationFinishedListener != null) {
            if (zA) {
                setInitedSuccess(true);
                initSdkError = new InitSdkError(AdError.ERROR_CODE_ADAPTER_INITIALIZATION_SUCCESS, "sigmob");
            } else {
                initSdkError = new InitSdkError(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "sigmob");
            }
            tTOnNetworkInitializationFinishedListener.onNetworkInitializationFinished(SigmobAdapterConfiguration.class, initSdkError);
        }
    }
}
