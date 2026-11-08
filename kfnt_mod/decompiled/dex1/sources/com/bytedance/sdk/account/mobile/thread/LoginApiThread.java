package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.LoginQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.LoginQueryCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class LoginApiThread extends BaseAccountApi<MobileApiResponse<LoginQueryObj>> {
    private LoginQueryObj obj;

    private LoginApiThread(Context context, ApiRequest apiRequest, LoginQueryObj loginQueryObj, LoginQueryCallback loginQueryCallback) {
        super(context, apiRequest, loginQueryCallback);
        this.obj = loginQueryObj;
    }

    public static LoginApiThread login(Context context, String str, String str2, String str3, LoginQueryCallback loginQueryCallback) {
        LoginQueryObj loginQueryObj = new LoginQueryObj(str, str2, str3);
        return new LoginApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getUserLogin()).parameters(getParams(loginQueryObj)).checkSupportMultiLogin().post(), loginQueryObj, loginQueryCallback);
    }

    protected static Map<String, String> getParams(LoginQueryObj loginQueryObj) {
        HashMap map = new HashMap();
        map.put("mobile", StringUtils.encryptWithXor(loginQueryObj.mMobile));
        if (!TextUtils.isEmpty(loginQueryObj.mCaptcha)) {
            map.put("captcha", loginQueryObj.mCaptcha);
        }
        map.put("password", StringUtils.encryptWithXor(loginQueryObj.mPassword));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<LoginQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1007, this.obj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.obj, jSONObject);
        this.obj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<LoginQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.LOGIN, "mobile", null, mobileApiResponse, this.mApiCall);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.obj.mUserInfo = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
        this.obj.jsonResult = jSONObject;
    }
}
