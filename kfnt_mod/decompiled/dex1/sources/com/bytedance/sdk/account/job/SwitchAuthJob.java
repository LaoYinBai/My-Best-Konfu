package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.callback.SwitchAuthCallback;
import com.bytedance.sdk.account.api.response.SwitchAuthResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SwitchAuthJob extends BaseAccountApi<SwitchAuthResponse> {
    IBDAccountUserEntity userEntity;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(SwitchAuthResponse switchAuthResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public SwitchAuthJob(Context context, ApiRequest apiRequest, AbsApiCall absApiCall) {
        super(context, apiRequest, absApiCall);
    }

    public static SwitchAuthJob switchAuth(Context context, String str, SwitchAuthCallback switchAuthCallback) {
        return new SwitchAuthJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getSwitchAuthPath()).parameter("to_user_id", str).get(), switchAuthCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public SwitchAuthResponse transformResponse(boolean z, ApiResponse apiResponse) {
        SwitchAuthResponse switchAuthResponse = new SwitchAuthResponse(z, 101);
        if (z) {
            switchAuthResponse.userInfo = this.userEntity;
        } else {
            switchAuthResponse.error = apiResponse.mError;
            switchAuthResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return switchAuthResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.userEntity = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
    }
}
