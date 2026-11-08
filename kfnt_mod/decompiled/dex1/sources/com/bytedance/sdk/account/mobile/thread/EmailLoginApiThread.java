package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.EmailLoginQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.EmailLoginQueryCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailLoginApiThread extends BaseAccountApi<MobileApiResponse<EmailLoginQueryObj>> {
    private EmailLoginQueryObj queryObj;

    public EmailLoginApiThread(Context context, ApiRequest apiRequest, EmailLoginQueryObj emailLoginQueryObj, EmailLoginQueryCallback emailLoginQueryCallback) {
        super(context, apiRequest, emailLoginQueryCallback);
        this.queryObj = emailLoginQueryObj;
    }

    public static EmailLoginApiThread emailLogin(Context context, String str, String str2, String str3, EmailLoginQueryCallback emailLoginQueryCallback) {
        EmailLoginQueryObj emailLoginQueryObj = new EmailLoginQueryObj(str, str2, str3);
        return new EmailLoginApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getEmailLogin()).parameters(getParams(emailLoginQueryObj)).checkSupportMultiLogin().post(), emailLoginQueryObj, emailLoginQueryCallback);
    }

    protected static Map<String, String> getParams(EmailLoginQueryObj emailLoginQueryObj) {
        HashMap map = new HashMap();
        map.put("email", StringUtils.encryptWithXor(emailLoginQueryObj.mEmail));
        if (!TextUtils.isEmpty(emailLoginQueryObj.mCaptcha)) {
            map.put("captcha", emailLoginQueryObj.mCaptcha);
        }
        map.put("password", StringUtils.encryptWithXor(emailLoginQueryObj.mPassword));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<EmailLoginQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
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
    public void onSendEvent(MobileApiResponse<EmailLoginQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.LOGIN, "email", null, mobileApiResponse, this.mApiCall);
    }
}
