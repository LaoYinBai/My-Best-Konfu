package com.bytedance.sdk.account.impl;

import android.text.TextUtils;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.constants.UserInfoThreadConstants;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.mobile.query.MobileQueryObj;
import com.bytedance.sdk.account.mobile.query.OneBindMobileQueryObj;
import com.bytedance.sdk.account.mobile.query.SendCodeQueryObj;
import com.bytedance.sdk.account.sso.ApiObj;
import com.bytedance.sdk.account.sso.BindConflictUser;
import com.bytedance.sdk.account.sso.UserApiObj;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import com.ss.android.account.BDAccountUserEntity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ApiHelper {
    public static ApiRequest.Builder createSsoBuilder(String str, String str2, String str3, String str4, String str5, String str6, Map<String, String> map) {
        ApiRequest.Builder builder = new ApiRequest.Builder();
        if (!TextUtils.isEmpty(str)) {
            builder.parameter("platform", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            builder.parameter("access_token", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            builder.parameter("expires_in", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            builder.parameter("code", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            builder.parameter("profile_key", str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            builder.parameter("platform_app_id", str6);
        }
        if (map != null && !map.isEmpty()) {
            for (String str7 : map.keySet()) {
                if (!TextUtils.isEmpty(str7)) {
                    builder.parameter(str7, map.get(str7));
                }
            }
        }
        return builder;
    }

    public static Map<String, String> getBindWithMobileParams(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, Map<String, String> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        if (!TextUtils.isEmpty(str)) {
            map.put("platform_app_id", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("platform", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            map.put("code", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            map.put("access_token", str4);
        }
        if (!TextUtils.isEmpty(str5)) {
            map.put("access_token_secret", str5);
        }
        if (!TextUtils.isEmpty(str6)) {
            map.put("profile_key", str6);
        }
        if (!TextUtils.isEmpty(str7)) {
            map.put("openid", str7);
        }
        if (!TextUtils.isEmpty(str8)) {
            map.put("expires_in", str8);
        }
        if (!TextUtils.isEmpty(str9)) {
            map.put("verify_type", str9);
        }
        if (!TextUtils.isEmpty(str10)) {
            map.put("verified_ticket", str10);
        }
        return map;
    }

    public static void apiError(ApiObj apiObj, JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject.has("error_code")) {
            apiObj.mError = jSONObject.optInt("error_code", apiObj.mError);
        } else if (jSONObject.has("code")) {
            apiObj.mError = jSONObject.optInt("code", apiObj.mError);
        }
        apiObj.mErrorMsg = jSONObject.optString(UserInfoThreadConstants.DESCRIPTION);
        if (apiObj.mError == 1075) {
            apiObj.mCancelApplyTime = jSONObject.optLong("apply_time");
            apiObj.mCancelAvatarUrl = jSONObject.optString("avatar_url");
            apiObj.mCancelNickName = jSONObject.optString("nick_name");
            apiObj.mCancelToken = jSONObject.optString("token");
            apiObj.mCancelTime = jSONObject.optLong("cancel_time");
        }
    }

    public static void mobileError(MobileQueryObj mobileQueryObj, JSONObject jSONObject) {
        if (jSONObject.has("error_code")) {
            mobileQueryObj.mError = jSONObject.optInt("error_code", mobileQueryObj.mError);
        } else if (jSONObject.has("code")) {
            mobileQueryObj.mError = jSONObject.optInt("code", mobileQueryObj.mError);
        }
        mobileQueryObj.mErrorMsg = jSONObject.optString(UserInfoThreadConstants.DESCRIPTION);
        if (mobileQueryObj instanceof MobileQueryObj) {
            mobileQueryObj.mErrorCaptcha = jSONObject.optString("captcha");
            mobileQueryObj.mErrorAlert = jSONObject.optString("alert_text");
        }
        if (mobileQueryObj.mError == 1001 && (mobileQueryObj instanceof SendCodeQueryObj)) {
            ((SendCodeQueryObj) mobileQueryObj).mConfirmSwitchBindTips = jSONObject.optString(UserInfoThreadConstants.DIALOG_TIPS);
        }
        if (mobileQueryObj.mError == 1057 && (mobileQueryObj instanceof SendCodeQueryObj)) {
            SendCodeQueryObj sendCodeQueryObj = (SendCodeQueryObj) mobileQueryObj;
            sendCodeQueryObj.mConfirmSwitchBindTips = jSONObject.optString(UserInfoThreadConstants.DIALOG_TIPS);
            sendCodeQueryObj.mConfirmSwitchBindUrl = jSONObject.optString("next_url");
        }
        if (mobileQueryObj.mError == 1057 && (mobileQueryObj instanceof OneBindMobileQueryObj)) {
            OneBindMobileQueryObj oneBindMobileQueryObj = (OneBindMobileQueryObj) mobileQueryObj;
            oneBindMobileQueryObj.mConfirmSwitchBindTips = jSONObject.optString(UserInfoThreadConstants.DIALOG_TIPS);
            oneBindMobileQueryObj.mConfirmSwitchBindUrl = jSONObject.optString("next_url");
        }
        if (mobileQueryObj.mError == 1075) {
            mobileQueryObj.mCancelApplyTime = jSONObject.optLong("apply_time");
            mobileQueryObj.mCancelAvatarUrl = jSONObject.optString("avatar_url");
            mobileQueryObj.mCancelNickName = jSONObject.optString("nick_name");
            mobileQueryObj.mCancelToken = jSONObject.optString("token");
            mobileQueryObj.mCancelTime = jSONObject.optLong("cancel_time");
        }
    }

    public static class UserApiHelper {
        private static BDAccountUserEntity.UserInfoFactory sDefaultTtUserInfoFactory = new BDAccountUserEntity.UserInfoFactory();

        public static void onStatusError(UserApiObj userApiObj, JSONObject jSONObject) {
            if (jSONObject != null) {
                if (jSONObject.has("error_code")) {
                    userApiObj.mError = jSONObject.optInt("error_code", userApiObj.mError);
                } else if (jSONObject.has("code")) {
                    userApiObj.mError = jSONObject.optInt("code", userApiObj.mError);
                }
                userApiObj.mErrorMsg = jSONObject.optString(UserInfoThreadConstants.DESCRIPTION);
                String strOptString = jSONObject.optString("name");
                if (UserInfoThreadConstants.CONNECT_SWITCH.equals(strOptString) || UserInfoThreadConstants.CONNECT_EXIST.equals(strOptString)) {
                    userApiObj.errorTip = jSONObject.optString(UserInfoThreadConstants.DESCRIPTION);
                    userApiObj.confirmTip = jSONObject.optString(UserInfoThreadConstants.DIALOG_TIPS);
                    userApiObj.authToken = jSONObject.optString("auth_token");
                }
                if (userApiObj.mError == 1075) {
                    userApiObj.mCancelApplyTime = jSONObject.optLong("apply_time");
                    userApiObj.mCancelAvatarUrl = jSONObject.optString("avatar_url");
                    userApiObj.mCancelNickName = jSONObject.optString("nick_name");
                    userApiObj.mCancelToken = jSONObject.optString("token");
                    userApiObj.mCancelTime = jSONObject.optLong("cancel_time");
                }
                if (userApiObj.mError == 1041) {
                    userApiObj.mConflictUser = new BindConflictUser();
                    BindConflictUser.extract(userApiObj.mConflictUser, jSONObject);
                }
            }
        }

        public static UserApiResponse fromUserApiObj(UserApiObj userApiObj, boolean z, int i) {
            UserApiResponse userApiResponse = new UserApiResponse(z, i);
            userApiResponse.error = userApiObj.mError;
            userApiResponse.errorMsg = userApiObj.mErrorMsg;
            userApiResponse.errorTip = userApiObj.errorTip;
            userApiResponse.confirmTip = userApiObj.confirmTip;
            userApiResponse.authToken = userApiObj.authToken;
            userApiResponse.userInfo = userApiObj.info;
            return userApiResponse;
        }

        public static IBDAccountUserEntity parseUserInfo(JSONObject jSONObject) throws Exception {
            return sDefaultTtUserInfoFactory.parseUserInfo(jSONObject);
        }

        public static void extractUserInfo(JSONObject jSONObject, JSONObject jSONObject2, UserApiObj userApiObj) throws Exception {
            IBDAccountUserEntity userInfo = parseUserInfo(jSONObject);
            if (userInfo != null) {
                userApiObj.info = userInfo;
            }
        }

        public static IBDAccountUserEntity parseUser(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
            return parseUserInfo(jSONObject);
        }
    }
}
