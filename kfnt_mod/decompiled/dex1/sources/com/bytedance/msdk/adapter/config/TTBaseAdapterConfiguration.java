package com.bytedance.msdk.adapter.config;

import android.text.TextUtils;
import anet.channel.strategy.dispatch.DispatchConstants;
import com.bytedance.msdk.adapter.util.Logger;
import com.bytedance.msdk.api.v2.GMAppDialogClickListener;
import com.bytedance.msdk.api.v2.GMPrivacyConfig;
import java.util.Map;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public abstract class TTBaseAdapterConfiguration implements ITTAdapterConfiguration {
    public static final String APP_ID_EXTRA_KEY = "app_id";
    public static final String APP_KEY_EXTRA_KEY = "app_key";
    public static final String APP_NAME_EXTRA_KEY = "app_name";
    public static final String BAIDU_HTTPS = "baidu_https";
    public static final String PANGLE_APP_NAME = "pangle_app_name";
    public static final String PANGLE_DATA = "pangle_data";
    public static final String PANGLE_DIRECT_DOWNLOAD_NETWORKTYPE = "pangle_direct_download_networktype";
    public static final String PANGLE_IS_PANGLE_ALLOW_SHOW_NOTIFY = "pangle_is_pangle_allow_show_notify";
    public static final String PANGLE_IS_PANGLE_ALLOW_SHOW_PAGE_WHEN_SCREEN_LOCK = "pangle_is_pangle_allow_show_page_when_screen_lock";
    public static final String PANGLE_IS_PANGLE_PAID = "pangle_is_pangle_paid";
    public static final String PANGLE_IS_PANGLE_USE_TEXTURE_VIEW = "pangle_is_pangle_use_texture_view";
    public static final String PANGLE_KEYWORDS = "pangle_keywords";
    public static final String PANGLE_NEED_CLEAR_TASK_RESET = "pangle_need_clear_task_reset";
    public static final String PANGLE_PLUGIN_UPDATE_CONFIG = "pangle_plugin_update_config";
    public static final String PANGLE_TITLE_BAR_THEME = "pangle_title_bar_theme";
    public static String TT_MSDK_ADSLOT_INFO = "tt_msdk_adslot_info";
    public static String TT_MSDK_CLIENT_REQ_ID = "tt_msdk_client_req_id";
    public static String TT_MSDK_THEME_STATUS = "tt_msdk_theme_status";
    public static String TT_MSDK_WATER_FALL_AB_TEST = "tt_msdk_water_fall_ab_test";
    public static String TT_MSDK_WATER_ORIGIN_TYPE = "tt_msdk_water_orgin_type";
    public static String TT_MSDK_WATER_SUB_AD_TYPE = "tt_msdk_water_sub_ad_type";

    /* renamed from: a, reason: collision with root package name */
    private volatile boolean f3967a = false;

    private static int a(String str, String str2) {
        if (TextUtils.isEmpty(str) && TextUtils.isEmpty(str2)) {
            return 0;
        }
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (TextUtils.isEmpty(str2)) {
            return 1;
        }
        if (str.startsWith(DispatchConstants.VERSION) || str.startsWith("V")) {
            str = str.substring(1);
        }
        if (str2.startsWith(DispatchConstants.VERSION) || str2.startsWith("V")) {
            str2 = str2.substring(1);
        }
        String[] strArrSplit = str.split("\\.");
        String[] strArrSplit2 = str2.split("\\.");
        int iMin = Math.min(strArrSplit.length, strArrSplit2.length);
        for (int i = 0; i < iMin; i++) {
            if (strArrSplit[i].length() != strArrSplit2[i].length()) {
                return strArrSplit[i].length() > strArrSplit2[i].length() ? 1 : -1;
            }
            int iCompareTo = strArrSplit[i].compareTo(strArrSplit2[i]);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (strArrSplit.length == strArrSplit2.length) {
            return 0;
        }
        return strArrSplit.length > strArrSplit2.length ? 1 : -1;
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void checkVersion() {
        try {
            String groMoreSdkVersion = getGroMoreSdkVersion();
            if (a(groMoreSdkVersion, "3.1.0.1") != 0) {
                Logger.e("TTMediationSDK_InitChecker", getAdNetworkName() + "版本号：" + getNetworkSdkVersion() + " , " + getAdNetworkName() + "Adapter版本号：" + getAdapterVersion() + " , GroMore版本不符合，要求等于" + groMoreSdkVersion + "，当前是3.1.0.1");
            } else {
                Logger.d("TTMediationSDK_InitChecker", getAdNetworkName() + "版本号：" + getNetworkSdkVersion() + " , " + getAdNetworkName() + "Adapter版本号：" + getAdapterVersion() + " , GroMore版本正常 ，要求等于" + groMoreSdkVersion + "，当前是3.1.0.1");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public Map<String, String> getMsdkRequestOptions() {
        return null;
    }

    public boolean isInitedSuccess() {
        return this.f3967a;
    }

    public void setInitedSuccess(boolean z) {
        this.f3967a = z;
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void setMsdkRequestOptions(Map<String, String> map) {
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void setPrivacyConfig(GMPrivacyConfig gMPrivacyConfig) {
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public void setThemeStatus(Map<String, Object> map) {
    }

    @Override // com.bytedance.msdk.adapter.config.ITTAdapterConfiguration
    public int showOpenOrInstallAppDialog(GMAppDialogClickListener gMAppDialogClickListener) {
        return 0;
    }
}
