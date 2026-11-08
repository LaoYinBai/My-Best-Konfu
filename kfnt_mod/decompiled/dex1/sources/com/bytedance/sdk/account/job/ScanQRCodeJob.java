package com.bytedance.sdk.account.job;

import android.content.Context;
import com.alipay.sdk.widget.d;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.ScanQRCodeCallback;
import com.bytedance.sdk.account.api.response.ScanQRCodeResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.google.android.gms.actions.SearchIntents;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class ScanQRCodeJob extends BaseAccountApi {
    private ScanQRCodeResponse mScanQRCodeResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(BaseApiResponse baseApiResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public ScanQRCodeJob(Context context, ApiRequest apiRequest, AbsApiCall absApiCall) {
        super(context, apiRequest, absApiCall);
    }

    public static ScanQRCodeJob scanQRCode(Context context, String str, ScanQRCodeCallback scanQRCodeCallback) {
        return new ScanQRCodeJob(context, new ApiRequest.Builder().parameter("token", str).url(BDAccountNetApi.Account.getAuthorizeScanQRCodePath()).get(), scanQRCodeCallback);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected BaseApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        ScanQRCodeResponse scanQRCodeResponse = this.mScanQRCodeResponse;
        if (scanQRCodeResponse == null) {
            scanQRCodeResponse = new ScanQRCodeResponse(z, BaseApiResponse.API_SCAN_QR_CODE);
        } else {
            scanQRCodeResponse.success = z;
        }
        if (!z) {
            scanQRCodeResponse.error = apiResponse.mError;
            scanQRCodeResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return scanQRCodeResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.mScanQRCodeResponse = new ScanQRCodeResponse(true, BaseApiResponse.API_SCAN_QR_CODE);
        this.mScanQRCodeResponse.csrfToken = jSONObject2.optString("csrf_token");
        this.mScanQRCodeResponse.sourceIcon = jSONObject2.optString("source_icon");
        JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("source");
        if (jSONObjectOptJSONObject != null) {
            this.mScanQRCodeResponse.title = jSONObjectOptJSONObject.optString(d.m);
            this.mScanQRCodeResponse.desc = jSONObjectOptJSONObject.optString("desc");
            this.mScanQRCodeResponse.query = jSONObjectOptJSONObject.optString(SearchIntents.EXTRA_QUERY);
        }
    }
}
