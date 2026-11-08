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
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SsoApiBindThread extends BaseAccountApi<UserApiResponse> {
    private SwitchObj queryObj;

    public static class SwitchObj extends UserApiObj {
        JSONObject response;
    }

    private SsoApiBindThread(Context context, ApiRequest apiRequest, AbsApiCall<UserApiResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
        this.queryObj = new SwitchObj();
    }

    public static SsoApiBindThread withAccessTokenBind(Context context, String str, String str2, String str3, String str4, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiBindThread(context, ApiHelper.createSsoBuilder(str2, str3, str4, null, null, str, map).url(BDAccountNetApi.Platform.getSsoCallbackBind()).get(), absApiCall);
    }

    public static SsoApiBindThread withAccessTokenBind(Context context, String str, String str2, String str3, String str4, String str5, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        ApiRequest.Builder builderCreateSsoBuilder = ApiHelper.createSsoBuilder(str2, str3, str5, null, null, str, map);
        if (!TextUtils.isEmpty(str4)) {
            builderCreateSsoBuilder.parameter("access_token_secret", str4);
        }
        builderCreateSsoBuilder.url(BDAccountNetApi.Platform.getSsoCallbackBind());
        return new SsoApiBindThread(context, builderCreateSsoBuilder.get(), absApiCall);
    }

    public static SsoApiBindThread withAuthCodeBind(Context context, String str, String str2, String str3, String str4, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiBindThread(context, ApiHelper.createSsoBuilder(str2, null, str4, str3, null, str, map).url(BDAccountNetApi.Platform.getSsoCallbackBind()).get(), absApiCall);
    }

    public static SsoApiBindThread withProfileKeyBind(Context context, String str, String str2, String str3, String str4, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiBindThread(context, ApiHelper.createSsoBuilder(str2, null, str4, null, str3, str, map).url(BDAccountNetApi.Platform.getSsoCallbackBind()).get(), absApiCall);
    }

    public static SsoApiBindThread bindWithMobile(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, Map<String, String> map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiBindThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Platform.getBindWithMobilePath()).parameters(ApiHelper.getBindWithMobileParams(str, str2, str3, str4, str5, str6, str7, str8, str9, str10, map)).post(), absApiCall);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public UserApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        UserApiResponse userApiResponseFromUserApiObj = ApiHelper.UserApiHelper.fromUserApiObj(this.queryObj, z, 0);
        if (z) {
            userApiResponseFromUserApiObj.userInfo = this.queryObj.info;
        } else {
            userApiResponseFromUserApiObj.error = this.queryObj.mError;
            userApiResponseFromUserApiObj.errorMsg = this.queryObj.mErrorMsg;
            userApiResponseFromUserApiObj.mSharkTicket = this.queryObj.mSharkTicket;
            userApiResponseFromUserApiObj.setProfileKey(this.queryObj.mProfileKey);
            if (this.queryObj.mError == 1075) {
                userApiResponseFromUserApiObj.mCancelApplyTime = this.queryObj.mCancelApplyTime;
                userApiResponseFromUserApiObj.mCancelAvatarUrl = this.queryObj.mCancelAvatarUrl;
                userApiResponseFromUserApiObj.mCancelNickName = this.queryObj.mCancelNickName;
                userApiResponseFromUserApiObj.mCancelTime = this.queryObj.mCancelTime;
                userApiResponseFromUserApiObj.mCancelToken = this.queryObj.mCancelToken;
            }
        }
        userApiResponseFromUserApiObj.result = this.queryObj.response;
        return userApiResponseFromUserApiObj;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.UserApiHelper.onStatusError(this.queryObj, jSONObject);
        this.queryObj.response = jSONObject2;
        if (jSONObject != null) {
            this.queryObj.mProfileKey = jSONObject.optString("profile_key");
            this.queryObj.mSharkTicket = jSONObject.optString("shark_ticket");
        }
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        ApiHelper.UserApiHelper.extractUserInfo(jSONObject, jSONObject2, this.queryObj);
        this.queryObj.response = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(UserApiResponse userApiResponse) throws JSONException {
        if (TextUtils.isEmpty(userApiResponse.mRequestUrl)) {
            return;
        }
        AccountMonitorUtil.onEvent(userApiResponse.mRequestUrl.contains(BDAccountNetApi.Platform.getBindWithMobilePath()) ? AccountMonitorConstants.EventPlatform.BIND_WITH_MOBILE : AccountMonitorConstants.EventPlatform.OAUTH_BIND_CLICK, this.apiRequest.parameter("platform"), AccountMonitorConstants.EventPlatform.SCENE_BIND, userApiResponse, this.mApiCall);
    }
}
