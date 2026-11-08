package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.UserPasswordLoginQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.UserPasswordLoginQueryCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UserPasswordLoginApiThread extends BaseAccountApi<MobileApiResponse<UserPasswordLoginQueryObj>> {
    private UserPasswordLoginQueryObj queryObj;

    private UserPasswordLoginApiThread(Context context, ApiRequest apiRequest, UserPasswordLoginQueryObj userPasswordLoginQueryObj, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        super(context, apiRequest, userPasswordLoginQueryCallback);
        this.queryObj = userPasswordLoginQueryObj;
    }

    public static UserPasswordLoginApiThread userPasswordLogin(Context context, String str, String str2, String str3, String str4, String str5, String str6, String str7, int i, UserPasswordLoginQueryCallback userPasswordLoginQueryCallback) {
        UserPasswordLoginQueryObj userPasswordLoginQueryObj = new UserPasswordLoginQueryObj(str2, str3, str, str4, str5, str6, str7, i);
        return new UserPasswordLoginApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getUserPasswordLogin()).parameters(getParams(userPasswordLoginQueryObj)).post(), userPasswordLoginQueryObj, userPasswordLoginQueryCallback);
    }

    protected static Map<String, String> getParams(UserPasswordLoginQueryObj userPasswordLoginQueryObj) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(userPasswordLoginQueryObj.mEmail)) {
            map.put("email", StringUtils.encryptWithXor(userPasswordLoginQueryObj.mEmail));
        }
        if (!TextUtils.isEmpty(userPasswordLoginQueryObj.mMobile)) {
            map.put("mobile", StringUtils.encryptWithXor(userPasswordLoginQueryObj.mMobile));
        }
        if (!TextUtils.isEmpty(userPasswordLoginQueryObj.mUserName)) {
            map.put("username", StringUtils.encryptWithXor(userPasswordLoginQueryObj.mUserName));
        }
        if (!TextUtils.isEmpty(userPasswordLoginQueryObj.mAccount)) {
            map.put("account", StringUtils.encryptWithXor(userPasswordLoginQueryObj.mAccount));
        }
        if (!TextUtils.isEmpty(userPasswordLoginQueryObj.mToken)) {
            map.put("token", userPasswordLoginQueryObj.mToken);
        }
        if (!TextUtils.isEmpty(userPasswordLoginQueryObj.mCaptcha)) {
            map.put("captcha", userPasswordLoginQueryObj.mCaptcha);
        }
        if (userPasswordLoginQueryObj.mScene > 0) {
            map.put("scene", StringUtils.encryptWithXor(String.valueOf(userPasswordLoginQueryObj.mScene)));
        }
        map.put("password", StringUtils.encryptWithXor(userPasswordLoginQueryObj.mPassword));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<UserPasswordLoginQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1016, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.mUserInfo = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
        this.queryObj.jsonResult = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<UserPasswordLoginQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.LOGIN, null, null, mobileApiResponse, this.mApiCall);
    }
}
