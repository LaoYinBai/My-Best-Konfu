package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.UpdatePwdCallback;
import com.bytedance.sdk.account.api.response.UpdatePwdResponse;
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
public class UpdatePwdJob extends BaseAccountApi<UpdatePwdResponse> {
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
    }

    private UpdatePwdJob(Context context, ApiRequest apiRequest, UpdatePwdCallback updatePwdCallback) {
        super(context, apiRequest, updatePwdCallback);
    }

    public static UpdatePwdJob updatePwd(Context context, String str, String str2, UpdatePwdCallback updatePwdCallback) {
        return new UpdatePwdJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getUpdatePwdPath()).parameters(getParams(str, str2)).post(), updatePwdCallback);
    }

    protected static Map<String, String> getParams(String str, String str2) {
        HashMap map = new HashMap();
        map.put("current_password", StringUtils.encryptWithXor(str));
        map.put("password", StringUtils.encryptWithXor(str2));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public UpdatePwdResponse transformResponse(boolean z, ApiResponse apiResponse) {
        UpdatePwdResponse updatePwdResponse = new UpdatePwdResponse(z, BaseApiResponse.API_UPDATE_PWD);
        if (!z) {
            updatePwdResponse.error = apiResponse.mError;
            updatePwdResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return updatePwdResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(UpdatePwdResponse updatePwdResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.UPDATE_PWD, null, null, updatePwdResponse, this.mApiCall);
    }
}
