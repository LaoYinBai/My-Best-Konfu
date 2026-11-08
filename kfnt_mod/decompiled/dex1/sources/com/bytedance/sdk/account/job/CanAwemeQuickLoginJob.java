package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.CanAwemeQuickLoginCallback;
import com.bytedance.sdk.account.api.response.CanAwemeQuickLoginResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CanAwemeQuickLoginJob extends BaseAccountApi<CanAwemeQuickLoginResponse> {
    private CanAwemeQuickLoginResponse canAwemeQuickLoginResponse;

    public CanAwemeQuickLoginJob(Context context, ApiRequest apiRequest, CanAwemeQuickLoginCallback canAwemeQuickLoginCallback) {
        super(context, apiRequest, canAwemeQuickLoginCallback);
    }

    public static CanAwemeQuickLoginJob canAwemeQuickLogin(Context context, CanAwemeQuickLoginCallback canAwemeQuickLoginCallback) {
        return new CanAwemeQuickLoginJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCanAwemeQuickLoginPath()).get(), canAwemeQuickLoginCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CanAwemeQuickLoginResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CanAwemeQuickLoginResponse canAwemeQuickLoginResponse = this.canAwemeQuickLoginResponse;
        if (canAwemeQuickLoginResponse == null) {
            canAwemeQuickLoginResponse = new CanAwemeQuickLoginResponse(z, BaseApiResponse.API_CAN_AWEME_QUICK_LOGIN);
        } else {
            canAwemeQuickLoginResponse.success = z;
        }
        if (!z) {
            canAwemeQuickLoginResponse.error = apiResponse.mError;
            canAwemeQuickLoginResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return canAwemeQuickLoginResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        this.canAwemeQuickLoginResponse = new CanAwemeQuickLoginResponse(false, BaseApiResponse.API_CAN_AWEME_QUICK_LOGIN);
        this.canAwemeQuickLoginResponse.result = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.canAwemeQuickLoginResponse = new CanAwemeQuickLoginResponse(true, BaseApiResponse.API_CAN_AWEME_QUICK_LOGIN);
        this.canAwemeQuickLoginResponse.canAwemeQuickLogin = jSONObject2.optBoolean("can_aweme_quick_login");
        this.canAwemeQuickLoginResponse.result = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CanAwemeQuickLoginResponse canAwemeQuickLoginResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.CAN_AWEME_QUICK_LOGIN, null, null, canAwemeQuickLoginResponse, this.mApiCall);
    }
}
