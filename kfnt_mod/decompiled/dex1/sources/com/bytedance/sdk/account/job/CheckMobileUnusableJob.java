package com.bytedance.sdk.account.job;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.CheckMobileUnusableCallback;
import com.bytedance.sdk.account.api.response.CheckMobileUnusableResponse;
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
public class CheckMobileUnusableJob extends BaseAccountApi<CheckMobileUnusableResponse> {
    private boolean mIsUnusable;
    private boolean mIsVerified;
    private boolean mMnoSupport;
    private String mTicket;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public CheckMobileUnusableJob(Context context, ApiRequest apiRequest, CheckMobileUnusableCallback checkMobileUnusableCallback) {
        super(context, apiRequest, checkMobileUnusableCallback);
    }

    public static CheckMobileUnusableJob checkMobileUnusable(Context context, String str, String str2, String str3, CheckMobileUnusableCallback checkMobileUnusableCallback) {
        return new CheckMobileUnusableJob(context, new ApiRequest.Builder().parameters(getParams(str, str2, str3)).url(BDAccountNetApi.Account.getCheckMobileUnusablePath()).post(), checkMobileUnusableCallback);
    }

    protected static Map<String, String> getParams(String str, String str2, String str3) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(str)) {
            map.put("mobile", StringUtils.encryptWithXor(str));
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("auth_token", str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            map.put("not_login_ticket", str3);
        }
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CheckMobileUnusableResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CheckMobileUnusableResponse checkMobileUnusableResponse = new CheckMobileUnusableResponse(z, 10023);
        if (z) {
            checkMobileUnusableResponse.setUnusable(this.mIsUnusable);
            checkMobileUnusableResponse.setVerified(this.mIsVerified);
            checkMobileUnusableResponse.setMnoSupport(this.mMnoSupport);
            checkMobileUnusableResponse.setTicket(this.mTicket);
        } else {
            checkMobileUnusableResponse.error = apiResponse.mError;
            checkMobileUnusableResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return checkMobileUnusableResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.mIsUnusable = jSONObject2.optBoolean("is_unusable");
        this.mIsVerified = jSONObject2.optBoolean("is_verified");
        this.mMnoSupport = jSONObject2.optBoolean("mno_support");
        this.mTicket = jSONObject2.optString("ticket");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CheckMobileUnusableResponse checkMobileUnusableResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.CHECK_MOBILE_UNUSABLE, null, null, checkMobileUnusableResponse, this.mApiCall);
    }
}
