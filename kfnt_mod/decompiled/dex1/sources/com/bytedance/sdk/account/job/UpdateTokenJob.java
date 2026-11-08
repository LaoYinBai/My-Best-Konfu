package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.UpdateTokenCallback;
import com.bytedance.sdk.account.api.response.UpdateTokenResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UpdateTokenJob extends BaseAccountApi<UpdateTokenResponse> {
    private UpdateTokenResponse updateTokenResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(UpdateTokenResponse updateTokenResponse) {
    }

    private UpdateTokenJob(Context context, ApiRequest apiRequest, UpdateTokenCallback updateTokenCallback) {
        super(context, apiRequest, updateTokenCallback);
    }

    public static UpdateTokenJob updateToken(Context context, String str, UpdateTokenCallback updateTokenCallback) {
        return new UpdateTokenJob(context, new ApiRequest.Builder().url(str).get(), updateTokenCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public UpdateTokenResponse transformResponse(boolean z, ApiResponse apiResponse) {
        UpdateTokenResponse updateTokenResponse = this.updateTokenResponse;
        if (updateTokenResponse == null) {
            updateTokenResponse = new UpdateTokenResponse(z, BaseApiResponse.API_UPDATE_TOKEN);
        } else {
            updateTokenResponse.success = z;
        }
        if (!z) {
            updateTokenResponse.error = apiResponse.mError;
            updateTokenResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return updateTokenResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        this.updateTokenResponse = new UpdateTokenResponse(false, BaseApiResponse.API_UPDATE_TOKEN);
        this.updateTokenResponse.result = jSONObject2;
        this.updateTokenResponse.errorName = jSONObject.optString("error_name");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.updateTokenResponse = new UpdateTokenResponse(true, BaseApiResponse.API_UPDATE_TOKEN);
        this.updateTokenResponse.result = jSONObject;
    }
}
