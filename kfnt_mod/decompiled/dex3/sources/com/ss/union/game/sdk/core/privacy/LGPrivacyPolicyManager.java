package com.ss.union.game.sdk.core.privacy;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.dialog.BaseFragment;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.ToastUtils;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.browser.BrowserNoJsFragment;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class LGPrivacyPolicyManager {
    private static final String a = "lg_init_config";
    private static final String b = "lg_key_user_protocol_url";
    private static final String c = "lg_key_privacy_protocol_url";
    private static final String d = "lg_key_identify_protocol_url";
    private static boolean e = false;
    private static long f = 0;
    private static final int g = 1000;

    public static void setLoginPrivacyChecked(boolean z) {
        e = z;
    }

    public static boolean isLoginPrivacyChecked() {
        return e;
    }

    public static void openUserProtocol(BaseFragment<?, ?> baseFragment) {
        String serverUserProtocolUrl;
        if (a()) {
            return;
        }
        if (!ConfigManager.AppConfig.isGamePlatformSdk()) {
            serverUserProtocolUrl = getServerUserProtocolUrl();
        } else {
            serverUserProtocolUrl = a.e();
        }
        if (TextUtils.isEmpty(serverUserProtocolUrl)) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("lg_no_net_work_tips"));
        } else if (baseFragment != null) {
            baseFragment.navigation(BrowserNoJsFragment.a(serverUserProtocolUrl));
        } else {
            BrowserNoJsFragment.b(serverUserProtocolUrl);
        }
    }

    public static void openPrivacyProtocol(BaseFragment<?, ?> baseFragment) {
        String serverPrivacyProtocolUrl;
        if (a()) {
            return;
        }
        if (!ConfigManager.AppConfig.isGamePlatformSdk()) {
            serverPrivacyProtocolUrl = getServerPrivacyProtocolUrl();
        } else {
            serverPrivacyProtocolUrl = a.d();
        }
        if (TextUtils.isEmpty(serverPrivacyProtocolUrl)) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("lg_no_net_work_tips"));
        } else if (baseFragment != null) {
            baseFragment.navigation(BrowserNoJsFragment.a(serverPrivacyProtocolUrl));
        } else {
            BrowserNoJsFragment.b(serverPrivacyProtocolUrl);
        }
    }

    public static void openIdentifyProtocol(BaseFragment<?, ?> baseFragment) {
        String serverIdentifyProtocolUrl;
        if (a()) {
            return;
        }
        if (!ConfigManager.AppConfig.isGamePlatformSdk()) {
            serverIdentifyProtocolUrl = getServerIdentifyProtocolUrl();
        } else {
            serverIdentifyProtocolUrl = a.f();
        }
        if (TextUtils.isEmpty(serverIdentifyProtocolUrl)) {
            ToastUtils.getInstance().toast(ResourceUtils.getString("lg_no_net_work_tips"));
        } else if (baseFragment != null) {
            baseFragment.navigation(BrowserNoJsFragment.a(serverIdentifyProtocolUrl));
        } else {
            BrowserNoJsFragment.b(serverIdentifyProtocolUrl);
        }
    }

    public static void saveServerUserProtocolUrl(String str) {
        SPUtils.getInstance(a).put(b, str);
    }

    public static void saveServerPrivacyProtocolUrl(String str) {
        SPUtils.getInstance(a).put(c, str);
    }

    public static void saveServerIdentifyProtocolUrl(String str) {
        SPUtils.getInstance(a).put(d, str);
    }

    public static String getServerUserProtocolUrl() {
        return SPUtils.getInstance(a).getString(b, null);
    }

    public static String getServerPrivacyProtocolUrl() {
        return SPUtils.getInstance(a).getString(c, null);
    }

    public static String getServerIdentifyProtocolUrl() {
        return SPUtils.getInstance(a).getString(d, null);
    }

    private static boolean a() {
        if (System.currentTimeMillis() - f < 1000) {
            return true;
        }
        f = System.currentTimeMillis();
        return false;
    }

    private static final class a {
        private static final String a = "https://api.momoyu.com/magic/eco/runtime/release/61694822cf6a36032da01076?appType=momoyu";
        private static final String b = "https://api.momoyu.com/magic/eco/runtime/release/6169494b22b44403b7907d8b?appType=momoyu";
        private static final String c = "https://sf1-cdn-tos.douyinstatic.com/obj/ies-hotsoon-draft/momoyu/certification.html";

        /* JADX INFO: Access modifiers changed from: private */
        public static String d() {
            return a;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String e() {
            return b;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String f() {
            return c;
        }

        private a() {
        }

        private static String a(String str) {
            return String.format(str, ConfigManager.AppConfig.appName(), ConfigManager.AppConfig.appCompanyName(), ConfigManager.AppConfig.appPrivacyUpdateTime(), ConfigManager.AppConfig.appPrivacyValidTime(), ConfigManager.AppConfig.appCompanyRegisterAddress());
        }
    }
}
