package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.callback.CancelCloseAccountCallback;
import com.bytedance.sdk.account.api.response.CancelCloseAccountResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CancelCloseAccountJob extends BaseAccountApi<CancelCloseAccountResponse> {
    private JSONObject rawResponse;
    IBDAccountUserEntity userEntity;

    public CancelCloseAccountJob(Context context, ApiRequest apiRequest, AbsApiCall absApiCall) {
        super(context, apiRequest, absApiCall);
    }

    public static CancelCloseAccountJob cancelCloseAccountWithToken(Context context, String str, CancelCloseAccountCallback cancelCloseAccountCallback) {
        return new CancelCloseAccountJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCancelCLoseAccountPath()).parameter("token", str).post(), cancelCloseAccountCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CancelCloseAccountResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CancelCloseAccountResponse cancelCloseAccountResponse = new CancelCloseAccountResponse(z, 1032);
        if (z) {
            cancelCloseAccountResponse.userInfo = this.userEntity;
        } else {
            cancelCloseAccountResponse.error = apiResponse.mError;
            cancelCloseAccountResponse.errorMsg = apiResponse.mErrorMsg;
        }
        cancelCloseAccountResponse.result = this.rawResponse;
        return cancelCloseAccountResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        this.rawResponse = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.userEntity = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
        this.rawResponse = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CancelCloseAccountResponse cancelCloseAccountResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.CANCEL_LOGIN, null, null, cancelCloseAccountResponse, this.mApiCall);
    }
}
