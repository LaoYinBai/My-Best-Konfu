package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.EmailRegisterVerifyQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.EmailRegisterVerifyCallback;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class EmailRegisterVerifyApiThread extends BaseAccountApi<MobileApiResponse<EmailRegisterVerifyQueryObj>> {
    private EmailRegisterVerifyQueryObj queryObj;

    private EmailRegisterVerifyApiThread(Context context, ApiRequest apiRequest, EmailRegisterVerifyQueryObj emailRegisterVerifyQueryObj, EmailRegisterVerifyCallback emailRegisterVerifyCallback) {
        super(context, apiRequest, emailRegisterVerifyCallback);
        this.queryObj = emailRegisterVerifyQueryObj;
    }

    public static EmailRegisterVerifyApiThread emailRegisterVerify(Context context, String str, String str2, int i, Map map, String str3, EmailRegisterVerifyCallback emailRegisterVerifyCallback) {
        EmailRegisterVerifyQueryObj emailRegisterVerifyQueryObj = new EmailRegisterVerifyQueryObj(str, str2, i, map, str3);
        return new EmailRegisterVerifyApiThread(context, new ApiRequest.Builder().parameters(getParams(emailRegisterVerifyQueryObj), emailRegisterVerifyQueryObj.mExtendInfo).url(Utils.getUrl(BDAccountNetApi.Account.getEmailRegisterVerifyPath(), str3)).post(), emailRegisterVerifyQueryObj, emailRegisterVerifyCallback);
    }

    public static EmailRegisterVerifyApiThread emailRegisterVerifyLogin(Context context, String str, String str2, int i, Map map, String str3, EmailRegisterVerifyCallback emailRegisterVerifyCallback) {
        EmailRegisterVerifyQueryObj emailRegisterVerifyQueryObj = new EmailRegisterVerifyQueryObj(str, str2, i, map, str3);
        return new EmailRegisterVerifyApiThread(context, new ApiRequest.Builder().parameters(getParams(emailRegisterVerifyQueryObj), emailRegisterVerifyQueryObj.mExtendInfo).url(Utils.getUrl(BDAccountNetApi.Account.getEmailRegisterVerifyLoginPath(), str3)).post(), emailRegisterVerifyQueryObj, emailRegisterVerifyCallback);
    }

    protected static Map<String, String> getParams(EmailRegisterVerifyQueryObj emailRegisterVerifyQueryObj) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(emailRegisterVerifyQueryObj.mEmail)) {
            map.put("email", StringUtils.encryptWithXor(emailRegisterVerifyQueryObj.mEmail));
        }
        map.put("type", StringUtils.encryptWithXor(String.valueOf(emailRegisterVerifyQueryObj.mType)));
        if (!TextUtils.isEmpty(emailRegisterVerifyQueryObj.mCode)) {
            map.put("code", emailRegisterVerifyQueryObj.mCode);
        }
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<EmailRegisterVerifyQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1025, this.queryObj);
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
    public void onSendEvent(MobileApiResponse<EmailRegisterVerifyQueryObj> mobileApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.EMAIL_REGISTER_VERIFY, "email", this.apiRequest.parameter("type"), mobileApiResponse, this.mApiCall);
    }
}
