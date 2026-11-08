package com.ss.union.game.sdk.core.base.event;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import com.ss.android.download.api.constant.BaseConstants;
import com.ss.union.game.sdk.common.util.AppUtils;
import com.ss.union.game.sdk.common.util.DigestUtils;
import com.ss.union.game.sdk.common.util.GlobalApplicationUtils;
import com.ss.union.game.sdk.common.util.NetworkUtils;
import com.ss.union.game.sdk.common.util.RomUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.StringUtils;
import com.ss.union.game.sdk.core.LGSDKCore;
import com.ss.union.game.sdk.core.base.config.AppIdManager;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.constant.SDKVersion;
import java.util.TimeZone;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class EventJSONHeaders {
    private static final String A = "timezone";
    private static final String B = "access";
    private static final String C = "os";
    private static final String D = "os_version";
    private static final String E = "os_api";
    private static final String F = "rom";
    private static final String G = "device_model";
    private static final String H = "language";
    private static final String I = "resolution";
    private static final String J = "build_serial";
    private static final String K = "display_density";
    private static final String L = "density_dpi";
    private static final String M = "mc";
    private static final String N = "carrier";
    private static final String O = "sig_hash";
    private static final String b = "open_id";
    private static final String c = "is_adult";
    private static final String d = "account_type";
    private static final String e = "device_brand";
    private static final String f = "udid";
    private static final String g = "openudid";
    private static final String h = "clientudid";
    private static final String i = "install_id";
    private static final String j = "bd_did";
    private static final String k = "oaid";

    @Deprecated
    private static final String l = "aid";
    private static final String m = "local_app_id";
    private static final String n = "sdk_version";
    private static final String o = "sdk_version_name";
    private static final String p = "sdk_type";
    private static final String q = "ad_sdk_version";
    private static final String r = "dy_version";
    private static final String s = "tt_version";
    private static final String t = "applog_version";
    private static final String u = "online_appid";
    private static final String v = "package";
    private static final String w = "channel";
    private static final String x = "display_name";
    private static final String y = "app_version";
    private static final String z = "version_code";
    private volatile JSONObject Q = new JSONObject();
    private volatile boolean R = false;
    static final /* synthetic */ boolean a = !EventJSONHeaders.class.desiredAssertionStatus();
    private static EventJSONHeaders P = new EventJSONHeaders();

    private void c() {
    }

    public static EventJSONHeaders getInstance() {
        return P;
    }

    public boolean isInitSuccess() {
        return this.R;
    }

    public boolean init() throws JSONException, PackageManager.NameNotFoundException {
        if (!a(this.Q, GlobalApplicationUtils.getContext())) {
            g.b("header初始化，请求必要的版本号信息失败", null);
            return false;
        }
        if (LGSDKCore.isSdkInitSuccess()) {
            b();
        }
        d();
        c();
        this.R = a();
        if (!this.R) {
            g.b("header初始化，请求必要参数did｜iid失败", null);
        } else {
            g.a("header初始化成功");
        }
        return this.R;
    }

    private boolean a() {
        return (TextUtils.isEmpty(this.Q.optString(j)) || TextUtils.isEmpty(this.Q.optString("install_id")) || TextUtils.isEmpty(this.Q.optString("device_brand"))) ? false : true;
    }

    private int a(Context context) {
        return context.getResources().getDisplayMetrics().densityDpi;
    }

    private String b(Context context) {
        int i2 = context.getResources().getDisplayMetrics().densityDpi;
        return i2 < 160 ? "ldpi" : i2 < 240 ? "mdpi" : i2 < 320 ? "hdpi" : i2 < 480 ? "xhdpi" : i2 < 640 ? "xxhdpi" : "xxxhdpi";
    }

    @SuppressLint({"MissingPermission", "HardwareIds"})
    private void b() throws JSONException {
        String deviceId;
        Context context = GlobalApplicationUtils.getContext();
        try {
            this.Q.put(E, Build.VERSION.SDK_INT);
            this.Q.put("device_model", Build.MODEL);
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            this.Q.put(K, b(context));
            this.Q.put(L, displayMetrics.densityDpi);
            this.Q.put("resolution", displayMetrics.heightPixels + "x" + displayMetrics.widthPixels);
            this.Q.put("device_brand", Build.BRAND.toLowerCase());
            this.Q.put(J, Build.SERIAL);
        } catch (Exception unused) {
        }
        try {
            String language = context.getResources().getConfiguration().locale.getLanguage();
            if (!StringUtils.isEmpty(language)) {
                this.Q.put("language", language);
            }
            String strA = a(NetworkUtils.getMacAddress(), "mac_addr");
            if (!StringUtils.isEmpty(strA)) {
                this.Q.put("mc", strA);
            }
            int rawOffset = TimeZone.getDefault().getRawOffset() / BaseConstants.Time.HOUR;
            if (rawOffset < -12) {
                rawOffset = -12;
            }
            if (rawOffset > 12) {
                rawOffset = 12;
            }
            this.Q.put("timezone", rawOffset);
            String networkAccessType = NetworkUtils.getNetworkAccessType();
            if (networkAccessType != null) {
                this.Q.put("access", networkAccessType);
            }
        } catch (Exception unused2) {
        }
        String networkOperatorName = null;
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
            if (!a && telephonyManager == null) {
                throw new AssertionError();
            }
            deviceId = telephonyManager.getDeviceId();
        } catch (Exception e2) {
            g.a("failed to get imei: " + e2.getMessage());
            deviceId = null;
        }
        try {
            TelephonyManager telephonyManager2 = (TelephonyManager) context.getSystemService("phone");
            if (!a && telephonyManager2 == null) {
                throw new AssertionError();
            }
            networkOperatorName = telephonyManager2.getNetworkOperatorName();
        } catch (Exception e3) {
            g.b("failed to getNetworkOperatorName: ", e3);
        }
        StringBuilder sb = new StringBuilder();
        try {
            if (RomUtils.isXiaomi()) {
                sb.append("MIUI-");
            }
            sb.append(Build.VERSION.INCREMENTAL);
        } catch (Throwable unused3) {
        }
        String strA2 = j.a(context);
        String strB = j.b(context);
        try {
            if (!StringUtils.isEmpty(deviceId)) {
                this.Q.put(f, deviceId);
            }
            if (!StringUtils.isEmpty(networkOperatorName)) {
                this.Q.put("carrier", networkOperatorName);
            }
            if (!StringUtils.isEmpty(strB)) {
                this.Q.put("clientudid", strB);
            }
            if (!StringUtils.isEmpty(strA2)) {
                this.Q.put("openudid", strA2);
            }
            if (!StringUtils.isEmpty(Build.BRAND)) {
                this.Q.put("brand", Build.BRAND);
            }
            if (sb.length() > 0) {
                this.Q.put(F, sb.toString());
            }
        } catch (JSONException e4) {
            g.b("prepareUDID exception: ", e4);
        }
    }

    private void c(Context context) throws JSONException, PackageManager.NameNotFoundException {
        Signature signature;
        String strMd5Hex;
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 64);
            if (packageInfo == null || packageInfo.signatures == null || packageInfo.signatures.length < 1 || (signature = packageInfo.signatures[0]) == null || (strMd5Hex = DigestUtils.md5Hex(signature.toByteArray())) == null) {
                return;
            }
            this.Q.put(O, strMd5Hex);
        } catch (Exception e2) {
            g.b("failed to get package signature: ", e2);
        }
    }

    private String a(String str, String str2) {
        String strB = a.b(str2);
        if (TextUtils.isEmpty(str) && !TextUtils.isEmpty(strB)) {
            return strB;
        }
        if (!TextUtils.isEmpty(str) && !TextUtils.equals(str, strB)) {
            a.b(str2, str);
        }
        return str == null ? "" : str;
    }

    private void d() throws JSONException, PackageManager.NameNotFoundException {
        Context context = GlobalApplicationUtils.getContext();
        try {
            this.Q.put(m, AppIdManager.lgAppID());
            this.Q.put("sdk_type", SDKVersion.LG_SDK_TYPE_U_SDK);
            this.Q.put("channel", ConfigManager.AppConfig.appChannel());
            this.Q.put("sdk_version", 2333);
            this.Q.put(o, "2.3.3.3");
            this.Q.put(q, "4.0.2.1");
            this.Q.put(r, "0.0.1.3");
            this.Q.put(t, "6.3.0");
        } catch (JSONException e2) {
            g.a("initAppInfo fail:", e2);
        }
        if (AppUtils.isDebug(context)) {
            return;
        }
        c(context);
    }

    private boolean a(JSONObject jSONObject, Context context) throws JSONException, PackageManager.NameNotFoundException {
        int i2;
        try {
            String packageName = context.getPackageName();
            jSONObject.put("package", packageName);
            jSONObject.put("os", "Android");
            jSONObject.put("os_version", Build.VERSION.RELEASE);
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            jSONObject.put("app_version", packageInfo.versionName);
            jSONObject.put("version_code", packageInfo.versionCode);
            if (packageInfo.applicationInfo == null || (i2 = packageInfo.applicationInfo.labelRes) <= 0) {
                return true;
            }
            jSONObject.put("display_name", context.getString(i2));
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void update(String str, String str2) throws JSONException {
        try {
            this.Q.put(str, str2);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void updateUserInfo(String str, String str2, boolean z2) throws JSONException {
        try {
            this.Q.put("open_id", str);
            this.Q.put(d, str2);
            this.Q.put("is_adult", z2 ? 1 : 0);
        } catch (Exception unused) {
        }
    }

    public void updateDeviceAdultInfo(boolean z2) throws JSONException {
        try {
            this.Q.put("is_adult", z2 ? 1 : 0);
        } catch (Exception unused) {
        }
    }

    public void updateOaid(String str) throws JSONException {
        try {
            this.Q.put(k, str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void updateDid(String str) throws JSONException {
        try {
            this.Q.put(j, str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void updateIid(String str) throws JSONException {
        try {
            this.Q.put("install_id", str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public void updateOnlineAppId(String str) throws JSONException {
        try {
            this.Q.put(u, str);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    public JSONObject getJSONObject() {
        return this.Q;
    }

    public String getOpenUDID(Context context) {
        String strOptString = this.Q.optString("openudid");
        return !TextUtils.isEmpty(strOptString) ? strOptString : j.a(context);
    }

    private static class a {
        private static final String a = "lg_event_header_stats";
        private static final String b = "mac_addr";

        private a() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static void b(String str, String str2) {
            SPUtils.getInstance(a).put(str, str2);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static String b(String str) {
            return SPUtils.getInstance(a).getString(str, "");
        }
    }
}
