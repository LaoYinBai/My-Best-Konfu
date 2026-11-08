package com.ss.union.game.sdk.account.a;

import android.text.TextUtils;
import com.ss.union.game.sdk.common.util.DeviceUtils;
import com.ss.union.game.sdk.core.base.account.constant.LGAccountConstant;
import com.ss.union.game.sdk.core.base.event.PageStater;
import java.util.HashMap;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class a {
    public static final String A = "auto";
    public static final String B = "manual";
    public static final String C = "acquire_phone";
    public static final String D = "click_close_button";
    public static final String E = "click_operator_service_terms";
    public static final String F = "operator_certification";
    public static final String G = "user_click_operator_service_terms";
    public static final String H = "launch_result";
    public static final String I = "client_fail";
    public static final String J = "client_success";
    public static final String K = "launch_error";
    public static final String L = "click_bind";
    public static final String M = "click_button";
    public static final String N = "bind_success";
    public static final String O = "bind_fail";
    public static final int P = 1;
    public static final int Q = 2;
    public static final int R = 3;
    public static final int S = 4;
    public static final int T = 5;
    public static final int U = 6;
    public static final String V = "result_code";
    public static final String W = "error_code";
    public static final String X = "error_msg";
    public static final String Y = "visitor_bind";
    public static final String Z = "switch_account";
    public static final String a = "ohayoo_sdk_launch";
    public static final String aa = "switch_success";
    public static final String ab = "switch_fail";
    public static final String ac = "login_success";
    public static final String ad = "login_fail";
    public static final String ae = "login";
    public static final String af = "switch_account";
    public static final String ag = "show_login_tips";
    public static final String ah = "welcome_tips";
    public static final String ai = "auto";
    public static final String aj = "manual";
    public static final String ak = "new_auto_login";
    public static final String al = "TOUTIAO_AUTH";
    public static final String am = "DOUYIN_AUTH";
    public static final String an = "APP_CLOUD_MOBILE";
    public static final String ao = "GUEST";
    private static final String ap = "ohayoo_sdk_logoff";
    private static final String aq = "ohayoo_sdk_douyinUnbind";
    public static final String b = "window_show";
    public static final String c = "launch_show";
    public static final String d = "window_show_normal";
    public static final String e = "window_show_bind";
    public static final String f = "window_show_switch";
    public static final String g = "phone_click_login";
    public static final String h = "phone_bind_login";
    public static final String i = "phone_switch_login";
    public static final String j = "show_sms_verification_code_window";
    public static final String k = "click_login";
    public static final String l = "click_button";
    public static final String m = "login_result";
    public static final String n = "launch_click";
    public static final String o = "phone_login_click";
    public static final String p = "other_login_click";
    public static final String q = "user_agreement";
    public static final String r = "phone_privacy";
    public static final String s = "phone_next_step";
    public static final String t = "phone_back_button";
    public static final String u = "resend_sms_verification_code";
    public static final String v = "verification_code_back_button";
    public static final String w = "launch_status";
    public static final String x = "mobile_phone";
    public static final String y = "guest";
    public static final String z = "douyin";

    public static void a(String str) {
        HashMap map = new HashMap();
        map.put(c, str);
        PageStater.onEvent(a, map);
    }

    public static void a(String str, String str2) {
        HashMap map = new HashMap();
        map.put(n, str);
        map.put(w, str2);
        PageStater.onEvent(a, map);
    }

    public static void b(String str) {
        HashMap map = new HashMap();
        map.put(n, str);
        PageStater.onEvent(a, map);
    }

    public static void a(String str, boolean z2, int i2) {
        HashMap map = new HashMap();
        map.put(w, str);
        if (z2) {
            map.put(H, J);
        } else {
            map.put(H, I);
            map.put(K, i2 + "");
        }
        PageStater.onEvent(a, map);
    }

    public static void a(int i2) throws JSONException {
        String str = "";
        if (i2 == 1) {
            str = "login_box_close_window";
        } else if (i2 == 2) {
            str = "guest_binding_close_window";
        } else if (i2 == 3) {
            str = "scene_switch_account_close_window";
        }
        PageStater.V1.onEvent(D, str);
    }

    public static void a(int i2, String str) throws JSONException {
        if (i2 == 1) {
            PageStater.V1.onEvent("click_login_other_account", "login_box_login", str);
        } else if (i2 == 2) {
            PageStater.V1.onEvent("click_login_other_account", "guest_binding_login", str);
        } else if (i2 == 3) {
            PageStater.V1.onEvent("click_login_other_account", "scene_switch_account_login", str);
        }
    }

    public static void a(String str, boolean z2) throws JSONException {
        PageStater.V1.onEvent(C, str, z2 ? "success" : "fail");
    }

    public static void c(String str) {
        HashMap map = new HashMap();
        map.put("logoff_request", DeviceUtils.getAppPackageName());
        String strF = f(str);
        if (!TextUtils.isEmpty(strF)) {
            map.put("logoff_type", strF);
        }
        PageStater.onEvent(ap, map);
    }

    public static void d(String str) {
        b(str, "logoff_success");
    }

    public static void e(String str) {
        b(str, "logoff_fail");
    }

    private static void b(String str, String str2) {
        HashMap map = new HashMap();
        map.put("logoff_result", str2);
        String strF = f(str);
        if (!TextUtils.isEmpty(strF)) {
            map.put("logoff_type", strF);
        }
        PageStater.onEvent(ap, map);
    }

    private static String f(String str) {
        if (LGAccountConstant.LoginType.LOGIN_TYPE_CLOUD_PHONE.getName().equals(str)) {
            return "mobile_phone_logoff";
        }
        if (LGAccountConstant.LoginType.LOGIN_TYPE_GUEST.getName().equals(str)) {
            return "guest_logoff";
        }
        if (LGAccountConstant.LoginType.LOGIN_TYPE_DY.getName().equals(str)) {
            return "douyin_logoff";
        }
        return null;
    }

    public static void a() {
        HashMap map = new HashMap();
        map.put("douyinUnbind_request", DeviceUtils.getAppPackageName());
        PageStater.onEvent(aq, map);
    }

    public static void b() {
        HashMap map = new HashMap();
        map.put("douyinUnbind_result", "douyinUnbind_success");
        PageStater.onEvent(aq, map);
    }

    public static void c() {
        HashMap map = new HashMap();
        map.put("douyinUnbind_result", "douyinUnbind_fail");
        PageStater.onEvent(aq, map);
    }
}
