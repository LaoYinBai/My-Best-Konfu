package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.EmailRegisterQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.EmailRegisterQueryCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailRegisterApiThread extends BaseAccountApi<MobileApiResponse<EmailRegisterQueryObj>> {
    private EmailRegisterQueryObj queryObj;

    public EmailRegisterApiThread(Context context, ApiRequest apiRequest, EmailRegisterQueryObj emailRegisterQueryObj, EmailRegisterQueryCallback emailRegisterQueryCallback) {
        super(context, apiRequest, emailRegisterQueryCallback);
        this.queryObj = emailRegisterQueryObj;
    }

    public static EmailRegisterApiThread emailRegister(Context context, String str, String str2, String str3, String str4, EmailRegisterQueryCallback emailRegisterQueryCallback) {
        EmailRegisterQueryObj emailRegisterQueryObj = new EmailRegisterQueryObj(str, str2, str3, str4);
        return new EmailRegisterApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getEmailRegister()).parameters(getParams(emailRegisterQueryObj)).post(), emailRegisterQueryObj, emailRegisterQueryCallback);
    }

    protected static Map<String, String> getParams(EmailRegisterQueryObj emailRegisterQueryObj) {
        HashMap map = new HashMap();
        map.put("email", StringUtils.encryptWithXor(emailRegisterQueryObj.mEmail));
        if (!TextUtils.isEmpty(emailRegisterQueryObj.mCode)) {
            map.put("code", emailRegisterQueryObj.mCode);
        }
        map.put("password", StringUtils.encryptWithXor(emailRegisterQueryObj.mPassword));
        if (!TextUtils.isEmpty(emailRegisterQueryObj.mRecaptchaToken)) {
            map.put("recaptcha_token", emailRegisterQueryObj.mRecaptchaToken);
        }
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<EmailRegisterQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1009, this.queryObj);
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
    public void onSendEvent(MobileApiResponse<EmailRegisterQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.EMAIL_REGISTER, "email", null, mobileApiResponse, this.mApiCall);
    }
}
