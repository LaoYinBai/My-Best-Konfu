package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.GetLoginDevicesCallback;
import com.bytedance.sdk.account.api.response.GetLoginDevicesResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetLoginDevicesJob extends BaseAccountApi<GetLoginDevicesResponse> {
    private GetLoginDevicesResponse loginDevicesResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    private GetLoginDevicesJob(Context context, ApiRequest apiRequest, GetLoginDevicesCallback getLoginDevicesCallback) {
        super(context, apiRequest, getLoginDevicesCallback);
    }

    public static GetLoginDevicesJob getLoginDevices(Context context, GetLoginDevicesCallback getLoginDevicesCallback) {
        return new GetLoginDevicesJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getDeviceListPath()).get(), getLoginDevicesCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public GetLoginDevicesResponse transformResponse(boolean z, ApiResponse apiResponse) {
        GetLoginDevicesResponse getLoginDevicesResponse = this.loginDevicesResponse;
        if (getLoginDevicesResponse == null) {
            getLoginDevicesResponse = new GetLoginDevicesResponse(z, BaseApiResponse.API_GET_LOGIN_DEVICES);
        } else {
            getLoginDevicesResponse.success = z;
        }
        if (!z) {
            getLoginDevicesResponse.error = apiResponse.mError;
            getLoginDevicesResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return getLoginDevicesResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.loginDevicesResponse = new GetLoginDevicesResponse(true, BaseApiResponse.API_GET_LOGIN_DEVICES);
        this.loginDevicesResponse.rawData = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(GetLoginDevicesResponse getLoginDevicesResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.GET_LOGIN_DEVICE, null, null, getLoginDevicesResponse, this.mApiCall);
    }
}
