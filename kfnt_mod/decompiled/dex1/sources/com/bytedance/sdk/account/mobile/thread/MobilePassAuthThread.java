package com.bytedance.sdk.account.mobile.thread;

import android.content.Context;
import android.text.TextUtils;
import com.bytedance.sdk.account.api.BDAccountNetApi;
import com.bytedance.sdk.account.api.call.MobileApiResponse;
import com.bytedance.sdk.account.execute.ApiRequest;
import com.bytedance.sdk.account.execute.ApiResponse;
import com.bytedance.sdk.account.impl.ApiHelper;
import com.bytedance.sdk.account.impl.BaseAccountApi;
import com.bytedance.sdk.account.mobile.query.MobilePassAuthObj;
import com.bytedance.sdk.account.mobile.thread.call.MobilePassAuthCallback;
import com.bytedance.sdk.account.utility.StringUtils;
import com.bytedance.sdk.account.utils.NetUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: D:\My-Best-Konfu\kfnt_mod\extracted\classes.dex */
public class MobilePassAuthThread extends BaseAccountApi<MobileApiResponse<MobilePassAuthObj>> {
    private MobilePassAuthObj queryObj;

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public void onSendEvent(MobileApiResponse<MobilePassAuthObj> mobileApiResponse) {
    }

    private MobilePassAuthThread(Context context, ApiRequest apiRequest, MobilePassAuthObj mobilePassAuthObj, MobilePassAuthCallback mobilePassAuthCallback) {
        super(context, apiRequest, mobilePassAuthCallback);
        this.queryObj = mobilePassAuthObj;
    }

    public static MobilePassAuthThread passAuth(Context context, String str, String str2, String str3, MobilePassAuthCallback mobilePassAuthCallback) {
        MobilePassAuthObj mobilePassAuthObj = new MobilePassAuthObj(str, str2, str3);
        return new MobilePassAuthThread(context, new ApiRequest.Builder().url(BDAccountNetApi.Account.getMobileAuthPath()).parameters(getParams(mobilePassAuthObj)).post(), mobilePassAuthObj, mobilePassAuthCallback);
    }

    protected static Map<String, String> getParams(MobilePassAuthObj mobilePassAuthObj) {
        HashMap map = new HashMap();
        map.put("mobile", StringUtils.encryptWithXor(mobilePassAuthObj.mMobile));
        if (!TextUtils.isEmpty(mobilePassAuthObj.mCaptcha)) {
            map.put("captcha", mobilePassAuthObj.mCaptcha);
        }
        map.put("password", StringUtils.encryptWithXor(mobilePassAuthObj.mPassword));
        map.put("mix_mode", "1");
        return map;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    public MobileApiResponse<MobilePassAuthObj> transformResponse(boolean z, ApiResponse apiResponse) {
        return new MobileApiResponse<>(z, 10003, this.queryObj);
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void onStatusError(JSONObject jSONObject, JSONObject jSONObject2) {
        ApiHelper.mobileError(this.queryObj, jSONObject);
        this.queryObj.jsonResult = jSONObject2;
    }

    @Override // com.bytedance.sdk.account.impl.BaseAccountApi
    protected void parseData(JSONObject jSONObject, JSONObject jSONObject2) throws Exception {
        try {
            this.queryObj.mToken = jSONObject2.optString("token");
        } catch (Exception e) {
            this.queryObj.mError = NetUtils.checkApiException(this.mContextRef.get(), e);
        }
        this.queryObj.jsonResult = jSONObject;
    }
}
