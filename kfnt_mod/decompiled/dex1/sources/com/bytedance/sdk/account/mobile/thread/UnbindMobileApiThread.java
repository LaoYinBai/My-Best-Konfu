package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.UnbindMobileQueryObj;
import com.bytedance.sdk.account.mobile.thread.call.UnbindMobileCallback;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class UnbindMobileApiThread extends BaseAccountApi<MobileApiResponse<UnbindMobileQueryObj>> {
    private UnbindMobileQueryObj queryObj;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<UnbindMobileQueryObj> mobileApiResponse) {
    }

    private UnbindMobileApiThread(Context context, ApiRequest apiRequest, UnbindMobileQueryObj unbindMobileQueryObj, UnbindMobileCallback unbindMobileCallback) {
        super(context, apiRequest, unbindMobileCallback);
        this.queryObj = unbindMobileQueryObj;
    }

    public static UnbindMobileApiThread unbindMobile(Context context, String str, UnbindMobileCallback unbindMobileCallback) {
        UnbindMobileQueryObj unbindMobileQueryObj = new UnbindMobileQueryObj(str);
        return new UnbindMobileApiThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getUserUnbindMobile()).parameters(getParams(unbindMobileQueryObj)).post(), unbindMobileQueryObj, unbindMobileCallback);
    }

    protected static Map<String, String> getParams(UnbindMobileQueryObj unbindMobileQueryObj) {
        HashMap map = new HashMap();
        if (!TextUtils.isEmpty(unbindMobileQueryObj.captcha)) {
            map.put("captcha", unbindMobileQueryObj.captcha);
        }
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<UnbindMobileQueryObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 1001, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        this.queryObj.jsonResult = jSONObject;
    }
}
