package com.bytedance.msdk.adapter.config;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.bytedance.msdk.api.v2.GMAppDialogClickListener;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public interface ITTAdapterConfiguration {
    void checkVersion();

    String getAdNetworkName();

    String getAdapterVersion();

    String getBiddingToken(Context context, Map<String, Object> map);

    String getGroMoreSdkVersion();

    Map<String, String> getMsdkRequestOptions();

    String getNetworkSdkVersion();

    void initializeNetwork(@NonNull Context context, @Nullable Map<String, Object> map, @NonNull TTOnNetworkInitializationFinishedListener tTOnNetworkInitializationFinishedListener);

    void setMsdkRequestOptions(Map<String, String> map);

    void setPrivacyConfig(GMPrivacyConfig gMPrivacyConfig);

    void setThemeStatus(Map<String, Object> map);

    int showOpenOrInstallAppDialog(GMAppDialogClickListener gMAppDialogClickListener);
}
