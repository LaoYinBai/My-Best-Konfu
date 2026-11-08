package com.bytedance.msdk.api.v2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import androidx.annotation.NonNull;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.a;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_19do.b;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_for12.g;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.c;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.d;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.a0;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.j;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.r;
import bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.u;
import com.bytedance.msdk.adapter.config.DefaultAdapterClasses;
import com.bytedance.msdk.adapter.config.ITTAdapterConfiguration;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.activity.TTDelegateActivity;
import com.bytedance.msdk.api.v2.ad.custom.init.GMCustomAdapterConfiguration;
import java.util.HashMap;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public final class GMMediationAdSdk {
    private static void a(GMAdConfig gMAdConfig) {
        b.D().a(System.currentTimeMillis());
        b.D().a(gMAdConfig.getAppId());
        b.D().b(gMAdConfig.getAppName());
        b.D().c(gMAdConfig.getGMPangleOption().isAllowShowNotify());
        b.D().d(gMAdConfig.getGMPangleOption().isAllowShowPageWhenScreenLock());
        b.D().b(gMAdConfig.getGMPangleOption().getTitleBarTheme());
        b.D().a(gMAdConfig.getGMPangleOption().getDirectDownloadNetworkType());
        b.D().a(gMAdConfig.getGMPangleOption().getNeedClearTaskReset());
        b.D().f(gMAdConfig.getGMPangleOption().isIsUseTextureView());
        b.D().e(gMAdConfig.getGMPangleOption().isPaid());
        b.D().e(gMAdConfig.getPublisherDid());
        b.D().b(gMAdConfig.isOpenAdnTest());
        b.D().c(gMAdConfig.getGMPangleOption().getData());
        b.D().a(gMAdConfig.getGMPangleOption().getExtraData());
        b.D().a(gMAdConfig.getPrivacyConfig());
        updatePrivacyConfigToADNs();
        b.D().a(gMAdConfig.getGMConfigUserInfoForSegment(), true);
        b.D().a(gMAdConfig.getGMPangleOption().getPluginUpdateConfig());
        b.D().d(gMAdConfig.getGMPangleOption().getKeywords());
        b.D().b(gMAdConfig.getLocalExtra());
        b.D().a(gMAdConfig.isHttps());
    }

    public static boolean configLoadSuccess() {
        return a.f().u();
    }

    public static String getAppId() {
        return b.D().c();
    }

    public static String getAppName() {
        return b.D().h();
    }

    public static GMCustomAdapterConfiguration getCustomAdapterConfiguration(String str) {
        return c.a(str);
    }

    public static String getPangleData() {
        return b.D().j();
    }

    public static Map<String, String> getPangleExtraData() {
        return b.D().d();
    }

    public static boolean getPanglePaid() {
        return b.D().A();
    }

    public static String getSdkVersion() {
        return "3.1.0.1";
    }

    public static String getValueFromPPeInfo(String str) {
        return a.d() == null ? "" : u.a("tt_mediation_ppe_info", a.d()).a(str, "");
    }

    public static String getZbh(Context context) {
        return r.a();
    }

    public static void initUnityForBanner(Activity activity) {
        c.a(activity);
    }

    public static void initialize(@NonNull Context context, @NonNull GMAdConfig gMAdConfig) {
        if (gMAdConfig == null) {
            Log.d("TTMediationSDK_SDK_Init", "TTMediationAdSdk初始化失败，TTAdConfig不能是null");
            return;
        }
        if (gMAdConfig.isDebug()) {
            Logger.openDebugMode();
            bykvm_19do.bykvm_19do.bykvm_for12.bykvm_19do.bykvm_19do.bykvm_for12.a.c();
        }
        if (context == null) {
            Logger.d("TTMediationSDK_SDK_Init", "TTMediationAdSdk初始化失败，context不能是null");
            return;
        }
        d.a(context.getApplicationContext());
        Logger.e("TTMediationSDK_SDK_Init", "msdk_init.............");
        a(gMAdConfig);
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
        j.a(context);
    }

    public static boolean isAdapterVersionFit(String str, String str2) {
        return bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.a.b(str, str2);
    }

    public static boolean isAdnVersionFit(String str, String str2) {
        return bykvm_19do.bykvm_19do.bykvm_if122.bykvm_int108.a.c(str, str2);
    }

    public static boolean isCustom(String str) {
        return a.f().f(str);
    }

    public static boolean isTestDemo() {
        return b.D().C();
    }

    public static boolean isUITest() {
        return a.f().E();
    }

    public static void registerConfigCallback(GMSettingConfigCallback gMSettingConfigCallback) {
        a.f().a(gMSettingConfigCallback);
    }

    public static void requestPermissionIfNecessary(Context context) {
        Intent intent = new Intent(context, (Class<?>) TTDelegateActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("type", 3);
        if (context != null) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.c.a(context, intent, null);
        }
    }

    public static void setPangleData(String str) {
        b.D().c(str);
    }

    public static void setPulisherDid(String str) {
        Logger.d("TTMediationSDK", "app运行中setPulisherDid: " + str);
        b.D().e(str);
    }

    public static void setThemeStatus(int i) {
        HashMap map = new HashMap();
        map.put(TTBaseAdapterConfiguration.TT_MSDK_THEME_STATUS, Integer.valueOf(i));
        Map<String, ITTAdapterConfiguration> mapE = c.e();
        if (mapE == null || mapE.size() == 0) {
            return;
        }
        for (ITTAdapterConfiguration iTTAdapterConfiguration : mapE.values()) {
            if (iTTAdapterConfiguration != null) {
                iTTAdapterConfiguration.setThemeStatus(map);
            }
        }
    }

    public static void setUserInfoForSegment(GMConfigUserInfoForSegment gMConfigUserInfoForSegment) {
        b.D().a(gMConfigUserInfoForSegment, false);
    }

    public static int showOpenOrInstallAppDialog(GMAppDialogClickListener gMAppDialogClickListener) {
        ITTAdapterConfiguration iTTAdapterConfiguration;
        Map<String, ITTAdapterConfiguration> mapE = c.e();
        if (mapE == null || mapE.size() == 0 || (iTTAdapterConfiguration = mapE.get(DefaultAdapterClasses.getClassNameByAdnName("gdt"))) == null) {
            return 0;
        }
        return iTTAdapterConfiguration.showOpenOrInstallAppDialog(gMAppDialogClickListener);
    }

    public static void unregisterConfigCallback(GMSettingConfigCallback gMSettingConfigCallback) {
        a.f().b(gMSettingConfigCallback);
    }

    public static void updatePangleConfig(GMAdConfig gMAdConfig) {
        if (gMAdConfig == null) {
            return;
        }
        if (!TextUtils.isEmpty(gMAdConfig.getGMPangleOption().getData())) {
            b.D().c(gMAdConfig.getGMPangleOption().getData());
        }
        if (!TextUtils.isEmpty(gMAdConfig.getGMPangleOption().getKeywords())) {
            b.D().d(gMAdConfig.getGMPangleOption().getKeywords());
        }
        b.D().e(gMAdConfig.getGMPangleOption().getExtraData());
    }

    public static void updatePanglePaid(boolean z) {
        b.D().e(z);
    }

    public static void updatePrivacyConfig(GMPrivacyConfig gMPrivacyConfig) {
        b.D().a(gMPrivacyConfig);
        updatePrivacyConfigToADNs();
    }

    public static void updatePrivacyConfigToADNs() {
        ITTAdapterConfiguration value;
        try {
            Map<String, ITTAdapterConfiguration> mapE = c.e();
            if (mapE == null || mapE.size() <= 0) {
                return;
            }
            for (Map.Entry<String, ITTAdapterConfiguration> entry : mapE.entrySet()) {
                if (entry != null && (value = entry.getValue()) != null) {
                    value.setPrivacyConfig(b.D().q());
                }
            }
        } catch (Throwable unused) {
        }
    }

    public static void requestPermissionIfNecessary(Context context, int[] iArr) {
        Intent intent = new Intent(context, (Class<?>) TTDelegateActivity.class);
        intent.addFlags(268435456);
        intent.putExtra("type", 1);
        intent.putExtra(TTDelegateActivity.INTENT_PERMISSIONS, iArr);
        if (context != null) {
            bykvm_19do.bykvm_19do.bykvm_if122.bykvm_new1.c.a(context, intent, null);
        }
    }
}
