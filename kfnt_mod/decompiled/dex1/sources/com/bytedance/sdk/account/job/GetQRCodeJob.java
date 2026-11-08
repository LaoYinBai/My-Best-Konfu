package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.GetQRCodeCallback;
import com.bytedance.sdk.account.api.response.GetQRCodeResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetQRCodeJob extends BaseAccountApi {
    private GetQRCodeResponse mQRCodeResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(BaseApiResponse baseApiResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public GetQRCodeJob(Context context, ApiRequest apiRequest, AbsApiCall absApiCall) {
        super(context, apiRequest, absApiCall);
    }

    public static GetQRCodeJob getQRCode(Context context, String str, GetQRCodeCallback getQRCodeCallback) {
        return new GetQRCodeJob(context, new ApiRequest.Builder().parameter("service", str).url(BDAccountNetApi.Account.getQRCodePath()).get(), getQRCodeCallback);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected BaseApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        GetQRCodeResponse getQRCodeResponse = this.mQRCodeResponse;
        if (getQRCodeResponse == null) {
            getQRCodeResponse = new GetQRCodeResponse(z, BaseApiResponse.API_GET_QR_CODE);
        } else {
            getQRCodeResponse.success = z;
        }
        if (!z) {
            getQRCodeResponse.error = apiResponse.mError;
            getQRCodeResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return getQRCodeResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.mQRCodeResponse = new GetQRCodeResponse(true, BaseApiResponse.API_GET_QR_CODE);
        this.mQRCodeResponse.qrCode = jSONObject2.optString("qrcode");
        this.mQRCodeResponse.qrCodeUrl = jSONObject2.optString("qrcode_index_url");
        this.mQRCodeResponse.token = jSONObject2.optString("token");
        this.mQRCodeResponse.appName = jSONObject2.optString(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY);
        this.mQRCodeResponse.webName = jSONObject2.optString("web_name");
    }
}
