package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.GetAccountInfoCallback;
import com.bytedance.sdk.account.api.response.GetAccountInfoResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetAccountInfoJob extends BaseAccountApi<GetAccountInfoResponse> {
    private GetAccountInfoResponse accountInfoResponse;

    private GetAccountInfoJob(Context context, ApiRequest apiRequest, GetAccountInfoCallback getAccountInfoCallback) {
        super(context, apiRequest, getAccountInfoCallback);
    }

    public static GetAccountInfoJob getAccountInfo(Context context, GetAccountInfoCallback getAccountInfoCallback) {
        return new GetAccountInfoJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Platform.getAccountUserInfo()).get(), getAccountInfoCallback);
    }

    public static GetAccountInfoJob getNewAccountInfo(Context context, String str, GetAccountInfoCallback getAccountInfoCallback) {
        return new GetAccountInfoJob(context, new ApiRequest.Builder().parameter("scene", str).url(BDAccountNetApi.Platform.getNewAccountUserInfo()).get(), getAccountInfoCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public GetAccountInfoResponse transformResponse(boolean z, ApiResponse apiResponse) {
        GetAccountInfoResponse getAccountInfoResponse = this.accountInfoResponse;
        if (getAccountInfoResponse == null) {
            getAccountInfoResponse = new GetAccountInfoResponse(z, BaseApiResponse.API_GET_ACCOUNT_INFO);
        } else {
            getAccountInfoResponse.success = z;
        }
        if (!z) {
            getAccountInfoResponse.error = apiResponse.mError;
            getAccountInfoResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return getAccountInfoResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        this.accountInfoResponse = new GetAccountInfoResponse(false, BaseApiResponse.API_GET_ACCOUNT_INFO);
        this.accountInfoResponse.result = jSONObject2;
        if (jSONObject.has("name")) {
            this.accountInfoResponse.errorName = jSONObject.optString("name");
        } else if (jSONObject.has("error_name")) {
            this.accountInfoResponse.errorName = jSONObject.optString("error_name");
        }
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.accountInfoResponse = new GetAccountInfoResponse(true, BaseApiResponse.API_GET_ACCOUNT_INFO);
        this.accountInfoResponse.result = jSONObject;
        this.accountInfoResponse.userInfo = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(GetAccountInfoResponse getAccountInfoResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.GET_ACCOUNT_INFO, null, null, getAccountInfoResponse, this.mApiCall);
    }
}
