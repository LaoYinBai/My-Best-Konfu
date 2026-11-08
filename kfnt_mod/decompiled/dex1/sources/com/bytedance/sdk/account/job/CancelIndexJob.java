package com.bytedance.sdk.account.job;

import android.content.Context;
import com.baidu.mobads.sdk.internal.a;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.callback.CancelIndexCallback;
import com.bytedance.sdk.account.api.response.CancelIndexResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class CancelIndexJob extends BaseAccountApi<CancelIndexResponse> {
    private CancelIndexResponse cancelIndexResponse;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(CancelIndexResponse cancelIndexResponse) {
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
    }

    private CancelIndexJob(Context context, ApiRequest apiRequest, CancelIndexCallback cancelIndexCallback) {
        super(context, apiRequest, cancelIndexCallback);
    }

    public static CancelIndexJob cancelIndex(Context context, CancelIndexCallback cancelIndexCallback) {
        return new CancelIndexJob(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getCancelIndex()).get(), cancelIndexCallback);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public CancelIndexResponse transformResponse(boolean z, ApiResponse apiResponse) {
        CancelIndexResponse cancelIndexResponse = this.cancelIndexResponse;
        if (cancelIndexResponse == null) {
            cancelIndexResponse = new CancelIndexResponse(z, 10009);
        } else {
            cancelIndexResponse.success = z;
        }
        if (!z) {
            cancelIndexResponse.error = apiResponse.mError;
            cancelIndexResponse.errorMsg = apiResponse.mErrorMsg;
        }
        return cancelIndexResponse;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        CancelIndexResponse cancelIndexResponse = new CancelIndexResponse(false, 10009);
        this.cancelIndexResponse = cancelIndexResponse;
        cancelIndexResponse.rawData = jSONObject2;
        cancelIndexResponse.hasMobile = jSONObject2.optBoolean("has_mobile");
        cancelIndexResponse.identified = jSONObject2.optBoolean("identified");
        cancelIndexResponse.protocol = jSONObject2.optInt("protocol");
        if (jSONObject2.has("audit_status")) {
            cancelIndexResponse.auditStatus = Boolean.valueOf(jSONObject2.optBoolean("audit_status"));
        }
        JSONArray jSONArrayOptJSONArray = jSONObject2.optJSONArray("conditions");
        if (jSONArrayOptJSONArray != null) {
            cancelIndexResponse.conditionsList = new ArrayList();
            for (int i = 0; i < jSONArrayOptJSONArray.length(); i++) {
                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                CancelIndexResponse.Conditions conditions = new CancelIndexResponse.Conditions();
                if (jSONObjectOptJSONObject != null) {
                    conditions.text = jSONObjectOptJSONObject.optString(a.f3090b);
                }
                cancelIndexResponse.conditionsList.add(conditions);
            }
        }
    }
}
