package com.bytedance.sdk.account.monitor;

import android.text.TextUtils;
import com.bytedance.sdk.account.IMonitor;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.ss.android.account.TTAccountInit;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AccountMonitorUtil {
    public static void eventFinalize(String str, String str2, boolean z, int i, String str3, JSONObject jSONObject) throws JSONException {
        event(AccountMonitorConstants.EVENT_LOGIN_FINALIZE, null, str, str2, z, i, str3, jSONObject);
    }

    public static void authCallback(String str, boolean z, int i, String str2, JSONObject jSONObject) throws JSONException {
        event("passport_oauth_callback", null, str, "auth", z, i, str2, jSONObject);
    }

    public static void eventSwitchBindCancel(String str, JSONObject jSONObject) throws JSONException {
        event(AccountMonitorConstants.EventPlatform.OAUTH_SWITCH_CLICK, null, str, "cancel", false, 0, null, jSONObject);
    }

    public static void loginUiShow(String str, String str2, boolean z, int i, JSONObject jSONObject) throws JSONException {
        event(AccountMonitorConstants.EventPlatform.DIALOG_SHOW, null, str, str2, z, i, null, jSONObject);
    }

    public static void loginEntranceShowEvent(String str, JSONObject jSONObject) throws JSONException {
        onLoginEvent(AccountMonitorConstants.EventPlatform.LOGIN_ENTRANCE_EVENT, str, null, null, null, jSONObject);
    }

    public static void loginPageShowEvent(String str, String str2, String str3, JSONObject jSONObject) throws JSONException {
        onLoginEvent(AccountMonitorConstants.EventPlatform.LOGIN_PAGE_SHOW_EVENT, str, str2, str3, null, jSONObject);
    }

    public static void loginClickEvent(String str, String str2, String str3, JSONObject jSONObject) throws JSONException {
        onLoginEvent(AccountMonitorConstants.EventPlatform.LOGIN_CLICK_EVENT, str, str2, null, str3, jSONObject);
    }

    public static void loginResultEvent(String str, String str2, String str3, boolean z, int i, String str4, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_POSITION, str);
        jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_PAGE, str2);
        jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_PLATFORM, str3);
        if (z) {
            jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_RESULT, "success");
        } else {
            jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_RESULT, AccountMonitorConstants.CommonParameter.RESULT_FAIL);
            jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_ERROR_CODE, i);
            jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_ERROR_MSG, str4);
        }
        jSONObject.put("passport-sdk-version", 9);
        jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, 2);
        onEventV3(AccountMonitorConstants.EventPlatform.LOGIN_CLICK_RESULT_EVENT, jSONObject);
    }

    public static void sendCodeClickEvent(String str, boolean z, int i, String str2, JSONObject jSONObject) throws JSONException {
        onSendCodeEvent(AccountMonitorConstants.EventPlatform.MOBILE_CODE_CLICK_EVENT, str, z, i, str2, jSONObject);
    }

    public static void inputCodeEvent(String str, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.put(AccountMonitorConstants.CommonParameter.SENDCODE_POSITION, str);
        jSONObject.put("passport-sdk-version", 9);
        jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, 2);
        onEventV3(AccountMonitorConstants.EventPlatform.MOBILE_CODE_INPUT_EVENT, jSONObject);
    }

    public static void submitCodeEvent(String str, boolean z, int i, String str2, JSONObject jSONObject) throws JSONException {
        onSendCodeEvent(AccountMonitorConstants.EventPlatform.MOBILE_CODE_SUBMIT_EVENT, str, z, i, str2, jSONObject);
    }

    public static void onLoginEvent(String str, String str2, String str3, String str4, String str5, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_POSITION, str2);
        jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_PAGE, str3);
        jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_PLATFORM, str5);
        jSONObject.put(AccountMonitorConstants.CommonParameter.LOGIN_PLATFORM_LIST, str4);
        jSONObject.put("passport-sdk-version", 9);
        jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, 2);
        onEventV3(str, jSONObject);
    }

    public static void onSendCodeEvent(String str, String str2, boolean z, int i, String str3, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.put(AccountMonitorConstants.CommonParameter.SENDCODE_POSITION, str2);
        if (z) {
            jSONObject.put(AccountMonitorConstants.CommonParameter.SENDCODE_RESULT, 1);
        } else {
            jSONObject.put(AccountMonitorConstants.CommonParameter.SENDCODE_RESULT, 0);
            jSONObject.put(AccountMonitorConstants.CommonParameter.SENDCODE_ERROR_CODE, i);
            jSONObject.put(AccountMonitorConstants.CommonParameter.SENDCODE_ERROR_MSG, str3);
        }
        jSONObject.put("passport-sdk-version", 9);
        jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, 2);
        onEventV3(str, jSONObject);
    }

    public static void event(String str, String str2, String str3, String str4, boolean z, int i, String str5, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.put("platform", str3);
        jSONObject.put("scene", str4);
        if (!TextUtils.isEmpty(str2)) {
            jSONObject.put(AccountMonitorConstants.CommonParameter.URLPATH, str2);
        }
        if (z) {
            jSONObject.put("result", 1);
        } else {
            jSONObject.put("result", 0);
            jSONObject.put(AccountMonitorConstants.CommonParameter.ERROR, i);
            if (str5 != null) {
                jSONObject.put("errDesc", str5);
            }
        }
        jSONObject.put("passport-sdk-version", 9);
        jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, 2);
        onEventV3(str, jSONObject);
    }

    public static void onEvent(String str, String str2, String str3, BaseApiResponse baseApiResponse, AbsApiCall absApiCall) throws JSONException {
        event(str, str2, str3, baseApiResponse, absApiCall != null ? absApiCall.onSendEvent(str) : null);
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0031  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public static void event(java.lang.String r16, java.lang.String r17, java.lang.String r18, com.bytedance.sdk.account.api.call.BaseApiResponse r19, org.json.JSONObject r20) throws org.json.JSONException {
        /*
            r1 = r19
            if (r1 == 0) goto L4b
            java.lang.String r0 = r1.errorMsg
            boolean r2 = android.text.TextUtils.isEmpty(r0)
            if (r2 == 0) goto Le
            java.lang.String r0 = r1.mDetailErrorMsg
        Le:
            r7 = r0
            boolean r0 = r1 instanceof com.bytedance.sdk.account.api.call.LogoutApiResponse     // Catch: org.json.JSONException -> L34
            if (r0 == 0) goto L31
            r0 = r1
            com.bytedance.sdk.account.api.call.LogoutApiResponse r0 = (com.bytedance.sdk.account.api.call.LogoutApiResponse) r0     // Catch: org.json.JSONException -> L34
            java.lang.String r0 = r0.mLogoutScene     // Catch: org.json.JSONException -> L34
            boolean r2 = android.text.TextUtils.isEmpty(r0)     // Catch: org.json.JSONException -> L34
            if (r2 != 0) goto L31
            if (r20 != 0) goto L27
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch: org.json.JSONException -> L34
            r3.<init>()     // Catch: org.json.JSONException -> L34
            r2 = r3
            goto L29
        L27:
            r2 = r20
        L29:
            java.lang.String r3 = "logout_from"
            r2.put(r3, r0)     // Catch: org.json.JSONException -> L2f
            goto L3a
        L2f:
            r0 = move-exception
            goto L37
        L31:
            r2 = r20
            goto L3a
        L34:
            r0 = move-exception
            r2 = r20
        L37:
            r0.printStackTrace()
        L3a:
            r8 = r2
            java.lang.String r2 = r1.mRequestUrl
            boolean r5 = r1.success
            int r6 = r1.error
            r1 = r16
            r3 = r17
            r4 = r18
            event(r1, r2, r3, r4, r5, r6, r7, r8)
            goto L5a
        L4b:
            r9 = 0
            r12 = 0
            r13 = -1
            r14 = 0
            r8 = r16
            r10 = r17
            r11 = r18
            r15 = r20
            event(r8, r9, r10, r11, r12, r13, r14, r15)
        L5a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.sdk.account.monitor.AccountMonitorUtil.event(java.lang.String, java.lang.String, java.lang.String, com.bytedance.sdk.account.api.call.BaseApiResponse, org.json.JSONObject):void");
    }

    public static void platformEvent(String str, String str2, String str3, int i, String str4, String str5, boolean z, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.put("platform", str2);
        jSONObject.put("result", i);
        jSONObject.put("scene", str3);
        if (str4 != null) {
            jSONObject.put("errCode", str4);
        }
        if (str5 != null) {
            jSONObject.put("errDesc", str5);
        }
        if (i == 0 && z) {
            jSONObject.put("errorCancel", z);
        }
        jSONObject.put("passport-sdk-version", 9);
        jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, 2);
        onEventV3(str, jSONObject);
    }

    public static void platformAuthEvent(String str, String str2, int i, String str3, String str4, boolean z, JSONObject jSONObject) throws JSONException {
        platformEvent("passport_oauth_callback", str, str2, i, str3, str4, z, jSONObject);
    }

    public static void onSessionExpriedEvent(String str, String str2, String str3, String str4) throws JSONException {
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(str3)) {
                jSONObject.put(AccountMonitorConstants.CommonParameter.LOGID, str3);
            }
            if (!TextUtils.isEmpty(str2)) {
                jSONObject.put(AccountMonitorConstants.CommonParameter.URLPATH, str2);
            }
            jSONObject.put(AccountMonitorConstants.NEW_SDK_VERSION_KEY, 2);
            if (!TextUtils.isEmpty(str4)) {
                jSONObject.put(AccountMonitorConstants.CommonParameter.EXTRA, str4);
            }
            jSONObject.put("passport-sdk-version", 9);
            jSONObject.put("result", 0);
            onEventV3(str, jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void onTokenEvent(String str, JSONObject jSONObject) throws JSONException {
        if (jSONObject == null) {
            try {
                jSONObject = new JSONObject();
            } catch (JSONException e) {
                e.printStackTrace();
                return;
            }
        }
        jSONObject.put("passport-sdk-version", 9);
        jSONObject.put("result", 0);
        onEventV3(str, jSONObject);
    }

    private static void onEventV3(String str, JSONObject jSONObject) {
        IMonitor monitor = TTAccountInit.getConfig().getMonitor();
        if (monitor != null) {
            monitor.onEvent(str, jSONObject);
        }
    }
}
