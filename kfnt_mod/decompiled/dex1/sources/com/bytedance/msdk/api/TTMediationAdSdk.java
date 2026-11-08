package com.bytedance.msdk.api;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.a0;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.v2.GMAppDialogClickListener;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import com.bytedance.msdk.api.v2.GMSettingConfigCallback;
import java.util.Map;

@Deprecated
/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class TTMediationAdSdk {
    @Deprecated
    public static boolean configLoadSuccess() {
        return GMMediationAdSdk.configLoadSuccess();
    }

    public static String getAppId() {
        return GMMediationAdSdk.getAppId();
    }

    public static String getAppName() {
        return GMMediationAdSdk.getAppName();
    }

    public static String getPangleData() {
        return GMMediationAdSdk.getPangleData();
    }

    public static Map<String, String> getPangleExtraData() {
        return GMMediationAdSdk.getPangleExtraData();
    }

    public static boolean getPanglePaid() {
        return GMMediationAdSdk.getPanglePaid();
    }

    public static String getSdkVersion() {
        return "3.1.0.1";
    }

    public static String getValueFromPPeInfo(String str) {
        return GMMediationAdSdk.getValueFromPPeInfo(str);
    }

    public static String getZbh(Context context) {
        return GMMediationAdSdk.getZbh(context);
    }

    public static void initUnityForBanner(Activity activity) {
        GMMediationAdSdk.initUnityForBanner(activity);
    }

    @Deprecated
    public static void initialize(Context context, TTAdConfig tTAdConfig) {
        if (tTAdConfig == null) {
            Log.d("TTMediationSDK_SDK_Init", "TTMediationAdSdk初始化失败，TTAdConfig不能是null");
            return;
        }
        if (tTAdConfig.isDebug()) {
            Logger.openDebugMode();
            a.c();
        }
        if (context == null) {
            Logger.d("TTMediationSDK_SDK_Init", "TTMediationAdSdk初始化失败，context不能是null");
            return;
        }
        d.a(context.getApplicationContext());
        Logger.e("TTMediationSDK_SDK_Init", "msdk_init.............");
        b.D().a(System.currentTimeMillis());
        b.D().a(tTAdConfig.getAppId());
        b.D().b(tTAdConfig.getAppName());
        b.D().c(tTAdConfig.isPangleAllowShowNotify());
        b.D().d(tTAdConfig.isPangleAllowShowPageWhenScreenLock());
        b.D().b(tTAdConfig.getPangleTitleBarTheme());
        b.D().a(tTAdConfig.getPangleDirectDownloadNetworkType());
        b.D().a(tTAdConfig.getPangleNeedClearTaskReset());
        b.D().f(tTAdConfig.isPangleUseTextureView());
        b.D().e(tTAdConfig.isPanglePaid());
        b.D().e(tTAdConfig.getPublisherDid());
        b.D().b(tTAdConfig.isOpenAdnTest());
        b.D().c(tTAdConfig.getPangleData());
        b.D().a(tTAdConfig.getExtraData());
        b.D().a(tTAdConfig.getPrivacyConfig());
        GMMediationAdSdk.updatePrivacyConfigToADNs();
        b.D().a(tTAdConfig.getAdapterConfigurationClasses());
        b.D().c(tTAdConfig.getMediatedNetworkConfigurations());
        b.D().d(tTAdConfig.getRequestOptions());
        b.D().a(tTAdConfig.getUserInfoForSegment(), true);
        b.D().a(tTAdConfig.getPanglePluginUpdateConfig());
        b.D().d(tTAdConfig.getPangleKeywords());
        c.a(context.getApplicationContext());
        if (d.d()) {
            Logger.d("TMe", "-----==---- 正常上报sdk_init");
            g.b(-1L);
        } else {
            Logger.d("TMe", "-----==---- 延时上报sdk_init");
            d.e();
        }
        if (a0.b(context)) {
            g.a();
        }
    }

    public static boolean isAdapterVersionFit(String str, String str2) {
        return GMMediationAdSdk.isAdapterVersionFit(str, str2);
    }

    public static boolean isAdnVersionFit(String str, String str2) {
        return GMMediationAdSdk.isAdnVersionFit(str, str2);
    }

    public static boolean isTestDemo() {
        return GMMediationAdSdk.isTestDemo();
    }

    public static boolean isUITest() {
        return GMMediationAdSdk.isUITest();
    }

    @Deprecated
    public static void registerConfigCallback(GMSettingConfigCallback gMSettingConfigCallback) {
        GMMediationAdSdk.registerConfigCallback(gMSettingConfigCallback);
    }

    public static void requestPermissionIfNecessary(Context context) {
        GMMediationAdSdk.requestPermissionIfNecessary(context);
    }

    public static void setPangleData(String str) {
        GMMediationAdSdk.setPangleData(str);
    }

    public static void setPulisherDid(String str) {
        Logger.d("TTMediationSDK", "app运行中setPulisherDid: " + str);
        GMMediationAdSdk.setPulisherDid(str);
    }

    public static void setThemeStatus(int i) {
        GMMediationAdSdk.setThemeStatus(i);
    }

    @Deprecated
    public static void setUserInfoForSegment(UserInfoForSegment userInfoForSegment) {
        b.D().a(userInfoForSegment, false);
    }

    public static int showOpenOrInstallAppDialog(GMAppDialogClickListener gMAppDialogClickListener) {
        return GMMediationAdSdk.showOpenOrInstallAppDialog(gMAppDialogClickListener);
    }

    @Deprecated
    public static void unregisterConfigCallback(GMSettingConfigCallback gMSettingConfigCallback) {
        GMMediationAdSdk.unregisterConfigCallback(gMSettingConfigCallback);
    }

    @Deprecated
    public static void updatePangleConfig(TTAdConfig tTAdConfig) {
        if (tTAdConfig == null) {
            return;
        }
        if (!TextUtils.isEmpty(tTAdConfig.getPangleData())) {
            b.D().c(tTAdConfig.getPangleData());
        }
        if (!TextUtils.isEmpty(tTAdConfig.getPangleKeywords())) {
            b.D().d(tTAdConfig.getPangleKeywords());
        }
        b.D().e(tTAdConfig.getExtraData());
    }

    public static void updatePanglePaid(boolean z) {
        GMMediationAdSdk.updatePanglePaid(z);
    }

    public static void updatePrivacyConfig(GMPrivacyConfig gMPrivacyConfig) {
        GMMediationAdSdk.updatePrivacyConfig(gMPrivacyConfig);
    }

    public static void requestPermissionIfNecessary(Context context, int[] iArr) {
        GMMediationAdSdk.requestPermissionIfNecessary(context, iArr);
    }
}
