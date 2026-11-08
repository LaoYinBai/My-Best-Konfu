package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.GenerateUserInfoTicketCallback;
import com.bytedance.sdk.account.api.response.GenerateUserInfoTicketResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.utility.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GenerateUserInfoTicketJob extends BaseAccountApi<GenerateUserInfoTicketResponse> {
    private String ticket;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public GenerateUserInfoTicketJob(Context context, ApiRequest apiRequest, GenerateUserInfoTicketCallback generateUserInfoTicketCallback) {
        super(context, apiRequest, generateUserInfoTicketCallback);
    }

    public static GenerateUserInfoTicketJob generateUserInfoTicket(Context context, String str, String str2, GenerateUserInfoTicketCallback generateUserInfoTicketCallback) {
        return new GenerateUserInfoTicketJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getGenerateUserInfoTicketPath()).parameter("mix_mode", "1").parameter("mobile", StringUtils.encryptWithXor(str)).parameter("code", StringUtils.encryptWithXor(str2)).get(), generateUserInfoTicketCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public GenerateUserInfoTicketResponse transformResponse(boolean z, ApiResponse apiResponse) {
        GenerateUserInfoTicketResponse generateUserInfoTicketResponse = new GenerateUserInfoTicketResponse(z, BaseApiResponse.API_GENERATE_USER_INFO_TICKET);
        if (z) {
            generateUserInfoTicketResponse.setTicket(this.ticket);
        } else {
            generateUserInfoTicketResponse.error = apiResponse.mError;
            generateUserInfoTicketResponse.errorMsg = apiResponse.mDetailErrorMsg;
        }
        return generateUserInfoTicketResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.ticket = jSONObject2.optString("ticket");
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(GenerateUserInfoTicketResponse generateUserInfoTicketResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventMobile.GENERATE_USER_INFO_TICKET, null, null, generateUserInfoTicketResponse, this.mApiCall);
    }
}
