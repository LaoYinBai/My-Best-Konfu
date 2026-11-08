package com.ss.union.game.sdk.core.realName.event;

import com.sigmob.sdk.common.Constants;
import com.sigmob.sdk.common.mta.PointCategory;
import com.ss.union.game.sdk.core.base.config.ConfigManager;
import com.ss.union.game.sdk.core.base.event.PageStater;
import com.ss.union.game.sdk.core.init.config.GameOptionConfig;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class RealNameLogEvent {
    public static final String REAL_NAME_EVENT_KEY_ERROR = "realname_error";
    public static final String REAL_NAME_EVENT_KEY_RESULT = "realname_result";
    public static final String REAL_NAME_EVENT_KEY_SHOW = "realname_show";
    public static final String REAL_NAME_EVENT_KEY_STATUS = "realname_status";
    public static final String REAL_NAME_EVENT_TYPE_VALUE = "ohayoo_sdk_realname";
    public static final String REAL_NAME_EVENT_VALUE_ADULT = "adult";
    public static final String REAL_NAME_EVENT_VALUE_ADULT_WINDOW = "adult_window";
    public static final String REAL_NAME_EVENT_VALUE_CHOSE_BEIDONG = "realname_chose_beidong";
    public static final String REAL_NAME_EVENT_VALUE_CHOSE_ZHUDONG = "realname_chose_zhudong";
    public static final String REAL_NAME_EVENT_VALUE_FAIL = "realname_fail";
    public static final String REAL_NAME_EVENT_VALUE_LOGIN_BEIDONG = "realname_login_beidong";
    public static final String REAL_NAME_EVENT_VALUE_LOGIN_ZHUDONG = "realname_login_zhudong";
    public static final String REAL_NAME_EVENT_VALUE_MINOR = "minor";
    public static final String REAL_NAME_EVENT_VALUE_MINOR_WNDOW = "minor_window";
    public static final String REAL_NAME_EVENT_VALUE_SUCCESS = "realname_success";
    public static final String REAL_NAME_EVENT_VALUE_WINDOW_BEIDONG = "realname_window_beidong";
    public static final String REAL_NAME_EVENT_VALUE_WINDOW_LOGIN_BEIDONG = "realname_window_login_beidong";
    public static final String REAL_NAME_EVENT_VALUE_WINDOW_LOGIN_NONTOURIST = "realname_window_login_nontourist";
    public static final String REAL_NAME_EVENT_VALUE_WINDOW_LOGIN_ZHUDONG = "realname_window_login_zhudong";
    public static final String REAL_NAME_EVENT_VALUE_WINDOW_ZHUDONG = "realname_window_zhudong";
    private static final String a = "window_show";
    private static final String b = "window_close_or_not";
    private static final String c = "click_button";
    private static final String d = "verify_real_name";
    private static final String e = "real_name_window";
    private static final String f = "real_name_window_close_or_not";
    private static final String g = "click_submit_real_name";
    private static final String h = "click_close_real_name_window";
    private static final String i = "real_name_result";
    private static Map<Integer, Integer> j = new HashMap();
    private static final String k = "ohayoo_sdk_didrealname";
    private static final String l = "ohayoo_sdk_realname_reward";

    static {
        j.put(100, 1);
        j.put(101, 2);
        j.put(102, 3);
        j.put(103, 4);
        j.put(104, 5);
        j.put(105, 6);
        j.put(106, 6);
        j.put(107, 6);
        j.put(108, 7);
        j.put(109, 8);
    }

    public static void onWindowCloseOrNotConfig() throws JSONException {
        boolean zIsRealNameWindowCanClose;
        boolean z = !ConfigManager.LoginConfig.isNoUserLogin();
        if (z) {
            zIsRealNameWindowCanClose = GameOptionConfig.GameOption.AntiAddiction.Account.isRealNameWindowCanClose();
        } else {
            zIsRealNameWindowCanClose = GameOptionConfig.GameOption.AntiAddiction.Device.isRealNameWindowCanClose();
        }
        a(b, f, zIsRealNameWindowCanClose ? "close" : PointCategory.CANNOT_CLOSE, z ? 2L : 1L);
    }

    public static void closeRealNameWindow(int i2) throws JSONException {
        a("click_button", h, "", j.get(Integer.valueOf(i2)).intValue());
    }

    public static void realNameAuthResult(boolean z, int i2, int i3) throws JSONException {
        a(d, i, z ? "success" : "fail", i3, j.get(Integer.valueOf(i2)).intValue());
    }

    private static void a(String str, String str2, String str3, long j2) throws JSONException {
        PageStater.V1.onEvent(str, str2, str3, 0, j2);
    }

    private static void a(String str, String str2, String str3, int i2, long j2) throws JSONException {
        PageStater.V1.onEvent(str, str2, str3, i2, j2);
    }

    public static void reportShow(String str) {
        HashMap map = new HashMap();
        map.put(REAL_NAME_EVENT_KEY_SHOW, str);
        PageStater.onEvent(REAL_NAME_EVENT_TYPE_VALUE, map);
    }

    public static void reportRealNameSuccess(String str) {
        HashMap map = new HashMap();
        map.put(REAL_NAME_EVENT_KEY_RESULT, REAL_NAME_EVENT_VALUE_SUCCESS);
        map.put(REAL_NAME_EVENT_KEY_STATUS, str);
        PageStater.onEvent(REAL_NAME_EVENT_TYPE_VALUE, map);
    }

    public static void reportRealNameFail(String str) {
        HashMap map = new HashMap();
        map.put(REAL_NAME_EVENT_KEY_RESULT, REAL_NAME_EVENT_VALUE_FAIL);
        map.put(REAL_NAME_EVENT_KEY_ERROR, str);
        PageStater.onEvent(REAL_NAME_EVENT_TYPE_VALUE, map);
    }

    public static void reportDeviceRealNameRequest(String str) {
        a("didrealname_request", str);
    }

    public static void reportDeviceRealNameRequestSuccess() {
        a("didrealname_result", "didrealname_success");
    }

    public static void reportDeviceRealNameRequestFail() {
        a("didrealname_result", "didrealname_fail");
    }

    public static void reportDeviceRealNameShow() {
        a("didrealname_show", "didrealname_popup");
    }

    public static void reportDeviceRealNameClick() {
        a("didrealname_click", "didrealname_submit");
    }

    public static void reportDeviceRealNameDay(int i2) {
        a("didrealname_day", String.valueOf(i2));
    }

    private static void a(String str, String str2) {
        HashMap map = new HashMap();
        map.put(str, str2);
        PageStater.onEvent(k, map);
    }

    public static void reportRealNameAwardRequest(String str) {
        b("realname_reward_request", str);
    }

    public static void reportHasRealName() {
        b("realname_reward_historycheck", "1");
    }

    public static void reportNoRealName() {
        b("realname_reward_historycheck", Constants.FAIL);
    }

    public static void reportRealNameAwardShowFromLoginWindow() {
        b("realname_reward_show", "realname_reward_login");
    }

    public static void reportRealNameAwardShowFromSilentWindow() {
        b("realname_reward_show", "realname_reward_autotourist");
    }

    public static void reportRealNameAwardShowFromNoAccount() {
        b("realname_reward_show", "realname_reward_noaccount");
    }

    public static void reportRealNameSubmit() {
        b("realname_reward_click", "realname_reward_submit");
    }

    public static void reportRealNameAwardResultSuccess() {
        b("realname_reward_result", "realname_reward_success");
    }

    public static void reportRealNameAwardResultFail() {
        b("realname_reward_result", "realname_reward_fail");
    }

    private static void b(String str, String str2) {
        HashMap map = new HashMap();
        map.put(str, str2);
        PageStater.onEvent(l, map);
    }
}
