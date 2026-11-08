package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.AuthTicketCallback;
import com.bytedance.sdk.account.api.response.AuthTicketResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.open.aweme.common.constants.ParamKeyConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetAuthTicketJob extends BaseAccountApi<AuthTicketResponse> {
    private AuthTicketResponse authTicketResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    private GetAuthTicketJob(Context context, ApiRequest apiRequest, AuthTicketCallback authTicketCallback) {
        super(context, apiRequest, authTicketCallback);
    }

    public static GetAuthTicketJob getAuthTicket(Context context, String str, String str2, AuthTicketCallback authTicketCallback) {
        return new GetAuthTicketJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getAuthTicketPath()).parameters(getParams(str, str2)).post(), authTicketCallback);
    }

    protected static Map<String, String> getParams(String str, String str2) {
        HashMap map = new HashMap();
        map.put(ParamKeyConstants.WebViewConstants.QUERY_FROM, str2);
        map.put("token", str);
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public AuthTicketResponse transformResponse(boolean z, ApiResponse apiResponse) {
        AuthTicketResponse authTicketResponse = this.authTicketResponse;
        if (authTicketResponse == null) {
            authTicketResponse = new AuthTicketResponse(z, BaseApiResponse.API_GET_AUTH_TICKET);
        } else {
            authTicketResponse.success = z;
        }
        if (!z) {
            authTicketResponse.error = apiResponse.mError;
            authTicketResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return authTicketResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.authTicketResponse = new AuthTicketResponse(true, BaseApiResponse.API_GET_AUTH_TICKET);
        this.authTicketResponse.rawData = jSONObject2;
        try {
            this.authTicketResponse.ticket = jSONObject2.optString("ticket");
            this.authTicketResponse.mobile = jSONObject2.optString("mobile");
            this.authTicketResponse.ticket_age = jSONObject2.optInt("ticket_age");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(AuthTicketResponse authTicketResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.AUTH_ONE_LOGIN_GET_TICKET, null, null, authTicketResponse, this.mApiCall);
    }
}
