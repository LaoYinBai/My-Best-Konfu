package com.bytedance.sdk.account.sso;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.PlatformAuthTokenResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class PlatformAuthTokenThread extends BaseAccountApi<PlatformAuthTokenResponse> {
    private PlatformAuthTokenObj queryObj;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(PlatformAuthTokenResponse platformAuthTokenResponse) {
    }

    private PlatformAuthTokenThread(Context context, ApiRequest apiRequest, AbsApiCall<PlatformAuthTokenResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
        this.queryObj = new PlatformAuthTokenObj();
    }

    public static PlatformAuthTokenThread withAuthCode(Context context, String str, String str2, String str3, String str4, String str5, String str6, Map map, AbsApiCall<PlatformAuthTokenResponse> absApiCall) {
        return new PlatformAuthTokenThread(context, ApiHelper.createSsoBuilder(str2, str4, str6, str3, null, str, map).parameter("access_token_secret", str5).url(BDAccountNetApi.Account.getMergeAuthPath()).get(), absApiCall);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public PlatformAuthTokenResponse transformResponse(boolean z, ApiResponse apiResponse) {
        PlatformAuthTokenResponse platformAuthTokenResponse = new PlatformAuthTokenResponse(z, 10006);
        if (z) {
            platformAuthTokenResponse.token = this.queryObj.token;
        } else {
            platformAuthTokenResponse.errorMsg = this.queryObj.mErrorMsg;
            platformAuthTokenResponse.error = this.queryObj.mError;
        }
        return platformAuthTokenResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.apiError(this.queryObj, jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.token = jSONObject2.optString("token");
    }

    static class PlatformAuthTokenObj extends ApiObj {
        String token;

        PlatformAuthTokenObj() {
        }
    }
}
