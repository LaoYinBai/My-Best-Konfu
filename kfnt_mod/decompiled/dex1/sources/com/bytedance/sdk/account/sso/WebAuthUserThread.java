package com.bytedance.sdk.account.sso;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class WebAuthUserThread extends BaseAccountApi<UserApiResponse> {
    private ApiWebAuthObj queryObj;

    public static class ApiWebAuthObj extends UserApiObj {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(UserApiResponse userApiResponse) {
    }

    private WebAuthUserThread(Context context, ApiRequest apiRequest, AbsApiCall<UserApiResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
        this.queryObj = new ApiWebAuthObj();
    }

    public static WebAuthUserThread withAuthUser(Context context, String str, AbsApiCall<UserApiResponse> absApiCall) {
        return new WebAuthUserThread(context, ApiHelper.createSsoBuilder(str, null, null, null, null, null, null).url(BDAccountNetApi.Platform.getUserinfoUrl()).get(), absApiCall);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public UserApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        UserApiResponse userApiResponseFromUserApiObj = ApiHelper.UserApiHelper.fromUserApiObj(this.queryObj, z, 0);
        if (z) {
            userApiResponseFromUserApiObj.userInfo = this.queryObj.info;
        } else {
            userApiResponseFromUserApiObj.error = this.queryObj.mError;
            userApiResponseFromUserApiObj.errorMsg = this.queryObj.mErrorMsg;
        }
        return userApiResponseFromUserApiObj;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.UserApiHelper.onStatusError(this.queryObj, jSONObject);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        ApiHelper.UserApiHelper.extractUserInfo(jSONObject, jSONObject2, this.queryObj);
    }
}
