package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.EmailSendCodeQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.EmailSendCodeCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailSendCodeApiThread extends BaseAccountApi<MobileApiResponse<EmailSendCodeQueryObj>> {
    private EmailSendCodeQueryObj queryObj;

    private EmailSendCodeApiThread(Context context, ApiRequest apiRequest, EmailSendCodeQueryObj emailSendCodeQueryObj, EmailSendCodeCallback emailSendCodeCallback) {
        super(context, apiRequest, emailSendCodeCallback);
        this.queryObj = emailSendCodeQueryObj;
    }

    public static EmailSendCodeApiThread emailSendCode(Context context, String str, String str2, String str3, int i, String str4, Map map, String str5, EmailSendCodeCallback emailSendCodeCallback) {
        EmailSendCodeQueryObj emailSendCodeQueryObj = new EmailSendCodeQueryObj(str, str2, str3, i, str4, map, str5);
        return new EmailSendCodeApiThread(context, sendCodeRequest(emailSendCodeQueryObj).url(Utils.getUrl(BDAccountNetApi.Account.getEmailSendCode(), str5)).post(), emailSendCodeQueryObj, emailSendCodeCallback);
    }

    private static ApiRequest.Builder sendCodeRequest(EmailSendCodeQueryObj emailSendCodeQueryObj) {
        return new ApiRequest.Builder().parameters(getParams(emailSendCodeQueryObj), emailSendCodeQueryObj.mExtendInfo);
    }

    protected static Map<String, String> getParams(EmailSendCodeQueryObj emailSendCodeQueryObj) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(emailSendCodeQueryObj.mEmail)) {
            map.put("email", StringUtils.encryptWithXor(emailSendCodeQueryObj.mEmail));
        }
        if (!TextUtils.isEmpty(emailSendCodeQueryObj.mCaptcha)) {
            map.put("captcha", emailSendCodeQueryObj.mCaptcha);
        }
        map.put("type", StringUtils.encryptWithXor(String.valueOf(emailSendCodeQueryObj.mType)));
        if (!TextUtils.isEmpty(emailSendCodeQueryObj.mPassWord)) {
            map.put("password", StringUtils.encryptWithXor(emailSendCodeQueryObj.mPassWord));
        }
        if (!TextUtils.isEmpty(emailSendCodeQueryObj.mNext)) {
            map.put("next", emailSendCodeQueryObj.mNext);
        }
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<EmailSendCodeQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1024, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.jsonResult = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<EmailSendCodeQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.EMAIL_SEND_CODE, "email", this.apiRequest.parameter("type"), mobileApiResponse, this.mApiCall);
    }
}
