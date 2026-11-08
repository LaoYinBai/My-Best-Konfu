package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.SwitchTicketCallback;
import com.bytedance.sdk.account.api.response.SwitchTicketResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.user.IBDAccountUserEntity;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class SwitchTicketJob extends BaseAccountApi<SwitchTicketResponse> {
    IBDAccountUserEntity userEntity;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(SwitchTicketResponse switchTicketResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public SwitchTicketJob(Context context, ApiRequest apiRequest, SwitchTicketCallback switchTicketCallback) {
        super(context, apiRequest, switchTicketCallback);
    }

    public static SwitchTicketJob switchTicket(Context context, String str, SwitchTicketCallback switchTicketCallback) {
        return new SwitchTicketJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getSwitchTicket()).parameter("cipher", str).post(), switchTicketCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public SwitchTicketResponse transformResponse(boolean z, ApiResponse apiResponse) {
        SwitchTicketResponse switchTicketResponse = new SwitchTicketResponse(z, 1020);
        if (z) {
            switchTicketResponse.userInfo = this.userEntity;
        } else {
            switchTicketResponse.error = apiResponse.mError;
            switchTicketResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return switchTicketResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.userEntity = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
    }
}
