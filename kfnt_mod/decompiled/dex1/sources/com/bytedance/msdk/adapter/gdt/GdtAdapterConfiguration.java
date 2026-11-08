package com.bytedance.msdk.adapter.gdt;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTOnNetworkInitializationFinishedListener;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.AdError;
import com.bytedance.msdk.api.error.InitSdkError;
import com.bytedance.msdk.api.v2.GMAppDialogClickListener;
import com.qq.e.ads.dfa.GDTAppDialogClickListener;
import com.qq.e.comm.managers.GDTADManager;
import com.qq.e.comm.managers.GDTAdSdk;
import com.qq.e.comm.managers.status.SDKStatus;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GdtAdapterConfiguration extends TTBaseAdapterConfiguration {
    private boolean a(Context context, String str, TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        try {
            if (context == null || str == null) {
                Logger.e("TTMediationSDK_SDK_Init", "init GDT SDK fail ! context or appId can't be null !!");
                return false;
            }
            Logger.i("TTMediationSDK_SDK_Init", "init GDT SDK start......");
            boolean zInitWith = GDTADManager.getInstance().initWith(context, str);
            Logger.i("TTMediationSDK_SDK_Init", "init GDT SDK  finish......");
            return zInitWith;
        } catch (Throwable th) {
            Logger.e("TTMediationSDK_SDK_Init", "init GDT SDK fail error msg:" + th.toString());
            return false;
        }
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdNetworkName() {
        return "gdt";
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public String getAdapterVersion() {
        return "4.430.1300.0";
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
        return SDKStatus.getIntegrationSDKVersion();
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void initializeNetwork(@NonNull Context context, @Nullable Map<String, Object> map, @NonNull TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener) {
        Class<GdtAdapterConfiguration> cls;
        InitSdkError initSdkError;
        synchronized (GdtAdapterConfiguration.class) {
            if (!isInitedSuccess() && map != null && !map.isEmpty()) {
                boolean zA = a(context, (String) map.get("app_id"), tTOnNetworkInitializationFinishedListener);
                if (tTOnNetworkInitializationFinishedListener != null) {
                    if (zA) {
                        setInitedSuccess(true);
                        cls = GdtAdapterConfiguration.class;
                        initSdkError = new InitSdkError(AdError.ERROR_CODE_ADAPTER_INITIALIZATION_SUCCESS, "gdt");
                    } else {
                        cls = GdtAdapterConfiguration.class;
                        initSdkError = new InitSdkError(AdError.ERROR_CODE_ADAPTER_CONFIGURATION_ERROR, "gdt");
                    }
                    tTOnNetworkInitializationFinishedListener.onNetworkInitializationFinished(cls, initSdkError);
                }
            }
        }
    }

    @Override // com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration, com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public int showOpenOrInstallAppDialog(final GMAppDialogClickListener gMAppDialogClickListener) {
        try {
            return GDTAdSdk.getGDTAdManger().showOpenOrInstallAppDialog(new GDTAppDialogClickListener(this) { // from class: com.bytedance.msdk.adapter.gdt.GdtAdapterConfiguration.1
                public void onButtonClick(int i) {
                    GMAppDialogClickListener gMAppDialogClickListener2 = gMAppDialogClickListener;
                    if (gMAppDialogClickListener2 != null) {
                        gMAppDialogClickListener2.onButtonClick(i);
                    }
                }
            });
        } catch (Exception unused) {
            return 0;
        }
    }
}
