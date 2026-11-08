package com.bytedance.sdk.account.sso;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.AbsApiCall;
import com.bytedance.sdk.account.api.call.BaseApiResponse;
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
public class SsoApiUnbindTrhead extends BaseAccountApi<BaseApiResponse> {
    UnbinObj queryObj;

    public static class UnbinObj extends ApiObj {
        JSONObject response;
    }

    private SsoApiUnbindTrhead(Context context, ApiRequest apiRequest, AbsApiCall<BaseApiResponse> absApiCall) {
        super(context, apiRequest, absApiCall);
        this.queryObj = new UnbinObj();
    }

    public static SsoApiUnbindTrhead withUnbind(Context context, String str, AbsApiCall<BaseApiResponse> absApiCall) {
        return new SsoApiUnbindTrhead(context, new ApiRequest.Builder().parameter("platform", str).url(BDAccountNetApi.Platform.getUnbindUrl()).get(), absApiCall);
    }

    public static SsoApiUnbindTrhead withUnbind(Context context, String str, int i, String str2, AbsApiCall<BaseApiResponse> absApiCall) {
        return new SsoApiUnbindTrhead(context, new ApiRequest.Builder().parameters(getParams(str, i, str2)).url(BDAccountNetApi.Platform.getUnbindUrl()).get(), absApiCall);
    }

    protected static Map<String, String> getParams(String str, int i, String str2) {
        HashMap map = new HashMap();
        map.put("platform", str);
        if (i != -1) {
            map.put("verify_type", String.valueOf(i));
        }
        if (!TextUtils.isEmpty(str2)) {
            map.put("verified_ticket", str2);
        }
        return map;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected BaseApiResponse transformResponse(boolean z, ApiResponse apiResponse) {
        BaseApiResponse baseApiResponse = new BaseApiResponse(z, 2);
        if (!z) {
            baseApiResponse.error = this.queryObj.mError;
            baseApiResponse.errorMsg = this.queryObj.mErrorMsg;
        }
        baseApiResponse.result = this.queryObj.response;
        return baseApiResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.apiError(this.queryObj, jSONObject, jSONObject2);
        this.queryObj.response = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.response = jSONObject;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(BaseApiResponse baseApiResponse) throws JSONException {
        AccountMonitorUtil.onEvent(AccountMonitorConstants.EventPlatform.OAUTH_UBIND_CLICK, this.apiRequest.parameter("platform"), AccountMonitorConstants.EventPlatform.SCENE_UNBIND, baseApiResponse, this.mApiCall);
    }
}
