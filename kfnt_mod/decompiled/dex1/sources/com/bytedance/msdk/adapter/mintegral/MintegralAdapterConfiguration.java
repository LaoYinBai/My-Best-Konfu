package com.bytedance.msdk.adapter.mintegral;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.adapter.util.ThreadHelper;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.error.InitSdkError;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import com.mbridge.msdk.mbbid.out.BidManager;
import com.mbridge.msdk.out.MBridgeSDKFactory;
import com.mbridge.msdk.system.a;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MintegralAdapterConfiguration extends TTBaseAdapterConfiguration {

    /* renamed from: a, reason: collision with root package name */
    private GMPrivacyConfig f4068a;

    /* JADX INFO: Access modifiers changed from: private */
    public boolean a(Context context, String str, String str2) {
        try {
            Logger.e("TTMediationSDK_SDK_Init", "init Mintegral SDK start......appId:" + str + "   appKey:" + str2);
            a mBridgeSDK = MBridgeSDKFactory.getMBridgeSDK();
            mBridgeSDK.init(mBridgeSDK.getMBConfigurationMap(str, str2), context);
            mBridgeSDK.setDoNotTrackStatus(this.f4068a != null && this.f4068a.isLimitPersonalAds());
            Logger.e("TTMediationSDK_SDK_Init", "init Mintegral SDK finish......");
            return true;
        } catch (Throwable th) {
            Logger.e("TTMediationSDK_SDK_Init", "init Mintegral SDK error......e:" + th.toString());
            return false;
        }
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdNetworkName() {
        return "mintegral";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdapterVersion() {
        return "15.7.57.0";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getBiddingToken(Context context, Map<String, Object> map) {
        if (context == null) {
            return null;
        }
        return BidManager.getBuyerUid(context);
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getGroMoreSdkVersion() {
        return "3.1.0.1";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getNetworkSdkVersion() {
        return "MAL_15.7.57";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void initializeNetwork(@NonNull final Context context, @Nullable final Map<String, Object> map, @NonNull final TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        ThreadHelper.runOnUiThread(new Runnable() { // from class: com.bytedance.msdk.adapter.mintegral.MintegralAdapterConfiguration.1
            @Override // java.lang.Runnable
            public void run() {
                Map map2;
                if (MintegralAdapterConfiguration.this.isInitedSuccess() || (map2 = map) == null || map2.isEmpty()) {
                    return;
                }
                boolean zA = MintegralAdapterConfiguration.this.a(context, (String) map.get("app_id"), (String) map.get("app_key"));
                TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener2 = tTOnNetworkInitializationFinishedListener;
                if (tTOnNetworkInitializationFinishedListener2 != null) {
                    if (!zA) {
                        tTOnNetworkInitializationFinishedListener2.onNetworkInitializationFinished(MintegralAdapterConfiguration.class, new InitSdkError(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "mintegral"));
                    } else {
                        MintegralAdapterConfiguration.this.setInitedSuccess(true);
                        tTOnNetworkInitializationFinishedListener.onNetworkInitializationFinished(MintegralAdapterConfiguration.class, new InitSdkError(AdError.ERROR_CODE_ADAPTER_INITIALIZATION_SUCCESS, "mintegral"));
                    }
                }
            }
        });
    }

    @Override // com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration, com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void setPrivacyConfig(GMPrivacyConfig gMPrivacyConfig) {
        this.f4068a = gMPrivacyConfig;
        if (isInitedSuccess()) {
            a mBridgeSDK = MBridgeSDKFactory.getMBridgeSDK();
            GMPrivacyConfig gMPrivacyConfig2 = this.f4068a;
            mBridgeSDK.setDoNotTrackStatus(gMPrivacyConfig2 != null && gMPrivacyConfig2.isLimitPersonalAds());
        }
    }
}
