package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.VerifyApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.thread.call.VerifyDeviceCallBack;
import com.bytedance.sdk.account.sso.ApiObj;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class VerifyDeviceThread extends BaseAccountApi<VerifyApiResponse> {
    private VerifyApiObj queryObj;

    public static class VerifyApiObj extends ApiObj {
        boolean isVerified;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(VerifyApiResponse verifyApiResponse) {
    }

    private VerifyDeviceThread(Context context, ApiRequest apiRequest, VerifyApiObj verifyApiObj, VerifyDeviceCallBack verifyDeviceCallBack) {
        super(context, apiRequest, verifyDeviceCallBack);
        this.queryObj = verifyApiObj;
    }

    public static VerifyDeviceThread verifyDevice(Context context, VerifyDeviceCallBack verifyDeviceCallBack) {
        return new VerifyDeviceThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getMobileCheckPath()).post(), new VerifyApiObj(), verifyDeviceCallBack);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public VerifyApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        VerifyApiResponse verifyApiResponse = new VerifyApiResponse(z, 1014);
        if (verifyApiResponse.success) {
            verifyApiResponse.setVerified(this.queryObj.isVerified);
        } else {
            verifyApiResponse.error = this.queryObj.mError;
            verifyApiResponse.errorMsg = this.queryObj.mErrorMsg;
        }
        return verifyApiResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.apiError(this.queryObj, jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        if (jSONObject2 != null) {
            this.queryObj.isVerified = jSONObject2.optBoolean("verified", false);
        }
    }
}
