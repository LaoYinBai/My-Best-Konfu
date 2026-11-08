package com.bytedance.sdk.account;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.LogoutApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.sso.ApiObj;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ApiLogoutThread extends BaseAccountApi<LogoutApiResponse> {
    private String mLoginScene;
    private ApiObj queryObj;

    private ApiLogoutThread(Context context, ApiRequest apiRequest, String str, AbsApiCall<LogoutApiResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
        this.queryObj = new ApiObj();
        this.mLoginScene = str;
    }

    public static ApiLogoutThread logout(Context context, String str, Map map, AbsApiCall<LogoutApiResponse> absApiCall) {
        return new ApiLogoutThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Platform.getUserLogoutUrl()).parameter("logout_from", str).parameters(map).get(), str, absApiCall);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public LogoutApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        LogoutApiResponse logoutApiResponse = new LogoutApiResponse(z, 10001);
        if (z) {
            logoutApiResponse.setSessionKey(this.queryObj.mSessionKey);
        } else {
            logoutApiResponse.error = this.queryObj.mError;
            logoutApiResponse.errorMsg = this.queryObj.mErrorMsg;
        }
        logoutApiResponse.mLogoutScene = this.mLoginScene;
        return logoutApiResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.apiError(this.queryObj, jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.mSessionKey = jSONObject.optString("session_key");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(LogoutApiResponse logoutApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EVENT_LOGOUT, null, null, logoutApiResponse, this.mApiCall);
    }
}
