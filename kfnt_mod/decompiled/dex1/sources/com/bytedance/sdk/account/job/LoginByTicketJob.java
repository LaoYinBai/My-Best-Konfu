package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.LoginByTicketCallback;
import com.bytedance.sdk.account.api.response.LoginByTicketResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.sso.ApiObj;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LoginByTicketJob extends BaseAccountApi<LoginByTicketResponse> {
    private LoginByTicketResponse loginResponse;
    private ApiObj mQueryObj;

    private LoginByTicketJob(Context context, ApiRequest apiRequest, LoginByTicketCallback loginByTicketCallback) {
        super(context, apiRequest, loginByTicketCallback);
        this.mQueryObj = new ApiObj();
    }

    public static LoginByTicketJob loginByAuthTicket(Context context, String str, LoginByTicketCallback loginByTicketCallback) {
        return new LoginByTicketJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getLoginByTicketPath()).parameters(getParams(str)).post(), loginByTicketCallback);
    }

    protected static Map<String, String> getParams(String str) {
        HashMap map = new HashMap();
        map.put("ticket", str);
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public LoginByTicketResponse transformResponse(boolean z, ApiResponse apiResponse) {
        LoginByTicketResponse loginByTicketResponse = this.loginResponse;
        if (loginByTicketResponse == null) {
            loginByTicketResponse = new LoginByTicketResponse(z, BaseApiResponse.API_LOGIN_BY_TICKET);
        } else {
            loginByTicketResponse.success = z;
        }
        if (!z) {
            loginByTicketResponse.error = apiResponse.mError;
            loginByTicketResponse.errorMsg = apiResponse.mErrorMsg;
            if (this.mQueryObj.mError == 1075) {
                loginByTicketResponse.mCancelApplyTime = this.mQueryObj.mCancelApplyTime;
                loginByTicketResponse.mCancelAvatarUrl = this.mQueryObj.mCancelAvatarUrl;
                loginByTicketResponse.mCancelNickName = this.mQueryObj.mCancelNickName;
                loginByTicketResponse.mCancelTime = this.mQueryObj.mCancelTime;
                loginByTicketResponse.mCancelToken = this.mQueryObj.mCancelToken;
            }
        }
        return loginByTicketResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        this.loginResponse = new LoginByTicketResponse(false, BaseApiResponse.API_LOGIN_BY_TICKET);
        this.loginResponse.rawData = jSONObject;
        this.loginResponse.mErrorCaptcha = jSONObject.optString("captcha");
        ApiHelper.apiError(this.mQueryObj, jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.loginResponse = new LoginByTicketResponse(true, BaseApiResponse.API_LOGIN_BY_TICKET);
        this.loginResponse.rawData = jSONObject2;
        this.loginResponse.mUserInfo = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
        this.loginResponse.mErrorCaptcha = jSONObject2.optString("captcha");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(LoginByTicketResponse loginByTicketResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.AUTH_ONE_LOGIN_BY_TICKET, null, null, loginByTicketResponse, this.mApiCall);
    }
}
