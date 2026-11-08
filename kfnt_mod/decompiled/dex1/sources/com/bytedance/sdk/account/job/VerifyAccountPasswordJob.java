package com.bytedance.sdk.account.job;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.VerifyAccountPasswordCallback;
import com.bytedance.sdk.account.api.response.VerifyAccountPasswordResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class VerifyAccountPasswordJob extends BaseAccountApi<VerifyAccountPasswordResponse> {
    private String captcha;
    private String ticket;

    public VerifyAccountPasswordJob(Context context, ApiRequest apiRequest, VerifyAccountPasswordCallback verifyAccountPasswordCallback) {
        super(context, apiRequest, verifyAccountPasswordCallback);
    }

    public static VerifyAccountPasswordJob verifyAccountPassword(Context context, String str, String str2, String str3, String str4, String str5, VerifyAccountPasswordCallback verifyAccountPasswordCallback) {
        return new VerifyAccountPasswordJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getVerifyAccountPasswordPath()).parameters(getParams(str, str2, str3, str4, str5)).post(), verifyAccountPasswordCallback);
    }

    protected static Map<String, String> getParams(String str, String str2, String str3, String str4, String str5) {
        HashMap map = new HashMap();
        map.put("mix_mode", "1");
        if (!TextUtils.isEmpty(str)) {
            map.put("username", StringUtils.encryptWithXor(str));
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("mobile", StringUtils.encryptWithXor(str2));
        }
        if (!TextUtils.isEmpty(str3)) {
            map.put("email", StringUtils.encryptWithXor(str3));
        }
        if (!TextUtils.isEmpty(str4)) {
            map.put("password", StringUtils.encryptWithXor(str4));
        }
        if (!TextUtils.isEmpty(str5)) {
            map.put("verify_ticket", StringUtils.encryptWithXor(str5));
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public VerifyAccountPasswordResponse transformResponse(boolean z, ApiResponse apiResponse) {
        VerifyAccountPasswordResponse verifyAccountPasswordResponse = new VerifyAccountPasswordResponse(z, BaseApiResponse.API_VERIFY_ACCOUNT_PASSWORD);
        if (z) {
            verifyAccountPasswordResponse.setTicket(this.ticket);
        } else {
            verifyAccountPasswordResponse.setCaptcha(this.captcha);
            verifyAccountPasswordResponse.error = apiResponse.mError;
            verifyAccountPasswordResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return verifyAccountPasswordResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        this.captcha = jSONObject.optString("captcha");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.ticket = jSONObject2.optString("ticket");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(VerifyAccountPasswordResponse verifyAccountPasswordResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.VERIFY_ACCOUNT_PASSWORD, null, null, verifyAccountPasswordResponse, this.mApiCall);
    }
}
