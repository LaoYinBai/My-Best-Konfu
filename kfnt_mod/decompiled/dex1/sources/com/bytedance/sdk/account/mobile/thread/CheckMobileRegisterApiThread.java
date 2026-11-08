package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.call.CheckMobileRegisterResponse;
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
public class CheckMobileRegisterApiThread extends BaseAccountApi<CheckMobileRegisterResponse> {
    private boolean isRegistered;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public CheckMobileRegisterApiThread(Context context, ApiRequest apiRequest, AbsApiCall<CheckMobileRegisterResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
    }

    public static CheckMobileRegisterApiThread checkMobileRegister(Context context, String str, AbsApiCall<CheckMobileRegisterResponse> absApiCall) {
        return new CheckMobileRegisterApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCheckMobileRegisteredPath()).parameters(getParams(str)).post(), absApiCall);
    }

    protected static Map<String, String> getParams(String str) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            map.put("mobile", StringUtils.encryptWithXor(str));
        }
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CheckMobileRegisterResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CheckMobileRegisterResponse checkMobileRegisterResponse = new CheckMobileRegisterResponse(z, BaseApiResponse.API_CHECK_MOBILE_REGISTER);
        if (z) {
            checkMobileRegisterResponse.isRegistered = this.isRegistered;
        } else {
            checkMobileRegisterResponse.error = apiResponse.mError;
            checkMobileRegisterResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return checkMobileRegisterResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.isRegistered = jSONObject2.optBoolean("is_registered");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CheckMobileRegisterResponse checkMobileRegisterResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.CHECK_MOBILE_REGISTERED, null, null, checkMobileRegisterResponse, this.mApiCall);
    }
}
