package com.bytedance.sdk.account.job;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
import com.bytedance.sdk.account.api.callback.GetAvailableWaysCallback;
import com.bytedance.sdk.account.api.response.GetAvailableWaysResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.monitor.AccountMonitorConstants;
import com.bytedance.sdk.account.monitor.AccountMonitorUtil;
import com.bytedance.sdk.account.platform.base.OnekeyLoginConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class GetAvailableWaysJob extends BaseAccountApi<GetAvailableWaysResponse> {
    private GetAvailableWaysResponse getAvailableWaysResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    public GetAvailableWaysJob(Context context, ApiRequest apiRequest, GetAvailableWaysCallback getAvailableWaysCallback) {
        super(context, apiRequest, getAvailableWaysCallback);
    }

    public static GetAvailableWaysJob getAvailableWays(Context context, int i, String str, GetAvailableWaysCallback getAvailableWaysCallback) {
        return new GetAvailableWaysJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getAvailableWaysPath()).parameters(getParams(i, str)).post(), getAvailableWaysCallback);
    }

    protected static Map<String, String> getParams(int i, String str) {
        HashMap map = new HashMap();
        if (i == 0) {
            map.put("need_limit_platform", OnekeyLoginConstants.CU_RESULT_SUCCESS);
        } else {
            map.put("need_limit_platform", "1");
        }
        if (!TextUtils.isEmpty(str)) {
            map.put("not_login_ticket", str);
        }
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public GetAvailableWaysResponse transformResponse(boolean z, ApiResponse apiResponse) {
        GetAvailableWaysResponse getAvailableWaysResponse;
        if (this.getAvailableWaysResponse != null) {
            getAvailableWaysResponse = this.getAvailableWaysResponse;
            getAvailableWaysResponse.success = z;
        } else {
            getAvailableWaysResponse = new GetAvailableWaysResponse(z, BaseApiResponse.API_GET_AVAILABLE_WAYS);
        }
        if (!z) {
            getAvailableWaysResponse.error = apiResponse.mError;
            getAvailableWaysResponse.errorMsg = apiResponse.mErrorMsg;
        }
        this.getAvailableWaysResponse = null;
        return getAvailableWaysResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        GetAvailableWaysResponse getAvailableWaysResponse = new GetAvailableWaysResponse(true, BaseApiResponse.API_GET_AVAILABLE_WAYS);
        this.getAvailableWaysResponse = getAvailableWaysResponse;
        getAvailableWaysResponse.setEmail(jSONObject2.optString("email"));
        getAvailableWaysResponse.setHasEmail(jSONObject2.optBoolean("has_email"));
        getAvailableWaysResponse.setHasMobile(jSONObject2.optBoolean("has_mobile"));
        getAvailableWaysResponse.setHasOauth(jSONObject2.optBoolean("has_oauth"));
        getAvailableWaysResponse.setHasPwd(jSONObject2.optBoolean("has_pwd"));
        getAvailableWaysResponse.setMostDevice(jSONObject2.optBoolean("is_most_device"));
        getAvailableWaysResponse.setMobile(jSONObject2.optString("mobile"));
        getAvailableWaysResponse.setToken(jSONObject2.optString("token"));
        ArrayList arrayList = new ArrayList();
        JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("oauth_platforms");
        if (jSONArrayOptJSONArray != null && jSONArrayOptJSONArray.length() > 0) {
            int length = jSONArrayOptJSONArray.length();
            for (int i = 0; i < length; i++) {
                arrayList.add(jSONArrayOptJSONArray.getString(i));
            }
        }
        getAvailableWaysResponse.setOauthPlatforms(arrayList);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(GetAvailableWaysResponse getAvailableWaysResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventAccount.GET_AVAILABLE_WAYS, null, null, getAvailableWaysResponse, this.mApiCall);
    }
}
