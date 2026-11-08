package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.CheckPwdCallback;
import com.bytedance.sdk.account.api.response.CheckPwdResponse;
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
public class CheckPwdJob extends BaseAccountApi<CheckPwdResponse> {
    private CheckPwdResponse checkPwdResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    private CheckPwdJob(Context context, ApiRequest apiRequest, CheckPwdCallback checkPwdCallback) {
        super(context, apiRequest, checkPwdCallback);
    }

    public static CheckPwdJob checkPwd(Context context, String str, CheckPwdCallback checkPwdCallback) {
        return new CheckPwdJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getPwdCheckPath()).parameters(getParams(str)).post(), checkPwdCallback);
    }

    protected static Map<String, String> getParams(String str) {
        HashMap map = new HashMap();
        map.put("password", StringUtils.encryptWithXor(str));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CheckPwdResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CheckPwdResponse checkPwdResponse = this.checkPwdResponse;
        if (checkPwdResponse == null) {
            checkPwdResponse = new CheckPwdResponse(z, BaseApiResponse.API_CHECK_PWD);
        } else {
            checkPwdResponse.success = z;
        }
        if (!z) {
            checkPwdResponse.error = apiResponse.mError;
            checkPwdResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return checkPwdResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.checkPwdResponse = new CheckPwdResponse(true, BaseApiResponse.API_CHECK_PWD);
        this.checkPwdResponse.mErrorCaptcha = jSONObject2.optString("captcha");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CheckPwdResponse checkPwdResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.CHECK_PASSWORD, null, null, checkPwdResponse, this.mApiCall);
    }
}
