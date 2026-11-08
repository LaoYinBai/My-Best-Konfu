package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.RegisterQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.RegisterCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class RegisterApiThread extends BaseAccountApi<MobileApiResponse<RegisterQueryObj>> {
    private RegisterQueryObj queryObj;

    public RegisterApiThread(Context context, ApiRequest apiRequest, RegisterQueryObj registerQueryObj, RegisterCallback registerCallback) {
        super(context, apiRequest, registerCallback);
        this.queryObj = registerQueryObj;
    }

    public static RegisterApiThread register(Context context, String str, String str2, String str3, String str4, RegisterCallback registerCallback) {
        RegisterQueryObj registerQueryObj = new RegisterQueryObj(str, str2, str3, str4);
        return new RegisterApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getUserRegister()).parameters(getParams(registerQueryObj)).post(), registerQueryObj, registerCallback);
    }

    protected static Map<String, String> getParams(RegisterQueryObj registerQueryObj) {
        HashMap map = new HashMap();
        map.put("mobile", StringUtils.encryptWithXor(registerQueryObj.mMobile));
        map.put("captcha", registerQueryObj.mCaptcha);
        map.put("code", StringUtils.encryptWithXor(String.valueOf(registerQueryObj.mCode)));
        map.put("password", StringUtils.encryptWithXor(registerQueryObj.mPassword));
        map.put("type", StringUtils.encryptWithXor(String.valueOf(registerQueryObj.mScenario)));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<RegisterQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1004, this.queryObj);
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
    public void onSendEvent(MobileApiResponse<RegisterQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.REGISTER, "mobile", null, mobileApiResponse, this.mApiCall);
    }
}
