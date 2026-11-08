package com.bytedance.sdk.account.job;

import android.content.Context;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.TVCheckQRConnectCallback;
import com.bytedance.sdk.account.api.response.TVCheckQRConnectResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class TVCheckQRConnectJob extends BaseAccountApi<TVCheckQRConnectResponse> {
    TVCheckQRConnectResponse tvCheckQRConnectResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public TVCheckQRConnectJob(Context context, ApiRequest apiRequest, TVCheckQRConnectCallback tVCheckQRConnectCallback) {
        super(context, apiRequest, tVCheckQRConnectCallback);
    }

    public static TVCheckQRConnectJob checkQRConnect(Context context, String str, String str2, TVCheckQRConnectCallback tVCheckQRConnectCallback) {
        return new TVCheckQRConnectJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getTVCheckQrconnectPath()).parameters(getParams(str, str2)).post(), tVCheckQRConnectCallback);
    }

    protected static Map<String, String> getParams(String str, String str2) {
        HashMap map = new HashMap();
        map.put("token", str);
        map.put("url", str2);
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public TVCheckQRConnectResponse transformResponse(boolean z, ApiResponse apiResponse) {
        TVCheckQRConnectResponse tVCheckQRConnectResponse = this.tvCheckQRConnectResponse;
        if (tVCheckQRConnectResponse == null) {
            tVCheckQRConnectResponse = new TVCheckQRConnectResponse(z, 1030);
        } else {
            tVCheckQRConnectResponse.success = z;
        }
        if (!z) {
            tVCheckQRConnectResponse.error = apiResponse.mError;
            tVCheckQRConnectResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return tVCheckQRConnectResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.tvCheckQRConnectResponse = new TVCheckQRConnectResponse(true, 1031);
        this.tvCheckQRConnectResponse.rawData = jSONObject2;
        if (jSONObject2.has("status")) {
            this.tvCheckQRConnectResponse.status = jSONObject2.optString("status");
            this.tvCheckQRConnectResponse.qrcode = jSONObject2.optString("qrcode");
            this.tvCheckQRConnectResponse.token = jSONObject2.optString("token");
            return;
        }
        this.tvCheckQRConnectResponse.userInfo = ApiHelper.UserApiHelper.parseUser(jSONObject, jSONObject2);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(TVCheckQRConnectResponse tVCheckQRConnectResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventTVQR.CHECK_QRCONNECT, null, null, tVCheckQRConnectResponse, this.mApiCall);
    }
}
