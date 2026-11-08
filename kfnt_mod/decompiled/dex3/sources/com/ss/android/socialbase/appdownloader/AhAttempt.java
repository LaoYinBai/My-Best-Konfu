package com.ss.android.socialbase.appdownloader;

import android.text.TextUtils;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes3.dex */
public class AhAttempt {
    public static final int ERROR_CODE_DEFAULT = -1;
    public static final int ERROR_CODE_DEVICE_NOT_FIT = 2;
    public static final int ERROR_CODE_FIND_URI_FAILED = 10;
    public static final int ERROR_CODE_HAS_TASK_NOT_REDIRECTED = 8;
    public static final int ERROR_CODE_MODIFY_NAME = 11;
    public static final int ERROR_CODE_NOT_APK = 9;
    public static final int ERROR_CODE_RESOLVE_ACTIVITY_FAILED = 3;
    public static final int ERROR_CODE_SAVE_PATH_ERROR = 7;
    public static final int ERROR_CODE_START_ACTIVITY_FAILED = 1;
    public static final int ERROR_CODE_SUCCESS = 0;
    public static final int ERROR_CREATE_DESC_FAIL = 6;
    public static final int ERROR_NO_CUSTOM_DIR = 5;
    public static final int ERROR_OTHER = 4;
    public static final String JSON_TITLE = "ah_attempt";
    public static final String KEY_ANTI_PLAN_TYPE = "ah_plan_type";
    public static final String KEY_DEVICE_PLANS = "device_plans";
    public static final String KEY_ERROR_CODE = "error_code";
    public static final String KEY_ERROR_MSG = "error_msg";
    public static final String KEY_REAL_DEVICE_PLAN = "real_device_plan";
    public String anti_plan_type;
    public String device_plans;
    public int error_code = -1;
    public String error_msg;
    public String real_device_plan;

    public String getJsonString() {
        return getJSONObject().toString();
    }

    public JSONObject getJSONObject() {
        JSONObject jSONObject = new JSONObject();
        appendJSONObject(jSONObject);
        return jSONObject;
    }

    public void appendJSONObject(JSONObject jSONObject) {
        if (jSONObject == null) {
            return;
        }
        try {
            jSONObject.put(KEY_ANTI_PLAN_TYPE, this.anti_plan_type);
            jSONObject.put("error_code", String.valueOf(this.error_code));
            jSONObject.put("error_msg", this.error_msg);
            jSONObject.put(KEY_REAL_DEVICE_PLAN, this.real_device_plan);
            jSONObject.put("device_plans", this.device_plans);
        } catch (Throwable unused) {
        }
    }

    public static AhAttempt fromJson(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        AhAttempt ahAttempt = new AhAttempt();
        try {
            JSONObject jSONObject = new JSONObject(str);
            ahAttempt.device_plans = jSONObject.optString("device_plans", null);
            ahAttempt.real_device_plan = jSONObject.optString(KEY_REAL_DEVICE_PLAN, null);
            ahAttempt.error_msg = jSONObject.optString("error_msg", null);
            ahAttempt.anti_plan_type = jSONObject.optString(KEY_ANTI_PLAN_TYPE, null);
            String strOptString = jSONObject.optString("error_code");
            if (TextUtils.isEmpty(strOptString)) {
                ahAttempt.error_code = -1;
            } else {
                ahAttempt.error_code = Integer.parseInt(strOptString);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return ahAttempt;
    }
}
