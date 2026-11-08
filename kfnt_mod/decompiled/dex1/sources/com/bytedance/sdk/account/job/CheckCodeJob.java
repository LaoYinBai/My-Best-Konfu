package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.CheckCodeCallback;
import com.bytedance.sdk.account.api.response.CheckCodeResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utils.Utils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CheckCodeJob extends BaseAccountApi<CheckCodeResponse> {
    private JSONObject rawResponse;
    private String ticket;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CheckCodeResponse checkCodeResponse) {
    }

    private CheckCodeJob(Context context, ApiRequest apiRequest, CheckCodeCallback checkCodeCallback) {
        super(context, apiRequest, checkCodeCallback);
    }

    public static CheckCodeJob checkCode(Context context, String str, String str2, int i, CheckCodeCallback checkCodeCallback) {
        return new CheckCodeJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCheckCode()).parameters(getParams(str, str2, i)).post(), checkCodeCallback);
    }

    public static CheckCodeJob checkCode(Context context, String str, String str2, int i, Map map, CheckCodeCallback checkCodeCallback) {
        return new CheckCodeJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCheckCode()).parameters(getParams(str, str2, i), map).post(), checkCodeCallback);
    }

    public static CheckCodeJob checkEmailCode(Context context, String str, String str2, int i, Map map, String str3, CheckCodeCallback checkCodeCallback) {
        return new CheckCodeJob(context, new ApiRequest.Builder().url(Utils.getUrl(BDAccountNetApi.Account.getEmailCheckCode(), str3)).parameters(getEmailParams(str, str2, i), map).post(), checkCodeCallback);
    }

    protected static Map<String, String> getParams(String str, String str2, int i) {
        HashMap map = new HashMap();
        map.put("mobile", StringUtils.encryptWithXor(str));
        map.put("code", StringUtils.encryptWithXor(str2));
        map.put("type", String.valueOf(i));
        map.put("mix_mode", "1");
        return map;
    }

    protected static Map<String, String> getEmailParams(String str, String str2, int i) {
        HashMap map = new HashMap();
        map.put("email", StringUtils.encryptWithXor(str));
        map.put("code", StringUtils.encryptWithXor(str2));
        map.put("type", String.valueOf(i));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CheckCodeResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CheckCodeResponse checkCodeResponse = new CheckCodeResponse(z, 1019);
        if (z) {
            checkCodeResponse.ticket = this.ticket;
        } else {
            checkCodeResponse.error = apiResponse.mError;
            checkCodeResponse.errorMsg = apiResponse.mErrorMsg;
        }
        checkCodeResponse.result = this.rawResponse;
        return checkCodeResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        this.rawResponse = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.ticket = jSONObject2.optString("ticket");
        this.rawResponse = jSONObject;
    }
}
