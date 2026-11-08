package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.msdk.adapter.config.TTBaseAdapterConfiguration;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.CheckQRCodeStatusCallback;
import com.bytedance.sdk.account.api.response.CheckQRCodeStatusResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CheckQRCodeStatusJob extends BaseAccountApi {
    private CheckQRCodeStatusResponse mCheckQRCodeStatusResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(BaseApiResponse baseApiResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public CheckQRCodeStatusJob(Context context, ApiRequest apiRequest, AbsApiCall absApiCall) {
        super(context, apiRequest, absApiCall);
    }

    public static CheckQRCodeStatusJob checkQRCodeStatus(Context context, String str, String str2, CheckQRCodeStatusCallback checkQRCodeStatusCallback) {
        return new CheckQRCodeStatusJob(context, new ApiRequest.Builder().parameter("token", str).parameter("service", str2).url(BDAccountNetApi.Account.getQRCodeStatusPath()).get(), checkQRCodeStatusCallback);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected BaseApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CheckQRCodeStatusResponse checkQRCodeStatusResponse = this.mCheckQRCodeStatusResponse;
        if (checkQRCodeStatusResponse == null) {
            checkQRCodeStatusResponse = new CheckQRCodeStatusResponse(z, BaseApiResponse.API_GET_QR_CODE_STATUS);
        } else {
            checkQRCodeStatusResponse.success = z;
        }
        if (!z) {
            checkQRCodeStatusResponse.error = apiResponse.mError;
            checkQRCodeStatusResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return checkQRCodeStatusResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.mCheckQRCodeStatusResponse = new CheckQRCodeStatusResponse(true, BaseApiResponse.API_GET_QR_CODE_STATUS);
        this.mCheckQRCodeStatusResponse.qrCode = jSONObject2.optString("qrcode");
        this.mCheckQRCodeStatusResponse.status = jSONObject2.optString("status");
        this.mCheckQRCodeStatusResponse.appName = jSONObject2.optString(TTBaseAdapterConfiguration.APP_NAME_EXTRA_KEY);
        this.mCheckQRCodeStatusResponse.webName = jSONObject2.optString("web_name");
        this.mCheckQRCodeStatusResponse.qrCodeUrl = jSONObject2.optString("qrcode_index_url");
        this.mCheckQRCodeStatusResponse.token = jSONObject2.optString("token");
        this.mCheckQRCodeStatusResponse.redirectUrl = jSONObject2.optString("redirect_url");
        JSONObject jSONObjectOptJSONObject = jSONObject2.optJSONObject("user_data");
        if (jSONObjectOptJSONObject != null) {
            jSONObject.put("data", jSONObjectOptJSONObject);
            this.mCheckQRCodeStatusResponse.mUserInfo = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObjectOptJSONObject);
        }
    }
}
