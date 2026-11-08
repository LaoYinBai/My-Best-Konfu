package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.CheckEvnCallback;
import com.bytedance.sdk.account.api.response.CheckEnvResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CheckEnvJob extends BaseAccountApi<CheckEnvResponse> {
    private CheckEnvResponse checkEnvResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    private CheckEnvJob(Context context, ApiRequest apiRequest, CheckEvnCallback checkEvnCallback) {
        super(context, apiRequest, checkEvnCallback);
    }

    public static CheckEnvJob checkEnv(Context context, int i, CheckEvnCallback checkEvnCallback) {
        return new CheckEnvJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCheckEnvPath()).parameter("scene", String.valueOf(i)).get(), checkEvnCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CheckEnvResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CheckEnvResponse checkEnvResponse = this.checkEnvResponse;
        if (checkEnvResponse == null) {
            checkEnvResponse = new CheckEnvResponse(z, 10010);
        } else {
            checkEnvResponse.success = z;
        }
        if (!z) {
            checkEnvResponse.error = apiResponse.mError;
            checkEnvResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return checkEnvResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.checkEnvResponse = new CheckEnvResponse(true, 10010);
        this.checkEnvResponse.rawData = jSONObject2;
        this.checkEnvResponse.mErrorCaptcha = jSONObject2.optString("captcha");
        this.checkEnvResponse.safe = jSONObject2.optString("safe");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CheckEnvResponse checkEnvResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.CHECK_ENV, null, null, checkEnvResponse, this.mApiCall);
    }
}
