package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.CancelDoCallback;
import com.bytedance.sdk.account.api.response.CancelDoResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CancelDoJob extends BaseAccountApi<CancelDoResponse> {
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CancelDoResponse cancelDoResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
    }

    private CancelDoJob(Context context, ApiRequest apiRequest, CancelDoCallback cancelDoCallback) {
        super(context, apiRequest, cancelDoCallback);
    }

    public static CancelDoJob cancelDo(Context context, boolean z, CancelDoCallback cancelDoCallback) {
        return new CancelDoJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCancelDo()).parameter("type", z ? "1" : OnekeyLoginConstants.CU_RESULT_SUCCESS).post(), cancelDoCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CancelDoResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CancelDoResponse cancelDoResponse = new CancelDoResponse(z, 10008);
        if (!z) {
            cancelDoResponse.error = apiResponse.mError;
            cancelDoResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return cancelDoResponse;
    }
}
