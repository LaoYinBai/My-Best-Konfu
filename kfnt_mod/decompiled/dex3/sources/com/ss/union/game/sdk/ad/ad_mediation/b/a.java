package com.ss.union.game.sdk.ad.ad_mediation.b;

import android.text.TextUtils;
import android.util.Log;
import com.bytedance.msdk.api.v2.GMAdConfig;
import com.bytedance.msdk.api.v2.GMMediationAdSdk;
import com.bytedance.msdk.api.v2.GMPangleOption;
import com.sigmob.sdk.common.Constants;
import com.ss.union.game.sdk.ad.ad_mediation.d.b;
import com.ss.union.game.sdk.common.util.ResourceUtils;
import com.ss.union.game.sdk.common.util.SPUtils;
import com.ss.union.game.sdk.common.util.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    private static boolean a;
    private static boolean b;
    private static boolean c;
    private static boolean d;
    private static String e;
    private static String f;
    private static int[] g;

    public static void a() {
        try {
            a = ResourceUtils.getBoolean("lg_ad_paid", false);
            b = ResourceUtils.getBoolean("lg_ad_use_textureview", false);
            c = ResourceUtils.getBoolean("lg_ad_allow_show_page_when_screen_lock", false);
            d = ResourceUtils.getBoolean("lg_ad_allow_show_notify", true);
            e = ResourceUtils.getString("lg_ad_data");
            f = ResourceUtils.getString("lg_ad_keywords");
            g = ResourceUtils.getIntegerArray("lg_ad_direct_download_network_type", new int[0]);
            b(i());
        } catch (Throwable th) {
            b.a("LGAdConfigManager init Error " + Log.getStackTraceString(th));
        }
    }

    public static boolean b() {
        return a;
    }

    public static boolean c() {
        return b;
    }

    public static boolean d() {
        return c;
    }

    public static boolean e() {
        return d;
    }

    public static String f() {
        return e;
    }

    public static String g() {
        return f;
    }

    public static int[] h() {
        return g;
    }

    public static void a(String str) {
        if (StringUtils.isEmpty(str)) {
            return;
        }
        a("game_adapter_did", str);
    }

    public static String i() {
        return SPUtils.getInstance().getString("lg_personal_ads_type", "-1");
    }

    public static void b(String str) {
        if ("1".equals(str) || Constants.FAIL.equals(str)) {
            a("personal_ads_type", str);
            SPUtils.getInstance().put("lg_personal_ads_type", str);
        }
    }

    private static <T> void a(String str, T t) {
        JSONArray jSONArray;
        JSONObject jSONObject = null;
        if (StringUtils.isEmpty(e)) {
            jSONArray = null;
        } else {
            try {
                jSONArray = new JSONArray(e);
            } catch (Throwable unused) {
            }
        }
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        int i = 0;
        while (true) {
            if (i >= jSONArray.length()) {
                break;
            }
            JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(i);
            if (str.equals(jSONObjectOptJSONObject.optString("name"))) {
                jSONObject = jSONObjectOptJSONObject;
                break;
            }
            i++;
        }
        if (jSONObject == null) {
            jSONObject = new JSONObject();
            jSONArray.put(jSONObject);
        }
        try {
            jSONObject.put("name", str);
            jSONObject.put("value", t);
        } catch (Throwable unused2) {
        }
        e = jSONArray.toString();
    }

    public static void c(String str) {
        try {
            GMMediationAdSdk.updatePangleConfig(new GMAdConfig.Builder().setPangleOption(new GMPangleOption.Builder().setData(str).build()).build());
        } catch (Exception e2) {
            b.a("setCustomDataToTTAd() exception:" + Log.getStackTraceString(e2));
        }
    }

    public static void d(String str) {
        if (TextUtils.isEmpty(str)) {
            b.a("updateDidToMSdk did is null...");
        } else {
            GMMediationAdSdk.setPulisherDid(str);
        }
    }
}
