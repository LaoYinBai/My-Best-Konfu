package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.DeleteDeviceCallback;
import com.bytedance.sdk.account.api.response.DeleteDeviceResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class DeleteDeviceJob extends BaseAccountApi<DeleteDeviceResponse> {
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
    }

    private DeleteDeviceJob(Context context, ApiRequest apiRequest, DeleteDeviceCallback deleteDeviceCallback) {
        super(context, apiRequest, deleteDeviceCallback);
    }

    public static DeleteDeviceJob deleteDevice(Context context, String str, DeleteDeviceCallback deleteDeviceCallback) {
        return new DeleteDeviceJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getDelDevicePath()).parameter("del_did", str).post(), deleteDeviceCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public DeleteDeviceResponse transformResponse(boolean z, ApiResponse apiResponse) {
        DeleteDeviceResponse deleteDeviceResponse = new DeleteDeviceResponse(z, BaseApiResponse.API_DELETE_DEVICE);
        if (!z) {
            deleteDeviceResponse.error = apiResponse.mError;
            deleteDeviceResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return deleteDeviceResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(DeleteDeviceResponse deleteDeviceResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.DELETE_LOGIN_DEVICE, null, null, deleteDeviceResponse, this.mApiCall);
    }
}
