package com.bytedance.sdk.account.sso;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.UserApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SsoApiOnlyLoginThread extends BaseAccountApi<UserApiResponse> {
    private ApiLoginObj queryObj;

    public static class ApiLoginObj extends UserApiObj {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(UserApiResponse userApiResponse) {
    }

    public SsoApiOnlyLoginThread(Context context, ApiRequest apiRequest, AbsApiCall<UserApiResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
        this.queryObj = new ApiLoginObj();
    }

    public static SsoApiOnlyLoginThread withAccessTokenOnlyLogin(Context context, String str, String str2, String str3, String str4, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiOnlyLoginThread(context, ApiHelper.createSsoBuilder(str2, str3, str4, null, null, str, map).url(BDAccountNetApi.Platform.getSsoOnlyLoginUrl()).get(), absApiCall);
    }

    public static SsoApiOnlyLoginThread withAuthCodeOnlyLogin(Context context, String str, String str2, String str3, String str4, Map map, AbsApiCall<UserApiResponse> absApiCall) {
        return new SsoApiOnlyLoginThread(context, ApiHelper.createSsoBuilder(str2, null, str4, str3, null, str, map).url(BDAccountNetApi.Platform.getSsoOnlyLoginUrl()).get(), absApiCall);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public UserApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        UserApiResponse userApiResponse = new UserApiResponse(z, 1);
        if (z) {
            userApiResponse.userInfo = this.queryObj.info;
        } else {
            userApiResponse.setProfileKey(this.queryObj.mProfileKey);
            userApiResponse.error = this.queryObj.mError;
            userApiResponse.errorMsg = this.queryObj.mErrorMsg;
            userApiResponse.mSharkTicket = this.queryObj.mSharkTicket;
            if (this.queryObj.mError == 1075) {
                userApiResponse.mCancelApplyTime = this.queryObj.mCancelApplyTime;
                userApiResponse.mCancelAvatarUrl = this.queryObj.mCancelAvatarUrl;
                userApiResponse.mCancelNickName = this.queryObj.mCancelNickName;
                userApiResponse.mCancelTime = this.queryObj.mCancelTime;
                userApiResponse.mCancelToken = this.queryObj.mCancelToken;
            }
        }
        return userApiResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.UserApiHelper.onStatusError(this.queryObj, jSONObject);
        if (jSONObject != null) {
            this.queryObj.mProfileKey = jSONObject.optString("profile_key");
            this.queryObj.mSharkTicket = jSONObject.optString("shark_ticket");
        }
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        ApiHelper.UserApiHelper.extractUserInfo(jSONObject, jSONObject2, this.queryObj);
    }
}
