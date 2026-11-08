package com.bytedance.sdk.account.sso;

import android.content.Context;
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
public class SsoApiSwitchBindThread extends BaseAccountApi<UserApiResponse> {
    private SwitchObj queryObj;

    public static class SwitchObj extends UserApiObj {
    }

    private SsoApiSwitchBindThread(Context context, ApiRequest apiRequest, AbsApiCall<UserApiResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
        this.queryObj = new SwitchObj();
    }

    public static SsoApiSwitchBindThread withAuthTokenSwitchBind(Context context, String str, String str2, String str3, String str4, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiSwitchBindThread(context, ApiHelper.createSsoBuilder(str2, null, str4, null, null, str, map).parameter("auth_token", str3).url(BDAccountNetApi.Platform.getSsoSwitchBind()).get(), absApiCall);
    }

    public static SsoApiSwitchBindThread withAccessTokenSwitchBind(Context context, String str, String str2, String str3, String str4, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiSwitchBindThread(context, ApiHelper.createSsoBuilder(str2, str3, str4, null, null, str, map).url(BDAccountNetApi.Platform.getSsoSwitchBind()).get(), absApiCall);
    }

    public static SsoApiSwitchBindThread withAuthCodeSwitchBind(Context context, String str, String str2, String str3, String str4, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiSwitchBindThread(context, ApiHelper.createSsoBuilder(str2, null, str4, str3, null, str, map).url(BDAccountNetApi.Platform.getSsoSwitchBind()).get(), absApiCall);
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
            if (this.queryObj.mError == 1075) {
                userApiResponseFromUserApiObj.mCancelApplyTime = this.queryObj.mCancelApplyTime;
                userApiResponseFromUserApiObj.mCancelAvatarUrl = this.queryObj.mCancelAvatarUrl;
                userApiResponseFromUserApiObj.mCancelNickName = this.queryObj.mCancelNickName;
                userApiResponseFromUserApiObj.mCancelTime = this.queryObj.mCancelTime;
                userApiResponseFromUserApiObj.mCancelToken = this.queryObj.mCancelToken;
            }
        }
        return userApiResponseFromUserApiObj;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.UserApiHelper.onStatusError(this.queryObj, jSONObject);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        ApiHelper.UserApiHelper.extractUserInfo(jSONObject, jSONObject2, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(UserApiResponse userApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventPlatform.OAUTH_SWITCH_CLICK, this.apiRequest.parameter("platform"), userApiResponse.success ? "success" : AccountMonitorConstants.CommonParameter.RESULT_FAIL, userApiResponse, this.mApiCall);
    }
}
