package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.GetTvQRCodeCallback;
import com.bytedance.sdk.account.api.response.GetTvQRCodeResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetTvQRCodeJob extends BaseAccountApi<GetTvQRCodeResponse> {
    GetTvQRCodeResponse getTvQRCodeResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    private GetTvQRCodeJob(Context context, ApiRequest apiRequest, GetTvQRCodeCallback getTvQRCodeCallback) {
        super(context, apiRequest, getTvQRCodeCallback);
    }

    public static GetTvQRCodeJob getTvQRCode(Context context, String str, GetTvQRCodeCallback getTvQRCodeCallback) {
        return new GetTvQRCodeJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getTVQrcodePath()).parameters(getParams(str)).post(), getTvQRCodeCallback);
    }

    protected static Map<String, String> getParams(String str) {
        HashMap map = new HashMap();
        map.put("url", str);
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public GetTvQRCodeResponse transformResponse(boolean z, ApiResponse apiResponse) {
        GetTvQRCodeResponse getTvQRCodeResponse = this.getTvQRCodeResponse;
        if (getTvQRCodeResponse == null) {
            getTvQRCodeResponse = new GetTvQRCodeResponse(z, 1030);
        } else {
            getTvQRCodeResponse.success = z;
        }
        if (!z) {
            getTvQRCodeResponse.error = apiResponse.mError;
            getTvQRCodeResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return getTvQRCodeResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.getTvQRCodeResponse = new GetTvQRCodeResponse(true, 1030);
        this.getTvQRCodeResponse.status = jSONObject2.optString("status");
        this.getTvQRCodeResponse.qrcode = jSONObject2.optString("qrcode");
        this.getTvQRCodeResponse.token = jSONObject2.optString("token");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(GetTvQRCodeResponse getTvQRCodeResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventTVQR.GET_QRCODE, null, null, getTvQRCodeResponse, this.mApiCall);
    }
}
