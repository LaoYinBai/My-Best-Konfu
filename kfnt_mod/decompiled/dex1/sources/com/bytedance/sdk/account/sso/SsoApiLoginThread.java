package com.bytedance.sdk.account.sso;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SsoApiLoginThread extends BaseAccountApi<UserApiResponse> {
    private ApiLoginObj mQueryObj;

    public static class ApiLoginObj extends UserApiObj {
        JSONObject response;
    }

    private SsoApiLoginThread(Context context, ApiRequest apiRequest, AbsApiCall<UserApiResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
        this.mQueryObj = new ApiLoginObj();
    }

    public static SsoApiLoginThread withProfileKeyLogin(Context context, String str, String str2, String str3, String str4, Map<String, String> map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiLoginThread(context, ApiHelper.createSsoBuilder(str2, null, str4, null, str3, str, map).url(BDAccountNetApi.Platform.getSsoCallbackUrl()).checkSupportMultiLogin().get(), absApiCall);
    }

    public static SsoApiLoginThread withAccessTokenLogin(Context context, String str, String str2, String str3, String str4, Map<String, String> map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiLoginThread(context, ApiHelper.createSsoBuilder(str2, str3, str4, null, null, str, map).url(BDAccountNetApi.Platform.getSsoCallbackUrl()).checkSupportMultiLogin().get(), absApiCall);
    }

    public static SsoApiLoginThread withAccessTokenLogin(Context context, String str, String str2, String str3, String str4, String str5, Map<String, String> map, AbsApiCall<UserApiResponse> absApiCall) {
        ApiRequest.Builder builderCreateSsoBuilder = ApiHelper.createSsoBuilder(str2, str3, str5, null, null, str, map);
        if (!TextUtils.isEmpty(str4)) {
            builderCreateSsoBuilder.parameter("access_token_secret", str4);
        }
        builderCreateSsoBuilder.url(BDAccountNetApi.Platform.getSsoCallbackUrl()).checkSupportMultiLogin();
        return new SsoApiLoginThread(context, builderCreateSsoBuilder.get(), absApiCall);
    }

    public static SsoApiLoginThread withAuthCodeLogin(Context context, String str, String str2, String str3, String str4, Map<String, String> map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiLoginThread(context, ApiHelper.createSsoBuilder(str2, null, str4, str3, null, str, map).url(BDAccountNetApi.Platform.getSsoCallbackUrl()).checkSupportMultiLogin().get(), absApiCall);
    }

    public static SsoApiLoginThread withAuthCodeBindMobileLogin(Context context, String str, String str2, String str3, String str4, boolean z, boolean z2, Map<String, String> map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiLoginThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Platform.getAuthBindWithMobileLogin()).parameters(getParams(str, str2, str3, str4, z, z2, map)).post(), absApiCall);
    }

    protected static Map<String, String> getParams(String str, String str2, String str3, String str4, boolean z, boolean z2, Map<String, String> map) {
        HashMap map2 = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            map2.put("platform_app_id", str);
        }
        if (!TextUtils.isEmpty(str2)) {
            map2.put("platform", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            map2.put("code", str3);
        }
        if (!TextUtils.isEmpty(str4)) {
            map2.put("profile_key", str4);
        }
        map2.put("need_mobile", z ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
        map2.put("change_bind", z2 ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS);
        if (map != null && !map.isEmpty()) {
            for (String str5 : map.keySet()) {
                if (!TextUtils.isEmpty(str5)) {
                    map2.put(str5, map.get(str5));
                }
            }
        }
        return map2;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public UserApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        UserApiResponse userApiResponse = new UserApiResponse(z, 1);
        if (z) {
            userApiResponse.userInfo = this.mQueryObj.info;
        } else {
            userApiResponse.error = this.mQueryObj.mError;
            userApiResponse.errorMsg = this.mQueryObj.mErrorMsg;
            userApiResponse.mSharkTicket = this.mQueryObj.mSharkTicket;
            userApiResponse.setProfileKey(this.mQueryObj.mProfileKey);
            if (this.mQueryObj.mError == 1075) {
                userApiResponse.mCancelApplyTime = this.mQueryObj.mCancelApplyTime;
                userApiResponse.mCancelAvatarUrl = this.mQueryObj.mCancelAvatarUrl;
                userApiResponse.mCancelNickName = this.mQueryObj.mCancelNickName;
                userApiResponse.mCancelTime = this.mQueryObj.mCancelTime;
                userApiResponse.mCancelToken = this.mQueryObj.mCancelToken;
            }
            userApiResponse.mConflictUser = this.mQueryObj.mConflictUser;
        }
        userApiResponse.result = this.mQueryObj.response;
        return userApiResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(UserApiResponse userApiResponse) throws JSONException {
        if (TextUtils.isEmpty(userApiResponse.mRequestUrl)) {
            return;
        }
        AccountMonitorUtil.onEvent(userApiResponse.mRequestUrl.contains(BDAccountNetApi.Platform.getAuthBindWithMobileLogin()) ? AccountMonitorConstants.EventPlatform.AUTH_BIND_WITH_MOBILE_LOGIN : AccountMonitorConstants.EventPlatform.OAUTH_LOGIN_CLICK, this.apiRequest.parameter("platform"), "login", userApiResponse, this.mApiCall);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.UserApiHelper.onStatusError(this.mQueryObj, jSONObject);
        this.mQueryObj.response = jSONObject2;
        if (jSONObject != null) {
            this.mQueryObj.mProfileKey = jSONObject.optString("profile_key");
            this.mQueryObj.mSharkTicket = jSONObject.optString("shark_ticket");
        }
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        ApiHelper.UserApiHelper.extractUserInfo(jSONObject, jSONObject2, this.mQueryObj);
        this.mQueryObj.response = jSONObject;
    }
}
