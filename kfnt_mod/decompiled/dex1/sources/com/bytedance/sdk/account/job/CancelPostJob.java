package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.CancelPostCallback;
import com.bytedance.sdk.account.api.response.CancelPostResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CancelPostJob extends BaseAccountApi<CancelPostResponse> {
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CancelPostResponse cancelPostResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
    }

    public CancelPostJob(Context context, ApiRequest apiRequest, CancelPostCallback cancelPostCallback) {
        super(context, apiRequest, cancelPostCallback);
    }

    public static CancelPostJob cancelPost(Context context, String str, String str2, String str3, String str4, CancelPostCallback cancelPostCallback) {
        return new CancelPostJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCancelPost()).parameter("id_uri1", str).parameter("id_uri2", str2).parameter("apply_uri", str3).parameter("ticket", str4).post(), cancelPostCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CancelPostResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CancelPostResponse cancelPostResponse = new CancelPostResponse(z, 10007);
        if (!z) {
            cancelPostResponse.error = apiResponse.mError;
            cancelPostResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return cancelPostResponse;
    }
}
