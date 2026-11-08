package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.AuthorizeQRCodeLoginCallback;
import com.bytedance.sdk.account.api.response.AuthorizeQRCodeLoginResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class AuthorizeQRCodeLoginJob extends BaseAccountApi {
    private AuthorizeQRCodeLoginResponse mAuthorizeQRCodeLoginResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(BaseApiResponse baseApiResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public AuthorizeQRCodeLoginJob(Context context, ApiRequest apiRequest, AbsApiCall absApiCall) {
        super(context, apiRequest, absApiCall);
    }

    public static AuthorizeQRCodeLoginJob authorizeLogin(Context context, String str, String str2, String str3, AuthorizeQRCodeLoginCallback authorizeQRCodeLoginCallback) {
        return new AuthorizeQRCodeLoginJob(context, new ApiRequest.Builder().parameter("token", str).parameter("decision", str2).parameter("csrf_token", str3).url(BDAccountNetApi.Account.getAuthorizeQRCodeToLoginPath()).get(), authorizeQRCodeLoginCallback);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected BaseApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        AuthorizeQRCodeLoginResponse authorizeQRCodeLoginResponse = this.mAuthorizeQRCodeLoginResponse;
        if (authorizeQRCodeLoginResponse == null) {
            authorizeQRCodeLoginResponse = new AuthorizeQRCodeLoginResponse(z, BaseApiResponse.API_AUTHORIZE_QR_CODE_LOGIN);
        } else {
            authorizeQRCodeLoginResponse.success = z;
        }
        if (!z) {
            authorizeQRCodeLoginResponse.error = apiResponse.mError;
            authorizeQRCodeLoginResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return authorizeQRCodeLoginResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.mAuthorizeQRCodeLoginResponse = new AuthorizeQRCodeLoginResponse(true, BaseApiResponse.API_AUTHORIZE_QR_CODE_LOGIN);
    }
}
