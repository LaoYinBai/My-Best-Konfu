package com.bytedance.sdk.account.api.call;

import android.os.Bundle;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class BaseApiResponse {
    public static final int API_AUTH = 10003;
    public static final int API_AUTHORIZE_QR_CODE_LOGIN = 10021;
    public static final int API_BIND_LOGIN = 10005;
    public static final int API_CANCEL_CLOSE_ACCOUNT = 1032;
    public static final int API_CANCEL_DO = 10008;
    public static final int API_CANCEL_INDEX = 10009;
    public static final int API_CANCEL_POST = 10007;
    public static final int API_CAN_AWEME_QUICK_LOGIN = 10030;
    public static final int API_CHECK_CODE = 1019;
    public static final int API_CHECK_EVN = 10010;
    public static final int API_CHECK_LOGIN = 10029;
    public static final int API_CHECK_MOBILE_REGISTER = 10028;
    public static final int API_CHECK_MOBILE_UNUSABLE = 10023;
    public static final int API_CHECK_PWD = 10011;
    public static final int API_CHECK_TV_QR_CONNECT = 1031;
    public static final int API_DELETE_DEVICE = 10016;
    public static final int API_EMAIL_CHECK_REGISTER = 1026;
    public static final int API_EMAIL_REGISTER_VERIFY = 1025;
    public static final int API_EMAIL_SEND_CODE = 1024;
    public static final int API_GENERATE_USER_INFO_TICKET = 10026;
    public static final int API_GET_ACCOUNT_INFO = 10017;
    public static final int API_GET_AUTH_TICKET = 10013;
    public static final int API_GET_AVAILABLE_WAYS = 10024;
    public static final int API_GET_LOGIN_DEVICES = 10015;
    public static final int API_GET_QR_CODE = 10018;
    public static final int API_GET_QR_CODE_STATUS = 10019;
    public static final int API_GET_TV_QR_CODE = 1030;
    public static final int API_LOGIN_BY_TICKET = 10014;
    public static final int API_LOGIN_BY_TICKET_AFTER_REGISTER = 10027;
    public static final int API_LOGOUT = 10001;
    public static final int API_LOGOUT_OTHERS = 10023;
    public static final int API_MERGE = 10004;
    public static final int API_MOBILE_BIND_MOBILE = 1013;
    public static final int API_MOBILE_BIND_MOBILE_NO_PASS = 1012;
    public static final int API_MOBILE_CHANGE_PASS = 1010;
    public static final int API_MOBILE_CHECK_USER_NAME = 1008;
    public static final int API_MOBILE_EMAIL_LOGIN = 1009;
    public static final int API_MOBILE_LOGIN = 1007;
    public static final int API_MOBILE_MOBILE_NUM = 1011;
    public static final int API_MOBILE_QUEICK_LOGIN = 1006;
    public static final int API_MOBILE_QUEICK_LOGIN_CONTINUE = 1022;
    public static final int API_MOBILE_QUEICK_LOGIN_ONLY = 1021;
    public static final int API_MOBILE_REFRESH = 1000;
    public static final int API_MOBILE_REGISTER = 1004;
    public static final int API_MOBILE_RESET_WORD = 1003;
    public static final int API_MOBILE_SEND_CODE = 1002;
    public static final int API_MOBILE_UNBIND_MOBILE = 1001;
    public static final int API_ONE_MOBILE_BIND_MOBILE = 1027;
    public static final int API_PASS_SET_PASS_STRING = 10002;
    public static final int API_PLATFORM_TOKEN = 10006;
    public static final int API_SCAN_QR_CODE = 10020;
    public static final int API_SSO_BIND = 0;
    public static final int API_SSO_LOGIN = 1;
    public static final int API_SSO_SWITCH_BIND = 3;
    public static final int API_SSO_UNBIND = 2;
    public static final int API_SWITCH_AUTH = 101;
    public static final int API_SWITCH_TICKET = 1020;
    public static final int API_TICKET_RESET_PASSWORD = 1018;
    public static final int API_UPDATE_PWD = 10012;
    public static final int API_UPDATE_TOKEN = 10022;
    public static final int API_USER_PASSWORD_LOGIN = 1016;
    public static final int API_USER_PASSWORD_REGISTER = 1023;
    public static final int API_VALIDATE_CODE = 1015;
    public static final int API_VERIFY_ACCOUNT_PASSWORD = 10025;
    public static final int API_VERIFY_DEVICE = 1014;
    public final int api;
    public Bundle dataTip;
    public int error;
    public String errorMsg;
    public String mDetailErrorMsg;
    public String mRequestUrl;
    public JSONObject result;
    public boolean success;

    @Deprecated
    public String url;

    public BaseApiResponse(boolean z, int i) {
        this.success = z;
        this.api = i;
    }

    public boolean isBindExist() {
        return this.error == 1030 || this.error == 1041;
    }

    public boolean needCaptcha() {
        return this.error > 1100 && this.error < 1199;
    }

    public boolean needPicCaptcha() {
        return this.error == 1101 || this.error == 1102 || this.error == 1103;
    }

    public boolean needSecureCaptcha() {
        return this.error == 1104 || this.error == 1105;
    }
}
